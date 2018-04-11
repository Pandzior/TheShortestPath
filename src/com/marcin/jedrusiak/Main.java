package com.marcin.jedrusiak;

import com.marcin.jedrusiak.model.Neighbor;
import com.marcin.jedrusiak.utils.DijkstraAlgorithm;
import com.marcin.jedrusiak.utils.FastReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        FastReader reader = new FastReader();

        int tests = reader.nextInt();

        StringBuilder resultsCosts = new StringBuilder();
        Map<String, Integer> nodes = new HashMap<>();
        Map<String, Map<String, Integer>> resultsForTest = new HashMap<>();
        List<Neighbor>[] neighborsList;

        for (int test = 0; test < tests; test++) {
            int cities = reader.nextInt();

            nodes.clear();
            resultsForTest.clear();

            neighborsList = new ArrayList[cities];

            for (int city = 0; city < cities; city++) {
                String cityName = reader.next();

                nodes.put(cityName, city);

                int neighbors = reader.nextInt();

                neighborsList[city] = new ArrayList<>(neighbors);

                for (int route = 0; route < neighbors; route++) {
                    int destId = reader.nextInt();
                    int weight = reader.nextInt();

                    neighborsList[city].add(new Neighbor(destId - 1, weight));
                }
            }

            int results = reader.nextInt();

            for (int resultPair = 0; resultPair < results; resultPair++) {
                String line = reader.nextLine();
                String[] parsed = line.split(" ");

                resultsForTest.putIfAbsent(parsed[0], new HashMap<>());
                resultsForTest.get(parsed[0]).putIfAbsent(parsed[1], null);

                int sourceIndex = nodes.get(parsed[0]);
                int destinationIndex = nodes.get(parsed[1]);

                Integer cost = resultsForTest.get(parsed[0]).get(parsed[1]);

                if (cost == null) {
                    cost = DijkstraAlgorithm.calculateShortestPath(sourceIndex, neighborsList, destinationIndex);
                    resultsForTest.get(parsed[0]).put(parsed[1], cost);
                }

                if (cost == -1) {
                    resultsCosts.append("0\n");
                } else {
                    resultsCosts.append(cost + "\n");
                }
            }

            reader.nextLine();
        }

        System.out.print(resultsCosts.toString());
    }
}