package modeloPaneles;

import jdk.nashorn.internal.scripts.JO;
import modeloJuego.Juego;
import modeloTicket.HistoricoTickets;
import modeloTicket.Ticket;
import utilidades.Impresora;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;


public class PanelRecibo implements Serializable {

    /**
     * Atributos de la clase PanelRecibo
     * Tiene 1 panel principal, en el que se añadirán los otros 2 paneles.
     * 2 paneles: panel que tiene la lista de juegos, y un panel con el coste total y un botón para imprimir el recibo.
     * También tiene un ticket, para obtener la referencia de los datos de los juegos.
     */
    private final JPanel panelPrincipal;
    private final JPanel panelListaJuegos;
    private final JPanel panelInteractivoRecibo;
    private JLabel costeTotalRecibo;
    private final Ticket ticket;

    /**
     * Constructor de PanelRecibo.
     * Genera los atributos.
     * @param ticket
     */
    public PanelRecibo(Ticket ticket) {
        this.panelPrincipal = new JPanel(new GridLayout(2,1));
        this.panelListaJuegos = new JPanel(new GridLayout(0, 2));
        this.panelInteractivoRecibo = new JPanel();
        generaPanelInteractivo();
        this.ticket = ticket;
        this.panelPrincipal.add(panelListaJuegos);
        this.panelPrincipal.add(panelInteractivoRecibo);
    }

    /**
     * Obtiene el panel de la lista de juegos.
     * @return panelListaJuegos
     */
    public JPanel getPanelListaJuegos() {
        return panelListaJuegos;
    }

    /**
     * Obtiene el panel interactivo.
     * @return panelInteractivoRecibo.
     */
    public JPanel getPanelInteractivoRecibo() {
        return panelInteractivoRecibo;
    }

    /**
     * Obtiene el coste total que tiene almacenado el recibo.
     * @return costeTotalRecibo.
     */
    public JLabel getCosteTotalRecibo() {
        return costeTotalRecibo;
    }

    /**
     * Obtiene el ticket.
     * @return ticket.
     */
    public Ticket getTicket() {
        return ticket;
    }

    /**
     * Obtiene el panel principal.
     * @return panelPrincipal.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Método usado en los action listeners de los botones de juegos.
     * Se le pasa un juego, lo añade al mapa del ticket y muestra la información en el panel de la lista de juegos.
     * También actualiza el coste total.
     * @param juego
     */
    public void hanPulsado(Juego juego) {
        ticket.anyadeATicket(juego);
        panelListaJuegos.removeAll();
        generaInfoRecibo();
        actualizaCosteTotalRecibo();
        panelListaJuegos.repaint();
        panelListaJuegos.revalidate();
    }

    /**
     * Genera la información del recibo a mostrar en la lista de juegos.
     * Método usado en el método hanPulsado.
     */
    private void generaInfoRecibo() {
        for (Juego j : ticket.getListaJuegosSeleccionados()) {
            int cantidad = ticket.getCantidadAlmacenada(j);
            String subtotal = ticket.getSubtotalJuego(j);

            JLabel label = new JLabel("x" + cantidad + " " + j.getInfo() + " - Total: " + subtotal);
            label.setFont(new Font("Courier New", Font.PLAIN, 20));
            JButton button = new JButton("X");
            button.addActionListener( e-> {
                panelListaJuegos.remove(label);
                panelListaJuegos.remove(button);
                ticket.quitaDeLista(j);
                actualizaCosteTotalRecibo();
                panelListaJuegos.revalidate();
                panelListaJuegos.repaint();
            });
            panelListaJuegos.add(label);
            panelListaJuegos.add(button);
        }
    }

    /**
     * Método para generar el panel interactivo.
     */
    private void generaPanelInteractivo() {
        this.costeTotalRecibo = new JLabel("Coste total: 0,0€");
        costeTotalRecibo.setFont(new Font("Courier New", Font.BOLD, 16));
        panelInteractivoRecibo.add(costeTotalRecibo);
        JButton boton = new JButton("Imprimir recibo");
        boton.addActionListener( e-> {
            int respuesta = JOptionPane.showConfirmDialog(panelPrincipal,"Le has dado a imprimir recibo, ¿quiere hacerlo?", "Imprimir", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                Impresora.imprimirTicket(ticket.getInfoTicketParaImprimir());
                HistoricoTickets.guardaRecibo(ticket);
            }

        });
        panelInteractivoRecibo.add(boton);


    }

    /**
     * Actualiza el coste total del recibo.
     */
    private void actualizaCosteTotalRecibo() {
        costeTotalRecibo.setText("Coste total: " + ticket.getCosteTotal());
    }
    //TODO: Para imprimir el recibo usamos textArea
    //TODO: https://stackoverflow.com/questions/12558212/how-to-show-the-computation-area-in-jtextarea-field-like-a-receipt-with-tax-and
}
