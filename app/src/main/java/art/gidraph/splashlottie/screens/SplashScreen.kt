package art.gidraph.splashlottie.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import art.gidraph.splashlottie.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun SplashScreen() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash))
    var isPlaying by remember { mutableStateOf(false) }
    var isComplete by remember { mutableStateOf(false) }
    var isPrevious by remember { mutableStateOf(false) }

    var clipSpecs = LottieClipSpec.Progress(
        min = if (isPrevious) 0f else 0.5f,
        max = if (isComplete) 1f else 0.5f
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isPlaying,
        clipSpec = clipSpecs
    )


    LaunchedEffect(key1 = progress) {
        if (progress == 0f) isPlaying = true
        if (progress == 1f) isPlaying = false

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),

        ) {

        LottieAnimation(
            modifier = Modifier
                .size(1000.dp)
                .align(Alignment.Center)
                .blur(radius = 20.dp),
            composition = composition,
            progress = { progress },
        )
//        Box(
//            modifier = Modifier
//                .width(375.dp)
//                .height(812.dp)
//                .background(color = Color.White)
//                .alpha(1f)
//                .blur(radius = 50.dp)
//                .border(1.dp, Color.Red)
//        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                elevation = ButtonDefaults.buttonElevation(8.dp),
                onClick = {
                    isPrevious = true
                    isComplete = false
                    isPlaying = true

                }
            ) {
                Text(text = "Back")

            }
            Button(

                elevation = ButtonDefaults.buttonElevation(8.dp),
                onClick = {
                    isComplete = true
                    isPrevious = false
                    isPlaying = true

                }
            ) {
                Text(text = "Next")

            }
        }
//        Button(
//            modifier= Modifier
//                .align(Alignment.BottomCenter)
//                .navigationBarsPadding(),
//            elevation = ButtonDefaults.buttonElevation(8.dp) ,
//            onClick = { isPlaying = true}
//        ) {
//            Text(text = "Play Again")
//
//        }

    }

}


@Preview
@Composable
fun preview() {
    SplashScreen()
}