package com.mehmetboluk.noteapp.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mehmetboluk.noteapp.R
import com.mehmetboluk.noteapp.db.Note
import com.mehmetboluk.noteapp.db.NoteDatabase
import com.mehmetboluk.noteapp.ui.HomeFragmentDirections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesAdapter(private val list : ArrayList<Note> = arrayListOf(), private val context: Context) : RecyclerView.Adapter<NotesAdapter.VH>() {



    class VH(val view: View) : RecyclerView.ViewHolder(view){
        val title : TextView = itemView.findViewById(R.id.etRowTitle)
        val body : TextView = itemView.findViewById(R.id.etRowName)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_noteapp,parent,false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val currentItem = list[position]
        holder.title.text = "Title: ${currentItem.title}"
        holder.body.text = "Exp: ${currentItem.note}"

        holder.view.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddNoteFragment()
            action.note = currentItem
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }


}