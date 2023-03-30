package uk.ac.sheffield.com1003.assignment.gui;
import uk.ac.sheffield.com1003.assignment.codeprovided.*;
import uk.ac.sheffield.com1003.assignment.codeprovided.AbstractPlayerCatalog;
import uk.ac.sheffield.com1003.assignment.codeprovided.gui.AbstractPlayerDashboardPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
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
        //TODO remove code below and implement, the comboboxes should be dynamically updated based
        // on user actions
        //THE LEAGUE COMBOBOX



        //THE NAME COMBOBOX
        //playerNamesList.addAll();
        DefaultComboBoxModel<String> namesModel = (DefaultComboBoxModel<String>) comboPlayerNames.getModel();
        playerNamesList.add(0,"");
        namesModel.addAll(playerNamesList);

        //THE NATION COMBOBOX
        DefaultComboBoxModel<String> nationModel = (DefaultComboBoxModel<String>) comboNations.getModel();
        nationList.add(0,"");
        nationModel.addAll(nationList);

        //THE POSITION COMBOBOX
        DefaultComboBoxModel<String> positionModel = (DefaultComboBoxModel<String>) comboPositions.getModel();
        positionList.add(0,"");
        positionModel.addAll(positionList);

        //THE TEAM COMBOBOX
        DefaultComboBoxModel<String> teamModel = (DefaultComboBoxModel<String>) comboTeams.getModel();
        teamList.add(0,"");
        teamModel.addAll(teamList);
    }

    /**
     * addListeners method - adds relevant actionListeners to the GUI components
     */
    @SuppressWarnings("unused")
    @Override
    public void addListeners() {
        //create an instance of the class to recieve the user actions data
        // TODO implement
        //THE LEAGUE COMBOBOX (event) when a user selects a league 
        comboLeagueTypes.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // Code to be executed when a user selects an item in the combo box
                    String selectedLeague = (String) comboLeagueTypes.getSelectedItem();
                    League myLeague = League.fromName(selectedLeague);
                    
                    //pass the selected list to the data class.
                }              
        });

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
