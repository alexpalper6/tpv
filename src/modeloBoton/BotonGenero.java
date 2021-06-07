package modeloBoton;

import modeloJuego.Generos;

import javax.swing.*;
import java.io.Serializable;
import java.util.Objects;

public class BotonGenero {
    /**
     * Atributos de la clase.
     * Tiene el género y un botón que contendrá la información del género.
     */
    private final Generos genero;
    private final JButton boton;

    /**
     * Constructor de genero.
     * @param genero
     */
    public BotonGenero(Generos genero) {
        this.genero = genero;
        this.boton = new JButton(genero.getDescripcion());
    }

    /**
     * Obtiene el género.
     * @return genero
     */
    public Generos getGenero() {
        return genero;
    }

    /**
     * Obtiene el botón.
     * @return boton.
     */
    public JButton getBoton() {
        return boton;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BotonGenero that = (BotonGenero) o;
        return genero == that.genero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(genero);
    }
}