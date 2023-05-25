package com.lelestacia.lelenime.feature.explore.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lelestacia.lelenime.core.common.displayStyle.DisplayStyle
import com.lelestacia.lelenime.core.domain.usecases.explore.IExploreUseCases
import com.lelestacia.lelenime.core.domain.usecases.settings.IUserPreferencesUseCases
import com.lelestacia.lelenime.core.model.Anime
import com.lelestacia.lelenime.feature.explore.component.header.HeaderScreenState
import com.lelestacia.lelenime.feature.explore.stateAndEvent.AnimeFilter
import com.lelestacia.lelenime.feature.explore.stateAndEvent.ExploreScreenEvent
import com.lelestacia.lelenime.feature.explore.stateAndEvent.ExploreScreenState
import com.lelestacia.lelenime.feature.explore.stateAndEvent.PopularAnimeFilter
import com.lelestacia.lelenime.feature.explore.stateAndEvent.SearchAnimeFilter
import com.lelestacia.lelenime.feature.explore.stateAndEvent.UpcomingAnimeFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class ExplorationScreenViewModel @Inject constructor(
    private val useCases: IExploreUseCases,
    private val useCasesPreferences: IUserPreferencesUseCases,
) : ViewModel() {


    private val headerState: MutableStateFlow<HeaderScreenState> =
        MutableStateFlow(HeaderScreenState())

    private val displayedStyle: MutableStateFlow<DisplayStyle> =
        MutableStateFlow(DisplayStyle.CARD)

    private val displayedAnimeType: MutableStateFlow<DisplayType> =
        MutableStateFlow(DisplayType.POPULAR)


    //  Anime Filter
    private val popularAnimeFilter: MutableStateFlow<PopularAnimeFilter> =
        MutableStateFlow(PopularAnimeFilter())

    private val upcomingAnimeFilter: MutableStateFlow<UpcomingAnimeFilter> =
        MutableStateFlow(UpcomingAnimeFilter())

    private val searchQuery: MutableStateFlow<String> = MutableStateFlow("")
    private val searchedAnimeFilter: MutableStateFlow<SearchAnimeFilter> =
        MutableStateFlow(SearchAnimeFilter())
    private val searchAnimeQueryAndFilter: StateFlow<Pair<String, SearchAnimeFilter>> =
        combine(
            searchQuery,
            searchedAnimeFilter
        ) { searchQuery, searchAnimeFilter ->
            Pair(
                first = searchQuery,
                second = searchAnimeFilter
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Pair("", SearchAnimeFilter())
        )

    val appliedAnimeFilter: StateFlow<AnimeFilter> = combine(
        popularAnimeFilter,
        upcomingAnimeFilter,
        searchedAnimeFilter
    ) { popularAnimeFilter, upcomingAnimeFilter, searchAnimeFilter ->
        AnimeFilter(
            popularAnimeFilter = popularAnimeFilter,
            upcomingAnimeFilter = upcomingAnimeFilter,
            searchAnimeFilter = searchAnimeFilter
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = AnimeFilter()
    )

    private val _currentAnimeFilter: MutableStateFlow<AnimeFilter> = MutableStateFlow(AnimeFilter())
    val currentAnimeFilter: StateFlow<AnimeFilter> = _currentAnimeFilter.asStateFlow()

    private val popularAnime: Flow<PagingData<Anime>> by lazy {
        popularAnimeFilter
            .debounce(2500)
            .distinctUntilChanged()
            .flatMapLatest { filter ->
                useCases.getPopularAnime(
                    type = filter.animeType?.name?.lowercase(),
                    status = filter.animeStatus?.name?.lowercase()
                )
            }.cachedIn(viewModelScope)
    }

    private val airingAnime: Flow<PagingData<Anime>> =
        useCases.getAiringAnime().cachedIn(viewModelScope)

    private val upcomingAnime: Flow<PagingData<Anime>> by lazy {
        upcomingAnimeFilter.flatMapLatest {
            useCases.getUpcomingAnime(
                type = it.animeType?.name?.lowercase()
            )
        }.cachedIn(viewModelScope)
    }

    private val searchedAnime: Flow<PagingData<Anime>> = searchAnimeQueryAndFilter
        .debounce(0)
        .distinctUntilChanged()
        .flatMapLatest {
            val filter: SearchAnimeFilter = it.second
            useCases.getAnimeSearch(
                searchQuery = it.first,
                type = filter.animeType?.name?.lowercase(),
                status = filter.animeStatus?.name?.lowercase(),
                rating = filter.animeRating?.query
            )
        }
        .cachedIn(viewModelScope)

    private val anime: Flow<PagingData<Anime>> =
        displayedAnimeType.flatMapLatest { type ->
            when (type) {
                DisplayType.POPULAR -> popularAnime
                DisplayType.AIRING -> airingAnime
                DisplayType.UPCOMING -> upcomingAnime
                DisplayType.SEARCH -> searchedAnime
            }
        }

    val explorationScreenState: StateFlow<ExploreScreenState> =
        combine(
            headerState,
            displayedStyle,
            displayedAnimeType
        ) { headerState: HeaderScreenState, displayedStyle: DisplayStyle, displayedType: DisplayType ->
            ExploreScreenState(
                headerScreenState = headerState,
                displayStyle = displayedStyle,
                displayType = displayedType,
                anime = anime
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = ExploreScreenState()
        )

    fun onEvent(event: ExploreScreenEvent) {
        Timber.d("Explore Screen Event: $event")
        when (event) {
            is ExploreScreenEvent.OnDisplayTypeChanged -> {
                displayedAnimeType.update {
                    event.selectedType
                }

                if (event.selectedType == DisplayType.SEARCH) return
                headerState.update {
                    it.copy(
                        searchedAnimeTitle = "",
                        isSearching = false
                    )
                }
                searchQuery.update { "" }
            }

            is ExploreScreenEvent.OnDisplayStyleChanged -> displayedStyle.update {
                event.selectedStyle
            }

            ExploreScreenEvent.OnFilterOptionMenuChangedState -> headerState.update {
                it.copy(
                    isFilterOptionOpened = !it.isFilterOptionOpened
                )
            }

            ExploreScreenEvent.OnDisplayStyleOptionMenuStateChanged -> headerState.update {
                it.copy(
                    isDisplayStyleOptionOpened = !it.isDisplayStyleOptionOpened
                )
            }

            is ExploreScreenEvent.OnSearchQueryChanged -> headerState.update {
                it.copy(
                    searchQuery = event.newSearchQuery
                )
            }

            ExploreScreenEvent.OnStartSearching -> headerState.update {
                it.copy(
                    isSearching = true
                )
            }

            ExploreScreenEvent.OnSearch -> {
                displayedAnimeType.update {
                    DisplayType.SEARCH
                }
                headerState.update {
                    it.copy(
                        searchedAnimeTitle = it.searchQuery
                    )
                }
                searchQuery.update {
                    headerState.value.searchQuery
                }
            }

            ExploreScreenEvent.OnStopSearching -> {
                headerState.update {
                    it.copy(
                        searchQuery = "",
                        isSearching = false
                    )
                }
            }

            is ExploreScreenEvent.OnPopularAnimeFilterChanged -> popularAnimeFilter.update {
                event.popularAnimeFilter
            }

            is ExploreScreenEvent.OnUpcomingAnimeFilterChanged -> upcomingAnimeFilter.update {
                event.upcomingAnimeFilter
            }

            is ExploreScreenEvent.OnAnimeFilterChanged -> {
                _currentAnimeFilter.update {
                    event.animeFilter
                }
            }

            ExploreScreenEvent.OnAnimeFilterApplied -> {
                popularAnimeFilter.update {
                    currentAnimeFilter.value.popularAnimeFilter
                }

                upcomingAnimeFilter.update {
                    currentAnimeFilter.value.upcomingAnimeFilter
                }

                searchedAnimeFilter.update {
                    currentAnimeFilter.value.searchAnimeFilter
                }
            }
        }
    }

    fun errorParsingRequest(t: Throwable): String =
        useCases.parseThrowable(t)

    fun insertOrUpdateAnimeHistory(anime: Anime) = viewModelScope.launch {
        useCases.insertOrUpdateAnimeHistory(anime = anime)
    }

    init {
        viewModelScope.launch {
            useCasesPreferences.getUserDisplayStyle().collectLatest { stylePreferences ->
                val displayStyle = when (stylePreferences) {
                    1 -> DisplayStyle.CARD
                    2 -> DisplayStyle.COMPACT_CARD
                    else -> DisplayStyle.LIST
                }
                Timber.d("Updated Style Preferences : $displayStyle")
                displayedStyle.update { displayStyle }
            }
        }
    }
}
