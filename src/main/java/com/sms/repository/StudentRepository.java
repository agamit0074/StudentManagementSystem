package com.sms.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

import com.sms.entity.StudentEntity;

//We Create or Application in layer
//Presentation->controller->service->repository
//Database connectivity -> Repository Layer
//Repository Layer is responsible to communicate whith
//the DB

public class StudentRepository {
	//This class is responsible for Communicating with Student
	//table
	//Reason to create this class -> in future any error or 
	//updation i have to do in student table or in 
	//communication
	
	//->add() | update() | delete() | find()
	//for each and every thing this call should contain 
	//a method
	
	//connection object
	private String url = "jdbc:postgresql://localhost:5433/student_management";
	private String user = "postgres";
	private String password = "root";
	private Connection connection ;
	
	
	{
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//You want to add student details 
	public int saveStudent(StudentEntity s) throws SQLException{
		// I want to store this s object date into the 
		//db table
		String sql = "insert into student values(nextval('stu_id'),?,?,?,?,?,?)";
		PreparedStatement ps  = connection.prepareStatement(sql);
		ps.setString(1,s.getName());
		ps.setLong(2,s.getMobile());
		ps.setDate(3,s.getDob());
		ps.setDouble(4,s.getPer10());
		Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
		ps.setTimestamp(5,ts);
		ps.setTimestamp(6, ts);
		ps.execute();
		return 1;
	}
	
	public StudentEntity readById(int id) throws SQLException {
		String sql = "select * from student where id=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs =  ps.getResultSet();
		if(rs.next()) {
			StudentEntity s = new StudentEntity(rs.getInt(1),
					rs.getString(2),
					rs.getLong(3),
					rs.getDate(4),
					rs.getDouble(5),
					rs.getTimestamp(6),
					rs.getTimestamp(7));
			return s;
		}
		return null;
	}
	
	//delete by Id
	public boolean deleteById(int id) throws SQLException {
		String sql = "delete from student where id=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		int i = ps.executeUpdate(); //int -> no of rows affected
		if(i==1) {
			return true;
		}
		return false;
	}
	
	//read All Data
	public void readAll() {
		String sql = "select * from student";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			System.out.println("ID\tNAME\tMOBILE\tDOB\tPER10\tCREATED_AT\tUPDATED_AT");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t"
						+rs.getString(2)+"\t"
						+rs.getLong(3)+"\t"
						+rs.getDate(4)+"\t"
						+rs.getDouble(5)+"\t"
						+rs.getTimestamp(6)+"\t"
						+rs.getTimestamp(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void main(String[] args) throws SQLException {
		StudentRepository repository = new StudentRepository();
		Scanner sc  = new Scanner(System.in);
		while(true) {
			System.out.println("Press 1 : For Insert Data");
			System.out.println("Press 2 : For Delete Student By ID");
			System.out.println("Press 3 : For Update Student By ID");
			System.out.println("Press 4 : For Read Student By ID");
			System.out.println("Press 5 : For Read All Student Data");
			System.out.println("Press 7 : For Exit");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:{
				System.out.println("Enter Your Name :");
				String name = sc.next();
				System.out.println("Enter Your Mobile No:");
				long mobile = sc.nextLong();
				System.out.println("Enter Your DOB");
				String dob = sc.next();
				System.out.println("Enter Your 10th Precentage");
				double per =  sc.nextDouble();
				StudentEntity s = new StudentEntity(name, mobile,Date.valueOf(dob), per);
				int i = repository.saveStudent(s);
				if(i==1) {
					System.out.println("SDtudent Data Sucess Fully Stored");
				}
			    }
				break;
			case 2 : {
				System.out.println("Input Id");
				int id = sc.nextInt();
				Boolean res = repository.deleteById(id);
				if(res==true) {
					System.out.println("Student data Sucessfully Deleted");
				}else {
					System.out.println("Failed to Delete Student Data");
				}
			}
			case 5 : {
				repository.readAll();
			}
			break;
			default:
				break;
			}
		}
	}
	
	
	
	
	
	
	
	
}
