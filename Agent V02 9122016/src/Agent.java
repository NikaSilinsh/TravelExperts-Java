import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import javax.swing.JScrollPane;

public class Agent extends JFrame {

	private JPanel contentPane;
	private JButton btnSave;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnAgtStatusInactive;
	private JTextField txtfFName;
	private JTextField txtfMInitial;
	private JTextField txtfLName;
	private JTextField txtfEmail;
	private JTextField txtfPosition;
	private JFormattedTextField textFieldPhone;
	private JComboBox<String> cmbAgencyID;
	
	public static Agent frame;
	
	private JTable tableListAgents;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					frame = new Agent();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Agent() {
		setTitle("Agent Maintenance");
		
		initComponents();
		createEvents();
		
	}
	
	/************************************************
	 *** This method contains all of the code for ***
	 *** creating and initializing components.    ***
	 ************************************************/
	private void initComponents() {
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1219, 602);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);	
		
		btnSave = new JButton("Save");		
		btnSave.setBounds(67, 388, 103, 33);
		Image img = new ImageIcon(this.getClass().getResource("/Ok.png")).getImage();
		btnSave.setIcon(new ImageIcon(img));
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(293, 388, 103, 33);
		Image img1 = new ImageIcon(this.getClass().getResource("/end-icon.png")).getImage();
		btnClose.setIcon(new ImageIcon(img1));
		
		JPanel panel = new JPanel();
		panel.setBounds(67, 40, 342, 283);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "New Agent", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setToolTipText("");
		panel.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstName.setBounds(25, 39, 69, 14);
		panel.add(lblFirstName);
		
		JLabel lblInitialName = new JLabel("Middle Initial:");
		lblInitialName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInitialName.setBounds(10, 64, 84, 14);
		panel.add(lblInitialName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastName.setBounds(25, 89, 69, 14);
		panel.add(lblLastName);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhone.setBounds(39, 114, 55, 14);
		panel.add(lblPhone);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(39, 139, 55, 14);
		panel.add(lblEmail);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setToolTipText("");
		lblPosition.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPosition.setBounds(39, 164, 55, 14);
		panel.add(lblPosition);
		
		JLabel lblAgencyId = new JLabel("Agency ID:");
		lblAgencyId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAgencyId.setBounds(25, 189, 69, 14);
		panel.add(lblAgencyId);
		
		txtfFName = new JTextField();
		txtfFName.setBounds(98, 36, 194, 20);
		panel.add(txtfFName);
		txtfFName.setColumns(10);
		
		txtfMInitial = new JTextField();
		txtfMInitial.setColumns(10);
		txtfMInitial.setBounds(98, 61, 194, 20);
		panel.add(txtfMInitial);
		
		txtfLName = new JTextField();
		txtfLName.setColumns(10);
		txtfLName.setBounds(98, 86, 194, 20);
		panel.add(txtfLName);
		
		
		try 
		{
			   // create the formatter 
			MaskFormatter phoneNumberMaskFormatter = new MaskFormatter("(###) ###-####");
		      //  set place hold character to _
		    phoneNumberMaskFormatter.setPlaceholderCharacter('_');
		      
		    textFieldPhone = new JFormattedTextField(phoneNumberMaskFormatter);
		    panel.add(textFieldPhone);
			textFieldPhone.setBounds(98, 111, 104, 20);		   
		} 
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		   
		contentPane.setLayout(null);
	
	      
	      
		
		txtfEmail = new JTextField();
		txtfEmail.setColumns(10);
		txtfEmail.setBounds(98, 136, 194, 20);
		panel.add(txtfEmail);
		
		txtfPosition = new JTextField();
		txtfPosition.setColumns(10);
		txtfPosition.setBounds(98, 161, 194, 20);
		panel.add(txtfPosition);
		
		cmbAgencyID = new JComboBox<String>();
		cmbAgencyID.setModel(new DefaultComboBoxModel<String>(new String[] {"Please select a code agency."}));
		cmbAgencyID.setBounds(98, 189, 194, 20);
		panel.add(cmbAgencyID);
		
		JRadioButton rdbtnAgtStatusActive = new JRadioButton("Active");
		rdbtnAgtStatusActive.setBounds(108, 227, 77, 23);
		panel.add(rdbtnAgtStatusActive);
		buttonGroup.add(rdbtnAgtStatusActive);
		
		rdbtnAgtStatusInactive = new JRadioButton("Inactive");
		rdbtnAgtStatusInactive.setBounds(187, 227, 78, 23);
		panel.add(rdbtnAgtStatusInactive);
		buttonGroup.add(rdbtnAgtStatusInactive);
		
		JLabel lblAgentStatus = new JLabel("Agent Status:");
		lblAgentStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAgentStatus.setBounds(22, 231, 84, 14);
		panel.add(lblAgentStatus);
		contentPane.add(panel);
		contentPane.add(btnSave);
		contentPane.add(btnClose);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(419, 48, 758, 408);
		contentPane.add(scrollPane);
		
		tableListAgents = new JTable();
		scrollPane.setViewportView(tableListAgents);
		tableListAgents.setEnabled(false);
		
		JButton btnModify = new JButton("Modify...");
		
		btnModify.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				 frame.dispose();
				 ModifyAgent modifyAgent = new ModifyAgent();
				  modifyAgent.setVisible(true);
				 
			}
		});
		
		btnModify.setBounds(180, 388, 103, 33);
		contentPane.add(btnModify);
		
		
		Image img2 = new ImageIcon(this.getClass().getResource("/app-form-edit.png")).getImage();
		btnModify.setIcon(new ImageIcon(img2));
		
	}

	/************************************************
	 *** This method contains all of the code for ***
	 *** creating events.                         ***
	 ************************************************/
	private void createEvents() {
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
								// start
				if (checkEmptyTextField())  // Validation empty text fields
				{		
					addAgent();
					setEmptyTexfield();
				}									
			}
		});
		getAllAgenciesIDs();		
		// Display list agents
		getListAgent();
	}
		
				// Method reinitialize text field
				public void setEmptyTexfield()
				{
					txtfFName.setText("");
					txtfMInitial.setText("");				
					txtfLName.setText("");				
					//txtfPhone.setText("");
					textFieldPhone.setText("");
					txtfEmail.setText("");			
					txtfPosition.setText("");				
					//txtfAgencyId.setText("");
					cmbAgencyID.setSelectedIndex(0);
				}  // End method reinitialize text field
				
	
				// Check empty text fields
				public boolean checkEmptyTextField()
				{
					if (txtfFName.getText().trim().equals(""))
					{
						JOptionPane.showMessageDialog(null, "First name should not be left blank");
						txtfFName.requestFocusInWindow();
						return false;
					}
					
					else if (txtfLName.getText().trim().equals(""))	
					{
						JOptionPane.showMessageDialog(null, "Last name should not be left blank");
						txtfLName.requestFocusInWindow();
						return false;
					}
					/*else if (txtfPhone.getText().trim().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Phone number should not be left blank");
						txtfPhone.requestFocusInWindow();
						return false;
					}*/
					
					else if (textFieldPhone.getText().trim().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Phone number should not be left blank");
						textFieldPhone.requestFocusInWindow();
						return false;
					}
								
					else if (txtfPosition.getText().trim().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Position should not be left blank");
						txtfPosition.requestFocusInWindow();
						return false;
					}
					else if (cmbAgencyID.getSelectedIndex()==0)
					{					
						JOptionPane.showMessageDialog(null, "Agency ID should not be left blank");
						cmbAgencyID.requestFocusInWindow();
						return false;
					}
					else
					{
						return true;
					}
				}  // End of method validation text fields
				
	
	//Method Adding new agent 					
	public void addAgent()
	{								
		DBConnection dbConn = new DBConnection();
		//Statement stmt;
		//ResultSet rs;					
		
			// Create statement
			try {
				//stmt = dbConn.get_Connection().createStatement();
				
				String sql = " INSERT INTO Agents (AgtFirstName, AgtMiddleInitial, AgtLastName, AgtBusPhone, AgtEmail, AgtPosition, AgencyId) values(?,?,?,?,?,?,?) ";
				PreparedStatement pstmt = dbConn.get_Connection().prepareStatement(sql);
				// Set the values
				pstmt.setString(1, txtfFName.getText());
				pstmt.setString(2, txtfMInitial.getText());
				pstmt.setString(3, txtfLName.getText());
				//pstmt.setString(4, txtfPhone.getText());
				pstmt.setString(4, textFieldPhone.getText());
				
				pstmt.setString(5, txtfEmail.getText());
				pstmt.setString(6, txtfPosition.getText());
				pstmt.setString(7, cmbAgencyID.getSelectedItem().toString());
				
				// execute prepared statement
				pstmt.execute();
				
				JOptionPane.showMessageDialog(null, "Agent data saved");
				
				// Close prepared statement
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			// End method adding mew agent

	// Method to get Agencies IDs and load combo box
	public void getAllAgenciesIDs()
	{
		DBConnection dbConn = new DBConnection();
		Statement stmt;
		ResultSet rs;		
			
		try{
			//cbAgentID.setSelectedIndex(0);
			
			// Create statement
			stmt = dbConn.get_Connection().createStatement();
			
			String sql = "SELECT * FROM agencies";
			// Submit the statement
			rs = stmt.executeQuery(sql);	
			
			//cmbAgencyID.addItem("Please select the code agency");
			while (rs.next())   //Build the model in loop.
			{						
				// Load comboBox - Agencies ID
				cmbAgencyID.addItem(rs.getString(1));
			}			
			rs.close();
			dbConn.get_Connection().close();
		}
		catch (Exception e)
		{
			System.err.println(e);
		}		
	}  // End method getting agencies IDs
	
		// Method to get list Agents
		public void getListAgent()
		{
			DBConnection dbConn = new DBConnection();
			Statement stmt;
			ResultSet rs;
			
			DefaultTableModel model = new DefaultTableModel();
			
			model.addColumn("Agent ID");
			model.addColumn("First Name");
			model.addColumn("Middle Initial");
			model.addColumn("Last Name");
			model.addColumn("Phone Number");
			model.addColumn("Email");
			model.addColumn("Position");
			model.addColumn("Agency ID");
			model.addColumn("Active");
			
			try{			
				// Create statement
				stmt = dbConn.get_Connection().createStatement();
				
				String sql = "SELECT * FROM agents";
				// Submit the statement
				rs = stmt.executeQuery(sql);	
				
				while (rs.next())   //Build the model in loop.
				{
					model.addRow(new Object[]{rs.getString("AgentId"), rs.getString("AgtFirstName"), rs.getString("AgtMiddleInitial") 
							, rs.getString("AgtLastName"), rs.getString("AgtBusPhone"), rs.getString("AgtEmail")
							, rs.getString("AgtPosition"), rs.getString("AgencyId"), rs.getString("Active")});		
				}			
				rs.close();
				dbConn.get_Connection().close();
			}
			catch (Exception e)
			{
				System.err.println(e);
			}
			// load table list Agents
			tableListAgents.setModel(model);		
		}  // End method to get list Agents
}



