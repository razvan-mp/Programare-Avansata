package compulsory.mainframe;

import compulsory.panels.ConfigPanel;
import compulsory.panels.ControlPanel;
import compulsory.panels.DrawingPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    private void init() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        configPanel = new ConfigPanel(this);

        add(configPanel, BorderLayout.NORTH);

        canvas = new DrawingPanel(this);

        controlPanel = new ControlPanel(this);

        add(canvas, BorderLayout.CENTER);
        add(controlPanel.exitButton, BorderLayout.SOUTH);

    }

    public DrawingPanel getCanvas() {
        return canvas;
    }
}
