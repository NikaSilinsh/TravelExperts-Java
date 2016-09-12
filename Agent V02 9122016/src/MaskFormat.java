import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class MaskFormat extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 new MaskFormat();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MaskFormat() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		try{
			JFrame Window = new JFrame("JFormattedTextField");
			
			
			
				// create the formatter
		      MaskFormatter phoneNumberMaskFormatter = new MaskFormatter("(###)###-####");		   
		      //  set place hold character to _
		      phoneNumberMaskFormatter.setPlaceholderCharacter('_');
		      
		      
		      JFormattedTextField textField = new JFormattedTextField(phoneNumberMaskFormatter);
			   Window.getContentPane().add(textField);
			   textField.setBounds(25, 68, 82, 20);
		      
		   
		// create the JFormattedTextField
		   JFormattedTextField phoneFormattedTextField = new JFormattedTextField(phoneNumberMaskFormatter);  
		   phoneFormattedTextField.setBounds(257, 5, 48, 20);
		   Window.getContentPane().setLayout(null);
		   
		   
		 
		 Window.getContentPane().add(phoneFormattedTextField);
		   Window.setSize(526, 295);
		   Window.setVisible(true);
		   //Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   }
		   catch (Exception e)
		   {
		   }
		//createFormatter(s);
	}

	
	
	


}
