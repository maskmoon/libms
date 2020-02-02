package lms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
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

public class top10reader extends JFrame {

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
					top10reader frame = new top10reader();
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
	public top10reader() {
		
		con = makeConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(106, 40, 104, 40);
		contentPane.add(comboBox);
		comboBox.addItem("Brooklyn");
		comboBox.addItem("Queens");
		comboBox.addItem("Manhattan");
		
		JLabel lblBranch = new JLabel("Select Branch");
		lblBranch.setBounds(25, 40, 65, 40);
		contentPane.add(lblBranch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 91, 399, 159);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textArea.setText("");
				int index = comboBox.getSelectedIndex();
				String query = null;
				if(index == 0) 
				{
					query = "SELECT readerid, COUNT(*) as count FROM borrow WHERE branchid = 1 GROUP BY readerid ORDER BY count DESC;";
				}
				if(index == 1)
				{
					query = "SELECT readerid, COUNT(*) as count FROM borrow WHERE branchid = 2 GROUP BY readerid ORDER BY count DESC;";
				}
				if(index == 2)
				{
					query = "SELECT readerid, COUNT(*) as count FROM borrow WHERE branchid = 3 GROUP BY readerid ORDER BY count DESC;";
				}
				try {
		            st = con.createStatement();
		            ResultSet r1 = con.createStatement().executeQuery(query);
		            while(r1.next()) {
		            textArea.append(r1.getString("readerid") + " | " +r1.getString("count")); 
		            textArea.append("\n");
		            }
		            }
		        catch (SQLException ex) {
		            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
		        }
			}
		});
		
		btnNewButton.setBounds(220, 39, 104, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("back");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(363, 11, 71, 23);
		contentPane.add(btnNewButton_1);
	}
}
