This is a simple Android application (school project) that displays a task list.
The app is built using Jetpack Compose and uses pure Kotlin functions for data handling.

The User can: 
1. add task
2. sort tasks by due date
3. toggle tasks done
4. filter tasks by done and todo

All funktion can be found within the domain layer.

The screen contains:
a title (Task List)
three top buttons: Add, Filter, Sort
a list of tasks showing:
task title
due date
done state
a Toggle button for each task

The filter button sycles through states:
All -> Todo -> Done -> All ...
