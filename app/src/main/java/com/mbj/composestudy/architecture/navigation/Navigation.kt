package com.mbj.composestudy.architecture.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mbj.composestudy.ui.theme.JetpackComposeStudyTheme

/**
 * navigation을 사용할 때 사용되는 속성인 popUpTo("원하는 곳")는 스택에서 해당 요소를 찾아서 그 사이에 있는 스택을 모두 지우는 것을 말한다.
 * popUpTo("원하는 곳")의 후행 블럭(PopUpToBuilder)에서 사용되는 속성인 inclusibve = true로 설정하면 원하는 곳을 설정한 곳만 스택에서 지운다.
 * ==> 그렇다면 inclusive는 언제 사용될까 ?
 * ==> Home -> Login -> Mail 이라는 UserFlow가 있다고 하면 Login 후에 Mail로 이동하고서 inclucive = true를 이용해서 Login 화면을 스택에서 지워 Home으로 가게 할 때 사용된다.
 * ==> popUpto("Login") { inclucive = true }
 * popUpTo("원하는 곳")의 후행 블럭(PopUpToBuilder)에서 사용되는 속성인 launchSingleTop = true로 설정하면 자신의 화면을 들어온다고 하더라도 스택에 쌓이지 않는다.
 * 즉, 쉽게 말하면 자신의 화면이 최상단에 떠있을때 화면을 새로 띄우지 않는것이다.
 * ==> Home -> Home -> Home 을 했을 시 뒤로가기를 하면 종료가 된다.
 */

class Navigation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeStudyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyNav()
                }
            }
        }
    }
}

// 단계 2: `navController` 파라미터를 만듭니다.
// `NavHostController` 타입에 기본 값은 `rememberNavController()`
@Composable
fun MyNav(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    // 단계 3: `NavHost`를 만듭니다.
    // `navController`, `"Home"`, `modifier`를 전달합시다.
    NavHost(navController, "Home", modifier = modifier) {
        composable("Home") {
            Column {
                Text("Home")
                Button(onClick = {
                    navController.navigate("Office") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }) {
                    Text("Office로 이동")
                }
                Button(onClick = {
                    navController.navigate("Playground") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }) {
                    Text("Playground로 이동")
                }
                Button(onClick = {
                    navController.navigate("Home") {
                        launchSingleTop = true
                    }
                }) {
                    Text("Home으로 이동")
                }
                Button(onClick = {
                    navController.navigate("Argument/fastcampus") {
                        launchSingleTop = true
                    }
                }) {
                    Text("fastcampus 아이디로  이동")
                }
            }
        }
        composable("Office") {
            Column {
                Text("Office")
                Button(onClick = {
                    navController.navigate("Playground") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }) {
                    Text("Playground로 이동")
                }
                Button(onClick = {
                    navController.navigate("Home") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }) {
                    Text("Home으로 이동")
                }
            }
        }
        composable("Playground") {
            Column {
                Text("Playground")
                Button(onClick = {
                    navController.navigate("Home") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }) {
                    Text("Home으로 이동")
                }
                Button(onClick = {
                    navController.navigate("Office") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }) {
                    Text("Office로 이동")
                }
            }
        }

        composable("Argument/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.get("userId")
            Text("userId: $userId")
        }
    }

    // 단계 4: `composable("Home")`를 만들고 안에 "Office로 이동" 버튼을
    // 만듭니다.

    // 단계 5: `composable("Office")`를 만들고 텍스트를 넣어봅시다.
    // "Office로 이동" 버튼에 `navController.navigate("Office")`를
    // 넣어줍니다.

    // 단계 6: `Playground`를 만들고 `Home`, `Office`, `Playgorund`를
    // 서로 연결합니다.

    // 단계 7: Home, Office, Playgorund, Home, Office, Playgorund
    // 순으로 이동한 후 백버튼을 계속 눌러서 이동을 확인해봅시다.

    // 단계 8: navigate에 후행 람다로 `popUpTo("Home")`을 넣고 스택 이동을
    // 확인해봅니다.

    // 단계 9: `popUpTo`의 후행 람다에 `inclusive = true`를 넣어보고
    // 스택 이동을 확인해봅시다.

    // 단계 10: `Home`에서 `Home`으로 가는 버튼을 만들고
    // `launchSingleTop = true`을 설정해보세요.

    // 단계 11: "Argument/{userId}"를 라우트로 받는
    // composable을 만드세요.
    // `arguments?.get("userId")`을 받아 출력하세요.
    // "Argument/fastcampus"로 이동하는 버튼을 만들어보세요.
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeStudyTheme {
        MyNav()
    }
}
