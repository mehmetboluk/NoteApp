package com.mehmetboluk.noteapp.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.mehmetboluk.noteapp.R
import com.mehmetboluk.noteapp.databinding.FragmentNoteDetailsBinding
import com.mehmetboluk.noteapp.db.Note
import com.mehmetboluk.noteapp.db.NoteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteDetails : Fragment() {

    private lateinit var binding : FragmentNoteDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteDetailsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            with(it.getBundle("item") as Note) {
                binding.tvTitleDetail.text = this.title
                binding.tvBodyDetail.text = this.note
        }
        }



    }


}