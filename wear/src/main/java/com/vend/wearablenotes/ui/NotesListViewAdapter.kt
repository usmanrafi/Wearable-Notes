package com.vend.wearablenotes.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.vend.wearablenotes.model.Note

class NotesListViewAdapter(val con: Context, val resource: Int, val notes: List<Note>) :
    ArrayAdapter<Note>(con, resource) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)

        val textView = convertView.findViewById(android.R.id.text1) as TextView
        textView.text = notes[position].text

        return convertView
    }

    override fun getCount(): Int {
        return notes.size
    }

    override fun getItem(position: Int): Note {
        return notes[position]
    }
}