/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author alu53788313f
 */
public class SpecialFood extends Food implements ActionListener{

    private Timer timer;
    private Board board;

    public SpecialFood(Board board){
        super();
        this.board = board;
        timer = new Timer(5000, this);
        color = Color.MAGENTA;
        timer.start();
    }
    
 
    @Override
    public void actionPerformed(ActionEvent ae) {
       board.removeSpecialFood();
       timer.stop();
    }

}
