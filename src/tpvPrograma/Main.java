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
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class Main {

    //Por ahora solo son pruebas
    //TODO: Usar look and feel
    public static void main(String[] args) {
        Dimension tamanyoMonitor = Toolkit.getDefaultToolkit().getScreenSize();
        File ficLecturaObjetos = new File("Juegos.csv");
        File ficLeturaTamBoton = new File("Tamanyo_Boton.txt");

        int tamanyoBoton = 200;
        try {
             List<String> lineas = Files.readAllLines(ficLeturaTamBoton.toPath());
             tamanyoBoton = Integer.parseInt(lineas.get(0));
        } catch (IOException e) {
            TPVLogger.log(Level.SEVERE, "No se ha encontrado el fichero");
        }

        Ticket ticket = new Ticket();
        PanelRecibo panelRecibo = new PanelRecibo(ticket);
        PanelJuegos panelJuego = new PanelJuegos(panelRecibo, tamanyoBoton);
        PanelGeneros panelGeneros = new PanelGeneros(panelJuego);
        Juego.setTamanyoImagen(tamanyoBoton);

        try (BufferedReader bf = new BufferedReader(new FileReader(ficLecturaObjetos))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                List<String> campos = Arrays.asList(linea.split(";"));
                Generos genero = Generos.valueOf(campos.get(2));
                Juego juego = new Juego(campos.get(0), Integer.parseInt(campos.get(1)), genero);

                panelJuego.anyadeJuego(juego);
                panelGeneros.anyadeJuegoListaGenero(juego);
                panelRecibo.actualizaDistanciaMasLarga(juego.getInfo());
                TPVLogger.log(Level.FINE, "Juego " + juego.getNombre() + " añadido correctamente.");
            }
        } catch (FileNotFoundException e) {
            TPVLogger.log(Level.SEVERE, "No se ha encontrado el fichero.");
        } catch (ArrayIndexOutOfBoundsException aibe) {
            TPVLogger.log(Level.WARNING, "El juego no se ha creado correctamente.");
        } catch (IllegalArgumentException iae) {
            TPVLogger.log(Level.WARNING, "Formato incorrecto.");
        } catch (IOException e) {
            TPVLogger.log(Level.WARNING, "Ha ocurrido algo inesperado.");
        }


        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        int tamanyoHorizontal = (int)tamanyoMonitor.getWidth() * 7 / 12;
        panelJuego.getPanelPrincipal().setPreferredSize(new Dimension(tamanyoHorizontal, (int)tamanyoMonitor.getHeight()));
        frame.add(panelJuego.getPanelPrincipal(), BorderLayout.WEST);
        tamanyoHorizontal = (int)tamanyoMonitor.getWidth() * 5 / 12;
        panelRecibo.getPanelPrincipal().setPreferredSize(new Dimension(tamanyoHorizontal, (int)tamanyoMonitor.getHeight()));
        frame.add(panelRecibo.getPanelPrincipal(), BorderLayout.CENTER);

        tamanyoHorizontal = (int)tamanyoMonitor.getWidth() / 12;
        panelGeneros.getPanelPrincipal().setPreferredSize(new Dimension(tamanyoHorizontal, (int)tamanyoMonitor.getHeight()));
        frame.add(panelGeneros.getPanelPrincipal(), BorderLayout.EAST);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
