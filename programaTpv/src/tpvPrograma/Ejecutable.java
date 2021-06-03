package tpvPrograma;

import modeloJuego.Generos;
import modeloJuego.Juego;
import modeloPaneles.PanelGeneros;
import modeloPaneles.PanelJuegos;
import modeloPaneles.PanelRecibo;
import modeloTicket.Ticket;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Ejecutable {
    //Por ahora solo son pruebas
    //TODO: Usar look and feel
    public static void main(String[] args) {
        File ficLecturaObjetos = new File("Juegos.csv");

        PanelRecibo panelRecibo = new PanelRecibo();
        Ticket ticket = new Ticket(panelRecibo);
        PanelJuegos panelJuego = new PanelJuegos(panelRecibo, ticket);
        PanelGeneros panelGeneros = new PanelGeneros(panelJuego);

        try (BufferedReader bf = new BufferedReader(new FileReader(ficLecturaObjetos))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                List<String> campos = Arrays.asList(linea.split(";"));
                Generos genero = Generos.valueOf(campos.get(2));
                Juego juego = new Juego(campos.get(0), Integer.parseInt(campos.get(1)), genero);
                panelJuego.anyadeJuego(juego);
                panelGeneros.anyadeJuegoListaGenero(juego);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException aibe) {

            //TODO: Log del aibe
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //TODO: Falta GridBagLayout

        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(1,3));
        frame.add(panelJuego.getPanelJuego());
        frame.add(panelRecibo.getPanelRecibo());
        frame.add(panelGeneros.getPanel());

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
