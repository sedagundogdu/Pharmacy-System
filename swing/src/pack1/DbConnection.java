package pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DbConnection {
	
    public static Connection getConnection() throws SQLException {
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/homework","root","Ssdag851");
		return con;
		
	}
	
	public boolean checkLogin(String username, String password) throws SQLException {
		
		Statement st=getConnection().createStatement();
		ResultSet rs=st.executeQuery("select *from login where username ='"+username+"'and password='"+password+"'");
		if(rs.next()) {
			return true;
		}
		else {
			return false;
		}
	}

	
	public static ResultSet getResult(String username,String password) throws SQLException {
		
		Statement st = getConnection().createStatement();
		ResultSet rs=st.executeQuery("select *from login where username ='"+username+"'and password='"+password+"'");		
		return rs;
		
	}
	
	public static ResultSet getResultPatient() throws SQLException {
		
		Statement st = getConnection().createStatement();
		ResultSet rs=st.executeQuery("select *from newpatient");		
		return rs;
		
	}
	
	public void savePatient(Patient pt) throws SQLException {
		
		String query= "insert into newpatient(TC,name,surname,birthYear,gender,social,phone,given) values(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = getConnection().prepareStatement(query);
		ps.setInt   (1, pt.tc);
		ps.setString(2, pt.name);
		ps.setString(3, pt.surname);
		ps.setInt   (4, pt.birth_year);
		ps.setString(5, pt.gender);
		ps.setString(6, pt.social);
		ps.setInt   (7, pt.phone_num);
		ps.setString(8,pt.given_medicine);
		ps.executeUpdate();

	}
	
	public void saveMedicine(medicine pt) throws SQLException {
		
		String query= "insert into medicine(barcode,medicine,manufacturer,piece,price,catogory) values(?,?,?,?,?,?)";
		PreparedStatement ps = getConnection().prepareStatement(query);
		ps.setInt   (1, pt.barcode);
		ps.setString(2, pt.medicine_name);
		ps.setString(3, pt.manufacturer);
		ps.setInt   (4, pt.piece);
		ps.setInt   (5, pt.price);
		ps.setString(6, pt.catogory);
		ps.executeUpdate();

	}

	public static ResultSet getCatogory() throws SQLException {
		
		Statement st = getConnection().createStatement();
		ResultSet rs=st.executeQuery("select catogory from catogory");		
		return rs;
		
	}
	
	public static ResultSet getCatogorytoPatient() throws SQLException {
		
		Statement st = getConnection().createStatement();
		ResultSet rs=st.executeQuery("select medicine from medicine");		
		return rs;
		
	}
	
	public void updatePatient(Patient pt,int tc) throws SQLException {
		
		String query = "update newpatient set name=?, surname=?, birthYear=?, gender=?, social=?, phone=?,given=? where TC=? ";
		PreparedStatement ps = getConnection().prepareStatement(query);
		ps.setString(1, pt.name);
		ps.setString(2, pt.surname);
		ps.setInt   (3, pt.birth_year);
		ps.setString(4, pt.gender);
		ps.setString(5, pt.social);
		ps.setInt   (6, pt.phone_num);
		ps.setString(7, pt.given_medicine);
		ps.setInt   (8, tc);
		
		ps.executeUpdate();
		
	}
	
	public void updateMedicine(medicine md,int barcode) throws SQLException {
		
		String query = "update medicine set medicine=?, manufacturer=?, piece=?, price=?, catogory=? where barcode=? ";
		PreparedStatement ps = getConnection().prepareStatement(query);
		ps.setString(1, md.medicine_name);
		ps.setString(2, md.manufacturer );
		ps.setInt   (3, md.piece );
		ps.setInt   (4, md.price );
		ps.setString(5, md.catogory);
		ps.setInt   (6, md.barcode );
		
		ps.executeUpdate();
		
	}
	
	public void deletePatient(Object tcpatient ) throws SQLException {
				
		int tc = Integer.parseInt( tcpatient.toString() );
		Statement st =getConnection().createStatement();
		st.executeUpdate("delete from newpatient where TC='"+tc+"'");

	}
	
	public void deleteMedicine(Object barcode_medicine ) throws SQLException {
		
		int barcode = Integer.parseInt( barcode_medicine.toString() );
		Statement st =getConnection().createStatement();
		st.executeUpdate("delete from medicine where barcode='"+barcode+"'");

	}
	
	public static ResultSet getResultMedicine() throws SQLException {
		
		Statement st = getConnection().createStatement();
		ResultSet rs=st.executeQuery("select *from medicine");		
		return rs;
		
	}
	

}
