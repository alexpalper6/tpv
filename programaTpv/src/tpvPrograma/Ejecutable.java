package tpvPrograma;

import modeloJuego.Generos;
import modeloJuego.Juego;
import modeloPaneles.PanelGeneros;
import modeloPaneles.PanelJuegos;
import modeloPaneles.PanelRecibo;
import modeloTicket.Ticket;
import utilidades.TPVLogger;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class Ejecutable {

    //Por ahora solo son pruebas
    //TODO: Usar look and feel
    public static void main(String[] args) {

        File ficLecturaObjetos = new File("Juegos.csv");
        Ticket ticket = new Ticket();
        PanelRecibo panelRecibo = new PanelRecibo(ticket);

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
                TPVLogger.log(Level.INFO, "Juego " + juego.getNombre() + " añadido correctamente.");
            }
        } catch (FileNotFoundException e) {
            TPVLogger.log(Level.SEVERE, "No se ha encontrado el fichero.");
        } catch (ArrayIndexOutOfBoundsException aibe) {
            TPVLogger.log(Level.WARNING, "El juego no se ha creado correctamente.");
            //TODO: Log del aibe
        } catch (IllegalArgumentException iae) {

        } catch (IOException e) {
            TPVLogger.log(Level.WARNING, "Algo ha pasado.");
        }

        //TODO: Falta GridBagLayout

        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(1,3));
        frame.add(panelJuego.getPanelJuego());
        frame.add(panelRecibo.getPanelPrincipal());
        frame.add(panelGeneros.getPanel());

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
