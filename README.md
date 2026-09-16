## Introduction

GRAVE WARNING: **All** software engineers may get replaced by *AI*.
PSA: Are ***you*** an aspiring software engineer? Beating AI at software engineering seems more and more like of a ***MYTH*** every day.

### URGENT ACTION YOU MUST DO THIS: EMBRACE THE MYTH

- Stare directly into ***THE MYTH***. Walk into it. Embrace it. Let it envelop you.
- **Start with THE MYTH** chatbot (this product btw).

## Set up

1. Ensure you have JDK 25. **Skip to step 6** if you already have JDK 25.
2. **Download JDK 25**, which includes the Java Virtual Machine (JVM), from the [Java downloads page](https://www.oracle.com/java/technologies/downloads/#java25). Choose the package for your operating system and processor architecture.
3. **Install JDK 25** using the [installation instructions](https://docs.oracle.com/en/java/javase/25/install/) for Windows, macOS, or Linux.
4. **Set** `JAVA_HOME` to the JDK installation directory, excluding its `bin` folder.
5. **Update** `PATH` to place `%JAVA_HOME%\bin` (Windows) or `$JAVA_HOME/bin` (macOS/Linux) before any existing Java entries.
6. **Verify the setup:** Open a new terminal or command prompt and run `java -version`. Confirm that the reported version starts with `25`.
7. Download The Myth from its [sparkling palace](https://github.com/fireinyu/ip/releases/tag/stable-latest).
8. **Summon The Myth** by executing `java -jar themyth.jar` in the directory containing *themyth.jar*.

## Commands

| Command | Description |
| --- | --- |
| [quiz](#cmd-quiz) | View the software engineering quiz question |
| [answer](#cmd-answer) | Answer the quiz question |
| [todo](#cmd-todo) | Create a "todo" task |
| [deadline](#cmd-deadline) | Create a "deadline" task due by a [Datetime](#argument-formats) |
| [event](#cmd-event) | Create an "event" task between start and end [Datetime](#argument-formats)s |
| [list](#cmd-list) | List all tasks |
| [find](#cmd-find) | List tasks matching a keyword |
| [due](#cmd-due) | List "deadline" tasks due by a [Datetime](#argument-formats) |
| [at](#cmd-at) | List "event" tasks happening at a [Datetime](#argument-formats) |
| [mark](#cmd-mark) | Mark a task as completed |
| [unmark](#cmd-unmark) | Mark a task as pending/ incomplete |
| [delete](#cmd-delete) | Delete a task |
| [bye](#cmd-deadline) | Exit the app |

### Take a quiz

| Command | Description |
| --- | --- |
| [quiz](#cmd-quiz) | View the software engineering quiz question |
| [answer](#cmd-answer) | Answer the quiz question |

<a id="cmd-quiz"></a>

#### quiz

- View the software engineering quiz question, along with the answer options.
- The first question is randomly selected.

<a id="cmd-answer"></a>

#### answer \<idx:[Integer](#argument-formats)>

- Attempt the quiz question by selecting an answer option.
- Answering correctly marks the *Quiz Task* as complete.
- A random new question is selected after each attempt, regardless of whether it is right or wrong.
- \<idx>: index of option to use as answer

### Create tasks

| Command | Description |
| --- | --- |
| todo | Create a "todo" task |
| deadline | Create a "deadline" task due by a [Datetime](#argument-formats) |
| event | Create an "event" task between start and end [Datetime](#argument-formats)s |

<a id="cmd-todo"></a>

#### todo \<desc:[String](#argument-formats)>

- Create a new "todo" task.
- \<desc>: description of the task

<a id="cmd-deadline"></a>

#### deadline \<desc:[String](#argument-formats)> \</by:[Datetime](#argument-formats)>

- Create a "deadline" task due by a [Datetime](#argument-formats).
- \<desc>: description of the task
- \<by>: task due [Datetime](#argument-formats)

<a id="cmd-event"></a>

#### event \<desc:[String](#argument-formats)> \</from:[Datetime](#argument-formats)> \</to:[Datetime](#argument-formats)>

- Create an "event" task between start and end [Datetime](#argument-formats)s.
- \<desc>: description of the task
- \<from>: event start [Datetime](#argument-formats)
- \<to>: event end [Datetime](#argument-formats)

### List tasks

| Command | Description |
| --- | --- |
| list | List all tasks |
| find | List tasks matching a keyword |
| due | List "deadline" tasks due by a [Datetime](#argument-formats) |
| at | List "event" tasks happening at a [Datetime](#argument-formats) |

<a id="cmd-list"></a>

#### list \[/sort:[Order](#argument-formats)]

- List all tasks, optionally sorting it first.
- The *index* of each task is set according to its numbering in this list.
- Sorting the tasks may change their numbering in the displayed list. The *index* of each task **will be updated accordingly**.
- \[/sort]: [Order](#argument-formats) for sorting the list of tasks
    - defaults to "modified"

<a id="cmd-find"></a>

#### find \<keyword:[String](#argument-formats)> \[/sort:[Order](#argument-formats)]

- List tasks matching a keyword, optionally in a sorted order.
- The numbering of tasks in this list **does not represent** the *index* of each task.
- \[/sort]: [Order](#argument-formats) for sorting the list of tasks
    - defaults to "modified"

<a id="cmd-due"></a>

#### due \<dt:[String](#argument-formats)> \[/sort:[Order](#argument-formats)]

- List "deadline" tasks due by a [Datetime](#argument-formats), optionally in a sorted order.
- The numbering of tasks in this list **does not represent** the *index* of each task.
- \[/sort]: [Order](#argument-formats) for sorting the list of tasks
    - defaults to "modified"

<a id="cmd-at"></a>

#### at \<dt:[String](#argument-formats)> \[/sort:[Order](#argument-formats)]

- List "event" tasks happening at a [Datetime](#argument-formats), optionally in a sorted order.
- The numbering of tasks in this list **does not represent** the *index* of each task.
- \[/sort]: [Order](#argument-formats) for sorting the list of tasks
    - defaults to "modified"

### Edit tasks

| Command | Description |
| --- | --- |
| mark | Mark a task as completed |
| unmark | Mark a task as pending/ incomplete |
| delete | Delete a task |

<a id="cmd-mark"></a>

#### mark \<idx:[Integer](#argument-formats)>

- Mark a task as completed.
- Marking the *Quiz Task* as completed is equivalent to [quiz](#cmd-quiz); if the *Quiz Task* was unmarked, it remains unmarked.
- \<idx>: Index of task to mark as completed
    - check the index of each task using [list](#cmd-list)

<a id="cmd-unmark"></a>

#### unmark \<idx:[Integer](#argument-formats)>

- Mark a task as pending/ incomplete.
- \<idx>: Index of task to mark as pending/ incomplete
    - check the index of each task using [list](#cmd-list)

<a id="cmd-delete"></a>

#### delete \<idx:[Integer](#argument-formats)>

- Delete a task.
- Marking the *Quiz Task* is not allowed; doing so will cause an *exception* and the *Quiz Task* will remain.
- \<idx>: Index of task to mark as pending/ incomplete
    - check the index of each task using [list](#cmd-list)

### Argument Formats

| Type | Format/s | Notes | Examples |
| --- | --- | --- | --- |
| String | {characters} | No spaces. | software<br>the_myth |
| Integer | {digits} | No negative integers. | 9<br>67 |
| Datetime | YYYY<br>YYYY-MM<br>YYYY-MM-DD<br>YYYY-MM-DD-HH<br>YYYY-MM-DD-HH-mm | Set to the start of the time period.<br>Time is specified in 24-hour format. | 2026-03<br>2026-03-15-21-42 |
| Order | Choice of:<br>- name<br>- created<br>- modified<br>- pending<br><br><br><br> | Sorting order for tasks.<br>**name**: ascending alphabetical order of descriptions.<br>**created**: descending order of creation<br>**modified**: descending order of modification (such as marking)<br>**pending**: unmarked tasks first<br> | name<br>modified |
