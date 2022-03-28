import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitButton = new JButton("Exit");
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
