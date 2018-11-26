package com.vend.wearablenotes.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.wearable.activity.ConfirmationActivity
import com.vend.wearablenotes.R
import com.vend.wearablenotes.data.SharedPreferenceHelper
import com.vend.wearablenotes.model.Note
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : Activity() {
    private val SPEECH_CODE = 100
    private val TEXT_MAX_LENGTH = 150

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        setupView()
    }

    private fun setupView() {
        ibVoiceInput.setOnClickListener {
            val speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            speechIntent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )

            startActivityForResult(speechIntent, SPEECH_CODE)
        }

        ibConfirm.setOnClickListener {
            val text = etNoteText.text.toString()
            if (text.length <= TEXT_MAX_LENGTH) {
                SharedPreferenceHelper.saveNote(
                    Note(
                        System.currentTimeMillis().toString(),
                        text
                    )
                )
                displayConfirmation("Added Note", true)
                finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SPEECH_CODE && resultCode == Activity.RESULT_OK) {
            val result = data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

            etNoteText.setText(result[0])

        }
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
