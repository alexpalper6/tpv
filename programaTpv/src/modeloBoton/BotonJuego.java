package modeloBoton;

import modeloJuego.Generos;
import modeloJuego.Juego;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Objects;

public class BotonJuego {
    private final JButton boton;
    private final Juego juego;

    public BotonJuego(Juego juego) {
        this.juego = juego;
        this.boton = new JButton(juego.getImagen());
        boton.setBorder(new EmptyBorder(1, 2, 1, 2));
        boton.addActionListener(e-> {
            juego.getNombre();
        });


    }

    public JButton getBoton() {
        return boton;
    }

    public Juego getJuego() {
        return juego;
    }

    public static void main(String[] args) {
        Juego juego = new Juego("Animal Crossing New Horizons", 6999, Generos.FPS);

        BotonJuego button = new BotonJuego(juego);
        //TODO: PARA QUITAR EL BORDE
        // https://www.decodejava.com/java-jbutton.htm
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.add(button.getBoton());
        frame.add(panel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();

        frame.setVisible(true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BotonJuego that = (BotonJuego) o;
        return juego.equals(that.juego);
    }

    @Override
    public int hashCode() {
        return Objects.hash(juego);
    }
}
