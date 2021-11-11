package com.mehmetboluk.noteapp.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mehmetboluk.noteapp.R
import com.mehmetboluk.noteapp.databinding.FragmentAddNoteBinding
import com.mehmetboluk.noteapp.db.Note
import com.mehmetboluk.noteapp.db.NoteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding
    private var note : Note? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        binding = FragmentAddNoteBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            note = AddNoteFragmentArgs.fromBundle(it).note
            binding.etTitle.setText(note?.title)
            binding.etNote.setText(note?.note)
        }

        binding.fabSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val explanation = binding.etNote.text.toString()
            if(title.isEmpty() || explanation.isEmpty()){
                Toast.makeText(context,"Please enter all information", Toast.LENGTH_LONG).show()
                 return@setOnClickListener
            }

            CoroutineScope(Dispatchers.Main).launch {
                val item = Note(title,explanation)

                if(note == null){
                    NoteDatabase(requireActivity()).getNotDAO().upsert(item)
                    Toast.makeText(context, "Successfully saved.", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment)
                }else{
                    item.id = note!!.id
                    NoteDatabase(requireActivity()).getNotDAO().upsert(item)
                    Toast.makeText(context, "Successfully updated.", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment)
                }

            }

        }
    }

    fun deleteNote(){
        AlertDialog.Builder(context).apply {
            setTitle("Are you sure?")
            setMessage("You can not undo this operation")
            setPositiveButton("YES"){_, _->
                CoroutineScope(Dispatchers.Main).launch {
                    NoteDatabase(context).getNotDAO().delete(note!!)
                }
                findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment)
            }
            setNegativeButton("NO"){_, _ ->

            }
        }.create().show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
           R.id.deleteMenu -> deleteNote()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_delete, menu)
    }
}