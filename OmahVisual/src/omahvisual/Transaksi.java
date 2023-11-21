package omahvisual;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;

public class Transaksi extends javax.swing.JFrame {

    public Transaksi() {
        initComponents();
        loadTableData();

    }

    private double getHargaBarang(String namaBarang) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Transaksi_Omah_Visual;user=Project-OmahVisual;password=OmahVisual2023;integratedSecurity=true;" + "encrypt=true;trustServerCertificate=true";
        String user = "Project-OmahVisual";
        String dbPassword = "OmahVisual2023";

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
            String query;
            if (namaBarang.toUpperCase().startsWith("PAKET")) {
                query = "SELECT Harga FROM Katalog WHERE Nama_Paket = ?";
            } else {
                query = "SELECT Harga FROM Katalog WHERE Nama_Alat = ?";
            }

            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.setString(1, namaBarang);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        return rs.getDouble("Harga");
                    } else {
                        System.out.println("Item/Package not found in the database");
                        return 0.0;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0.0;
        }
    }

    private Date calculateReturnDate(Date startDate, int duration) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_MONTH, duration);
        return calendar.getTime();
    }

    private String calculateReturnTime(Date startTime, int duration) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.add(Calendar.HOUR_OF_DAY, duration);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(calendar.getTime());
    }

    private void loadTableData() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Transaksi_Omah_Visual;user=Project-OmahVisual;password=OmahVisual2023;integratedSecurity=true;" + "encrypt=true;trustServerCertificate=true";
        String user = "Project-OmahVisual";
        String dbPassword = "OmahVisual2023";
        try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
            String query = "SELECT * FROM Katalog";
            try (PreparedStatement pst = connection.prepareStatement(query); ResultSet rs = pst.executeQuery()) {

                // Create a DefaultTableModel to store the data
                DefaultTableModel model = new DefaultTableModel();

                // Add columns to the model
                model.addColumn("Kategori");
                model.addColumn("Nama Paket");
                model.addColumn("Nama Alat");
                model.addColumn("Include Unit");
                model.addColumn("Harga");

                // Add rows to the model
                while (rs.next()) {
                    Object[] row = new Object[]{
                        rs.getString("Kategori"),
                        rs.getString("Nama_Paket"),
                        rs.getString("Nama_Alat"),
                        rs.getString("Include_Unit"),
                        formatCurrency(rs.getDouble("Harga"))
                    };
                    model.addRow(row);
                }
                tabelKatalog.setModel(model);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private String formatCurrency(double amount) {
        Locale indonesiaLocale = new Locale("id", "ID");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(indonesiaLocale);
        return currencyFormat.format(amount);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelKatalog = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        pilihBarang = new javax.swing.JComboBox<>();
        confirm = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        durasiSewa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        namaPengguna = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        diskon = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabelKatalog.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        tabelKatalog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kategori", "Nama Paket", "Nama Alat", "Include Unit", "Harga"
            }
        ));
        tabelKatalog.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tabelKatalog);
        tabelKatalog.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel1.setText("Pilih Barang");

        pilihBarang.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        pilihBarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PAKET LIVECAM 1", "PAKET CINEMATIC 1", "PAKET CINEMATIC 3", "PAKET LIVECAM 2", "PAKET CINEMATIC 9", "CANON 6D", "CANON 600D", "CANON 1200D+18-55mmMEMORY", "SONY a7 Mark iii KIT", "SONY a6 400", "IPHONE XR", "IPHONE 11", "CANON FIX 50MM F1.8", "SIGMA 16MM F1.4 FOR SONY", "CANON 600D+18-55MM", "CANON 650D", "DJI MAVIC 2 ZOOM", "DJI MAVIC AIR 2", "FUJIFILM XT 30ii BO", "SIGMA FUJI 16mm f1.4 DG for FUJI", "SIGMA 30mm F1.4", "DJI RONIN SC 2", "IPHONE 14 PRO", "JIMMY JIB + INSTALASI + PENGIRIMAN", "CANON FIX 50MM F1.8", "SONY HXR NX--100 SDI", "IPHONE 14 PRO", "AIRPODS 3RD GENERATION", "CANON 60D + 24-105mm", "GoPro Hero9", "GoPro Hero8", "SARAMONIC BLINK 500 B2 WIRELES MIC", "CANON EOS M100", "SONY a7 Mark ii KIT." }));
        pilihBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihBarangActionPerformed(evt);
            }
        });

        confirm.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        confirm.setText("Confirm");
        confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel2.setText("Durasi Sewa");

        durasiSewa.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        durasiSewa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                durasiSewaActionPerformed(evt);
            }
        });

        jLabel4.setText("Nama");

        namaPengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaPenggunaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel6.setText("Diskon");

        diskon.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(namaPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pilihBarang, 0, 133, Short.MAX_VALUE)
                            .addComponent(durasiSewa, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(33, 33, 33)
                                .addComponent(diskon, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(214, 214, 214))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(namaPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pilihBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(durasiSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(diskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addComponent(confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {diskon, durasiSewa, jLabel1, jLabel2, jLabel6, pilihBarang});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pilihBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihBarangActionPerformed

    }//GEN-LAST:event_pilihBarangActionPerformed

    private void confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmActionPerformed
        // Get the selected data
        String namaPenggunaValue = namaPengguna.getText();
        String barangDipilihValue = pilihBarang.getSelectedItem().toString();
        int durasiSewaValue = Integer.parseInt(durasiSewa.getText());
        double diskonValue = Double.parseDouble(diskon.getText());

        // Perform any additional calculations needed for the Confirmation
        Date tanggalPengambilan = new Date(); // current date and time
        Date tanggalPengembalian = calculateReturnDate(tanggalPengambilan, durasiSewaValue);
        String waktuPengambilan = new SimpleDateFormat("HH:mm:ss").format(tanggalPengambilan);
        String waktuPengembalian = calculateReturnTime(tanggalPengambilan, durasiSewaValue);

        // Calculate Total
        double hargaBarang = getHargaBarang(barangDipilihValue);
        double total = (hargaBarang * durasiSewaValue) - diskonValue;

        Confirmation confirmation = new Confirmation(namaPenggunaValue, barangDipilihValue, hargaBarang,
                durasiSewaValue, diskonValue, tanggalPengambilan, waktuPengambilan,
                tanggalPengembalian, waktuPengembalian, total);

        setVisible(false);
        confirmation.setVisible(true);
    }//GEN-LAST:event_confirmActionPerformed

    private void durasiSewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_durasiSewaActionPerformed

    }//GEN-LAST:event_durasiSewaActionPerformed

    private void namaPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaPenggunaActionPerformed

    }//GEN-LAST:event_namaPenggunaActionPerformed

    public static void main(String args[]) {

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
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confirm;
    private javax.swing.JTextField diskon;
    private javax.swing.JTextField durasiSewa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField namaPengguna;
    private javax.swing.JComboBox<String> pilihBarang;
    private javax.swing.JTable tabelKatalog;
    // End of variables declaration//GEN-END:variables
}
