package ui;

import model.ram;
import java.util.ArrayList;
import controller.fileManager;
import controller.ramControl;
import model.program;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class simulationWindow extends JFrame {
	
	private JPanel contentPane;
	private ram MyRam;
	private simulationWindow ActualSimulation;
	private int seconds;
	private int minutes;
	private JLabel lblRAMType;
	private JLabel lblRAMSize;
	private ramControl RAMLogic;
	private ArrayList<JLabel> QueueUiBlocks;
	private ArrayList<JLabel> RAMUiBlocks;
	Timer timer;
	JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public simulationWindow() {
		RAMLogic = new ramControl(MyRam);
		QueueUiBlocks = new ArrayList<JLabel>();
		RAMUiBlocks = new ArrayList<JLabel>();
		
		this.ActualSimulation = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.setBounds(10, 11, 116, 29);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("            Menu            ");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Set RAM");
		mnNewMenu.add(mntmNewMenuItem);
		
		JLabel lblRUNTIME = new JLabel(" - ");
		lblRUNTIME.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRUNTIME.setBounds(10, 187, 116, 29);
		contentPane.add(lblRUNTIME);
		
		timer = new Timer (1000, new ActionListener ()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	TimerWork();
		    	
		    	lblRUNTIME.setText(minutes + ":" + seconds);
		    	
		    	if (RAMLogic.ProgramsTimeOutram(MyRam) == true) {
		    		deletePreviousQueue(RAMUiBlocks);
					UpdateRAMBlocks(RAMUiBlocks, MyRam);
					getContentPane().revalidate();
					getContentPane().repaint();
		    	}
		    	
		    	
		    }
		});
		
		
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("Button RAM works");
					MyRam = new ram ("",0,0);
					newRamDialog RamDialog = new newRamDialog(ActualSimulation);
					RamDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					RamDialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Add Program");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JLabel lblNewLabel = new JLabel("Cantidad de Ram: ");
		lblNewLabel.setBounds(10, 124, 116, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblTipoDeRam = new JLabel("Tipo de Ram: ");
		lblTipoDeRam.setBounds(10, 71, 116, 14);
		contentPane.add(lblTipoDeRam);
		
		lblRAMType = new JLabel("-");
		lblRAMType.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRAMType.setBounds(10, 92, 63, 14);
		contentPane.add(lblRAMType);
		
		lblRAMSize = new JLabel("-");
		lblRAMSize.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRAMSize.setBounds(10, 136, 160, 29);
		contentPane.add(lblRAMSize);
		
		JLabel lblTime = new JLabel("Tiempo de la Simulacion");
		lblTime.setBounds(10, 176, 167, 14);
		contentPane.add(lblTime);
		
		JButton btnStart = new JButton("Iniciar");
		btnStart.setBounds(10, 227, 89, 23);
		contentPane.add(btnStart);
		
		JLabel lblNewLabel_1 = new JLabel("Lista de Espera");
		lblNewLabel_1.setBounds(199, 11, 116, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("En Ejecucion");
		lblNewLabel_1_1.setBounds(331, 11, 116, 14);
		contentPane.add(lblNewLabel_1_1);
		
		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fileManager InitialProgramsReader = new fileManager();
					InitialProgramsReader.GetFilePrograms(MyRam);
					
					deletePreviousQueue(QueueUiBlocks);
					UpdateQueueBlocks(QueueUiBlocks, MyRam);
					
					deletePreviousQueue(RAMUiBlocks);
					UpdateRAMBlocks(RAMUiBlocks, MyRam);
					
					timer.start();
					
					
				} catch (Exception e1) {

				}
			}
			
		});
		
		
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("Button Program works");
					newProgramDialog Programdialog = new newProgramDialog(ActualSimulation, RAMLogic);
					Programdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					Programdialog.setVisible(true);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
	}
	
	public void TimerWork() {
		this.seconds += 1;
			
		if (this.seconds > 59) {
			this.seconds = 0;
			this.minutes++;
		}
		
	}

	public JLabel getLblRAMType() {
		return lblRAMType;
	}

	public JLabel getLblRAMSize() {
		return lblRAMSize;
	}

	public ram getMyRam() {
		return MyRam;
	}

	public ArrayList<JLabel> getQueueUiBlocks() {
		return QueueUiBlocks;
	}
	
	public ArrayList<JLabel> getRAMUiBlocks() {
		return RAMUiBlocks;
	}

	public void UpdateQueueBlocks(ArrayList<JLabel> _blocks, ram _MyRam) {
		_blocks.clear();
		int Position = 1;
		
		for (program aProgram : _MyRam.getProgramsLINE()) {

			JLabel newProgramlbl = new JLabel("" + aProgram.getName());
			newProgramlbl.setBounds(200, 40 * Position, 90,20);
			newProgramlbl.setOpaque(true);
			newProgramlbl.setBackground(SystemColor.activeCaption);
			
			_blocks.add(newProgramlbl);
			Position+=1;	
			
			getContentPane().add(newProgramlbl);
			getContentPane().revalidate();
			getContentPane().repaint();
		}
		
		
	}
	
	public void UpdateRAMBlocks(ArrayList<JLabel> _blocks, ram _MyRam) {
		_blocks.clear();
		
		int Position = 1;
		
		for (program aProgram : _MyRam.getProgramsEXE()) {
			for(int i = 0; i < aProgram.getProgramSize() ; i++) {
				JLabel newEXElbl = new JLabel("" + aProgram.getName());
				newEXElbl.setBounds(330, 40 * Position, 90,20);
				newEXElbl.setOpaque(true);
				newEXElbl.setBackground(Color.RED);
				_blocks.add(newEXElbl);
				Position+=1;
				
				getContentPane().add(newEXElbl);
				getContentPane().revalidate();
				getContentPane().repaint();
				System.out.println(_MyRam.getAvailableMemory() + "a" + Position);
			} 

		}
		
		for (int j = 0; j < _MyRam.getAvailableMemory(); j++) {
			JLabel newFreelbl = new JLabel("LIBRE");
			newFreelbl.setBounds(330, 40 * Position, 90,20);
			newFreelbl.setOpaque(true);
			newFreelbl.setBackground(Color.GREEN);
			_blocks.add(newFreelbl);
			getContentPane().add(newFreelbl);
			getContentPane().revalidate();
			getContentPane().repaint();
			Position += 1;
		}
		
		
	}
	
	public void deletePreviousQueue(ArrayList<JLabel> _blocksForLabels) {
		for(int i = 0; i < _blocksForLabels.size(); i++) {
			
			getContentPane().remove(_blocksForLabels.get(i));
			getContentPane().revalidate();
			getContentPane().repaint();
		}
	}
}