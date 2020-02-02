package lms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import lms.reader;
import javax.swing.JLabel;

public class UserReserve extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private Statement st;
    Connection con;
    private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserReserve frame = new UserReserve();
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
	public UserReserve() {
		con = makeConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 261, 232);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(72, 53, 99, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("reserve");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String sss = textField.getText();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            Date date = new Date();
	            String df = dateFormat.format(date);
	            String sql = "INSERT INTO reserve (readerid, bookid, date)"
    					+ "VALUES (?, ?, ?)";
	        	PreparedStatement ps;
	        	String strr = reader.textField.getText();
				try {
					ps = con.prepareStatement(sql);
		        	ps.setString(1, strr);
		        	ps.setString(2, sss);
		        	ps.setString(3, df);
		        	ps.execute();
				} catch (SQLException e1) {
					JOptionPane.showConfirmDialog(null, "reserve failure", strr, JOptionPane.YES_NO_OPTION);
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(72, 111, 99, 30);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(72, 152, 99, 30);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Please input the Bookid");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(46, 11, 154, 30);
		contentPane.add(lblNewLabel);
	}
}
