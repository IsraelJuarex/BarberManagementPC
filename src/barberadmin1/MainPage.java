/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/Application.java to edit this template
 */
package barberadmin1;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author israel
 */
public class MainPage extends javax.swing.JFrame {

    private static final java.util.Map<Integer, Instant> activityStartTimes = new java.util.HashMap<>();

    /**
     * Creates new form MainPage
     */
    public MainPage() {
        initComponents();
        showDate();
        showTime();
             
        //Execute every certain time 
Runnable Loadalldata = () -> { loadData(); showResumen(); };
      ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
executor.scheduleAtFixedRate(Loadalldata, 0, 5, TimeUnit.SECONDS);
      
   
        showCostos();
    }
    
    
public static void resetData(){
         try {
            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
            dt.setRowCount(0);

            Statement st = DataBaseConnection.getConnectionToDB().createStatement();
            String query= "UPDATE trabajadores SET cortes = DEFAULT, barbas = DEFAULT, cejas = DEFAULT , total= DEFAULT";
            st.executeUpdate(query);
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    

    public static void loadData() {
        try {
            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
            dt.setRowCount(0);

            Statement st = DataBaseConnection.getConnectionToDB().createStatement();
            ResultSet resultset = st.executeQuery("select * from trabajadores where admin = 0");
            
                     // Custom renderer for cell [1, 1]
            DefaultTableCellRenderer renderer;
            renderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    // Check the condition for changing the color
                    if (column == 0) {
                        String cellValue = (String) value;
                        // Replace "yourCondition" with your actual condition
                        switch (cellValue) {
                            case "Offline" -> {
                                c.setForeground(Color.RED);
                                activityStartTimes.remove(row);
                            }
                            case "Online" -> {
                                c.setForeground(Color.GREEN);
                                activityStartTimes.remove(row);
                            }
                            case "Cortando" -> {
                                c.setForeground(Color.ORANGE);
                                String timerText = getTimerTextForActivity(row);
                                setText(cellValue + " " + timerText);
                            }
                            default -> {
                            }
                        }
                        
                        
                    }
                    return c;
                }
            };

            // Set the custom renderer for column 1
            jTable1.getColumnModel().getColumn(0).setCellRenderer(renderer);

            
            while (resultset.next()) {
                ArrayList v = new ArrayList();
                v.add(resultset.getString(11));
                v.add(resultset.getString(5));
                v.add(resultset.getString(6));
                v.add(resultset.getString(7));
                v.add(resultset.getString(8));
                v.add(resultset.getString(9));
                dt.addRow(v.toArray());
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    // Helper method to get the timer text based on the activity
  private static String getTimerTextForActivity(int row) {
        Instant startTime = activityStartTimes.get(row);
        if (startTime == null) {
            // Activity has just started, record the start time
            startTime = Instant.now();
            activityStartTimes.put(row, startTime);
        }

        // Calculate the elapsed time
        Duration duration = Duration.between(startTime, Instant.now());

        // Format the duration
        long hours = duration.toHours();
        long minutes = (duration.toMinutes() % 60);
        long seconds = (duration.getSeconds() % 60);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
    
    final void showDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date();
        LabelDate.setText(sdf.format(d));
    }

    final void showTime() {
        new Timer(0, (ActionEvent e) -> {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
            Date d = new Date();
            LabelTime.setText(sdf.format(d));
        }).start();
    }
    
    public void showResumen(){
        jLabelBarbasTotales.setText("Barbas: "+ DatabaseData.getTotalBarbas());
        jLabelCejasTotales.setText("Cejas: "+ DatabaseData.getTotalCejas());
        jLabelCortesTotales.setText("Cortes: "+ DatabaseData.getTotalCortes());
        jLabelEfectivoTotal.setText("Total: $"+ DatabaseData.getTotalEfectivo());
        jLabelBarberosOnline.setText("Barberos Online: "+ DatabaseData.getTotalOnline());
    }

    public static void showCostos() {
        jlabelPrecioCorte.setText("Corte $" + DatabaseData.getPrecioCorte());
        jlabelPrecioBarba.setText("Barba $" + DatabaseData.getPrecioBarba());
        jlabelPrecioCeja.setText("Ceja $" + DatabaseData.getPrecioCeja());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel13 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabelCortesTotales = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabelBarberosOnline = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabelCejasTotales = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabelEfectivoTotal = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabelBarbasTotales = new javax.swing.JLabel();
        LabelTime = new javax.swing.JLabel();
        LabelDate = new javax.swing.JLabel();
        jlabelPrecioCorte = new javax.swing.JLabel();
        jlabelPrecioBarba = new javax.swing.JLabel();
        jlabelPrecioCeja = new javax.swing.JLabel();
        jButtonReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        exitMenuItem1 = new javax.swing.JMenuItem();
        exitMenuItem2 = new javax.swing.JMenuItem();
        fileMenu1 = new javax.swing.JMenu();
        openMenuItem2 = new javax.swing.JMenuItem();

        jPanel13.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("AppleGothic", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barberadmin1/barber-shop-2.png"))); // NOI18N
        jLabel5.setText("BarberManage");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 190, 80));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 100));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));

        jLabelCortesTotales.setText("Cortes:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelCortesTotales)
                .addContainerGap(132, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(jLabelCortesTotales)
                .addContainerGap())
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 180, 30));

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));

        jLabelBarberosOnline.setText("Barberos Online:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelBarberosOnline)
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelBarberosOnline)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 180, 30));

        jPanel6.setBackground(new java.awt.Color(204, 255, 255));

        jLabelCejasTotales.setText("Cejas:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelCejasTotales)
                .addContainerGap(137, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(jLabelCejasTotales)
                .addContainerGap())
        );

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 180, 30));

        jPanel7.setBackground(new java.awt.Color(204, 255, 255));

        jLabelEfectivoTotal.setText("Total: ");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelEfectivoTotal)
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(jLabelEfectivoTotal)
                .addContainerGap())
        );

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 180, 30));

        jPanel8.setBackground(new java.awt.Color(204, 255, 255));

        jLabelBarbasTotales.setText("Barbas:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelBarbasTotales)
                .addContainerGap(128, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(jLabelBarbasTotales)
                .addContainerGap())
        );

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 180, 30));

        LabelTime.setFont(new java.awt.Font("Apple Braille", 1, 18)); // NOI18N
        LabelTime.setForeground(new java.awt.Color(255, 255, 255));
        LabelTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTime.setText("Label Hora");
        jPanel3.add(LabelTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 200, 20));

        LabelDate.setFont(new java.awt.Font("Apple Braille", 1, 18)); // NOI18N
        LabelDate.setForeground(new java.awt.Color(255, 255, 255));
        LabelDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelDate.setText("Label Fecha");
        jPanel3.add(LabelDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 10, 200, -1));

        jlabelPrecioCorte.setForeground(new java.awt.Color(255, 255, 255));
        jlabelPrecioCorte.setText("Corte $");
        jPanel3.add(jlabelPrecioCorte, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        jlabelPrecioBarba.setForeground(new java.awt.Color(255, 255, 255));
        jlabelPrecioBarba.setText("Barba $");
        jPanel3.add(jlabelPrecioBarba, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, -1, -1));

        jlabelPrecioCeja.setForeground(new java.awt.Color(255, 255, 255));
        jlabelPrecioCeja.setText("Ceja $");
        jPanel3.add(jlabelPrecioCeja, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, -1, -1));

        jButtonReset.setText("Resetear");
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 200, 40));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 200, 480));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Status", "Barbero", "Cortes", "Barbas", "Cejas", "$"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(0, 204, 204));
        jTable1.setMinimumSize(new java.awt.Dimension(120, 150));
        jTable1.setOpaque(false);
        jTable1.setRequestFocusEnabled(false);
        jTable1.setRowHeight(30);
        jTable1.setRowSelectionAllowed(false);
        jTable1.setShowGrid(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setUpdateSelectionOnSort(false);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 980, 260));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Corte $");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, -1, -1));

        fileMenu.setMnemonic('f');
        fileMenu.setText("Menu");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Historial");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Estadisticas");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        exitMenuItem1.setMnemonic('x');
        exitMenuItem1.setText("Trabajadores");
        exitMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItem1ActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem1);

        exitMenuItem2.setMnemonic('x');
        exitMenuItem2.setText("Salir");
        exitMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItem2ActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem2);

        menuBar.add(fileMenu);

        fileMenu1.setMnemonic('f');
        fileMenu1.setText("Configuracion");

        openMenuItem2.setMnemonic('o');
        openMenuItem2.setText("Cortes y cobros");
        openMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItem2ActionPerformed(evt);
            }
        });
        fileMenu1.add(openMenuItem2);

        menuBar.add(fileMenu1);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void exitMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItem1ActionPerformed
        TrabajadoresPage trabajadoresPage = new TrabajadoresPage();
        trabajadoresPage.setVisible(true);
        trabajadoresPage.setLocationRelativeTo(null);
// TODO add your handling code here:
    }//GEN-LAST:event_exitMenuItem1ActionPerformed

    private void exitMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItem2ActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_exitMenuItem2ActionPerformed

    private void openMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItem2ActionPerformed
        CortesyServiciosPage cortesyserviciospage = new CortesyServiciosPage();
        cortesyserviciospage.setVisible(true);
        cortesyserviciospage.setLocationRelativeTo(null);        // TODO add your handling code here:
    }//GEN-LAST:event_openMenuItem2ActionPerformed

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        // TODO add your handling code here:
        resetData();
        
    }//GEN-LAST:event_jButtonResetActionPerformed

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
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelDate;
    private javax.swing.JLabel LabelTime;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenuItem exitMenuItem1;
    private javax.swing.JMenuItem exitMenuItem2;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu fileMenu1;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelBarbasTotales;
    private javax.swing.JLabel jLabelBarberosOnline;
    private javax.swing.JLabel jLabelCejasTotales;
    private javax.swing.JLabel jLabelCortesTotales;
    private javax.swing.JLabel jLabelEfectivoTotal;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JLabel jlabelPrecioBarba;
    public static javax.swing.JLabel jlabelPrecioCeja;
    public static javax.swing.JLabel jlabelPrecioCorte;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem openMenuItem2;
    // End of variables declaration//GEN-END:variables



}
