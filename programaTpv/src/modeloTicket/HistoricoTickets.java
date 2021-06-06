package modeloTicket;

import utilidades.TPVLogger;

import java.io.*;
import java.util.logging.Level;

public class HistoricoTickets {
    private final static File HISTORICO_DE_TICKETS = new File("Historico_Tickets.txt");
    private final static File HISTORICO_TICKETS_HTML = new File("Resumen_Tickets.html");

    public static void guardaRecibo(Ticket ticket) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(HISTORICO_DE_TICKETS, true))) {
            oos.writeObject(ticket);
        } catch (IOException e) {
            TPVLogger.log(Level.WARNING, "No se ha podido encontrar o crear el archivo de histórico de tickets.");
        }
    }

    private void generaHTMLHistoricoticket() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HISTORICO_DE_TICKETS))) {

        } catch (FileNotFoundException fne) {
            TPVLogger.log(Level.SEVERE, "No se ha encontrado el fichero.");
        } catch (IOException ioe) {
            TPVLogger.log(Level.WARNING, "Ha ocurrido un error con el fichero.");
        }
    }
}
