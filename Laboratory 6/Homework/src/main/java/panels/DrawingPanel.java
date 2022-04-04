package panels;

import frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class DrawingPanel extends JPanel {
    static int clickCounter = 0;
    private final MainFrame frame;
    int rows, cols;
    int canvasWidth = 400, canvasHeight = 400;
    int boardWidth, boardHeight;
    int cellWidth, cellHeight;
    int paddingX, paddingY;
    int stoneSize = 20;
    Point2D point = new Point2D.Double(0, 0);
    ArrayList<ArrayList<Ellipse2D>> ovals = new ArrayList<>();
    ArrayList<ArrayList<Ellipse2D>> adjacency = new ArrayList<>();
    ArrayList<ArrayList<Color>> colorMatrix = new ArrayList<>();

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
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

        for (int row = 0; row < rows; row++) {
            colorMatrix.add(new ArrayList<>());
            for (int col = 0; col < cols; col++) {
                colorMatrix.get(row).add(Color.LIGHT_GRAY);
            }
        }

        resetColorMatrix();

        adjacency.clear();

        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    }


    protected void resetColorMatrix() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                colorMatrix.get(row).set(col, Color.LIGHT_GRAY);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, canvasWidth, canvasHeight);
        paintGrid(graphics2D, true);
    }

    private void paintGrid(Graphics2D graphics2D, boolean needLines) {
        graphics2D.setColor(Color.DARK_GRAY);

        ovals.clear();
        for (int row = 0; row < rows; row++) {
            ovals.add(new ArrayList<>(rows));
            for (int col = 0; col < cols; col++) {
                int height = paddingX + col * cellWidth;
                int width = paddingY + row * cellHeight;
                var ellipse = new Ellipse2D.Double(height - (double) stoneSize / 2, width - (double) stoneSize / 2, stoneSize, stoneSize);
                ovals.get(row).add(ellipse);
                graphics2D.setColor(colorMatrix.get(row).get(col));
                if (colorMatrix.get(row).get(col).equals(Color.LIGHT_GRAY))
                    graphics2D.draw(ellipse);
                else {
                    graphics2D.draw(ellipse);
                    graphics2D.fill(ellipse);
                }
            }
        }

        if (needLines) {
            graphics2D.setColor(Color.LIGHT_GRAY);
            for (int row = 0; row < rows; row++) {
                graphics2D.drawLine(paddingX, paddingY + row * cellHeight, paddingX + boardWidth, paddingY + row * cellHeight);

                graphics2D.setColor(Color.BLACK);
                graphics2D.setStroke(new BasicStroke(5));

                int randomNumberStart = ThreadLocalRandom.current().nextInt(0, cols - 1);
                int randomStart = randomNumberStart * cellWidth + paddingX;
                int randomNumberFinish = ThreadLocalRandom.current().nextInt(0, cols - 1);
                int randomFinish = randomNumberFinish * cellWidth + paddingX;
                graphics2D.drawLine(randomStart, paddingY + row * cellHeight, randomFinish, paddingY + row * cellHeight);

                graphics2D.setColor(Color.LIGHT_GRAY);
                graphics2D.setStroke(new BasicStroke());
            }

            for (int col = 0; col < cols; col++) {
                graphics2D.setColor(Color.LIGHT_GRAY);
                graphics2D.setStroke(new BasicStroke());
                graphics2D.drawLine(paddingX + col * cellWidth, paddingY, paddingX + col * cellWidth, boardHeight + paddingY);

                graphics2D.setColor(Color.BLACK);
                graphics2D.setStroke(new BasicStroke(5));

                int randomNumberStart = ThreadLocalRandom.current().nextInt(0, rows - 1);
                int randomStart = randomNumberStart * cellHeight + paddingX;
                int randomNumberFinish = ThreadLocalRandom.current().nextInt(0, rows - 1);
                int randomFinish = randomNumberFinish * cellHeight + paddingX;
                graphics2D.drawLine(paddingX + col * cellWidth, randomStart, paddingX + col * cellWidth, randomFinish);

                graphics2D.setColor(Color.LIGHT_GRAY);
                graphics2D.setStroke(new BasicStroke());
            }
            drawSticks(); // o sa umple adjacency
        }

        main(graphics2D);
    }

    public void main(Graphics2D graphics2D) {
 
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                point = event.getPoint();
                drawImage(graphics2D);
            }
        });
    }


    public void drawImage(Graphics2D graphics2D) {
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++) {
                System.out.println(ovals.get(row).get(col).getBounds2D() + " " + row + " " + col);
            }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                var ceva = ovals.get(row).get(col);
                if (ceva.contains(point.getX() - 10, point.getY() - 68)) {
                    if (clickCounter % 2 == 0)
                        colorMatrix.get(row).set(col, Color.RED);
                    else
                        colorMatrix.get(row).set(col, Color.BLUE);
                    clickCounter++;
                    paintGrid(graphics2D, false);
                    frame.repaint();
                }
            }
        }
    }

    private void addCurrentAdj(int row, int col, int index1, int index2) {
        int addAdjacency = ThreadLocalRandom.current().nextInt(0, 2);

        if (addAdjacency == 1)
            adjacency.get(row * cols + col).add(ovals.get(index1).get(index2));
    }

    public void drawSticks() {
        adjacency.clear();
        int addAdjacency, index1, index2;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                adjacency.add(new ArrayList<>(4));

                if (row - 1 >= 0) // up
                    addCurrentAdj(row, col, row - 1, col);

                if (col + 1 < cols)// right
                    addCurrentAdj(row, col, row, col + 1);

                if (row + 1 < rows)// down
                    addCurrentAdj(row, col, row + 1, col);

                if (col - 1 >= 0)// left
                    addCurrentAdj(row, col, row, col - 1);
                index1 = row + 1;
                index2 = col;

                index1 = row + 1; // bottom right
                index2 = col + 1;
            }
        }

    }
}