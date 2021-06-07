package modeloTicket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaTickets implements Serializable {
    private final List<Ticket> listaTickets;

    public ListaTickets() {
        this.listaTickets = new ArrayList<>();
    }

    public List<Ticket> getListaTickets() {
        return listaTickets;
    }
    public void anyadeTicket(Ticket ticket) {
        listaTickets.add(ticket);
    }


}
