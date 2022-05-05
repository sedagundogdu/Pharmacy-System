package pack1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		
		setTitle("MENU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 431);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogoOut = new JButton("Log Out");
		btnLogoOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Login login =new Login();
					login.setVisible(true);
					dispose();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLogoOut.setBounds(10, 365, 85, 21);
		contentPane.add(btnLogoOut);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(372, 365, 85, 21);
		contentPane.add(btnExit);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(267, 21, 190, 155);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setVisible(false);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBounds(267, 215, 190, 155);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(false);

		JButton btnPatient = new JButton("Patient Procedures");
		btnPatient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setVisible(true);
			}
		});
		btnPatient.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPatient.setBounds(24, 68, 174, 52);
		contentPane.add(btnPatient);

		JButton btnMedicine = new JButton("Medicine Procedures");
		btnMedicine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_1.setVisible(true);
			}
		});
		btnMedicine.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnMedicine.setBounds(24, 256, 174, 52);
		contentPane.add(btnMedicine);
		
		JButton btnNewButton = new JButton("Patient Registrataion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PatientRegistration pg;
				try {
					
					pg = new PatientRegistration();
					pg.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(10, 46, 170, 21);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Patient Listing");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PatientListing pl;
				pl = new PatientListing();
				pl.setVisible(true);

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_1.setBounds(10, 95, 170, 21);
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Add  Medicine");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddMedicine addmed;
				try {
					addmed = new AddMedicine();
					addmed.setVisible(true);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_2.setBounds(10, 37, 170, 21);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Medicine Listing");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MedicineListing mdcls ;
				mdcls=new MedicineListing();
				mdcls.setVisible(true);
				
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_3.setBounds(10, 85, 170, 21);
		panel_1.add(btnNewButton_3);

	}
}
