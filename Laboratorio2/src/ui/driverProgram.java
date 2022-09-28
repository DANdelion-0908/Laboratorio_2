package ui;
import model.ram;

import java.awt.EventQueue;

import model.program;

public class driverProgram {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					simulationWindow frame = new simulationWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
