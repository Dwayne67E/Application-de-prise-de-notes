package com.devinci.notes_v1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devinci.notes_v1.datar.dao.NoteDao
import com.devinci.notes_v1.datar.entity.Note
import com.devinci.notes_v1.datar.database.NoteDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteDao: NoteDao): ViewModel() {

    val notes = noteDao.getAllNotes()
    val notesChannel = Channel<NotesEvent>()
    val notesEvent = notesChannel.receiveAsFlow()

    fun insertNote(note: Note) = viewModelScope.launch {
        noteDao.insertNote(note)
        notesChannel.send(NotesEvent.NavigateToNotesFragment)
    }

    fun update(note: Note) = viewModelScope.launch {
        noteDao.updateNote(note)
        notesChannel.send(NotesEvent.NavigateToNotesFragment)
    }

    fun delete(note: Note) = viewModelScope.launch {
        noteDao.deleteNote(note)
        notesChannel.send(NotesEvent.ShowUndoSnackBar("Note deleted successfully", note))
    }

    sealed class NotesEvent{
        data class ShowUndoSnackBar(val msg: String, val note: Note): NotesEvent()
        object NavigateToNotesFragment: NotesEvent()
    }
}