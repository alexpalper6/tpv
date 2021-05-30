package tpvPrograma;

import modeloJuego.Generos;
import modeloJuego.Juego;
import modeloPaneles.PanelGeneros;
import modeloPaneles.PanelJuegos;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Ejecutable {
    //Por ahora solo son pruebas
    public static void main(String[] args) {
        File ficLecturaObjetos = new File("Juegos.csv");
        PanelJuegos panelJuego = new PanelJuegos();
        PanelGeneros panelGeneros = new PanelGeneros(panelJuego);
        try (BufferedReader bf = new BufferedReader(new FileReader(ficLecturaObjetos))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                List<String> campos = Arrays.asList(linea.split(":"));
                Generos genero = Generos.valueOf(campos.get(2));
                Juego juego = new Juego(campos.get(0), Integer.parseInt(campos.get(1)), genero);
                panelJuego.anyadeJuego(juego);
                panelGeneros.anyadeJuegoListaGenero(juego);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO: Falta GridBagLayout

        JFrame frame = new JFrame();
        frame.setLayout(new GridBagLayout());
        GridBagConstraints g1 = new GridBagConstraints();
        g1.gridx = 0;
        g1.gridy = 0;
        frame.add(panelJuego.getPanelJuego(), g1);
        g1.gridx = 1;
        g1.gridy = 1;
        frame.add(panelGeneros.getPanel(), g1);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
