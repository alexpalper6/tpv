package modeloPaneles;

import modeloBoton.BotonJuego;
import modeloJuego.Juego;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class PanelJuegos {
    private final JPanel panelJuego;
    private final PanelRecibo panelRecibo;

    public PanelJuegos(PanelRecibo panelRecibo) {
        this.panelJuego = new JPanel(new GridLayout( 5, 0));
        this.panelRecibo = panelRecibo;
    }

    public JPanel getPanelJuego() {
        return panelJuego;
    }


    public void anyadeJuego(Juego juego) {
        panelJuego.add(new BotonJuego(juego).getBoton());
    }

    public void actualizaBotonesEnPanel(Set<BotonJuego> listaDeJuegos) {
        panelJuego.removeAll();
        for (BotonJuego boton : listaDeJuegos) {
            panelJuego.add(boton.getBoton());
            boton.getBoton().addActionListener(e -> {
                panelRecibo.anyadeInfoRecibo(boton.getJuego());
            });
        }
        panelJuego.revalidate();
        panelJuego.repaint();
    }

}
