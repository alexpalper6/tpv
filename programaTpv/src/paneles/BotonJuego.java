package paneles;

import juegos.Juego;

import javax.swing.*;

public class BotonJuego {
    private JButton boton;
    private Juego juego;

    public BotonJuego(Juego juego) {
        this.juego = juego;
        this.boton = new JButton();
    }
}
