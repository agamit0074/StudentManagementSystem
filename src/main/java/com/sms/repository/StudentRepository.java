package com.sms.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
	
	
	
	public static void main(String[] args) {
		StudentEntity stu = new StudentEntity("Rahul",7894561230l
				,Date.valueOf(LocalDate.of(2001,5,12)),78.5);
		StudentRepository repository = new StudentRepository();
		try {
			repository.saveStudent(stu);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
}
