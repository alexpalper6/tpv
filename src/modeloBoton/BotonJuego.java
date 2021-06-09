package modeloBoton;

import modeloJuego.Generos;
import modeloJuego.Juego;
import modeloTicket.Ticket;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.Serializable;
import java.util.Objects;

public class BotonJuego {
    /**
     * Atributos del botón, contiene el botón y el juego para tener sus datos.
     */
    private final JButton boton;
    private final Juego juego;

    /**
     * Constructor del objeto, genera botón a partir de la imagen del juego.
     * @param juego
     */
    public BotonJuego(Juego juego) {
        this.juego = juego;
        this.boton = new JButton(juego.getImagen());
    }


    /**
     * Devuelve el botón
     * @return boton del objeto.
     */
    public JButton getBoton() {
        return boton;
    }

    /**
     * Devuelve el juego que tiene.
     * @return juego
     */
    public Juego getJuego() {
        return juego;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BotonJuego that = (BotonJuego) o;
        return juego.equals(that.juego);
    }

    @Override
    public int hashCode() {
        return Objects.hash(juego);
    }
}
