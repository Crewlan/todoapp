package com.wolfcodea.todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wolfcodea.todoapp.model.Note
import com.wolfcodea.todoapp.util.DateConverter
import com.wolfcodea.todoapp.util.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class  NoteDatabase: RoomDatabase() {
    abstract  fun noteDao(): NoteDatabaseDao
}