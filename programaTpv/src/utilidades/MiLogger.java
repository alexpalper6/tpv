package utilidades;

import java.io.IOException;
import java.util.logging.*;
import java.io.IOException;


public class MiLogger {
    static Logger logger;

    public MiLogger() {
        Logger result = Logger.getLogger("Log_TPV.txt");
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        Handler fileTxt = null;
        try {
            fileTxt = new FileHandler("Log_TPV.txt", true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileTxt.setFormatter(simpleFormatter);
            fileTxt.setLevel(Level.ALL);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        result.addHandler(consoleHandler);
        result.addHandler(fileTxt);
        result.setLevel(Level.ALL);
    }

    private static Logger getLogger() {
        if(logger == null) {
            new MiLogger();
        }
        return logger;
    }

    public static void log(Level level, String msg) {
        getLogger().log(level, msg);
    }
}
