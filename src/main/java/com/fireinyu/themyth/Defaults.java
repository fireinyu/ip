package com.fireinyu.themyth;

import com.fireinyu.themyth.chatmodes.ChatMode;
import com.fireinyu.themyth.chatmodes.TaskMode;

public class Defaults {
    public static final String TASKFILE = "resources/tasks.csv";
    public static final int LINEWIDTH = 100;
    public static final ChatMode STARTMODE = new TaskMode();
    public static final String USERPROMPT = ">>> ";
    public static final String BOTPROMPT = "The Myth says: ";
    public static final String TWEAKPROMPT = "The Myth tweaks: ";;
    public static final String DEATHPROMPT = "The Myth dies from a fatal exception! The tombstone says: ";
}
