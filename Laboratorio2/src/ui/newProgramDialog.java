package ui;

import controller.ramControl;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.program;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class newProgramDialog extends JFrame {

	private JPanel contentPane;
	private JTextField textField_ProgramName;
	private JTextField textField_ProgramSize;
	private JTextField textField_ProgramTime;
	private JLabel lblProgramTime;
	private newProgramDialog selfPrgDialog;


	public newProgramDialog(simulationWindow mainForm, ramControl ramLOGIC) {
		this.selfPrgDialog = this;

		// Setting the size of the window and the layout of the window.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Creating a new text field and setting its bounds.
		textField_ProgramName = new JTextField();
		textField_ProgramName.setBounds(20, 46, 86, 20);
		contentPane.add(textField_ProgramName);
		textField_ProgramName.setColumns(10);
		
		// Creating a label and setting its bounds.
		JLabel lblProgramName = new JLabel("Ingrese el nombre del programa");
		lblProgramName.setBounds(20, 14, 218, 30);
		contentPane.add(lblProgramName);
		
		// Creating a label and setting its bounds.
		JLabel lblProgramSpace = new JLabel("Ingrese el peso del programa (En mb)");
		lblProgramSpace.setBounds(20, 77, 218, 30);
		contentPane.add(lblProgramSpace);
		
		// Creating a new text field and setting its bounds.
		textField_ProgramSize = new JTextField();
		textField_ProgramSize.setColumns(10);
		textField_ProgramSize.setBounds(20, 118, 86, 20);
		contentPane.add(textField_ProgramSize);
		
		// Creating a new text field and setting its bounds.
		textField_ProgramTime = new JTextField();
		textField_ProgramTime.setColumns(10);
		textField_ProgramTime.setBounds(20, 192, 86, 20);
		contentPane.add(textField_ProgramTime);
		
		// Creating a new label and setting its bounds.
		lblProgramTime = new JLabel("Ingrese por cuantos segundos correra el programa");
		lblProgramTime.setBounds(20, 149, 319, 48);
		contentPane.add(lblProgramTime);
		
		
		// Creating a button and adding an action listener to it.
		JButton btnAcceptProgram = new JButton("Aceptar");
		btnAcceptProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				// Getting the text from the text fields and converting it to an integer.
				String PrgName = textField_ProgramName.getText().trim();
				int PrgSize = Integer.parseInt(textField_ProgramSize.getText().trim());
				PrgSize = ramLOGIC.mbToBlock(PrgSize);
				
				// Getting the text from the text fields and converting it to an integer.
				int PrgTime = Integer.parseInt(textField_ProgramTime.getText().trim());
				
				// Adding a new program to the queue.
				mainForm.deletePreviousQueue(mainForm.getQueueUiBlocks());
				mainForm.getMyRam().getProgramsLINE().add(new program(PrgName, PrgSize, PrgTime));
				mainForm.UpdateQueueBlocks(mainForm.getQueueUiBlocks(), mainForm.getMyRam());
				
				
				// Updating the UI.
				getContentPane().revalidate();
				getContentPane().repaint();
				selfPrgDialog.dispose();		
			
			}
		});
		
		// Setting the bounds of the button and adding it to the content pane.
		btnAcceptProgram.setBounds(335, 227, 89, 23);
		contentPane.add(btnAcceptProgram);
	}
}
