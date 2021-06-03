package modeloTicket;

import modeloJuego.Juego;
import modeloPaneles.PanelRecibo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Ticket implements Serializable {
    /**
     * Atributos de la clase Tickets,
     */
    private final Map<Juego,Integer> listaJuegosSeleccionados;
    private int costeTotal;
    private final LocalDateTime fechaCreacion;
    private final PanelRecibo panelRecibo;

    /**
     * Constructor de ticket
     */
    public Ticket(PanelRecibo panelRecibo) {
        this.listaJuegosSeleccionados = new HashMap<>();
        this.fechaCreacion = LocalDateTime.now();
        this.panelRecibo = panelRecibo;
    }

    public Map<Juego, Integer> getListaJuegosSeleccionados() {
        return listaJuegosSeleccionados;
    }

    public int getCosteTotal() {
        return costeTotal;
    }

    public void setCosteTotal(int costeTotal) {
        this.costeTotal = costeTotal;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
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
     * Añade un juego al listado de juegos del ticket.
     * @param juego
     */
    public void anyadeATicket(Juego juego) {
        Integer productoYaAnyadido = listaJuegosSeleccionados.putIfAbsent(juego, 1);
        if (productoYaAnyadido != null) {
            int cantidadAnterior = listaJuegosSeleccionados.get(juego);
            listaJuegosSeleccionados.put(juego, cantidadAnterior + 1);
        }
        actualizaPrecio(juego);
    }

    /**
     * Actualiza el coste total del ticket.
     * @param juego
     */
    private void actualizaPrecio(Juego juego) {
        costeTotal += juego.getPrecio();
    }
}
