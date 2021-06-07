package modeloTicket;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import modeloJuego.Juego;
import sun.rmi.runtime.Log;
import utilidades.TPVLogger;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistoricoTickets implements Serializable {
    private final static File HISTORICO_DE_TICKETS = new File("Historico_Tickets.txt");
    private final static File HISTORICO_TICKETS_HTML = new File("Resumen_Tickets.html");
    private static ListaTickets listaTickets;

    public static void guardaRecibo(Ticket ticket) {
        if (ticket.getLongitudLista() > 0) {
            leeListaTicket();
            listaTickets.anyadeTicket(ticket);
            escribeListaTickets(listaTickets);
        } else {
            JOptionPane.showConfirmDialog(null, "No ha añadido nada al ticket, no se guardará el recibo"
                    , "Error", JOptionPane.WARNING_MESSAGE);
        }

    }

    private static void escribeListaTickets(ListaTickets listaTickets) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(HISTORICO_DE_TICKETS))) {
            oos.writeObject(listaTickets);

        } catch (IOException e) {
            TPVLogger.log(Level.WARNING, "No se ha podido encontrar o crear el archivo de histórico de tickets.");
        }
    }

    private static void leeListaTicket() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HISTORICO_DE_TICKETS))) {
            listaTickets = (ListaTickets) ois.readObject();
        } catch (FileNotFoundException e) {
            try {
                listaTickets = new ListaTickets();
                HISTORICO_DE_TICKETS.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            TPVLogger.log(Level.WARNING, "No se ha podido importar la lista de tickets.");
        }
    }

    public static void generaHTMLHistoricoticket() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HISTORICO_DE_TICKETS));
             BufferedWriter bw = new BufferedWriter(new FileWriter(HISTORICO_TICKETS_HTML)))
        {
            ListaTickets listaTickets;
            listaTickets = (ListaTickets) ois.readObject();
            bw.write(getCabezaraHtml());
            escribeListaJuegosHtml(listaTickets, bw);
            bw.write("</body>\n</html>");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String getCabezaraHtml() {
        String cabezera = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Datos de histórico de tickets</title>\n" +
                "</head>\n" +
                "<body>\n";
        return cabezera;
    }

    private static void escribeListaJuegosHtml(ListaTickets listaTickets, BufferedWriter bw) throws IOException {

        for (Ticket ticket : listaTickets.getListaTickets()) {
            bw.write("    <table>");
            bw.newLine();
            bw.write("        <tr><th scope=\"col\" colspan=\"4\">Ticket creado el "+ ticket.getFechaCreacion() + "</th></tr>" );
            bw.newLine();
            bw.write("       <tr>\n" +
                    "            <th scope=\"col\">Nombre</th>\n" +
                    "            <th>Precio</th>\n" +
                    "            <th>Cantidad</th>\n" +
                    "            <th>Subtotal</th>\n" +
                    "        </tr>");
            bw.newLine();
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
            bw.write("<tr><td colspan=\"4\">TOTAL: " + ticket.getCosteTotal() + "</tr></td>");
            bw.write("    </table>");
            bw.newLine();
        }
    }

}
