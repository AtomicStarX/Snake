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

    public void moveSnake(boolean grow) {
        Node head = listNodes.get(0);
        Node firstNode = new Node(listNodes.get(0).getRow(), listNodes.get(0).getCol(), listNodes.get(0).getColor());
        switch (direction) {
            case DOWN:
                firstNode.setRow(firstNode.getRow() + 1);
                break;

            case UP:
                firstNode.setRow(firstNode.getRow() - 1);
                break;

            case RIGHT:
                firstNode.setCol(firstNode.getCol() + 1);
                break;

            case LEFT:
                firstNode.setCol(firstNode.getCol() - 1);
                break;
        }
        listNodes.add(0, firstNode);
        head.setColor(Color.GREEN);
        if (! grow) {
            listNodes.remove(listNodes.size() - 1);
        }
    }

    private  ArrayList<Node> listNodes;
    private  DirectionType direction;

    public Snake() {
        listNodes = new ArrayList(2);
        direction = DirectionType.RIGHT;
        Node node1 = new Node(Board.NUM_ROWS / 2, Board.NUM_COLS / 2, Color.RED);
        Node node2 = new Node(Board.NUM_ROWS / 2, Board.NUM_COLS / 2 - 1, Color.GREEN);
        listNodes.add(node1);
        listNodes.add(node2);

    }

    public void draw(Graphics g, int squareWidth, int squareHeight) {
        for (Node node : listNodes) {
            Util.drawSquare(g, node.getRow(), node.getCol(), node.getColor(), squareWidth, squareHeight);
        }
    }

    public DirectionType getDirection() {
        return direction;
    }

    public void setDirectionType(DirectionType direction) {
        this.direction = direction;
    }

    public boolean canEat(Food food) {
        Node firstNode = getSnakeHead();
            if (firstNode.getRow() == food.getRow() && firstNode.getCol() == food.getCol()) {
            return true;
        } else {
            return false;
        }
    }

    public Node getSnakeHead() {
        return listNodes.get(0);
    }

    public boolean checkColisionWithSnake(int row, int col) {
        for (Node nodes : listNodes) {
            if (row == nodes.getRow() & col == nodes.getCol()) {
                return true;
            }
        }
        return false;
    }
}
