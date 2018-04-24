/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author alu53788313f
 */
public class Board extends JPanel implements ActionListener {
    
    private int deltaTime;
    private Food food;
    private SpecialFood specialFood;
    private Snake snake;
    private Timer timer;
    public static final int NUM_COLS = 30;
    public static final int NUM_ROWS = 30;
    
    public Board(){
        super();
        initValues();
        
    }
    private void initValues(){
        setFocusable(true);
        
        deltaTime = 0;
        food = null;
        specialFood = null;
        snake = new Snake();
        timer = new Timer(deltaTime, this);
    }
    
   @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.draw(g,squareWidth(),squareHeight());
        //drawBorder(g);
    }
    private int squareWidth() {
        return getWidth() / NUM_COLS;
    }

    private int squareHeight() {
        return getHeight() / NUM_ROWS;
    }
  //board.setBackgroundColor(); Color de fondo

  //bucle del juego
    @Override
    public void actionPerformed(ActionEvent ae) {
        Snake.moveSnake();
    }
}
