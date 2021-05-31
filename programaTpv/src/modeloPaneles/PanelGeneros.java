package modeloPaneles;

import modeloBoton.BotonGenero;
import modeloBoton.BotonJuego;
import modeloJuego.Generos;
import modeloJuego.Juego;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PanelGeneros {
    private final Map<Generos, Set<BotonJuego>> listaGenerosJuegos;
    private final Set<BotonGenero> botonesGeneros;
    private final PanelJuegos panelJuegos;
    private final JPanel panel;

    public PanelGeneros(PanelJuegos panelJuegos) {
        this.listaGenerosJuegos = new HashMap<>();
        this.panel = new JPanel(new GridLayout(0, 1));
        this.panelJuegos = panelJuegos;
        this.botonesGeneros = new HashSet<>();
        generaMapa();
        generaBotonesPanel();
    }

    public JPanel getPanel() {
        return panel;
    }
    //TODO: No debe de devolver esto.
    public Map<Generos, Set<BotonJuego>> getListaGenerosJuegos() {
        return listaGenerosJuegos;
    }

    private void generaBotonesPanel() {
        for (Map.Entry<Generos, Set<BotonJuego>> mapa : listaGenerosJuegos.entrySet()){
            botonesGeneros.add(new BotonGenero(mapa.getKey()));
        }
        for (BotonGenero bg : botonesGeneros) {
            panel.add(bg.getBoton());
            Generos generoBoton = bg.getGenero();
            bg.getBoton().addActionListener(e -> {
                panelJuegos.actualizaBotonesEnPanel(listaGenerosJuegos.get(generoBoton));
            });
        }
    }

    private void generaMapa() {
        for (Generos genero : Generos.values()) {
            listaGenerosJuegos.put(genero, new HashSet<>());
        }
    }

    public void anyadeJuegoListaGenero(Juego juego) {
        listaGenerosJuegos.get(juego.getGenero()).add(new BotonJuego(juego));
        listaGenerosJuegos.get(Generos.TODOS).add(new BotonJuego(juego));
    }
}
