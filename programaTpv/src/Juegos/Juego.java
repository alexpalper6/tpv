package Juegos;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Juego implements Serializable {
    private String nombre;
    private int precio;
    private Generos genero;
    private ImageIcon imagen;

    public Juego(String nombre, int precio, Generos genero) {
        this.nombre = nombre;
        this.precio = precio;
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrecio() {
        int euros = precio / 100;
        int centimos = precio % 100;
        return euros + "," + centimos;
    }

    public Generos getGenero() {
        return genero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Juego juego = (Juego) o;
        return precio == juego.precio &&
                Objects.equals(nombre, juego.nombre) &&
                genero == juego.genero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, precio, genero);
    }

    private String getNombreArreglado() {
        return nombre.replaceAll(" ","_");
    }


    /*private Image obtieneImagen() {
        String nombre = getNombreArreglado();

    }*/

    public static void main(String[] args) {
        Juego juego = new Juego("Animal Crossing New Horizons", 6999, Generos.FPS);

        System.out.println(juego.getNombreArreglado());
    }
}
