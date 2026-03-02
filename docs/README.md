# Pixel User Guide

Pixel is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI).


## Quick Start
1. Ensure that you have Java `17` installed on your computer.
2. Download the latest `.jar` from [here](https://github.com/j-kennethh/ip/releases).
3. Copy the file to the folder you want to use as the home folder for Pixel.
4. Open a command terminal, `cd` into the folder you put the jar file in.
5. Use the `java -jar pixel.jar` command to run the application.

A welcome message similar to the one below should appear.
```
____________________________________________________________
Hello! I'm Pixel
What can I do for you?
____________________________________________________________
```

6. Type a command in the command line and press Enter to execute it. \
e.g. typing `help` and pressing Enter will list the commands you can use.

Some example commands you can try:
- `list`: List all tasks.
- `todo read book`: Adds a new todo called `read book` to the task list.
- `delete 3`: Deletes the 3rd task shown in the current list.
- `bye`: Exits the app.

7. Refer to the Features below for details of each command.


## Features

### Viewing help: `help`
Shows a message listing the valid commands for the app.

Format: `help`

```
____________________________________________________________
Commands: todo, deadline, event, list, mark, unmark, find, delete, bye
____________________________________________________________
```


### Adding a todo: `todo`
Adds a todo to the task list.

Format: `todo DESCRIPTION` 

Example: `todo read book`

```
____________________________________________________________
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.
____________________________________________________________
```


### Adding a deadline: `deadline`
Adds a deadline to the task list.

Format: `deadline DESCRIPTION /by DATE`

Example: `deadline submit book review /by 6 March`

```
____________________________________________________________
Got it. I've added this task:
[D][ ] submit book review (by: 6 March)
Now you have 2 tasks in the list.
____________________________________________________________

```


### Adding an event: `event`
Adds an event to the task list.

Format: `event DESCRIPTION /from START /to END`

Example: `event CS2113 lecture /from 4pm /to 6pm`

```
____________________________________________________________
Got it. I've added this task:
[E][ ] CS2113 lecture (from: 4pm to: 6pm)
Now you have 3 tasks in the list.
____________________________________________________________
```


### Listing all tasks: `list`
Shows a list of all tasks in the task list.

Format: `list`

```
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] submit book review (by: 6 March)
3.[E][ ] CS2113 lecture (from: 4pm to: 6pm)
____________________________________________________________
```


### Marking a task: `mark`
Marks the specified task as done in the task list.

Format: `mark INDEX`

Example: `mark 1`

```
____________________________________________________________
Nice! I've marked this task as done:
[T][X] read book
____________________________________________________________
```


### Unmarking a task: `unmark`
Marks the specified task as not done in the task list.

Format: `unmark INDEX`

Example: `unmark 1`

```
____________________________________________________________
Ok, I've marked this task as not done yet:
[T][ ] read book
____________________________________________________________
```


### Finding tasks by keyword: `find`
Finds tasks whose description contain the given keyword.

Format: `find KEYWORD`
- The search is case-insensitive. e.g. `book` will match `Book`
- Only one keyword is accepted.
- Only the task description is searched.
- Substrings will be matched. e.g. `boo` will match `book`

Example: `find book`

```
____________________________________________________________
Here are the matching tasks in your list:
1.[T][ ] read book
2.[D][ ] submit book review (by: 6 March)
____________________________________________________________
```

