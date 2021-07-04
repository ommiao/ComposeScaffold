package com.example.composescaffold

import android.os.Bundle
import android.view.View
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composescaffold.ui.page.*
import com.example.composescaffold.ui.theme.ComposeScaffoldTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overlayStatusBar()
        setContent {
            ComposeScaffoldTheme {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = Color.Transparent,
                        darkIcons = true
                    )
                }

                val navHostController = rememberNavController()
                NavHost(navController = navHostController, startDestination = "Scaffold Menu") {
                    composable("Scaffold Menu") {
                        ScaffoldMenu(navHostController)
                    }
                    composable(PageType.MODAL_DRAWER.key) {
                        ModalDrawerPage(navHostController)
                    }
                    composable(PageType.BOTTOM_DRAWER.key) {
                        BottomDrawerPage(navHostController)
                    }
                    composable(PageType.SCAFFOLD.key) {
                        ScaffoldPage(navHostController)
                    }
                    composable(PageType.BOTTOM_SHEET_SCAFFOLD.key) {
                        BottomSheetPage(navHostController)
                    }
                    composable(PageType.MODAL_BOTTOM_SHEET_LAYOUT.key) {
                        ModalBottomSheetLayoutPage(navHostController)
                    }
                    composable(PageType.BACKDROP_SCAFFOLD.key) {
                        BackdropScaffoldPage(navHostController)
                    }
                }
            }
        }
    }

    @Composable
    private fun ScaffoldMenu(navHostController: NavHostController) {
        ProvideWindowInsets {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
            ) {
                PageType.values().forEach { pageType ->
                    item {
                        ScaffoldMenuItem(navHostController, pageType)
                    }
                }
            }
        }
    }

    @Composable
    private fun ScaffoldMenuItem(
        navHostController: NavHostController,
        pageType: PageType
    ) {
        Card(
            Modifier
                .padding(15.dp)
                .fillMaxWidth()
                .clickable { navHostController.navigate(pageType.key) }
        ) {
            Text(modifier = Modifier.padding(20.dp), text = pageType.key)
        }
    }

    @Suppress("DEPRECATION")
    private fun overlayStatusBar() {
        window?.decorView?.apply {
            val flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window?.decorView?.systemUiVisibility = flags
        }
    }
}