package View;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.File;
import java.math.BigDecimal;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import javax.swing.JSpinner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class FormTransaksiPanel extends javax.swing.JPanel {
    
    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmodepayment;
    private DefaultTableModel tabmodeLembur;

    /**
     * global variable *
     */
    private String idLembur;
    private String pay;
    
    protected void aktiflembur() {
        txtKar2.setEnabled(true);
        txtnama.setEnabled(true);
        txtBayarLembur.setEnabled(true);
        txtKar2.requestFocus();
    }
    
    protected void kosonglembur() {
        txtKar2.setText("");
        txtnama.setText("");
        txtBayarLembur.setText("");
        tcari1.setText("");
    }
    
    protected void aktifpayment() {
        txtKaryawan.setEnabled(true);
        txtnama.setEnabled(true);
        txttunjangan1.setEnabled(true);
        txtgaji.setEnabled(true);
        txtpemblembur.setEnabled(true);
        txtPotongan.setEnabled(true);
        txtpembgaji.setEnabled(true);
        tglgajian.setEnabled(true);
        txtKaryawan.requestFocus();
    }
    
    protected void kosongpayment() {
        txtKaryawan.setText("");
        txtnama.setText("");
        txttunjangan1.setText("");
        txtgaji.setText("");
        txtpemblembur.setText("");
        txtPotongan.setText("");
        txtpembgaji.setText("");
        tglgajian.setDate(null);
        tcari2.setText("");
    }
    
    protected void datatabelLembur(String name) {
        Object[] baris = {"No", "ID Karyawan", "Bayaran", "Nama Karyawan", "ID Lembur"};
        tabmodeLembur = new DefaultTableModel(null, baris);
        
        tabellembur.setModel(tabmodeLembur);
        tabellembur.getColumn("ID Lembur").setMinWidth(0);
        
        tabellembur.getColumn("ID Lembur").setMaxWidth(0);
        tabellembur.getColumn("ID Lembur").setWidth(0);
        String sql = "SELECT a.*, b.nama FROM `data_lembur` a\n"
                + "INNER JOIN `data_karyawan` b ON b.`id_kar` = a.`id_kar`\n"
                + "WHERE b.`nama` LIKE '%" + name + "%'";
        try {
            java.sql.Statement stat2 = conn.createStatement();
            ResultSet hasil = stat2.executeQuery(sql);
            
            int no = 0;
            
            while (hasil.next()) {
                String a = hasil.getString("id_kar");
                String b = hasil.getString("bayaran");
                String c = hasil.getString("nama");
                String d = hasil.getString("id_lembur");
                
                ++no; // no = no + 1

                String[] data5 = {String.valueOf(no), a, b, c, d};
                tabmodeLembur.addRow(data5);
            }
        } catch (Exception e) {
        }
    }
    
    protected void datatablepayment(String name) {
        Object[] Baris = {"ID Karyawan", "Nama Karyawan", "Tunjangan", "Gaji Pokok", "Total Pembayaran Lembur", "Potongan", "Total Pembayaran Gaji", "ID Payment", "Tgl Gajian"};
        tabmodepayment = new DefaultTableModel(null, Baris);
        tabelpayment.setModel(tabmodepayment);
        tabelpayment.getColumn("ID Payment").setMinWidth(0);
        
        tabelpayment.getColumn("ID Payment").setMaxWidth(0);
        tabelpayment.getColumn("ID Payment").setWidth(0);
        String sql = "SELECT a.*, b.nama FROM `data_payment` a\n"
                + "INNER JOIN `data_karyawan` b ON b.`id_kar` = a.`id_kar`\n"
                + "WHERE b.`nama` LIKE '%" + name + "%'";
        try {
            java.sql.Statement stat1 = conn.createStatement();
            ResultSet hasil = stat1.executeQuery(sql);
            while (hasil.next()) {
                String a = hasil.getString("id_kar");
                String b = hasil.getString("nama");
                String c = hasil.getString("tunjangan");
                String d = hasil.getString("gp");
                String e = hasil.getString("pem_lembur");
                String f = hasil.getString("potongan");
                String g = hasil.getString("tot_gaji");
                String h = hasil.getString("id_pay");
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
                String i = "";
                
                if (hasil.getDate("created_date") != null) {
                    i = sdf.format(hasil.getDate("created_date"));
                }
                
                String[] data = {a, b, c, d, e, f, g, h, i};
                tabmodepayment.addRow(data);
            }
        } catch (Exception e) {
            
        }
    }
    
    public FormTransaksiPanel() {
        initComponents();
        tnama.setEditable(false);
        txtnama.setEditable(false);
        txttunjangan1.setEditable(false);
        txtgaji.setEditable(false);
        txtpemblembur.setEditable(false);
        txtPotongan.setEditable(false);
        txtpembgaji.setEditable(false);
        datatabelLembur("");
        datatablepayment("");
        
        tglgajian.setDateFormatString("dd MMMM yyyy");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        tabPayment = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        delete = new komponen.ButtonGlass();
        clear = new komponen.ButtonGlass();
        save = new komponen.ButtonGlass();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelpayment = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtpemblembur = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtKaryawan = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtgaji = new javax.swing.JTextField();
        txtPotongan = new javax.swing.JTextField();
        txtpembgaji = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txttunjangan1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        tglgajian = new com.toedter.calendar.JDateChooser();
        jPanel9 = new javax.swing.JPanel();
        tcari2 = new javax.swing.JTextField();
        Cari = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        tabLembur = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        hapus = new komponen.ButtonGlass();
        bersihkan = new komponen.ButtonGlass();
        ubah = new komponen.ButtonGlass();
        simpan = new komponen.ButtonGlass();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabellembur = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        txtKar2 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtBayarLembur = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        tnama = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        tcari1 = new javax.swing.JTextField();
        Cari1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTabbedPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane2.setPreferredSize(new java.awt.Dimension(1220, 896));

        tabPayment.setBackground(new java.awt.Color(255, 255, 255));
        tabPayment.setPreferredSize(new java.awt.Dimension(1220, 896));

        jPanel4.setBackground(new java.awt.Color(102, 255, 102));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/trash_24px.png"))); // NOI18N
        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        jPanel4.add(delete);

        clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/broom_32px.png"))); // NOI18N
        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        jPanel4.add(clear);

        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/save_32px.png"))); // NOI18N
        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jPanel4.add(save);

        tabelpayment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelpayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelpaymentMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelpayment);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("ID Karyawan");

        txtpemblembur.setPreferredSize(new java.awt.Dimension(300, 40));

        jLabel16.setText("Total Pembayaran Gaji");

        jLabel5.setText("Potongan");

        jLabel4.setText("Total Pembayaran Lembur");

        txtKaryawan.setPreferredSize(new java.awt.Dimension(200, 40));

        jLabel2.setText("Nama Karayawan");

        txtnama.setPreferredSize(new java.awt.Dimension(300, 30));

        jLabel3.setText("Gaji Pokok");

        txtgaji.setPreferredSize(new java.awt.Dimension(300, 40));

        txtPotongan.setPreferredSize(new java.awt.Dimension(300, 40));

        txtpembgaji.setPreferredSize(new java.awt.Dimension(300, 40));

        jLabel6.setText("Tunjangan");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("CARI");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Tanggal Gajian");

        tglgajian.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tglgajianPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(txtKaryawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton1))
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtnama, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttunjangan1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(696, 696, 696))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tglgajian, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel3))
                            .addComponent(txtgaji, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                            .addComponent(txtpemblembur, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(355, 355, 355))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtpembgaji, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                                        .addComponent(txtPotongan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                                .addContainerGap())))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPotongan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtgaji, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel16)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpembgaji, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpemblembur, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttunjangan1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tglgajian, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        tcari2.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));

        Cari.setText("Cari");

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("PRINT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tcari2, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cari)
                .addGap(277, 277, 277))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(tcari2)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cari)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tabPaymentLayout = new javax.swing.GroupLayout(tabPayment);
        tabPayment.setLayout(tabPaymentLayout);
        tabPaymentLayout.setHorizontalGroup(
            tabPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(tabPaymentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(tabPaymentLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        tabPaymentLayout.setVerticalGroup(
            tabPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPaymentLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Payment", tabPayment);

        tabLembur.setLayout(new java.awt.BorderLayout());

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setPreferredSize(new java.awt.Dimension(1220, 896));

        jPanel15.setBackground(new java.awt.Color(102, 255, 102));
        jPanel15.setLayout(new java.awt.GridLayout(1, 0));

        hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/trash_24px.png"))); // NOI18N
        hapus.setText("Delete");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        jPanel15.add(hapus);

        bersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/broom_32px.png"))); // NOI18N
        bersihkan.setText("Clear");
        bersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bersihkanActionPerformed(evt);
            }
        });
        jPanel15.add(bersihkan);

        ubah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/update_tag_32px.png"))); // NOI18N
        ubah.setText("Update");
        ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahActionPerformed(evt);
            }
        });
        jPanel15.add(ubah);

        simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/save_32px.png"))); // NOI18N
        simpan.setText("Save");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });
        jPanel15.add(simpan);

        tabellembur.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabellembur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabellemburMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tabellembur);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jLabel25.setText("ID Karyawan");

        txtKar2.setPreferredSize(new java.awt.Dimension(200, 40));

        jLabel32.setText("Bayaran Lembur");

        txtBayarLembur.setPreferredSize(new java.awt.Dimension(300, 30));
        txtBayarLembur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBayarLemburActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("CARI");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel26.setText("Nama Karyawan");

        tnama.setPreferredSize(new java.awt.Dimension(200, 40));
        tnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnamaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKar2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tnama, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtBayarLembur, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBayarLembur, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKar2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tnama, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        tcari1.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));

        Cari1.setText("Cari");

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setText("PRINT");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(jButton4)
                .addGap(161, 161, 161)
                .addComponent(tcari1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Cari1)
                .addGap(328, 328, 328))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(tcari1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cari1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1096, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabLembur.add(jPanel14, java.awt.BorderLayout.CENTER);

        jTabbedPane2.addTab("Lembur", tabLembur);

        add(jTabbedPane2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtBayarLemburActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBayarLemburActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBayarLemburActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // TODO add your handling code here:
        String sql = "insert into data_lembur (id_kar,bayaran) values (?,?)";
        try {
            PreparedStatement stat5 = conn.prepareStatement(sql);
            stat5.setString(1, txtKar2.getText());
            stat5.setString(2, txtBayarLembur.getText());
            
            stat5.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            kosonglembur();
            txtKar2.requestFocus();
            datatabelLembur("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan" + e);
        }
    }//GEN-LAST:event_simpanActionPerformed

    private void tabellemburMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabellemburMouseClicked
        // TODO add your handling code here:
        int bar = tabellembur.getSelectedRow();
        String a = tabmodeLembur.getValueAt(bar, 1).toString();
        String b = tabmodeLembur.getValueAt(bar, 2).toString();
        String c = tabmodeLembur.getValueAt(bar, 4).toString();
        tnama.setText("");
        idLembur = c;
        
        txtKar2.setText(a);
        txtBayarLembur.setText(b);

    }//GEN-LAST:event_tabellemburMouseClicked

    private void ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "update data_lembur set bayaran=? where id_lembur=?";
            PreparedStatement stat1 = conn.prepareStatement(sql);
            
            stat1.setString(1, txtBayarLembur.getText());
            stat1.setString(2, idLembur);
            stat1.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            kosonglembur();
            txtKar2.requestFocus();
            datatabelLembur("");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah" + e);
        }
        

    }//GEN-LAST:event_ubahActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String sql = "SELECT a.`nama`, COUNT(*) kondisi FROM `data_karyawan` a\n"
                + "WHERE a.`id_kar`=?";
        PreparedStatement stat1;
        try {
            stat1 = conn.prepareStatement(sql);
            stat1.setString(1, txtKar2.getText());
            
            ResultSet rs = stat1.executeQuery();
            
            while (rs.next()) {
                
                if (rs.getInt("kondisi") == 0) {
                    JOptionPane.showMessageDialog(null, "ID karyawan tidak ditemukan!");
                    tnama.setText("");
                }
                tnama.setText(rs.getString("nama"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataMasterPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null, "hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok == 0) {
            String sql = "delete from data_lembur where id_lembur ='" + idLembur + "'";
            try {
                PreparedStatement stat1 = conn.prepareStatement(sql);
                stat1.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                kosonglembur();
                txtKar2.requestFocus();
                datatabelLembur("");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data gagal dihapus" + e);
            }
        }
    }//GEN-LAST:event_hapusActionPerformed

    private void bersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bersihkanActionPerformed
        // TODO add your handling code here:
        kosonglembur();
        datatabelLembur("");

    }//GEN-LAST:event_bersihkanActionPerformed

    private void tnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tnamaActionPerformed

    private void tabelpaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelpaymentMouseClicked
        // TODO add your handling code here:
        int bar = tabelpayment.getSelectedRow();
        String a = tabmodepayment.getValueAt(bar, 0).toString();
        String b = tabmodepayment.getValueAt(bar, 1).toString();
        String c = tabmodepayment.getValueAt(bar, 2).toString();
        String d = tabmodepayment.getValueAt(bar, 3).toString();
        String e = tabmodepayment.getValueAt(bar, 4).toString();
        String f = tabmodepayment.getValueAt(bar, 5).toString();
        String g = tabmodepayment.getValueAt(bar, 6).toString();
        String h = tabmodepayment.getValueAt(bar, 7).toString();
        
        txtKaryawan.setText(a);
        txtnama.setText(b);
        txttunjangan1.setText(c);
        txtgaji.setText(d);
        txtpemblembur.setText(e);
        txtPotongan.setText(f);
        txtpembgaji.setText(g);
        pay = h;
    }//GEN-LAST:event_tabelpaymentMouseClicked

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        String sql = "insert into data_payment (id_kar,tunjangan,gp,pem_lembur,potongan,tot_gaji, created_date) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement stat7 = conn.prepareStatement(sql);
            stat7.setString(1, txtKaryawan.getText());
            stat7.setString(2, txttunjangan1.getText());
            stat7.setString(3, txtgaji.getText());
            stat7.setString(4, txtpemblembur.getText());
            stat7.setString(5, txtPotongan.getText());
            stat7.setString(6, txtpembgaji.getText());
            
            java.sql.Date date = null;
            
            if (tglgajian.getDate() != null) {
                date = new java.sql.Date(tglgajian.getDate().getTime());
            }
            
            stat7.setDate(7, date);
            
            stat7.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            kosongpayment();
            txtKaryawan.requestFocus();
            datatablepayment("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan" + e);
        }
    }//GEN-LAST:event_saveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String sql = "SELECT \n"
                + "k.`nama`, \n"
                + "IFNULL(t.`total_tun`, 0.00) total_tun, \n"
                + "k.`gp`, \n"
                + "IFNULL((SELECT SUM(l.`bayaran`) FROM `data_lembur` l WHERE l.`id_kar`=k.`id_kar`),0.00) total_lembur,\n"
                + "IFNULL((SELECT (p.`bpjs` + p.`pot_ket` + p.`pot_tam`) FROM `data_potongan` p WHERE p.`id_kar`= k.`id_kar`), 0.00) total_potongan\n"
                + "FROM `data_karyawan` k\n"
                + "LEFT JOIN `data_tunjangan` t ON t.`id_kar` = k.`id_kar`\n"
                + "\n"
                + "\n"
                + "WHERE k.id_kar= ?";
        try {
            PreparedStatement stat7 = conn.prepareStatement(sql);
            stat7.setString(1, txtKaryawan.getText());
            
            ResultSet rs = stat7.executeQuery();
            while (rs.next()) {
                String nama = rs.getString("nama");
                BigDecimal totalTun = rs.getBigDecimal("total_tun");
                BigDecimal gp = rs.getBigDecimal("gp");
                BigDecimal totalLembur = rs.getBigDecimal("total_lembur");
                BigDecimal totalPotongan = rs.getBigDecimal("total_potongan");
                BigDecimal totalGaji = gp.add(totalTun).add(totalLembur).subtract(totalPotongan);
                
                txtnama.setText(nama);
                txttunjangan1.setText(totalTun.toString());
                txtgaji.setText(gp.toString());
                txtpemblembur.setText(totalLembur.toString());
                txtPotongan.setText(totalPotongan.toString());
                txtpembgaji.setText(totalGaji.toString());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan" + e);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
        kosongpayment();
        datatablepayment("");
    }//GEN-LAST:event_clearActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null, "hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok == 0) {
            String sql = "delete from data_payment where id_pay ='" + pay + "'";
            try {
                PreparedStatement stat1 = conn.prepareStatement(sql);
                stat1.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                kosonglembur();
                txtKaryawan.requestFocus();
                datatablepayment("");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data gagal dihapus" + e);
            }
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void tglgajianPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tglgajianPropertyChange
        //
    }//GEN-LAST:event_tglgajianPropertyChange

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            String fileName = "src/report/PaymentReport.jasper";
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("File report tidak ditemukan!");
            }
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file.getPath());
            
            JasperPrint jp = JasperFillManager.fillReport(jasperReport, null, conn);
            JasperViewer.viewReport(jp, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            String fileName = "src/report/LemburReport.jasper";
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("File report tidak ditemukan!");
            }
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file.getPath());
            
            JasperPrint jp = JasperFillManager.fillReport(jasperReport, null, conn);
            JasperViewer.viewReport(jp, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cari;
    private javax.swing.JButton Cari1;
    private komponen.ButtonGlass bersihkan;
    private komponen.ButtonGlass clear;
    private komponen.ButtonGlass delete;
    private komponen.ButtonGlass hapus;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane2;
    private komponen.ButtonGlass save;
    private komponen.ButtonGlass simpan;
    private javax.swing.JPanel tabLembur;
    private javax.swing.JPanel tabPayment;
    private javax.swing.JTable tabellembur;
    private javax.swing.JTable tabelpayment;
    private javax.swing.JTextField tcari1;
    private javax.swing.JTextField tcari2;
    private com.toedter.calendar.JDateChooser tglgajian;
    private javax.swing.JTextField tnama;
    private javax.swing.JTextField txtBayarLembur;
    private javax.swing.JTextField txtKar2;
    private javax.swing.JTextField txtKaryawan;
    private javax.swing.JTextField txtPotongan;
    private javax.swing.JTextField txtgaji;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtpembgaji;
    private javax.swing.JTextField txtpemblembur;
    private javax.swing.JTextField txttunjangan1;
    private komponen.ButtonGlass ubah;
    // End of variables declaration//GEN-END:variables
}
