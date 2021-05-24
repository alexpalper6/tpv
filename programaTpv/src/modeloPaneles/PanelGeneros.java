package modeloPaneles;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import modeloBoton.BotonGenero;
import modeloBoton.BotonJuego;
import modeloJuego.Generos;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;

public class PanelGeneros {
    private final Map<BotonGenero, Set<BotonJuego>> listaGenerosJuegos;
    private final JPanel panel;

    public PanelGeneros() {
        this.listaGenerosJuegos = new HashMap<BotonGenero, Set<BotonJuego>>();
        this.panel = new JPanel(new GridLayout(0, 1));
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
        }
    }

    //Pruebas
    public static void main(String[] args) {
        PanelGeneros panel = new PanelGeneros();
        JFrame frame = new JFrame();
        panel.getPanel().setBorder(new EmptyBorder(0, 0, 0, 0));
        frame.add(panel.getPanel());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

    }


}
