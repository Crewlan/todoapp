package com.wolfcodea.todoapp.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wolfcodea.todoapp.R
import com.wolfcodea.todoapp.components.NoteButton
import com.wolfcodea.todoapp.components.NoteCard
import com.wolfcodea.todoapp.components.NoteInputText
import com.wolfcodea.todoapp.data.NoteDataSource
import com.wolfcodea.todoapp.model.Note
import com.wolfcodea.todoapp.util.formatDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit,
) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_title)) },
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Bell Notification"
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color(0xFF673AB7),
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White,
                actionIconContentColor = Color.White,
            ),
        )
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(modifier = Modifier.fillMaxWidth(),
                text = title,
                label = "Title",
                maxLine = 2,
                onTextChange = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() }) title = it
                })

            NoteInputText(modifier = Modifier.fillMaxWidth(),
                text = description,
                label = "Add a note",
                maxLine = 2,
                onTextChange = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() }) description = it
                })
            Spacer(modifier = Modifier.size(10.dp))
            NoteButton(text = "Save", onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    onAddNote(Note(title = title, description = description))
                    Toast.makeText(context, "Note added", Toast.LENGTH_SHORT).show()
                }else if(title.isNotEmpty() && description.isEmpty()){
                    Toast.makeText(context, "Insert a description", Toast.LENGTH_SHORT).show()
                }else if(title.isEmpty() && description.isNotEmpty()){
                    Toast.makeText(context, "Insert a Title", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(context, "Insert a text/description", Toast.LENGTH_SHORT).show()
                }
                title = ""
                description = ""
            })
        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn{
            items(notes) { note -> NoteCard(
                title = note.title,
                description = note.description,
                date = formatDate(note.entryDate.time),
                onClick = {
                    onRemoveNote(note)
                }
            ) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = NoteDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}