package com.vend.wearablenotes.application

import android.app.Application
import com.vend.wearablenotes.data.SharedPreferenceHelper

class NotesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        SharedPreferenceHelper.init(this)
    }
}