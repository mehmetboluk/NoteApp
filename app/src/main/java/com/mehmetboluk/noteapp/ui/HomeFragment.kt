package com.mehmetboluk.noteapp.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehmetboluk.noteapp.R
import com.mehmetboluk.noteapp.adapter.NotesAdapter
import com.mehmetboluk.noteapp.databinding.FragmentHomeBinding
import com.mehmetboluk.noteapp.db.Note
import com.mehmetboluk.noteapp.db.NoteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var list : ArrayList<Note>
    private lateinit var adapter: NotesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = arrayListOf()
        adapter = NotesAdapter(list, requireContext())
        binding.rvNotesApp.adapter = adapter
        binding.rvNotesApp.layoutManager = LinearLayoutManager(context)



        NoteDatabase(requireActivity()).getNotDAO().getAll().observe(viewLifecycleOwner, Observer {
            list.addAll(it)
            adapter.notifyDataSetChanged()
        })

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
        }



    }
}