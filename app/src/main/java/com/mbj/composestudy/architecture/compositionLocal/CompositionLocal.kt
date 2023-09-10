package com.mbj.composestudy.architecture.compositionLocal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mbj.composestudy.ui.theme.JetpackComposeStudyTheme

/**
 * CompositionLocal은 통일적인 UI를 구성할 수 있게 도와주는 것임.
 * 특징 :  각 Composable이 별도의 매개변수를 통해 데이터를 전달하는 대신 CompositionLocal을 사용하면 코드의 중복을 줄이고 의존성을 효율적으로 관리할 수 있음
 * LocalContentAlpha 는 Marterial3 에서는 없어졌음.
 * ==> 그렇기에 LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f) 를 이용 해야한다.
 * 원하는 값을 넣기 위해서 CompositionLocalProvider 와 매개변수로 넣는 방법 2가지가 있지만, 가급적으로 파라미터로 전달하는 방법을 사용하도록 하자.
 * ( 이유는 CompositionLocalProvider는 암시적인 방법이기 때문에 예상하기 어려운 부분이 존재하기 떄문이다. )
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeStudyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

// 단계 4: `compositionLocalOf`에 `8.dp`를 넣어 `LocalElevation`을 할당합니다.
val LocalElevation = compositionLocalOf { 8.dp }


@Composable
fun Greeting() {
    // 단계 1: `CompositionLocalProvider`을 이용하면 특정 블록에 암시적인 값을 설정할 수 있습니다.
    // `CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled)`
    // 등을 설정해봅시다.
    // `LocalContentAlpha`를 `ContentAlpha.disabled`로 설정하겠다는 뜻입니다.
    // `ContentAlpha.medium`, `ContentAlpha.high`, `ContentAlpha.disabled`등을
    // 제공할 수 있습니다.
    // `LocalContentColor`도 설정해봅시다. `Color.XXX`을 설정하면 됩니다.

    // 단계 2: 중간 중간에 `LocalContentColor.current` 등의 값을 출력해봅시다.
    // 가장 가까운 곳에서 설정한 값을 `current`로 얻을 수 있습니다.

    // 단계 5: Card의 elevation에 `LocalElevation`을 적용해봅시다.

    // 단계 6: LocalElevation의 값을 `CompositionLocalProvider`로
    // 바꾸어 봅시다.

    CompositionLocalProvider(LocalElevation provides  20.dp) {
        Card(
            modifier = Modifier.padding(8.dp),
            elevation = CardDefaults.cardElevation(LocalElevation.current)
        ) {
            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurface) {
                Column(
                    modifier = Modifier.padding(16.dp),
                ) {
                    Text("안녕하세요. 패스트캠퍼스")
                    Text("${LocalContentColor.current}")
                    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
                        Text("스안녕하세요. 패스트캠퍼")
                        Text("퍼스안녕하세요. 패스트캠")
                        CompositionLocalProvider(LocalContentColor provides Color.Magenta) {
                            Text("캠퍼스안녕하세요. 패스트")
                            Text("트캠퍼스안녕하세요. 패스")
                            Text("${LocalContentColor.current}")
                        }
                    }
                    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)) {
                        Text("스트캠퍼스안녕하세요. 패")
                        Text("패스트캠퍼스안녕하세요.")
                    }
                    // 단계 3: `LocalContext.current`의 `resources`를 출력해보세요.
                    Button(onClick = { /*TODO*/ }) {
                        
                    }
                }
            }
        }
    }
//
//    Card(
//        modifier = Modifier.padding(8.dp)
//    ) {
//        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurface) {
//            Column(
//                modifier = Modifier.padding(16.dp),
//            ) {
//                Text("안녕하세요. 패스트캠퍼스")
//                Text("${LocalContentColor.current}")
//                CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
//                    Text("스안녕하세요. 패스트캠퍼")
//                    Text("퍼스안녕하세요. 패스트캠")
//                    CompositionLocalProvider(LocalContentColor provides Color.Magenta) {
//                        Text("캠퍼스안녕하세요. 패스트")
//                        Text("트캠퍼스안녕하세요. 패스")
//                        Text("${LocalContentColor.current}")
//                    }
//                }
//                CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)) {
//                    Text("스트캠퍼스안녕하세요. 패")
//                    Text("패스트캠퍼스안녕하세요.")
//                }
//                // 단계 3: `LocalContext.current`의 `resources`를 출력해보세요.
//            }
//        }
//    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeStudyTheme {
        Greeting()
    }
}
