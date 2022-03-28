package compulsory.panels;

import compulsory.mainframe.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * configuration panel class
 */
public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label;
    JSpinner lineSpinner;
    JSpinner columnSpinner;
    JButton create;
    int rows = 10, cols = 10;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        label = new JLabel("Grid size: ");
        lineSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        columnSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        create = new JButton("Create");
        create.addActionListener(this::create);
        add(label);
        add(new JLabel("rows: "));
        add(lineSpinner);
        add(new JLabel("columns: "));
        add(columnSpinner);
        add(create);
    }

    private void create(ActionEvent actionEvent) {
        frame.getCanvas().init(this.getRows(), this.getCols());
    }

    public int getRows() {
        return (int) lineSpinner.getValue();
    }

    public int getCols() {
        return (int) columnSpinner.getValue();
    }
}
