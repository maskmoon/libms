package lms;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.Box;
import lms.reader;

public class UserSub extends JFrame {

	private JPanel contentPane;
	private Statement st;
    Connection con;
    private JTextField textField;
    
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserSub frame = new UserSub();
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
	
	public UserSub() {
		con = makeConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(403, 51, 109, 29);
		contentPane.add(comboBox);
		comboBox.addItem("Book Name");
		comboBox.addItem("ISBN");
		comboBox.addItem("publisher");
		
		textField = new JTextField();
		textField.setBounds(233, 51, 109, 29);
		contentPane.add(textField);
		textField.setColumns(10);
			
		JButton reservebook = new JButton("reserve book");
		reservebook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
			}
		});

		reservebook.setBounds(32, 73, 105, 29);
		contentPane.add(reservebook);
		
		JButton reservelist = new JButton("reserve list");
		reservelist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		reservelist.setBounds(32, 175, 105, 29);
		contentPane.add(reservelist);
		
		JButton bookreturn = new JButton("book return");
		bookreturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		bookreturn.setBounds(32, 277, 105, 29);
		contentPane.add(bookreturn);
		
		JButton bookcheckout = new JButton("book check out");
		bookcheckout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		bookcheckout.setBounds(32, 379, 105, 29);
		contentPane.add(bookcheckout);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(182, 132, 502, 339);
		contentPane.add(scrollPane);
		
		JTextArea output = new JTextArea();
		scrollPane.setViewportView(output);


		//output.append("isbn" + " | " +"title"+ " | " +"publisherid"+ " | " +"authorid"+ " | " +"publicationdate"+ " | " + " bookid" + "\n");
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addMouseListener(new MouseAdapter() {
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
					output.append("isbn" + " | " +"title"+ " | " +"publisherid"+ " | " +"authorid"+ " | " +"publicationdate"+ " | " + "Available bookid" + "\n");
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
		            output.append("\n");
		            }
		        } catch (SQLException ex) {
		            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
		        }
			}
		});
		btnNewButton.setBounds(567, 51, 109, 29);
		contentPane.add(btnNewButton);
		
		reservebook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserReserve res = new UserReserve();
				res.setVisible(true);
			}
		});
		
		reservelist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				output.setText("");
				String data =  reader.textField.getText();
				String query = "select * from reserve where readerid =" + data + ";";
				//output.append(query);
				try {
		            st = con.createStatement();
		            ResultSet r1 = con.createStatement().executeQuery(query);
		            while(r1.next()) {
		            //output.append(query);
		            output.append(r1.getString("readerid") + " | " +r1.getString("bookid")+ " | " +r1.getString("date")+"\n");           
		            }
		        } catch (SQLException ex) {
		            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
		        }
			}
		});
		
		bookcheckout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BookCheckout check = new BookCheckout();
				check.setVisible(true);
			}
		});
		
		bookreturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Bookreturn bkr = new Bookreturn();
				bkr.setVisible(true);
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(160, 469, -12, -446);
		contentPane.add(separator);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}
}
