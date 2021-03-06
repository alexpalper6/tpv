package modeloJuego;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.Serializable;
import java.util.Objects;

public class Juego implements Serializable {
    /**
     * Atributos del objeto, obtenidos al leer de ficheros.
     * El atributo tamanyoImagen comparte la variable que se usa en el main para establecer el tamaño de los botones
     * ya que son del mismo tamaño.
     */

    private final String nombre;
    private final int precio;
    private final Generos genero;
    private static int tamanyoImagen;

    /**
     * Constructor del objeto.
     * @param nombre Nombre del juego.
     * @param precio Precio del juego.
     * @param genero Género del juego.
     */
    public Juego(String nombre, int precio, Generos genero) {
        this.nombre = nombre;
        this.precio = precio;
        this.genero = genero;
    }

    /**
     * Obtiene el nombre del juego.
     * @return Nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio.
     * @return Valor numérico del precio.
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Genera una salida del valor del precio que está en céntimos a formato euros y céntimos.
     * @return Precio en formato euros y céntimos.
     */
    public String getPrecioFormateado() {
        int euros = precio / 100;
        int centimos = precio % 100;
        return euros + "," + centimos + "€";
    }



    /**
     * Obtiene el género del juego.
     * @return Género del juego.
     */
    public Generos getGenero() {
        return genero;
    }

    /**
     * Obtiene la imagen del objeto.
     * @return Imagen del objeto.
     */
   public ImageIcon getImagen() {
        return obtieneImagen();
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

    /**
     * Convierte el nombre en el formato que tienen los nombres de archivos de las imágenes.
     * @return Nombre con "_" en vez de espacios.
     */
    private String getNombreParaImagen() {
        return nombre.replaceAll(" ","_") + ".png";
    }

    /**
     * Devuelve la imagen asociada a su nombre en la carpeta imágenes. Sirve para cargar la imagen que pedirá el botón.
     * @return Icono que se ha obtenido de la carpeta imágenes.
     */
    private ImageIcon obtieneImagen() {
        File rutaImagenJuego = new File("imagenes" + File.separator + getNombreParaImagen());
        ImageIcon icono = new ImageIcon(rutaImagenJuego.getPath());

        //Si la imagen es encontrada, carga si no, se le pone imagen predeterminada
        boolean imagenEncontrada = icono.getIconHeight() > -1;
        if (imagenEncontrada) {
            return cambiaTamanyoImagen(icono);
            //return icono;
        } else {
            icono = new ImageIcon("imagenes" + File.separator + "Imagen_Defecto.png");
        }
        return icono;
    }

    /**
     * Obtiene la información del juego.
     * @return String datos del juego.
     */
    public String getInfo() {
        return nombre + " - " + getPrecioFormateado();
    }

    /**
     * Establece el tamaño de las imágenes, que es el mismo que el de los botones.
     * @param tamanyoImagen Tamaño de la imagen.
     */
    public static void setTamanyoImagen(int tamanyoImagen) {
        Juego.tamanyoImagen = tamanyoImagen;
    }

    /**
     * Modifica el tamaño de la imagen a 100x100 si es necesario, para que todas las imágenes tengan el mismo tamaño.
     * @param imagen Imagen que se ha obtenido de la carpeta imagenes.
     * @return Imagen con el tamaño modificado.
     */
    private ImageIcon cambiaTamanyoImagen(ImageIcon imagen) {
        Image img = imagen.getImage();
        Image imagenTamanyoModif = img.getScaledInstance(tamanyoImagen, tamanyoImagen, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenTamanyoModif);
    }


}
