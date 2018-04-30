/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author alu53788313f
 */
public class Board extends JPanel implements ActionListener {

    public static final int NUM_COLS = 30;
    public static final int NUM_ROWS = 30;

    private int deltaTime;
    private int foodCounter;

    private Food food;
    private SpecialFood specialFood;
    private Snake snake;
    private Timer timer;
    private IncrementScore scoreDelegete;

    private boolean turning;

    MyKeyAdapter keyAdepter;

    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (!turning) {
                turning = true;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (snake.getDirection() != DirectionType.RIGHT) {
                            snake.setDirectionType(DirectionType.LEFT);
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (snake.getDirection() != DirectionType.LEFT) {
                            snake.setDirectionType(DirectionType.RIGHT);
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (snake.getDirection() != DirectionType.DOWN) {
                            snake.setDirectionType(DirectionType.UP);
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (snake.getDirection() != DirectionType.UP) {
                            snake.setDirectionType(DirectionType.DOWN);
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        break;
                    case KeyEvent.VK_ENTER:
                        break;
                    default:
                        break;

                }
                repaint();
            }
        }
    }

    public Board() {
        super();
        keyAdepter = new MyKeyAdapter();
        initValues();

    }

    void initGame() {
        initValues();
        removeKeyListener(keyAdepter);
        addKeyListener(keyAdepter);
        timer.start();
        scoreDelegete.reset();
    }

    private void initValues() {
        setFocusable(true);

        foodCounter = 0;
        deltaTime = 100;
        food = new Food();
        specialFood = null;
        snake = new Snake();
        timer = new Timer(deltaTime, this);
        turning = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        food.draw(g, squareWidth(), squareHeight());
        if (specialFood != null) {
            specialFood.draw(g, squareWidth(), squareHeight());
        }
        snake.draw(g, squareWidth(), squareHeight());
        drawBorder(g);
    }

    private int squareWidth() {
        return getWidth() / NUM_COLS;
    }

    private int squareHeight() {
        return getHeight() / NUM_ROWS;
    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() / NUM_COLS * NUM_COLS, getHeight() / NUM_ROWS * NUM_ROWS);
    }

    //board.setBackgroundColor(); Color de fondo
    public void setScore(IncrementScore scorer) {
        this.scoreDelegete = scorer;
    }

    //bucle del juego
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!checkColision()) {
            if (snake.canEat(food)) {
                foodCounter++;
                snake.moveSnake(true);
                food = Food.createANewFood(snake);
                scoreDelegete.increment(100);
                if ((foodCounter % 3) == 0) {
                    specialFood = new SpecialFood(this);
                }
            } else {
                if (specialFood != null && snake.canEat(specialFood)) {
                    scoreDelegete.increment(200);
                    removeSpecialFood();
                } else {
                    snake.moveSnake(false);
                }
            }
            turning = false;
            repaint();
            Toolkit.getDefaultToolkit().sync();
        }
        gameOver();

    }

    public void removeSpecialFood() {
        specialFood = null;
    }

    private boolean checkColision() {
        int nextRow = snake.getSnakeHead().getRow();
        int nextCol = snake.getSnakeHead().getCol();
        switch (snake.getDirection()) {
            case DOWN:
                nextRow++;
                if (nextRow >= NUM_ROWS) {
                    return true;
                }
                break;
            case UP:
                nextRow--;
                if (nextRow < 0) {

                    return true;
                }
                break;
            case RIGHT:
                nextCol++;
                if (nextCol >= NUM_COLS) {

                    return true;
                }
                break;
            case LEFT:
                nextCol--;
                if (nextCol < 0) {

                    return true;
                }
                break;
        }
        if (snake.checkColisionWithSnake(nextRow, nextCol)) {
            return true;
        }
        return false;
    }

    private void gameOver() {
        if (checkColision()) {
            timer.stop();
        }

    }
}
