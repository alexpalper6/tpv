package modeloTicket;

import modeloJuego.Juego;
import utilidades.TPVLogger;

import javax.swing.*;
import java.io.*;
import java.util.logging.Level;


public class HistoricoTickets implements Serializable {
    /**
     * Atributos de la clase HistoricoTickets
     * Contiene 2 atributos globales que contiene la ruta de los archivos de lectura y escritura.
     * También contiene un atributo global de ListaTickets, para que siempre haga referencia a esta y funcione bien
     * la lectura y escritura de objetos.
     */
    private final static File HISTORICO_DE_TICKETS = new File("Historico_Tickets.txt");
    private final static File HISTORICO_TICKETS_HTML = new File("Resumen_Tickets.html");
    private static ListaTickets listaTickets;

    /**
     * Guarda en el recibo un ticket.
     * Si la longitud de la lista de los tickets que se le pasan no son mayor que 0, no añade.
     * Utiliza el método leeListaTicket(), ListaTicket.anyadeTicket() y escribeListaTickets()
     * Lee y escribe en HISTORICO_DE_TICKETS la lista.
     *
     * @param ticket
     */
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

    /**
     * Escribe en el archivo la lista de tickets.
     * @param listaTickets
     */
    private static void escribeListaTickets(ListaTickets listaTickets) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(HISTORICO_DE_TICKETS))) {
            oos.writeObject(listaTickets);

        } catch (IOException e) {
            TPVLogger.log(Level.WARNING, "No se ha podido encontrar o crear el archivo de histórico de tickets.");
        }
    }

    /**
     * Lee en el a Lee el archivo HISTORICO_DE_TICKETS para obtener la lista que hay. Si no hay se crea el archivo.
     *
     */
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

    /**
     * Genera el archivo html a partir de los datos del archivo HISTORICO_DE_TICKETS.
     */
    public static void generaHTMLHistoricoticket() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HISTORICO_DE_TICKETS));
             BufferedWriter bw = new BufferedWriter(new FileWriter(HISTORICO_TICKETS_HTML))) {
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

    /**
     * Genera la cabezera de html.
     * @return string con la cabezera de html.
     */
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

    /**
     * Escribe en el archivo que se le pase la tabla por cada ticket. Una fila por cada juego.
     * @param listaTickets
     * @param bw
     * @throws IOException
     */
    //TODO: Cambiar a string.
    private static void escribeListaJuegosHtml(ListaTickets listaTickets, BufferedWriter bw) throws IOException {

        for (Ticket ticket : listaTickets.getListaTickets()) {
            bw.write("    <table>");
            bw.newLine();
            bw.write("        <tr><th scope=\"col\" colspan=\"4\">Ticket creado el " + ticket.getFechaCreacion() + "</th></tr>");
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
                bw.newLine();
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
