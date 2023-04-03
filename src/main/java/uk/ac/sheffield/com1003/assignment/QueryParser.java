package uk.ac.sheffield.com1003.assignment;

import uk.ac.sheffield.com1003.assignment.codeprovided.*;
import java.util.*;

public class QueryParser extends AbstractQueryParser {

    @Override
    public List<Query> readQueries(List<String> queryTokens) throws IllegalArgumentException {
        List<Query> queries = new ArrayList<>();
        List<List<String>> QueryList = divideQueries(queryTokens);
        
        for (List<String> query : QueryList) {
            queries.add(readQuery(query));
        }
        return queries;
    }

    // Reads a query and returns a Query object
    public Query readQuery(List<String> queryTokens) throws IllegalArgumentException {
        League league = null;
        List<SubQuery> subQueries = new ArrayList<>();
    
        Iterator<String> iterator = queryTokens.iterator();
        while (iterator.hasNext()) {
            String token = iterator.next();
    
            switch (token) {
                case "select":
                    if (iterator.hasNext()) {
                        String leagueName = iterator.next();
                        league = getLeagueType(leagueName);
                    }
                    break;
                case "or":
                    if (iterator.hasNext()) {
                        iterator.next();
                        league = getLeagueType("all");
                    }
                    break;
                case "where":
                    subQueries = new ArrayList<>();
                    while (iterator.hasNext()) {
                        String property = iterator.next();
                        String operator = iterator.next();
                        double value = Double.parseDouble(iterator.next());
    
                        if (!SubQuery.isValidOperator(operator)) {
                            throw new IllegalArgumentException("Unexpected operator: " + operator);
                        }
    
                        PlayerProperty playerProperty = PlayerProperty.fromName(property);
                        if (playerProperty == null) {
                            throw new IllegalArgumentException("Unexpected property: " + property);
                        }
    
                        SubQuery subQuery = new SubQuery(playerProperty, operator, value);
                        subQueries.add(subQuery);
    
                        if (!iterator.hasNext() || "and".equals(iterator.next())) {
                            continue;
                        }
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected token: " + token);
            }
        }
    
        return new Query(subQueries, league);
    }
    
    // Returns the league type based on the given league string
    public League getLeagueType(String league) {
        League leagueType;
    
        if (league.equals("liga")) {
            leagueType = League.LIGA;
        } else if (league.equals("epl")) {
            leagueType = League.EPL;
        } else {
            leagueType = League.ALL;
        }
    
        return leagueType;
    }
    
    // Divides a list of query tokens into separate lists for each query
    public List<List<String>> divideQueries(List<String> queryTokens) {
        List<List<String>> queries = new ArrayList<>();
        List<String> currentQuery = new ArrayList<>();
    
        for (String token : queryTokens) {
            if (token.equals("select")) {
                if (!currentQuery.isEmpty()) {
                    queries.add(currentQuery);
                }
                currentQuery = new ArrayList<>();
            }
            currentQuery.add(token);
        }
        queries.add(currentQuery);
    
        return queries;
    }
}
