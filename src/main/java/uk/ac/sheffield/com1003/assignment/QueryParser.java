package uk.ac.sheffield.com1003.assignment;

import uk.ac.sheffield.com1003.assignment.codeprovided.*;
import java.util.*;

/**
 * SKELETON IMPLEMENTATION
 */
public class QueryParser extends AbstractQueryParser
{

    @Override
    public List<Query> readQueries(List<String> queryTokens) throws IllegalArgumentException {
    
        List<Query> queryList = new ArrayList<>();
        for(String keyword:queryTokens){
            
        }
        // if(queryTokens != null){
        //     int i = 0;
        //     while (i < queryTokens.size()) {
        //         String league = queryTokens.get(i++).toUpperCase();
        //         League leagueType = League.fromName(league);
    
        //         List<SubQuery> subQueryList = new ArrayList<>();
    
        //         while (i < queryTokens.size() && !"WHERE".equals(queryTokens.get(i).toUpperCase())) {
        //             String property = queryTokens.get(i++);
        //             String operator = queryTokens.get(i++);
        //             String valueStr = queryTokens.get(i++);
    
        //             // Attempt to parse the value into a double, with error handling
        //             double value = Double.NaN;
        //             try {
        //                 value = Double.parseDouble(valueStr);
        //             } catch (NumberFormatException e) {
        //                 System.err.println("Error parsing value: " + valueStr);
        //                 continue;
        //             }
    
        //             subQueryList.add(new SubQuery(PlayerProperty.valueOf(property.toUpperCase()), operator, value));
        //         }
    /*
     * ["select", "epl", "where", "goals", ">", "2",
     *  "select", "epl", "or", "liga", "where", "goals", ">", "2", "and", "goals", "<", "5", "and", "assists", ">", "0.1",
     *  "select", "epl", "where", "matches", "!=", "2", "and", "matches", "!=", "4", "and", "matches", "!=", "6", "and", "pkattempts", ">", "0",
     *  "select", "epl", "or", "liga", "where", "goals", ">", "5", "and", "goals", "<=", "6", "and", "minutes", ">=", "1245", 
     * "select", "liga", "where", "goals", "=", "12", "and", "owngoals", ">", "0"]
     */
                // skip the "WHERE" token
           // queryList.add(new Query(subQueryList, leagueType));
                // i+  }
        //}
    
    
        return queryList;
    }
    

}

