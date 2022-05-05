package pack1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtusername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public Login() throws IOException {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 416, 345);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsername.setBounds(10, 57, 68, 13);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(10, 98, 68, 13);
		contentPane.add(lblPassword);

		txtusername = new JTextField();
		txtusername.setBounds(110, 55, 96, 19);
		contentPane.add(txtusername);
		txtusername.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(110, 96, 96, 19);
		contentPane.add(passwordField);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = txtusername.getText();
				String password = new String(passwordField.getPassword());

				DbConnection db =new DbConnection();
				
				try {
					
					ResultSet rs = db.getResult(username, password);
					if(db.checkLogin(username, password)) {
						Menu menu = new Menu();
						menu.setVisible(true);
					}
					
					else {
						JOptionPane.showMessageDialog(null, "Wrong Username or Password","Login Failed!" ,JOptionPane.ERROR_MESSAGE);

					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(110, 152, 96, 21);
		contentPane.add(btnLogin);
		dispose();
		
		File imageg01 =new File("./pics/5.jpg");//1 for image
		Image img01 = ImageIO.read(imageg01);  //2 for image
		ImageIcon icon =new ImageIcon(img01);//3 for image

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(231, 28, 150, 245);
		contentPane.add(lblNewLabel);

		lblNewLabel.setIcon(icon);//4 for image


	}

}

