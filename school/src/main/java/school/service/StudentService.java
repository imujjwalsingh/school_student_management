package school.service;

// Service class

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import school.entity.Student;

public class StudentService {
		 
//	static String url = "jdbc:postgresql://localhost:5432/school";
//	static String user = "postgres";
//	static String pswd = "123";

	static Connection con;
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
			
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/school?user=postgres&password=123");
			
//			System.out.println("Driver class loaded & connection established");
			
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// Method to perform INSERT operation on database
	
	public void create(Student student) {
		
		String sql = "insert into student values(?, ?, ?)";
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			
			pstm.setInt(1, student.getId());
			pstm.setString(2, student.getName());
			pstm.setInt(3, student.getAge());
			
			int res = pstm.executeUpdate();
	
			if(res != 0) System.out.println("Data inserted into database!");
			else System.out.println("Data can't be inserted!");
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}
	
	// Method to perform SELECT operation on database
	
	public void fetch(int id) {
		
		String sql = "select * from student where id = ?";
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			rs.next();
			System.out.println("Student name : " + rs.getString(2) + "\nStudent age : " + rs.getInt(3));
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	// Method to perform UPDATE operation on database
	
	public void update(Student student) {
		
		String sql = "update student set name = ?, age = ? where id = ?";
		
		System.out.println("Old record :");
		fetch(student.getId());
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, student.getName());
			pstm.setInt(2, student.getAge());
			pstm.setInt(3, student.getId());
			pstm.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println("New Record : ");
		fetch(student.getId());
		
	}
	
	// Method to perform DELETE operation on database
	
	public void delete(int id) {
		
		String sql = "delete from student where id = ?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			
			int res = pstm.executeUpdate();
			
			if(res == 1) System.out.println("Record deleted succesfully!");
			else System.out.println("issue while deleting the record!");
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
