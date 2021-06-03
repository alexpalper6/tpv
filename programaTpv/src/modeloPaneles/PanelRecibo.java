package modeloPaneles;

import modeloJuego.Juego;
import modeloTicket.Ticket;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//TODO: Cambiar
public class PanelRecibo {

    private final JPanel panelRecibo;
    private final Set<JLabel> infoJuegos;
    private final Ticket ticket;

    public PanelRecibo(Ticket ticket) {
        this.panelRecibo = new JPanel(new GridLayout(0,2));
        this.infoJuegos = new HashSet<>();
        this.ticket = ticket;
    }

    public JPanel getPanelRecibo() {
        return panelRecibo;
    }

    public void hanPulsado(Juego juego) {
        ticket.anyadeATicket(juego);
        panelRecibo.removeAll();
        //TODO: Al recibir una copia del mapa no lo borra bien, error.
        for (Map.Entry<Juego, Integer> m : ticket.getCantidad().entrySet()) {
            JLabel label = new JLabel(m.getKey().getInfo());
            panelRecibo.add(label);
            JButton bj = new JButton("X");
            bj.addActionListener(e ->{
                ticket.quitaDeLista(juego);
                panelRecibo.remove(label);
                panelRecibo.remove(bj);
                panelRecibo.revalidate();}
                );
            panelRecibo.add(bj);
        }
        panelRecibo.repaint();
        panelRecibo.revalidate();

    }
    //TODO: Para imprimir el recibo usamos textArea
    //TODO: https://stackoverflow.com/questions/12558212/how-to-show-the-computation-area-in-jtextarea-field-like-a-receipt-with-tax-and
}
