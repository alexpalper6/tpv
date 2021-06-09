package modeloPaneles;

import modeloJuego.Juego;
import modeloTicket.HistoricoTickets;
import modeloTicket.Ticket;
import utilidades.Impresora;

import javax.swing.*;
import java.awt.*;


public class PanelRecibo {

    /**
     * Atributos de la clase PanelRecibo.
     * Tiene 1 panel principal, en el que se añadirán los otros 2 paneles.
     * 2 paneles: panel que tiene la lista de juegos, y un panel con el coste total y un botón para imprimir el recibo.
     * Tiene un JLabel que muestra el coste total del recibo.
     * También tiene un ticket, para obtener la referencia de los datos de los juegos.
     * Finalmente tiene una variable global que es la distancia a separar entre la información de la línea del recibo
     * y el botón que elimina esa línea.
     */
    private final JPanel panelPrincipal;
    private final JPanel panelListaJuegos;
    private final JPanel panelInteractivoRecibo;
    private JLabel costeTotalRecibo;
    private final Ticket ticket;
    private static int distanciaASepararMasLarga;

    /**
     * Constructor de PanelRecibo.
     * Genera los atributos.
     * @param ticket Ticket.
     *
     */
    public PanelRecibo(Ticket ticket) {
        this.panelPrincipal = new JPanel(new BorderLayout());
        this.panelListaJuegos = new JPanel(new FlowLayout(FlowLayout.LEADING));
        this.panelInteractivoRecibo = new JPanel(new GridBagLayout());
        generaPanelInteractivo();
        this.ticket = ticket;

        this.panelPrincipal.add(panelListaJuegos, BorderLayout.CENTER);

        this.panelPrincipal.add(panelInteractivoRecibo, BorderLayout.PAGE_END);
    }

    /**
     * Obtiene el panel de la lista de juegos.
     * @return Devuelve el panel de la lista de juegos.
     */
    public JPanel getPanelListaJuegos() {
        return panelListaJuegos;
    }

    /**
     * Obtiene el panel interactivo.
     * @return Devuelve el panel interactivo del recibo.
     */
    public JPanel getPanelInteractivoRecibo() {
        return panelInteractivoRecibo;
    }

    /**
     * Obtiene el coste total que tiene almacenado el recibo.
     * @return Devuelve el coste total del recibo.
     */
    public JLabel getCosteTotalRecibo() {
        return costeTotalRecibo;
    }

    /**
     * Obtiene el ticket.
     * @return Devuelve el ticket.
     */
    public Ticket getTicket() {
        return ticket;
    }

    /**
     * Obtiene el panel principal.
     * @return Devuelve el panel principal.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Método usado en los action listeners de los botones de juegos.
     * Se le pasa un juego, lo añade al mapa del ticket y muestra la información en el panel de la lista de juegos.
     * También actualiza el coste total.
     * @param juego Juego.
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
     * Actualiza el coste total del recibo.
     */
    private void actualizaCosteTotalRecibo() {
        costeTotalRecibo.setText("Coste total: " + ticket.getCosteTotal());
    }

    /**
     * Actualiza la distancia máxima a separar entre el texto y el botón de eliminar en cada línea del recibo.
     * Obtenido a partir del tamaño de la información de un juego.
     * @param longitud Tamaño de la información del juego.
     */
    public static void actualizaDistanciaMasLarga(int longitud) {
        PanelRecibo.distanciaASepararMasLarga = longitud;
    }

    /**
     * Genera la información del recibo a mostrar en la lista de juegos.
     * Método usado en el método hanPulsado.
     * Utiliza la variable distanciaASepararMasLarga para obtener la distancia que ha de separar en una línea specífica.
     * Esa distancia se obtiene restando la distancia mas larga - la distancia de la información del juego.
     */
    private void generaInfoRecibo() {
        for (Juego j : ticket.getListaJuegosSeleccionados()) {
            int cantidad = ticket.getCantidadAlmacenada(j);
            String subtotal = ticket.getSubtotalJuego(j);

            int distanciaASeparar = distanciaASepararMasLarga - j.getInfo().length();
            distanciaASeparar = distanciaASeparar <= 0 ? 1 : distanciaASeparar;

            JLabel label = new JLabel("x" + cantidad + " " + j.getInfo() + " - Total: " + subtotal
                    + String.format("%" + distanciaASeparar + "s", " "));
            label.setFont(new Font("Courier New", Font.PLAIN, 12));

            JButton boton = new JButton("X");
            JPanel panel = new JPanel();

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
     * Genera los botones y su action listeners.
     * Utiliza grid bag layout y grid bag constraints para que estén los botones bien alineados.
     */
    private void generaPanelInteractivo() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.gridwidth = 2;

        this.costeTotalRecibo = new JLabel("Coste total: 0,0€");
        costeTotalRecibo.setFont(new Font("Courier New", Font.BOLD, 16));
        panelInteractivoRecibo.add(costeTotalRecibo, gbc);

        JButton botonImprimir = new JButton("Imprimir recibo");
        botonImprimir.setPreferredSize(new Dimension(100, 50));
        botonImprimir.addActionListener( e-> {

            int respuesta = JOptionPane.showConfirmDialog(panelPrincipal,"Le has dado a imprimir recibo, ¿quiere hacerlo?"
                    , "Imprimir " + ticket.getLongitudLista() + " diferente(s) artículos en recibo", JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                HistoricoTickets.guardaRecibo(ticket);
                reiniciaRecibo();
                Impresora.imprimirTicket(ticket.getInfoTicketParaImprimir());
            }
        });

        JButton botonDatos = new JButton("Informe Tickets");
        botonDatos.setPreferredSize(new Dimension(100, 50));
        botonDatos.addActionListener( e-> HistoricoTickets.generaHTMLHistoricoticket());

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panelInteractivoRecibo.add(botonImprimir, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panelInteractivoRecibo.add(botonDatos, gbc);
    }

    /**
     * Reinicia el recibo, borra la información y limpia los paneles.
     */
    private void reiniciaRecibo() {
        ticket.limpiaListaJuegos();
        ticket.reiniciaCosteTotal();
        actualizaCosteTotalRecibo();
        panelListaJuegos.removeAll();
        panelListaJuegos.repaint();
        panelListaJuegos.revalidate();
    }
}
