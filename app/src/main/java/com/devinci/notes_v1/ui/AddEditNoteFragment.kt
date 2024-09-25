package com.devinci.notes_v1.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.devinci.notes_v1.databinding.FragmentEditCodeBinding
import com.devinci.notes_v1.R
import com.devinci.notes_v1.datar.entity.Note
import com.devinci.notes_v1.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddEditNoteFragment:Fragment(R.layout.fragment_edit_code) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<NoteViewModel> ()
        val binding = FragmentEditCodeBinding.bind(requireView())
        val args: AddEditNoteFragmentArgs by navArgs()
        val note = args.note

        if(note != null) {
            binding.apply {
                titreEdit.setText(note.titre)
                contenuEdit.setText(note.contenu)
                saveBtn.setOnClickListener {
                    val title = titreEdit.text.toString()
                    val content = contenuEdit.text.toString()
                    val updatedNote = note.copy(titre = title, contenu = content, date = System.currentTimeMillis())
                    viewModel.updateNote(updatedNote)
                }
            }
        }
        else {
            binding.apply {
                saveBtn.setOnClickListener {
                    val title = titreEdit.text.toString()
                    val content = contenuEdit.text.toString()
                    val note = Note(titre = title, contenu = content, date = System.currentTimeMillis())
                    viewModel.insertNote(note)
                }
            }
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.notesEvent.collect { event ->
                    if(event is NoteViewModel.NotesEvent.NavigateToNotesFragment) {
                        val action = AddEditNoteFragmentDirections.actionAddEditNoteFragmentToNoteFragment()
                        findNavController().navigate(action)
                    }
                }
            }
        }
    }


}