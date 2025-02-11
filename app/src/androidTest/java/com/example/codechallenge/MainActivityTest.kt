package com.example.codechallenge

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.example.codechallenge.repository.MockedDataRepository
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showOnlyCitiesOnPortraitOrientationTest() {
        composeTestRule.setContent {
            val innerLayout = PaddingValues()
            val navController = rememberNavController()
            val viewModel = MainActivityViewModel(MockedDataRepository())
            val screenOrientation = Configuration.ORIENTATION_PORTRAIT
            MainScreen(
                innerLayout, navController, viewModel, screenOrientation
            )
        }

        composeTestRule.onNode(
            hasTestTag("input")
        ).assertExists()

        composeTestRule.onNode(
            hasTestTag("cities")
        ).assertExists()

        composeTestRule.onNode(
            hasTestTag("maps")
        ).assertDoesNotExist()
    }

    @Test
    fun showOnlyCitiesAndMapsOnLandscapeOrientationTest() {
        composeTestRule.setContent {
            val innerLayout = PaddingValues()
            val navController = rememberNavController()
            val viewModel = MainActivityViewModel(MockedDataRepository())
            val screenOrientation = Configuration.ORIENTATION_LANDSCAPE
            MainScreen(
                innerLayout, navController, viewModel, screenOrientation
            )
        }

        composeTestRule.onNode(
            hasTestTag("cities")
        ).assertExists()

        composeTestRule.onNode(
            hasTestTag("input")
        ).assertExists()

        composeTestRule.onNode(
            hasTestTag("maps")
        ).assertExists()
    }
}