package com.example.composescaffold.ui.page;

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun BottomDrawerPage(navHostController: NavHostController) {
    val bottomDrawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)
    val scope = rememberCoroutineScope()
    BottomDrawer(
        drawerContent = {
            Box(Modifier.fillMaxSize()) {
                Box(
                    Modifier
                        .fillMaxHeight(0.5f)
                        .fillMaxWidth()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Bottom Drawer Content"
                    )
                }
            }
        },
        drawerState = bottomDrawerState
    ) {
        Box(Modifier.fillMaxSize()) {
            Column(
                Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Content")
                Text(
                    text = "Open Bottom Drawer",
                    color = Color.Blue,
                    modifier = Modifier
                        .padding(20.dp)
                        .clickable {
                            scope.launch {
                                bottomDrawerState.open()
                            }
                        })
                Text(
                    text = "Expand Bottom Drawer",
                    color = Color.Blue,
                    modifier = Modifier
                        .padding(20.dp)
                        .clickable {
                            scope.launch {
                                bottomDrawerState.expand()
                            }
                        })
            }
        }
    }
}
