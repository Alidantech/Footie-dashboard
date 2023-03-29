package uk.ac.sheffield.com1003.assignment.gui;

import uk.ac.sheffield.com1003.assignment.codeprovided.*;
import uk.ac.sheffield.com1003.assignment.codeprovided.gui.AbstractPlayerDashboardPanel;

import javax.swing.*;

/**
 * SKELETON IMPLEMENTATION
 */

public class PlayerDashboardPanel extends AbstractPlayerDashboardPanel
{

    // Constructor
    public PlayerDashboardPanel(AbstractPlayerCatalog playerCatalog) {
        super(playerCatalog);
    }

    @Override
    public void populatePlayerDetailsComboBoxes() {
        // TODO remove code below and implement, the comboboxes should be dynamically updated based
        // on user actions
        playerNamesList.add("Player 1");
        playerNamesList.add("Player 2");
        playerNamesList.add("Player 3");
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboPlayerNames.getModel();
        playerNamesList.add(0,"");
        model.addAll(playerNamesList);
    }

    /**
     * addListeners method - adds relevant actionListeners to the GUI components
     */
    @SuppressWarnings("unused")
    @Override
    public void addListeners() {
        // TODO implement

    }

    /**
     * clearFilters method - clears all filters from the subQueryList ArrayList and updates
     * the relevant GUI components
     */
    @Override
    public void clearFilters() {
        // TODO implement

    }

    @Override
    public void updateRadarChart() {
        // TODO implement

    }

    /**
     * updateStats method - updates the table with statistics after any changes which may
     * affect the JTable which holds the statistics
     */
    @Override
    public void updateStatistics() {
        // TODO implement

    }

    /**
     * updatePlayerCatalogDetailsBox method - updates the list of players when changes are made
     */
    @Override
    public void updatePlayerCatalogDetailsBox() {
        // TODO implement

    }

    /**
     * executeQuery method - applies chosen query to the relevant list
     */
    @Override
    public void executeQuery() {
        // TODO implement

    }

    /**
     * addFilters method - adds filters input into GUI to subQueryList ArrayList
     */
    @Override
    public void addFilter() {
        // TODO implement

    }
    @Override
    public boolean isMinCheckBoxSelected() {
        // TODO implement
        return false;
    }

    @Override
    public boolean isMaxCheckBoxSelected() {
        // TODO implement
        return false;
    }

    @Override
    public boolean isAverageCheckBoxSelected() {
        // TODO implement
        return false;
    }

}
