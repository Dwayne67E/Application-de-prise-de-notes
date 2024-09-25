package com.devinci.notes_v1.di

import android.content.Context
import androidx.room.Room
import com.devinci.notes_v1.datar.database.NoteDatabase
import com.devinci.notes_v1.datar.entity.Note
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(context, NoteDatabase::class.java, "NoteDataBase")

    @Provides
    @Singleton
    fun provideNoteDao(db: NoteDatabase) = db.noteDao()
}