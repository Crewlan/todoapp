package com.wolfcodea.todoapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NoteCard(
    title: String,
    description: String,
    date: String = "NaN",
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth().clickable{ onClick() },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
       Column (modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)){
           Text(text = title)
           Text(text = description)
           Text(text = date, style = MaterialTheme.typography.labelSmall)
       }
    }
}