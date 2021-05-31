package modeloPaneles;

import modeloJuego.Juego;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PanelRecibo {
    private final Map<Juego, Integer> listaJuegosRecibo;
    private final JPanel panelRecibo;
    private final JTextArea datosRecibo;
    private final JButton botonRecibo;


    public PanelRecibo() {
        this.listaJuegosRecibo = new HashMap<>();
        this.panelRecibo = new JPanel(new GridLayout(2,1));
        this.datosRecibo = new JTextArea(20,20);
        this.datosRecibo.setEditable(false);
        this.botonRecibo = new JButton();
        panelRecibo.add(datosRecibo);
        panelRecibo.add(botonRecibo);
    }

    public JPanel getPanelRecibo() {
        return panelRecibo;
    }

    public JTextArea getDatosRecibo() {
        return datosRecibo;
    }

    public JButton getBotonRecibo() {
        return botonRecibo;
    }
    //TODO: MODIFICAR
    public void anyadeInfoRecibo(Juego juego) {
        listaJuegosRecibo.put(juego, juego.getPrecio());

    }
}
