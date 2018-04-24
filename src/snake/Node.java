/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;

/**
 *
 * @author alu53788313f
 */
public class Node {
    
    public int row;
    public int col;
    public Color color;
    
    public Node(int row , int col , Color color){
        
        this.row = row;
        this.col = col;
        this.color=color;
        
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Color getColor() {
        return color;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    
}
