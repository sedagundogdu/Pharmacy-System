package pack1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class MedicineListing extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnUpdate;
	private JButton btnBack;
	private JButton btnGetMedicineList;
	private JButton btnDelete;
	private JButton btnExit;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedicineListing frame = new MedicineListing();
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
	public MedicineListing() {
		setTitle("Medicine Listing");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 913, 380);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 26, 879, 218);
		contentPane.add(scrollPane);
		
		DefaultTableModel model =new DefaultTableModel();
		model.setColumnIdentifiers(new String[] {"Barcode","Medicine Name","Manufacturer","Piece","Price","Catogory"});
		
		table = new JTable();
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				medicine md = new medicine();
				
				
				try {
					
					DbConnection db = new DbConnection();
					
					int barcode,row;
					row=table.getSelectedRow();
					barcode=(int)table.getValueAt(row, 0);
					
					
					md.medicine_name=(String)table.getValueAt(row, 1);
					md.manufacturer=(String)table.getValueAt(row, 2);
					md.piece=Integer.parseInt(table.getValueAt(row, 3).toString());
					md.price=Integer.parseInt(table.getValueAt(row, 4).toString());
					md.catogory=(String)table.getValueAt(row, 5);
					
					db.updateMedicine(md, barcode);
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
			}
		});
		btnUpdate.setBounds(10, 264, 131, 21);
		contentPane.add(btnUpdate);
		
		btnBack = new JButton("Back to Menu");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(10, 316, 131, 21);
		contentPane.add(btnBack);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DbConnection db= new DbConnection();
				
				int[] rows = table.getSelectedRows();
				
				for(int i=0 ; i<rows.length; i++) {
					
					try {
						db.deleteMedicine(table.getValueAt(rows[i]-i, 0));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					model.removeRow(rows[i]-i);

				}
			}
			
		});
		btnDelete.setBounds(758, 264, 131, 21);
		contentPane.add(btnDelete);
		
		
		btnGetMedicineList = new JButton("Get Medicine List");
		btnGetMedicineList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				
				DbConnection db =new DbConnection();
				try {
					ResultSet rs = db.getResultMedicine();
					
					int column = rs.getMetaData().getColumnCount();
					while(rs.next()) {
						
						Object[] row = new Object[column];
						for(int i=1; i<= column ;i++) {
							row[i-1]=rs.getObject(i);
						}
						model.addRow(row);
						
					} 
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
			}
		});
		btnGetMedicineList.setBounds(382, 264, 161, 21);
		contentPane.add(btnGetMedicineList);
		

		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(758, 316, 131, 21);
		contentPane.add(btnExit);
	}

}
