package com.mbj.composestudy.networkimage

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.mbj.composestudy.ui.theme.JetpackComposeStudyTheme

class NetworkImage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeStudyTheme {
                CoilEx()
            }
        }
    }
}

@Composable
fun CoilEx() {
    // 스텝 3: rememberImagePainter를 이용해 Image의 painter를 설정합니다.
    // (Compose 한국어 문서의 추천, but Deprecated)
    // 이미지 URI: https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part-chapter3-10/app/src/main/res/drawable-hdpi/wall.jpg

    Log.d("@@ 뭐야", "@@@@@")
//    val painter =
//        rememberImagePainter(data = "https://gongu.copyright.or.kr/gongu/wrt/cmmn/wrtFileImageView.do?wrtSn=11288788&filePath=L2Rpc2sxL25ld2RhdGEvMjAxNS8wMi9DTFM2OS9OVVJJXzAwMV8wMjc0X251cmltZWRpYV8yMDE1MTIwMw==&thumbAt=Y&thumbSe=b_tbumb&wrtTy=10006")
//    Image(
//        painter = painter,
//        contentDescription = "엔텔로프 캐년"
//    )
    
    Column {
        AsyncImage(
            model = "https://gongu.copyright.or.kr/gongu/wrt/cmmn/wrtFileImageView.do?wrtSn=11288788&filePath=L2Rpc2sxL25ld2RhdGEvMjAxNS8wMi9DTFM2OS9OVVJJXzAwMV8wMjc0X251cmltZWRpYV8yMDE1MTIwMw==&thumbAt=Y&thumbSe=b_tbumb&wrtTy=10006",
            contentDescription = "엔텔로프 캐년"
        )
        AsyncImage(
            model = "https://gongu.copyright.or.kr/gongu/wrt/cmmn/wrtFileImageView.do?wrtSn=11288788&filePath=L2Rpc2sxL25ld2RhdGEvMjAxNS8wMi9DTFM2OS9OVVJJXzAwMV8wMjc0X251cmltZWRpYV8yMDE1MTIwMw==&thumbAt=Y&thumbSe=b_tbumb&wrtTy=10006",
            contentDescription = "엔텔로프 캐년"
        )
    }


    // 스텝 4: AsyncImage를 이용해봅니다. model에 주소를 적으면 됩니다.
//    AsyncImage(
//        model = "https://gongu.copyright.or.kr/gongu/wrt/cmmn/wrtFileImageView.do?wrtSn=11288788&filePath=L2Rpc2sxL25ld2RhdGEvMjAxNS8wMi9DTFM2OS9OVVJJXzAwMV8wMjc0X251cmltZWRpYV8yMDE1MTIwMw==&thumbAt=Y&thumbSe=b_tbumb&wrtTy=10006",
//        contentDescription = "엔텔로프 캐년"
//    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeStudyTheme {
        CoilEx()
    }
}
