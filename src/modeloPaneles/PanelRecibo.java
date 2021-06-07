package modeloPaneles;

import jdk.nashorn.internal.scripts.JO;
import modeloBoton.BotonJuego;
import modeloJuego.Juego;
import modeloTicket.HistoricoTickets;
import modeloTicket.Ticket;
import utilidades.Impresora;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;


public class PanelRecibo {

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
     * @param tamanyoMonitor
     */
    public PanelRecibo(Ticket ticket, Dimension tamanyoMonitor) {
        this.panelPrincipal = new JPanel(new BorderLayout());
        this.panelListaJuegos = new JPanel(new FlowLayout(FlowLayout.LEADING));
        this.panelInteractivoRecibo = new JPanel();
        generaPanelInteractivo();
        this.ticket = ticket;

        this.panelPrincipal.add(panelListaJuegos, BorderLayout.CENTER);

        this.panelPrincipal.add(panelInteractivoRecibo, BorderLayout.PAGE_END);
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
            label.setFont(new Font("Courier New", Font.PLAIN, 18));
            JButton boton = new JButton("X");
            JPanel panel = new JPanel();

            //panel.setLayout();

            panel.add(label);
            panel.add(boton);
            boton.addActionListener( e-> {
                panelListaJuegos.remove(panel);
                ticket.quitaDeLista(j);
                actualizaCosteTotalRecibo();
                panelListaJuegos.revalidate();
                panelListaJuegos.repaint();
            });
            panelListaJuegos.add(panel);

        }
    }

    /**
     * Método para generar el panel interactivo.
     */
    private void generaPanelInteractivo() {
        this.costeTotalRecibo = new JLabel("Coste total: 0,0€");
        costeTotalRecibo.setFont(new Font("Courier New", Font.BOLD, 16));
        panelInteractivoRecibo.add(costeTotalRecibo);

        JButton botonImprimir = new JButton("Imprimir recibo");
        botonImprimir.setPreferredSize(new Dimension(200, 200));
        botonImprimir.addActionListener( e-> {
            int respuesta = JOptionPane.showConfirmDialog(panelPrincipal,"Le has dado a imprimir recibo, ¿quiere hacerlo?"
                    , "Imprimir " + ticket.getLongitudLista() + " diferente(s) artículos en recibo", JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                HistoricoTickets.guardaRecibo(ticket);
                Impresora.imprimirTicket(ticket.getInfoTicketParaImprimir());
            }
        });

        JButton botonDatos = new JButton("Informe Tickets");
        botonDatos.setPreferredSize(new Dimension(200, 200));
        botonDatos.addActionListener( e-> HistoricoTickets.generaHTMLHistoricoticket());
        panelInteractivoRecibo.add(botonImprimir);
        panelInteractivoRecibo.add(botonDatos);


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
