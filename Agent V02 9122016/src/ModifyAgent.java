import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

//import com.mysql.jdbc.Statement;

import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModifyAgent extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtfFName;
	private JTextField txtfMInitial;
	private JTextField txtfLName;
	private JTextField txtfEmail;
	private JTextField txtfPosition;
	private JComboBox<String> cbAgentID;
	private JComboBox<String> cmbAgencyID;
	private JRadioButton rdbtnAgtStatusActive;
	private JRadioButton rdbtnAgtStatusInactive;
	private JFormattedTextField textFieldPhone;
	private JButton btnCancel;
	private JTable tableListAgents;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyAgent frame = new ModifyAgent();
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
	public ModifyAgent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1140, 607);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAgentId = new JLabel("Agent ID:");
		lblAgentId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAgentId.setBounds(46, 56, 96, 14);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstName.setBounds(46, 82, 96, 14);
		
		JLabel lblMidleInitial = new JLabel("Midle Initial:");
		lblMidleInitial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMidleInitial.setBounds(46, 108, 96, 14);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastName.setBounds(46, 134, 96, 14);
		
		JLabel lblPhone = new JLabel("Phone Number:");
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhone.setBounds(46, 160, 96, 14);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(46, 186, 96, 14);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPosition.setBounds(46, 212, 96, 14);
		
		JLabel lblAgencyId = new JLabel("Agency ID:");
		lblAgencyId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAgencyId.setBounds(46, 238, 74, 14);
		
		JPanel panel = new JPanel();
		panel.setBounds(62, 268, 157, 67);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), " Agent Status", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		cbAgentID = new JComboBox<String>();
		cbAgentID.setBounds(145, 54, 70, 19);
		cbAgentID.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				// Calling this method to display Agent data
				getAgent();
			}
		});
		
		txtfFName = new JTextField();
		txtfFName.setBounds(145, 79, 223, 20);
		txtfFName.setColumns(10);
		
		txtfMInitial = new JTextField();
		txtfMInitial.setBounds(145, 105, 223, 20);
		txtfMInitial.setColumns(10);
		
		txtfLName = new JTextField();
		txtfLName.setBounds(145, 131, 223, 20);
		txtfLName.setColumns(10);
		
		txtfEmail = new JTextField();
		txtfEmail.setBounds(145, 183, 223, 20);
		txtfEmail.setColumns(10);
		
		txtfPosition = new JTextField();
		txtfPosition.setBounds(145, 209, 223, 20);
		txtfPosition.setColumns(10);
		
		cmbAgencyID = new JComboBox<String>();
		cmbAgencyID.setBounds(145, 235, 70, 20);
		
		rdbtnAgtStatusActive = new JRadioButton("Active");
		panel.add(rdbtnAgtStatusActive);
		buttonGroup.add(rdbtnAgtStatusActive);
		rdbtnAgtStatusActive.setSelected(true);
		
		rdbtnAgtStatusInactive = new JRadioButton("Inactive");
		panel.add(rdbtnAgtStatusInactive);
		buttonGroup.add(rdbtnAgtStatusInactive);
		contentPane.setLayout(null);
		contentPane.add(lblLastName);
		contentPane.add(lblMidleInitial);
		contentPane.add(panel);
		contentPane.add(lblAgentId);
		contentPane.add(lblFirstName);
		contentPane.add(lblPhone);
		contentPane.add(lblEmail);
		contentPane.add(lblPosition);
		contentPane.add(lblAgencyId);
		contentPane.add(cbAgentID);
		contentPane.add(txtfFName);
		contentPane.add(txtfMInitial);
		contentPane.add(txtfLName);
		contentPane.add(txtfEmail);
		contentPane.add(txtfPosition);
		contentPane.add(cmbAgencyID);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (checkEmptyTextField())  // Validation empty text fields
				{	
					updateAgent();					
					getListInactiveAgent();  // Calling this method to display list inactive agent 					
				}
			}
		});
		btnUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		btnUpdate.setBounds(70, 413, 103, 33);
		contentPane.add(btnUpdate);
		Image img = new ImageIcon(this.getClass().getResource("/edit-file.png")).getImage();
		btnUpdate.setIcon(new ImageIcon(img));
				
		try 
		{
			   // create the formatter 
			MaskFormatter phoneNumberMaskFormatter = new MaskFormatter("(###) ###-####");
		      //  set place hold character to _
		    phoneNumberMaskFormatter.setPlaceholderCharacter('_');
		      
		    textFieldPhone = new JFormattedTextField(phoneNumberMaskFormatter);
			textFieldPhone.setBounds(145, 157, 96, 20);
			contentPane.add(textFieldPhone);	   
			
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					setEmptyTexfield();
				}
			});
			btnCancel.setHorizontalAlignment(SwingConstants.LEFT);
			btnCancel.setBounds(183, 413, 103, 33);
			contentPane.add(btnCancel);
			Image img1 = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
			btnCancel.setIcon(new ImageIcon(img1));
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Update Agent", TitledBorder.CENTER, TitledBorder.TOP, null, null));
			panel_1.setBounds(28, 26, 381, 331);
			contentPane.add(panel_1);
			
			scrollPane = new JScrollPane();
			scrollPane.setBounds(437, 33, 675, 323);
			contentPane.add(scrollPane);
			
			tableListAgents = new JTable();
			tableListAgents.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					setEmptyTexfield();   // Initialize text fields
					
					DefaultTableModel model = (DefaultTableModel) tableListAgents.getModel();

					cbAgentID.setSelectedItem(model.getValueAt(tableListAgents.getSelectedRow(), 0).toString());
					if (model.getValueAt(tableListAgents.getSelectedRow(), 1)  != null) txtfFName.setText(model.getValueAt(tableListAgents.getSelectedRow(), 1).toString());

					if (model.getValueAt(tableListAgents.getSelectedRow(), 2)  != null) txtfMInitial.setText(model.getValueAt(tableListAgents.getSelectedRow(), 2).toString());

					if (model.getValueAt(tableListAgents.getSelectedRow(), 3)  != null) txtfLName.setText(model.getValueAt(tableListAgents.getSelectedRow(), 3).toString());

					if (model.getValueAt(tableListAgents.getSelectedRow(), 4)  != null) textFieldPhone.setText(model.getValueAt(tableListAgents.getSelectedRow(), 4).toString());

					if (model.getValueAt(tableListAgents.getSelectedRow(), 5)  != null) txtfEmail.setText(model.getValueAt(tableListAgents.getSelectedRow(), 5).toString());

					if (model.getValueAt(tableListAgents.getSelectedRow(), 6)  != null) txtfPosition.setText(model.getValueAt(tableListAgents.getSelectedRow(), 6).toString());

					if (model.getValueAt(tableListAgents.getSelectedRow(), 7)  != null) cmbAgencyID.setSelectedItem(model.getValueAt(tableListAgents.getSelectedRow(), 7).toString());
				}
			});
			scrollPane.setViewportView(tableListAgents);
		} 
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// calling the method to get Agents IDs 
		getAgentIDs();
		// calling the method to get Agencies IDs 
		getAllAgenciesIDs();
		
		// Calling this method to display Agent data
		getAgent();
		
		// Calling this method to display list inactive agent 
		getListInactiveAgent();
		
		
	}
	
		// Method to get Agents IDs and load combo box
		public void getAgentIDs()
		{
			DBConnection dbConn = new DBConnection();
			Statement stmt;
			ResultSet rs;		
				
			try{							
				// Create statement
				stmt = dbConn.get_Connection().createStatement();
				
				String sql = "SELECT * FROM agents";
				// Submit the statement
				rs = stmt.executeQuery(sql);	
				
				while (rs.next())   //Build the model in loop.
				{						
					// Load comboBox - Agents ID
					cbAgentID.addItem(rs.getString(1));
				}			
				rs.close();
				dbConn.get_Connection().close();
			}
			catch (Exception e)
			{
				System.err.println(e);
			}		
		}// Method to get Agents IDs 
	
		// Method to get Agencies IDs and load combo box
		public void getAllAgenciesIDs()
		{
			DBConnection dbConn = new DBConnection();
			Statement stmt;
			ResultSet rs;		
				
			try{
				// Create statement
				stmt = dbConn.get_Connection().createStatement();
				
				String sql = "SELECT * FROM agencies";
				// Submit the statement
				rs = stmt.executeQuery(sql);	
				
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
	
		
		// Method reinitialize text field
					public void setEmptyTexfield()
					{
						txtfFName.setText("");
						txtfMInitial.setText("");				
						txtfLName.setText("");				
						textFieldPhone.setText("");
						txtfEmail.setText("");			
						txtfPosition.setText("");				
						
					}
		
		// Method to get Agent data
				public void getAgent()
				{
					DBConnection dbConn = new DBConnection();
					Statement stmt;
					ResultSet rs;				
					
					try{
						// Create statement
						stmt = dbConn.get_Connection().createStatement();
						
						String sql = "SELECT * FROM agents WHERE AgentID =" + cbAgentID.getSelectedItem();
						// Submit the statement
						rs = stmt.executeQuery(sql);
						if (rs.next())  
						{ 
							setEmptyTexfield();
							// fill text fields
						if (rs.getString("AgtFirstName") != null)     txtfFName.setText(rs.getString("AgtFirstName").toString());				
						if (rs.getString("AgtMiddleInitial") !=null)  txtfMInitial.setText(rs.getString("AgtMiddleInitial").toString());									
						if (rs.getString("AgtLastName") != null )     txtfLName.setText(rs.getString("AgtLastName").toString());
						if (rs.getString("AgtBusPhone")  != null)     textFieldPhone.setText(rs.getString("AgtBusPhone").toString());
						if (rs.getString("AgtEmail") != null)         txtfEmail.setText(rs.getString("AgtEmail").toString());
						if (rs.getString("AgtPosition") != null)      txtfPosition.setText(rs.getString("AgtPosition").toString());
						if (rs.getString("AgencyId") != null)         cmbAgencyID.setSelectedItem(rs.getString("AgencyId").toString());	
							if (rs.getString("Active").equals("Yes"))
							{
								rdbtnAgtStatusActive.setSelected(true);							
							}
							else
							{
								rdbtnAgtStatusInactive.setSelected(true);							
							}	
						}
						else
						{
							System.out.println("No row returned");
						}
					}
					catch (Exception e)
					{
						System.err.println(e);
					}					
				}// End of getAgent
				
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
						JOptionPane.showMessageDialog(null, "Lst name should not be left blank");
						txtfLName.requestFocusInWindow();
						return false;
					}
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
					else
					{
						return true;
					}
				}
				
				// Method update agent data					
				public void updateAgent()
				{								
					DBConnection dbConn = new DBConnection();
					Statement stmt;						
					String statusAgent;
					
					if (rdbtnAgtStatusActive.isSelected())
					{
						statusAgent="Yes";   // agent active
					}
					else
					{
						statusAgent="No";  // agent not active
					}
					
					try{
						// Create statement
						stmt = dbConn.get_Connection().createStatement();
						
					
					String sql = "UPDATE agents SET "
				        	+ "AgtFirstName='" + txtfFName.getText()			        	
				        	+ "', AgtMiddleInitial='" + txtfMInitial.getText()			        	
				        	+ "', AgtLastName='" + txtfLName.getText()   		        	
				        	+ "', AgtBusPhone='" + textFieldPhone.getText()			        	
				        	+ "', AgtEmail='" + txtfEmail.getText() 
				        	+ "', AgtPosition='" + txtfPosition.getText() 
				        	+ "', Active='" + statusAgent
				        	+ "', AgencyId=" + cmbAgencyID.getSelectedItem().toString()				        					        	
				        	+ " WHERE AgentID=" + cbAgentID.getSelectedItem(); 					 		        
				        
				        int numRows;
						//try {
							numRows = stmt.executeUpdate(sql);
							if (numRows == 0)
							{
								System.out.println("update failed");
							}
							else
							{
								System.out.println("updated " + numRows + " row(s)");
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
									
					setEmptyTexfield();
				}// End method update agent
				
				// Method to get list Agent 
				public void getListInactiveAgent()
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
						
						String sql = "SELECT * FROM agents WHERE Active =" + "'No'";
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
				}
}
