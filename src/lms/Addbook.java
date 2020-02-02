package lms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Addbook extends JFrame {

	private JPanel contentPane;
	private JTextField isbntext;
	private JTextField titletext;
	private JTextField authortext;
	private JTextField datetext;
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
					Addbook frame = new Addbook();
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
	public Addbook() {
		
		con = makeConnection();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 358, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		isbntext = new JTextField();
		isbntext.setBounds(87, 31, 93, 29);
		contentPane.add(isbntext);
		isbntext.setColumns(10);
		
		titletext = new JTextField();
		titletext.setBounds(87, 90, 93, 29);
		contentPane.add(titletext);
		titletext.setColumns(10);
		
		authortext = new JTextField();
		authortext.setBounds(87, 149, 93, 29);
		contentPane.add(authortext);
		authortext.setColumns(10);
		
		datetext = new JTextField();
		datetext.setText("yyyy-mm-dd");
		datetext.setToolTipText("yyyy-mm-dd");
		datetext.setBounds(87, 267, 93, 29);
		contentPane.add(datetext);
		datetext.setColumns(10);
		
		JComboBox branchBox = new JComboBox();
		branchBox.setBounds(87, 208, 93, 29);
		contentPane.add(branchBox);
		branchBox.addItem("Manhattan");
		branchBox.addItem("Queens");
		branchBox.addItem("Brooklyn");
		
		JComboBox pubBox = new JComboBox();
		pubBox.setBounds(87, 326, 93, 29);
		contentPane.add(pubBox);
		pubBox.addItem("pub1");
		pubBox.addItem("pub2");
		pubBox.addItem("pub3");
		pubBox.addItem("pub4");
		pubBox.addItem("pub5");
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(10, 38, 46, 14);
		contentPane.add(lblIsbn);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(10, 97, 46, 14);
		contentPane.add(lblTitle);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(10, 156, 46, 14);
		contentPane.add(lblAuthor);
		
		JLabel lblSelectBranch = new JLabel("Select branch");
		lblSelectBranch.setBounds(10, 215, 67, 14);
		contentPane.add(lblSelectBranch);
		
		JLabel lblNewLabel = new JLabel("date");
		lblNewLabel.setBounds(10, 274, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("publisher");
		lblNewLabel_1.setBounds(10, 333, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Addbook");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNewButton.setBounds(221, 145, 93, 37);
		contentPane.add(btnNewButton);
		
		JButton btnBack = new JButton("back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setBounds(243, 353, 89, 23);
		contentPane.add(btnBack);
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String isbn = isbntext.getText();
				String title = titletext.getText();
				String authorid = "";
				String authorName = authortext.getText();
				String pubid = "";
				String pubName = pubBox.getSelectedItem().toString();
				String pubDate = datetext.getText();
				String branchid = "";
				String branchName = branchBox.getSelectedItem().toString();
				String bookid = "";
				try {
										
					String qAuthor = "SELECT * FROM author WHERE name = '" + authorName + "'";
					ResultSet rAuthor = con.createStatement().executeQuery(qAuthor);
					if (rAuthor.next()) {
						authorid = rAuthor.getString("authorid");
					}
					else {
						qAuthor = "SELECT * FROM author ORDER BY authorid DESC";
						rAuthor = con.createStatement().executeQuery(qAuthor);
						if (rAuthor.next()) {
							authorid = Integer.toString(Integer.parseInt(rAuthor.getString("authorid")) + 1);
						}
						String sqlAuthor = "INSERT INTO author (authorid, name)"
										+"VALUES (?, ?)";
			        	PreparedStatement psAuthor = con.prepareStatement(sqlAuthor);
			        	psAuthor.setString(1, authorid);
			        	psAuthor.setString(2, authorName);
			        	psAuthor.execute();
					}
					
					String qPub = "SELECT * FROM publisher WHERE publishername = '" + pubName + "'";
					ResultSet rPub = con.createStatement().executeQuery(qPub);
					if (rPub.next()) {
						pubid = rPub.getString("publisherid");
					}
					
					String qBranch = "SELECT * FROM branch WHERE name = '" + branchName + "'";
					ResultSet rBranch = con.createStatement().executeQuery(qBranch);
					if (rBranch.next()) {
						branchid = rBranch.getString("branchid");
					}
					
		    		String qBook = "SELECT * FROM book ORDER BY bookid DESC";
		    		ResultSet rBook = con.createStatement().executeQuery(qBook);
		    		if (rBook.next()) {
		        		bookid = Integer.toString(Integer.parseInt(rBook.getString("bookid")) + 1);
					}
		    		
		    		
		        	String sqlBook = "INSERT INTO book (isbn, bookid)"
							+ "VALUES (?, ?)";
		        	PreparedStatement psBook = con.prepareStatement(sqlBook);
		        	psBook.setString(1, isbn);
		        	psBook.setString(2, bookid);
		        	psBook.execute();
		        		        	
		        	String qCopy = "SELECT * FROM copy WHERE branchid = '" + branchid + "' AND isbn = '" + isbn + "'";
		        	ResultSet rCopy = con.createStatement().executeQuery(qCopy);
		        	
		        	if (rCopy.next()){
						String sqlCopy = "UPDATE copy SET numcopy = numcopy + 1 WHERE isbn = '" + isbn
										+ "' AND branchid = '" + branchid + "'";
						con.createStatement().execute(sqlCopy);
					}
		        	
		        	else {
		            	String sqlCopy = "INSERT INTO copy (branchid, isbn, numcopy)"
		            			+ "VALUES (?, ?, ?)";
		            	PreparedStatement psCopy = con.prepareStatement(sqlCopy);
		            	psCopy.setString(1, branchid);
		            	psCopy.setString(2, isbn);
		            	psCopy.setString(3, "1");
		            	psCopy.execute();
					}
		        	
		        	String quu = "Select max(position) from location ;";
		        	st = con.createStatement();
					ResultSet r1 = con.createStatement().executeQuery(quu);
					int a = 0;
		        	while(r1.next()) {
			            a =Integer.parseInt(r1.getString("max(position)"));
			            a = a + 1;
			            }
		        	
		        	String sqlLocation = "INSERT INTO location (bookid, branchid, position)"
		        			+ "VALUES (?, ?, ?)";
		        	PreparedStatement psLocation = con.prepareStatement(sqlLocation);
		        	psLocation.setString(1, bookid);
		        	psLocation.setString(2, branchid);
		        	psLocation.setString(3, Integer.toString(a));
		        	psLocation.execute();
		        	
		        	String sqlBookinfo = "INSERT INTO bookinfo (isbn, title, publisherid, publicationdate, authorid)"
			    						+ "VALUES (?, ?, ?, ?, ?)";
			    	PreparedStatement psBookinfo = con.prepareStatement(sqlBookinfo);
			    	psBookinfo.setString(1, isbn);
			    	psBookinfo.setString(2, title);
			    	psBookinfo.setString(3, pubid);
			    	psBookinfo.setString(4, pubDate);
			    	psBookinfo.setString(5, authorid);
			    	psBookinfo.execute();
					
				} catch (Exception e1) {
					System.err.println("ERROR: "+e1);
				}
				}
		});
	}
}
