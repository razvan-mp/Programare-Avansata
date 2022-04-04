package panels;

import frame.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
        saveButton.addActionListener(this::saveCurrentCanvas);

        add(saveButton);
        add(loadButton);
        add(exitBtn);
    }

    private void saveCurrentCanvas(ActionEvent actionEvent){
        Container c = frame.getContentPane();
        BufferedImage im = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
        c.paint(im.getGraphics());
        try {
            ImageIO.write(im, "PNG", new File("shot.png"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
}
