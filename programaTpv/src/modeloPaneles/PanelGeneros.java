package modeloPaneles;

import modeloBoton.BotonGenero;
import modeloBoton.BotonJuego;
import modeloJuego.Generos;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PanelGeneros {
    JPanel panel;
    Map<BotonGenero, List<BotonJuego>> listaGenerosJuegos;

    public PanelGeneros() {
        this.panel = new JPanel();
        this.listaGenerosJuegos = new HashMap<>();


    }

    private void generaPanel() {
        for (Generos genero: Generos.values()) {
            listaGenerosJuegos.put(new BotonGenero(genero), new ArrayList<>());
        }
    }
}
