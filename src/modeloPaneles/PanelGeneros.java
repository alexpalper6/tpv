package modeloPaneles;

import modeloBoton.BotonGenero;
import modeloJuego.Generos;
import modeloJuego.Juego;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PanelGeneros {
    /**
     * Atributos del panel de género
     * Contiene un mapa de generos y cada genero tiene una lista de botones los cuales tienen datos de botones de juegos.
     * Contiene un conjunto de botones de generos que no se pueden repetir.
     * (Es necesario tener el boton y el mapa separados ya que necesitamos la referencia del genero del boton,
     * para poder llamar al mapa y realizar las acciones necesarias.)
     * Contiene también un panel y una referencia al panel de juegos para poder añadir la lista al panel.
     */
    private final Map<Generos, Set<JButton>> listaGenerosJuegos;
    private final Set<BotonGenero> botonesGeneros;
    private final PanelJuegos panelJuegos;
    private final JPanel panelPrincipal;

    /**
     * Constructor del panel de genero.
     * Genera el mapa y los botones.
     *
     * @param panelJuegos
     */
    public PanelGeneros(PanelJuegos panelJuegos) {
        this.listaGenerosJuegos = new LinkedHashMap<>();
        this.panelPrincipal = new JPanel(new GridLayout(0, 1));
        this.panelJuegos = panelJuegos;
        this.botonesGeneros = new LinkedHashSet<>();
        generaMapa();
        generaBotonesPanel();
    }

    /**
     * Obtiene el panel.
     *
     * @return panel.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Obtiene una copia del mapa.
     * @return Map<Generos, Set<JButton>> copia del mapa.
     */
    public Map<Generos, Set<JButton>> getListaGenerosJuegos() {
        Map<Generos, Set<JButton>> copiaMapa = new LinkedHashMap<>();
        copiaMapa.putAll(listaGenerosJuegos);
        return copiaMapa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PanelGeneros that = (PanelGeneros) o;
        return Objects.equals(listaGenerosJuegos, that.listaGenerosJuegos) && Objects.equals(botonesGeneros, that.botonesGeneros) && Objects.equals(panelJuegos, that.panelJuegos) && Objects.equals(panelPrincipal, that.panelPrincipal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listaGenerosJuegos, botonesGeneros, panelJuegos, panelPrincipal);
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
     * Genera los botones a partir de los generos del mapa.
     * A partir de la lista de los botones, los añade al panel y crea su action listener.
     * Su action listener usa el método de la clase PanelJuegos para actualizar los botones del panel, usando este y los valores
     * de la clave referente del genero del boton.
     */
    private void generaBotonesPanel() {
        for (Map.Entry<Generos, Set<JButton>> mapa : listaGenerosJuegos.entrySet()) {
            botonesGeneros.add(new BotonGenero(mapa.getKey()));
        }
        for (BotonGenero bg : botonesGeneros) {
            panelPrincipal.add(bg.getBoton());
            bg.getBoton().addActionListener(e -> panelJuegos.actualizaBotonesEnPanel(listaGenerosJuegos.get(bg.getGenero())));
        }
    }


    /**
     * Añade un botón con los datos del juego al mapa. Utiliza un método del panel de juegos para eso.
     * Añade el botón a su clave correspondiente.
     *
     * @param juego
     */
    public void anyadeJuegoListaGenero(Juego juego) {
        listaGenerosJuegos.get(juego.getGenero()).add(panelJuegos.devuelveBoton(juego));
        listaGenerosJuegos.get(Generos.TODOS).add(panelJuegos.devuelveBoton(juego));
    }
}
