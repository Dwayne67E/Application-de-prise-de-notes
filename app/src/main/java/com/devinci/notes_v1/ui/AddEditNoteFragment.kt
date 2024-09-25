package com.devinci.notes_v1.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.devinci.notes_v1.R
import com.devinci.notes_v1.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddEditNoteFragment:Fragment(R.layout.fragment_edit_code) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<NoteViewModel> ()
        val binding = FragmentAddeditnotesBinding.bind(requireView())
        val args: AddEditNoteFragmentArgs by navArgs()
        val note = args.note

        if(note != null) {
            binding.apply {
                titre_edit.setText(note.title)
                contenu_edit.setText(note.content)
                saveBtn.setOnClickListener {
                    val title = titre_edit.text.toString()
                    val content = contenu_edit.text.toString()
                    val updatedNote = note.copy(title = titre, content = contenu, date = System.currentTimeMillis())
                    viewModel.updateNote(UpdateNote)
                }
            }
        }
        else {
            binding.apply {
                saveBtn.setOnClickListener {
                    val title = titre_edit.text.toString()
                    val content = contenu_edit.text.toString()
                    val note = Note(titre = title, contenu = content, date = System.currentTimeMillis())
                    viewModel.insertNote(note)
                }
            }
            viewLifecycleOwner.lifecycleScope.launch {
                ViewModel.notesEvent.collect { event ->
                    if(event is NoteViewModel.NotesEvent.NavigateToNotesFragment) {
                        val action = AddEditNoteFragmentDirections.actionAddEditNoteFragmentToNoteFragment()
                        findNavController().navigate(action)
                    }
                }
            }
        }
    }


}