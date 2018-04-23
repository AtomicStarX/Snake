/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author alu53788313f
 */
public class Board extends JPanel  {
    
    private int deltaTime;
    private Food food;
    private SpecialFood specialFood;
    private Snake snake;
    private int timer;
    
    public Board(){
        initComponents();
        
    }
    private void initComponents(){
        deltaTime = 0;
        food = null;
        specialFood = null;
        snake = null;
        timer = 0;
    }
        
}
