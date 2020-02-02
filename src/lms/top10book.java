package lms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Font;

public class top10book extends JFrame {

	private JPanel contentPane;
	private Statement st;
    Connection con;
    String uid;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					top10book frame = new top10book();
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
	public top10book() {
		con = makeConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectBranch = new JLabel("Select Branch");
		lblSelectBranch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSelectBranch.setBounds(25, 41, 98, 23);
		contentPane.add(lblSelectBranch);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setBounds(133, 39, 96, 26);
		contentPane.add(comboBox);
		comboBox.addItem("Brooklyn");
		comboBox.addItem("Queens");
		comboBox.addItem("Manhattan");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 93, 414, 157);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.append("title" + " | " +"isbn" + " | " +"times" + "\n");
		
		JButton btnNewButton = new JButton("search");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText("");
				int index = comboBox.getSelectedIndex();
				String query = null;
				if(index == 0) 
				{
					query = "select bookinfo.isbn,bookinfo.title,count(*) from borrow,book,bookinfo where bookinfo.isbn = book.isbn and borrow.bookid = book.bookid and borrow.branchid = 1 GROUP BY book.isbn ORDER BY count(*) DESC;";
				}
				if(index == 1)
				{
					query = "select bookinfo.isbn,bookinfo.title,count(*) from borrow,book,bookinfo where bookinfo.isbn = book.isbn and borrow.bookid = book.bookid and borrow.branchid = 2 GROUP BY book.isbn ORDER BY count(*) DESC;";
				}
				if(index == 2)
				{
					query = "select bookinfo.isbn,bookinfo.title,count(*) from borrow,book,bookinfo where bookinfo.isbn = book.isbn and borrow.bookid = book.bookid and borrow.branchid = 3 GROUP BY book.isbn ORDER BY count(*) DESC;";
				}
				try {
		            st = con.createStatement();
		            ResultSet r1 = con.createStatement().executeQuery(query);
		            while(r1.next()) {
		            textArea.append(r1.getString("title") + " | " +r1.getString("isbn") + " | " +r1.getString("count(*)"));
		            textArea.append("\n");
		            }
		            }
		        catch (SQLException ex) {
		            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
		        }
			}
		});
		btnNewButton.setBounds(250, 37, 96, 31);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("back");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(369, 0, 55, 23);
		contentPane.add(btnNewButton_1);
		

	}

}
