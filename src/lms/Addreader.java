package lms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Addreader extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField address;
	private JTextField phone;
	private JButton btnNewButton;
	private Statement st;
    Connection con;
    String uid;
    private JButton btnBack;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Addreader frame = new Addreader();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	private Connection makeConnection() {
        try{
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "jinzh19941113");
           st = con.createStatement();
           
       }catch(Exception e){
           System.err.println("ERROR: "+e);
       }
		return con;}
	
	public Addreader() {
		con = makeConnection();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 335, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		name = new JTextField();
		name.setFont(new Font("Dialog", Font.PLAIN, 14));
		name.setBounds(20, 44, 106, 32);
		contentPane.add(name);
		name.setColumns(10);
		
		address = new JTextField();
		address.setFont(new Font("Dialog", Font.PLAIN, 14));
		address.setColumns(10);
		address.setBounds(20, 128, 106, 32);
		contentPane.add(address);
		
		phone = new JTextField();
		phone.setFont(new Font("Dialog", Font.PLAIN, 14));
		phone.setColumns(10);
		phone.setBounds(20, 212, 106, 32);
		contentPane.add(phone);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblName.setBounds(20, 11, 76, 22);
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("address");
		lblAddress.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblAddress.setBounds(20, 95, 65, 22);
		contentPane.add(lblAddress);
		
		JLabel lblDate = new JLabel("phone");
		lblDate.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblDate.setBounds(20, 179, 65, 22);
		contentPane.add(lblDate);
		
		btnNewButton = new JButton("Add reader");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(166, 45, 104, 29);
		contentPane.add(btnNewButton);
		
		btnBack = new JButton("back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(220, 239, 89, 23);
		contentPane.add(btnBack);
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str1 = name.getText();
				String str2 = address.getText();
				String str3 = phone.getText();
				String quu = "Select max(readerid) from reader ;";
				try {
					st = con.createStatement();
					ResultSet r1 = con.createStatement().executeQuery(quu);
					while(r1.next()) {
			            int a =Integer.parseInt(r1.getString("max(readerid)"));
			            a = a + 1;
			            String id = Integer.toString(a);
			            String query = "INSERT INTO reader (readerid ,name ,address , phone)"
		    					+ "VALUES (?, ?, ?, ?)";
			            PreparedStatement ps;
						try {
							ps = con.prepareStatement(query);
							ps.setString(1, id);
					        ps.setString(2, str1);
					        ps.setString(3, str2);
					        ps.setString(4, str3);
					        ps.execute();
					        JOptionPane.showConfirmDialog(null, "Add success and your id is " + id,"", JOptionPane.YES_NO_OPTION); 
						} catch (SQLException e1) {
							JOptionPane.showConfirmDialog(null, "Can not add reader","", JOptionPane.YES_NO_OPTION);
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			            }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

}
