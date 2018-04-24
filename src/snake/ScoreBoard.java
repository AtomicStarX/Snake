/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import javax.swing.JLabel;

/**
 *
 * @author alu53788313f
 */
public class ScoreBoard extends JLabel implements IncrementScore {
    
    public static int score;
    
    public ScoreBoard() {
        super();
        score = 0;
        this.setText("Score : " + score);
    }
    
    public void increment(int points) {
        score += points;
        this.setText("Score : " + score);
    }
    
    public void reset() {
        score = 0;
        this.setText("Score : " + score);
    }
    
    public static int getScore(){
        return score;
    }
}
