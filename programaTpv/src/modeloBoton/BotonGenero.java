package modeloBoton;

import modeloJuego.Generos;

import javax.swing.*;
import java.util.Objects;

public class BotonGenero {
    Generos genero;
    JButton boton;

    public BotonGenero(Generos genero) {
        this.genero = genero;
        this.boton = new JButton(genero.getDescripcion());
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