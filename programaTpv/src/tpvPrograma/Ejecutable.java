package tpvPrograma;

import modeloJuego.Generos;
import modeloJuego.Juego;
import modeloPaneles.PanelGeneros;
import modeloPaneles.PanelJuegos;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Ejecutable {
    //Por ahora solo son pruebas
    public static void main(String[] args) {
        File ficLecturaObjetos = new File("Juegos.csv");
        PanelJuegos panelJuego = new PanelJuegos();
        try (BufferedReader bf = new BufferedReader(new FileReader(ficLecturaObjetos))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                List<String> campos = Arrays.asList(linea.split(":"));
                Generos genero = Generos.valueOf(campos.get(2));
                Juego juego = new Juego(campos.get(0), Integer.parseInt(campos.get(1)), genero);
                panelJuego.anyadeJuego(juego);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO: Falta GridBagLayout
        PanelGeneros panelGeneros = new PanelGeneros();
        JFrame frame = new JFrame();
        frame.add(panelJuego.getPanelJuego());
        //frame.add(panelGeneros.getPanel());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
