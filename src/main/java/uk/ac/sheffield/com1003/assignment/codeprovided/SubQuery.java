package uk.ac.sheffield.com1003.assignment.codeprovided;

import java.util.Arrays;

/**
 * Class designed to create SubQuery objects which make up parts of an individual query.
 *
 * @version 1.1  09/02/2023
 *
 * @author Maria-Cruz Villa-Uriol (m.villa-uriol@sheffield.ac.uk)
 * @author Ben Clegg
 *
 * Copyright (c) University of Sheffield 2023
 */
public class SubQuery
{
	public static final String[] VALID_OPERATORS = {"<", "<=", "=", ">=", ">", "!="};

	final PlayerProperty playerProperty;
	final String operator;
	final double value;

	/**
	 * Constructor for subquery.
	 *
	 * @param playerProperty the property involved in the query.
	 * @param operator the operator involved in the query.
	 * @param value the value involved in the query.
	 */
	public SubQuery(PlayerProperty playerProperty, String operator, double value) {
		this.playerProperty = playerProperty;
		this.operator = operator;
		this.value = value;
	}

	public PlayerProperty getPlayerProperty()
	{
		return playerProperty;
	}

	public String getOperator() {
		return operator;
	}

	public double getValue() {
		return value;
	}

	public String toString() {
		return this.getPlayerProperty() + " " +
				this.getOperator() + " " +
				this.getValue();
	}

	/**
	 * Check if a PlayerEntry satisfies the SubQuery.
	 *
	 * @param playerEntry the PlayerEntry to check
	 * @return true if the player entry matches the SubQuery; false otherwise
	 */
	protected boolean playerEntriesMatchesSubQuery(PlayerEntry playerEntry) {
		PlayerProperty playerProperty = getPlayerProperty();
		double propertyValue = getValue();

		switch (getOperator()) {

			case ">":
				if (playerEntry.getProperty(playerProperty) > propertyValue)
					return true;
				break;
			case ">=":
				if (playerEntry.getProperty(playerProperty) >= propertyValue)
					return true;
				break;
			case "<":
				if (playerEntry.getProperty(playerProperty) < propertyValue)
					return true;
				break;
			case "<=":
				if (playerEntry.getProperty(playerProperty) <= propertyValue)
					return true;
				break;
			case "=":
				if (playerEntry.getProperty(playerProperty) == propertyValue)
					return true;
				break;
			case "!=":
				if (playerEntry.getProperty(playerProperty) != propertyValue)
					return true;
				break;
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((playerProperty == null) ? 0 : playerProperty.hashCode());
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
		SubQuery other = (SubQuery) obj;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
			return false;
		return playerProperty == other.playerProperty;
	}

	/**
	 * Check if a String is a valid operator.
	 *
	 * @param operatorToCheck the String to check
	 * @return true if the String is a valid operator; false otherwise
	 */
	public static boolean isValidOperator(String operatorToCheck) {
		return Arrays.asList(VALID_OPERATORS).contains(operatorToCheck);
	}

    public String[] split(String string) {
        return null;
    }
}
