package pl.krypto.graficControllers;

import javax.swing.*;

public class DialogueBoxPopUp {
    public DialogueBoxPopUp() {
    }

    public void messsageBox(String str) {
        JOptionPane.showMessageDialog(null,
                str,
                "Ostrzezenie",
                JOptionPane.WARNING_MESSAGE);
    }
}
