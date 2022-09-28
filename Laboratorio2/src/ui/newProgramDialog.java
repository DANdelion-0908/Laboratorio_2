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

	/**
	 * Create the frame.
	 */
	public newProgramDialog(simulationWindow mainForm, ramControl ramLOGIC) {
		this.selfPrgDialog = this;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_ProgramName = new JTextField();
		textField_ProgramName.setBounds(20, 46, 86, 20);
		contentPane.add(textField_ProgramName);
		textField_ProgramName.setColumns(10);
		
		JLabel lblProgramName = new JLabel("Ingrese el nombre del programa");
		lblProgramName.setBounds(20, 14, 218, 30);
		contentPane.add(lblProgramName);
		
		JLabel lblProgramSpace = new JLabel("Ingrese el peso del programa (En mb)");
		lblProgramSpace.setBounds(20, 77, 218, 30);
		contentPane.add(lblProgramSpace);
		
		textField_ProgramSize = new JTextField();
		textField_ProgramSize.setColumns(10);
		textField_ProgramSize.setBounds(20, 118, 86, 20);
		contentPane.add(textField_ProgramSize);
		
		textField_ProgramTime = new JTextField();
		textField_ProgramTime.setColumns(10);
		textField_ProgramTime.setBounds(20, 192, 86, 20);
		contentPane.add(textField_ProgramTime);
		
		lblProgramTime = new JLabel("Ingrese por cuantos segundos correra el programa");
		lblProgramTime.setBounds(20, 149, 319, 48);
		contentPane.add(lblProgramTime);
		
		JButton btnAcceptProgram = new JButton("Aceptar");
		btnAcceptProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String PrgName = textField_ProgramName.getText().trim();
				int PrgSize = Integer.parseInt(textField_ProgramSize.getText().trim());
				PrgSize = ramLOGIC.mbToBlock(PrgSize);
				System.out.println(PrgSize);
				
				int PrgTime = Integer.parseInt(textField_ProgramTime.getText().trim());
				System.out.println(PrgName +" "+ PrgSize +" "+ PrgTime);
				
				mainForm.deletePreviousQueue(mainForm.getQueueUiBlocks());
				mainForm.getMyRam().getProgramsLINE().add(new program(PrgName, PrgSize, PrgTime));
				mainForm.UpdateQueueBlocks(mainForm.getQueueUiBlocks(), mainForm.getMyRam());
				
				
				mainForm.deletePreviousQueue(mainForm.getRAMUiBlocks());
				mainForm.getMyRam().getProgramsEXE().add(new program(PrgName, PrgSize, PrgTime));
				mainForm.UpdateRAMBlocks(mainForm.getRAMUiBlocks(), mainForm.getMyRam());
				
				
				getContentPane().revalidate();
				getContentPane().repaint();
				selfPrgDialog.dispose();
				
			
			}
		});
		btnAcceptProgram.setBounds(335, 227, 89, 23);
		contentPane.add(btnAcceptProgram);
	}
}
