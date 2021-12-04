package com.example.compose.rally

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RallyNavHostTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: NavHostController

    @Before
    fun setupRallyNavHost() {
        composeTestRule.setContent {
            navController = rememberNavController()
            RallyNavHost(navController = navController, modifier = Modifier.padding(10.dp))
        }
    }

    @Test
    fun rallyNavHost() {
        composeTestRule.setContent {
            navController = rememberNavController()
            RallyNavHost(navController = navController, modifier = Modifier.padding(10.dp))
        }
        fail()
        composeTestRule
            .onNodeWithContentDescription("Overview Screen")
            .assertIsDisplayed()
    }

    @Test
    fun rallyNavHost_navigateToAllAccounts_viaUI() {
//        composeTestRule
//            .onNodeWithContentDescription("All Accounts")
//            .perfromClick()
//        composeTestRule
//            .onNodeWithContentDescription("Accounts Screen")
//            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("All Bills").apply {
            performScrollTo()
            performClick()
        }
        val route = navController.currentBackStackEntry?.destination?.route
        Assert.assertEquals(route, "Bills")
    }

    @Test
    fun rallyNavHost_navigateToAllAccounts_callingNavigate() {
        runBlocking {
            withContext(Dispatchers.Main) {
                navController.navigate(RallyScreen.Accounts.name)
            }
        }
        composeTestRule
            .onNodeWithContentDescription("Accounts Screen")
            .assertIsDisplayed()
    }
}