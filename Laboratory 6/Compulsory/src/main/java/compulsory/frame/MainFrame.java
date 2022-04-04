package compulsory.frame;

import compulsory.panels.ConfigurationPanel;
import compulsory.panels.ControlPanel;
import compulsory.panels.DrawingPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Main class for game window, initialization with components, and framing
 */
public class MainFrame extends JFrame {
    public ConfigurationPanel configPanel;
    private ControlPanel controlPanel;
    private DrawingPanel canvas;

    public MainFrame(String name) {
        super(name);
        init();
    }

    /**
     * set default close operation as do nothing so that user has to click
     * exit button
     */
    private void init() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        configPanel = new ConfigurationPanel(this);
        controlPanel = new ControlPanel(this);
        canvas = new DrawingPanel(this);

        add(configPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);
        add(canvas, BorderLayout.CENTER);

        pack();
    }

    public DrawingPanel getCanvas() {
        return canvas;
    }
}
