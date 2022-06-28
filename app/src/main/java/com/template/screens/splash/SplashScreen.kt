package com.template.screens.splash


import android.graphics.BitmapFactory
import androidx.compose.animation.core.TargetBasedAnimation
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.template.R
import com.template.screens.navigation.NavigationTree
import com.template.ui.theme.PrimaryColor

@Composable
fun SplashScreen(navController: NavController) {

    val image = BitmapFactory.decodeResource(LocalContext.current.resources, R.mipmap.ic_launcher)

    var rotation by remember { mutableStateOf(0F) }
    var startAnimation by remember { mutableStateOf(false) }

    val anim = remember {
        TargetBasedAnimation(
            animationSpec = tween(1500),
            typeConverter = Float.VectorConverter,
            initialValue = 0f,
            targetValue = 360f
        )
    }

    /*Button(
        onClick = {
            startAnimation = startAnimation.not()
        },
        Modifier.offset(y = 300.dp)
    ) {
        Text("Change startAnimation state")
    }*/

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().background(color = PrimaryColor)
    ) {
        Image(
            painter = painterResource(R.mipmap.ic_launcher),
            contentDescription = "secondIcon",
            modifier = Modifier
                .size(100F.dp, 100F.dp)
                .rotate(rotation)
        )
        Text(text = "JK_0020_contentlist_v1", modifier = Modifier.size(100.dp), color = Color.White)
    }

    LaunchedEffect(startAnimation) {
        var playTime: Long
        val startTime = withFrameNanos { it }
        do {
            playTime = withFrameNanos { it } - startTime
            rotation = anim.getValueFromNanos(playTime)
        } while (!anim.isFinishedFromNanos(playTime))
        navController.navigate(NavigationTree.Main.name)
    }

}
