package modeloPaneles;

import modeloBoton.BotonJuego;
import modeloJuego.Juego;
import modeloTicket.Ticket;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Set;

public class PanelJuegos implements Serializable {
    /**
     * Atributos de panel de juego.
     * Contiene el panel y una referencia al panel del recibo.
     */
    private final JPanel panelJuego;
    private final PanelRecibo panelRecibo;

    /**
     * Constructor del panel de juegos.
     * Genera el panel y obtiene la referencia del panel del recibo.
     * @param panelRecibo
     */
    public PanelJuegos(PanelRecibo panelRecibo, Ticket ticket) {
        this.panelJuego = new JPanel(new GridLayout( 5, 0));
        this.panelRecibo = panelRecibo;
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
     * Le añade un action listener para usar un el método de panelRecibo hanPulsado.
     * @param juego
     */
    public void anyadeJuego(Juego juego) {
        BotonJuego bj = new BotonJuego(juego);
        panelJuego.add(bj.getBoton());
        bj.getBoton().addActionListener(e -> {panelRecibo.hanPulsado(juego);
                }
        );
    }

    /**
     * Actualiza los botones del panel de juegos, borra todos los que hay y añade nuevos a partir de una lista.
     * Esa listas que pasan son de la clase PanelGeneros.
     * @param listaDeJuegos
     */
    public void actualizaBotonesEnPanel(Set<Juego> listaDeJuegos) {
        panelJuego.removeAll();
        for (Juego juego : listaDeJuegos) {
            anyadeJuego(juego);
        }
        panelJuego.revalidate();
        panelJuego.repaint();
    }

}
