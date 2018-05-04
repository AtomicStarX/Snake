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

    private int row;
    private int col;
    private Color color;
    private Color[] arrColors;

    public Node(int row, int col, Color color) {

        this.row = row;
        this.col = col;
        this.color = color;
        arrColors = new Color[]{Color.MAGENTA, Color.PINK, Color.ORANGE, Color.YELLOW, Color.LIGHT_GRAY, Color.GREEN, Color.RED, Color.BLUE, Color.BLACK};

    }

    public void randomColor() {
        int randomNumber = (int) (Math.random() * arrColors.length);
        switch (randomNumber) {
            case 0:
                color = arrColors[0];
                break;
            case 1:
                color = arrColors[1];
                break;
            case 2:
                color = arrColors[2];
                break;
            case 3:
                color = arrColors[3];
                break;
            case 4:
                color = arrColors[4];
                break;
            case 5:
                color = arrColors[5];
                break;
            case 6:
                color = arrColors[6];
                break;
            case 7:
                color = arrColors[7];
                break;
            case 8:
                color = arrColors[8];
                break;
            default:
                color = Color.GREEN;
                break;

        }
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
