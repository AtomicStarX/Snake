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
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author alu53788313f
 */
public class Board extends JPanel implements ActionListener {

    private JFrame parentFrame;

    public static final int NUM_COLS = 60;
    public static final int NUM_ROWS = 60;

    private int deltaTime;
    private int foodCounter;
    private int specialFoodDifficulty;

    private Food food;
    private SpecialFood specialFood;
    private Snake snake;
    private Timer timer;
    private IncrementScore scoreDelegete;

    private boolean gamePaused;
    private boolean turning;
    public boolean easyMode;
    public boolean mediumMode;
    public boolean hardMode;
    //public boolean specialMode;

    MyKeyAdapter keyAdepter;

    class MyKeyAdapter extends KeyAdapter {

        //keyBoard Methods
        @Override
        public void keyPressed(KeyEvent e) {

            if (!turning) {

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (snake.getDirection() != DirectionType.RIGHT) {
                            snake.setDirectionType(DirectionType.LEFT);
                            turning = true;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (snake.getDirection() != DirectionType.LEFT) {
                            snake.setDirectionType(DirectionType.RIGHT);
                            turning = true;
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (snake.getDirection() != DirectionType.DOWN) {
                            snake.setDirectionType(DirectionType.UP);
                            turning = true;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (snake.getDirection() != DirectionType.UP) {
                            snake.setDirectionType(DirectionType.DOWN);
                            turning = true;
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        if (!gamePaused) {
                            gamePaused = true;
                            timer.stop();
                        } else {
                            gamePaused = false;
                            timer.start();
                        }
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

//Constructor
    public Board() {
        super();
        keyAdepter = new MyKeyAdapter();
        initValues();

    }

//Methods to initialize the game 
    void initGame() {
        initValues();
        removeKeyListener(keyAdepter);
        addKeyListener(keyAdepter);
        timer.start();
        scoreDelegete.reset();
    }

    private void initValues() {
        setFocusable(true);
        initDifficultyValues();

        foodCounter = 0;
        food = new Food();
        specialFood = null;
        snake = new Snake();
        timer = new Timer(deltaTime, this);
        turning = false;
        gamePaused = false;

    }

    private void initDifficultyValues() {
        if (easyMode) {
            deltaTime = 500;
            specialFoodDifficulty = 3;
        }
        if (mediumMode) {
            deltaTime = 300;
            specialFoodDifficulty = 5;
        }
        if (hardMode) {
            deltaTime = 100;
            specialFoodDifficulty = 10;
        }
    }

//Paint Methods
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

    private void drawBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() / NUM_COLS * NUM_COLS, getHeight() / NUM_ROWS * NUM_ROWS);
    }

    //board.setBackgroundColor(); Color de fondo
    public void setScore(IncrementScore scorer) {
        this.scoreDelegete = scorer;
    }

    public void changeColorSnake(Color color, boolean b) {

        if (b) {
            snake.changeColorSnakeHead(color);
        }
        if (!b) {
            snake.changeColorSnakeBody(color);
        }
        Game.rainbowColor = false;

    }

    public void randomBackgroundColor() {

        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);

        Color newColorRGB = new Color(r, g, b);

        if (!Game.rainbowColor) {
            if (newColorRGB.equals(snake.getBodyColor()) || newColorRGB.equals(snake.getHeadColor()) || newColorRGB.equals(food.getColor())) {
                randomBackgroundColor();
            }
        }

        setBackground(newColorRGB);
    }
//Game's Loop

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!checkColision()) {
            if(Game.epilepsiaMode){
                randomBackgroundColor();
            }
            if (snake.canEat(food)) {
                foodCounter++;
                snake.moveSnake(true);
                food = Food.createANewFood(snake);
                scoreDelegete.increment(100);
                if (Game.rainbowBackgroundColor) {
                        randomBackgroundColor();
                    }
                if ((foodCounter % specialFoodDifficulty) == 0) {
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
            if (!checkColision()) {
                repaint();
            }

            Toolkit.getDefaultToolkit().sync();
        }
        gameOver();

    }

//Methods to check colision
    private boolean checkColision() {
        int nextRow = snake.getSnakeHead().getRow();
        int nextCol = snake.getSnakeHead().getCol();
        switch (snake.getDirection()) {
            case DOWN:
                nextRow++;
                if (nextRow >= NUM_ROWS + 1) {
                    return true;
                }
                break;
            case UP:
                nextRow--;
                if (nextRow < -1) {

                    return true;
                }
                break;
            case RIGHT:
                nextCol++;
                if (nextCol >= NUM_COLS + 1) {

                    return true;
                }
                break;
            case LEFT:
                nextCol--;
                if (nextCol < -1) {
                    return true;
                }
                break;
        }
        if (snake.checkColisionWithSnake(nextRow, nextCol)) {
            return true;
        }
        return false;
    }

    public void removeSpecialFood() {
        specialFood = null;
    }

//GameOver Method
    private void gameOver() {
        if (checkColision()) {
            removeKeyListener(keyAdepter);
            timer.stop();
            if (Game.gameStarted) {
                int score = ScoreBoard.getScore();
                RecordsDialog d = new RecordsDialog(parentFrame, true, score, this);
                d.setVisible(true);
            }
            Game.gameStarted = false;
        }

    }

//Getters And Setters Methods
    public void setParentFrame(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    public Timer getTimer() {
        return timer;
    }

    public boolean getEasyMode() {
        return easyMode;
    }

    public boolean getMediumMode() {
        return mediumMode;
    }

    public boolean getHardMode() {
        return hardMode;
    }

//SqaureWidth and SquareHeight Methods
    private int squareWidth() {
        return getWidth() / NUM_COLS;
    }

    private int squareHeight() {
        return getHeight() / NUM_ROWS;
    }

}
