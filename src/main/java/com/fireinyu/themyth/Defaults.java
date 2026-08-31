package com.fireinyu.themyth;

import java.time.LocalDateTime;

import com.fireinyu.themyth.chatmodes.ChatMode;
import com.fireinyu.themyth.chatmodes.TaskMode;
import com.fireinyu.themyth.util.MythDateTime;


/**
 * Default values for the app.
 * Uses LocalDateTime internally.
 * @see MythDateTime
 * @see LocalDateTime
 */
public class Defaults {
    public static final String TASKFILE = "resources/tasks.csv";
    public static final int LINEWIDTH = 100;
    public static final ChatMode STARTMODE = new TaskMode();
    public static final String USERPROMPT = ">>> ";
    public static final String BOTPROMPT = "The Myth says: ";
    public static final String TWEAKPROMPT = "The Myth tweaks: ";
    public static final String DEATHPROMPT = "The Myth dies from a fatal exception! The tombstone says: ";
    public static final String DATE_INPUTFORMAT = "yyyy-MM-dd-HH-mm-ss";
}
