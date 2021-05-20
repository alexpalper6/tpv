package Paneles;

import Juegos.Juego;

import javax.swing.*;

public class BotonJuego {
    private JButton boton;
    private Juego juego;

    public BotonJuego(Juego juego) {
        this.juego = juego;
        this.boton = new JButton();
    }
}
