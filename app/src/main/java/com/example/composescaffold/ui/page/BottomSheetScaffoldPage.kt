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
fun BottomSheetPage(navHostController: NavHostController) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        drawerContent = {
            Box(Modifier.fillMaxSize()) {
                Text(modifier = Modifier.align(Alignment.Center), text = "Drawer Content")
            }
        },
        sheetContent = {
            Box(Modifier.fillMaxHeight(0.5f).fillMaxWidth()) {
                Text(modifier = Modifier.align(Alignment.Center), text = "Sheet Content")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Box(Modifier.fillMaxSize()) {
            Column(
                Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Content")
                Text(
                    text = "Open Modal Drawer",
                    color = Color.Blue,
                    modifier = Modifier
                        .padding(20.dp)
                        .clickable {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                )
                Text(
                    text = "Open Bottom Sheet",
                    color = Color.Blue,
                    modifier = Modifier
                        .padding(20.dp)
                        .clickable {
                            scope.launch {
                                scaffoldState.bottomSheetState.expand()
                            }
                        }
                )
            }
        }
    }
}
