package modeloPaneles;

import modeloBoton.BotonJuego;
import modeloJuego.Juego;
import modeloTicket.Ticket;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PanelJuegos {
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
    public PanelJuegos(PanelRecibo panelRecibo) {
        this.panelJuego = new JPanel(new FlowLayout(FlowLayout.LEFT));
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
     * Crea un BotonJuego para guardar la referencia.
     * Le añade un action listener para usar un el método de panelRecibo hanPulsado.
     * @param juego
     */
    public void anyadeJuego(Juego juego) {
        BotonJuego bj = new BotonJuego(juego);
        bj.getBoton().setPreferredSize(new Dimension(200, 200));
        bj.getBoton().addActionListener(e -> panelRecibo.hanPulsado(juego));
        panelJuego.add(bj.getBoton());

    }

    /**
     * Devuelve un botón con los datos del juego y con el action listener para realizar al acción hanPulsado.
     * Es como el método anyadeJuego, pero devuelve un JButton en vez de añadir.
     * @param juego
     * @return JButton botón generado con los datos del juego.
     */
    public JButton devuelveBoton(Juego juego) {
        BotonJuego bj = new BotonJuego(juego);
        bj.getBoton().setPreferredSize(new Dimension(200, 200));
        bj.getBoton().addActionListener(e -> panelRecibo.hanPulsado(juego));
        return bj.getBoton();
    }

    /**
     * Actualiza los botones del panel de juegos, borra todos los que hay y añade nuevos a partir de una lista.
     * Esa listas que pasan son de la clase PanelGeneros.
     * @param listaDeJuegos
     */
    public void actualizaBotonesEnPanel(Set<JButton> listaDeJuegos) {
        panelJuego.removeAll();
        for (JButton jb : listaDeJuegos) {
            panelJuego.add(jb);
        }
        panelJuego.revalidate();
        panelJuego.repaint();
    }





}
