
package View;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.File;
import java.sql.*;
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
/**
 *
 * @author kelompok 1
 */
public class DataMasterPanel extends javax.swing.JPanel {
    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmodeKaryawan;
    private DefaultTableModel tabmodeAbsen;
    private DefaultTableModel tabmodeCuti;
    private DefaultTableModel tabmodeTunjangan;
    private DefaultTableModel tabmodePotongan;
    public String dateChooser;
    
    
    protected void aktif(){
        txtIdKar.setEnabled(true);
        txtNama.setEnabled(true);
        txtAlamat.setEnabled(true);
        txtIdKar.requestFocus();
    }
    protected void kosong(){
        txtIdKar.setText("");
        txtNama.setText("");
        txtAlamat.setText("");
        rbLaki.setSelected(true);
        jcAgama.setSelectedIndex(0);
        txtEmail.setText("");
        txtGaji1.setText("");
        txtjabatan1.setText("");
        txtcari.setText("");
    } 
    protected void aktif1(){
        txtIdCut.setEnabled(true);
        txtIdKar4.setEnabled(true);
        txtnama.setEnabled(true);
        bstarcut.setEnabled(true);
        bendcut.setEnabled(true);
        txtreason.setEnabled(true);
        txtIdCut.requestFocus();
        
    }
    protected void kosong1(){
        txtIdCut.setText("");
        txtIdKar4.setText("");
        txtnama.setText("");
        bstarcut.setDate(null);
        bendcut.setDate(null);
        txtreason.setText("");
        zcari.setText("");
    }
    protected void aktif2(){
        txtIdtunjangan.setEnabled(true);
        txtIdKar5.setEnabled(true);
        txtnama3.setEnabled(true);
        txttotaltun.setEnabled(true);
        txtIdtunjangan.requestFocus();
        
    }
    protected void kosong2(){
         txtIdtunjangan.setText("");
         txtIdKar5.setText("");
         txtnama3.setText("");
         txttotaltun.setText("");
         
         ccari.setText("");
    }
    protected void aktif3(){
        txtIdpot.setEnabled(true);
        txtKar2.setEnabled(true);
        txtnama1.setEnabled(true);
        txtbpjs.setEnabled(true);
        txtpotketenaga.setEnabled(true);
        txtpottam.setEnabled(true);
        txtIdpot.requestFocus();
    }
    protected void kosong3(){
         txtIdpot.setText("");
         txtKar2.setText("");
         txtnama1.setText("");
         txtbpjs.setText("");
         txtpotketenaga.setText("");
         txtpottam.setText("");
         dcari.setText("");
         
    }
    protected void aktif4(){
        txtIdabsen.setEnabled(true);
        txtKaryawan.setEnabled(true);
        txtnama2.setEnabled(true);
        txtin.setEnabled(true);
        txtout.setEnabled(true);
        txtstatus.setEnabled(true);
        txtIdabsen.requestFocus();
    }
    protected void kosong4(){
         txtIdabsen.setText("");
         txtKaryawan.setText("");
         txtnama2.setText("");
         txtin.setText("");
         txtout.setText("");
         txtpottam.setText("");
         ecari.setText("");
         
    }
    protected void datatable(String name){
    Object [] Baris = {"ID Karyawan","Nama","Jenis Kelamin","Agama","Email","Gaji Pokok","Alamat","Jabatan"};
    tabmodeKaryawan = new DefaultTableModel(null, Baris);
    tabeldataKaryawan.setModel(tabmodeKaryawan);
    String sql =  "SELECT a.*, b.nama FROM `data_karyawan` a\n" +
                "INNER JOIN `data_karyawan` b ON b.`id_kar` = a.`id_kar`\n" +
                "WHERE b.`nama` LIKE '%"+name+"%'";
    try {
        java.sql.Statement stat1 = conn.createStatement();
        ResultSet hasil = stat1.executeQuery(sql);
        while(hasil.next()){
            String a = hasil.getString("id_kar");
            String b = hasil.getString("nama");
            String c = hasil.getString("jk");
            String d = hasil.getString("agama");
            String e = hasil.getString("email");
            String f = hasil.getString("gp");
            String g = hasil.getString("alamat");
            String h = hasil.getString("jabatan");
            
            String[] data={a,b,c,d,e,f,g,h};
            tabmodeKaryawan.addRow(data);
        }
    } catch (Exception e){
        
    }
    }
    protected void datatablecuti(String name){
        Object[] Baris = {"ID Cuti","ID Karyawan","Star Cuti","End Cuti","Reason","Nama Karyawan"};
        tabmodeCuti = new DefaultTableModel(null, Baris);
        tabelcuti.setModel(tabmodeCuti);
        String sql = "SELECT a.*, b.nama FROM `data_cuti` a\n" +
                "INNER JOIN `data_karyawan` b ON b.`id_kar` = a.`id_kar`\n" +
                "WHERE b.`nama` LIKE '%"+name+"%'";
        
        try{
        java.sql.Statement stat1 = conn.createStatement();
        ResultSet hasil = stat1.executeQuery(sql);
        while(hasil.next()){
            String a = hasil.getString("id_cuti");
            String b = hasil.getString("id_kar");
            String c = hasil.getString("start_cuti");
            String d = hasil.getString("end_cuti");
            String e = hasil.getString("reason");
            String f = hasil.getString("nama");
            
            String[] data1={a,b,c,d,e,f};
            tabmodeCuti.addRow(data1);
        }
        }catch (Exception e){
    }
    }
    protected void datatabeltunjangan(String name){
        Object[] baris= {"ID Tunjangan","ID Karyawan","Total Tunjangan","Nama Karyawan"};
        tabmodeTunjangan = new DefaultTableModel(null, baris);
        tabeltunjangan.setModel(tabmodeTunjangan);
        String sql = "SELECT a.*, b.nama FROM `data_tunjangan` a\n" +
                "INNER JOIN `data_karyawan` b ON b.`id_kar` = a.`id_kar`\n" +
                "WHERE b.`nama` LIKE '%"+name+"%'";
        try{
            java.sql.Statement stat2 = conn.createStatement();
            ResultSet hasil = stat2.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("id_tunjangan");
                String b =hasil.getString("id_kar");
                String c = hasil.getString("total_tun");
                String d = hasil.getString("nama");
                
                String[] data2={a,b,c,d};
                tabmodeTunjangan.addRow(data2);
            }
        }catch (Exception e){
        }
    }
protected void datatabelpotongan(String name){
        Object[] bariss= {"ID Potongan","ID Karyawan","BPJS Kesehatan","Potongan Ketenaga Kerjaan","Potongan Tambahan","Nama Karyawan"};
        tabmodePotongan = new DefaultTableModel(null, bariss);
        tabelpotongan.setModel(tabmodePotongan);
         String sql = "SELECT a.*, b.nama FROM `data_potongan` a\n" +
                "INNER JOIN `data_karyawan` b ON b.`id_kar` = a.`id_kar`\n" +
                "WHERE b.`nama` LIKE '%"+name+"%'";
        try{
            java.sql.Statement stat3 = conn.createStatement();
            ResultSet hasil = stat3.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("id_pot");
                String b = hasil.getString("id_kar");
                String c = hasil.getString("bpjs");
                String d = hasil.getString("pot_ket");
                String e = hasil.getString("pot_tam");
                String f = hasil.getString("nama");
                
                String[] data3={a,b,c,d,e,f};
                tabmodePotongan.addRow(data3);
            }
        }catch (Exception e){
        }
    }
protected void datatabelabsen(String name){
        Object[] bariss= {"ID Absen","ID Karyawan","IN","OUT","Status", "Nama Karyawan"};
        tabmodeAbsen = new DefaultTableModel(null, bariss);
        tabelabsen.setModel(tabmodeAbsen);
        String sql = sql = "SELECT a.*, b.nama FROM `data_absen` a\n" +
                "INNER JOIN `data_karyawan` b ON b.`id_kar` = a.`id_kar`\n" +
                "WHERE b.`nama` LIKE '%"+name+"%'";;
       
        
        try{
            java.sql.Statement stat4 = conn.createStatement();
            ResultSet hasil = stat4.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("id_absen");
                String b = hasil.getString("id_kar");
                String c = hasil.getString("masuk");
                String d = hasil.getString("keluar");
                String e = hasil.getString("status");
                String f = hasil.getString("nama");
                
                String[] data4={a,b,c,d,e, f};
                tabmodeAbsen.addRow(data4);
            }
        }catch (Exception e){
        }
    }
    /**
     * Creates new form HomePanel
     */
    public DataMasterPanel() {
        initComponents();
        datatable("");
        datatablecuti("");
        datatabeltunjangan("");
        datatabelpotongan("");
        datatabelabsen("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupJk = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        tabKaryawan = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        adelete = new komponen.ButtonGlass();
        aclear = new komponen.ButtonGlass();
        aupdate = new komponen.ButtonGlass();
        asave = new komponen.ButtonGlass();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabeldataKaryawan = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        txtEmail = new javax.swing.JTextField();
        jcAgama = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtIdKar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        rbLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        txtjabatan1 = new javax.swing.JTextField();
        txtGaji1 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        txtcari = new javax.swing.JTextField();
        Cari5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        tabCuti = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        cdelet = new komponen.ButtonGlass();
        cclear = new komponen.ButtonGlass();
        cupdate = new komponen.ButtonGlass();
        csave = new komponen.ButtonGlass();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabelcuti = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtreason = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtIdCut = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        bstarcut = new com.toedter.calendar.JDateChooser();
        bendcut = new com.toedter.calendar.JDateChooser();
        jLabel41 = new javax.swing.JLabel();
        txtIdKar4 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        zcari = new javax.swing.JTextField();
        Cari4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        tabTunjangan = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        ddelete = new komponen.ButtonGlass();
        dclear = new komponen.ButtonGlass();
        dupdate = new komponen.ButtonGlass();
        dsave = new komponen.ButtonGlass();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabeltunjangan = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        txtIdtunjangan = new javax.swing.JTextField();
        txttotaltun = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txtIdKar5 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtnama3 = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        ccari = new javax.swing.JTextField();
        Cari3 = new javax.swing.JButton();
        tabPotongan = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        gdelete = new komponen.ButtonGlass();
        gclear = new komponen.ButtonGlass();
        gupdate = new komponen.ButtonGlass();
        gsave = new komponen.ButtonGlass();
        jScrollPane10 = new javax.swing.JScrollPane();
        tabelpotongan = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        txtpotketenaga = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txtIdpot = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txtKar2 = new javax.swing.JTextField();
        txtpottam = new javax.swing.JTextField();
        txtbpjs = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtnama1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        dcari = new javax.swing.JTextField();
        Cari2 = new javax.swing.JButton();
        tababsen = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        sdelete = new komponen.ButtonGlass();
        sclear = new komponen.ButtonGlass();
        supdate = new komponen.ButtonGlass();
        ssave = new komponen.ButtonGlass();
        jScrollPane12 = new javax.swing.JScrollPane();
        tabelabsen = new javax.swing.JTable();
        jPanel24 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        txtIdabsen = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtstatus = new javax.swing.JTextField();
        txtin = new javax.swing.JTextField();
        txtout = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtnama2 = new javax.swing.JTextField();
        txtKaryawan = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        ecari = new javax.swing.JTextField();
        Cari1 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(1220, 896));
        setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1220, 896));

        tabKaryawan.setBackground(new java.awt.Color(255, 255, 255));
        tabKaryawan.setPreferredSize(new java.awt.Dimension(1220, 896));

        jPanel4.setBackground(new java.awt.Color(102, 255, 102));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        adelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/trash_24px.png"))); // NOI18N
        adelete.setText("Delete");
        adelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adeleteActionPerformed(evt);
            }
        });
        jPanel4.add(adelete);

        aclear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/broom_32px.png"))); // NOI18N
        aclear.setText("Clear");
        aclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aclearActionPerformed(evt);
            }
        });
        jPanel4.add(aclear);

        aupdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/update_tag_32px.png"))); // NOI18N
        aupdate.setText("Update");
        aupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aupdateActionPerformed(evt);
            }
        });
        jPanel4.add(aupdate);

        asave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/save_32px.png"))); // NOI18N
        asave.setText("Save");
        asave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asaveActionPerformed(evt);
            }
        });
        jPanel4.add(asave);

        tabeldataKaryawan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabeldataKaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabeldataKaryawanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabeldataKaryawan);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("ID Karyawan");

        txtAlamat.setColumns(20);
        txtAlamat.setRows(5);
        txtAlamat.setPreferredSize(new java.awt.Dimension(350, 40));
        jScrollPane5.setViewportView(txtAlamat);

        txtEmail.setPreferredSize(new java.awt.Dimension(300, 40));

        jcAgama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Islam", "Protestan", "Katolik", "Hindu", "Budha", "Khonghucu" }));
        jcAgama.setPreferredSize(new java.awt.Dimension(89, 40));

        jLabel16.setText("Jabatan");

        jLabel5.setText("Alamat");

        jLabel7.setText("Gaji Pokok");

        jLabel6.setText("Email");

        jLabel4.setText("Agama");

        txtIdKar.setPreferredSize(new java.awt.Dimension(200, 40));

        jLabel2.setText("Nama");

        txtNama.setPreferredSize(new java.awt.Dimension(300, 30));
        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        jLabel3.setText("Jenis Kelamin");

        rbLaki.setBackground(new java.awt.Color(255, 255, 255));
        btnGroupJk.add(rbLaki);
        rbLaki.setText("Laki-Laki");

        rbPerempuan.setBackground(new java.awt.Color(255, 255, 255));
        btnGroupJk.add(rbPerempuan);
        rbPerempuan.setText("Perempuan");
        rbPerempuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPerempuanActionPerformed(evt);
            }
        });

        txtjabatan1.setPreferredSize(new java.awt.Dimension(300, 40));

        txtGaji1.setPreferredSize(new java.awt.Dimension(300, 40));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNama, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                            .addComponent(txtIdKar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(rbLaki, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbPerempuan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                            .addComponent(jcAgama, 0, 345, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtGaji1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                    .addComponent(txtjabatan1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
                .addGap(54, 54, 54))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(52, 52, 52)
                        .addComponent(jLabel5))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtjabatan1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcAgama, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdKar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtGaji1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(rbLaki, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(rbPerempuan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        txtcari.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));

        Cari5.setText("Cari");
        Cari5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cari5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setText("PRINT");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jButton6)
                .addGap(204, 204, 204)
                .addComponent(txtcari, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cari5)
                .addGap(332, 332, 332))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cari5)
                .addContainerGap())
        );

        javax.swing.GroupLayout tabKaryawanLayout = new javax.swing.GroupLayout(tabKaryawan);
        tabKaryawan.setLayout(tabKaryawanLayout);
        tabKaryawanLayout.setHorizontalGroup(
            tabKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(tabKaryawanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        tabKaryawanLayout.setVerticalGroup(
            tabKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabKaryawanLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Data Karyawan", tabKaryawan);

        tabCuti.setLayout(new java.awt.BorderLayout());

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(1220, 896));

        jPanel11.setBackground(new java.awt.Color(102, 255, 102));
        jPanel11.setLayout(new java.awt.GridLayout(1, 0));

        cdelet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/trash_24px.png"))); // NOI18N
        cdelet.setText("Delete");
        cdelet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cdeletActionPerformed(evt);
            }
        });
        jPanel11.add(cdelet);

        cclear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/broom_32px.png"))); // NOI18N
        cclear.setText("Clear");
        cclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cclearActionPerformed(evt);
            }
        });
        jPanel11.add(cclear);

        cupdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/update_tag_32px.png"))); // NOI18N
        cupdate.setText("Update");
        cupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cupdateActionPerformed(evt);
            }
        });
        jPanel11.add(cupdate);

        csave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/save_32px.png"))); // NOI18N
        csave.setText("Save");
        csave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                csaveActionPerformed(evt);
            }
        });
        jPanel11.add(csave);

        tabelcuti.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelcuti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelcutiMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tabelcuti);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setText("ID Cuti");

        txtreason.setPreferredSize(new java.awt.Dimension(350, 40));

        jLabel22.setText("Reason");

        txtIdCut.setPreferredSize(new java.awt.Dimension(350, 40));
        txtIdCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdCutActionPerformed(evt);
            }
        });

        jLabel23.setText("Start Cuti");

        jLabel24.setText("End Cuti");

        bstarcut.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                bstarcutPropertyChange(evt);
            }
        });

        bendcut.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                bendcutPropertyChange(evt);
            }
        });

        jLabel41.setText("ID Karyawan");

        txtIdKar4.setPreferredSize(new java.awt.Dimension(350, 40));

        jLabel11.setText("Nama Karyawan");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("CARI");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtnama)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtIdCut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtIdKar4, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bendcut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel23)
                    .addComponent(bstarcut, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(txtreason, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
                .addGap(114, 114, 114))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdCut, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bstarcut, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtreason, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bendcut, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdKar4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnama, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        zcari.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));

        Cari4.setText("Cari");
        Cari4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cari4ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setText("PRINT");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addComponent(jButton7)
                .addGap(162, 162, 162)
                .addComponent(zcari, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Cari4)
                .addGap(313, 313, 313))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zcari, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cari4)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabCuti.add(jPanel10, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Data Cuti", tabCuti);

        tabTunjangan.setLayout(new java.awt.BorderLayout());

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setPreferredSize(new java.awt.Dimension(1220, 896));

        jPanel15.setBackground(new java.awt.Color(102, 255, 102));
        jPanel15.setLayout(new java.awt.GridLayout(1, 0));

        ddelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/trash_24px.png"))); // NOI18N
        ddelete.setText("Delete");
        ddelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ddeleteActionPerformed(evt);
            }
        });
        jPanel15.add(ddelete);

        dclear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/broom_32px.png"))); // NOI18N
        dclear.setText("Clear");
        dclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dclearActionPerformed(evt);
            }
        });
        jPanel15.add(dclear);

        dupdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/update_tag_32px.png"))); // NOI18N
        dupdate.setText("Update");
        dupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dupdateActionPerformed(evt);
            }
        });
        jPanel15.add(dupdate);

        dsave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/save_32px.png"))); // NOI18N
        dsave.setText("Save");
        dsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dsaveActionPerformed(evt);
            }
        });
        jPanel15.add(dsave);

        tabeltunjangan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabeltunjangan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabeltunjanganMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tabeltunjangan);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jLabel25.setText("ID Tunjangan");

        txtIdtunjangan.setPreferredSize(new java.awt.Dimension(200, 40));

        txttotaltun.setPreferredSize(new java.awt.Dimension(300, 30));

        jLabel32.setText("Total Tunjangan");

        jLabel43.setText("ID Karyawan");

        txtIdKar5.setPreferredSize(new java.awt.Dimension(350, 40));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setText("CARI");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel14.setText("Nama Karyawan");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(txtIdKar5, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtIdtunjangan, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addContainerGap(707, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txttotaltun, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtnama3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(429, 429, 429))))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnama3))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdtunjangan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdKar5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttotaltun, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        ccari.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));

        Cari3.setText("Cari");
        Cari3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cari3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(423, 423, 423)
                .addComponent(ccari, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Cari3)
                .addGap(339, 339, 339))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ccari, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cari3)
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
                    .addComponent(jScrollPane8)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabTunjangan.add(jPanel14, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Data Tunjangan", tabTunjangan);

        tabPotongan.setLayout(new java.awt.BorderLayout());

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setPreferredSize(new java.awt.Dimension(1220, 896));

        jPanel19.setBackground(new java.awt.Color(102, 255, 102));
        jPanel19.setLayout(new java.awt.GridLayout(1, 0));

        gdelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/trash_24px.png"))); // NOI18N
        gdelete.setText("Delete");
        gdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gdeleteActionPerformed(evt);
            }
        });
        jPanel19.add(gdelete);

        gclear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/broom_32px.png"))); // NOI18N
        gclear.setText("Clear");
        gclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gclearActionPerformed(evt);
            }
        });
        jPanel19.add(gclear);

        gupdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/update_tag_32px.png"))); // NOI18N
        gupdate.setText("Update");
        gupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gupdateActionPerformed(evt);
            }
        });
        jPanel19.add(gupdate);

        gsave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/save_32px.png"))); // NOI18N
        gsave.setText("Save");
        gsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gsaveActionPerformed(evt);
            }
        });
        jPanel19.add(gsave);

        tabelpotongan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelpotongan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelpotonganMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tabelpotongan);

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        jLabel33.setText("ID Potongan");

        txtpotketenaga.setPreferredSize(new java.awt.Dimension(300, 40));

        jLabel37.setText("Potongan Ketenaga Kerjaan");

        jLabel38.setText("BPJS Kesehatan");

        txtIdpot.setPreferredSize(new java.awt.Dimension(200, 40));

        jLabel39.setText("ID Karyawan");

        txtKar2.setPreferredSize(new java.awt.Dimension(300, 30));

        txtpottam.setPreferredSize(new java.awt.Dimension(300, 40));

        txtbpjs.setPreferredSize(new java.awt.Dimension(300, 40));

        jLabel40.setText("Potongan Tambahan");

        jLabel12.setText("Nama Karyawan");

        txtnama1.setPreferredSize(new java.awt.Dimension(300, 30));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("CARI");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(431, 431, 431))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdpot, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel39)
                                    .addGroup(jPanel20Layout.createSequentialGroup()
                                        .addComponent(txtKar2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpotketenaga, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                            .addComponent(txtbpjs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addComponent(txtpottam, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE))
                        .addGap(104, 104, 104))))
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnama1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdpot, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtbpjs, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtpottam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtKar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtpotketenaga, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel38)
                        .addComponent(jLabel40)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnama1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        dcari.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));

        Cari2.setText("Cari");
        Cari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cari2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(345, 345, 345)
                .addComponent(dcari, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Cari2)
                .addGap(406, 406, 406))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dcari, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cari2)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane10)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabPotongan.add(jPanel18, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Data Potongan", tabPotongan);

        tababsen.setLayout(new java.awt.BorderLayout());

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setPreferredSize(new java.awt.Dimension(1220, 896));

        jPanel23.setBackground(new java.awt.Color(102, 255, 102));
        jPanel23.setLayout(new java.awt.GridLayout(1, 0));

        sdelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/trash_24px.png"))); // NOI18N
        sdelete.setText("Delete");
        sdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sdeleteActionPerformed(evt);
            }
        });
        jPanel23.add(sdelete);

        sclear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/broom_32px.png"))); // NOI18N
        sclear.setText("Clear");
        sclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sclearActionPerformed(evt);
            }
        });
        jPanel23.add(sclear);

        supdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/update_tag_32px.png"))); // NOI18N
        supdate.setText("Update");
        supdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supdateActionPerformed(evt);
            }
        });
        jPanel23.add(supdate);

        ssave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/save_32px.png"))); // NOI18N
        ssave.setText("Save");
        ssave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ssaveActionPerformed(evt);
            }
        });
        jPanel23.add(ssave);

        tabelabsen.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelabsen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelabsenMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tabelabsen);

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        jLabel42.setText("ID Absen");

        txtIdabsen.setPreferredSize(new java.awt.Dimension(200, 40));
        txtIdabsen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdabsenActionPerformed(evt);
            }
        });

        jLabel50.setText("ID Karyawan");

        jLabel8.setText("IN");

        jLabel9.setText("OUT");

        jLabel10.setText("Status");

        jLabel13.setText("Nama Karyawan");

        txtnama2.setPreferredSize(new java.awt.Dimension(300, 30));
        txtnama2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnama2ActionPerformed(evt);
            }
        });

        txtKaryawan.setPreferredSize(new java.awt.Dimension(350, 40));

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setText("CARI");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel50)))
                        .addGap(0, 274, Short.MAX_VALUE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdabsen, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel24Layout.createSequentialGroup()
                                        .addComponent(txtKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton5))
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtout)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel24Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txtstatus, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
                        .addGap(39, 39, 39))))
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtnama2, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(67, 190, Short.MAX_VALUE))
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addComponent(jLabel8))
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdabsen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtout, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnama2, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        txtin.getAccessibleContext().setAccessibleDescription("");

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));

        ecari.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));

        Cari1.setText("Cari");
        Cari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cari1ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton8.setText("PRINT");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton9.setText("PRINT");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(jButton9)
                .addGap(150, 150, 150)
                .addComponent(ecari, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cari1)
                .addGap(332, 332, 332))
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel25Layout.createSequentialGroup()
                    .addGap(562, 562, 562)
                    .addComponent(jButton8)
                    .addContainerGap(562, Short.MAX_VALUE)))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(ecari, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cari1)
                .addContainerGap())
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel25Layout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane12)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tababsen.add(jPanel22, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("DataAbsen", tababsen);

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);
        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void Cari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cari1ActionPerformed
        // TODO add your handling code here:
        datatabelabsen(ecari.getText());
    }//GEN-LAST:event_Cari1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String sql = "SELECT a.`nama`, COUNT(*) kondisi FROM `data_karyawan` a\n" +
        "WHERE a.`id_kar`=?";
        PreparedStatement stat1;
        try {
            stat1 = conn.prepareStatement(sql);
            stat1.setString(1, txtKaryawan.getText());

            ResultSet rs = stat1.executeQuery();

            while(rs.next()) {

                if(rs.getInt("kondisi") == 0){
                    JOptionPane.showMessageDialog(null, "ID karyawan tidak ditemukan!");
                    txtnama2.setText("");
                }
                txtnama2.setText(rs.getString("nama"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataMasterPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtnama2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnama2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnama2ActionPerformed

    private void txtIdabsenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdabsenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdabsenActionPerformed

    private void tabelabsenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelabsenMouseClicked
        int bar = tabelabsen.getSelectedRow();
        String a = tabmodeAbsen.getValueAt(bar, 0) .toString();
        String b = tabmodeAbsen.getValueAt(bar, 1) .toString();
        String c = tabmodeAbsen.getValueAt(bar, 2) .toString();
        String d = tabmodeAbsen.getValueAt(bar, 3) .toString();
        String e = tabmodeAbsen.getValueAt(bar, 4) .toString();

        txtIdabsen.setText(a);
        txtKaryawan.setText(b);
        txtin.setText(c);
        txtout.setText(d);
        txtstatus.setText(e);
    }//GEN-LAST:event_tabelabsenMouseClicked

    private void ssaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ssaveActionPerformed
        String sql= "insert into data_absen values (?,?,?,?,?)";
        try{
            PreparedStatement stat4 = conn.prepareStatement(sql);
            stat4.setString(1, txtIdabsen.getText());
            stat4.setString(2, txtKaryawan.getText());
            stat4.setString(3, txtin.getText());
            stat4.setString(4, txtout.getText());
            stat4.setString(5, txtstatus.getText());
            stat4.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            kosong4();
            txtIdabsen.requestFocus();
            datatabelabsen("");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan"+e);
        }
    }//GEN-LAST:event_ssaveActionPerformed

    private void supdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supdateActionPerformed
        try{
            String sql = "update data_absen set id_kar=?,masuk=?,keluar=?,status=? where id_absen=?";
            PreparedStatement stat1 = conn.prepareStatement(sql);
            stat1.setString(1, txtKaryawan.getText());
            stat1.setString(2, txtin.getText());
            stat1.setString(3, txtout.getText());
            stat1.setString(4, txtstatus.getText());
            stat1.setString(5, txtIdabsen.getText());
            stat1.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            kosong1();
            txtIdabsen.requestFocus();
            datatabelabsen("");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah"+e);
        }
    }//GEN-LAST:event_supdateActionPerformed

    private void sclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sclearActionPerformed
        kosong4();
        datatabelabsen("");
    }//GEN-LAST:event_sclearActionPerformed

    private void sdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sdeleteActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null,"hapus","Konfirmasi Dialog",JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok==0){
            String sql ="delete from data_absen where id_absen ='"+txtIdabsen.getText()+"'";
            try{
                PreparedStatement stat4 = conn.prepareStatement (sql);
                stat4.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                kosong4();
                txtIdabsen.requestFocus();
                datatabelabsen("");
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data gagal dihapus"+e);
            }
        }
    }//GEN-LAST:event_sdeleteActionPerformed

    private void Cari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cari2ActionPerformed
        // TODO add your handling code here:
        datatabelpotongan(dcari.getText());
    }//GEN-LAST:event_Cari2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String sql = "SELECT a.`nama`, COUNT(*) kondisi FROM `data_karyawan` a\n" +
        "WHERE a.`id_kar`=?";
        PreparedStatement stat1;
        try {
            stat1 = conn.prepareStatement(sql);
            stat1.setString(1, txtKar2.getText());

            ResultSet rs = stat1.executeQuery();

            while(rs.next()) {

                if(rs.getInt("kondisi") == 0){
                    JOptionPane.showMessageDialog(null, "ID karyawan tidak ditemukan!");
                    txtnama1.setText("");
                }
                txtnama1.setText(rs.getString("nama"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataMasterPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tabelpotonganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelpotonganMouseClicked
        int bar = tabelpotongan.getSelectedRow();
        String a = tabmodePotongan.getValueAt(bar, 0) .toString();
        String b = tabmodePotongan.getValueAt(bar, 1) .toString();
        String c = tabmodePotongan.getValueAt(bar, 2) .toString();
        String d = tabmodePotongan.getValueAt(bar, 3) .toString();
        String e = tabmodePotongan.getValueAt(bar, 4) .toString();

        txtIdpot.setText(a);
        txtKar2.setText(b);
        txtbpjs.setText(c);
        txtpotketenaga.setText(d);
        txtpottam.setText(e);
    }//GEN-LAST:event_tabelpotonganMouseClicked

    private void gsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gsaveActionPerformed
        String sql= "insert into data_potongan values (?,?,?,?,?)";
        try{
            PreparedStatement stat3 = conn.prepareStatement(sql);
            stat3.setString(1, txtIdpot.getText());
            stat3.setString(2, txtKar2.getText());
            stat3.setString(3, txtbpjs.getText());
            stat3.setString(4, txtpotketenaga.getText());
            stat3.setString(5, txtpottam.getText());
            stat3.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            kosong3();
            txtIdpot.requestFocus();
            datatabelpotongan("");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah"+e);
        }
    }//GEN-LAST:event_gsaveActionPerformed

    private void gupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gupdateActionPerformed
        try{
            String sql = "update data_potongan set id_kar=?,bpjs=?,pot_ket=?,pot_tam=? where id_pot=?";
            PreparedStatement stat3 = conn.prepareStatement(sql);
            stat3.setString(1, txtKar2.getText());
            stat3.setString(2, txtbpjs.getText());
            stat3.setString(3, txtpotketenaga.getText());
            stat3.setString(4, txtpottam.getText());
            stat3.setString(5, txtIdpot.getText());
            stat3.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            kosong3();
            txtIdpot.requestFocus();
            datatabelpotongan("");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah"+e);
        }
    }//GEN-LAST:event_gupdateActionPerformed

    private void gclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gclearActionPerformed
        kosong3();
        datatabelpotongan("");
    }//GEN-LAST:event_gclearActionPerformed

    private void gdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gdeleteActionPerformed
        int ok = JOptionPane.showConfirmDialog(null,"hapus","Konfirmasi Dialog",JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok==0){
            String sql ="delete from data_potongan where id_pot ='"+txtIdpot.getText()+"'";
            try{
                PreparedStatement stat3 = conn.prepareStatement (sql);
                stat3.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                kosong3();
                txtIdpot.requestFocus();
                datatabelpotongan("");
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data gagal dihapus"+e);
            }
        }
    }//GEN-LAST:event_gdeleteActionPerformed

    private void Cari3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cari3ActionPerformed
        // TODO add your handling code here:
        datatabeltunjangan(ccari.getText());
    }//GEN-LAST:event_Cari3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String sql = "SELECT a.`nama`, COUNT(*) kondisi FROM `data_karyawan` a\n" +
        "WHERE a.`id_kar`=?";
        PreparedStatement stat1;
        try {
            stat1 = conn.prepareStatement(sql);
            stat1.setString(1, txtIdKar5.getText());

            ResultSet rs = stat1.executeQuery();

            while(rs.next()) {

                if(rs.getInt("kondisi") == 0){
                    JOptionPane.showMessageDialog(null, "ID karyawan tidak ditemukan!");
                    txtnama3.setText("");
                }
                txtnama3.setText(rs.getString("nama"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataMasterPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tabeltunjanganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabeltunjanganMouseClicked
        int bar= tabeltunjangan.getSelectedRow();
        String a = tabmodeTunjangan.getValueAt(bar, 0) .toString();
        String b = tabmodeTunjangan.getValueAt(bar, 1) .toString();
        String c = tabmodeTunjangan.getValueAt(bar, 2) .toString();

        txtIdtunjangan.setText(a);
        txtIdKar5.setText(b);
        txttotaltun.setText(c);
    }//GEN-LAST:event_tabeltunjanganMouseClicked

    private void dsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dsaveActionPerformed
        String sql= "insert into data_tunjangan values (?,?,?)";
        try{
            PreparedStatement stat2 = conn.prepareStatement(sql);
            stat2.setString(1, txtIdtunjangan.getText());
            stat2.setString(2, txtIdKar5.getText());
            stat2.setString(3, txttotaltun.getText());

            stat2.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            kosong2();
            txtIdtunjangan.requestFocus();
            datatabeltunjangan("");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan"+e);
        }
    }//GEN-LAST:event_dsaveActionPerformed

    private void dupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dupdateActionPerformed
        try{
            String sql = "update data_tunjangan set total_tun=? where id_tunjangan=?";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txttotaltun.getText());
            stat.setString(2, txtIdtunjangan.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            kosong2();
            txtIdtunjangan.requestFocus();
            datatabeltunjangan("");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah"+e);
        }
    }//GEN-LAST:event_dupdateActionPerformed

    private void dclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dclearActionPerformed
        kosong2();
        datatabeltunjangan("");        // TODO add your handling code here:
    }//GEN-LAST:event_dclearActionPerformed

    private void ddeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ddeleteActionPerformed
        int ok = JOptionPane.showConfirmDialog(null,"hapus","Konfirmasi Dialog",JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok==0){
            String sql ="delete from data_tunjangan where id_tunjangan ='"+txtIdtunjangan.getText()+"'";
            try{
                PreparedStatement stat2 = conn.prepareStatement (sql);
                stat2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                kosong2();
                txtIdtunjangan.requestFocus();
                datatabeltunjangan("");
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data gagal dihapus"+e);
            }
        }
    }//GEN-LAST:event_ddeleteActionPerformed

    private void Cari4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cari4ActionPerformed
        // TODO add your handling code here:
        datatablecuti(zcari.getText());
    }//GEN-LAST:event_Cari4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String sql = "SELECT a.`nama`, COUNT(*) kondisi FROM `data_karyawan` a\n" +
        "WHERE a.`id_kar`=?";
        PreparedStatement stat1;
        try {
            stat1 = conn.prepareStatement(sql);
            stat1.setString(1, txtIdKar4.getText());

            ResultSet rs = stat1.executeQuery();

            while(rs.next()) {

                if(rs.getInt("kondisi") == 0){
                    JOptionPane.showMessageDialog(null, "ID karyawan tidak ditemukan!");
                    txtnama.setText("");
                }
                txtnama.setText(rs.getString("nama"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataMasterPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bendcutPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_bendcutPropertyChange
        //
    }//GEN-LAST:event_bendcutPropertyChange

    private void bstarcutPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_bstarcutPropertyChange
        //
    }//GEN-LAST:event_bstarcutPropertyChange

    private void txtIdCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdCutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdCutActionPerformed

    private void tabelcutiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelcutiMouseClicked
        int bar= tabelcuti.getSelectedRow();
        String a = tabmodeCuti.getValueAt(bar, 0) .toString();
        String b = tabmodeCuti.getValueAt(bar, 1) .toString();
        String c = tabmodeCuti.getValueAt(bar, 2) .toString();
        String d = tabmodeCuti.getValueAt(bar, 3) .toString();
        String e = tabmodeCuti.getValueAt(bar, 4) .toString();

        txtIdCut.setText(a);
        txtIdKar4.setText(b);
        bstarcut.setDateFormatString(c);
        bendcut.setDateFormatString(d);
        txtreason.setText(e);
    }//GEN-LAST:event_tabelcutiMouseClicked

    private void csaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csaveActionPerformed
        String sql = "insert into data_cuti values (?, ?, ?, ?, ?)";
        try{
            PreparedStatement stat1 = conn.prepareStatement(sql);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy hh:mm");

            stat1.setString(1, txtIdCut.getText());
            stat1.setString(2, txtIdKar4.getText());
            stat1.setString(3, sdf.format(bstarcut.getDate()));
            stat1.setString(4, sdf.format(bendcut.getDate()));
            stat1.setString(5, txtreason.getText());

            stat1.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            kosong1();
            txtIdCut.requestFocus();
            datatablecuti("");

        }   catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan");
        }
    }//GEN-LAST:event_csaveActionPerformed

    private void cupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cupdateActionPerformed
        try{
            String sql = "update data_cuti set id_kar=?,start_cuti=?,end_cuti=?,reason=? where id_cuti=?";
            PreparedStatement stat1 = conn.prepareStatement(sql);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy hh:mm");

            stat1.setString(1, txtIdKar4.getText());
            stat1.setString(2, sdf.format(bstarcut.getDate()));
            stat1.setString(3, sdf.format(bendcut.getDate()));
            stat1.setString(4, txtreason.getText());
            stat1.setString(5, txtIdCut.getText());
            stat1.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            kosong1();
            txtIdCut.requestFocus();
            datatablecuti("");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah"+e);
        }
    }//GEN-LAST:event_cupdateActionPerformed

    private void cclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cclearActionPerformed
        kosong1();
        datatablecuti("");
    }//GEN-LAST:event_cclearActionPerformed

    private void cdeletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cdeletActionPerformed
        int ok = JOptionPane.showConfirmDialog(null,"hapus","Konfirmasi Dialog",JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok==0){
            String sql ="delete from data_cuti where id_cuti ='"+txtIdCut.getText()+"'";
            try{
                PreparedStatement stat1 = conn.prepareStatement (sql);
                stat1.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                kosong1();
                txtIdCut.requestFocus();
                datatablecuti("");
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data gagal dihapus"+e);
            }
        }
    }//GEN-LAST:event_cdeletActionPerformed

    private void Cari5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cari5ActionPerformed
        // TODO add your handling code here:
        datatable(txtcari.getText());
    }//GEN-LAST:event_Cari5ActionPerformed

    private void rbPerempuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPerempuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbPerempuanActionPerformed

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void tabeldataKaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabeldataKaryawanMouseClicked
        int bar= tabeldataKaryawan.getSelectedRow();
        String a = tabmodeKaryawan.getValueAt(bar, 0) .toString();
        String b = tabmodeKaryawan.getValueAt(bar, 1) .toString();
        String c = tabmodeKaryawan.getValueAt(bar, 2) .toString();
        String d = tabmodeKaryawan.getValueAt(bar, 3) .toString();
        String e = tabmodeKaryawan.getValueAt(bar, 4) .toString();
        String f = tabmodeKaryawan.getValueAt(bar, 5) .toString();
        String g = tabmodeKaryawan.getValueAt(bar, 6) .toString();
        String h = "";

        if(tabmodeKaryawan.getValueAt(bar, 7) != null)
        h = tabmodeKaryawan.getValueAt(bar, 7).toString();

        txtIdKar.setText(a);
        txtNama.setText(b);

        if(c.equals("Laki-Laki")){
            rbLaki.setSelected(true); rbPerempuan.setSelected(false);
        } else{
            rbLaki.setSelected(false); rbPerempuan.setSelected(true);
        }

        jcAgama.setSelectedItem(d);
        txtEmail.setText(e);
        txtGaji1.setText(f);
        txtAlamat.setText(g);
        txtjabatan1.setText(h);

    }//GEN-LAST:event_tabeldataKaryawanMouseClicked

    private void asaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asaveActionPerformed
        String sql = "insert into data_Karyawan values (?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtIdKar.getText());
            stat.setString(2, txtNama.getText());

            String jkel="";
            if(rbLaki.isSelected()) jkel="Laki-Laki";
            else jkel="Perempuan";
            stat.setString(3, jkel);

            stat.setString(4, jcAgama.getSelectedItem().toString());
            stat.setString(5, txtEmail.getText());
            stat.setString(6, txtGaji1.getText());
            stat.setString(7, txtAlamat.getText());
            stat.setString(8, txtjabatan1.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            kosong();
            txtIdKar.requestFocus();
            datatable("");

        }   catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan");
        }
    }//GEN-LAST:event_asaveActionPerformed

    private void aupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aupdateActionPerformed
        try{
            String sql = "update data_Karyawan set nama=?,jk=?,agama=?,email=?,gp=?,alamat=?,jabatan=? where id_kar=?";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtNama.getText());

            String jkel="";
            if(rbLaki.isSelected()) jkel="Laki-laki";
            else jkel="Perempuan";
            stat.setString(2, jkel);

            stat.setString(3, jcAgama.getSelectedItem().toString());
            stat.setString(4, txtEmail.getText());
            stat.setString(5, txtGaji1.getText());
            stat.setString(6, txtAlamat.getText());
            stat.setString(7, txtjabatan1.getText());
            stat.setString(8, txtIdKar.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            kosong();
            txtIdKar.requestFocus();
            datatable("");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah"+e);
        }
    }//GEN-LAST:event_aupdateActionPerformed

    private void aclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aclearActionPerformed
        kosong();
        datatable("");
    }//GEN-LAST:event_aclearActionPerformed

    private void adeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adeleteActionPerformed
        int ok = JOptionPane.showConfirmDialog(null,"hapus","Konfirmasi Dialog",JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok==0){
            String sql ="delete from data_karyawan where id_kar ='"+txtIdKar.getText()+"'";
            try{
                PreparedStatement stat = conn.prepareStatement (sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                kosong();
                txtIdKar.requestFocus();
                datatable("");
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data gagal dihapus"+e);
            }
        }
    }//GEN-LAST:event_adeleteActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try {
            String fileName = "src/report/KaryawanReport.jasper";
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
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        try {
            String fileName = "src/report/CutiReport.jasper";
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
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        try {
            String fileName = "src/report/AbsenReport.jasper";
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
    }//GEN-LAST:event_jButton9ActionPerformed

   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cari1;
    private javax.swing.JButton Cari2;
    private javax.swing.JButton Cari3;
    private javax.swing.JButton Cari4;
    private javax.swing.JButton Cari5;
    private komponen.ButtonGlass aclear;
    private komponen.ButtonGlass adelete;
    private komponen.ButtonGlass asave;
    private komponen.ButtonGlass aupdate;
    private com.toedter.calendar.JDateChooser bendcut;
    private com.toedter.calendar.JDateChooser bstarcut;
    private javax.swing.ButtonGroup btnGroupJk;
    private javax.swing.JTextField ccari;
    private komponen.ButtonGlass cclear;
    private komponen.ButtonGlass cdelet;
    private komponen.ButtonGlass csave;
    private komponen.ButtonGlass cupdate;
    private javax.swing.JTextField dcari;
    private komponen.ButtonGlass dclear;
    private komponen.ButtonGlass ddelete;
    private komponen.ButtonGlass dsave;
    private komponen.ButtonGlass dupdate;
    private javax.swing.JTextField ecari;
    private komponen.ButtonGlass gclear;
    private komponen.ButtonGlass gdelete;
    private komponen.ButtonGlass gsave;
    private komponen.ButtonGlass gupdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> jcAgama;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbPerempuan;
    private komponen.ButtonGlass sclear;
    private komponen.ButtonGlass sdelete;
    private komponen.ButtonGlass ssave;
    private komponen.ButtonGlass supdate;
    private javax.swing.JPanel tabCuti;
    private javax.swing.JPanel tabKaryawan;
    private javax.swing.JPanel tabPotongan;
    private javax.swing.JPanel tabTunjangan;
    private javax.swing.JPanel tababsen;
    private javax.swing.JTable tabelabsen;
    private javax.swing.JTable tabelcuti;
    private javax.swing.JTable tabeldataKaryawan;
    private javax.swing.JTable tabelpotongan;
    private javax.swing.JTable tabeltunjangan;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGaji1;
    private javax.swing.JTextField txtIdCut;
    private javax.swing.JTextField txtIdKar;
    private javax.swing.JTextField txtIdKar4;
    private javax.swing.JTextField txtIdKar5;
    private javax.swing.JTextField txtIdabsen;
    private javax.swing.JTextField txtIdpot;
    private javax.swing.JTextField txtIdtunjangan;
    private javax.swing.JTextField txtKar2;
    private javax.swing.JTextField txtKaryawan;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtbpjs;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtin;
    private javax.swing.JTextField txtjabatan1;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnama1;
    private javax.swing.JTextField txtnama2;
    private javax.swing.JTextField txtnama3;
    private javax.swing.JTextField txtout;
    private javax.swing.JTextField txtpotketenaga;
    private javax.swing.JTextField txtpottam;
    private javax.swing.JTextField txtreason;
    private javax.swing.JTextField txtstatus;
    private javax.swing.JTextField txttotaltun;
    private javax.swing.JTextField zcari;
    // End of variables declaration//GEN-END:variables
}
