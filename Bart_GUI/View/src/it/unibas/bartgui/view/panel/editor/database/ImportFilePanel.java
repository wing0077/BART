/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unibas.bartgui.view.panel.editor.database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.openide.filesystems.FileChooserBuilder;

/**
 *
 * @author Musicrizz
 */
public class ImportFilePanel extends javax.swing.JPanel {

    /**
     * Creates new form ImportFilePanel
     */
    
    private JButton okButton;
    private JButton cancelButton;
    private ButtonGroup bg;
    private FileChooserBuilder fcb;
    private FileFilter filterXML;
    private FileFilter filterCSV;
    
    public ImportFilePanel() {
        initComponents();
        init();
    }
    
    private void init()   {
        okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        
        bg = new ButtonGroup();
        bg.add(xmlRadioButton);
        bg.add(csvRadioButton);
        
        fcb = new FileChooserBuilder("Import_FILE")
                                        .setTitle("Import File")
                                        .setDefaultWorkingDirectory(new File(System.getProperty("user.home")))
                                        .setApproveText("Import")
                                        .setFilesOnly(true)
                                        .setAcceptAllFileFilterUsed(false);
        filterXML = new FileNameExtensionFilter("XML file", "xml","XML");
        filterCSV = new FileNameExtensionFilter("CSV file", "csv","CSV");
        
        fcb.setFileFilter(filterXML);
        xmlRadioButton.setSelected(true);     
        separatorTextField.setText(",");
        separatorTextField.setEnabled(false);
        quoteTextField.setText("\"");
        quoteTextField.setEnabled(false);
        
        xmlRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(xmlRadioButton.isSelected())  {
                    fcb.setFileFilter(filterXML);
                    separatorTextField.setEnabled(false);
                    quoteTextField.setEnabled(false);
                    pathTextField.setText("");
                }
            }
        });
        
        csvRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(csvRadioButton.isSelected())   {
                    fcb.setFileFilter(filterCSV);
                    separatorTextField.setEnabled(true);
                    quoteTextField.setEnabled(true);
                    pathTextField.setText("");
                }
            }
        });
        
        pathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = fcb.showOpenDialog();
                if(file != null)   {
                    pathTextField.setText(file.getAbsolutePath());
                }
            }
        });
    }
    
    public String getPath()   {
        return pathTextField.getText();
    }
    
    public String getSeparator()   {
        return separatorTextField.getText();
    }
    
    public String getQuote()   {
        return quoteTextField.getText();
    }
    
    public boolean isXML()   {
        return xmlRadioButton.isSelected();
    }
    
    public boolean isCSV()   {
        return csvRadioButton.isSelected();
    }
    
    public Object[] getButtons()   {
        Object[] obj = {
            okButton,
            cancelButton,
        };
        return obj;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        xmlRadioButton = new javax.swing.JRadioButton();
        csvRadioButton = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        separatorTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        quoteTextField = new javax.swing.JTextField();
        pathTextField = new javax.swing.JTextField();
        pathButton = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(xmlRadioButton, org.openide.util.NbBundle.getMessage(ImportFilePanel.class, "ImportFilePanel.xmlRadioButton.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(csvRadioButton, org.openide.util.NbBundle.getMessage(ImportFilePanel.class, "ImportFilePanel.csvRadioButton.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ImportFilePanel.class, "ImportFilePanel.jLabel1.text_1")); // NOI18N

        separatorTextField.setText(org.openide.util.NbBundle.getMessage(ImportFilePanel.class, "ImportFilePanel.separatorTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(ImportFilePanel.class, "ImportFilePanel.jLabel2.text_1")); // NOI18N

        quoteTextField.setText(org.openide.util.NbBundle.getMessage(ImportFilePanel.class, "ImportFilePanel.quoteTextField.text")); // NOI18N

        pathTextField.setText(org.openide.util.NbBundle.getMessage(ImportFilePanel.class, "ImportFilePanel.pathTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(pathButton, org.openide.util.NbBundle.getMessage(ImportFilePanel.class, "ImportFilePanel.pathButton.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(xmlRadioButton)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(pathTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(csvRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separatorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(quoteTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(pathButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pathButton)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(xmlRadioButton)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(csvRadioButton)
                            .addComponent(jLabel1)
                            .addComponent(separatorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(quoteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(pathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton csvRadioButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton pathButton;
    private javax.swing.JTextField pathTextField;
    private javax.swing.JTextField quoteTextField;
    private javax.swing.JTextField separatorTextField;
    private javax.swing.JRadioButton xmlRadioButton;
    // End of variables declaration//GEN-END:variables
}
