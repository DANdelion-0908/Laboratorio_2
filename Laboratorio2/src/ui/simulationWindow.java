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

	/**
	 * Create the frame.
	 */
	public simulationWindow() {
		RAMLogic = new ramControl(MyRam);
		QueueUiBlocks = new ArrayList<JLabel>();
		RAMUiBlocks = new ArrayList<JLabel>();
		
		this.ActualSimulation = this;

		// Creating the frame.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Creating a menu bar.
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.setBounds(10, 11, 116, 29);
		contentPane.add(menuBar);
		
		// Creating a menu bar.
		JMenu mnNewMenu = new JMenu("            Menu            ");
		menuBar.add(mnNewMenu);
		
		// Creating a menu item called "Set RAM" and adding it to the menu.
		JMenuItem mntmNewMenuItem = new JMenuItem("Set RAM");
		mnNewMenu.add(mntmNewMenuItem);
		
		// Creating a label called lblRUNTIME and setting its font and bounds.
		JLabel lblRUNTIME = new JLabel(" - ");
		lblRUNTIME.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRUNTIME.setBounds(10, 187, 116, 29);
		contentPane.add(lblRUNTIME);
		
		JPanel panel = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPanel(panel);
		panel.setLayout(null);
		
		
		JScrollPane scroll = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		JLabel label = new JLabel("New label");
		label.setBounds(120, 109, 46, 14);
		panel.add(label);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(189, 86, 46, 14);
		
		
		scroll.setBounds(199, 73, 372, 279);
		contentPane.add(scroll);
		
		JLabel lblRAMSize_2 = new JLabel("-");
		lblRAMSize_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRAMSize_2.setBounds(10, 150, 160, 23);
		contentPane.add(lblRAMSize_2);
		
		// Creating a timer that will execute the code inside the `actionPerformed` method every 1000
		// milliseconds.
		timer = new Timer (1000, new ActionListener ()
		{
		    // A method that is called every time the timer ticks.
			public void actionPerformed(ActionEvent e)
		    {
		    	TimerWork();
		    	lblRAMSize.setText(MyRam.getRamStorage() * 64 + " mb or " + MyRam.getRamStorage() + " bloques  (TOTALES)");
		    	lblRAMSize_2.setText(MyRam.getAvailableMemory() * 64 + " mb or " + MyRam.getAvailableMemory() + " bloques (EN USO)");
		    	/*Setting the text of the label `lblRUNTIME` to the value of the variables `minutes` and
				`seconds`.*/
				lblRUNTIME.setText(minutes + ":" + seconds);
		    	
		    	// Checking if the program has timed out, if it has, it deletes the previous queue and updates
				RAMLogic.reduceProgramsTime(MyRam);
				// the RAM blocks.
				if (RAMLogic.ProgramsTimeOutram(MyRam) == true) {
		    		deletePreviousQueue(RAMUiBlocks);
					UpdateRAMBlocks(RAMUiBlocks, MyRam, panel);
					getContentPane().revalidate();
					getContentPane().repaint();
		    	}
		    	
		    	for (int j = 0; j < MyRam.getProgramsLINE().size(); j++) {
		    		
		    		if(RAMLogic.CanAddProgramToRam(MyRam, MyRam.getProgramsLINE().get(j) ) ) {
		    			RAMLogic.addProgramToRam(MyRam, MyRam.getProgramsLINE().get(j));
		    			MyRam.getProgramsLINE().remove(j);
		    			
		    			deletePreviousQueue(QueueUiBlocks);
						UpdateQueueBlocks(QueueUiBlocks, MyRam);
						
						deletePreviousQueue(RAMUiBlocks);
						UpdateRAMBlocks(RAMUiBlocks, MyRam, panel);
						
						getContentPane().revalidate();
						getContentPane().repaint();
						
		    		}
		    	}
		    	
		    	if (MyRam.getRamType().equals("ddr")) {
		    		System.out.println("Is ddr");
		    	}
		    	
		    }
		});
		
		
		// Adding an action listener to the menu item `mntmNewMenuItem`.
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
		
		// Creating a menu item called "Add Program" and adding it to the menu.
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Add Program");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		// Creating a label called `lblNewLabel` and setting its text to "Cantidad de Ram: ", its bounds to
		// (10, 124, 116, 14) and adding it to the content pane.
		JLabel lblNewLabel = new JLabel("Cantidad de Ram: ");
		lblNewLabel.setBounds(10, 124, 116, 14);
		contentPane.add(lblNewLabel);
		
		// Creating a label called `lblTipoDeRam` and setting its text to "Tipo de Ram: ", its bounds to
		// (10, 71, 116, 14) and adding it to the content pane.
		JLabel lblTipoDeRam = new JLabel("Tipo de Ram: ");
		lblTipoDeRam.setBounds(10, 71, 116, 14);
		contentPane.add(lblTipoDeRam);
		
		// Creating a label called `lblRAMType` and setting its text to "-", its font to `Tahoma` and its
		// bounds to `(10, 92, 63, 14)`.
		lblRAMType = new JLabel("-");
		lblRAMType.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRAMType.setBounds(10, 92, 63, 14);
		contentPane.add(lblRAMType);
		
		// Creating a label called `lblRAMSize` and setting its text to "-", its font to `Tahoma` and its
		// bounds to `(10, 136, 160, 29)`.
		lblRAMSize = new JLabel("-");
		lblRAMSize.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRAMSize.setBounds(10, 136, 160, 29);
		contentPane.add(lblRAMSize);
		
		// Creating a label called `lblTime` and setting its text to "Tiempo de la Simulacion", its bounds to
		// (10, 176, 167, 14) and adding it to the content pane.
		JLabel lblTime = new JLabel("Tiempo de la Simulacion");
		lblTime.setBounds(10, 176, 167, 14);
		contentPane.add(lblTime);
		
		// Creating a button called `btnStart` and setting its text to "Iniciar", its bounds to `(10, 227,
		// 89, 23)` and adding it to the content pane.
		JButton btnStart = new JButton("Iniciar");
		btnStart.setBounds(10, 227, 89, 23);
		contentPane.add(btnStart);
		
		// Creating a label called `lblNewLabel_1` and setting its text to "Lista de Espera", its bounds to
		// (199, 11, 116, 14) and adding it to the content pane.
		JLabel lblNewLabel_1 = new JLabel("Lista de Espera");
		lblNewLabel_1.setBounds(199, 11, 116, 14);
		contentPane.add(lblNewLabel_1);
		
		// Creating a label called `lblNewLabel_1_1` and setting its text to "En Ejecucion", its bounds to
		// (331, 11, 116, 14) and adding it to the content pane.
		JLabel lblNewLabel_1_1 = new JLabel("En Ejecucion");
		lblNewLabel_1_1.setBounds(331, 11, 116, 14);
		contentPane.add(lblNewLabel_1_1);
		

		
		// Adding an action listener to the button `btnStart`.
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Reading the programs from a file and adding them to the RAM.
					fileManager InitialProgramsReader = new fileManager();
					InitialProgramsReader.GetFilePrograms(MyRam);
					
					// Deleting the previous queue and updating the RAM blocks.
					deletePreviousQueue(QueueUiBlocks);
					UpdateQueueBlocks(QueueUiBlocks, MyRam);
					
					// Deleting the previous queue and updating the RAM blocks.
					deletePreviousQueue(RAMUiBlocks);
					UpdateRAMBlocks(RAMUiBlocks, MyRam, panel);
					
					timer.start();
					
					
				
				// Catching any exception that might occur and printing it to the console.
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
			
		});
		
		
		// Adding an action listener to the menu item `mntmNewMenuItem_1`.
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
	
	private Object getPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	private void setPanel(JPanel panel) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * If the seconds are greater than 59, reset the seconds to 0 and increment the minutes.
	 */
	public void TimerWork() {
		this.seconds += 1;
			
		if (this.seconds > 59) {
			this.seconds = 0;
			this.minutes++;
		}
		
	}

	/**
	 * This function returns the JLabel lblRAMType
	 * 
	 * @return The RAM type label.
	 */
	public JLabel getLblRAMType() {
		return lblRAMType;
	}

	/**
	 * This function returns the JLabel lblRAMSize
	 * 
	 * @return The label lblRAMSize is being returned.
	 */
	public JLabel getLblRAMSize() {
		return lblRAMSize;
	}

	/**
	 * This function returns the value of the variable MyRam
	 * 
	 * @return The ram object.
	 */
	public ram getMyRam() {
		return MyRam;
	}

	/**
	 * This function returns the ArrayList of JLabels that represent the blocks in the queue
	 * 
	 * @return The ArrayList of JLabels that are used to represent the queue in the UI.
	 */
	public ArrayList<JLabel> getQueueUiBlocks() {
		return QueueUiBlocks;
	}
	
	/**
	 * This function returns the ArrayList of JLabels that represent the RAM blocks
	 * 
	 * @return The RAMUiBlocks ArrayList.
	 */
	public ArrayList<JLabel> getRAMUiBlocks() {
		return RAMUiBlocks;
	}

	// Clearing the ArrayList of JLabels that represent the blocks in the queue and setting the variable
	// `Position` to 1.
	public void UpdateQueueBlocks(ArrayList<JLabel> _blocks, ram _MyRam) {
		_blocks.clear();
		int Position = 1;
		
		// Creating a JLabel for each program in the queue and adding it to the content pane.
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
	
	// Clearing the ArrayList of JLabels that represent the blocks in the RAM.
	public void UpdateRAMBlocks(ArrayList<JLabel> _blocks, ram _MyRam, JPanel panel) {
		_blocks.clear();
		
		int Position = 1;
		
		// Creating a JLabel for each program in the RAM and adding it to the content pane.
		for (program aProgram : _MyRam.getProgramsEXE()) {
			for(int i = 0; i < aProgram.getProgramSize() ; i++) {
				JLabel newEXElbl = new JLabel("" + aProgram.getName());
				newEXElbl.setBounds(330, 40 * Position, 90,20);
				newEXElbl.setOpaque(true);
				newEXElbl.setBackground(Color.RED);
				_blocks.add(newEXElbl);
				Position+=1;
				
				// Adding the JLabel to the content pane, revalidating it and repainting it.
				getContentPane().add(newEXElbl);
				getContentPane().revalidate();
				getContentPane().repaint();
			} 

		}
		
		// Creating a JLabel for each block of free memory in the RAM.
		for (int j = 0; j < _MyRam.getAvailableMemory(); j++) {
			JLabel newFreelbl = new JLabel("LIBRE");
			newFreelbl.setBounds(330, 40 * Position, 90,20);
			newFreelbl.setOpaque(true);
			newFreelbl.setBackground(Color.GREEN);
			_blocks.add(newFreelbl);
			
			panel.add(newFreelbl);
			panel.revalidate();
			panel.repaint();
			
			getContentPane().add(newFreelbl);
			getContentPane().revalidate();
			getContentPane().repaint();
			Position += 1;
		}
		
		
	}
	
	// Deleting the previous queue.
	public void deletePreviousQueue(ArrayList<JLabel> _blocksForLabels) {
		for(int i = 0; i < _blocksForLabels.size(); i++) {
			
			// Removing the JLabel from the content pane, revalidating it and repainting it.
			getContentPane().remove(_blocksForLabels.get(i));
			getContentPane().revalidate();
			getContentPane().repaint();
		}
	}
}