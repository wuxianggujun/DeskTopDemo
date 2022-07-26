// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.BasicsCodelabTheme

@Composable
@Preview()
fun App() {
    var shouldShowOnboard by rememberSaveable { mutableStateOf(true) }
    if (shouldShowOnboard) {
        onBoardingScreen(onContinueClicked = { shouldShowOnboard = false })
    } else {
        Greetings()
    }
}

@Composable
fun onBoardingScreen(onContinueClicked: () -> Unit) {
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Code lab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}


@Composable
private fun Greetings(names: List<Message> = SampleData.conversationSample) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name)
        }
    }
}

@Composable
private fun Greeting(name: Message) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}

@Composable
fun CardContent(name: Message) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.padding(12.dp).animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    ) {
        Column(modifier = Modifier.weight(1f).padding(12.dp)) {
            Text(text = name.title)
            Text(text = name.body, style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.ExtraBold))
            if (expanded) {
                Text(text = ("Composem ipsum color sit lazy, " + "padding theme elit,sed do bouncy.").repeat(4))
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Filled.ExpandLess else Filled.ExpandMore,
                contentDescription = if (expanded) "我草" else "无情"
            )
        }

    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        BasicsCodelabTheme {
            App()
        }
    }
}
