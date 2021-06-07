package modeloTicket;

import modeloJuego.Juego;
import sun.rmi.runtime.Log;
import utilidades.TPVLogger;

import javax.swing.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistoricoTickets implements Serializable {
    private final static File HISTORICO_DE_TICKETS = new File("Historico_Tickets.txt");
    private final static File HISTORICO_TICKETS_HTML = new File("Resumen_Tickets.html");

    public static void guardaRecibo(Ticket ticket) {
        if (ticket.getLongitudLista() > 0) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(HISTORICO_DE_TICKETS, true))) {
                oos.writeObject(ticket);

            } catch (IOException e) {
                TPVLogger.log(Level.WARNING, "No se ha podido encontrar o crear el archivo de histórico de tickets.");
            }
        } else {
            JOptionPane.showConfirmDialog(null, "No ha añadido nada al ticket, no se guardará el recibo"
                    , "Error", JOptionPane.WARNING_MESSAGE);
        }

    }

    public static void generaHTMLHistoricoticket() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HISTORICO_DE_TICKETS))) {
            Ticket ticket;
            while (true) {
                ticket = (Ticket) ois.readObject();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException eofe) {
            TPVLogger.log(Level.FINE, "Fin del while.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*private static void escribeListaJuegosHtml(Ticket ticket, BufferedWriter bw) throws IOException {
        for (Juego juego : ticket.getListaJuegosSeleccionados()) {
            bw.write("        <tr>");
            bw.newLine();
            bw.write("            <td>" + juego.getNombre() + "</td>");
            bw.write("            <td>" + juego.getPrecioFormateado() + "</td>");
            bw.newLine();
            bw.write("            <td>" + ticket.getCantidadAlmacenada(juego) + "</td>");
            bw.newLine();
            bw.write("            <td>" + ticket.getSubtotalJuego(juego) + " </td>");
            bw.newLine();
            bw.write("        </tr>");
            bw.newLine();
        }
    }*/
}
 /*
                bw.write("    <table>");
                bw.newLine();
                bw.write("        <tr><th scope=\"col\" colspan=\"4\">Ticket</th></tr>" + ticketLeido.getFechaCreacion() + "</th><tr>");
                bw.newLine();
                bw.write("       <tr>\n" +
                        "            <th scope=\"col\">Nombre</th>\n" +
                        "            <th>Precio</th>\n" +
                        "            <th>Cantidad</th>\n" +
                        "            <th>Subtotal</th>\n" +
                        "        </tr>");

                bw.write("    </table>");
                bw.newLine();

                 */

/*String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Datos de histórico de tickets</title>\n" +
                "</head>\n" +
                "<body>\n"; */