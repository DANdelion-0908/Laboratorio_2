package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ramControl;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class newRamDialog extends JFrame {

	private JPanel contentPane;
	private newRamDialog selfRamDialog;

	/**
	 * Create the frame.
	 */
	public newRamDialog(simulationWindow mainForm) {
		this.selfRamDialog = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultComboBoxModel<String> RamTypeList = new DefaultComboBoxModel<String>();
		RamTypeList.addElement("sdr");
		RamTypeList.addElement("ddr");
		
		JComboBox<String> comboBoxRamType = new JComboBox<String>();
		comboBoxRamType.setBounds(27, 54, 56, 22);
		comboBoxRamType.setModel(RamTypeList);
		comboBoxRamType.setSelectedIndex(0);
		
		contentPane.add(comboBoxRamType);
		
		DefaultComboBoxModel<String> RamSizes = new DefaultComboBoxModel<String>();
		RamSizes.addElement("4");
		RamSizes.addElement("8");
		RamSizes.addElement("12");
		RamSizes.addElement("16");
		RamSizes.addElement("32");
		RamSizes.addElement("64");
		
		String hi = (String) comboBoxRamType.getSelectedItem();
		System.out.println(hi);
		
		JComboBox<String> comboBoxRamSize = new JComboBox<String>();
		comboBoxRamSize.setBounds(27, 129, 56, 22);
		comboBoxRamSize.setModel(RamSizes);
		comboBoxRamSize.setSelectedIndex(0);
		
		contentPane.add(comboBoxRamSize);
		
		JLabel lblRamType = new JLabel("Eliga el tipo de memoria RAM");
		lblRamType.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblRamType.setBounds(27, 23, 190, 20);
		contentPane.add(lblRamType);
		
		JButton btnAcceptSpecs = new JButton("Aceptar");
		btnAcceptSpecs.setBounds(323, 215, 89, 23);
		contentPane.add(btnAcceptSpecs);
		
		JLabel lblRamSize = new JLabel("Eliga la cantidad de Gigas");
		lblRamSize.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblRamSize.setBounds(27, 98, 190, 20);
		contentPane.add(lblRamSize);
		
		btnAcceptSpecs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String ramType = (String) comboBoxRamType.getSelectedItem();
					int ramSize = Integer.parseInt((String) comboBoxRamSize.getSelectedItem()) * 1024;
					
					mainForm.getMyRam().setRamType(ramType);
					mainForm.getMyRam().setRamStorage(ramSize / 64);
					mainForm.getMyRam().setAvailableMemory(ramSize / 64);
		
					mainForm.getLblRAMType().setText(ramType);
					mainForm.getLblRAMSize().setText(ramSize +" mb or " + ramSize / 64+ " bloques") ;
					
					selfRamDialog.dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
