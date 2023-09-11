package com.mbj.composestudy.pokemon
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// 단계 2: DIApp을 `@HiltAndroidApp`로 어노테이션합니다.
@HiltAndroidApp
class PokemonApp : Application()
