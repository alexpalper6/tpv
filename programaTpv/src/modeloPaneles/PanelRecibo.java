package modeloPaneles;

import modeloJuego.Juego;
import modeloTicket.Ticket;

import javax.swing.*;

//TODO: Cambiar
public class PanelRecibo {

    private final JPanel panelRecibo;
    private final Ticket ticket;

    public PanelRecibo(Ticket ticket) {
        this.panelRecibo = new JPanel();
        this.ticket = ticket;
    }

    public JPanel getPanelRecibo() {
        return panelRecibo;
    }

    public void hanPulsado(Juego juego) {
        ticket.anyadeATicket(juego);
    }
}
