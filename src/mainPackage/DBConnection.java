package mainPackage;

import java.io.File;

import java.sql.*;

import javax.swing.JOptionPane;

public class DBConnection {

	private Connection conn = null;
	private Statement state = null;
	private ResultSet rs = null;
	
	DBConnection(){
		try {

			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (ClassNotFoundException cnfex) {

			System.out.println("Problem in loading or registering MS Access JDBC driver");
			cnfex.printStackTrace();
		}
		
		try {
			File dbFile = new File("EmployeeDB.accdb");
			String msAccDB = dbFile.getAbsolutePath();
			String dbURL = "jdbc:ucanaccess://" + msAccDB;
	
			conn = DriverManager.getConnection(dbURL);
	
			state = conn.createStatement();
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if (conn != null) {
				rs.close();
				state.close();
				conn.close();
			}
		} catch (SQLException sqlex) {
			System.out.println("There was an error in closing the database!");
		}
	}
	
// Checks user name and password against the password	
	public boolean readUP(String userName, String password) {
		boolean access = false;
		try {
			rs = state.executeQuery("SELECT * FROM UserName");
			do {
				rs.next();
				if(rs.getString(2).equals(userName) && rs.getString(3).equals(password)) {
					access = true;
				}
				else{
					access = false;
				}
			} while (!rs.getString(2).equals(userName) && !rs.getString(3).equals(password));
			
		} catch (Exception e) {};
		
		return access;
	}
	
	public void readData() {
		
	}
	
	public void insertData(String fName, String lName, String eTitle, String salary, String dob) {
		// SQL command
			String insertQuery = "INSERT INTO EmployeeTable ([EmployeeID], [FName], [LName], [JobTitle], [Salary], [DoB]) VALUES (?, ?, ?, ?, ?, ?)";		
		try {
			PreparedStatement st = conn.prepareStatement (insertQuery);
		// Grab the data from the text field	
			st.setString(1, null);
			st.setString(2, fName);
			st.setString(3, lName);
			st.setString(4, eTitle);
			st.setString(5, salary);
			st.setString(6, dob);
		// Run command
			st.executeUpdate();
		// Pop up confirmation
			JOptionPane.showMessageDialog(null, "Data has been enter!");
		} catch (SQLException sqlex) {
			
		}
	}
	
	public void deleteData(String tableRow) {
		String updateQuery = "DELETE FROM EmployeeTable WHERE EmployeeID = " + tableRow ;
		try {
			rs = state.executeQuery("SELECT * FROM EmployeeTable");	
			PreparedStatement st = conn.prepareStatement (updateQuery);
	
		// Loads data from MS access database	
			while(rs.next()) {
				if (rs.getString(1).equals(tableRow)) {
					st.executeUpdate();
				}
			}
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, "There was an error in deleting!");
		}
	}
	
	public void editData(String empID, String fName, String lName, String eTitle, String salary, String dob) {
		try {
	// SQL Command
			String updateQuery = "UPDATE EmployeeTable SET FName=?, LName=?, JobTitle=?, Salary=?, DoB=? WHERE EmployeeID=" + empID;
			PreparedStatement st = conn.prepareStatement (updateQuery);
		
	// Set data into Database
			st.setString(1, fName);
			st.setString(2, lName);
			st.setString(3, eTitle);
			st.setString(4, salary);
			st.setString(5, dob);
	// Execute the update
			st.executeUpdate();
	// Pop up confirmation
		JOptionPane.showMessageDialog(null, "Data has been updated!");	
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, "There was an error in updating!");
		}
	}
}
