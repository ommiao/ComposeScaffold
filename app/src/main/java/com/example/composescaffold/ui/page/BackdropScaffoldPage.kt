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
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun BackdropScaffoldPage(navHostController: NavHostController) {
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)
    val scope = rememberCoroutineScope()
    ProvideWindowInsets() {
        BackdropScaffold(
            modifier = Modifier.statusBarsPadding(),
            appBar = {
                TopAppBar(
                    title = { Text(text = "Top App Bar") })
            },
            frontLayerContent = {
                Box(Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(top = 20.dp), text = "Front Layer Content"
                    )
                }
            },
            backLayerContent = {
                Box(Modifier.fillMaxSize()) {
                    Column(
                        Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Back Layer Content")
                        Text(
                            text = "Reveal Front Layer",
                            color = Color.Green,
                            modifier = Modifier
                                .padding(20.dp)
                                .clickable {
                                    scope.launch {
                                        scaffoldState.conceal()
                                    }
                                }
                        )
                    }
                }
            },
            scaffoldState = scaffoldState
        )
    }
}
