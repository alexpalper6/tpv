package modeloPaneles;

import modeloBoton.BotonJuego;
import modeloJuego.Juego;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class PanelJuegos {
    private Set<BotonJuego> listaJuegos;
    private final JPanel panelJuego;

    public PanelJuegos() {
        this.listaJuegos = new HashSet<>();
        this.panelJuego = new JPanel(new GridLayout( 0, 5));
    }

    public JPanel getPanelJuego() {
        return panelJuego;
    }

    public String getListaJuegos() {
        String salida = "";
        for (BotonJuego botonJuego : listaJuegos) {
            salida += botonJuego.getJuego().getNombre() + "\n";
        }
        return salida;
    }

    public void anyadeJuego(Juego juego) {
        listaJuegos.add(new BotonJuego(juego));
        panelJuego.add(new BotonJuego(juego).getBoton());
    }

    public void actualizaListaBotones(Set<BotonJuego> listaDeJuegos) {
        panelJuego.removeAll();
        panelJuego.repaint();
        for (BotonJuego boton : listaDeJuegos) {
            panelJuego.add(boton.getBoton());
        }
    }

}
