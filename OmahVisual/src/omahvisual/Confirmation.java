package omahvisual;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JLabel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Confirmation extends javax.swing.JFrame {

    private String namaPengguna;
    private String barangDipilih;
    private double hargaBarang;
    private int durasiSewa;
    private double diskon;
    private Date tanggalPengambilan;
    private String waktuPengambilan;
    private Date tanggalPengembalian;
    private String waktuPengembalian;
    private double total;
    private String barangDipilihValue;
    private String loggedUsername;

    Confirmation() {
        sukses.setVisible(false);
    }

    public Confirmation(String namaPengguna, String barangDipilih, double hargaBarang,
            int durasiSewa, double diskon, Date tanggalPengambilan, String waktuPengambilan,
            Date tanggalPengembalian, String waktuPengembalian, double total) {
        initComponents();
        sukses.setVisible(false);
        // Set the instance variables with the passed data
        this.namaPengguna = namaPengguna;
        this.barangDipilih = barangDipilih;
        this.hargaBarang = hargaBarang;
        this.durasiSewa = durasiSewa;
        this.diskon = diskon;
        this.tanggalPengambilan = tanggalPengambilan;
        this.waktuPengambilan = waktuPengambilan;
        this.tanggalPengembalian = tanggalPengembalian;
        this.waktuPengembalian = waktuPengembalian;
        this.total = total;

        // Display the data in the GUI
        displayData();
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

    private void displayData() {
        // Set data to JLabels or other components in your GUI
        jLabelNama.setText("Nama: " + namaPengguna);
        jLabelBarang.setText("Barang: " + barangDipilih);
        jLabelHarga.setText("Harga Barang: " + formatCurrency(hargaBarang));
        jLabelDurasi.setText("Durasi Sewa: " + durasiSewa + " hari");
        jLabelDiskon.setText("Diskon: " + formatCurrency(diskon));
        jLabelTanggalPengambilan.setText("Tanggal Pengambilan: " + formatDate(tanggalPengambilan) + " " + waktuPengambilan);

        // Calculate and set the return date based on the duration
        Date returnDate = calculateReturnDate(tanggalPengambilan, durasiSewa);
        String returnTime = calculateReturnTime(tanggalPengambilan, durasiSewa);
        jLabelTanggalPengembalian.setText("Tanggal Pengembalian: " + formatDate(returnDate) + " " + returnTime);

        // Set the total amount
        jLabelTotal.setText("Total: " + formatCurrency(total));
        double dpTotal = 0.3 * total;
        jLabelDP.setText("DP : " + formatCurrency(total * 0.3));
    }

// Add a method to format currency
    private String formatCurrency(double amount) {
        Locale indonesiaLocale = new Locale("id", "ID");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(indonesiaLocale);
        return currencyFormat.format(amount);
    }

// Add a method to format date
    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        submit = new javax.swing.JButton();
        jLabelNama = new javax.swing.JLabel();
        jLabelBarang = new javax.swing.JLabel();
        jLabelHarga = new javax.swing.JLabel();
        jLabelDurasi = new javax.swing.JLabel();
        jLabelDiskon = new javax.swing.JLabel();
        jLabelTanggalPengambilan = new javax.swing.JLabel();
        jLabelTanggalPengembalian = new javax.swing.JLabel();
        jLabelTotal = new javax.swing.JLabel();
        sukses = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        dp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        namaPegawai = new javax.swing.JComboBox<>();
        jLabelDP = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        submit.setBackground(new java.awt.Color(0, 153, 0));
        submit.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        submit.setText("Submit");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        jLabelNama.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabelNama.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNama.setText("jLabel1");
        jLabelNama.setMaximumSize(new java.awt.Dimension(100, 40));
        jLabelNama.setPreferredSize(new java.awt.Dimension(74, 32));

        jLabelBarang.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabelBarang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBarang.setText("jLabel1");
        jLabelBarang.setMaximumSize(new java.awt.Dimension(100, 40));
        jLabelBarang.setPreferredSize(new java.awt.Dimension(74, 32));

        jLabelHarga.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabelHarga.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelHarga.setText("jLabel2");
        jLabelHarga.setMaximumSize(new java.awt.Dimension(100, 40));
        jLabelHarga.setPreferredSize(new java.awt.Dimension(74, 32));

        jLabelDurasi.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabelDurasi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDurasi.setText("jLabel3");
        jLabelDurasi.setMaximumSize(new java.awt.Dimension(100, 40));
        jLabelDurasi.setPreferredSize(new java.awt.Dimension(74, 32));

        jLabelDiskon.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabelDiskon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDiskon.setText("jLabel5");
        jLabelDiskon.setMaximumSize(new java.awt.Dimension(100, 40));
        jLabelDiskon.setPreferredSize(new java.awt.Dimension(74, 32));

        jLabelTanggalPengambilan.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabelTanggalPengambilan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTanggalPengambilan.setText("jLabel6");
        jLabelTanggalPengambilan.setMaximumSize(new java.awt.Dimension(100, 40));
        jLabelTanggalPengambilan.setPreferredSize(new java.awt.Dimension(74, 32));

        jLabelTanggalPengembalian.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabelTanggalPengembalian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTanggalPengembalian.setText("jLabel7");
        jLabelTanggalPengembalian.setMaximumSize(new java.awt.Dimension(100, 40));
        jLabelTanggalPengembalian.setPreferredSize(new java.awt.Dimension(74, 32));

        jLabelTotal.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabelTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTotal.setText("jLabel1");
        jLabelTotal.setMaximumSize(new java.awt.Dimension(100, 40));
        jLabelTotal.setPreferredSize(new java.awt.Dimension(74, 32));

        sukses.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        sukses.setText("Transaksi Berhasil!!");

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel1.setText("DP");

        dp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dpActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel2.setText("Nama Pegawai");

        namaPegawai.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        namaPegawai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALDINO", "VINO", "RYAN" }));

        jLabelDP.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabelDP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDP.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(sukses)
                                .addGap(305, 305, 305))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(submit, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2))
                                        .addGap(35, 35, 35)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(dp)
                                            .addComponent(namaPegawai, 0, 149, Short.MAX_VALUE))))
                                .addGap(242, 242, 242))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabelDP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabelNama, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
                            .addComponent(jLabelBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelHarga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelDurasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelDiskon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTanggalPengambilan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTanggalPengembalian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabelHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelDurasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTanggalPengambilan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTanggalPengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelDP)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(namaPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submit, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(sukses)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        // Get the last invoice number from the database
        String lastInvoiceNumber = getLastInvoiceNumberFromDatabase();

        // Generate the next invoice number
        String nextInvoiceNumber = generateNoInvoice(lastInvoiceNumber);

        // Calculate paketHarga and alatHarga based on the formula
        double paketHarga = (total + diskon) / durasiSewa; // Adjust the formula if needed
        double alatHarga = (total + diskon) / durasiSewa; // Adjust the formula if needed

        String url = "jdbc:sqlserver://localhost:1433;databaseName=Transaksi_Omah_Visual2;user=sa;password=12345678;integratedSecurity=true;" + "encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String dbPassword = "12345678";
        // Get database connection
        try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {

            // Insert data into Tabel_Utama
            // Query to retrieve the first name based on the logged-in username
            String employeeQuery = "SELECT first_name FROM employee WHERE username = ?";

            String utamaQuery = "INSERT INTO Tabel_Utama (No_Invoice, Nama_Pelanggan, Tanggal_Sewa, Jam_Ambil, Tanggal_Kembali, Jam_Kembali, Durasi_Sewa, Ket, Disc, Total, DP, Nama_Pegawai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement utamaStatement = connection.prepareStatement(utamaQuery)) {
                utamaStatement.setString(1, nextInvoiceNumber); // Assuming you have a method to generate a unique No_Invoice
                utamaStatement.setString(2, namaPengguna);
                utamaStatement.setDate(3, new java.sql.Date(tanggalPengambilan.getTime())); // Assuming tanggalPengambilan is a Date
                utamaStatement.setString(4, waktuPengambilan);
                utamaStatement.setDate(5, new java.sql.Date(tanggalPengembalian.getTime())); // Assuming tanggalPengembalian is a Date
                utamaStatement.setString(6, waktuPengembalian);
                utamaStatement.setInt(7, durasiSewa);
                utamaStatement.setString(8, null);
                utamaStatement.setDouble(9, diskon);
                utamaStatement.setDouble(10, total);
                utamaStatement.setDouble(11, Double.parseDouble(dp.getText()));
                utamaStatement.setString(12, namaPegawai.getSelectedItem().toString());

                utamaStatement.executeUpdate();
            }

            // Insert data into Tabel_Penjual (assuming constant values)
            String penjualQuery = "INSERT INTO Tabel_Penjual (No_Invoice, Nama_Penjual, Alamat_Penjual, Telp_Penjual) VALUES (?, ?, ?, ?)";
            try (PreparedStatement penjualStatement = connection.prepareStatement(penjualQuery)) {
                penjualStatement.setString(1, nextInvoiceNumber);
                penjualStatement.setString(2, "OMAH VISUAL KAMERA");
                penjualStatement.setString(3, "JL TERUSAN KSATRIAN");
                penjualStatement.setString(4, "82330682323");

                penjualStatement.executeUpdate();
            }

            if (barangDipilih.toUpperCase().startsWith("PAKET")) {
                String paketQuery = "INSERT INTO Tabel_Paket (No_Invoice, Nama_Paket, Harga) VALUES (?, ?, ?)";
                try (PreparedStatement paketStatement = connection.prepareStatement(paketQuery)) {
                    paketStatement.setString(1, nextInvoiceNumber);
                    paketStatement.setString(2, barangDipilih);
                    paketStatement.setDouble(3, paketHarga); // Assuming you have a variable for paketHarga

                    paketStatement.executeUpdate();
                }

                // Retrieve Nama_Alat from Katalog where Nama_Paket is equal to barangDipilih
                String alatPaketQuery = "SELECT Nama_Alat FROM Katalog WHERE Nama_Paket = ?";
                List<String> alatPaketList = new ArrayList<>();
                try (PreparedStatement alatPaketStatement = connection.prepareStatement(alatPaketQuery)) {
                    alatPaketStatement.setString(1, barangDipilih);

                    // Execute the query to get the list of Nama_Alat
                    try (ResultSet resultSet = alatPaketStatement.executeQuery()) {
                        while (resultSet.next()) {
                            alatPaketList.add(resultSet.getString("Nama_Alat"));
                        }
                    }
                }

                // Insert data into Tabel_Alat for each Nama_Alat in the alatPaketList
                String alatQuery = "INSERT INTO Tabel_Alat (No_Invoice, Nama_Alat, Include_Unit, Harga) VALUES (?, ?, ?, ?)";
                for (String alat : alatPaketList) {
                    try (PreparedStatement alatStatement = connection.prepareStatement(alatQuery)) {
                        alatStatement.setString(1, nextInvoiceNumber);
                        alatStatement.setString(2, alat);

                        // Retrieve Include_Unit from Katalog based on the condition
                        String includeUnitQuery = "SELECT Include_Unit FROM Katalog WHERE Nama_Alat = ?";
                        try (PreparedStatement includeUnitStatement = connection.prepareStatement(includeUnitQuery)) {
                            includeUnitStatement.setString(1, alat);

                            // Execute the query to get Include_Unit
                            try (ResultSet resultSet = includeUnitStatement.executeQuery()) {
                                if (resultSet.next()) {
                                    String includeUnitValue = resultSet.getString("Include_Unit");
                                    alatStatement.setString(3, includeUnitValue);
                                } else {
                                    // Handle the case where no matching record is found in Katalog
                                    alatStatement.setString(3, "DefaultIncludeUnit");
                                }
                            }
                        }

                        // Assuming you have a variable for alatHarga
                        alatStatement.setDouble(4, alatHarga);

                        alatStatement.executeUpdate();
                    }
                }
            } else {
                String paketQuery = "INSERT INTO Tabel_Paket (No_Invoice, Nama_Paket, Harga) VALUES (?, ?, ?)";
                try (PreparedStatement paketStatement = connection.prepareStatement(paketQuery)) {
                    paketStatement.setString(1, nextInvoiceNumber);
                    paketStatement.setString(2, "Non Paket");
                    paketStatement.setDouble(3, paketHarga); // Assuming you have a variable for paketHarga

                    paketStatement.executeUpdate();
                }

                String alatQuery = "INSERT INTO Tabel_Alat (No_Invoice, Nama_Alat, Include_Unit, Harga) VALUES (?, ?, ?, ?)";
                try (PreparedStatement alatStatement = connection.prepareStatement(alatQuery)) {
                    alatStatement.setString(1, nextInvoiceNumber);
                    alatStatement.setString(2, barangDipilih);

                    // Retrieve Include_Unit from Katalog based on the condition
                    String includeUnitQuery = "SELECT Include_Unit FROM Katalog WHERE Nama_Alat = ?";
                    try (PreparedStatement includeUnitStatement = connection.prepareStatement(includeUnitQuery)) {
                        includeUnitStatement.setString(1, barangDipilih);

                        // Execute the query to get Include_Unit
                        try (ResultSet resultSet = includeUnitStatement.executeQuery()) {
                            if (resultSet.next()) {
                                String includeUnitValue = resultSet.getString("Include_Unit");
                                alatStatement.setString(3, includeUnitValue);
                            } else {
                                // Handle the case where no matching record is found in Katalog
                                alatStatement.setString(3, "DefaultIncludeUnit");
                            }
                        }
                    }

                    // Assuming you have a variable for alatHarga
                    alatStatement.setDouble(4, alatHarga);

                    alatStatement.executeUpdate();
                }

            }
            // Close the confirmation window or perform any other actions after successful submission
            setVisible(false);
            LandingPage field = new LandingPage();
            field.setVisible(true);

        
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions accordingly
        }
        
        //Invoice//
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Transaksi_Omah_Visual2;integratedSecurity=true;user=sa;password=12345678"+"encrypt=true;trustServerCertificate=true")) {
                String reportPath = "C:\\Users\\naray\\Downloads\\Invoice\\Note_Omah_Visual5.jasper";
                JasperReport jr = JasperCompileManager.compileReport(reportPath);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
                JasperViewer.viewReport(jp);
            }
        }
        catch(ClassNotFoundException | SQLException | JRException e){
            JOptionPane.showMessageDialog(null,e);
        }
            
  

        
        
        sukses.setVisible(true);
        setVisible(false);
        LandingPage field = new LandingPage();
        field.setVisible(true);
    }//GEN-LAST:event_submitActionPerformed

    private void dpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dpActionPerformed

    private String generateNoInvoice(String lastInvoiceNumber) {
        // Extract the numeric part
        String numericPart = lastInvoiceNumber.trim();

        // Increment the numeric part for the next invoice
        int nextNumericPart = Integer.parseInt(numericPart) + 1;

        // Combine the incremented numeric part to create the next invoice number
        String nextInvoiceNumber = String.format("%04d", nextNumericPart);

        return nextInvoiceNumber;
    }

    private String getLastInvoiceNumberFromDatabase() {
        String lastInvoiceNumber = "";

        String url = "jdbc:sqlserver://localhost:1433;databaseName=Transaksi_Omah_Visual2;user=sa;password=12345678;integratedSecurity=true;" + "encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String dbPassword = "12345678";
        try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
            String query = "SELECT MAX(No_Invoice) AS LastInvoiceNumber FROM Tabel_Utama";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    lastInvoiceNumber = resultSet.getString("LastInvoiceNumber");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions accordingly
        }

        return lastInvoiceNumber;
    }

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
            java.util.logging.Logger.getLogger(Confirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Confirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Confirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Confirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Confirmation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelBarang;
    private javax.swing.JLabel jLabelDP;
    private javax.swing.JLabel jLabelDiskon;
    private javax.swing.JLabel jLabelDurasi;
    private javax.swing.JLabel jLabelHarga;
    private javax.swing.JLabel jLabelNama;
    private javax.swing.JLabel jLabelTanggalPengambilan;
    private javax.swing.JLabel jLabelTanggalPengembalian;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JComboBox<String> namaPegawai;
    private javax.swing.JButton submit;
    private javax.swing.JLabel sukses;
    // End of variables declaration//GEN-END:variables
}
