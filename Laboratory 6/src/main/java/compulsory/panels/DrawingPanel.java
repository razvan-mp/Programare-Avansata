package compulsory.panels;

import compulsory.mainframe.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * drawing panel class
 */
public class DrawingPanel extends JPanel {
    private final MainFrame frame;
    int rows, cols;
    int canvasWidth = 400, canvasHeight = 400;
    int boardWidth, boardHeight;
    int cellWidth, cellHeight;
    int paddingX, paddingY;
    int stoneSize = 20;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        init(frame.configPanel.getRows(), frame.configPanel.getCols());
    }


    final void init(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.paddingX = stoneSize + 10;
        this.paddingY = stoneSize + 10;
        this.cellWidth = (canvasWidth - 2 * paddingX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * paddingY) / (rows - 1);
        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);
        paintGrid(g);
    }

    private void paintGrid(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        for (int row = 0; row < rows; row++) {
            int x1 = paddingX;
            int y1 = paddingY + row * cellHeight;
            int x2 = paddingX + boardWidth;
            g.drawLine(x1, y1, x2, y1);
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = paddingX + col * cellWidth;
                int y = paddingY + row * cellHeight;
                g.setColor(Color.LIGHT_GRAY);
                g.drawOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
            }
        }
    }
}
