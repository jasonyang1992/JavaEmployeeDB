package mainPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JEmployeeDB implements ActionListener {

// Frame size
	private final int FRAMEX = 640;
	private final int FRAMEY = 480;
// Border create	
	private Border blackline = BorderFactory.createLineBorder(Color.black);
// Frames	
	private JFrame loginFrame = new JFrame("Employee Database");
	private JFrame createFormFrame = new JFrame("Employee Database - Create");
	private JFrame searchFrame = new JFrame("Employee Database - Search");
	private JFrame editFrame = new JFrame("Employee Database - Edit");
// GridBagConstraint
	GridBagConstraints GBC = new GridBagConstraints();
// Login Frame variables
	private JTextField loginField = new JTextField(15);
	private JPasswordField passwordField = new JPasswordField(15);
	private JButton loginButton = new JButton("Login");
// Search Frame Variables
	private JButton createEmployee = new JButton("Create");
	private JButton deleteEmployee = new JButton("Delete");
	private JButton logOff = new JButton("Log Off");
	private JButton editEmployee = new JButton("Edit");
	private final String searchString[] = { "Employee ID", "First Name", "Last Name"};
	
    private DefaultTableModel jTableModel = new DefaultTableModel(new String[]{"Employee ID", "First Name", "last Name", "Job TItle", "Salary", "DoB"}, 0);
	private JTable eTable = new JTable(jTableModel);

	private JComboBox searchbox = new JComboBox(searchString);
	private JTextField searchField = new JTextField(15);
	private JButton search = new JButton("search");
// Create Frame Variables
	private JLabel fNameLabelC = new JLabel("First Name:");
	private JTextField fNameFieldC = new JTextField();
	private JLabel lNameLabelC = new JLabel("last Name");
	private JTextField lNameFieldC = new JTextField();
	private JLabel eTitleLabelC = new JLabel("Employee Title");
	private JTextField eTitleFieldC = new JTextField();
	private JLabel salaryLabelC = new JLabel("Salary");
	private JTextField salaryFieldC = new JTextField();
	private JLabel dOBLabelC = new JLabel("Date of Birth:");
	private JTextField dOBFieldC = new JTextField();
	private JButton goBackC = new JButton("Go Back");
	private JButton createC = new JButton("Create Employee");
// Edit Frame Variables
	private JLabel fNameLabelE = new JLabel("First Name:");
	private JTextField fNameFieldE = new JTextField();
	private JLabel lNameLabelE = new JLabel("last Name");
	private JTextField lNameFieldE = new JTextField();
	private JLabel eTitleLabelE = new JLabel("Employee Title");
	private JTextField eTitleFieldE = new JTextField();
	private JLabel salaryLabelE = new JLabel("Salary");
	private JTextField salaryFieldE = new JTextField();
	private JLabel dOBLabelE = new JLabel("Date of Birth:");
	private JTextField dOBFieldE = new JTextField();
	private JButton goBackE = new JButton("Go Back");
	private JButton editE = new JButton("Edit Employee");
// Database Variables
	// Login Variables
	private String grabUserNameDB;
	private String grabPasswordDB;
	private String grabUsernameGUI;
	private String grabPasswordGUI;

	// initialize the
	// GUI-------------------------------------------------------------------------------------------------------------
	public void startGUI() {
		loginGUI(); // Call the loginGUI
		createFormGUI(); // call createGUI
		searchGUI(); // call search GUI
		editGUI(); // call editGUI
	}

	// Login
	// GUI----------------------------------------------------------------------------------------------------------------------
	private void loginGUI() {
		JPanel backgroundPanel = new JPanel(new GridBagLayout());
		backgroundPanel.setBackground(Color.WHITE);
		loginFrame.add(backgroundPanel);
		// Title Panel
		JPanel titlePanel = new JPanel();
		GBC.gridx = 0;
		GBC.gridy = 0;
		GBC.gridheight = 1;
		GBC.gridwidth = 1;
		GBC.insets = new Insets(15, 15, 15, 15);
		titlePanel.setBackground(Color.GRAY);
		titlePanel.setBorder(blackline);
		backgroundPanel.add(titlePanel, GBC);
		// Title Label
		final JLabel TITLELABEL = new JLabel("Employee Database");
		TITLELABEL.setFont(new Font("times", Font.BOLD, 24));
		titlePanel.add(TITLELABEL);
		// Login Panel
		JPanel loginPanel = new JPanel(new GridLayout(3, 2));
		GBC.gridx = 0;
		GBC.gridy = 2;
		GBC.insets = new Insets(15, 15, 15, 15);
		loginPanel.setBorder(blackline);
		backgroundPanel.add(loginPanel, GBC);
		// Login Body
		JLabel loginLabel = new JLabel("Login:");
		loginPanel.add(loginLabel);

		loginPanel.add(loginField);

		JLabel passowrdLabel = new JLabel("Password:");
		loginPanel.add(passowrdLabel);

		passwordField.addActionListener(this);
		loginPanel.add(passwordField);

		JLabel blank = new JLabel();
		loginPanel.add(blank);

		loginPanel.add(loginButton);
		loginButton.addActionListener(this);
		// frame stuff
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setSize(FRAMEX, FRAMEY);
		loginFrame.setResizable(false);
		loginFrame.setVisible(true);
	}

	// Create
	// GUI---------------------------------------------------------------------------------------------------------------------
	private void createFormGUI() {
		// Background Panel
		JPanel backgroundPanel = new JPanel(new GridBagLayout());
		backgroundPanel.setBackground(Color.WHITE);
		createFormFrame.add(backgroundPanel);
		// Title Panel
		JPanel titlePanel = new JPanel();
		GBC.gridx = 0;
		GBC.gridy = 0;
		GBC.gridheight = 1;
		GBC.gridwidth = 1;
		GBC.ipadx = 0;
		GBC.ipady = 0;
		GBC.insets = new Insets(15, 15, 15, 15);
		titlePanel.setBackground(Color.GRAY);
		titlePanel.setBorder(blackline);
		backgroundPanel.add(titlePanel, GBC);
		// Title Label
		final JLabel TITLELABEL = new JLabel("Create Employee");
		TITLELABEL.setFont(new Font("times", Font.BOLD, 24));
		titlePanel.add(TITLELABEL);
		// Option Panel
		JPanel formPanel = new JPanel(new GridLayout(6, 2));
		GBC.gridx = 0;
		GBC.gridy = 1;
		GBC.ipadx = 50;
		GBC.ipady = 25;
		GBC.insets = new Insets(15, 15, 15, 15);
		formPanel.setBorder(blackline);
		backgroundPanel.add(formPanel, GBC);
		// Option Panel Body
		// First Name
		formPanel.add(fNameLabelC);
		formPanel.add(fNameFieldC);
		// Last Name
		formPanel.add(lNameLabelC);
		formPanel.add(lNameFieldC);
		// Employee Title
		formPanel.add(eTitleLabelC);
		formPanel.add(eTitleFieldC);
		// Salary
		formPanel.add(salaryLabelC);
		formPanel.add(salaryFieldC);
		// DOB
		formPanel.add(dOBLabelC);
		formPanel.add(dOBFieldC);
		// Buttons return && create
		formPanel.add(goBackC);
		goBackC.addActionListener(this);
		formPanel.add(createC);
		createC.addActionListener(this);

		// Frame stuff
		createFormFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createFormFrame.setSize(FRAMEX, FRAMEY);
		createFormFrame.setResizable(false);
	}

	// search
	// GUI--------------------------------------------------------------------------------------------------------------------
	private void searchGUI() {
		// Background Panel
		JPanel backgroundPanel = new JPanel(new GridBagLayout());
		backgroundPanel.setBackground(Color.WHITE);
		searchFrame.add(backgroundPanel);
		// Title Panel
		JPanel titlePanel = new JPanel(new GridLayout(1, 4));
		GBC.gridx = 0;
		GBC.gridy = 0;
		GBC.gridheight = 1;
		GBC.gridwidth = 1;
		GBC.ipadx = 0;
		GBC.ipady = 0;
		GBC.insets = new Insets(15, 15, 15, 15);
		titlePanel.setBackground(Color.GRAY);
		titlePanel.setBorder(blackline);
		backgroundPanel.add(titlePanel, GBC);
		// Title Label
		final JLabel TITLELABEL = new JLabel("Search Employee");
		TITLELABEL.setFont(new Font("times", Font.BOLD, 24));
		titlePanel.add(TITLELABEL);
		// Button Panel
		JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
		GBC.gridx = 0;
		GBC.gridy = 1;
		GBC.ipadx = 150;
		GBC.ipady = 15;
		backgroundPanel.add(buttonPanel, GBC);
		// Button

		buttonPanel.add(createEmployee);
		createEmployee.addActionListener(this);
		buttonPanel.add(editEmployee);
		editEmployee.addActionListener(this);
		buttonPanel.add(deleteEmployee);
		deleteEmployee.addActionListener(this);
		buttonPanel.add(logOff);
		logOff.addActionListener(this);
		// Java Table Panel
		JPanel tablePanel = new JPanel();
		GBC.gridx = 0;
		GBC.gridy = 2;
		GBC.ipadx = 0;
		GBC.ipady = 0;
		// jt.setPreferredScrollableViewportSize(jt.getPreferredSize());
		JScrollPane scroll = new JScrollPane(eTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(500, 120));
		tablePanel.add(scroll);
		backgroundPanel.add(tablePanel, GBC);
		// Search Panel
		JPanel searchPanel = new JPanel();
		GBC.gridx = 0;
		GBC.gridy = 4;
		searchPanel.setBackground(Color.WHITE);
		backgroundPanel.add(searchPanel, GBC);
		// Search Label
		JLabel searchLabel = new JLabel("Search By: ");
		searchPanel.add(searchLabel);
		searchPanel.add(searchbox);
		searchPanel.add(searchField);
		searchPanel.add(search);
		search.addActionListener(this);
		// Frame stuff
		searchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		searchFrame.setSize(FRAMEX, FRAMEY);
		searchFrame.setResizable(false);
	}

	// Edit
	// GUI-------------------------------------------------------------------------------------------------------------------
	private void editGUI() {
		// Background Panel
		JPanel backgroundPanel = new JPanel(new GridBagLayout());
		backgroundPanel.setBackground(Color.WHITE);
		editFrame.add(backgroundPanel);
		// Title Panel
		JPanel titlePanel = new JPanel(new GridLayout(1, 4));
		GBC.gridx = 0;
		GBC.gridy = 0;
		GBC.gridheight = 1;
		GBC.gridwidth = 1;
		GBC.ipadx = 0;
		GBC.ipady = 0;
		GBC.insets = new Insets(15, 15, 15, 15);
		titlePanel.setBackground(Color.GRAY);
		titlePanel.setBorder(blackline);
		backgroundPanel.add(titlePanel, GBC);
		// Title Label
		final JLabel TITLELABEL = new JLabel("Edit Employee");
		TITLELABEL.setFont(new Font("times", Font.BOLD, 24));
		titlePanel.add(TITLELABEL);

		JPanel formPanel = new JPanel(new GridLayout(6, 2));
		GBC.gridx = 0;
		GBC.gridy = 1;
		GBC.ipadx = 50;
		GBC.ipady = 25;
		GBC.insets = new Insets(15, 15, 15, 15);
		formPanel.setBorder(blackline);
		backgroundPanel.add(formPanel, GBC);
		// Option Panel Body
		// First Name
		formPanel.add(fNameLabelE);
		formPanel.add(fNameFieldE);
		// Last Name
		formPanel.add(lNameLabelE);
		formPanel.add(lNameFieldE);
		// Employee Title
		formPanel.add(eTitleLabelE);
		formPanel.add(eTitleFieldE);
		// Salary
		formPanel.add(salaryLabelE);
		formPanel.add(salaryFieldE);
		// DOB
		formPanel.add(dOBLabelE);
		formPanel.add(dOBFieldE);
		// Buttons return && create
		formPanel.add(goBackE);
		goBackE.addActionListener(this);
		formPanel.add(editE);
		// Frame stuff
		editFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editFrame.setSize(FRAMEX, FRAMEY);
		editFrame.setResizable(false);
	}

	// Action Events
	public void actionPerformed(ActionEvent event) {
		// login GUI actions
		if (event.getSource() == loginButton || event.getSource() == passwordField) {
			Connection conn = null;
			Statement state = null;
			ResultSet rs = null;

			// Register Oracle JDBC driver class
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

				rs = state.executeQuery("SELECT * FROM UserName");
			// Loop to determine valid username and password
				try {
					do {
						rs.next();
						grabUserNameDB = rs.getString(2);
						grabPasswordDB = rs.getString(3);
						grabPasswordGUI = String.valueOf(passwordField.getPassword());
						grabUsernameGUI = loginField.getText();
					} while (!grabUsernameGUI.equals(grabUserNameDB) && !grabPasswordGUI.equals(grabPasswordDB));
				} catch (Exception e) {
					
				}

				if (grabUsernameGUI.equals(grabUserNameDB) && grabPasswordGUI.equals(grabPasswordDB)) {
					JOptionPane.showMessageDialog(null, "Successfully login!");
					grabPasswordDB = null; // wipe password variable 
					passwordField.setText(null); // reset the password field to null
					loginFrame.setVisible(false);
					searchFrame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Failed to login, invalid username or password!", "Error Message",JOptionPane.ERROR_MESSAGE);
					grabPasswordDB = null;
					passwordField.setText(null);
				}

			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			} finally {
				// Step 3: Closing database connection
				try {
					if (null != conn) {
						// cleanup resources, once after processing
						rs.close();
						state.close();

						// and then finally close connection
						conn.close();
					}
				} catch (SQLException sqlex) {
					sqlex.printStackTrace();
				}
		
			}
		}
		// search GUI actions
		if (event.getSource() == createEmployee) {
			createFormFrame.setVisible(true);
			searchFrame.setVisible(false);
		} else if (event.getSource() == editEmployee) {
			editFrame.setVisible(true);
			searchFrame.setVisible(false);
		} else if (event.getSource() == search) {
			Connection conn = null;
			Statement state = null;
			ResultSet rs = null;
			int tableRowCounter = 0;
			// Register Oracle JDBC driver class
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
			// SQL Command
				rs = state.executeQuery("SELECT * FROM EmployeeTable");			
			// Determine how many rows are there in the Table	
				tableRowCounter = eTable.getRowCount();
			// Check if there is previous data in the row and delete them
				for(int i = 0; i<tableRowCounter; i++) {
					jTableModel.removeRow(0);
				}
			// Loads data from MS access database	
				while(rs.next()) {
					String grabDBData [] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
					jTableModel.addRow(grabDBData);
				}
				
				
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
			jTableModel.fireTableDataChanged();
		} else if (event.getSource() == logOff) {
			loginFrame.setVisible(true);
			searchFrame.setVisible(false);
			JOptionPane.showMessageDialog(null, "You have logged off sucessfully!");
		}
		// Create GUI Action
		if (event.getSource() == createC) {
			Connection conn = null;
			String insertQuery;
			// Register Oracle JDBC driver class
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

				insertQuery = "INSERT INTO EmployeeTable ([EmployeeID], [FName], [LName], [JobTitle], [Salary], [DoB]) VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement st = conn.prepareStatement (insertQuery);
				st.setString(1, null);
				st.setString(2, fNameFieldC.getText());
				st.setString(3, lNameFieldC.getText());
				st.setString(4, eTitleFieldC.getText());
				st.setString(5, salaryFieldC.getText());
				st.setString(6, dOBFieldC.getText());
				
					st.executeUpdate();

					JOptionPane.showMessageDialog(null, "Data has been enter!");
					
					fNameFieldC.setText(null);
					lNameFieldC.setText(null);
					eTitleFieldC.setText(null);
					salaryFieldC.setText(null);
					dOBFieldC.setText(null);

			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			} finally {
			/*	// Step 3: Closing database connection
				try {
					if (null != conn) {
						// cleanup resources, once after processing
						rs.close();
						state.close();

						// and then finally close connection
						conn.close();
					}
				} catch (SQLException sqlex) {
					sqlex.printStackTrace();
				}*/
			}
			
		}
		else if (event.getSource() == goBackC) {
			createFormFrame.setVisible(false);
			searchFrame.setVisible(true);
		}
		// Edit GUi Action
		if (event.getSource() == goBackE) {
			editFrame.setVisible(false);
			searchFrame.setVisible(true);
		}
		// Search GUI Actions
		if (event.getSource() == search) {
			
		}
	}
}
