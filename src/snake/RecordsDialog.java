package snake;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alu53788313f
 */
public class RecordsDialog extends javax.swing.JDialog {

    private class Record {

        public int record;
        public String name;

        public Record(int record, String name) {
            this.record = record;
            this.name = name;
        }
    }

    //private static final String RECORD_FILE_NAME = "records.txt";
    private static final String RECORD_FILE_NAME_SNAKE_EASY = "recordsSnakeEasy.txt";
    private static final String RECORD_FILE_NAME_SNAKE_MEDIUM = "recordsSnakeMedium.txt";
    private static final String RECORD_FILE_NAME_SNAKE_HARD = "recordsSnakeHard.txt";
    private int score;
    private JLabel[] recordLabels;
    private int minRecord;
    private ArrayList<Record> listOfRecords;
    private Board board;

    /**
     * Creates new form RecordsDialog
     */
    public RecordsDialog(java.awt.Frame parent, boolean modal, int score, Board board) {
        super(parent, modal);
        initComponents();
        initRecordLabels();
        this.score = score;
        minRecord = 0;
        listOfRecords = new ArrayList<Record>();
        this.board = board;
        try {
            readRecords();
        } catch (IOException ex) {

        }
        processRecord();

    }

    private void processRecord() {
        if (board.getEasyMode()) {
            jLabelTopScoresDifficulty.setText("TOP SCORES EASY MODE !!!");
        }
        if (board.getMediumMode()) {
            jLabelTopScoresDifficulty.setText("TOP SCORES MEDIUM MODE !!!");
        }
        if (board.getHardMode()) {
            jLabelTopScoresDifficulty.setText("TOP SCORES HARD MODE !!!");
        }

        JLabelCurrentScore.setText("Your score: " + score);

        if (score <= minRecord && listOfRecords.size() == 5 ) {
            jLabelName.setVisible(false);
            jTextFieldName.setVisible(false);
        }
    }

    private void readRecords() throws FileNotFoundException, IOException {
        BufferedReader input = null;
        try {
            if (board.getEasyMode()) {
                input = new BufferedReader(new FileReader(RECORD_FILE_NAME_SNAKE_EASY));
            }
            if (board.getMediumMode()) {
                input = new BufferedReader(new FileReader(RECORD_FILE_NAME_SNAKE_MEDIUM));
            }
            if (board.getHardMode()) {
                input = new BufferedReader(new FileReader(RECORD_FILE_NAME_SNAKE_HARD));
            }

            int lineCount = 0;
            String line;
            String[] lineRecords = null;
            while (((line = input.readLine()) != null) && (lineCount < 5)) {
                lineRecords = line.split(",");
                recordLabels[lineCount].setText(lineRecords[0] + ": " + lineRecords[1]);
                lineCount++;
                listOfRecords.add(new Record(Integer.parseInt(lineRecords[0]), lineRecords[1]));
            }
            if (lineCount >= 5) {
                try {
                    minRecord = Integer.parseInt(lineRecords[0]);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }

        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelName = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        JLabelCurrentScore = new javax.swing.JLabel();
        jLabelRecord1 = new javax.swing.JLabel();
        jLabelRecord2 = new javax.swing.JLabel();
        jLabelRecord3 = new javax.swing.JLabel();
        jLabelRecord4 = new javax.swing.JLabel();
        jLabelRecord5 = new javax.swing.JLabel();
        jButtonOK = new javax.swing.JButton();
        jLabelTopScoresDifficulty = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabelName.setText("Name");

        jTextFieldName.setText("Write Your Name Or Nickname");
        jTextFieldName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNameActionPerformed(evt);
            }
        });

        JLabelCurrentScore.setText("jLabel2");

        jLabelRecord1.setText("0. NoName");

        jLabelRecord2.setText("0. Noname");

        jLabelRecord3.setText("0. NoName");

        jLabelRecord4.setText("0. NoName");

        jLabelRecord5.setText("0. NoName");

        jButtonOK.setText("OK");
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        jLabelTopScoresDifficulty.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonOK)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTopScoresDifficulty)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JLabelCurrentScore, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                        .addGap(97, 97, 97))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addComponent(jLabelRecord5, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                    .addComponent(jLabelRecord1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelRecord2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelRecord3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelRecord4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabelTopScoresDifficulty)
                .addGap(18, 18, 18)
                .addComponent(JLabelCurrentScore)
                .addGap(18, 18, 18)
                .addComponent(jLabelRecord1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelRecord2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelRecord3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelRecord4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelRecord5)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelName)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonOK)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNameActionPerformed

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        if (score >= minRecord) {
            try {
                saveRecord();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        dispose();
    }//GEN-LAST:event_jButtonOKActionPerformed

    private void saveRecord() throws IOException {
        PrintWriter output = null;
        int lineCounter = 0;
        boolean alreadyWrittenScore = false;
        try {
            if (board.getEasyMode()) {
                output = new PrintWriter(new FileWriter(RECORD_FILE_NAME_SNAKE_EASY));
            }
            if (board.getMediumMode()) {
                output = new PrintWriter(new FileWriter(RECORD_FILE_NAME_SNAKE_MEDIUM));
            }
            if (board.getHardMode()) {
                output = new PrintWriter(new FileWriter(RECORD_FILE_NAME_SNAKE_HARD));
            }
            for (Record record : listOfRecords) {
                if (score > record.record && !alreadyWrittenScore) {
                    output.println(score + ", " + jTextFieldName.getText());
                    alreadyWrittenScore = true;
                    lineCounter++;
                }
                if (lineCounter < 5) {
                    output.println(record.record + "," + record.name);
                    lineCounter++;
                }
            }
            if (!alreadyWrittenScore && lineCounter < 5) {
                output.println(score + ", " + jTextFieldName.getText());
            }
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }

    public int getScore() {
        return score;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabelCurrentScore;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelRecord1;
    private javax.swing.JLabel jLabelRecord2;
    private javax.swing.JLabel jLabelRecord3;
    private javax.swing.JLabel jLabelRecord4;
    private javax.swing.JLabel jLabelRecord5;
    private javax.swing.JLabel jLabelTopScoresDifficulty;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables

    private void initRecordLabels() {
        recordLabels = new JLabel[5];
        recordLabels[0] = jLabelRecord1;
        recordLabels[1] = jLabelRecord2;
        recordLabels[2] = jLabelRecord3;
        recordLabels[3] = jLabelRecord4;
        recordLabels[4] = jLabelRecord5;

    }

}
