package com.mbj.composestudy.modifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mbj.composestudy.ui.theme.JetpackComposeStudyTheme

class Modifier : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeStudyTheme {
                ModifierEx()
            }
        }
    }
}

/**
 * 중요한 점은 modifier를 통해서 background를 설정할 수 있는 것은 Icon,Spacer,Text이고,
 * Button의 경우[Material 3 경우]에는 colors - containerColor 를 통해 background를 설정할 수 있다.
 */

@Composable
fun ModifierEx() {
//    Button(onClick = {}) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }

    // 스텝 1: modifier에 Modifier.fillMaxSize()를 사용해봅시다.
//    Button(
//        onClick = {},
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }

    // 스텝 2: fillMaxSize대신 Modifier.height를 설정해봅시다.
//    Button(
//        onClick = {},
//        modifier = Modifier.height(100.dp)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }

    // 스텝 3: modifier에 height와 width를 같이 설정해봅시다.
//    Button(
//        onClick = {},
//        modifier = Modifier
//            .height(100.dp)
//            .width(200.dp)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }

    // 스텝 4: size에 width와 height를 인자로 넣을 수도 있습니다.
//    Button(
//        onClick = {},
//        modifier = Modifier
//            .size(200.dp)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }

    // 스텝 5: background를 설정해봅시다. (버튼에서는 되지 않음.)
    // Button에 되지 않으면 Text나 Icon에 지정해봅시다.
//    Button(
//        onClick = {},
//        modifier = Modifier
//            .size(200.dp)
//            .background(Color.Red)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }

    // 스탭 6: colors 파라미터에 ButtonDefaults.buttonColors를
    // 넣어보세요. backgroundColor와 contentColor 프로퍼티를
    // 설정하세요.
//    Button(
//        colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta,
//            contentColor = Color.Cyan
//        ),
//        onClick = {},
//        modifier = Modifier
//            .size(200.dp)
//            .background(Color.Red)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }

    // 스텝 7: Button의 modifier에 padding을 추가해봅시다.
//    Button(
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color.Magenta,
//            contentColor = Color.Cyan
//        ),
//        onClick = {},
//        modifier = Modifier
//            .size(300.dp)
//            .padding(50.dp)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }

    // 스탭 8: Button에 enabled를 false로 설정하고, Text의
    // modifier에 clickable을 넣어봅시다.
//    Button(
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color.Magenta,
//            contentColor = Color.Cyan
//        ),
//        onClick = {},
//        enabled = false,
//        modifier = Modifier
//            .size(300.dp)
//            .padding(50.dp)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text(
//            "Search",
//            modifier = Modifier.clickable {}
//        )
//    }

    // 스탭 9: Text의 modifier에 offset를 설정하고 x 파라미터를
    // 설정합니다.
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Magenta,
            contentColor = Color.Cyan
        ),
        onClick = {},
        modifier = Modifier
            .size(300.dp)
            .padding(50.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = null,
            modifier = Modifier.background(Color.Blue)
        )
        Spacer(
            modifier = Modifier
                .size(ButtonDefaults.IconSpacing)
                .background(Color.Blue)
        )
        Text(
            "Search",
            modifier = Modifier.offset(x = 30.dp, y = 20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeStudyTheme {
        ModifierEx()
    }
}
