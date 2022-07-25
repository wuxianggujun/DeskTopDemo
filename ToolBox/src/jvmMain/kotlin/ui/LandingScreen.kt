package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay

@Composable
fun LandingScreen(modifier: Modifier,onTimeout:()->Unit){
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        LaunchedEffect(onTimeout){
            
        }
        Image(painterResource("backpackage-windows.png"), contentDescription = null)
    }
    
}