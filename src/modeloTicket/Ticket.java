package modeloTicket;

import modeloJuego.Juego;
import utilidades.TPVLogger;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;

public class Ticket implements Serializable {
    /**
     * Atributos de la clase Tickets.
     * Contiene la longitud que se usará para formatear bien el recibo.
     * Almacena los datos como la lista de los juegos, el coste total ,y la creación del recibo.
     */
    private final static int LONGITUD_FORMATO = 20;
    private final Map<Juego, Integer> listaJuegosSeleccionados;
    private int costeTotal;
    private final LocalDateTime fechaCreacion;

    /**
     * Constructor de ticket
     */
    public Ticket() {
        this.listaJuegosSeleccionados = new LinkedHashMap<>();
        this.fechaCreacion = LocalDateTime.now();
    }

    /**
     * Obtiene los juegos que hay en el mapa.
     *
     * @return Lista de juegos.
     */
    public List<Juego> getListaJuegosSeleccionados() {
        List<Juego> salida = new ArrayList<>();
        salida.addAll(listaJuegosSeleccionados.keySet());
        return salida;
    }

    /**
     * Obtiene el coste total del ticket en formato de euro.
     *
     * @return String de dinero.
     */
    public String getCosteTotal() {
        return costeTotal / 100 + "," + costeTotal % 100 + "€";
    }

    /**
     * Obtiene el precio * cantidad del juego.
     *
     * @return String subtotal del juego formato euro.
     * @param juego Juego.
     */
    public String getSubtotalJuego(Juego juego) {
        int subtotal = juego.getPrecio() * listaJuegosSeleccionados.get(juego);
        return subtotal / 100 + "," + subtotal % 100 + "€";
    }

    /**
     * Obtiene la fecha en la que se crea el ticket.
     *
     * @return String con la fecha al crearse.
     */
    public String getFechaCreacion() {
        String dia = fechaCreacion.getDayOfMonth() + "-" + fechaCreacion.getMonthValue() + "-" + fechaCreacion.getYear();
        String hora = fechaCreacion.getHour() + ":" + fechaCreacion.getMinute() + ":" + fechaCreacion.getSecond();
        return dia + " a las " + hora;
    }

    /**
     * Obtiene la longitud de la lista.
     * @return int tamaño de lista.
     */
    public int getLongitudLista() {
        return listaJuegosSeleccionados.size();
    }

    /**
     * Obtiene el valor del mapa, que es la cantidad de veces que se ha almacenado el juego en el mapa.
     *
     * @param juego juego
     * @return int valor de la clave del juego.
     */
    public int getCantidadAlmacenada(Juego juego) {
        return listaJuegosSeleccionados.get(juego);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return costeTotal == ticket.costeTotal &&
                Objects.equals(listaJuegosSeleccionados, ticket.listaJuegosSeleccionados) &&
                Objects.equals(fechaCreacion, ticket.fechaCreacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listaJuegosSeleccionados, costeTotal, fechaCreacion);
    }



    /**
     * Obtiene información del ticket en formato de String para imprimir.
     *
     * @return String datos del ticket.
     */
    public String getInfoTicketParaImprimir() {
        String salida = "";
        salida += "RECIBO \t";
        salida += "Fecha: " + getFechaCreacion() + "\n";
        salida += "----------------------------------------------\n";

        int longNombreMasGrande = getNombreMasLargo() + 5;
        String formatoIdentacion = "%-" + longNombreMasGrande + "s %-" + LONGITUD_FORMATO + "s %-" + LONGITUD_FORMATO + "s %-" + LONGITUD_FORMATO + "s %n";
        for (Juego j : listaJuegosSeleccionados.keySet()) {
            try {
                salida += String.format(formatoIdentacion, j.getNombre(), "Precio: " + j.getPrecioFormateado(), "Cantidad: " + listaJuegosSeleccionados.get(j)
                        , "Subtotal: " + getSubtotalJuego(j)) + "\n";
            } catch (MissingFormatArgumentException msfae) {
                //TODO: LOG
                TPVLogger.log(Level.SEVERE, "El formato no coincide con la cantidad de columnas.");
            }
        }
        salida += "\n----------------";
        salida += "\nTOTAL: " + getCosteTotal();
        return salida;
    }

    /**
     * Obtiene el nombre con mayor longitud, se usa para formatear bien el ticket al imprimir.
     *
     * @return int longitud del nombre más largo.
     */
    private int getNombreMasLargo() {
        int salida = 0;
        for (Juego j : listaJuegosSeleccionados.keySet()) {
            if (salida < j.getNombre().length()) {
                salida = j.getNombre().length();
            }
        }
        return salida;
    }

    /**
     * Añade un juego al listado de juegos del ticket.
     *
     * @param juego juego
     */
    public void anyadeATicket(Juego juego) {
        Integer productoYaAnyadido = listaJuegosSeleccionados.putIfAbsent(juego, 1);
        if (productoYaAnyadido != null) {
            int cantidadAnterior = listaJuegosSeleccionados.get(juego);
            listaJuegosSeleccionados.put(juego, cantidadAnterior + 1);
        }
        actualizaCosteTotal(juego.getPrecio());
    }

    /**
     * Actualiza el coste total del ticket.
     *
     * @param precio coste del juego
     */
    private void actualizaCosteTotal(int precio) {
        costeTotal += precio;
    }

    /**
     * Reinicia el coste total.
     */
    public void reiniciaCosteTotal() {
        costeTotal = 0;
    }

    /**
     * Borra el juego de la colección, también modifica el coste total, ya que se quita el precio de la clave quitada.
     *
     * @param juego juego
     */
    public void quitaDeLista(Juego juego) {
        int cantidadJuego = listaJuegosSeleccionados.get(juego);
        costeTotal = costeTotal - juego.getPrecio() * cantidadJuego;
        listaJuegosSeleccionados.remove(juego);
    }


    public void limpiaListaJuegos() {
        listaJuegosSeleccionados.clear();
    }
}
