package com.vend.wearablenotes.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.vend.wearablenotes.R
import com.vend.wearablenotes.model.Note
import kotlinx.android.synthetic.main.activity_details.*
import java.io.Serializable

class DetailsActivity : Activity() {

    private lateinit var note: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        note = intent.getSerializableExtra("Note") as Note

        setupView()
    }

    private fun setupView() {
        tvNoteDetails.text = note.text

        ibDeleteNote.setOnClickListener {
            val deleteNoteIntent = Intent(this, DeleteNoteActivity::class.java)
            deleteNoteIntent.putExtra("Note", note as Serializable)

            startActivity(deleteNoteIntent)
        }
    }
}
