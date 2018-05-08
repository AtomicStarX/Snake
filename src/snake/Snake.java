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
        Color oldHeadColor = head.getColor();
        Node firstNode;
        if (!Game.rainbowColor) {
            firstNode = new Node(listNodes.get(0).getRow(), listNodes.get(0).getCol(), listNodes.get(0).getColor());
        } else {
            firstNode = new Node(listNodes.get(0).getRow(), listNodes.get(0).getCol(), listNodes.get(0).randomColor(head.getColor()));
        }
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
        if (!Game.rainbowColor) {
            head.setColor(getBodyColor().brighter());
        }else{
            head.setColor(oldHeadColor);
        }
        if (!grow) {
            listNodes.remove(listNodes.size() - 1);
        }
    }

    private ArrayList<Node> listNodes;
    private DirectionType direction;
    private Color bodyColor;
    private Color headColor;
    private Node node1;
    private Node node2;

    public Snake() {
        bodyColor = Color.GREEN;
        headColor = Color.RED;
        listNodes = new ArrayList(2);
        direction = DirectionType.RIGHT;
        node1 = new Node(Board.NUM_ROWS / 2, Board.NUM_COLS / 2, headColor);
        node2 = new Node(Board.NUM_ROWS / 2, Board.NUM_COLS / 2 - 1, bodyColor);
        listNodes.add(node1);
        listNodes.add(node2);
    }

    public void draw(Graphics g, int squareWidth, int squareHeight) {
        for (Node node : listNodes) {
            Util.drawSquare(g, node.getRow(), node.getCol(), node.getColor(), squareWidth, squareHeight);
        }
    }

    public Color getBodyColor() {
        return bodyColor;
    }

    public Color getHeadColor() {
        return headColor;
    }

    public void setBodyColor(Color c) {
        this.bodyColor = c;
    }

    public void setHeadColor(Color c) {
        this.headColor = c;
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

    public void changeColorSnakeBody(Color color) {
        int counter = 0;
        for (Node node : listNodes) {
            if (counter != 0) {
                node.setColor(color);
            }
            counter++;
        }
        setBodyColor(color);
    }

    public void changeColorSnakeHead(Color color) {
        listNodes.get(0).setColor(color);
        setHeadColor(color);
    }
    
}
