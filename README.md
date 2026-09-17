## Introduction

- GRAVE WARNING: **All** software engineers may get replaced by *AI*.
- PSA: Are ***you*** an aspiring software engineer? Beating AI at software engineering seems more and more like a ***MYTH*** every day.

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
| [todo](#cmd-todo) | Create a [todo task](#glossary) |
| [deadline](#cmd-deadline) | Create a [deadline task](#glossary) due by a [Datetime](#argument-formats) |
| [event](#cmd-event) | Create an [event task](#glossary) between start and end [Datetime](#argument-formats)s |
| [list](#cmd-list) | List all [task](#glossary)s |
| [find](#cmd-find) | List [task](#glossary)s matching a keyword |
| [due](#cmd-due) | List [deadline task](#glossary)s due by a [Datetime](#argument-formats) |
| [at](#cmd-at) | List [event task](#glossary)s happening at a [Datetime](#argument-formats) |
| [mark](#cmd-mark) | Mark a [task](#glossary) as [completed](#glossary) |
| [unmark](#cmd-unmark) | Mark a [task](#glossary) as [pending](#glossary) |
| [delete](#cmd-delete) | Delete a [task](#glossary) |
| [bye](#cmd-bye) | Exit the app |

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
- Answering correctly marks the *[Quiz Task](#glossary)* as complete.
- A random new question is selected after each attempt, regardless of whether it is right or wrong.
- \<idx>: index of option to use as answer

### Create [task](#glossary)s

| Command | Description |
| --- | --- |
| [todo](#cmd-todo) | Create a [todo task](#glossary) |
| [deadline](#cmd-deadline) | Create a [deadline task](#glossary) due by a [Datetime](#argument-formats) |
| [event](#cmd-event) | Create an [event task](#glossary) between start and end [Datetime](#argument-formats)s |
|  |  |

<a id="cmd-todo"></a>

#### todo \<desc:[String](#argument-formats)>

- Create a new [todo task](#glossary).
- \<desc>: description of the [task](#glossary)

<a id="cmd-deadline"></a>

#### deadline \<desc:[String](#argument-formats)> \</by:[Datetime](#argument-formats)>

- Create a [deadline task](#glossary) due by a [Datetime](#argument-formats).
- \<desc>: description of the [task](#glossary)
- \<by>: [task](#glossary) due [Datetime](#argument-formats)

<a id="cmd-event"></a>

#### event \<desc:[String](#argument-formats)> \</from:[Datetime](#argument-formats)> \</to:[Datetime](#argument-formats)>

- Create an [event task](#glossary) between start and end [Datetime](#argument-formats)s.
- \<desc>: description of the [task](#glossary)
- \<from>: event start [Datetime](#argument-formats)
- \<to>: event end [Datetime](#argument-formats)

### List [task](#glossary)s

| Command | Description |
| --- | --- |
| [list](#cmd-list) | List all [task](#glossary)s |
| [find](#cmd-find) | List [task](#glossary)s matching a keyword |
| [due](#cmd-due) | List [deadline task](#glossary)s due by a [Datetime](#argument-formats) |
| [at](#cmd-at) | List [event task](#glossary)s happening at a [Datetime](#argument-formats) |

<a id="cmd-list"></a>

#### list \[/sort:[Order](#argument-formats)]

- List all [task](#glossary)s, optionally sorting it first.
- The [index](#glossary) of each [task](#glossary) is set according to its numbering in this list.
- Sorting the [task](#glossary)s may change their numbering in the displayed list. The [index](#glossary) of each [task](#glossary) **will be updated accordingly**.
- \[/sort]: [Order](#argument-formats) for sorting the list of [task](#glossary)s
  - defaults to "modified"

<a id="cmd-find"></a>

#### find \<keyword:[String](#argument-formats)> \[/sort:[Order](#argument-formats)]

- List [task](#glossary)s matching a keyword, optionally in a sorted order.
- The numbering of [task](#glossary)s in this list **does not represent** the [index](#glossary) of each [task](#glossary).
- \[/sort]: [Order](#argument-formats) for sorting the list of [task](#glossary)s
  - defaults to "modified"

<a id="cmd-due"></a>

#### due \<dt:[String](#argument-formats)> \[/sort:[Order](#argument-formats)]

- List "deadline" [task](#glossary)s due by a [Datetime](#argument-formats), optionally in a sorted order.
- The numbering of [task](#glossary)s in this list **does not represent** the [index](#glossary) of each [task](#glossary).
- \[/sort]: [Order](#argument-formats) for sorting the list of [task](#glossary)s
  - defaults to "modified"

<a id="cmd-at"></a>

#### at \<dt:[String](#argument-formats)> \[/sort:[Order](#argument-formats)]

- List "event" [task](#glossary)s happening at a [Datetime](#argument-formats), optionally in a sorted order.
- The numbering of [task](#glossary)s in this list **does not represent** the [index](#glossary) of each [task](#glossary).
- \[/sort]: [Order](#argument-formats) for sorting the list of [task](#glossary)s
  - defaults to "modified"

### Edit [task](#glossary)s

| Command | Description |
| --- | --- |
| mark | Mark a [task](#glossary) as completed |
| unmark | Mark a [task](#glossary) as pending/ incomplete |
| delete | Delete a [task](#glossary) |

<a id="cmd-mark"></a>

#### mark \<idx:[Integer](#argument-formats)>

- Mark a [task](#glossary) as [completed](#glossary).
- Marking the [Quiz Task](#glossary) as [completed](#glossary) is equivalent to [quiz](#cmd-quiz); if the *Quiz [task](#glossary)* was [pending](#glossary), it remains [pending](#glossary).
- \<idx>: [index](#glossary) of [task](#glossary) to mark as [completed](#glossary)
  - check the [index](#glossary) of each [task](#glossary) using [list](#cmd-list)

<a id="cmd-unmark"></a>

#### unmark \<idx:[Integer](#argument-formats)>

- Mark a [task](#glossary) as [pending](#glossary).
- \<idx>: [index](#glossary) of [task](#glossary) to mark as [pending](#glossary)
  - check the [index](#glossary) of each [task](#glossary) using [list](#cmd-list)

<a id="cmd-delete"></a>

#### delete \<idx:[Integer](#argument-formats)>

- Delete a [task](#glossary).
- Marking the [Quiz Task](#glossary) is not allowed; doing so will cause an *exception* and the [Quiz Task](#glossary) will remain.
- \<idx>: [index](#glossary) of [task](#glossary) to mark as [pending](#glossary)
  - check the [index](#glossary) of each [task](#glossary) using [list](#cmd-list)

### Exit the app

<a id="cmd-bye"></a>

#### bye

- Exit the app and save the current [task](#glossary) list to disk.
- **Important**: if you don't use this command (e.g. exit by closing the window), any changes you made **will be lost**.

### Argument Formats

| Type | Format/s | Notes | Examples |
| --- | --- | --- | --- |
| String | {characters} | No spaces. | software<br>the_myth |
| Integer | {digits} | No negative integers. | 9<br>67 |
| Datetime | YYYY<br>YYYY-MM<br>YYYY-MM-DD<br>YYYY-MM-DD-HH<br>YYYY-MM-DD-HH-mm | Set to the start of the time period.<br>Time is specified in 24-hour format. | 2026-03<br>2026-03-15-21-42 |
| Order | Choice of:<br>- name<br>- created<br>- modified<br>- pending<br><br><br><br> | Sorting order for [task](#glossary)s.<br>**name**: ascending alphabetical order of descriptions.<br>**created**: descending order of creation<br>**modified**: descending order of modification (such as marking)<br>**pending**: unmarked [task](#glossary)s first<br> | name<br>modified |

### Glossary

| Term | Definition |
| --- | --- |
| **task** | A tracked activity or work item. Each task comprises a description, a status (completed or pending), creation/modification timestamps. |
| **task index** | A 1-based positive integer corresponding to the position of a task in the list shown by the `list` command. |
| **Todo task** | A simple task containing only a text description without any associated date or time constraints. |
| **Deadline task** | A task that must be completed before a specified due date and time. |
| **Event task** | A task that occurs across a specific time interval defined by start and end datetimes. |
| **Quiz task** | A persistent built-in task for revising software engineering. It cannot be deleted and is marked as complete upon submitting the correct answer to the quiz via the `answer` command. |
| **Completed** | The status of a task indicating that it has been completed (displayed with `[X]`). |
| **Pending** | The status of a task indicating that it has not been completed (displayed with `[ ]`). |
