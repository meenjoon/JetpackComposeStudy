package com.mbj.composestudy.architecture.livedata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mbj.composestudy.ui.theme.JetpackComposeStudyTheme

class LiveData : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeStudyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopLevel()
                }
            }
        }
    }
}

// 단계 2: `ViewModel`을 상속받은 `ToDoViewModel`을 만듭니다.
// 첫 단계에서는 내용을 비워두고 시작합시다.
class ToDoViewModel : ViewModel() {
//    val text = mutableStateOf("")
    private val _text = MutableLiveData("")
    val text: LiveData<String> = _text

    val setText: (String) -> Unit = {
        _text.value = it
    }

    /**
     * mutableStateListOf 의 경우에는 불변(immutable)으로 변경하여 사용할 수 없다.
     * ==> mutableStateListOf 는 이렇게만 사용해야만 한다.
     *
     * LiveData를 통해 state List를 만들때는 List 자체가 업데이트가 되어야지만 업데이트가 된다는 단점이 있다.
     * ( 정리 : LiveData<List<T>>.observeAsState() 인 경우 List가 통째로 다른 List로 바뀌었을 때만 State가 갱신된다.
     * 그에 반면, mutableStateListOf는 각 항목이 변경되지 않으면 업데이트가 되지 않는 단점이 있다.
     * ( 정리 : 추가, 삭제, 대입 -> UI가 갱신이 되지만, 각 항목의 필드(속성)이 바뀌었을 때는 갱신이 되지 않음 )
     * 결론적으로 LiveData를 이용해서 list 를 다루어서 State로 바꾸는것은 비효율적이다!!!!
     *  각 항목의 필드(속성)이 바뀌었을 때는 갱신이 되지 않음을 해결하기 위해 불변(Immutable) 한 객체를 집어넣어서 사용하는 StateListOf 를 사용하자 !!!
     */
//    val toDoList = mutableStateListOf<ToDoData>()
    private val _rawToDoList = mutableListOf<ToDoData>()
    private val _toDoList = MutableLiveData<List<ToDoData>>(_rawToDoList)
    val toDoList: LiveData<List<ToDoData>> = _toDoList

    val onSubmit: (String) -> Unit = {
        val key = (_rawToDoList.lastOrNull()?.key ?: 0) + 1
        _rawToDoList.add(ToDoData(key, it))
        _toDoList.value = _rawToDoList.toMutableList()
        _text.value = ""
    }

    val onEdit: (Int, String) -> Unit = { key, newText ->
        val i = _rawToDoList.indexOfFirst { it.key == key }
        _rawToDoList[i] = _rawToDoList[i].copy(text = newText)
        _toDoList.value = _rawToDoList.toMutableList()
    }

    val onToggle: (Int, Boolean) -> Unit = { key, checked ->
        val i = _rawToDoList.indexOfFirst { it.key == key }
        _rawToDoList[i] = _rawToDoList[i].copy(done = checked)
        _toDoList.value = _rawToDoList.toMutableList()
    }

    val onDelete: (Int) -> Unit = { key ->
        val i = _rawToDoList.indexOfFirst { it.key == key }
        _rawToDoList.removeAt(i)
        _toDoList.value = _rawToDoList.toMutableList()
    }
}

// 단계 3: `TopLevel`의 파라미터로 `ToDoViewModel` 타입의
// `viewModel`을 전달합니다. 기본 값은 `viewModel()`로 설정합시다.
// 에러가 발생하면 아래의 `import` 문을 추가합니다.
// `import androidx.lifecycle.viewmodel.compose.viewModel`
@Composable
fun TopLevel(viewModel: ToDoViewModel = viewModel()) {
    // 단계 4: text, setText를 뷰 모델로 옮겨봅시다.
    // 뷰 모델의 프로퍼티로 변경할 경우에는 destrunction (비구조화,
    // 구조 분해)는 사용할 수 없으니 `by`를 써봅시다.
    // `remember`는 제거해야 합니다.
//    val (text, setText) = remember { mutableStateOf("") }

    // 단계 5: `toDoList`, `onSubmit`, `onEdit`, `onToggle`,
    // `onDelete`를 모두 뷰 모델로 옮겨봅시다.
//    val toDoList = remember { mutableStateListOf<ToDoData>() }

//    val onSubmit: (String) -> Unit = {
//        val key = (toDoList.lastOrNull()?.key ?: 0) + 1
//        toDoList.add(ToDoData(key, it))
//        viewModel.text.value = ""
//    }
//
//    val onEdit: (Int, String) -> Unit = { key, newText ->
//        val i = toDoList.indexOfFirst { it.key == key }
//        toDoList[i] = toDoList[i].copy(text = newText)
//    }
//
//    val onToggle: (Int, Boolean) -> Unit = { key, checked ->
//        val i = toDoList.indexOfFirst { it.key == key }
//        toDoList[i] = toDoList[i].copy(done = checked)
//    }
//
//    val onDelete: (Int) -> Unit = { key ->
//        val i = toDoList.indexOfFirst { it.key == key }
//        toDoList.removeAt(i)
//    }

    Scaffold(content = { it
        Column {
            ToDoInput(
                /**
                 * LiveData를 State로 바꾸고 기본값이 있기 때문에 null이 없는 State이고 그 값을 가지고 와서 text에 설정을 하는것이다.
                 */
                text = viewModel.text.observeAsState("").value,
                onTextChange = viewModel.setText,
                onSubmit = viewModel.onSubmit
            )
            val items = viewModel.toDoList.observeAsState(emptyList()).value
            LazyColumn {
                items(
                    items = items,
                    key = { it.key }
                ) { toDoData ->
                    ToDo(
                        toDoData = toDoData,
                        onEdit = viewModel.onEdit,
                        onToggle = viewModel.onToggle,
                        onDelete = viewModel.onDelete
                    )
                }
            }
        }
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeStudyTheme {
        TopLevel()
    }
}

@Composable
fun ToDoInput(
    text: String,
    onTextChange: (String) -> Unit,
    onSubmit: (String) -> Unit
) {
    Row(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Button(onClick = {
            onSubmit(text)
        }) {
            Text("입력")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoInputPreview() {
    JetpackComposeStudyTheme {
        ToDoInput("테스트", {}, {})
    }
}

@Composable
fun ToDo(
    toDoData: ToDoData,
    onEdit: (key: Int, text: String) -> Unit = { _, _ -> },
    onToggle: (key: Int, checked: Boolean) -> Unit = { _, _ -> },
    onDelete: (key: Int) -> Unit = {}
) {
    var isEditing by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.padding(4.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Crossfade(
            targetState = isEditing, label = "",
        ) {
            when (it) {
                false -> {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = toDoData.text,
                            modifier = Modifier.weight(1f)
                        )
                        Text("완료")
                        Checkbox(
                            checked = toDoData.done,
                            onCheckedChange = { checked ->
                                onToggle(toDoData.key, checked)
                            }
                        )
                        Button(
                            onClick = { isEditing = true }
                        ) {
                            Text("수정")
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        Button(
                            onClick = { onDelete(toDoData.key) }
                        ) {
                            Text("삭제")
                        }
                    }
                }

                true -> {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val (text, setText) = remember { mutableStateOf(toDoData.text) }
                        OutlinedTextField(
                            value = text,
                            onValueChange = setText,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Button(onClick = {
                            isEditing = false
                            onEdit(toDoData.key, text)
                        }) {
                            Text("완료")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoPreview() {
    JetpackComposeStudyTheme {
        ToDo(ToDoData(1, "nice", true))
    }
}

data class ToDoData(
    val key: Int,
    val text: String,
    val done: Boolean = false
)
