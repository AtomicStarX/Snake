/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author alu53788313f
 */
public class Snake {

    static void moveSnake() {
        Node firstNode = listNodes.get(0);
        switch (direction){
                case DOWN:
                    firstNode.setRow(firstNode.row +1 );
                    break;
                    
                case UP:
                    firstNode.setRow(firstNode.row - 1 );
                    break;
                    
                case RIGHT:
                    firstNode.setRow(firstNode.col + 1 );
                    break;
                    
                case LEFT:
                    firstNode.setRow(firstNode.col - 1 );
                    break;
        }
    }

    private static ArrayList<Node> listNodes;
    private static DirectionType direction;

    public Snake() {
        listNodes = new ArrayList(2);
        direction = DirectionType.NO_DIRECTION;
        Node node1 = new Node(Board.NUM_ROWS / 2, Board.NUM_COLS / 2, Color.BLACK);
        Node node2 = new Node(Board.NUM_ROWS / 2, Board.NUM_COLS / 2 - 1, Color.GREEN);
        listNodes.add(node1);
        listNodes.add(node2);
        
    }

    public void draw(Graphics g, int squareWidth, int squareHeight) {
        for (Node node : listNodes) {
            Util.drawSquare(g, node.row, node.col, node.color, squareWidth, squareHeight);
        }
    }
    
    public DirectionType getDirectionType(){
        return direction;
    }
}
