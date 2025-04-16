import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener
{   JFrame jf;
	JMenuBar mbar;
	JMenu m1,m2,m3,m4,m5,m6;
	JMenuItem m1_1,m1_2,m1_3,m1_4,m1_5,m2_1,m2_2,m2_3,m2_4,m2_5,m3_1,m3_2,m4_1,m5_1,m6_1;
	JLabel l1,LogoColl;
	GridBagLayout gbl;
	private JMenu m0;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem m0_1;
	private JMenuItem m3_3;

	public MainMenu()
	{
		Connection con;
	    PreparedStatement ps;
	    Statement stmt;
	    ResultSet rs;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store","root","");
		
		System.out.println("Connected to database.");
		ps = con.prepareStatement("select mexpdate,mpurdate from medicine");
		Date sysdate;
        rs = ps.executeQuery();
        Date exp;
        Date pur;
        java.util.Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String todayString = formatter.format(todayDate);
        LocalDate todaysdt= LocalDate.parse(todayString);
        System.out.println("todays date"+todayString);
        while(rs.next())
        {
        exp=rs.getDate("mexpdate");
        pur=rs.getDate("mpurdate");
        System.out.println("exp:"+exp+ "pur:"+pur);
       
		LocalDate expdate=exp.toLocalDate();
		LocalDate purdate=pur.toLocalDate();
		int expyear=expdate.getYear();
        int puyear=purdate.getYear();
        System.out.println("exp yr="+expyear +"pur yr="+puyear);  
        int count=0;
        if(expdate.isBefore(todaysdt))
        {
        	count++;
        }
        if(count>0)
        {
        	JOptionPane.showMessageDialog(jf, count+"medicine('s) are expired Please check the stock...");
        	}
        }
        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        jf=new JFrame();
		gbl=new GridBagLayout();
		jf.getContentPane().setLayout(gbl);

		l1=new JLabel("WELCOME TO MEDICAL MANAGEMENT SYSTEM");
		l1.setFont(new Font("Times New Roman",Font.BOLD,20));
		jf.getContentPane().add(l1);

		mbar = new JMenuBar();
		jf.setJMenuBar(mbar);
		
		m6 = new JMenu("Billing");
		
		mbar.add(m6);
                m6_1=new JMenuItem("Bill",new ImageIcon("E:\\Medical Shop Management\\src\\images\\invoice.png"));
                m6.add(m6_1);
		
		
		m1=new JMenu("Supplier");
		mbar.add(m1);
		m1_1 = new JMenuItem("Add New Supplier",new ImageIcon("E:\\Medical Shop Management\\src\\images\\addnew.png"));
		m1.add(m1_1);
		m1_2 = new JMenuItem("Search Supplier",new ImageIcon("E:\\Medical Shop Management\\src\\images\\search.png"));
		m1.add(m1_2);
		m1_3 = new JMenuItem("Update Supplier",new ImageIcon("E:\\Medical Shop Management\\src\\images\\update.png"));
		m1.add(m1_3);
		m1_4 = new JMenuItem("Delete Supplier",new ImageIcon("E:\\Medical Shop Management\\src\\images\\delete.png"));
		m1.add(m1_4);
	    m1_5 = new JMenuItem("List of Supplier",new ImageIcon("E:\\Medical Shop Management\\src\\images\\all.png"));
		m1.add(m1_5);

		m2=new JMenu("Medicine");
		mbar.add(m2);
		m2_1 = new JMenuItem("Add New Medicine",new ImageIcon("E:\\Medical Shop Management\\src\\images\\addnew.png"));
		m2.add(m2_1);
		m2_2 = new JMenuItem("Search Medicine",new ImageIcon("E:\\Medical Shop Management\\src\\images\\search.png"));
		m2.add(m2_2);
		m2_3 = new JMenuItem("Update Medicine",new ImageIcon("E:\\Medical Shop Management\\src\\images\\update.png"));
		m2.add(m2_3);
		m2_4 = new JMenuItem("Delete Medicine",new ImageIcon("E:\\Medical Shop Management\\src\\images\\delete.png"));
		m2.add(m2_4);
	    m2_5 = new JMenuItem("Stock of Medicine",new ImageIcon("E:\\Medical Shop Management\\src\\images\\all.png"));
		m2.add(m2_5);


		m3=new JMenu("Report");
	    mbar.add(m3);
		m3_1 = new JMenuItem("Daily Purchase Report",new ImageIcon("E:\\Medical Shop Management\\src\\images\\report.png"));
		m3.add(m3_1);

		m3_2 = new JMenuItem("Suplier wise medicine Report",new ImageIcon("E:\\Medical Shop Management\\src\\images\\report.png"));
		m3.add(m3_2);
		
		m3_3 = new JMenuItem("Daily Sales Report");
		m3_3.setIcon(new ImageIcon("E:\\Medical Shop Management\\src\\images\\report.png"));
		m3.add(m3_3);

		m4=new JMenu("About");
		mbar.add(m4);
		m4_1 = new JMenuItem("About System",new ImageIcon("E:\\Medical Shop Management\\src\\images\\help.png"));
		m4.add(m4_1);

		m5=new JMenu("Exit");
		mbar.add(m5);
		m5_1 = new JMenuItem("Exit",new ImageIcon("E:\\Medical Shop Management\\src\\images\\exit.png"));
		m5.add(m5_1);
		
        m1_1.addActionListener(this);
		m1_2.addActionListener(this);
		m1_3.addActionListener(this);
		m1_4.addActionListener(this);
    	m1_5.addActionListener(this);

		m2_1.addActionListener(this);
		m2_2.addActionListener(this);
		m2_3.addActionListener(this);
		m2_4.addActionListener(this);
	    m2_5.addActionListener(this);

		m3_1.addActionListener(this);
		m3_2.addActionListener(this);
		m3_3.addActionListener(this);
		m4_1.addActionListener(this);
		m5_1.addActionListener(this);   
                m6_1.addActionListener(this);
		jf.setTitle("Main Menu");
		jf.setLocation(20,20);
	    jf.setSize(900,700);
	    jf.setResizable(true);
		jf.getContentPane().setBackground(Color.yellow);
		jf.setVisible(true);

	}

	public void actionPerformed(ActionEvent ae)
	{

	  if(ae.getSource()==m1_1)
		{
		  new AddNewSupplier();
		}
		else if(ae.getSource()==m1_2)
		{
		 new SearchSupplier();
		}
		else if(ae.getSource()==m1_3)
		{
		new UpdateSupplier();
		}
		else if(ae.getSource()==m1_4)
		{
		new DeleteSupplier();
		}
	    else if(ae.getSource()==m1_5)
		{
		  new SupplierList();
		}


		else if(ae.getSource()==m2_1)
		{
			new AddNewMedicine();
		}
		else if(ae.getSource()==m2_2)
		{
			new SearchMedicine();
		}
		else if(ae.getSource()==m2_3)
		{
			new UpdateMedicine();
		}
		else if(ae.getSource()==m2_4)
		{
			new DeleteMedicine();
		}
		else if(ae.getSource()==m2_5)
		{
			new MedicineList();
		}

		 else if(ae.getSource()==m3_1)
		 {
		   new DailyPurchaseReport();
		 }

		  else if(ae.getSource()==m3_2)
		 {
		   new SupplierWiseMedList();
		 }
		  		
		  else if(ae.getSource()==m3_3)
			 {
			  BillList billObj=new BillList();
              billObj.setVisible(true);
			 }

		else if(ae.getSource()==m4_1)
		{
	        new About();
		}

		else if(ae.getSource()==m5_1)
		{
		  System.exit(0);
		}
		else if(ae.getSource()==m6_1)
		{
		 
              try {
                  Billing billObj=new Billing();
                  billObj.setVisible(true);
              } catch (Exception ex) {
                  Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
              }
		
		}

  }

	public static void main(String args[]) throws Exception
	{
		
		new MainMenu();
		// TODO Auto-generated method stub
		
	}
}
