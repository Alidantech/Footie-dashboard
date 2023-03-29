package uk.ac.sheffield.com1003.assignment.codeprovided.gui;

import javax.swing.*;

/**
 * Creates the GUI
 *
 * @version 1.1  09/02/2023
 *
 * @author Maria-Cruz Villa-Uriol (m.villa-uriol@sheffield.ac.uk)
 *
 * Copyright (c) University of Sheffield 2023
 */
public class PlayerEntryDashboard extends JFrame {

	public PlayerEntryDashboard(AbstractPlayerDashboardPanel panel) {
		setTitle("Footie Dashboard");
		add(panel);
		// maximises the JFrame
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
