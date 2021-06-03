package modeloPaneles;

import modeloBoton.BotonGenero;
import modeloBoton.BotonJuego;
import modeloJuego.Generos;
import modeloJuego.Juego;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PanelGeneros {
    /**
     * Atributos del panel de género
     * Contiene un mapa de generos y cada genero tiene una lista de generos.
     * Contiene un conjunto de botones de generos que no se pueden repetir.
     * (Es necesario tener el boton y el mapa separados ya que necesitamos la referencia del genero del boton,
     * para poder llamar al mapa y realizar las acciones necesarias.)
     * Contiene también un panel y una referencia al panel de juegos para poder añadir la lista al panel.
     */
    private final Map<Generos, Set<BotonJuego>> listaGenerosJuegos;
    private final Set<BotonGenero> botonesGeneros;
    private final PanelJuegos panelJuegos;
    private final JPanel panel;

    /**
     * Constructor del panel de genero.
     * Genera el mapa y los botones.
     * @param panelJuegos
     */
    public PanelGeneros(PanelJuegos panelJuegos) {
        this.listaGenerosJuegos = new HashMap<>();
        this.panel = new JPanel(new GridLayout(0, 1));
        this.panelJuegos = panelJuegos;
        this.botonesGeneros = new HashSet<>();
        generaMapa();
        generaBotonesPanel();
    }

    /**
     * Obtiene el panel.
     * @return panel.
     */
    public JPanel getPanel() {
        return panel;
    }
    //TODO: No debe de devolver esto.
    public Map<Generos, Set<BotonJuego>> getListaGenerosJuegos() {
        return listaGenerosJuegos;
    }

    /**
     * Genera los botones a partir de los generos del mapa.
     * A partir de la lista de los botones, los añade al panel y crea su action listener.
     * Su action listener usa el método de la clase PanelJuegos para actualizar los botones del panel, usando este y los valores
     * de la clave referente del genero del boton.
     */
    private void generaBotonesPanel() {
        for (Map.Entry<Generos, Set<BotonJuego>> mapa : listaGenerosJuegos.entrySet()){
            botonesGeneros.add(new BotonGenero(mapa.getKey()));
        }
        for (BotonGenero bg : botonesGeneros) {
            panel.add(bg.getBoton());
            bg.getBoton().addActionListener(e -> panelJuegos.actualizaBotonesEnPanel(listaGenerosJuegos.get(bg.getGenero())));
        }
    }

    /**
     * Genera las claves del mapa e inicializa el conjunto.
     */
    private void generaMapa() {
        for (Generos genero : Generos.values()) {
            listaGenerosJuegos.put(genero, new HashSet<>());
        }
    }

    /**
     * Añade el juego al mapa, cada juego con su género correspondiente, y al apartado de todos.
     * @param juego
     */
    public void anyadeJuegoListaGenero(Juego juego) {
        listaGenerosJuegos.get(juego.getGenero()).add(new BotonJuego(juego));
        listaGenerosJuegos.get(Generos.TODOS).add(new BotonJuego(juego));
    }
}
