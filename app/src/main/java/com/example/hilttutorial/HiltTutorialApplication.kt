package com.example.hilttutorial

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

const val TAG = "HiltTutorialTag"

@HiltAndroidApp
class HiltTutorialApplication: Application() {
}