package compulsory.panels;

import compulsory.frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * contains all drawing instructions for the game components
 */
public class DrawingPanel extends JPanel {
    int rows, cols;
    int canvasWidth = 400, canvasHeight = 400;
    int boardWidth, boardHeight;
    int cellWidth, cellHeight;
    int paddingX, paddingY;
    int stoneSize = 20;
    ArrayList<ArrayList<Ellipse2D>> ellipseList = new ArrayList<>();

    public DrawingPanel(MainFrame frame) {
        init((int) frame.configPanel.getHeightSpinner().getValue(), (int) frame.configPanel.getWidthSpinner().getValue());
    }

    final void init(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.paddingX = stoneSize;
        this.paddingY = stoneSize;
        this.canvasHeight = 40 * rows;
        this.canvasWidth = 40 * cols;
        this.cellWidth = (canvasWidth - 2 * paddingX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * paddingY) / (rows - 1);
        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;

        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, canvasWidth, canvasHeight);
        paintGrid(graphics2D);
    }

    private void paintGrid(Graphics2D graphics2D) {
        graphics2D.setColor(Color.DARK_GRAY);

        ellipseList.clear();
        for (int row = 0; row < rows; row++) {
            ellipseList.add(new ArrayList<>(rows));
            for (int col = 0; col < cols; col++) {
                int height = paddingX + col * cellWidth;
                int width = paddingY + row * cellHeight;
                var ellipse = new Ellipse2D.Double(height - (double) stoneSize / 2, width - (double) stoneSize / 2, stoneSize, stoneSize);
                ellipseList.get(row).add(ellipse);
                graphics2D.setColor(Color.LIGHT_GRAY);
                graphics2D.draw(ellipse);
            }
        }
        graphics2D.setColor(Color.LIGHT_GRAY);
        for (int row = 0; row < rows; row++)
            graphics2D.drawLine(paddingX, paddingY + row * cellHeight, paddingX + boardWidth, paddingY + row * cellHeight);

        for (int col = 0; col < cols; col++)
            graphics2D.drawLine(paddingX + col * cellWidth, paddingY, paddingX + col * cellWidth, boardHeight + paddingY);
    }
}