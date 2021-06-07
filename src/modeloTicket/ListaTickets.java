package modeloTicket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaTickets implements Serializable {
    /**
     * Esta clase se usa para escribir y leer en un fichero de objetos una lista.
     * Atributos de ListaTickets.
     * Contiene una lista de tickets.
     */
    private final List<Ticket> listaTickets;

    /**
     * Constructor, inicializa la lista.
     */
    public ListaTickets() {
        this.listaTickets = new ArrayList<>();
    }

    /**
     * Devuelve una lista de tickets.
     * @return Lista de tickets.
     */
    public List<Ticket> getListaTickets() {
        return listaTickets;
    }

    /**
     * Añade a la lista un ticket.
     * @param ticket
     */
    public void anyadeTicket(Ticket ticket) {
        listaTickets.add(ticket);
    }


}
