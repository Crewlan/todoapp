package com.wolfcodea.todoapp.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wolfcodea.todoapp.data.NoteDataSource
import com.wolfcodea.todoapp.model.Note
import com.wolfcodea.todoapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    //var noteList = mutableStateListOf<Note>()
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged().collect { listOfNotes ->
                _noteList.value = listOfNotes
            }
        }
        //noteList.addAll(NoteDataSource().loadNotes())
    }

     fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }
     fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }
     fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }
     fun removeAllNote() = viewModelScope.launch { repository.deleteAllNotes() }

}