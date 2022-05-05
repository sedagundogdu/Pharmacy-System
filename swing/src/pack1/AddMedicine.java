package pack1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddMedicine extends JFrame {

	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtmedName;
	private JTextField txtmanifactu;
	private JTextField txtpiece;
	private JTextField txtprice;
	private JLabel lblManifac;
	private JComboBox cbCatogory;
	private JLabel lblNewLabel_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMedicine frame = new AddMedicine();
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
	public AddMedicine() throws SQLException {
		
		setTitle("Medicine Addition");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 384, 480);
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
		btnBack.setBounds(10, 412, 131, 21);
		contentPane.add(btnBack);
		
		txtid = new JTextField();
		txtid.setBounds(107, 29, 117, 19);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		txtmedName = new JTextField();
		txtmedName.setBounds(107, 82, 117, 19);
		contentPane.add(txtmedName);
		txtmedName.setColumns(10);
		
		txtmanifactu = new JTextField();
		txtmanifactu.setBounds(107, 141, 117, 19);
		contentPane.add(txtmanifactu);
		txtmanifactu.setColumns(10);
		
		txtpiece = new JTextField();
		txtpiece.setBounds(107, 187, 117, 19);
		contentPane.add(txtpiece);
		txtpiece.setColumns(10);
		
		txtprice = new JTextField();
		txtprice.setBounds(107, 238, 117, 19);
		contentPane.add(txtprice);
		txtprice.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ID");
		//lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 32, 45, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Piece");
		//lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 190, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Price");
		//lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(10, 241, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Medicine Name");
		//lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(10, 85, 87, 13);
		
		DefaultComboBoxModel<String> model_catogory =new DefaultComboBoxModel<String>();
		cbCatogory = new JComboBox(model_catogory);
		cbCatogory.setBounds(107, 286, 117, 21);
		contentPane.add(cbCatogory);
		
		DbConnection db = new DbConnection();
		ResultSet rs =db.getCatogory();
		
		int column = rs.getMetaData().getColumnCount();
		
		while(rs.next()) {
			
			for(int i =1;i<=column;i++) {
				String catogory= rs.getString(i);
				model_catogory.addElement(catogory);
				
			}

		}
		

		lblNewLabel_4 = new JLabel("Catogory");
		//lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(10, 290, 87, 13);
		contentPane.add(lblNewLabel_4);
		contentPane.add(lblNewLabel_3);
		
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DbConnection db = new DbConnection();
				medicine mdc=new medicine();
				
				mdc.barcode=Integer.parseInt(txtid.getText());
				mdc.medicine_name=txtmedName.getText();
				mdc.manufacturer=txtmanifactu.getText();
				mdc.piece=Integer.parseInt(txtpiece.getText());
				mdc.price=Integer.parseInt(txtprice.getText());
				mdc.catogory=cbCatogory.getSelectedItem().toString();
				
				try {
					
					db.saveMedicine(mdc);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}
		});
		btnSave.setBounds(229, 412, 131, 21);
		contentPane.add(btnSave);
		
		lblManifac = new JLabel("Manufacturer");
		//lblManifac.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblManifac.setBounds(10, 144, 75, 13);
		contentPane.add(lblManifac);
		
	}

}
