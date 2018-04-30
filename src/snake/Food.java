/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author alu53788313f
 */
public class Food {
    
    private int row;
    private int col;
    protected Color color; 
    
    public Food(){
        row = getRandomRow();
        col = getRandomCol();
        color = Color.WHITE;
    }

    public void draw(Graphics g, int squareWidth, int squareHeight) {
        Util.drawSquare(g, row, col, color, squareWidth, squareHeight);
    }
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    public Color getColor(){
        return color;
    }
    
    public void setColor(Color color){
        this.color = color;
    }

    public int getRandomRow() {
        int randomRow = (int) (Math.random() * (Board.NUM_ROWS-1) +1);
        return randomRow;
    }

    public int getRandomCol() {
        int randomCol = (int) (Math.random() * (Board.NUM_ROWS-1) +1);
        return randomCol;
    }
    public static Food createANewFood(Snake snake){
        Food food = new Food();
        if(snake.checkColisionWithSnake(food.getRow(), food.getCol())){
            return createANewFood(snake);
        }
        return food;
    }
    
}


