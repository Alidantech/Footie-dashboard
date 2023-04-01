package uk.ac.sheffield.com1003.assignment.gui;
import uk.ac.sheffield.com1003.assignment.codeprovided.*;
import uk.ac.sheffield.com1003.assignment.codeprovided.gui.AbstractPlayerDashboardPanel;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
    //instace variables
    
    List<SubQuery> subQueryList = new ArrayList<>();
    String selectedLeague = (String) comboLeagueTypes.getSelectedItem();
    String selectedName = (String) comboPlayerNames.getSelectedItem();
    String selectedNation = (String) comboNations.getSelectedItem();
    String selectedPosition = (String) comboPositions.getSelectedItem();
    String SelectedTeam = (String) comboTeams.getSelectedItem();
    League myLeague = League.fromName(selectedLeague);
    List<PlayerEntry> playerEntries = playerCatalog.getPlayerEntriesList(myLeague);
    List<PlayerEntry> datalist = playerCatalog.getPlayerEntriesList(myLeague);

    @Override
    public void populatePlayerDetailsComboBoxes() {
        myLeague = League.ALL;
        datalist = playerCatalog.getPlayerEntriesList(League.ALL);
       // DONE remove code below and implement, the comboboxes should be dynamically updated based
        getPlayerDetails(League.ALL);
        updatePlayerCatalogDetailsBox();
        updateStatistics();
        playerNamesList.add(0,"");
        DefaultComboBoxModel<String> namesModel = (DefaultComboBoxModel<String>) comboPlayerNames.getModel();
        namesModel.addAll(playerNamesList);
       
        DefaultComboBoxModel<String> nationModel = (DefaultComboBoxModel<String>) comboNations.getModel();
        nationList.add(0,"");
        nationModel.addAll(nationList);

        DefaultComboBoxModel<String> positionModel = (DefaultComboBoxModel<String>) comboPositions.getModel();
        positionList.add(0,"");
        positionModel.addAll(positionList);

        DefaultComboBoxModel<String> teamModel = (DefaultComboBoxModel<String>) comboTeams.getModel();
        teamList.add(0,"");
        teamModel.addAll(teamList);
       
    }

    /**
     * addListeners method - adds relevant actionListeners to the GUI components
     */
    @SuppressWarnings("")
    @Override
    public void addListeners() {

        comboLeagueTypes.addActionListener(e -> {
            // Code to be executed when a user selects an item in the combo box
            if(selectedLeague != null){
               
                String selectedLeague = (String) comboLeagueTypes.getSelectedItem();
                myLeague = League.fromName(selectedLeague);
                
                datalist = playerCatalog.getPlayerEntriesList(myLeague);
                getPlayerDetails(myLeague);
        
                updatePlayerCatalogDetailsBox();
                updateStatistics(); 
            }
        });
     
        //add event listeners for the buttons

        buttonAddFilter.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            addFilter();
            buttonAddFilter.setBackground(Color.GREEN);
            buttonClearFilters.setBackground(Color.GRAY);

        }              
        });


        buttonClearFilters.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            buttonAddFilter.setBackground(Color.gray);
            buttonClearFilters.setBackground(Color.RED);

            
        }              
         });
    }
    /**
     * clearFilters method - clears all filters from the subQueryList ArrayList and updates
     * the relevant GUI components
     */
    @Override
    public void clearFilters() {
        
        subQueryList.clear();

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

        // Print the property headers in the first column
        statisticsTextArea.append(String.format("%-55s %-55s %-55s %-55s\n", "Property","Maximum", "Average", "Minimum"));
        // Print the property headers in the first row
        for (PlayerProperty property : PlayerProperty.values()) {
            statisticsTextArea.append(String.format("%-55s", property.getName()));
            double maxValue = playerCatalog.getMaximumValue(property, datalist);
            double avgValue = playerCatalog.getMeanAverageValue(property, datalist);
            double minValue = playerCatalog.getMinimumValue(property, datalist);
            statisticsTextArea.append(String.format("%-55s %-55s %-55s\n", String.format("%.2f", maxValue),
                    String.format("%.2f", avgValue), String.format("%.2f", minValue)));
        }
            
    }

    /**
     * updatePlayerCatalogDetailsBox method - updates the list of players when changes are made
     */
    @Override
    public void updatePlayerCatalogDetailsBox() {

        filteredPlayerEntriesTextArea.append(String.format("%-16s %-26s %-34s %-38s %-32s %-34s",
                "ID", "League Type", "Player's Name", "Nation", "Position", "Team"));
        for (PlayerProperty property : PlayerProperty.values()) {
            filteredPlayerEntriesTextArea.append(String.format("%-48s", property.getName()));
        }
        filteredPlayerEntriesTextArea.append("\n");

        // Print the data rows
        for (PlayerEntry playerEntry : datalist) {
            filteredPlayerEntriesTextArea.append(String.format("%-16s %-26s %-34s %-38s %-32s %-34s",
                    playerEntry.getId(), playerEntry.getLeagueType().name(), playerEntry.getPlayerName(),
                    playerEntry.getNation(), playerEntry.getPosition(), playerEntry.getTeam()));

            for (PlayerProperty property : PlayerProperty.values()) {
                double entryValue = playerEntry.getProperty(property);

                filteredPlayerEntriesTextArea.append(String.format("%-74s", String.format("%.2f", entryValue)));
            }
            filteredPlayerEntriesTextArea.append("\n");
        }

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
        //QueryParser queryParser = new QueryParser();
        System.out.println(myLeague);
        // String 
        String selectedProperty = (String) comboQueryProperties.getSelectedItem();
        String operator = (String) comboOperators.getSelectedItem();
        double Value = Double.parseDouble(value.getText());
        PlayerProperty property = PlayerProperty.fromPropertyName(selectedProperty);

        // Create a new SubQuery object for the selected property, operator, and value
        SubQuery subQuery = new SubQuery(property, operator, Value);
        
        // Add the SubQuery object to the list
        subQueryList.add(subQuery);
        System.out.println(subQueryList);
        String updatedText =  subQueryList + ",   ";
        subQueriesTextArea.append(updatedText);
        new  Query(subQueryList, myLeague);
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


    /*
     * This method gets all the player details and passes them to
     * the comboboxes
     */
    public List<PlayerEntry> getPlayerDetails(League league){
        statisticsTextArea.setText("");
        filteredPlayerEntriesTextArea.setText("");
        //clear all the lists then update them
        List<String> namesList = new ArrayList<String>();
        namesList.clear();
        List<String> nationsList = new ArrayList<String>();
        namesList.clear();
        List<String> positionsList = new ArrayList<String>();
        namesList.clear();
        List<String> teamsList = new ArrayList<String>();
        namesList.clear();

        for (PlayerEntry playerEntry : datalist) {

            String playerName = playerEntry.getPlayerName();
            namesList.add(playerName);


            String playerNation = playerEntry.getNation();
           
            //select only unique nations names
            if (!nationsList.contains(playerNation)) {
                nationsList.add(playerNation);
            }

            String playerPosition = playerEntry.getPosition();

            //select only unique positions names
            if (!positionsList.contains(playerPosition)) {
                positionsList.add(playerPosition);
            }
           
            
            String playerTeam = playerEntry.getTeam();
            
            //select only unique team names
            if (!teamsList.contains(playerTeam)) {
                teamsList.add(playerTeam);
            }
           
        }
        updateNamesComboBox(namesList);
        updateNationsComboBox(nationsList);
        updatePositionsComboBox(positionsList);
        updateTeamsComboBox(teamsList);
        return datalist;
        
    }


    private void updateNamesComboBox(List<String> newList) {
        newList.add(0,"");
        DefaultComboBoxModel<String> namesModel = (DefaultComboBoxModel<String>) comboPlayerNames.getModel();
        namesModel.removeAllElements();
        namesModel.addAll(newList);
       
    }

    private void updateNationsComboBox(List<String> newList) {
        DefaultComboBoxModel<String> nationModel = (DefaultComboBoxModel<String>) comboNations.getModel();
        newList.add(0,"");

        nationModel.removeAllElements();
        nationModel.addAll(newList);
       
    }
        
    private void updatePositionsComboBox(List<String> newList) {
    
        DefaultComboBoxModel<String> positionModel = (DefaultComboBoxModel<String>) comboPositions.getModel();
        newList.add(0,"");

        positionModel.removeAllElements();
        positionModel.addAll(newList);
    
    }

       
    private void updateTeamsComboBox(List<String> newList) {
        DefaultComboBoxModel<String> teamModel = (DefaultComboBoxModel<String>) comboTeams.getModel();
        newList.add(0,"");

        teamModel.removeAllElements();
        teamModel.addAll(newList);
    }

}
