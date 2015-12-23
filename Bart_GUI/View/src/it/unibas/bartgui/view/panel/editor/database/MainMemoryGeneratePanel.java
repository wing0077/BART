/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unibas.bartgui.view.panel.editor.database;

import javax.swing.JTextArea;
import org.openide.awt.UndoRedo;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 */
public class MainMemoryGeneratePanel extends javax.swing.JPanel {

    private JTextArea plainInstanceArea;
    //private final UndoRedo.Manager UndoRedomanager = new UndoRedo.Manager();
    /**
     * Creates new form MainMemoryGeneratePanel
     */
    public MainMemoryGeneratePanel() {
        initComponents();
        initPlainInstanceArea();
    }

    private void initPlainInstanceArea()   {
        plainInstanceArea = new JTextArea();
        //plainInstanceArea.getDocument().addUndoableEditListener(UndoRedomanager);
        getPlainInstanceArea().setLineWrap(false);
        jScrollPane1.setViewportView(getPlainInstanceArea());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the plainInstanceArea
     */
    public JTextArea getPlainInstanceArea() {
        return plainInstanceArea;
    }
}