package lms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
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

public class AdminSub extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
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
					AdminSub frame = new AdminSub();
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
	public AdminSub() {
		con = makeConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Addbook res = new Addbook();
				res.setVisible(true);
			}
		});
		btnAddBook.setBounds(10, 52, 103, 48);
		contentPane.add(btnAddBook);
		
		JButton btnAddReader = new JButton("Add Reader");
		btnAddReader.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Addreader ar = new Addreader();
				ar.setVisible(true);
			}
		});
		btnAddReader.setBounds(10, 152, 103, 48);
		contentPane.add(btnAddReader);
		
		JButton btnPrintTop_1 = new JButton("Print top frequent ");
		btnPrintTop_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				top10book topb = new top10book();
				topb.setVisible(true);
			}
		});
		btnPrintTop_1.setBounds(10, 252, 103, 48);
		contentPane.add(btnPrintTop_1);
		
		JButton btnPrintTop = new JButton("Print top 10 most borrowed ");
		btnPrintTop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				top10reader topr = new top10reader();
				topr.setVisible(true);
			}
		});
		btnPrintTop.setBounds(10, 352, 103, 48);
		contentPane.add(btnPrintTop);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(161, 44, 109, 29);
		contentPane.add(textField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(145, 103, 459, 323);
		contentPane.add(scrollPane);
		
		JTextArea output = new JTextArea();
		scrollPane.setViewportView(output);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(331, 44, 109, 29);
		contentPane.add(comboBox);
		contentPane.add(comboBox);
		comboBox.addItem("Book Name");
		comboBox.addItem("ISBN");
		comboBox.addItem("publisher");
		
		//output.append("isbn" + " | " +"title"+ " | " +"publisherid"+ " | " +"authorid"+ " | " +"publicationdate"+ " | " + " bookid in the library"+ " | " + " All bookid of the book" + "\n");
		JButton button = new JButton("Search");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = comboBox.getSelectedIndex();
				String data = textField.getText();
				String query = "111";
				if(index == 0) 
				{
					query = "select * from bookinfo where title like '%"+ data +"%' ;";
				}
				if(index == 1)
				{
					query = "select * from bookinfo where isbn ="+ data +";";
				}
				if(index == 2)
				{
					query = "select * from bookinfo where publisherid ="+ data +";";
				}
				try {
		            output.setText("");
		            output.append("isbn" + " | " +"title"+ " | " +"publisherid"+ " | " +"authorid"+ " | " +"publicationdate"+ " | " + " bookid in the library"+ " | " + " All bookid of the book" + "\n");
		            st = con.createStatement();
		            ResultSet r1 = con.createStatement().executeQuery(query);
		            while(r1.next()) {
		            //output.append(query);
		            output.append(r1.getString("isbn") + " | " +r1.getString("title")+ " | " +r1.getString("publisherid")+ " | " +r1.getString("authorid")+ " | " +r1.getString("publicationdate")+ " | ");           
		            String que = "Select distinct book.bookid from book,borrow where borrow.bookid <> book.bookid and isbn = " + r1.getString("isbn") +";";
		            ResultSet r2 = con.createStatement().executeQuery(que);
		            while(r2.next()) {
		            	output.append(r2.getString("book.bookid")+",");		            	
		            }
		            String quee = "Select bookid from book where isbn = " + r1.getString("isbn") +";";
		            ResultSet r3 = con.createStatement().executeQuery(quee);
		            output.append("|");
		            while(r3.next()) {
		            	output.append(r3.getString("book.bookid")+",");	
		            }
		            output.append("\n");
		            }
		        } catch (SQLException ex) {
		            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
		        }
			}
		});
		button.setBounds(484, 44, 109, 29);
		contentPane.add(button);
		btnAddReader.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Addreader add = new Addreader();
				add.setVisible(true);
			}
		});
	}
}
