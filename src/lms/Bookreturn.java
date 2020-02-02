package lms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Font;

public class Bookreturn extends JFrame {

	private JPanel contentPane;
	private Statement st;
    Connection con;
    String uid = reader.textField.getText();
    private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bookreturn frame = new Bookreturn();
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
	 * @throws SQLException 
	 */
	public Bookreturn(){
		con = makeConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 415, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton returnbu = new JButton("return");
		returnbu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		returnbu.setBounds(39, 176, 114, 38);
		contentPane.add(returnbu);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(10, 260, 89, 23);
		contentPane.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(39, 96, 114, 38);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblInputTheBookid = new JLabel("Input the bookid you want to return");
		lblInputTheBookid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInputTheBookid.setBounds(57, 18, 299, 38);
		contentPane.add(lblInputTheBookid);
		
		JTextArea booklist = new JTextArea();
		booklist.setBounds(204, 67, 185, 216);
		contentPane.add(booklist);
		booklist.append("title"+ " bookid" + "\n");
		
		String uid = reader.textField.getText();
		String qBranch = "SELECT borrow.bookid,title FROM borrow,book,bookinfo where borrow.rdate is null and book.bookid = borrow.bookid and book.isbn = bookinfo.isbn and readerid ="+ uid + ";";
		try {
			ResultSet r1 = con.createStatement().executeQuery(qBranch);
			while (r1.next()) {
				booklist.append(r1.getString("title") + " | " );
				booklist.append(r1.getString("borrow.bookid") + ",");
				booklist.append("\n");
			}
		} catch (SQLException e1) {
			JOptionPane.showConfirmDialog(null, "You have no book to return","", JOptionPane.YES_NO_OPTION);
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		returnbu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        Date date = new Date();
		        String datef = dateFormat.format(date);
				String idd = textField.getText();
				String quu = "UPDATE borrow SET rdate = '" + datef + "' WHERE bookid = " + idd +";";
				//JOptionPane.showConfirmDialog(null, quu,"", JOptionPane.YES_NO_OPTION);
				try {
					con.createStatement().executeUpdate(quu);
					JOptionPane.showConfirmDialog(null, "Return success","", JOptionPane.YES_NO_OPTION); 
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
