package com.example.week1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.week1.domain.*
import com.example.week1.ui.theme.Week1Theme
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    var taskList by remember { mutableStateOf(mockTasks) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Task List",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Add-nappi
        Button(
            onClick = {
                val newTask = Task(
                    id = taskList.size + 1,
                    title = "New Task",
                    description = "This is a new task",
                    priority = Priority.MEDIUM,
                    dueDate = LocalDate.now().plusDays(7),
                    done = false
                )
                taskList = addTask(taskList, newTask)
            }
        ) {
            Text("Add Task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista
        taskList.forEach { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("${task.title} (done: ${task.done})")

                Button(onClick = {
                    taskList = toggleDone(taskList, task.id)
                }) {
                    Text("Toggle")
                }
            }
        }
    }
}