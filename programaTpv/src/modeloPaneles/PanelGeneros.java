package modeloPaneles;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import modeloBoton.BotonGenero;
import modeloBoton.BotonJuego;
import modeloJuego.Generos;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PanelGeneros {
    private final Map<BotonGenero, Set<BotonJuego>> listaGenerosJuegos;
    private final PanelJuegos panelJuegos;
    private final JPanel panel;

    public PanelGeneros(PanelJuegos panelJuegos) {
        this.listaGenerosJuegos = new HashMap<BotonGenero, Set<BotonJuego>>();
        this.panel = new JPanel(new GridLayout(0, 1));
        this.panelJuegos = panelJuegos;
        generaBotonesPanel();
    }

    public JPanel getPanel() {
        return panel;
    }
    //TODO: No debe de devolver esto.
    public Map<BotonGenero, Set<BotonJuego>> getListaGenerosJuegos() {
        return listaGenerosJuegos;
    }

    private void generaBotonesPanel() {
        for (Generos genero : Generos.values()) {
            listaGenerosJuegos.put(new BotonGenero(genero), new HashSet<>());
        }
        for (Map.Entry<BotonGenero, Set<BotonJuego>> mapa : listaGenerosJuegos.entrySet()){

            panel.add(mapa.getKey().getBoton());
            mapa.getKey().getBoton().addActionListener( e-> {
                panelJuegos.actualizaListaBotones(mapa.getValue());
                JOptionPane.showMessageDialog(panelJuegos.getPanelJuego(), "Funciona");
            });
        }
    }

}
