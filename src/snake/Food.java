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
    
    private Node food;
    private static int row;
    private static int col;
    
    public Food(){
        row = getRandomRow();
        col = getRandomCol();
        food = new Node(row,col,Color.WHITE);
    }

    public void draw(Graphics g, int squareWidth, int squareHeight) {
        Util.drawSquare(g, food.getRow(), food.getCol(), food.getColor(), squareWidth, squareHeight);
    }
    public static int getRow() {
        return row;
    }

    public static int getCol() {
        return col;
    }

    public static int getRandomRow() {
        int randomRow = (int) (Math.random() * (Board.NUM_ROWS-1) +1);
        return randomRow;
    }

    public static int getRandomCol() {
        int randomCol = (int) (Math.random() * (Board.NUM_ROWS-1) +1);
        return randomCol;
    }
}


