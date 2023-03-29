package uk.ac.sheffield.com1003.assignment.codeprovided;

import java.util.*;

/**
 * Class designed to be used to create Query objects from the Query List.
 *
 * @version 1.1  09/02/2023
 *
 * @author Maria-Cruz Villa-Uriol (m.villa-uriol@sheffield.ac.uk)
 * @author Ben Clegg
 *
 * Copyright (c) University of Sheffield 2023
 */
public class Query {

    final List<SubQuery> subQueryList;
    final League leagueType;

    /**
     * Constructor.
     *
     * @param subQueryList
     * @param leagueType
     */
    public Query(List<SubQuery> subQueryList, League leagueType) {
        this.subQueryList = subQueryList;
        this.leagueType = leagueType;
    }

    /**
     * Get the league type.
     *
     * @return league type.
     */
    public League getLeagueType() {
        return leagueType;
    }

    /**
     * Get SubQueries of this Query.
     *
     * @return subQueryList
     */
    public List<SubQuery> getSubQueryList() {
        return subQueryList;
    }


    /**
     * Apply the Query to the players of a PlayerCatalog, retrieve the players which match.
     *
     * @param playerCatalog the PlayerCatalog to query
     * @return List of filtered player entries
     */
    public List<PlayerEntry> executeQuery(AbstractPlayerCatalog playerCatalog) {
        // Start by adding all the player entries with the matching type
        List<PlayerEntry> filteredPlayerEntriesList =
                new ArrayList<>(playerCatalog.getPlayerEntriesList(leagueType));

        // Continuously filter the player entries according to each SubQuery
        for (SubQuery subQuery : subQueryList) {
            filteredPlayerEntriesList =
                    executeSubQuery(filteredPlayerEntriesList, subQuery);

        }
        // Return the filtered player entries
        return filteredPlayerEntriesList;
    }

    /**
     * Filter provided player entries according to a SubQuery.
     *
     * @param playerEntries the Collection of relevant player entries
     * @param subQuery the SubQuery to use to filter player entries
     * @return List of all player entries which meet criteria
     */
    private List<PlayerEntry> executeSubQuery(Collection<PlayerEntry> playerEntries, SubQuery subQuery) {
        List<PlayerEntry> filteredPlayerEntriesList = new ArrayList<>();

        for (PlayerEntry w : playerEntries) {
            if(subQuery.playerEntriesMatchesSubQuery(w))
                filteredPlayerEntriesList.add(w);
        }
        return filteredPlayerEntriesList;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((subQueryList == null) ? 0 : subQueryList.hashCode());
		result = prime * result + ((leagueType == null) ? 0 : leagueType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Query other = (Query) obj;
		if (subQueryList == null) {
			if (other.subQueryList != null)
				return false;
		} else if (!subQueryList.equals(other.subQueryList))
			return false;
        return leagueType == other.leagueType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Query: ").append(leagueType).append("{");
        Iterator<SubQuery> subQueryIterator = subQueryList.iterator();
        while (subQueryIterator.hasNext()) {
            sb.append(subQueryIterator.next());
            if(subQueryIterator.hasNext())
                sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }
}