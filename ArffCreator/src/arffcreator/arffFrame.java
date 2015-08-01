/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arffcreator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author HASNA
 */

public class arffFrame extends javax.swing.JFrame {

    /**
     * Creates new form arffFrame
     */
    protected String dataset;
    public arffFrame() {
        initComponents();
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
        textArea = new javax.swing.JTextArea();
        create = new javax.swing.JButton();
        export = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        create.setText("Create ARFF");
        create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createActionPerformed(evt);
            }
        });

        export.setText("Export");
        export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(create)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(export)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(create)
                    .addComponent(export))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createActionPerformed
        // TODO add your handling code here:
        
    FastVector      atts;
    FastVector      attsRel;
    FastVector      attVals;
    FastVector      attValsRel;
    Instances       data;
    Instances       dataRel;
    double[]        vals;
    double[]        valsRel;
    int             i;

    // 1. set up attributes
    atts = new FastVector();
    // - numeric
    atts.addElement(new Attribute("att1"));
    // - nominal
    attVals = new FastVector();
    for (i = 0; i < 5; i++)
      attVals.addElement("val" + (i+1));
    atts.addElement(new Attribute("att2", attVals));
    // - string
    atts.addElement(new Attribute("att3", (FastVector) null));
    // - date
    atts.addElement(new Attribute("att4", "yyyy-MM-dd"));
    // - relational
    attsRel = new FastVector();
    // -- numeric
    attsRel.addElement(new Attribute("att5.1"));
    // -- nominal
    attValsRel = new FastVector();
    for (i = 0; i < 5; i++)
      attValsRel.addElement("val5." + (i+1));
    attsRel.addElement(new Attribute("att5.2", attValsRel));
    dataRel = new Instances("att5", attsRel, 0);
    atts.addElement(new Attribute("att5", dataRel, 0));

    // 2. create Instances object
    data = new Instances("MyRelation", atts, 0);

    // 3. fill with data
    // first instance
    vals = new double[data.numAttributes()];
    // - numeric
    vals[0] = Math.PI;
    // - nominal
    vals[1] = attVals.indexOf("val3");
    // - string
    vals[2] = data.attribute(2).addStringValue("This is a string!");
        try {
            // - date
            vals[3] = data.attribute(3).parseDate("2015-07-30");
        } catch (ParseException ex) {
            Logger.getLogger(arffFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    // - relational
    dataRel = new Instances(data.attribute(4).relation(), 0);
    // -- first instance
    valsRel = new double[2];
    valsRel[0] = Math.PI + 1;
    valsRel[1] = attValsRel.indexOf("val5.3");
    dataRel.add(new Instance(1.0, valsRel));
    // -- second instance
    valsRel = new double[2];
    valsRel[0] = Math.PI + 2;
    valsRel[1] = attValsRel.indexOf("val5.2");
    dataRel.add(new Instance(1.0, valsRel));
    vals[4] = data.attribute(4).addRelation(dataRel);
    // add
    data.add(new Instance(1.0, vals));

    // second instance
    vals = new double[data.numAttributes()];  // important: needs NEW array!
    // - numeric
    vals[0] = Math.E;
    // - nominal
    vals[1] = attVals.indexOf("val1");
    // - string
    vals[2] = data.attribute(2).addStringValue("And another one!");
        try {
            // - date
            vals[3] = data.attribute(3).parseDate("2015-07-30");
        } catch (ParseException ex) {
            Logger.getLogger(arffFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    // - relational
    dataRel = new Instances(data.attribute(4).relation(), 0);
    // -- first instance
    valsRel = new double[2];
    valsRel[0] = Math.E + 1;
    valsRel[1] = attValsRel.indexOf("val5.4");
    dataRel.add(new Instance(1.0, valsRel));
    // -- second instance
    valsRel = new double[2];
    valsRel[0] = Math.E + 2;
    valsRel[1] = attValsRel.indexOf("val5.1");
    dataRel.add(new Instance(1.0, valsRel));
    vals[4] = data.attribute(4).addRelation(dataRel);
    // add
    data.add(new Instance(1.0, vals));

    // 4. output data
          
    textArea.append(data.toString());
           
    dataset=data.toString();
    
    }//GEN-LAST:event_createActionPerformed

    private void exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportActionPerformed
        // TODO add your handling code here:
        File file=new File("C:\\Users\\Rapid\\Desktop\\Data\\datatest.arff");
        
        if(!file.exists())
        {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(arffFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            FileWriter fw=new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write(dataset);
            bw.close();
            textArea.append("Exported and exiting");
        } catch (IOException ex) {
            Logger.getLogger(arffFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.exit(0);
        
        
    }//GEN-LAST:event_exportActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(arffFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(arffFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(arffFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(arffFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new arffFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton create;
    private javax.swing.JButton export;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
}
