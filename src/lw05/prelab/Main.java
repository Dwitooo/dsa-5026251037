package lw05.prelab;

import java.util.*;

public class Main {
    static int size;
    static String[] names;
    static int[][] graph;
    static List<Integer> dfsOrder = new ArrayList<>();

    static void addEdge(int v1, int v2, int weight) {
        graph[v1][v2] = weight;
        graph[v2][v1] = weight;
    }

    static void printPath(List<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(names[path.get(i)]);
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    static List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(current);

            for (int i = 0; i < size; i++) {
                if (graph[current][i] != 0 && !visited.contains(i)) {
                    visited.add(i);
                    queue.add(i);
                }
            }
        }
        return order;
    }

    static void dfs(int current, Set<Integer> visited) {
        visited.add(current);
        dfsOrder.add(current);

        for (int i = 0; i < size; i++) {
            if (graph[current][i] != 0 && !visited.contains(i)) {
                dfs(i, visited);
            }
        }
    }

    static int findTheNextNode(int[] dist, boolean[] visited) {
        int minDist = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < size; i++) {
            if (!visited[i] && dist[i] < minDist) {
                minDist = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    static void dijkstraAlg(int start, int destination) {
        int[] dist = new int[size];
        int[] prev = new int[size];
        boolean[] visited = new boolean[size];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[start] = 0;

        for (int i = 0; i < size; i++) {
            int u = findTheNextNode(dist, visited);
            if (u == -1) break;
            visited[u] = true;

            for (int v = 0; v < size; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    prev[v] = u;
                }
            }
        }

        System.out.println("=== Shortest Time from " + names[start] + " ===");
        for (int i = 0; i < size; i++) {
            System.out.println(names[i] + ": " + dist[i] + " min");
        }

        System.out.println("=== Shortest Route from " + names[start] + " to " + names[destination] + " ===");
        route(prev, destination);
        System.out.println();
        System.out.println("Total time: " + dist[destination] + " min");
    }

    static void route(int[] prev, int current) {
        if (prev[current] == -1) {
            System.out.print(names[current]);
            return;
        }
        route(prev, prev[current]);
        System.out.print(" -> " + names[current]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("campus.txt"));

        size = sc.nextInt();
        names = new String[size];
        for (int i = 0; i < size; i++) {
            names[i] = sc.next();
        }

        graph = new int[size][size];
        int edges = sc.nextInt();
        for (int i = 0; i < edges; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int minutes = sc.nextInt();
            addEdge(v1, v2, minutes);
        }

        int start = sc.nextInt();
        int destination = sc.nextInt();

        System.out.println("=== BFS from " + names[start] + " ===");
        printPath(bfs(start));

        System.out.println("=== DFS from " + names[start] + " ===");
        dfsOrder.clear();
        dfs(start, new HashSet<>());
        printPath(dfsOrder);

        dijkstraAlg(start, destination);
        sc.close();
    }
}