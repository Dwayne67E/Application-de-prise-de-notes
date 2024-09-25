package com.devinci.notes_v1.datar.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "notes")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titre: String?,
    val contenu: String?,
    val date: Long
): Parcelable