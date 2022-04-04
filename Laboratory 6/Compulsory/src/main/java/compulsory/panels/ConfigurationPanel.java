package compulsory.panels;

import compulsory.frame.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Class for a JPanel that contains the configuration
 * elements of the game (buttons (Save, Load, Exit))
 */
public class ConfigurationPanel extends JPanel {
    final MainFrame frame;
    JLabel label;
    JSpinner widthSpinner;
    JSpinner heightSpinner;
    JButton createGameButton;

    public ConfigurationPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    public JSpinner getWidthSpinner() {
        return widthSpinner;
    }

    public JSpinner getHeightSpinner() {
        return heightSpinner;
    }

    /**
     * initialize the spinners with default values and create panel
     * that lets the user adjust things
     */
    private void init() {
        label = new JLabel("Grid size:");
        widthSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        heightSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        createGameButton = new JButton("Create game");
        createGameButton.addActionListener(this::createGame);

        add(label);
        add(widthSpinner);
        add(heightSpinner);
        add(createGameButton);
    }

    /**
     * Gets spinner values and adjusts window size and dots accordingly
     */
    private void createGame(ActionEvent actionEvent) {
        System.out.println("Game created.");

        int newColNumber = (int) widthSpinner.getValue();
        int newRowNumber = (int) heightSpinner.getValue();

        frame.getCanvas().init(newColNumber, newRowNumber);
        frame.repaint();

        frame.pack();
    }
}

