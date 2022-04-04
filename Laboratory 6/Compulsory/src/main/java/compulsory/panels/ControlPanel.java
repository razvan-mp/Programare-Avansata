package compulsory.panels;

import compulsory.frame.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * initalizes button panel with Load, Save and Exit buttons
 * and creates them accordingly
 */
public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    JButton loadButton = new JButton("Load");
    JButton saveButton = new JButton("Save");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 4));
        exitBtn.addActionListener(this::exitGame);

        add(saveButton);
        add(loadButton);
        add(exitBtn);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
}
