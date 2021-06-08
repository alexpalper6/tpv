package modeloPaneles;

import modeloBoton.BotonJuego;
import modeloJuego.Juego;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class PanelJuegos {
    /**
     * Atributos de panel de juego.
     * Contiene el panel y una referencia al panel del recibo.
     * Atributo que contiene el tamaño de los botones.
     */
    private final JPanel panelPrincipal;
    private final PanelRecibo panelRecibo;
    private static int tamanyoBoton;

    /**
     * Constructor del panel de juegos.
     * Genera el panel y obtiene la referencia del panel del recibo. Establece el tamaño de los botones.
     * @param panelRecibo
     * @param tamanyoBoton
     */
    public PanelJuegos(PanelRecibo panelRecibo, int tamanyoBoton) {
        this.panelPrincipal = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.panelRecibo = panelRecibo;
        PanelJuegos.tamanyoBoton = tamanyoBoton;
    }

    /**
     * Obtiene el panel del juego
     * @return panelJuego
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Añade un boton de un juego al panel.
     * Crea un BotonJuego para guardar la referencia.
     * Le añade un action listener para usar un el método de panelRecibo hanPulsado.
     * @param juego
     */
    public void anyadeJuego(Juego juego) {
        BotonJuego bj = new BotonJuego(juego);
        bj.getBoton().setPreferredSize(new Dimension(tamanyoBoton, tamanyoBoton));
        bj.getBoton().addActionListener(e -> panelRecibo.hanPulsado(juego));
        panelPrincipal.add(bj.getBoton());

    }

    /**
     * Devuelve un botón con los datos del juego y con el action listener para realizar al acción hanPulsado.
     * Es como el método anyadeJuego, pero devuelve un JButton en vez de añadir.
     * @param juego
     * @return JButton botón generado con los datos del juego.
     */
    public JButton devuelveBoton(Juego juego) {
        BotonJuego bj = new BotonJuego(juego);
        bj.getBoton().setPreferredSize(new Dimension(tamanyoBoton, tamanyoBoton));
        bj.getBoton().addActionListener(e -> panelRecibo.hanPulsado(juego));
        return bj.getBoton();
    }

    /**
     * Actualiza los botones del panel de juegos, borra todos los que hay y añade nuevos a partir de una lista.
     * Esa listas que pasan son de la clase PanelGeneros.
     * @param listaDeJuegos
     */
    public void actualizaBotonesEnPanel(Set<JButton> listaDeJuegos) {
        panelPrincipal.removeAll();
        for (JButton jb : listaDeJuegos) {
            panelPrincipal.add(jb);
        }
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }





}
