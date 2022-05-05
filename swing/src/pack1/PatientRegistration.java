package pack1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PatientRegistration extends JFrame {

	private JPanel contentPane;
	private JTextField txtTC;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtPhoneNumber;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientRegistration frame = new PatientRegistration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public PatientRegistration() throws SQLException {
		
		setTitle("Patient Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 555);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Back to Menu");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(10, 487, 131, 21);
		contentPane.add(btnBack);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblName.setBounds(10, 70, 67, 13);
		contentPane.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSurname.setBounds(10, 110, 82, 13);
		contentPane.add(lblSurname);
		
		JLabel lblTC = new JLabel("T.C.");
		lblTC.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTC.setBounds(10, 30, 45, 13);
		contentPane.add(lblTC);
		
		JLabel lblBirthYear = new JLabel("Birth Year");
		lblBirthYear.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBirthYear.setBounds(10, 150, 67, 13);
		contentPane.add(lblBirthYear);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblGender.setBounds(10, 190, 67, 13);
		contentPane.add(lblGender);
		
		JLabel lblSocial = new JLabel("Social \u0130nsurance");
		lblSocial.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSocial.setBounds(10, 270, 111, 13);
		contentPane.add(lblSocial);
		
		JLabel lblPhone = new JLabel("Phone Number");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPhone.setBounds(10, 322, 91, 13);
		contentPane.add(lblPhone);
		
		txtTC = new JTextField();
		txtTC.setBounds(136, 27, 96, 19);
		contentPane.add(txtTC);
		txtTC.setColumns(10);
		
		txtName = new JTextField();
		txtName.setBounds(136, 67, 96, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtSurname = new JTextField();
		txtSurname.setBounds(136, 107, 96, 19);
		contentPane.add(txtSurname);
		txtSurname.setColumns(10);
		
		JComboBox cbBirthYear = new JComboBox();
		cbBirthYear.setBounds(136, 146, 96, 21);
		contentPane.add(cbBirthYear);
		
		for(int i=2022; i>=1930;i--) {
			cbBirthYear.addItem(i);
		}
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(136, 186, 103, 21);
		contentPane.add(rdbtnMale);
		rdbtnMale.setActionCommand("Male");

		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(136, 219, 103, 21);
		contentPane.add(rdbtnFemale);
		rdbtnFemale.setActionCommand("Female");

		
		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(rdbtnFemale);
		genderGroup.add(rdbtnMale);
		
		JComboBox cbSocial = new JComboBox();
		cbSocial.setModel(new DefaultComboBoxModel(new String[]{"SGK","Pension Fund","None"}));
		cbSocial.setBounds(136, 268, 96, 21);
		contentPane.add(cbSocial);

		DefaultComboBoxModel<String> model =new DefaultComboBoxModel<String>();

		JComboBox cbMedicine = new JComboBox(model);
		
		cbMedicine.setBounds(136, 374, 96, 21);
		contentPane.add(cbMedicine);
		
		DbConnection db =new DbConnection();
		ResultSet rs =db.getCatogorytoPatient();
		
		int column = rs.getMetaData().getColumnCount();
		while(rs.next()) {
			
		for(int i=1 ; i<=column ; i++) {
			
			String taken_medicine = rs.getString(i);
			model.addElement(taken_medicine);
		   }
		}
		
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setBounds(136, 319, 96, 19);
		contentPane.add(txtPhoneNumber);
		txtPhoneNumber.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Patient pt =new Patient();
				DbConnection db =new DbConnection();

				pt.tc= Integer.parseInt(txtTC.getText());
				pt.name=txtName.getText();
			    pt.surname=txtSurname.getText();
			    pt.birth_year=Integer.parseInt(cbBirthYear.getSelectedItem().toString());
			    pt.gender=genderGroup.getSelection().getActionCommand();
			    pt.social=cbSocial.getSelectedItem().toString();
			    pt.phone_num=Integer.parseInt(txtPhoneNumber.getText());
			    pt.given_medicine=cbMedicine.getSelectedItem().toString();
			    

			   try {
				   
				db.savePatient(pt);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			}
		});
		btnSave.setBounds(270, 487, 131, 21);
		contentPane.add(btnSave);
		
		JLabel lblNewLabel = new JLabel("Taken Medication");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 378, 91, 13);
		contentPane.add(lblNewLabel);
	}

}
