package frame;

import panels.ConfigurationPanel;
import panels.ControlPanel;
import panels.DrawingPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    public ConfigurationPanel configPanel;
    private DrawingPanel canvas;

    public MainFrame(String name) {
        super(name);
        init();
    }

    private void init() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        configPanel = new ConfigurationPanel(this);
        ControlPanel controlPanel = new ControlPanel(this);
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
