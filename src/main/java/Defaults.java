import chatmodes.ChatMode;
import chatmodes.EchoMode;
import chatmodes.TodoMode;

public class Defaults {
    public static final int LINEWIDTH = 100;
    public static final ChatMode STARTMODE = new TodoMode();
    public static final String USERPROMPT = ">>> ";
    public static final String BOTPROMPT = "The Myth says: ";
}
