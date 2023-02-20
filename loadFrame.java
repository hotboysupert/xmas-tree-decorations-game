package menu;

import javax.swing.*;

public class loadFrame extends JFrame {
    loadFrame(){//loading the game
        setSize(600,200);
        setLocationRelativeTo(null);
        add(new textPanel());//adding some loading text
        setTitle(textPanel.title);
        setVisible(true);

    }
}
