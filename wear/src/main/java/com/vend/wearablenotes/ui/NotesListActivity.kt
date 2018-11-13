package com.vend.wearablenotes.ui

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import com.vend.wearablenotes.R
import com.vend.wearablenotes.data.SharedPreferenceHelper
import com.vend.wearablenotes.model.Note
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class NotesListActivity : WearableActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enables Always-on
        setAmbientEnabled()
    }

    override fun onResume() {
        super.onResume()

        setupListView()
    }

    private fun setupListView() {
        val notes = SharedPreferenceHelper.getAllNotes()
        notes.add(0, Note("", "Add")) // dummy for add option

        lvNotes.adapter = NotesListViewAdapter(this, 0, notes)

        lvNotes.setOnItemClickListener { adapterView, view, i, l ->
            if (i == 0) {
                displayAddNoteScreen()
            } else {
                val note = adapterView.getItemAtPosition(i) as Note

                val detailsIntent = Intent(this, DetailsActivity::class.java)
                detailsIntent.putExtra("Note", note as Serializable)

                startActivity(detailsIntent)
            }
        }
    }

    private fun displayAddNoteScreen() {

        val addIntent = Intent(this, AddNoteActivity::class.java)
        startActivity(addIntent)
    }


}
