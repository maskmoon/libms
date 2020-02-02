package lms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.sql.*;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class reader extends JFrame {

	private JPanel contentPane;
	static JTextField textField;
	private Statement s;
    Connection con;
    String uid;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reader frame = new reader();
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
           s = con.createStatement();
           
       }catch(Exception e){
           System.err.println("ERROR: "+e);
       }
		return con;}
	/**
	 * Create the frame.
	 */
	public reader() {
		con = makeConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 205, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(20, 74, 133, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPleaseInputYour = new JLabel("Please input your reader ID");
		lblPleaseInputYour.setBounds(15, 37, 143, 20);
		contentPane.add(lblPleaseInputYour);
		
		JButton btnNewButton = new JButton("sign in");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				uid=textField.getText();
				String query = "select * from reader where readerid ='"+ uid +"'";
				try {
					
		            s = con.createStatement();
		            ResultSet r = s.executeQuery(query);
		            if(r.next() == false){
		                JOptionPane op = new JOptionPane();
		                op.setMessage("The user "+uid+" doesn't exist  ");
		                op.setMessageType(0);
		                JDialog dia = op.createDialog(null,"Error");
		                dia.setTitle("LOGIN ERROR");
		                dia.setVisible(true);
		            }else{
		                //dispose();
		                UserSub us = new UserSub();
		                us.setVisible(true);
		                }
		        } catch (SQLException ex) {
		            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
		        } 
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(42, 116, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back to main");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				login us = new login();
                us.setVisible(true);
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(31, 150, 111, 23);
		contentPane.add(btnNewButton_1);
	}
}
