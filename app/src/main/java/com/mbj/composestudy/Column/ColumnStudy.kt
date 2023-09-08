package com.mbj.composestudy.Column

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mbj.composestudy.ui.theme.JetpackComposeStudyTheme


/**
 * Column는 수직(위 아래)이기 때문에 Alignment[horizontalAlignment]는 항상 수평(가로)로 사용 된다. [ EX: 가운데 정렬 ]
 * Column는 수직(위 아래)이기 때문에 Arrangement[verticalArrangement]는 항상 수직(위 아래)로 사용된다. [ EX: 높이 정렬 ]
 * Alignment의 경우 Center / CenterHorizontally / CenterVertically 로 나눠져 있다.
 * Arrangement의 경우 어떤 경우든 간에 center로 쓴다.
 */
class ColumnStudy : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeStudyTheme {
                ColumnEx()
            }
        }
    }
}

@Composable
fun ColumnEx() {
//    Column(modifier = Modifier.size(100.dp)) {
//        Text(text = "첫 번째")
//        Text(text = "두 번째")
//        Text(text = "세 번째")
//    }

    // 스텝 1: horizontalAlignment를 Column에 적용해봅시다.

//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally
//        ,modifier = Modifier.size(100.dp)) {
//        Text(text = "첫 번째")
//        Text(text = "두 번째")
//        Text(text = "세 번째")
//    }


    // 스텝 2: Column에 verticalArrangement를 지정해봅시다.
    // SpaceAround, SpaceEvenly, SpaceBetween도 해봅시다.

//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//        ,modifier = Modifier.size(100.dp)) {
//        Text(text = "첫 번째")
//        Text(text = "두 번째")
//        Text(text = "세 번째")
//    }

    // 스텝 3: Text에 Modifier.align을 사용해 봅시다.

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center, modifier = Modifier.size(100.dp)
    ) {
        Text(
            text = "첫 번째",
            modifier = Modifier.align(Alignment.End)
        )
        Text(text = "두 번째")
        Text(text = "세 번째", modifier = Modifier.align(Alignment.Start))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeStudyTheme {
        ColumnEx()
    }
}
