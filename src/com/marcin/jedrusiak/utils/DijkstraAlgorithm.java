package com.marcin.jedrusiak.utils;

import com.marcin.jedrusiak.model.Neighbor;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
    https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm#Pseudocode
 */
public class DijkstraAlgorithm {

    private DijkstraAlgorithm() {
    }

    public static int calculateShortestPath(int source, List<Neighbor>[] neighborsList, int destination) {
        Queue<Neighbor> priorityQueue = new PriorityQueue<>((neighbor, t1) -> neighbor.getWeight() - t1.getWeight());

        boolean[] visited = new boolean[neighborsList.length];

        int[] distances = new int[neighborsList.length];
        Arrays.fill(distances, Integer.MAX_VALUE);

        int cost = -1;

        priorityQueue.add(new Neighbor(source, 0));

        distances[source] = 0;

        while (!priorityQueue.isEmpty()) {
            Neighbor current = priorityQueue.poll();

            if (visited[current.getIndex()]) {
                continue;
            }

            if (current.getIndex() == destination) {
                cost = distances[destination];
                break;
            }

            for (int i = 0; i < neighborsList[current.getIndex()].size(); i++) {
                Neighbor neighbor = neighborsList[current.getIndex()].get(i);

                int distance = neighbor.getWeight() + current.getWeight();

                if (distance < distances[neighbor.getIndex()]) {
                    distances[neighbor.getIndex()] = distance;

                    priorityQueue.add(new Neighbor(neighbor.getIndex(), distance));
                }
            }

            visited[current.getIndex()] = true;
        }

        return cost;
    }
}
