package jtglover_CSCI201_Lab9;

import java.sql.*;
import java.util.*;

public class Lab9 {
	public static void main(String[] args) {
		tableOne();
		tableTwo();
	}
	
	public static void tableOne() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Lab9?user=root&password=Julian@123");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT g.ClassName from Grades g");
			Map<String, Integer> numClasses = new HashMap();
			ArrayList<String> classNames = new ArrayList<String>();
			
			numClasses.put("ART123", 0);
			numClasses.put("BUS456", 0);
			numClasses.put("REL100", 0);
			numClasses.put("ECO966", 0);
			
			while(rs.next()) {
				String className = rs.getString("ClassName");
				numClasses.put(className, numClasses.get(className) + 1);
			}
			
			System.out.println("Class Name    Number of Students");
			rs = st.executeQuery("SELECT g.ClassName from Grades g");
			
			while(rs.next()) {
				String className = rs.getString("ClassName");
				if(classNames.indexOf(className) == -1) {
					System.out.println(className + "        " + numClasses.get(className));
					classNames.add(className);
				}
			}
			
		} catch (SQLException sqle) {
			System.out.println (sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println(sqle.getMessage());
			}
		}
		System.out.println();
	}
	
	public static void tableTwo() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Lab9?user=root&password=Julian@123");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT g.ClassName, g.Grade, s.Name FROM Grades g, StudentInfo s WHERE g.SID = s.SID");
			
			System.out.println("Class Name    Name    		Grade");
			
			while(rs.next()) {
				String className = rs.getString("ClassName");
				String name = rs.getString("Name");
				String grade = rs.getString("Grade");
				
				System.out.println(className + "	      " + name + "		" + grade);
			}
			
			
		} catch (SQLException sqle) {
			System.out.println (sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println(sqle.getMessage());
			}
		}
	}
}
