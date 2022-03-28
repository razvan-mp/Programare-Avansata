package compulsory.panels;

import compulsory.mainframe.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * control panel class
 */
public class ControlPanel extends JPanel {
    final MainFrame frame;
    public JButton exitButton = new JButton("Exit");
    JButton loadButton = new JButton("Load");
    JButton saveButton = new JButton("Save");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 4));
        add(loadButton);
        add(saveButton);
        add(exitButton);
        exitButton.addActionListener(this::exitGame);
        loadButton.addActionListener(this::load);
        saveButton.addActionListener(this::save);
    }

    private void save(ActionEvent actionEvent) {
    }

    private void load(ActionEvent actionEvent) {
    }

    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
}
