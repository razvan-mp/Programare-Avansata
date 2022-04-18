package app;

import frame.MainFrame;

public record Game(String name) {
    public void start() {
        new MainFrame(this.name).setVisible(true);
    }
}
