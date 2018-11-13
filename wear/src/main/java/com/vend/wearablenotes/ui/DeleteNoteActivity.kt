package com.vend.wearablenotes.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.wearable.activity.ConfirmationActivity
import com.vend.wearablenotes.R
import com.vend.wearablenotes.data.SharedPreferenceHelper
import com.vend.wearablenotes.model.Note
import kotlinx.android.synthetic.main.activity_delete_note.*

class DeleteNoteActivity : Activity() {

    private lateinit var note: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_note)

        note = intent.getSerializableExtra("Note") as Note

        setupView()
    }

    private fun setupView() {
        circularProgress.totalTime = 3000
        circularProgress.setOnTimerFinishedListener {

            displayConfirmation("Note Deleted", true)

            SharedPreferenceHelper.removeNote(note)

            val mainIntent = Intent(this, NotesListActivity::class.java)
            mainIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

            Handler().postDelayed({
                startActivity(mainIntent)
                finish()
            }, 1000)
        }

        circularProgress.setOnClickListener {
            finish()
        }

        circularProgress.startTimer()
    }


    private fun displayConfirmation(message: String, success: Boolean) {
        val animation =
            if (success) ConfirmationActivity.SUCCESS_ANIMATION
            else ConfirmationActivity.FAILURE_ANIMATION

        val confirmationActivityIntent = Intent(this, ConfirmationActivity::class.java)
        confirmationActivityIntent.putExtra(
            ConfirmationActivity.EXTRA_ANIMATION_TYPE,
            animation
        )
        confirmationActivityIntent.putExtra(ConfirmationActivity.EXTRA_MESSAGE, message)

        startActivity(confirmationActivityIntent)
    }
}
