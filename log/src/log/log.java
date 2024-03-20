package log;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class log {

	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					log window = new log();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public log() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" login page");
		lblNewLabel.setBounds(174, 11, 70, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel tb1 = new JLabel(" name");
		tb1.setBounds(46, 73, 46, 14);
		frame.getContentPane().add(tb1);
		
		JLabel p1 = new JLabel("password");
		p1.setBounds(46, 122, 46, 14);
		frame.getContentPane().add(p1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(174, 70, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(174, 119, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton(" submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=tb1.getText();
				String password=p1.getText();
				try {
					Connection con =DriverManager.getConnection
							("jdbc:mysql://localhost:3306/aimlc","root","mrec");
					PreparedStatement stn=con.prepareStatement
							("select name,password from login where name=? and password=?");
					stn.setString(1, name);
					stn.setString(2, password);
					ResultSet rs=stn.executeQuery();
					if(rs.next())
					{
						JOptionPane.showMessageDialog(btnNewButton, "valid user");
					}
					else
					{
						JOptionPane.showMessageDialog(btnNewButton, "invalid user");
					}
							
				}
				catch(SQLException e1)
				{
					e1.printStackTrace();
					
				}
				
			}
			
		});
		btnNewButton.setBounds(131, 187, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\online\\Desktop\\tree-736885_1280.jpg"));
		lblNewLabel_1.setBounds(0, 0, 424, 250);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
