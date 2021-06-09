package utilidades;

import javax.swing.*;
import java.awt.*;

public class Impresora {
    /**
     * Imprime el ticket.
     * @param informacion String con la información.
     */
    public static void imprimirTicket(String informacion){
        JTextPane jtp = new JTextPane();
        jtp.setBackground(Color.white);
        jtp.setFont(new Font("Courier New",Font.BOLD,7));
        jtp.setText(informacion);
        boolean show = true;
        try {
            jtp.print(null, null, show, null, null, show);
        } catch (java.awt.print.PrinterException ex) {
            ex.printStackTrace();
        }
    }

}
