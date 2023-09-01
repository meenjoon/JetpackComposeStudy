package com.mbj.composestudy.surface

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mbj.composestudy.ui.theme.JetpackComposeStudyTheme

/**
 * Surface : Material 디자인의 패턴 ( Clipping, Elevation, Borders,
 Background[color 설정을 Surface로 하게 되면 contentColor가 자동으로 설정됨] )
 쉽게 말해 Surface는 가장 기본적으로 UI를 구성하는 기반이 되는것을 말을 하는것이며 터치가 되지 않음.
 주의할 점 : Color만 설정하고 ContentColor를 설정하지 않으면 Color에 맞춰서 적당한 ContentColor가 설정이 된다!
 */
class Surface : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeStudyTheme {
                Greeting("Android")
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
//    Surface(
//        color = MaterialTheme.colorScheme.surface,
//        modifier = Modifier.padding(5.dp)
//    ) {
//        Text(
//            text = "Hello $name!",
//            modifier = Modifier.padding(8.dp)
//        )
//    }

    // 스텝 1: Surface에 elevation을 설정합시다.
//    Surface(
//        modifier = Modifier.padding(5.dp),
//        shadowElevation = 5.dp
//    ) {
//        Text(
//            text = "Hello $name!",
//            modifier = Modifier.padding(8.dp)
//        )
//    }

    // 스텝 2: border의 값을 설정해봅시다.
//    Surface(
//        border = BorderStroke(
//            width = 2.dp,
//            color = Color.Magenta
//        ),
//        modifier = Modifier.padding(50.dp),
//        shadowElevation = 5.dp
//    ) {
//        Text(
//            text = "Hello $name!",
//            modifier = Modifier.padding(8.dp)
//        )
//    }

    // 스텝 3: Surface의 shape도 설정해봅시다.
//    Surface(
//        border = BorderStroke(
//            width = 2.dp,
//            color = Color.Magenta
//        ),
//        modifier = Modifier.padding(5.dp),
//        shadowElevation = 5.dp,
//        shape = RoundedCornerShape(10.dp)
//    ) {
//        Text(
//            text = "Hello $name!",
//            modifier = Modifier.padding(8.dp)
//        )
//    }

    // 스텝 4: color를 지정합시다.
    // MaterialTheme.colors에서 primary, error,
    // background, surface, secondary 등을 지정해봅시다.
    // contentColor가 자동으로 선택됩니다.
    Surface(
        border = BorderStroke(
            2.dp, color = Color.Magenta
        ),
        modifier = Modifier.padding(5.dp),
        shadowElevation = 10.dp,
        shape = CircleShape,
        color = MaterialTheme.colorScheme.secondary
    ) {
        Text(
            text = "Hello $name!",
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeStudyTheme {
        Greeting("Android")
    }
}
