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

  // Filtteri, null= kaikki fale= tekemätön, true= tehdyt
    var filterDone by remember { mutableStateOf<Boolean?>(null) }

    val visibleList = when (filterDone) {
        null -> taskList
        else -> filterByDone(taskList, filterDone!!)
    }

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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // Add
            Button(onClick = {
                val newTask = Task(
                    id = taskList.size + 1,
                    title = "New Task",
                    description = "This is a new task",
                    priority = Priority.MEDIUM,
                    dueDate = LocalDate.now().plusDays(7),
                    done = false
                )
                taskList = addTask(taskList, newTask)
            }) {
                Text("Add")
            }

            // Filter
            Button(onClick = {
                filterDone = when (filterDone) {
                    null -> false
                    false -> true
                    true -> null
                }
            }) {
                Text(
                    when (filterDone) {
                        null -> "Filter: All"
                        false -> "Filter: Todo"
                        true -> "Filter: Done"
                    }
                )
            }

            // Sort
            Button(onClick = {
                taskList = sortByDueDate(taskList)
            }) {
                Text("Sort")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista
        visibleList.forEach { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Title + dueDate + done samaan "vasempaan palstaan"
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = task.title)
                    Text(
                        text = "Due: ${task.dueDate}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Done: ${task.done}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                //toggle
                Button(onClick = {
                    taskList = toggleDone(taskList, task.id)
                }) {
                    Text("Toggle")
                }
            }
        }
    }
}