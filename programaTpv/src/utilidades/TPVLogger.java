package utilidades;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;


public class TPVLogger {
    private static final String FICHERO = "Log_TPV.txt";
    static Logger logger;

    /**
     * Constructor privado de MiLogger. Se usa en el método getLogger.
     */
    private TPVLogger() {
        //Borra handler
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers.length > 0 && handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }

        logger = Logger.getLogger(FICHERO   );
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        Handler fileTxt = null;
        try {
            fileTxt = new FileHandler(FICHERO, true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileTxt.setFormatter(simpleFormatter);
            fileTxt.setLevel(Level.WARNING);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        logger.addHandler(consoleHandler);
        logger.addHandler(fileTxt);
        logger.setLevel(Level.ALL);
    }

    /**
     * Crea el log si no está creado.
     * @return Logger.
     */
    private static Logger getLogger() {
        if(logger == null) {
            new TPVLogger();
        }
        return logger;
    }

    /**
     * Crea un mensaje de log de esta clase, usando un nivel, y el mensaje.
     * @param level
     * @param msg
     */
    public static void log(Level level, String msg) {
        getLogger().log(level, msg);
    }
}
