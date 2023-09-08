package com.mbj.composestudy.topappbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mbj.composestudy.ui.theme.JetpackComposeStudyTheme


/**
 * TopAppBar를 사용할때는 navigationIcon 와 actions를 사용할것 !!!!
 * Material2 -> Materail3로 넘어오면서 TopAppBar의 경우 무조건 인자(title)를 할당해줘야 하는 방식으로 바뀜
 */
class TopAppBar : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeStudyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopBarEx("Android")
                }
            }
        }
    }
}

@Composable
fun TopBarEx(name: String) {
    Column {
        // 스텝 1: TopAppBar를 만들고 title 항목을 채워봅시다.
//       TopAppBar(title = {
//            Text(text = "TopAppBar")
//        })

        // 스텝 2: navigationIcon 파라미터를 채워봅시다.
        // IconButton을 만들고 자식으로 Icon을 넣읍시다.
        // 아이콘은 Icons.Filled.ArrowBack을 채웁시다.
        // onClick은 비워둡시다.

//        TopAppBar(
//            title = {
//                Text(text = "TopAppBar")},
//            navigationIcon = {
//                IconButton(onClick = { /*TODO*/ }) {
//                    Icon(imageVector = Icons.Filled.ArrowBack,
//                        contentDescription = "업 네비게이션" )
//                }
//            },
//            actions = {
//                IconButton(onClick = { /*TODO*/ }) {
//                    Icon(imageVector = Icons.Filled.Search,
//                        contentDescription = "검색")
//                }
//                IconButton(onClick = { /*TODO*/ }) {
//                    Icon(imageVector = Icons.Filled.Settings,
//                        contentDescription = "설정")
//                }
//                IconButton(onClick = { /*TODO*/ }) {
//                    Icon(imageVector = Icons.Filled.AccountBox,
//                        contentDescription = "계정")
//                }
//            }
//        )


        // 스텝 3: actions를 추가해봅시다.
        // Icons.Filled의 여러 아이콘을 이용해봅시다.

//        CenterAlignedTopAppBar(title = { Text(text = "asd")})
////
//        MediumTopAppBar(title = { Text(text = "ssss")})

        // 스텝 4: TopAppBar content 파라미터 버전을 만들어봅시다.

//        MediumTopAppBar(
//            title = {
//                Text(text = "22")
//            },
//            navigationIcon = {
//                IconButton(onClick = { /*TODO*/ }) {
//                    Icon(
//                        imageVector = Icons.Filled.ArrowBack,
//                        contentDescription = "업 네비게이션"
//                    )
//                }
//            },
//            actions = {
//                IconButton(onClick = { /*TODO*/ }) {
//                    Icon(
//                        imageVector = Icons.Filled.Search,
//                        contentDescription = "검색"
//                    )
//                }
//                IconButton(onClick = { /*TODO*/ }) {
//                    Icon(
//                        imageVector = Icons.Filled.Settings,
//                        contentDescription = "설정"
//                    )
//                }
//                IconButton(onClick = { /*TODO*/ }) {
//                    Icon(
//                        imageVector = Icons.Filled.AccountBox,
//                        contentDescription = "계정"
//                    )
//                }
//            }
//        )

        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                MediumTopAppBar(
                    title = {
                        Text(
                            "Medium TopAppBar",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior
                )
            },
            content = { innerPadding ->
                LazyColumn(
                    contentPadding = innerPadding,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val list = (0..75).map { it.toString() }
                    items(count = list.size) {
                        Text(
                            text = list[it],
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                    }
                }
            })

        Text(text = "Hello $name!")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeStudyTheme {
        TopBarEx("Android")
    }
}
