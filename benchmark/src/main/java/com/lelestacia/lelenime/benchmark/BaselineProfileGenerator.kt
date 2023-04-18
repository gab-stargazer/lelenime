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
            device.wait(Until.hasObject(scrollAnime), 60000)
            device.findObject(scrollAnime).scroll(Direction.DOWN, 50F)

            //  Switch to Airing Anime from Popular to Airing Anime, Wait for it to be loaded, then scroll
            device.findObject(By.text("AIRING")).click()
            device.wait(Until.hasObject(scrollAnime), 60000)
            device.findObject(scrollAnime).scroll(Direction.DOWN, 50F)

            //  Switch to Upcoming Anime from Popular to Airing Anime, Wait for it to be loaded, then scroll
            device.findObject(By.text("UPCOMING")).click()
            device.wait(Until.hasObject(scrollAnime), 60000)
            device.findObject(scrollAnime).scroll(Direction.DOWN, 50F)

            //  Change display style to Compact Card then scroll
            device.findObject(By.desc("Display Style")).click()
            device.wait(Until.hasObject(By.text("Compact Card")), 5000)
            device.findObject(By.text("Compact Card")).click()
            device.wait(Until.hasObject(scrollAnime), 5000)
            device.findObject(scrollAnime).scroll(Direction.DOWN, 50F)

            //  Change display style to List then scroll
            device.findObject(By.desc("Display Style")).click()
            device.wait(Until.hasObject(By.text("List")), 5000)
            device.findObject(By.text("List")).click()
            device.wait(Until.hasObject(scrollAnime), 5000)
            device.findObject(scrollAnime).scroll(Direction.DOWN, 50F)

            //  Search for anime
            device.findObject(By.desc("Search Anime")).click()
            device.wait(Until.hasObject(By.desc("Insert Anime Title")), 5000)

            //  Open some Anime First
            device.findObject(By.text("POPULAR")).click()
            device.wait(Until.hasObject(scrollAnime), 5000)
            device.findObject(By.textContains("Fullmetal")).click()
            device.wait(Until.hasObject(navigationIcon), 10000)
            device.findObject(navigationIcon).click()
            device.wait(Until.hasObject(scrollAnime), 5000)
            device.findObject(By.textContains("Stein")).click()
            device.wait(Until.hasObject(navigationIcon), 10000)
            device.findObject(navigationIcon).click()
            device.wait(Until.hasObject(By.text("Collection")), 10000)
            device.findObject(By.text("Collection")).click()
            device.wait(Until.hasObject(scrollAnime), 5000)

            //  Change Display Style to List
            device.wait(Until.hasObject(By.desc("Display Style Button")), 5000)
            device.findObject(By.desc("Display Style Button")).click()
            device.wait(Until.hasObject(By.text("List")), 5000)
            device.findObject(By.text("List")).click()
            device.wait(Until.hasObject(scrollAnime), 5000)

            //  Open some Anime First
            device.findObject(By.textContains("Fullmetal")).click()
            device.wait(Until.hasObject(navigationIcon), 10000)
            device.findObject(navigationIcon).click()
            device.findObject(By.textContains("Stein")).click()
            device.wait(Until.hasObject(navigationIcon), 10000)
            device.findObject(navigationIcon).click()
            device.wait(Until.hasObject(By.text("More")), 10000)

            //  Navigate to More Screen
            device.findObject(By.text("More")).click()
            device.wait(Until.hasObject(By.text("Settings")), 5000)

            //  Navigate to Settings Screen
            device.findObject(By.text("Settings")).click()
            device.wait(Until.hasObject(By.text("Back Button")), 5000)
            val darkModeSettings = By.desc("Dark Mode Preferences")
            device.findObject(darkModeSettings).click()
            device.wait(Until.hasObject(By.text("Day Mode")), 5000)
            device.findObject(By.text("Day Mode")).click()
            device.wait(Until.hasObject(darkModeSettings), 5000)
            device.findObject(darkModeSettings).click()
            device.wait(Until.hasObject(By.text("Dark Mode")), 5000)
            device.findObject(By.text("Dark Mode")).click()
            device.wait(Until.hasObject(darkModeSettings), 5000)
            device.findObject(darkModeSettings).click()
            device.wait(Until.hasObject(By.text("Use System Settings")), 5000)
            device.findObject(By.text("Use System Settings")).click()
        }
    )
}
