package uk.ac.sheffield.com1003.assignment.codeprovided.gui;
// import statements

import uk.ac.sheffield.com1003.assignment.codeprovided.*;
import uk.ac.sheffield.com1003.assignment.gui.RadarChart;
import uk.ac.sheffield.com1003.assignment.gui.RadarChartPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * Class that provides a basic implementation of the main elements in the GUI as discussed in the handout.
 * Please consider the methods that need to be implemented by overriding, including the method described in the
 * note at the end of this class.
 *
 * Should be implemented as uk.ac.sheffield.assignment2021.gui.PlayerDashboardPanel
 *
 * @version 1.1 09/02/2023
 *
 * @author Maria-Cruz Villa-Uriol (m.villa-uriol@sheffield.ac.uk)
 * @author Ben Clegg
 *
 * Copyright (c) University of Sheffield 2023
 */

// constructor
@SuppressWarnings("ALL")
public abstract class AbstractPlayerDashboardPanel extends JPanel {

    // instance variables

    // playerCatalog provides a convenient access to the original datasets - might be of use
    protected final AbstractPlayerCatalog playerCatalog;
    // filteredPlayerEntriesList is the list that needs to be kept up to date with the results of
    // running the list of SubQuery objects in queryConditionList
    protected List<PlayerEntry> filteredPlayerEntriesList;

    // TODO, update explanation
    // subQueryList contains all the conditions as they are added when the button buttonAddFilter is clicked
    // the contents of subQueryList will need to be cleared if the buttonClearFilters is clicked
    protected List<SubQuery> subQueryList = new ArrayList<>();

    // by default all player entries for both leagues are used
    protected League selectedLeagueType = League.ALL;

    // these need to be set up to nothing
    protected String selectedPlayerName = "";
    protected String selectedPlayerNation = "";
    protected String selectedPlayerPosition = "";
    protected String selectedTeam = "";

    // radar chart being shown
    protected AbstractRadarChart radarChart;


    // starting the definition of buttons
    // buttonAddFilter will add a new query condition to queryConditionList when clicked
    // clicking this button implies calling the method addFilter(...)
    protected final JButton buttonAddFilter = new JButton("Add Filter");
    // buttonClearFilters will remove all query conditions in queryConditionList when clicked
    // clicking this button implies calling the method clearFilters(...)
    protected final JButton buttonClearFilters = new JButton("Clear All Filters");

    // defining the combobox used to select the league type to which the filters (queryConditionList or list of SubQuery object) need to be applied
    protected String[] leagueTypes = { League.ALL.getName(), League.EPL.getName(), League.LIGA.getName() };
    protected JComboBox<String> comboLeagueTypes = new JComboBox<>(leagueTypes);

    // defining the comboboxes used to select player names, nation, player's position and team
    // this subselection will be used by the filters (queryConditionList or list of SubQuery object)
    // when they need to be applied
    protected ArrayList<String> playerNamesList = new ArrayList<>();
    protected JComboBox<String> comboPlayerNames = new JComboBox<>();

    protected ArrayList<String> nationList = new ArrayList<>();
    protected JComboBox<String> comboNations = new JComboBox<>();

    protected ArrayList<String> positionList = new ArrayList<>();
    protected JComboBox<String> comboPositions = new JComboBox<>();

    protected ArrayList<String> teamList = new ArrayList<>();
    protected JComboBox<String> comboTeams = new JComboBox<>();

    Vector<String> propertyValues = new Vector<>(Arrays.stream(PlayerProperty.values())
            .map(PlayerProperty::getName).collect(Collectors.toList()));

    Vector<String> categoryValues = new Vector<>(Arrays.stream(Category.values())
            .map(Category::getName).collect(Collectors.toList()));

    protected JComboBox<String> comboQueryProperties = new JComboBox<>(propertyValues);

    // defining the combobox used to select the operator that will be used to build the filter (or SubQuery object) than will be applied
    protected String[] operators = {">", ">=", "<", "<=", "=", "!="};
    protected JComboBox<String> comboOperators = new JComboBox<>(operators);

    // defining the textfield where the value of the SubQuery (or filter)
    protected JTextField value = new JTextField(5);

    // defining all the labels to facilitate the what goes where in the GUI
    protected JLabel leagueTypeSelectorLabel = new JLabel("League:", SwingConstants.LEFT);
    protected JLabel playerSelectorLabel = new JLabel("Player's name:", SwingConstants.LEFT);
    protected JLabel nationSelectorLabel = new JLabel("Nation:", SwingConstants.LEFT);
    protected JLabel positionSelectorLabel = new JLabel("Position:", SwingConstants.LEFT);
    protected JLabel teamSelectorLabel = new JLabel("Team:", SwingConstants.LEFT);

    protected JLabel subQueryLabel = new JLabel("Filter by property:", SwingConstants.LEFT);
    protected JLabel operatorLabel = new JLabel("Operator:", SwingConstants.LEFT);
    protected JLabel operatorValueLabel = new JLabel("Value:", SwingConstants.LEFT);
    protected JLabel subQueryListLabel = new JLabel("List of filters (or subqueries):", SwingConstants.LEFT);
    protected JLabel radarChartCategoryLabel = new JLabel("Radar chart category:", SwingConstants.LEFT);

    // defining all the checkboxes to control what is shown in radar plot
    protected JCheckBox minCheckBox = new JCheckBox("Minimum");
    protected JCheckBox maxCheckBox = new JCheckBox("Maximum");
    protected JCheckBox averageCheckBox = new JCheckBox("Average");


    // defining the three JTextAreas that will need to be updated every time the buttons
    // buttonAddFilter and buttonClearFilters are clicked
    // subQueriesTextArea will show the contents of subQueryList
    protected JTextArea subQueriesTextArea = new JTextArea(1, 50);
    // statisticsTextArea will show basic summary statistics for the filteredPlayerEntriesList
    // (which contains the results after executing the filters or SubQuery in subQueryList)
    protected JTextArea statisticsTextArea = new JTextArea(5, 70);
    // filteredPlayerEntriesTextArea will show the results contained in the filteredPlayerEntriesTextArea object
    protected JTextArea filteredPlayerEntriesTextArea = new JTextArea(30, 70);

    // defining the gui elements that will show the radar chart
    protected JComboBox<String> comboRadarChartCategories = new JComboBox<>(categoryValues);

    // titles for TitleBorders used to name the three main GUI areas
    protected String statisticsTitle = "PLAYER CATALOG STATISTICS";
    protected String playerEntriesTitle = "PLAYER ENTRIES";
    protected String radarChartTitle = "RADAR CHART";


    // Constructor
    public AbstractPlayerDashboardPanel(AbstractPlayerCatalog playerCatalog) {
        Border blackline = BorderFactory.createLineBorder(Color.black);

        // providing access to the playerCatalog so that this class can access its contents if required
        this.playerCatalog = playerCatalog;
        // by default the GUI starts showing all player entries for both leagues
        this.filteredPlayerEntriesList = playerCatalog.getPlayerEntriesList(League.ALL);

        subQueriesTextArea.setName("subQueries");
        comboQueryProperties.setName("playerProperties");
        value.setName("filterValue");
        filteredPlayerEntriesTextArea.setName("filteredPlayerEntries");
        statisticsTextArea.setName("playerCatalogStats");
        comboOperators.setName("operators");
        comboLeagueTypes.setName("leagueTypes");
        buttonAddFilter.setName("addFilter");
        buttonClearFilters.setName("clearFilters");

        // building the GUI using a combination of JPanels and a range of LayoutManagers
        // to get a structured GUI
        this.setLayout(new BorderLayout());

        // Query panel
        JPanel queryPanel = new JPanel();
        queryPanel.setLayout(new GridLayout(4, 1));

        JPanel typeSelectorPanel = new JPanel();
        typeSelectorPanel.setLayout(new FlowLayout());
        typeSelectorPanel.add(leagueTypeSelectorLabel);
        typeSelectorPanel.add(comboLeagueTypes);

        typeSelectorPanel.add(playerSelectorLabel);
        typeSelectorPanel.add(comboPlayerNames);
        typeSelectorPanel.add(nationSelectorLabel);
        typeSelectorPanel.add(comboNations);
        typeSelectorPanel.add(positionSelectorLabel);
        typeSelectorPanel.add(comboPositions);
        typeSelectorPanel.add(teamSelectorLabel);
        typeSelectorPanel.add(comboTeams);

        JPanel filterBuilderPanel = new JPanel();
        filterBuilderPanel.setLayout(new FlowLayout());
        filterBuilderPanel.add(subQueryLabel);
        filterBuilderPanel.add(comboQueryProperties);
        filterBuilderPanel.add(operatorLabel);
        filterBuilderPanel.add(comboOperators);
        filterBuilderPanel.add(operatorValueLabel);
        filterBuilderPanel.add(value);
        filterBuilderPanel.add(buttonAddFilter);
        filterBuilderPanel.add(buttonClearFilters);

        queryPanel.add(typeSelectorPanel);
        queryPanel.add(filterBuilderPanel);
        queryPanel.add(subQueryListLabel);

        JScrollPane jscQueries = new JScrollPane(subQueriesTextArea);
        queryPanel.add(jscQueries);
        queryPanel.setBorder(blackline);

        // Radar chart panel
        JPanel radarChartContainer = new JPanel();
        JPanel controlRadarChartContainer = new JPanel();

        radarChartContainer.setLayout(new BorderLayout());
        controlRadarChartContainer.add(radarChartCategoryLabel);
        controlRadarChartContainer.add(comboRadarChartCategories);
        controlRadarChartContainer.add(minCheckBox);
        controlRadarChartContainer.add(maxCheckBox);
        controlRadarChartContainer.add(averageCheckBox);

        String categoryName = (String) comboRadarChartCategories.getSelectedItem();
        Category cat = Category.getCategoryFromName(categoryName);
        List<PlayerProperty> listProperties = Arrays.asList(cat.getProperties());
        radarChart = new RadarChart(playerCatalog, filteredPlayerEntriesList, listProperties);
        AbstractRadarChartPanel radarChartPanel = new RadarChartPanel(this, radarChart);
        radarChartContainer.add(radarChartPanel, BorderLayout.CENTER);
        radarChartContainer.add(controlRadarChartContainer, BorderLayout.SOUTH);

        TitledBorder tbRadarChart = BorderFactory.createTitledBorder(
                blackline, radarChartTitle);
        tbRadarChart.setTitleJustification(TitledBorder.CENTER);
        radarChartContainer.setBorder(tbRadarChart);


        // Statistics panel
        JPanel statisticsPanel = new JPanel();
        statisticsPanel.setLayout(new BorderLayout());
        JScrollPane statisticsScrollPane = new JScrollPane(statisticsTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        statisticsPanel.add(statisticsScrollPane, BorderLayout.CENTER);
        statisticsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        TitledBorder tbStatistics = BorderFactory.createTitledBorder(
                blackline, statisticsTitle);
        tbStatistics.setTitleJustification(TitledBorder.CENTER);
        statisticsPanel.setBorder(tbStatistics);

        // Filtered player entries panel
        JPanel playerEntriesPanel = new JPanel();
        playerEntriesPanel.setLayout(new BorderLayout());
        JScrollPane playerEntriesScrollPane = new JScrollPane(filteredPlayerEntriesTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        playerEntriesPanel.add(playerEntriesScrollPane, BorderLayout.CENTER);
        playerEntriesPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        TitledBorder tbPlayerEntries = BorderFactory.createTitledBorder(
                blackline, playerEntriesTitle);
        tbPlayerEntries.setTitleJustification(TitledBorder.CENTER);
        playerEntriesPanel.setBorder(tbPlayerEntries);

        JPanel playerCatalogPanel = new JPanel();
        playerCatalogPanel.setLayout(new BorderLayout());
        playerCatalogPanel.add(statisticsPanel, BorderLayout.NORTH);
        playerCatalogPanel.add(playerEntriesPanel, BorderLayout.SOUTH);

        this.add(queryPanel, BorderLayout.NORTH);
        this.add(radarChartContainer, BorderLayout.CENTER);
        this.add(playerCatalogPanel, BorderLayout.EAST);

        // dynamically populate the comboboxes with Player details
        this.populatePlayerDetailsComboBoxes();

        // adding the listeners, you will need to implement this method to register the events generated
        // by the GUI components that will be expecting a change in the results being displayed by the GUI
        this.addListeners();
    }

    /**
     * Updates the radar chart with the currently filtered player entries and selected category
     */
    public abstract void updateRadarChart();


    /**
     * @return the active SubQueries
     */
    public List<SubQuery> getAllSubQueries() {
        return subQueryList;
    }

    /**
     * getFilteredPlayerEntriesList method - getter of filtered player entries list
     * @return List of PlayerEntry objects after running filtering SubQueries
     */
    public List<PlayerEntry> getFilteredPlayerEntriesList() {
        return filteredPlayerEntriesList;
    }

    // list of abstract methods starts

    /**
     * populatePlayerDetailsComboBoxes method - dynamically populates the player detail comboboxes:
     * comboPlayerNames, comboNations, comboPositions, and comboTeams.
     */
    public abstract void populatePlayerDetailsComboBoxes();

    /**
     * addListeners method - adds relevant actionListeners to the GUI components
     * You will need to listen (at least) to the following:
     * - buttonAddFilter
     * - buttonClearFilters
     * - comboLeagueTypes, comboPlayerNames, comboNations, comboPositions, and comboTeams,
     *              if you want the filteredPlayerEntriesTextArea to be updated
     *              to show only the player entries specified by these comboboxes
     * - comboRadarChartCategories, to update the properties that the radar chart should display
     */
    public abstract void addListeners();

    /**
     * addFilter method -
     * 1- this method is called when the JButton buttonAddFilter is clicked
     * 2- adds a new filter (a SubQuery object) to subQueryList ArrayList
     * 3- updates the GUI results accordingly, i.e. updates the three JTextAreas as follows:
     *    3a- subQueriesTextArea will show the new SubQuery
     *    3b- statisticsTextArea will show the updated statistics for the results after applying this filter
     *    3c- filteredPlayerEntriesTextArea will show the contents of filteredPlayerEntriesList (the results after applying this filter)
     *    3d- the radar chart is updated to display the newly filtered player entries (Note: this can alternatively be done
     *    in another method)
     */
    public abstract void addFilter();

    /**
     * clearFilters method - clears all filters from the subQueryList ArrayList and updates
     * the relevant GUI components when the button buttonClearFilters is clicked
     */
    public abstract void clearFilters();

    /**
     * updateStatistics method - updates the statistics to be displayed in the
     * statisticsTextArea when the results being shown in the GUI need to be updated,
     * recalculates the average, minimum and maximum values for each player property.
     */
    public abstract void updateStatistics();

    /**
     * updatePlayerCatalogDetailsBox method - updates the player details panel when changes are made
     */
    public abstract void updatePlayerCatalogDetailsBox();

    /**
     * executeQuery method - executes the complete query to the relevant player list
     */
    public abstract void executeQuery();

    /**
     * isMinCheckBoxSelected method - executes the complete query to the relevant player list
     */
    public abstract boolean isMinCheckBoxSelected();

    /**
     * isMaxCheckBoxSelected method - executes the complete query to the relevant player list
     */
    public abstract boolean isMaxCheckBoxSelected();

    /**
     * isAverageCheckBoxSelected method - checks if average
     */
    public abstract boolean isAverageCheckBoxSelected();


    /*
     * NOTE: You will also need to override JPanel's paintComponent(Graphics g) method, to redraw the GUI
     * Remember that to redraw your panel, you should never call the paintComponent(Graphics g) explicitly,
     * you will be calling instead the repaint() method! (Check lab sheet of week 7).
     * The repaint() method will make sure that the appropriate paintComponent is called.
     */

}

