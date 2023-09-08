package com.mbj.composestudy.checkbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mbj.composestudy.ui.theme.JetpackComposeStudyTheme

/**
 * compose에서는 계속 다시 그려지는 과정이 반복이 되는데 이것을 recomposition 이라고 말을 하고 상태가 바꼈을때 recompositon 이 이루어진다고 말을 한다.
 * by(위임)을 이용하면 그 자체 프로퍼티 인 것 처럼 쓸 수 있기 때문에 .value를 생략하고 사용할 수 있다.
 */

class CheckBox : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeStudyTheme {
                CheckBoxEx()
            }
        }
    }
}

@Composable
fun CheckBoxEx() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        // 스텝 1: Checkbox를 만들어봅시다. checked 속성은 false
        // onCheckedChange는 비워둡시다.

//        Checkbox(checked = false, onCheckedChange = {})

        // 스텝 2: onCheckedChange에서 boolean 값 변수를 바꾸고
        // checked에서 그 값을 반영해봅시다. (잘 되지 않습니다.)
//        var checked = false
//        Checkbox(checked = false,
//            onCheckedChange = {
//                checked = !checked
//            }
//        )

        // 스텝 3: boolean 대신 remember { mutableStateOf(false) }를
        // 사용하여 상태를 도입합시다. (value 프로퍼티를 이용해야 합니다.)
//        val checked = remember {
//            mutableStateOf(false)
//        }
//        Checkbox(
//            checked = checked.value,
//            onCheckedChange = {
//                checked.value = !checked.value
//            }
//        )

        // 스텝 4: delegated properties로 변경해봅시다.
//        val checked by remember {
//            mutableStateOf(false)
//        }
//        Checkbox(
//            checked = checked,
//            onCheckedChange = {
//                checked = !checked
//            }
//        )


        // 스텝 5: destruction으로 상태를 받아서 사용해봅시다.

        val (getter, setter) = remember {
            mutableStateOf(false)
        }
        Checkbox(
            checked = getter,
            onCheckedChange = setter
        )

        // Checkbox를 앞에 넣어주세요.
        Text(
            text = "프로그래머입니까?",
            modifier = Modifier.clickable {
                setter(!getter)
            })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeStudyTheme {
        CheckBoxEx()
    }
}
