package com.vend.wearablenotes.data

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.vend.wearablenotes.model.Note

class SharedPreferenceHelper {


    companion object {

        private lateinit var sharedPreferences: SharedPreferences

        fun init(context: Context) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        }

        fun saveNote(note: Note) {
            sharedPreferences.edit().putString(note.id, note.text).commit()
        }

        fun getNote(id: String): Note {
            return Note(
                id,
                sharedPreferences.getString(id, "")
            )
        }

        fun getAllNotes(): ArrayList<Note> {

            val notes = ArrayList<Note>()
            val keys = sharedPreferences.all

            keys.forEach {
                val text = it.value as String

                if (!text.isNullOrBlank())
                    notes.add(Note(it.key, it.value as String))
            }

            return notes
        }

        fun removeNote(note: Note) {
            removeNote(note.id)
        }

        fun removeNote(id: String) {
            sharedPreferences.edit().remove(id).commit()
        }

    }
}

