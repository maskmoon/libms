package lms;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class admin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	private Connection con;
	private Statement st;
    private ResultSet rs;

    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin frame = new admin();
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
	
		
	public admin() {
		con = makeConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Input your account");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(47, 56, 162, 41);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(47, 135, 136, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Input your password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(239, 58, 141, 36);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(254, 135, 126, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button = new JButton("sign in");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			String nn = textField.getText();
			String pass = textField_1.getText();
			String query = "select password from admin where id ='"+ nn +"'";
			try {
	            st = con.createStatement();

	            ResultSet r = st.executeQuery(query);

	            if(r.next() == false){
	                JOptionPane op = new JOptionPane();
	                op.setMessage("The admin "+nn+" doesn't exist  ");
	                op.setMessageType(0);
	                JDialog dia = op.createDialog(null,"Error");
	                dia.setTitle("LOGIN ERROR");
	                dia.setVisible(true);
	            }else{
	            	if (r.getString("password").equals(pass)) 
	            	{
	            		dispose();
		                AdminSub us = new AdminSub();
		                us.setVisible(true);
	            	}
	            	else 
	            	{
	            		JOptionPane op = new JOptionPane();
	            		op.setMessage("password error");
	            	}
	            		
	                
	                //UserSub us = new UserSub(this.con, uid);
	                //us.setVisible(true);
	                
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
	        }			
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button.setBounds(267, 197, 89, 23);
		contentPane.add(button);
		
		JButton btnBackToMain = new JButton("back to main");
		btnBackToMain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				login us = new login();
                us.setVisible(true);
			}
		});
		btnBackToMain.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBackToMain.setBounds(61, 199, 122, 23);
		contentPane.add(btnBackToMain);
	}

}

