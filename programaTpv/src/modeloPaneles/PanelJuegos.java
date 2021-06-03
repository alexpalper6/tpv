package modeloPaneles;

import modeloBoton.BotonJuego;
import modeloJuego.Juego;
import modeloTicket.Ticket;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class PanelJuegos {
    /**
     * Atributos de panel de juego.
     * Contiene el panel y una referencia al panel del recibo.
     */
    private final JPanel panelJuego;
    private final Ticket ticket;

    /**
     * Constructor del panel de juegos.
     * Genera el panel con el layout correspondiente y obtiene la referencia del panel del recibo.
     * @param panelRecibo
     */
    public PanelJuegos(PanelRecibo panelRecibo, Ticket ticket) {
        this.panelJuego = new JPanel(new GridLayout( 5, 0));
        this.ticket = ticket;
    }

    /**
     * Obtiene el panel del juego
     * @return panelJuego
     */
    public JPanel getPanelJuego() {
        return panelJuego;
    }

    /**
     * Añade un boton de un juego al panel.
     * @param juego
     */
    public void anyadeJuego(Juego juego) {
        panelJuego.add(new BotonJuego(juego, ticket).getBoton());
    }

    /**
     * Actualiza los botones del panel de juegos, borra todos los que hay y añade nuevos a partir de una lista.
     * Esa listas que pasan son de la clase PanelGeneros.
     * @param listaDeJuegos
     */
    public void actualizaBotonesEnPanel(Set<BotonJuego> listaDeJuegos) {
        panelJuego.removeAll();
        for (BotonJuego boton : listaDeJuegos) {
            panelJuego.add(boton.getBoton());
        }
        panelJuego.revalidate();
        panelJuego.repaint();
    }

}
