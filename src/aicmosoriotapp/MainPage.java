/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aicmosoriotapp;

import classes.dbconn;
import datechooser.beans.DateChooserCombo;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileOutputStream;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;
import org.jdom.Document;

/**
 *
 * @author root
 */
public class MainPage extends javax.swing.JFrame {
     private double[] marks;
     private datechooser.beans.DateChooserCombo dateChooser;
    private JTextField[] marksField;
    private JLabel resultLabel;
    private JButton jButton;
    
Connection conn=  new dbconn().dbconn();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
ResultSet rs=null;
PreparedStatement pst;
    /**
     * Creates new form MainPage
     */
    public MainPage() {
        initComponents();
        
         marks = new double[3];
        marksField = new JTextField[3];
        marksField[0] = new JTextField(10);
        marksField[1] = new JTextField(10);
        marksField[2] = new JTextField(10);
        jButton=new JButton("Save");
        dateChooser=new DateChooserCombo();
        
        populate_Table();
        counties();
       // sub_counties();
       
       
       ActionListener actionListener = new ActionListener() {
         public void actionPerformed(ActionEvent event) {
            String str = event.getActionCommand();
             
            switch (str){
                case "Add Child":
                    add_child();
                    break;
            }
         }
      };
      jButton.setActionCommand("Add Child");
      jButton.addActionListener(actionListener);
    }
    
    public void add_child(){
         try {
             String parentid=jTextField2.getText();
             String child_name=marksField[0].getText().toUpperCase();
                    Calendar cal= dateChooser.getSelectedDate();
                    Date date =  cal.getTime();
                    String child_date_of_birth=simpleDateFormat.format(date);
             String gifted_in=marksField[2].getText();
             
             pst=conn.prepareStatement("INSERT into children(parent_id,child_name,child_date_of_birth,gifted_in) values(?,?,?,?)");
            pst.setString(1, parentid);
            pst.setString(2, child_name);
            pst.setString(3, child_date_of_birth);
            pst.setString(4, gifted_in);
          int i=   pst.executeUpdate();
          if(i>0){
          JOptionPane.showMessageDialog(null, i);
          
          }
             
         } catch (SQLException ex) {
             Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
         }
    
    }
    
    public void counties(){
    String[] counties={"Mombasa","Kwale","Kilifi","Tana River","Lamu","Taita_Taveta","Garissa","Wajir","Mandera","Marsabit","Isiolo","Meru","Tharaka_Nithi","Embu","Kitui","Machakos","Makueni","Nyandarua","Nyeri","Kirinyaga","Muranga","Kiambu","Turkana","West_Pokot","Samburu","Trans_Nzoia","Uasin_Gishu","Elgeyo_Marakwet","Nandi","Baringo","Laikipia","Nakuru","Narok","Kajiado","Kericho","Bomet","Kakamega","Vihiga","Bungoma","Busia","Siaya","Kisumu","Homa_Bay","Migori","Kisii","Nyamira","Nairobi"};
        for(int i=0;i<counties.length;i++){
        jComboBox1.addItem(counties[i]);
        }
    }
    
    public void sub_counties(){
        jComboBox2.removeAllItems();
        String county=jComboBox1.getSelectedItem().toString();
        
//    
                 String[] Mombasa={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                 String[] Kwale={"Matuga","Msambweni","Kinango","Lunga Lunga"};
                 String[] Kilifi={"Rabai","Kaum a ","Chonyi","Kilifi South","Kilifi North","Ganze","Malindi","Magarini","Kaloleni"};
                 String[] Tana_River={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                 String[] Lamu={"Lamu East","Lamu West"};
                 String[] Taita_Taveta={"Taveta","Wundanyi","Mwatate","Voi"};
                 String[] Garissa={"Township","Fafi","Lagdera","Balambala","Ijara"};
                 String[] Wajir={"Buna","Eldas","Habaswein","Tarbaj","Wajir East","Wajir North","Wajir South","Wajir West"};
                 String[] Mandera={"Mandera West","Banisa","Kutulo","Lafey","Mandera Central","Mandera East","Mandera North"};
                 String[] Marsabit={"North Horr","Moyale","Saku","Laisamis"};
                 
                 String[] Isiolo={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                 String[] Meru={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                 String[] Tharaka_Nithi={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                 String[] Embu={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                 String[] Kitui={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                 String[] Machakos={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                 String[] Makueni={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                 String[] Nyandarua={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                 String[] Nyeri={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                 String[] Kirinyaga={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                 String[] Muranga={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                 String[] Kiambu={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                     String[] Turkana={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                 String[] West_Pokot={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                 String[] Samburu={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                 
                 String[] Trans_Nzoia={"Kwanza","Endebess","Saboti","Kiminini","Cherangany"};
                 String[] Uasin_Gishu={"Turbo", "Soy", "Ainabkoi","Moiben", "Kessess","Kapseret"};
                 String[] Elgeyo_Marakwet={"Keiyo North","Keiyo South","Marakwet West","Marakwet East"};
                 String[] Nandi={"Mosop","Emgwen","Aldai","Tinderet","Nandi Hills","Chesumei"};
                 String[] Baringo={"Baringo Central","Baringo South","Baringo North", "Eldama Ravine", "Mogotio","Tiaty"};
                 String[] Laikipia={"Laikipia East", "Laikipia North","Laikipia West"};
                 String[] Nakuru={"Njoro","Molo","Kuresoi South","Kuresoi North","Rongai","Naivasha","Gilgil"};
                 String[] Narok={"Kilgoris","Narok North","Narok South","Narok East","Narok West","Emurua Dikirr"};
                 String[] Kajiado={"Isinya","Kajiado Central","Kajiado North","Kajiado West","Loitokitok","Mashuuru"};
                 String[] Kericho={"Bureti","Belgut","Ainamoi","Soin/ Sigowet","Kipkelion East","Kipkelion West"};
                 String[] Bomet={"Bomet Central","Bomet East","Chepalungu","Sotik","Konoin"};
                 String[] Kakamega={"Shinyalu","Navakholo","Mumias East","Mumias West","Matungu","Malava","Lurambi","Lugari","Butere","Khwisero","Kakamega Central","Likuyani","Matete","Kakamega South"};
                 String[] Vihiga={"Sabatia","Vihiga", "Hamisi", "Emuhaya" , "Luanda"};
                 
                 String[] Bungoma={"Bumula", "Kanduyi", "Sirisia", "Kabuchai", "Kimilili","Tongare","Webuye East","Webuye West","MtElgon"};
                
                 String[] Busia={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                   String[] Siaya={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                 String[] Kisumu={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                 String[] Homa_Bay={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                 String[] Migori={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                 String[] Kisii={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                 String[] Nyamira={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                 String[] Nairobi={"Changamwe","Jomvu","Kisauni","Likoni","Mvita","Nyali"};
                                  
                 switch (county){
                     case "Mombasa":
                         int x=Mombasa.length;
                     for(int i=0;i<Mombasa.length;i++){
                        jComboBox2.addItem(Mombasa[i]);
                        }
                     
                   //  addsub(int x,);
                     break;
                     case "Kilifi":
                         for(int i=0;i<Kilifi.length;i++){
                            jComboBox2.addItem(Kilifi[i]);
                            }
                         break;
                       
                     case "Kwale":
                       for(int i=0;i<Kwale.length;i++){
                        jComboBox2.addItem(Kwale[i]);
                       }
                        break;
                        
                        case "Lamu":
                        for(int i=0;i<Lamu.length;i++){
        jComboBox2.addItem(Lamu[i]);
        }
                        break;
                                                
                        case "Taita_Taveta":
                        for(int i=0;i<Taita_Taveta.length;i++){
        jComboBox2.addItem(Taita_Taveta[i]);
        }
                        break;
                                                
                        case "Tana_River":
                         for(int i=0;i<Tana_River.length;i++){
                            jComboBox2.addItem(Tana_River[i]);
                               }
                        break;
                        
                        
                    
                 
                
                        case "Garissa":
         for(int i=0;i<Garissa.length;i++){
        jComboBox2.addItem(Garissa[i]);
        }
         break;
         
                        case "Wajir":
         for(int i=0;i<Wajir.length;i++){
        jComboBox2.addItem(Wajir[i]);
        } 
         break;
                        case "Mandera":
         for(int i=0;i<Mandera.length;i++){
        jComboBox2.addItem(Mandera[i]);
        }
         break;
                        case "Marsabit":
         for(int i=0;i<Marsabit.length;i++){
        jComboBox2.addItem(Marsabit[i]);
        }break; case "Isiolo":
         for(int i=0;i<Isiolo.length;i++){
        jComboBox2.addItem(Isiolo[i]);
        }break; case "Meru":
         for(int i=0;i<Meru.length;i++){
        jComboBox2.addItem(Meru[i]);
        }break; case "Tharaka_Nithi":
         for(int i=0;i<Tharaka_Nithi.length;i++){
        jComboBox2.addItem(Tharaka_Nithi[i]);
        }break; case "Embu":
         for(int i=0;i<Embu.length;i++){
        jComboBox2.addItem(Embu[i]);
        }
         
         break; case "Kitui":
         for(int i=0;i<Kitui.length;i++){
        jComboBox2.addItem(Kitui[i]);
        }break; case "Machakos":
         for(int i=0;i<Machakos.length;i++){
        jComboBox2.addItem(Machakos[i]);
        }break; case "Makueni":
         for(int i=0;i<Makueni.length;i++){
        jComboBox2.addItem(Makueni[i]);
        }break; case "Nyandarua":
         for(int i=0;i<Nyandarua.length;i++){
        jComboBox2.addItem(Nyandarua[i]);
        }break; case "Nyeri":
         for(int i=0;i<Nyeri.length;i++){
        jComboBox2.addItem(Nyeri[i]);
        }break;
                 }
         if(county.equals("Kirinyaga")){
         for(int i=0;i<Kirinyaga.length;i++){
        jComboBox2.addItem(Kirinyaga[i]);
        }}else if(county.equals("Muranga")){
         for(int i=0;i<Muranga.length;i++){
        jComboBox2.addItem(Muranga[i]);
        }}else if(county.equals("Kiambu")){
         for(int i=0;i<Kiambu.length;i++){
        jComboBox2.addItem(Kiambu[i]);
        }}else if(county.equals("Turkana")){
         for(int i=0;i<Turkana.length;i++){
        jComboBox2.addItem(Turkana[i]);
        }}else if(county.equals("West_Pokot")){
         for(int i=0;i<West_Pokot.length;i++){
        jComboBox2.addItem(West_Pokot[i]);
        }}else if(county.equals("Samburu")){
         for(int i=0;i<Samburu.length;i++){
        jComboBox2.addItem(Samburu[i]);
        }}else if(county.equals("Trans_Nzoia")){
         for(int i=0;i<Trans_Nzoia.length;i++){
        jComboBox2.addItem(Trans_Nzoia[i]);
        }}else if(county.equals("Uasin_Gishu")){
         for(int i=0;i<Uasin_Gishu.length;i++){
        jComboBox2.addItem(Uasin_Gishu[i]);
        }}else if(county.equals("Elgeyo_Marakwet")){
         for(int i=0;i<Elgeyo_Marakwet.length;i++){
        jComboBox2.addItem(Elgeyo_Marakwet[i]);
        }}else if(county.equals("Nandi")){
         for(int i=0;i<Nandi.length;i++){
        jComboBox2.addItem(Nandi[i]);
        }}else if(county.equals("Baringo")){
         for(int i=0;i<Baringo.length;i++){
        jComboBox2.addItem(Baringo[i]);
        }}else if(county.equals("Laikipia")){
         for(int i=0;i<Laikipia.length;i++){
        jComboBox2.addItem(Laikipia[i]);
        }}else if(county.equals("Nakuru")){
         for(int i=0;i<Nakuru.length;i++){
        jComboBox2.addItem(Nakuru[i]);
        }}else if(county.equals("Narok")){
         for(int i=0;i<Narok.length;i++){
        jComboBox2.addItem(Narok[i]);
        }}else if(county.equals("Kajiado")){
         for(int i=0;i<Kajiado.length;i++){
        jComboBox2.addItem(Kajiado[i]);
        }}else if(county.equals("Kericho")){
         for(int i=0;i<Kericho.length;i++){
        jComboBox2.addItem(Kericho[i]);
        }}else if(county.equals("Bomet")){
         for(int i=0;i<Bomet.length;i++){
        jComboBox2.addItem(Bomet[i]);
        }}else if(county.equals("Kakamega")){
         for(int i=0;i<Kakamega.length;i++){
        jComboBox2.addItem(Kakamega[i]);
        }}else if(county.equals("Vihiga")){
         for(int i=0;i<Vihiga.length;i++){
        jComboBox2.addItem(Vihiga[i]);
        }}else if(county.equals("Bungoma")){
         for(int i=0;i<Bungoma.length;i++){
        jComboBox2.addItem(Bungoma[i]);
        }}else if(county.equals("Busia")){
         for(int i=0;i<Busia.length;i++){
        jComboBox2.addItem(Busia[i]);
        }}else if(county.equals("Siaya")){
         for(int i=0;i<Siaya.length;i++){
        jComboBox2.addItem(Siaya[i]);
        }}else if(county.equals("Kisumu")){
         for(int i=0;i<Kisumu.length;i++){
        jComboBox2.addItem(Kisumu[i]);
        }}else if(county.equals("Homa_Bay")){
         for(int i=0;i<Homa_Bay.length;i++){
        jComboBox2.addItem(Homa_Bay[i]);
        }}else if(county.equals("Migori")){
         for(int i=0;i<Migori.length;i++){
        jComboBox2.addItem(Migori[i]);
        }}else if(county.equals("Kisii")){
         for(int i=0;i<Kisii.length;i++){
        jComboBox2.addItem(Kisii[i]);
        }}else if(county.equals("Nyamira")){
         for(int i=0;i<Nyamira.length;i++){
        jComboBox2.addItem(Nyamira[i]);
        }}else if(county.equals("Nairobi")){
         for(int i=0;i<Nairobi.length;i++){
        jComboBox2.addItem(Nairobi[i]);
        }}
                 
    }
    
public void populate_Table(){
 try {
           
           // Class.forName("com.mysql.jdbc.Driver");
          // Connection conn = DriverManager.getConnection("jdbc:mysql:///mosoriot", "root", "");
            pst = conn.prepareStatement("select name as 'NAME' ,idno as 'ID',phoneno as 'PHONE',marital_status,"
                    + "date_of_marriage,marriage_by_rev,date_of_joining,baptized_by,date_of_baptism,"
                    + "home_county,sub_county,home_church,professional_area,gifted_in from church_members ");
            
            rs = pst.executeQuery();
            
           while(rs.next()){
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
           }
           
           
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jDatePickerUtil1 = new org.jdatepicker.util.JDatePickerUtil();
        jDatePickerUtil2 = new org.jdatepicker.util.JDatePickerUtil();
        utilDateModel1 = new org.jdatepicker.impl.UtilDateModel();
        jPanel2 = new javax.swing.JPanel();
        card1 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField4 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jRadioButton2 = new javax.swing.JRadioButton();
        jTextField6 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        dateChooserCombo3 = new datechooser.beans.DateChooserCombo();
        jLabel15 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        card2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        jPanel2.setLayout(new java.awt.CardLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setText("Sub County");

        jLabel7.setText("Date Of Baptism");

        jLabel1.setText("Name");

        jLabel2.setText("Id No");

        jLabel5.setText("Date Of Joining");

        jLabel8.setText("Home County");

        jLabel10.setText("Home Church");

        jLabel3.setText("Phone No");

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox1KeyReleased(evt);
            }
        });

        jLabel6.setText("Baptized By");

        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Delete");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Yes");

        jLabel13.setText("Have Children Under 18?");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("No");

        jLabel12.setText("Gifted in");

        jLabel11.setText("Professional Area");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single", "Married", "Devorced" }));

        jLabel4.setText("Marital Status");

        jLabel14.setText("Marriage By:");

        jLabel15.setText("Date Of Marrage");

        jButton6.setText("Register Child");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(88, 88, 88)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .addComponent(jTextField2)
                    .addComponent(jTextField3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(40, 40, 40)
                                    .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(63, 63, 63)
                                    .addComponent(jTextField4)))
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox2, 0, 172, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                    .addComponent(jTextField5)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)
                        .addGap(2, 2, 2)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(40, 40, 40)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateChooserCombo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dateChooserCombo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton4)
                            .addComponent(jButton5)
                            .addComponent(jButton6))
                        .addGap(142, 142, 142))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout card1Layout = new javax.swing.GroupLayout(card1);
        card1.setLayout(card1Layout);
        card1Layout.setHorizontalGroup(
            card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(card1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(card1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 38, Short.MAX_VALUE))
        );
        card1Layout.setVerticalGroup(
            card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(card1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
        );

        jPanel2.add(card1, "card2");

        card2.setBackground(new java.awt.Color(45, 231, 121));

        javax.swing.GroupLayout card2Layout = new javax.swing.GroupLayout(card2);
        card2.setLayout(card2Layout);
        card2Layout.setHorizontalGroup(
            card2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1405, Short.MAX_VALUE)
        );
        card2Layout.setVerticalGroup(
            card2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 647, Short.MAX_VALUE)
        );

        jPanel2.add(card2, "card3");

        jButton1.setText("Change");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Print");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(713, 713, 713)
                .addComponent(jButton1)
                .addGap(80, 80, 80)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CardLayout card = (CardLayout) jPanel2.getLayout();
        card.show(jPanel2, "card3");
    }//GEN-LAST:event_jButton1ActionPerformed
public void registerMember() throws ParseException{
    String name=jTextField1.getText().toUpperCase();
    String idno=jTextField2.getText();
    String phoneno=jTextField3.getText();
    String marital_status=jComboBox3.getSelectedItem().toString();
    
    Calendar cal= dateChooserCombo3.getSelectedDate();
    Date date =  cal.getTime();
    
    String  date_of_marriage = simpleDateFormat.format(date);
   //Date date_of_marriage=simpleDateFormat.parse(simpleDateFormat.format(date));//new SimpleDateFormat("yyyy-MM-dd").parse(mydate);
    

    
    String marriage_by_rev=jTextField8.getText();
    
    Calendar cal1=dateChooserCombo2.getSelectedDate();
    Date date1 =  cal1.getTime();
    String  date_of_joining = simpleDateFormat.format(date1);
     
     
    String baptized_by=jTextField4.getText();
    
        Calendar cal2=dateChooserCombo2.getSelectedDate();
    Date date2 =  cal2.getTime();
    String  date_of_baptism = simpleDateFormat.format(date2);
     
    String home_county=jComboBox1.getSelectedItem().toString();
    String sub_county=jComboBox2.getSelectedItem().toString();
    String home_church=jTextField5.getText();
    String professional_area=jTextField6.getText();
    String gifted_in=jTextField7.getText();
  //  buttonGroup1.getSelection()
try{
            pst = conn.prepareStatement("insert into church_members(name,idno,phoneno,marital_status,"
                    + "date_of_marriage,marriage_by_rev,date_of_joining,baptized_by,date_of_baptism,"
                    + "home_county,sub_county,home_church,professional_area,gifted_in) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, name);
            pst.setString(2, idno);
            pst.setString(3, phoneno);
            pst.setString(4, marital_status);
            pst.setString(5,date_of_marriage);
            pst.setString(6, marriage_by_rev);
            pst.setString(7, date_of_joining);
            pst.setString(8, baptized_by);
            pst.setString(9, date_of_baptism);
            pst.setString(10, home_county);
            pst.setString(11, sub_county);
            pst.setString(12, home_church);
            pst.setString(13, professional_area);
            pst.setString(14, gifted_in);
            
          if(!pst.execute()){
                JOptionPane.showMessageDialog(null, "saved");
                populate_Table();
               // JOptionPane.showInputDialog(rs, rs);
//JFrame newFrame = new children();
//newFrame.setVisible(true);

          }
    
}catch(Exception  e){JOptionPane.showMessageDialog(null, e);}
}


    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            int row = jTable1.getSelectedRow();
            String Table_click = (jTable1.getModel().getValueAt(row, 0).toString());
            int x=jTable1.getColumnCount();
            for(int i=0;i<x;i++){
                
                switch (i){
                        case 0:
                            jTextField1.setText(jTable1.getModel().getValueAt(row, 0).toString());
                            break;
                        case 1:
                            jTextField2.setText(jTable1.getModel().getValueAt(row, 1).toString());
                            break;
                        case 2:
                            jTextField3.setText(jTable1.getModel().getValueAt(row, 2).toString());
                            break;
                        case 3:
                            jComboBox3.setSelectedItem(jTable1.getModel().getValueAt(row, 3));
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                    Date d=simpleDateFormat.parse(jTable1.getModel().getValueAt(row, 6).toString());
                    Calendar c=null;
                    c=Calendar.getInstance();
                    c.setTime(d);
                    dateChooserCombo1.setSelectedDate(c);
                            break;
                        case 7:
                            jTextField4.setText(jTable1.getModel().getValueAt(row, 7).toString());
                            break;
                        case 8:
                    d=simpleDateFormat.parse(jTable1.getModel().getValueAt(row, 8).toString());
                     c=null;
                    c=Calendar.getInstance();
                    c.setTime(d);
                    dateChooserCombo2.setSelectedDate(c);
                            break;
                        case 9:
                            jComboBox1.setSelectedItem(jTable1.getModel().getValueAt(row, 9));
                            break;
                        case 10:
                            sub_counties();
                            jComboBox2.setSelectedItem(jTable1.getModel().getValueAt(row, 10));
                            break;
                        case 11:
                            jTextField5.setText(jTable1.getModel().getValueAt(row, 11).toString());
                            break;
                        case 12:
                            jTextField6.setText(jTable1.getModel().getValueAt(row, 12).toString());
                            break;
                        case 13:
                            jTextField7.setText(jTable1.getModel().getValueAt(row, 13).toString());
                            break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTable1MouseClicked
 private void displayGUI()
    {
  JPanel panel = getPanel();

// panel.add(new JTextField(20));
// panel.add(new JLabel("Label"));
 JOptionPane.showMessageDialog(null,panel,"Information",JOptionPane.INFORMATION_MESSAGE);
    }
    private JPanel getPanel()
    {
       // JPanel basePanel = new JPanel();
        //basePanel.setLayout(new BorderLayout(5, 5));
       // basePanel.setOpaque(true);
      //  basePanel.setBackground(Color.BLUE.darker());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 3, 5, 5));
        centerPanel.setBorder(
            BorderFactory.createEmptyBorder(5, 5, 5, 5));
        centerPanel.setOpaque(true);
        centerPanel.setBackground(Color.WHITE);

        JLabel mLabel1 = new JLabel("Enter NAME : ");
        JLabel mLabel2 = new JLabel("Enter DATE OF BIRTH : ");
        JLabel mLabel3 = new JLabel("Gifted in : ");
        JLabel mLabel4 = new JLabel("");
      //  JButton jButton1=new JButton("Click");

        centerPanel.add(mLabel1);
        centerPanel.add(marksField[0]);
        centerPanel.add(mLabel2);
        centerPanel.add(dateChooser);
        centerPanel.add(mLabel3);
        centerPanel.add(marksField[2]);
        centerPanel.add(mLabel4);
        centerPanel.add(jButton);

        return centerPanel;
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            registerMember();
        } catch (ParseException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyReleased

    }//GEN-LAST:event_jComboBox1KeyReleased

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        sub_counties();
    }//GEN-LAST:event_jComboBox1ItemStateChanged
public void updateMember() throws ParseException{
    String name=jTextField1.getText();
    String idno=jTextField2.getText();
    String phoneno=jTextField3.getText();
    String marital_status=jComboBox3.getSelectedItem().toString();
    
    Calendar cal= dateChooserCombo2.getSelectedDate();
    Date date =  cal.getTime();
    
    String  date_of_marriage = simpleDateFormat.format(date);
   //Date date_of_marriage=simpleDateFormat.parse(simpleDateFormat.format(date));//new SimpleDateFormat("yyyy-MM-dd").parse(mydate);
    

    
    String marriage_by_rev="Rev Kinyanjui";
    
    Calendar cal1=dateChooserCombo2.getSelectedDate();
    Date date1 =  cal1.getTime();
    String  date_of_joining = simpleDateFormat.format(date1);
     
     
    String baptized_by=jTextField4.getText();
    
        Calendar cal2=dateChooserCombo2.getSelectedDate();
    Date date2 =  cal2.getTime();
    String  date_of_baptism = simpleDateFormat.format(date2);
     
    String home_county=jComboBox1.getSelectedItem().toString();
    String sub_county=jComboBox2.getSelectedItem().toString();
    String home_church=jTextField5.getText();
    String professional_area=jTextField6.getText();
    String gifted_in=jTextField7.getText();
  //  buttonGroup1.getSelection()
try{
            pst = conn.prepareStatement("update church_members set name='"+name+"',phoneno='"+phoneno+"',marital_status='"+marital_status+"',"
                    + "date_of_marriage='"+date_of_marriage+"',marriage_by_rev='"+marriage_by_rev+"',date_of_joining='"+date_of_joining+"',baptized_by='"+baptized_by+"',date_of_baptism='"+date_of_baptism+"',"
                    + "home_county='"+home_county+"',sub_county='"+sub_county+"',home_church='"+home_church+"',professional_area='"+professional_area+"',gifted_in='"+gifted_in+"' where idno='"+idno+"'");
            
          if(!pst.execute()){
                JOptionPane.showMessageDialog(null, "Update Successful");
                populate_Table();
          }
    
}catch(Exception  e){JOptionPane.showMessageDialog(null, e);}
}
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    try {
        updateMember();
    } catch (ParseException ex) {
        Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        displayGUI();
    }//GEN-LAST:event_jButton6ActionPerformed

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
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JPanel card1;
    private javax.swing.JPanel card2;
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private datechooser.beans.DateChooserCombo dateChooserCombo3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private org.jdatepicker.util.JDatePickerUtil jDatePickerUtil1;
    private org.jdatepicker.util.JDatePickerUtil jDatePickerUtil2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private org.jdatepicker.impl.UtilDateModel utilDateModel1;
    // End of variables declaration//GEN-END:variables

    private void setLabel(String good) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
