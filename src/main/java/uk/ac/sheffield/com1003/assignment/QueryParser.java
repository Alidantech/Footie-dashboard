package uk.ac.sheffield.com1003.assignment;

import uk.ac.sheffield.com1003.assignment.codeprovided.*;
import uk.ac.sheffield.com1003.assignment.gui.PlayerDashboardPanel;

import java.util.*;

/**
 * SKELETON IMPLEMENTATION
 */
public class QueryParser extends AbstractQueryParser
{

    // Default implementation to be provided
    @Override
    public List<Query> readQueries(List<String> queryTokens) throws IllegalArgumentException {

        List<Query> queryList = new ArrayList<>();
        
        return queryList;
       
    }
    public static String generateQuery(String subQueryList) {
        String [] subQueries = subQueryList.split(" ");
        String queryString = "";
        String condition = "";
        String operator = "";
        String value = "";
        for(int i = 0; i < subQueries.length; i++){
            condition = subQueries[0]+" ";
            operator = subQueries[1]+" ";
            value = subQueries[2]+" ";
            queryString = condition + operator + value;
            if (i < subQueries.length - 1) {
                queryString = queryString + " and ";
            }
        }

        return queryString;
    }
   
    

}

