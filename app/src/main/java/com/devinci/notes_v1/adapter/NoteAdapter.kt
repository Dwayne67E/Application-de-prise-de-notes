package com.devinci.notes_v1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devinci.notes_v1.databinding.ItemNoteBinding
import com.devinci.notes_v1.datar.entity.Note
import com.devinci.notes_v1.ui.NoteFragment
import java.text.SimpleDateFormat

class NoteAdapter(private val mNotes: List<Note>, private val listener: NoteFragment): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    interface OnNoteClickListener{
        fun onNoteClick(note: Note)
        fun onNoteLongClick(note: Note)
    }
    inner class ViewHolder(private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION) {
                        val note = mNotes[position]
                        listener.onNoteClick(note)
                    }
                }
                root.setOnLongClickListener {
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION) {
                        val note = mNotes[position]
                        listener.onNoteLongClick(note)
                    }
                    true
                }
            }
        }
        fun bind(note: Note){
            binding.apply {
                titreNote.text = note.titre
                contenuNote.text = note.contenu
                val formatter = SimpleDateFormat("dd/MM/yyyy")
                dateNote.text = note.date.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(mNotes[position]) {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int {
        return mNotes.size
    }

}