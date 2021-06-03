package modeloJuego;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.Serializable;
import java.util.Objects;

public class Juego implements Serializable {
    /**
     * Atributos del objeto, obtenidos a futuro al leer de un fichero. //TODO:Cambiarlo cuando se genere el objeto con fichero
     */
    private final String nombre;
    private int precio;
    private final Generos genero;
    private ImageIcon imagen;

    /**
     * Constructor del objeto
     * @param nombre
     * @param precio
     * @param genero
     */
    public Juego(String nombre, int precio, Generos genero) {
        this.nombre = nombre;
        this.precio = precio;
        this.genero = genero;
        this.imagen = obtieneImagen();
    }

    /**
     * Obtiene el nombre del juego.
     * @return nombre.
     */
    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    /**
     * Genera una salida del valor del precio que está en céntimos a formato euros y céntimos.
     * @return precio en formato euros y céntimos
     */
    public String getPrecioFormateado() {
        int euros = precio / 100;
        int centimos = precio % 100;
        return euros + "," + centimos;
    }



    /**
     * Obtiene el género del juego.
     * @return género del juego.
     */
    public Generos getGenero() {
        return genero;
    }

    /**
     * Obtiene la imagen del objeto.
     * @return imagen del objeto
     */
    public ImageIcon getImagen() {
        return imagen;
    }

    /**
     *  Equals de la clase, compara objetos.
     * @param o
     * @return boleano si es igual el objeto a otro o no
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Juego juego = (Juego) o;
        return precio == juego.precio &&
                Objects.equals(nombre, juego.nombre) &&
                genero == juego.genero;
    }

    /**
     * Genera código hash del objeto.
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre, precio, genero);
    }

    /**
     * Convierte el nombre en el formato que tienen los nombres de archivos de las imágenes.
     * @return nombre con "_" en vez de espacios.
     */
    private String getNombreParaImagen() {
        return nombre.replaceAll(" ","_") + ".png";
    }

    /**
     * Devuelve la imagen asociada a su nombre en la carpeta imágenes. Sirve para cargar el atributo ImageIcon del objeto.
     * @return icono que se ha obtenido de la carpeta imágenes.
     */
    private ImageIcon obtieneImagen() {
        File rutaImagenJuego = new File("imagenes" + File.separator + getNombreParaImagen());
        ImageIcon icono = new ImageIcon(rutaImagenJuego.getPath());

        //Si la imagne es encontrada, carga si no, se le pone imagen predeterminada
        boolean imagenEncontrada = icono.getIconHeight() > -1;
        if (imagenEncontrada) {
            return icono;
        } else {
            icono = new ImageIcon("imagenes" + File.separator + "Imagen_Defecto.png");
        }
        return icono;
    }

    public String getInfo() {
        return nombre + " - " + getPrecioFormateado();
    }

    /**
     * Modifica el tamaño de la imagen a 100x100 si es necesario, para que todas las imágenes tengan el mismo tamaño.
     * @param imagen que se ha obtenido de la carpeta imágenes
     * @return imagen con el tamaño modificado.
     */
    //TODO: Probablemente no sea necesario
    /*
    private ImageIcon cambiaTamanyoImagen(ImageIcon imagen) {
        Image img = imagen.getImage();
        Image imagenTamanyoModif = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenTamanyoModif);
    } */
}
