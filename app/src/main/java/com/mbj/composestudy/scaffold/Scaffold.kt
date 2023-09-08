package com.mbj.composestudy.scaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mbj.composestudy.ui.theme.JetpackComposeStudyTheme

/**
 * Scaffold는 매개변수를 기준으로 우리가 필요한 부분의 어떤 내용을 넣을 수 있게 도와주는 기본적인 템플릿을 제공한다.
 * 즉, 쉽게 말해 앱의 기본 레이아웃 구조를 정의하는데 사용한다고 보면 된다.
 * Slot API 형태처럼 내가 원하는 매개변수를 채워넣어서 형태를 만들어간다고 생각하면 된다.
 * 다른 API를 쓸때도 Slot API를 이용해서 체계젹으로 개발하고 정리할 수 있는 구조가 되어 있다.
 * ==> compose 스럽게 만드는 과정
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeStudyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScaffoldEx()
                }
            }
        }
    }
}

@Composable
fun CheckBoxWithContent(
    checked: Boolean,
    toggleState: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { toggleState() }
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { toggleState() },
        )
        content()
    }
}

@Composable
fun ScaffoldEx() {
    var checked by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        // 스텝 1: `topBar`를 `TopAppBar`로 채워 봅시다.
        TopAppBar(
            title = {
                Text(text = "Scaffold App")
            },
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "뒤로 가기"
                    )
                }
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "메뉴"
                    )
                }
            } )
    }, floatingActionButton = {
        FloatingActionButton(onClick = { /*TODO*/ }) {

        }
    }, content = { padding ->
        Surface(modifier = Modifier.padding(padding)) {
            // 스텝 2: 아래에 CheckBoxWithContent를 넣어봅시다.
            CheckBoxWithContent(checked = checked,
                toggleState = { checked = checked.not() }) {
                Text(text = "컴포즈를 해보려고 합니다.")
            }
        }
    }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeStudyTheme {
        ScaffoldEx()
    }
}
