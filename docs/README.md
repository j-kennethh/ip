# Pixel User Guide

Pixel is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI).

<br>

## Quick Start
1. Ensure that you have Java `17` installed on your computer.
2. Download the latest `.jar` from [here](https://github.com/j-kennethh/ip/releases).
3. Copy the file to the folder you want to use as the home folder for Pixel.
4. Open a command terminal, `cd` into the folder you put the jar file in.
5. Use the `java -jar ip.jar` command to run the application.

A welcome message similar to the one below should appear.
```
____________________________________________________________
Hello! I'm Pixel
What can I do for you?
____________________________________________________________
```

Type a command in the command line and press Enter to execute it. \
e.g. typing `help` and pressing Enter will list the commands you can use.

Some example commands you can try:
- `list`: List all tasks.
- `todo read book`: Adds a new todo called `read book` to the task list.
- `delete 3`: Deletes the 3rd task shown in the current list.
- `bye`: Exits the app.

Refer to the Features below for details of each command.

<br>

## Features

### Viewing help: `help`
Shows a message listing the valid commands for the app.

Format: `help`

```
help
____________________________________________________________
Commands: todo, deadline, event, list, mark, unmark, find, delete, bye
____________________________________________________________
```

<br>

### Adding a todo: `todo`
Adds a todo to the task list.

Format: `todo DESCRIPTION` 

Example: `todo read book`

```
todo read book
____________________________________________________________
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.
____________________________________________________________
```

<br>

### Adding a deadline: `deadline`
Adds a deadline to the task list.

Format: `deadline DESCRIPTION /by DATE`

Example: `deadline submit book review /by 6 March`

```
deadline submit book review /by 6 March
____________________________________________________________
Got it. I've added this task:
[D][ ] submit book review (by: 6 March)
Now you have 2 tasks in the list.
____________________________________________________________
```

<br>

### Adding an event: `event`
Adds an event to the task list.

Format: `event DESCRIPTION /from START /to END`

Example: `event CS2113 lecture /from 4pm /to 6pm`

```
event CS2113 lecture /from 4pm /to 6pm
____________________________________________________________
Got it. I've added this task:
[E][ ] CS2113 lecture (from: 4pm to: 6pm)
Now you have 3 tasks in the list.
____________________________________________________________
```

<br>

### Listing all tasks: `list`
Shows a list of all tasks in the task list.

Format: `list`

```
list
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] submit book review (by: 6 March)
3.[E][ ] CS2113 lecture (from: 4pm to: 6pm)
____________________________________________________________
```

<br>

### Marking a task: `mark`
Marks the specified task as done in the task list.

Format: `mark INDEX`

Example: `mark 1`

```
mark 1
____________________________________________________________
Nice! I've marked this task as done:
[T][X] read book
____________________________________________________________
```

<br>

### Unmarking a task: `unmark`
Marks the specified task as not done in the task list.

Format: `unmark INDEX`

Example: `unmark 1`

```
unmark 1
____________________________________________________________
Ok, I've marked this task as not done yet:
[T][ ] read book
____________________________________________________________

```

<br>

### Finding tasks by keyword: `find`
Finds tasks whose description contain the given keyword.

Format: `find KEYWORD`
- The search is case-insensitive. e.g. `book` will match `Book`
- Only one keyword is accepted.
- Only the task description is searched.
- Substrings will be matched. e.g. `boo` will match `book`

Example: `find book`

```
find book
____________________________________________________________
Here are the matching tasks in your list:
1.[T][ ] read book
2.[D][ ] submit book review (by: 6 March)
____________________________________________________________
```

<br>

### Deleting a task: `delete`
Deletes the specified task from the task list.

Format: `delete INDEX`

- Deletes the task at the specified index.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

Example: `list` followed by `delete 2`

```
list
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] submit book review (by: 6 March)
3.[E][ ] CS2113 lecture (from: 4pm to: 6pm)
____________________________________________________________
delete 2
____________________________________________________________
Noted. I've removed this task:
[D][ ] submit book review (by: 6 March)
Now you have 2 tasks in the list.
____________________________________________________________
```

<br>

### Exiting the program: `bye`
Exits the program.

Format: `bye`

```
bye
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

<br>

### Saving the data
Task data are saved in the hard disk automatically after any command that changes the data.

There is no need to save manually.

<br>

### Editing the data file
Task data are saved automatically as a text file `./data.txt`

Advanced users are welcome to update data directly by editing that text file.

Format for todo: `T | 0 or 1 | DESCRIPTION`

Format for deadline: `D | 0 or 1 | DESCRIPTION | DATE`

Format for event: `E | 0 or 1 | DESCRIPTION | START | END`

`0` denotes that a task is not done and `1` denotes that a task is done

Example:
```
T | 0 | read book
D | 1 | submit book review | 6 March
E | 0 | CS2113 lecture | 4pm | 6pm
```

<br>

## Command Summary

| Action       | Format                                  | Example                                   |
|--------------|-----------------------------------------|-------------------------------------------|
| Help         | `help`                                  | `help`                                    |
| Add todo     | `todo DESCRIPTION`                      | `todo read book`                          |
| Add deadline | `deadline DESCRIPTION /by DATE`         | `deadline submit book review /by 6 March` |
| Add event    | `event DESCRIPTION /from START /to END` | `event CS2113 lecture /from 4pm /to 6pm`  |
| List         | `list`                                  | `list`                                    |
| Mark         | `mark INDEX`                            | `mark 1`                                  |
| Unmark       | `unmark INDEX`                          | `unmark 1`                                |
| Find         | `find KEYWORD`                          | `find book`                               |
| Delete       | `delete INDEX`                          | `delete 2`                                |
| Exit         | `bye`                                   | `bye`                                     |
