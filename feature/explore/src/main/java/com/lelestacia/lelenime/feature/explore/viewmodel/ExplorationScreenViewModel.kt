package com.lelestacia.lelenime.feature.explore.viewmodel

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lelestacia.lelenime.core.common.displayStyle.DisplayStyle
import com.lelestacia.lelenime.core.common.util.toQueryParam
import com.lelestacia.lelenime.core.domain.usecases.explore.IExploreUseCases
import com.lelestacia.lelenime.core.domain.usecases.settings.IUserPreferencesUseCases
import com.lelestacia.lelenime.core.model.Anime
import com.lelestacia.lelenime.feature.explore.component.displayType.DisplayType
import com.lelestacia.lelenime.feature.explore.component.filter.AiringAnimeFilter
import com.lelestacia.lelenime.feature.explore.component.filter.PopularAnimeFilter
import com.lelestacia.lelenime.feature.explore.component.filter.SearchAnimeFilter
import com.lelestacia.lelenime.feature.explore.component.filter.UpcomingAnimeFilter
import com.lelestacia.lelenime.feature.explore.state.AnimeFilterState
import com.lelestacia.lelenime.feature.explore.event.BottomSheetEvent
import com.lelestacia.lelenime.feature.explore.state.ExploreBottomSheetState
import com.lelestacia.lelenime.feature.explore.event.ExploreScreenEvent
import com.lelestacia.lelenime.feature.explore.state.ExploreScreenState
import com.lelestacia.lelenime.feature.explore.event.SearchBarEvent
import com.lelestacia.lelenime.feature.explore.state.SearchBarState
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

@OptIn(
    ExperimentalCoroutinesApi::class,
    FlowPreview::class
)
@HiltViewModel
class ExplorationScreenViewModel @Inject constructor(
    private val useCases: IExploreUseCases,
    private val useCasesPreferences: IUserPreferencesUseCases,
) : ViewModel() {

    /**
     * Represents a [MutableStateFlow] holding the currently displayed style for anime.
     * The style can be updated to change the appearance of anime items, such as using cards or a list view.
     */
    private val _currentDisplayStyle: MutableStateFlow<DisplayStyle> =
        MutableStateFlow(DisplayStyle.CARD)

    /**
     * Represents the currently displayed style for anime in a [StateFlow] format.
     *
     * The displayed style can be updated to change the appearance of anime items, such as using cards
     * or a list view. The initial default value is [DisplayStyle.CARD].
     *
     * @see DisplayStyle
     */
    val currentDisplayStyle: StateFlow<DisplayStyle> = _currentDisplayStyle.asStateFlow()

    /**
     * Represents a [MutableStateFlow] holding the currently displayed type of anime.
     * The display type can be updated to switch between different categories of anime, e.g., popular, airing, upcoming.
     *
     * @see DisplayType
     */
    private val _currentDisplayType: MutableStateFlow<DisplayType> =
        MutableStateFlow(DisplayType.POPULAR)

    /**
     * Represents the currently displayed type of anime in a [StateFlow] format.
     *
     * The display type can be updated to switch between different categories of anime, such as popular, airing, or upcoming.
     * The initial default value is [DisplayType.POPULAR].
     *
     * @see DisplayType
     */
    val currentDisplayType: StateFlow<DisplayType> = _currentDisplayType.asStateFlow()

    /**
     * Represents a private [MutableStateFlow] for holding the filter used to fetch popular anime.
     * The popular anime filter can be updated to fetch anime based on specific criteria such as type or status.
     *
     * @see PopularAnimeFilter
     */
    private val _popularAnimeFilter: MutableStateFlow<PopularAnimeFilter> =
        MutableStateFlow(PopularAnimeFilter())

    /**
     * Represents a private [MutableStateFlow] for holding the filter used to fetch airing anime.
     * The popular anime filter can be updated to fetch anime based on specific criteria such as type or status.
     *
     * @see AiringAnimeFilter
     */
    private val _airingAnimeFilter: MutableStateFlow<AiringAnimeFilter> =
        MutableStateFlow(AiringAnimeFilter())

    /**
     * Represents a private [MutableStateFlow] for holding the filter used to fetch upcoming anime.
     * The upcoming anime filter can be updated to fetch anime based on specific criteria such as type or status.
     *
     * @see UpcomingAnimeFilter
     */
    private val _upcomingAnimeFilter: MutableStateFlow<UpcomingAnimeFilter> =
        MutableStateFlow(UpcomingAnimeFilter())

    /**
     * Holds the mutable state flow of anime grid states associated with different display types.
     */
    private val _animeGridState: MutableStateFlow<Map<DisplayType, LazyGridState>> =
        MutableStateFlow(
            mapOf(
                DisplayType.POPULAR to LazyGridState(),
                DisplayType.AIRING to LazyGridState(),
                DisplayType.UPCOMING to LazyGridState(),
                DisplayType.SEARCH to LazyGridState()
            )
        )

    /**
     * Provides a read-only state flow of anime grid states associated with different display types.
     * The state flow emits the current map of anime grid states whenever it is updated.
     */
    val animeGridState: StateFlow<Map<DisplayType, LazyGridState>> = _animeGridState.asStateFlow()

    /**
     * Holds the mutable state flow of anime list states associated with different display types.
     */
    private val _animeListState: MutableStateFlow<Map<DisplayType, LazyListState>> =
        MutableStateFlow(
            mapOf(
                DisplayType.POPULAR to LazyListState(),
                DisplayType.AIRING to LazyListState(),
                DisplayType.UPCOMING to LazyListState(),
                DisplayType.SEARCH to LazyListState()
            )
        )

    /**
     * Provides a read-only state flow of anime list states associated with different display types.
     * The state flow emits the current map of anime list states whenever it is updated.
     */
    val animeListState: StateFlow<Map<DisplayType, LazyListState>> = _animeListState.asStateFlow()

    /**
     * Holds the search query as a mutable state flow.
     */
    private val _searchQuery: MutableStateFlow<String> =
        MutableStateFlow("")

    /**
     * Holds the searched anime filter as a mutable state flow.
     */
    private val _searchedAnimeFilter: MutableStateFlow<SearchAnimeFilter> =
        MutableStateFlow(SearchAnimeFilter())

    /**
     * Combines the search query and searched anime filter into a state flow representing the search query and filter pair.
     * The state flow emits the pair whenever either the search query or the searched anime filter changes.
     *
     * @return The state flow of the search query and searched anime filter pair.
     */
    private val _searchAnimeQueryAndFilter: StateFlow<Pair<String, SearchAnimeFilter>> =
        combine(
            _searchQuery,
            _searchedAnimeFilter
        ) { searchQuery: String, searchAnimeFilter: SearchAnimeFilter ->
            Pair(
                first = searchQuery,
                second = searchAnimeFilter
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Pair("", SearchAnimeFilter())
        )

    /**
     * Represents a state flow of the applied anime filter.
     * The state flow emits the combined anime filter values from popular, upcoming, and searched anime filters.
     * It provides a single source of truth for the currently applied anime filter.
     */
    private val _appliedAnimeFilterState: StateFlow<AnimeFilterState> = combine(
        flow = _popularAnimeFilter,
        flow2 = _airingAnimeFilter,
        flow3 = _upcomingAnimeFilter,
        flow4 = _searchedAnimeFilter,
        transform = ::AnimeFilterState
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = AnimeFilterState()
    )

    /**
     * Holds the current anime filter as a mutable state flow.
     */
    private val _currentAnimeFilterState: MutableStateFlow<AnimeFilterState> = MutableStateFlow(
        AnimeFilterState()
    )

    /**
     * Represents a flow of paging data for popular anime.
     * The flow emits the updated paging data whenever the popular anime filter changes.
     */
    private val popularAnime: Flow<PagingData<Anime>> =
        _popularAnimeFilter
            .debounce(0)
            .distinctUntilChanged()
            .flatMapLatest { filter ->
                val animeType: String? = filter.type?.name?.lowercase()
                val animeRating = filter.rating?.query
                val animeFilter = filter.filter?.query
                useCases.getPopularAnime(
                    type = animeType,
                    filter = animeFilter,
                    rating = animeRating
                )
            }.cachedIn(viewModelScope)

    private val airingAnime: Flow<PagingData<Anime>> =
        _airingAnimeFilter.flatMapLatest { filter ->
            useCases.getAiringAnime(
                type = filter.type?.name?.lowercase()
            )
        }.cachedIn(viewModelScope)

    private val upcomingAnime: Flow<PagingData<Anime>> =
        _upcomingAnimeFilter.flatMapLatest { filter ->
            useCases.getUpcomingAnime(
                type = filter.type?.name?.lowercase()
            )
        }.cachedIn(viewModelScope)

    /**
     * Represents a flow of paging data for searched anime.
     * The flow emits the updated paging data whenever the search query or filter changes.
     */
    private val searchedAnime: Flow<PagingData<Anime>> = _searchAnimeQueryAndFilter
        .debounce(0)
        .distinctUntilChanged()
        .flatMapLatest {
            val filter: SearchAnimeFilter = it.second
            val searchQuery: String = it.first
            useCases.getAnimeSearch(
                searchQuery = searchQuery,
                type = filter.type?.name?.lowercase(),
                rating = filter.rating?.query,
                status = filter.status?.name?.lowercase(),
                sort = filter.sort.query,
                genres = filter.genres.toQueryParam()
            )
        }
        .cachedIn(viewModelScope)

    /**
     * Represents a flow of paging data for anime.
     * The flow emits the corresponding paging data based on the currently displayed anime type.
     */
    private val anime: Flow<PagingData<Anime>> =
        _currentDisplayType.flatMapLatest { type ->
            when (type) {
                DisplayType.POPULAR -> popularAnime
                DisplayType.AIRING -> airingAnime
                DisplayType.UPCOMING -> upcomingAnime
                DisplayType.SEARCH -> searchedAnime
            }
        }

    val explorationScreenState: StateFlow<ExploreScreenState> =
        combine(
            _currentDisplayStyle,
            _currentDisplayType
        ) { displayedStyle, displayedType ->
            ExploreScreenState(
                displayStyle = displayedStyle,
                displayType = displayedType,
                anime = anime
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = ExploreScreenState()
        )

    private val _searchBarState: MutableStateFlow<SearchBarState> =
        MutableStateFlow(SearchBarState())
    val searchBarState: StateFlow<SearchBarState> = _searchBarState.asStateFlow()

    val bottomSheetState = combine(
        flow = _currentDisplayType,
        flow2 = _currentAnimeFilterState,
        flow3 = _appliedAnimeFilterState,
        transform = ::ExploreBottomSheetState
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = ExploreBottomSheetState()
    )

    fun onEvent(event: ExploreScreenEvent) {
        Timber.d("Explore Screen Event: $event")
        when (event) {
            is ExploreScreenEvent.OnDisplayTypeChanged -> {
                _currentDisplayType.update {
                    event.selectedType
                }
            }

            is SearchBarEvent.OnSearch -> {
                _currentDisplayType.update { DisplayType.SEARCH }
                _searchBarState.update { currentSearchBarState ->
                    val recentlySearched = currentSearchBarState
                        .recentlySearched
                        .toMutableList()
                        .also { history ->
                            history.add(0, event.query)
                        }
                        .toSet()
                        .toList()
                    SearchBarState(
                        query = event.query,
                        isActive = false,
                        recentlySearched = recentlySearched
                    )
                }

                _searchQuery.update { event.query }

                viewModelScope.launch {
                    _animeGridState.value[DisplayType.SEARCH]?.scrollToItem(0, 0)
                    _animeListState.value[DisplayType.SEARCH]?.scrollToItem(0, 0)
                }
            }

            is SearchBarEvent.OnSearchQueryChanged -> {
                _searchBarState.update {
                    it.copy(
                        query = event.newSearchQuery
                    )
                }
            }

            is SearchBarEvent.OnStateChanged -> {
                _searchBarState.update {
                    it.copy(
                        query = _searchQuery.value,
                        isActive = event.newState
                    )
                }
            }

            is BottomSheetEvent.OnDisplayTypeChanged -> {
                _currentDisplayType.update { event.displayType }
            }

            is BottomSheetEvent.OnAnimeFilterChanged -> {
                _currentAnimeFilterState.update { event.animeFilterState }
            }

            BottomSheetEvent.OnAnimeFilterApplied -> {
                viewModelScope.launch {
                    _animeGridState.value[_currentDisplayType.value]?.scrollToItem(0, 0)
                    _animeListState.value[_currentDisplayType.value]?.scrollToItem(0, 0)
                }

                val currentAnimeFilter = _currentAnimeFilterState.value
                _popularAnimeFilter.update { currentAnimeFilter.popularAnimeFilter }
                _airingAnimeFilter.update { currentAnimeFilter.airingAnimeFilter }
                _upcomingAnimeFilter.update { currentAnimeFilter.upcomingAnimeFilter }
                _searchedAnimeFilter.update { currentAnimeFilter.searchAnimeFilter }
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
                _currentDisplayStyle.update { displayStyle }
            }
        }
    }
}
