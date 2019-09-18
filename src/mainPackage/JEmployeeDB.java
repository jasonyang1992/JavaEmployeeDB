package mainPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class JEmployeeDB implements ActionListener{

// Frame size
	private final int FRAMEX = 640;
	private final int FRAMEY = 480;
// Border create	
	private Border blackline = BorderFactory.createLineBorder(Color.black);	
// Frames	
	private JFrame loginFrame = new JFrame("Employee Database");
	private JFrame menuFrame = new JFrame("Employee Database - Menu");
	private JFrame createFormFrame = new JFrame ("Employee Database - Create");
// GridBagConstraint
	GridBagConstraints GBC = new GridBagConstraints();
// Login Frame variables
	private JTextField loginField = new JTextField(15);
	private JPasswordField passwordField = new JPasswordField(15);
	private JButton loginButton = new JButton("Login");
// Menu Frame Variables
	private JButton createEmployee = new JButton("Create");
	private JButton searchEmployee = new JButton("Search");
	private JButton logOff = new JButton("Log Off");
// Create Frame Variables
	private JLabel fNameLabel = new JLabel("First Name:");
	private JTextField fNameField = new JTextField();
	private JLabel lNameLabel = new JLabel("last Name");
	private JTextField lNameField = new JTextField();
	private JLabel eTitleLabel = new JLabel("Employee Title");
	private JTextField eTitleField = new JTextField();
	private JLabel salaryLabel = new JLabel ("Salary");
	private JTextField salaryField = new JTextField();
	private JLabel dOBLabel = new JLabel ("Date of Birth:");
	private JTextField dOBField = new JTextField();
	private JButton goBack = new JButton("Go Back");
	private JButton create = new JButton("Create Employee");
	
	// initialize the GUI-------------------------------------------------------------------------------------------------------------
		public void startGUI() 
		{
			loginGUI(); // Call the loginGUI
			menuGUI(); // Call the menuGUI
			createFormGUI(); // call createGUI
			searchGUI(); // call display GUI
			editGUI(); // call editGUI
		}
	// Login GUI----------------------------------------------------------------------------------------------------------------------
		private void loginGUI()
		{
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
				loginFrame.setVisible(true);
		}
	// Menu GUI-----------------------------------------------------------------------------------------------------------------------
		private void menuGUI()
		{
		// Background Panel	
				JPanel backgroundPanel = new JPanel(new GridBagLayout());
				backgroundPanel.setBackground(Color.WHITE);
				menuFrame.add(backgroundPanel);
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
			// Title panel Body
				final JLabel TITLELABEL = new JLabel("Welcome: ");
				TITLELABEL.setFont(new Font("times", Font.BOLD, 24));
				titlePanel.add(TITLELABEL);		
		// Option Panel
				JPanel optionPanel = new JPanel(new GridLayout(1, 3));
				GBC.gridx = 0;
				GBC.gridy = 2;
				GBC.ipadx = 255;
				GBC.ipady = 25;
				GBC.insets = new Insets(15, 15, 15, 15);
				optionPanel.setBorder(blackline);
				backgroundPanel.add(optionPanel, GBC);	
			// Option Panel Body
				optionPanel.add(createEmployee);
				createEmployee.addActionListener(this);
				optionPanel.add(searchEmployee);
				searchEmployee.addActionListener(this);
				optionPanel.add(logOff);
				logOff.addActionListener(this);
		// Frame stuff		
				menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				menuFrame.setSize(FRAMEX,FRAMEY);
		}
	// Create GUI---------------------------------------------------------------------------------------------------------------------
		private void createFormGUI()
		{
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
				formPanel.add(fNameLabel);
				formPanel.add(fNameField);
				// Last Name
				formPanel.add(lNameLabel);
				formPanel.add(lNameField);
				// Employee Title
				formPanel.add(eTitleLabel);
				formPanel.add(eTitleField);
				// Salary
				formPanel.add(salaryLabel);
				formPanel.add(salaryField);
				// DOB
				formPanel.add(dOBLabel);
				formPanel.add(dOBField);
				// Buttons return && create
				formPanel.add(goBack);
				goBack.addActionListener(this);
				formPanel.add(create);
				
		// Frame stuff		
				createFormFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				createFormFrame.setSize(FRAMEX,FRAMEY);	
		}
	// Display GUI--------------------------------------------------------------------------------------------------------------------
		private void searchGUI() 
		{
			
		}
		// Edit GUI-------------------------------------------------------------------------------------------------------------------
		private void editGUI()
		{
			
		}
	// Action Events	
		public void actionPerformed(ActionEvent event){
		// login GUI actions	
			if (event.getSource() == loginButton || event.getSource() == passwordField) {
				loginFrame.setVisible(false);
				menuFrame.setVisible(true);
			}
		// Menu GUI actions
			if (event.getSource() == createEmployee) {
				createFormFrame.setVisible(true);
				menuFrame.setVisible(false);
			}
			else if (event.getSource() == logOff) {
				loginFrame.setVisible(true);
				menuFrame.setVisible(false);
			}
		//	Create GUI Action
			if (event.getSource() == goBack) {
				createFormFrame.setVisible(false);
				menuFrame.setVisible(true);
			}
		}
}
