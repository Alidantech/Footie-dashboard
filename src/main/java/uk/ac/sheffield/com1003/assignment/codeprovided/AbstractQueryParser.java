package uk.ac.sheffield.com1003.assignment.codeprovided;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Abstract class to parse Queries.
 * Provides functionality to read tokens from a query file
 *
 * @version 1.0 02/03/2023
 *
 * @author Ben Clegg
 *
 * Copyright (c) University of Sheffield 2023
 */
@SuppressWarnings("unused")
public abstract class AbstractQueryParser
{
    /**
     * Reads the queries file and splits each token in the queryFile into individual strings,
     * (i.e. splits by spaces) and stores them in a List for further processing by the readQueries method.
     *
     * @param queryFile The text file containing relevant queries
     * @return List of tokenized Strings
     */
    public static List<String> readQueryFile(String queryFile) {

        List<String> queryTokens = new ArrayList<>();
        String split = " ";

        try (BufferedReader br = new BufferedReader(new FileReader(queryFile))) {
            String line = br.readLine();
            if (line == null) {
                throw new IllegalArgumentException("File is empty");
            }
            while (line != null) {
                String[] query = line.toLowerCase().split(split);
                queryTokens.addAll(Arrays.asList(query));
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            System.err.println(queryFile + " could not be found");
        } catch (IOException e) {
            System.err.println("File could not be handled");
            // enable the next line for debugging purposes
            // e.printStackTrace();
        }
        return queryTokens;
    }

    /**
     * 1 - receives the List of Strings, each is a single token
     * 2 - assesses their content, creates the relevant Query & SubQuery objects
     * 3 - and then returns a List of the Query objects
     *
     * @param queryTokens The List of tokenized Strings from the readQueryFile method
     * @return List of all Query objects
     * @throws IllegalArgumentException if the provided query tokens are invalid (e.g. non-numbers
     * as boundary values, invalid operators, etc)
     */
    public abstract List<Query> readQueries(List<String> queryTokens) throws IllegalArgumentException;
}
