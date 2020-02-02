package lms;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookCheckout extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private Statement st;
    Connection con;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookCheckout frame = new BookCheckout();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Connection makeConnection() {
        try{
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "jinzh19941113");
           st = con.createStatement();
           
       }catch(Exception e){
           System.err.println("ERROR: "+e);
       }
		return con;}
	/**
	 * Create the frame.
	 */
	public BookCheckout() {
		con = makeConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 321, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(40, 50, 108, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(182, 47, 77, 32);
		contentPane.add(comboBox);
		try {
			String qBranch = "SELECT * FROM branch";
			ResultSet rBranch = con.createStatement().executeQuery(qBranch);
			while (rBranch.next()) {
				comboBox.addItem(rBranch.getString("name"));
			}
		} catch (Exception e) {
			System.err.println("ERROR: "+e);
		}
		
		JButton checkout = new JButton("check out");
		checkout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String idd = textField.getText();
				String uid = reader.textField.getText();
				String branchName = comboBox.getSelectedItem().toString();
				String branchid="";
				
				String quu = "SELECT * FROM borrow WHERE rdate = null and bookid = '" + idd + "'";
				try {
					ResultSet rB = con.createStatement().executeQuery(quu);
					if(rB.next())
					{
						JOptionPane.showConfirmDialog(null, "Someone has borrowed the book!","", JOptionPane.YES_NO_OPTION); 
					}
					else {
						String query1 = "SELECT * FROM branch WHERE name = '" + branchName + "'";
						ResultSet rBranch;
						try {
							rBranch = con.createStatement().executeQuery(query1);
							while (rBranch.next()) {
								branchid = rBranch.getString("branchid");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}				
						String query = "INSERT INTO borrow (bookid ,readerid ,branchid , bdate)"
		    					+ "VALUES (?, ?, ?, ?)";
						
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				        Date date = new Date();
				        String datef = dateFormat.format(date);
				         
				        PreparedStatement ps;
						try {
							ps = con.prepareStatement(query);
							ps.setString(1, idd);
					        ps.setString(2, uid);
					        ps.setString(3, branchid);
					        ps.setString(4, datef);
					        ps.execute();
					        JOptionPane.showConfirmDialog(null, "Book Borrowed!","", JOptionPane.YES_NO_OPTION); 
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showConfirmDialog(null, "Can not Borrow the book!","", JOptionPane.YES_NO_OPTION);
						}
						 
						String del = "Delete from reserve where bookid =" + idd +";";
						try {
							con.createStatement().executeUpdate(del);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		checkout.setBounds(98, 138, 108, 37);
		contentPane.add(checkout);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(98, 199, 108, 26);
		contentPane.add(btnNewButton);
	}
}
