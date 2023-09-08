package com.mbj.composestudy.boxwithconstraints

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mbj.composestudy.ui.theme.JetpackComposeStudyTheme

/**
 * BoxWithConstraints 는 자주 사용되진 않지만, 어떤 길이에 맞춰서 유동적으로 width가 얼마 이상일 때 어떤것을 넣거나 height가 얼마 이상 일때 어떤것을 넣을때 사용한다.
 */

class BoxWithConstraints : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeStudyTheme {
                Outer()
            }
        }
    }
}

@Composable
fun Outer() {
    // 스텝 4: Column에 width를 지정해서 제한해 봅시다.
    Column(modifier = Modifier.width(150.dp)) {
        // 스텝 2: Inner의 인자로 Modifier.widthIn(min = 100.dp)를
        // 전달해봅시다. heightIn도 전달해봅시다. 각각 인자의 max값도
        // 전달해봅시다.
        Inner(modifier = Modifier
            .width(200.dp)
            .height(160.dp))

        Inner(modifier = Modifier
            .width(200.dp)
            .height(70.dp))
    }
}

// 스텝 1: Inner 인자로 modifier를 전달해봅시다. 기본 값을 Modifier로 지정합시다.
// 파라미터로 받은 modifier를 BoxWithConstrains에 전달합시다.
@Composable
private fun Inner(modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier) {
        // 스텝 3: maxHeight 값이 150dp가 넘을 때만 추가로 텍스트를 출력해봅시다.
        if (maxHeight > 90.dp)  {
            Text(text = "여기 길어요~",
                modifier = Modifier.align(Alignment.BottomCenter))
        }
        Text("maxW:$maxWidth maxH:$maxHeight minW: $minWidth minH:$minHeight")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeStudyTheme {
        Outer()
    }
}
