package com.lelestacia.lelenime.benchmark

import androidx.benchmark.macro.ExperimentalBaselineProfilesApi
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalBaselineProfilesApi::class)
class BaselineProfileGenerator {

    @get:Rule
    val baselineProfileRule = BaselineProfileRule()

    @Test
    fun startup() = baselineProfileRule.collectBaselineProfile(
        packageName = "com.lelestacia.lelenime",
        profileBlock = {
            pressHome()
            startActivityAndWait()

            val scrollAnime = By.res("explore:scrollAnime")
            val navigationIcon = By.desc("Navigation Icon")

            //  Wait until the anime is loaded and scroll down
            device.wait(
                Until.hasObject(scrollAnime),
                DEFAULT_TIMEOUT
            )
            device.findObject(scrollAnime).fling(Direction.DOWN)

            //  Apply every filter for Popular Anime
            device.findObject(By.res("explore:filter")).click()
            device.wait(
                Until.hasObject(By.text("Type")),
                DEFAULT_TIMEOUT
            )
            device.findObject(By.text("Type")).click()
            device.wait(
                Until.hasObject(By.text("Tv")),
                DEFAULT_TIMEOUT
            )
            device.findObject(By.text("Tv")).click()
            device.pressBack()
            device.wait(
                Until.hasObject(By.text("Status")),
                DEFAULT_TIMEOUT
            )
            device.findObject(By.text("Status")).click()
            device.wait(
                Until.hasObject(By.text("Complete")),
                DEFAULT_TIMEOUT
            )
            device.findObject(By.text("Complete")).click()
            device.pressBack()
            device.pressBack()
            device.wait(
                Until.hasObject(By.text("Apply")),
                DEFAULT_TIMEOUT
            )
            device.findObject(By.text("Apply")).click()
            device.wait(
                Until.hasObject(By.textStartsWith("Fullmetal")),
                DEFAULT_TIMEOUT
            )

            //  Switch to Airing Anime from Popular to Airing Anime, Wait for it to be loaded, then scroll
            device.wait(
                Until.hasObject(By.text("AIRING")),
                DEFAULT_TIMEOUT
            )
            device.findObject(By.text("AIRING")).click()
            device.wait(
                Until.hasObject(scrollAnime),
                DEFAULT_TIMEOUT
            )

            //  Switch to Upcoming Anime from Popular to Airing Anime, Wait for it to be loaded, then scroll
            device.findObject(By.text("UPCOMING")).click()
            device.wait(
                Until.hasObject(scrollAnime),
                DEFAULT_TIMEOUT
            )
            device.findObject(scrollAnime).fling(Direction.DOWN)
            device.findObject(By.res("explore:filter")).click()
            device.wait(
                Until.hasObject(By.text("Type")),
                DEFAULT_TIMEOUT
            )
            device.findObject(By.text("Type")).click()
            device.wait(
                Until.hasObject(By.text("Tv")),
                DEFAULT_TIMEOUT
            )
            device.findObject(By.text("Tv")).click()
            device.pressBack()
            device.pressBack()
            device.wait(
                Until.hasObject(By.text("Apply")),
                DEFAULT_TIMEOUT
            )
            device.findObject(By.text("Apply")).click()
            device.wait(
                Until.hasObject(By.textStartsWith("Tate")),
                DEFAULT_TIMEOUT
            )

            //  Change display style to Compact Card then scroll
            device.findObject(By.desc("Display Style")).click()
            device.wait(
                Until.hasObject(By.text("Compact Card")),
                DEFAULT_TIMEOUT
            )
            device.findObject(By.text("Compact Card")).click()
            device.wait(
                Until.hasObject(scrollAnime),
                DEFAULT_TIMEOUT
            )
            device.findObject(scrollAnime).fling(Direction.DOWN)

            //  Change display style to List then scroll
            device.findObject(By.desc("Display Style")).click()
            device.wait(
                Until.hasObject(By.text("List")),
                DEFAULT_TIMEOUT
            )
            device.findObject(By.text("List")).click()
            device.wait(
                Until.hasObject(scrollAnime),
                DEFAULT_TIMEOUT
            )
            device.findObject(scrollAnime).fling(Direction.DOWN)

            //  Search for anime
            device.findObject(By.desc("Search Anime")).click()
            device.wait(
                Until.hasObject(By.desc("Insert Anime Title")),
                DEFAULT_TIMEOUT
            )
            device.findObject(By.res("explore:search")).text = "Bocchi"

            //  Open some Anime First
            device.findObject(By.text("POPULAR")).click()
            device.wait(
                Until.hasObject(scrollAnime),
                DEFAULT_TIMEOUT
            )

            //  FMA
            device.findObject(By.textContains("Fullmetal")).click()
            device.wait(
                Until.hasObject(By.res("detailAnime")),
                DEFAULT_TIMEOUT
            )
            device.pressBack()

            //  Bleach
            device.wait(
                Until.hasObject(By.textContains("Bleach")),
                DEFAULT_TIMEOUT
            )
            device.findObject(By.textContains("Bleach")).click()
            device.wait(
                Until.hasObject(navigationIcon),
                DEFAULT_TIMEOUT
            )
            device.findObject(navigationIcon).click()
            device.wait(
                Until.hasObject(By.text("Collection")),
                DEFAULT_TIMEOUT
            )
            device.findObject(By.text("Collection")).click()
            device.wait(
                Until.hasObject(scrollAnime),
                DEFAULT_TIMEOUT
            )

            //  Change Display Style to List
            device.wait(
                Until.hasObject(By.desc("Display Style Button")),
                DEFAULT_TIMEOUT
            )
            device.findObject(By.desc("Display Style Button")).click()
            device.wait(
                Until.hasObject(By.text("List")),
                DEFAULT_TIMEOUT
            )
            device.findObject(By.text("List")).click()
            device.wait(
                Until.hasObject(scrollAnime),
                DEFAULT_TIMEOUT
            )

            //  Open some Anime First
            device.findObject(By.textContains("Fullmetal")).click()
            device.wait(
                Until.hasObject(navigationIcon),
                DEFAULT_TIMEOUT
            )
            device.pressBack()

            device.wait(
                Until.hasObject(By.textContains("Bleach")),
                DEFAULT_TIMEOUT
            )
            device.findObject(By.textContains("Bleach")).click()
            device.wait(
                Until.hasObject(navigationIcon),
                DEFAULT_TIMEOUT
            )
            device.findObject(navigationIcon).click()
            device.wait(
                Until.hasObject(By.text("More")),
                DEFAULT_TIMEOUT
            )

            //  Navigate to More Screen
            device.findObject(By.text("More")).click()
            device.wait(Until.hasObject(By.text("Settings")), DEFAULT_TIMEOUT)

            //  Navigate to Settings Screen
            device.findObject(By.text("Settings")).click()
            device.wait(
                Until.hasObject(By.text("Back Button")),
                DEFAULT_TIMEOUT
            )
            val darkModeSettings = By.desc("Dark Mode Preferences")
            device.findObject(darkModeSettings).click()
            device.wait(
                Until.hasObject(By.text("Day Mode")),
                DEFAULT_TIMEOUT
            )
            device.findObject(By.text("Day Mode")).click()
            device.wait(
                Until.hasObject(darkModeSettings),
                DEFAULT_TIMEOUT
            )
            device.findObject(darkModeSettings).click()
            device.wait(
                Until.hasObject(By.text("Dark Mode")),
                DEFAULT_TIMEOUT
            )
            device.findObject(By.text("Dark Mode")).click()
            device.wait(
                Until.hasObject(darkModeSettings),
                DEFAULT_TIMEOUT
            )
            device.findObject(darkModeSettings).click()
            device.wait(
                Until.hasObject(By.text("Use System Settings")),
                DEFAULT_TIMEOUT
            )
            device.findObject(By.text("Use System Settings")).click()
        }
    )

    companion object {
        private const val DEFAULT_TIMEOUT: Long = 60000
    }
}
