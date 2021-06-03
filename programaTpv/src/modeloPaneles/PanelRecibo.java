package modeloPaneles;

import modeloJuego.Juego;
import modeloTicket.Ticket;

import javax.swing.*;
import java.awt.*;

//TODO: Cambiar
public class PanelRecibo {

    private final JPanel panelRecibo;
    private final Ticket ticket;

    public PanelRecibo(Ticket ticket) {
        this.panelRecibo = new JPanel(new GridLayout(0,2));
        this.ticket = ticket;
    }

    public JPanel getPanelRecibo() {
        return panelRecibo;
    }

    public void hanPulsado(Juego juego) {
        ticket.anyadeATicket(juego);
        panelRecibo.add(new JLabel(juego.getNombre()));
        panelRecibo.add(new JButton("X"));
        panelRecibo.revalidate();

    }
}
