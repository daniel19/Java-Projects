package problems.easy.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EvenTree{
    private int vertices, edges;
    private List<List<Integer>> adjacency = new ArrayList<>();
    private List<Integer> vistedNodes = new ArrayList<>();
    private Map<Integer, Integer> subtreeMap = new HashMap<>();

    public EvenTree(int vertices, int edges, List<String> edgeList){
        this.vertices = vertices;
        this.edges = edges;
        populate(edgeList);
    }

    private void populate(List<String> edgeList){
        for(int j = 0; j < vertices; j++){
            adjacency.add(new ArrayList<Integer>());
        }

        for(int i =0; i < edges; i++){
            String[] nodes = edgeList.get(i).split(" ");
            List<Integer> listOne = adjacency.get(Integer.parseInt(nodes[0])-1);
            List<Integer> listTwo = adjacency.get(Integer.parseInt(nodes[1])-1);
            listOne.add(Integer.parseInt(nodes[1]));
            listTwo.add(Integer.parseInt(nodes[0]));
        }
    }

    private void dfs(int node){
        vistedNodes.add(node);
        subtreeMap.putIfAbsent(node, 1);

        List<Integer> traversingList = adjacency.get(node);
        for(int i=0; i < traversingList.size(); i++){
            int vertex = traversingList.get(i) - 1;
            if(!vistedNodes.contains(vertex)){
               dfs(vertex);
               int value = subtreeMap.get(vertex) + subtreeMap.get(node);
               subtreeMap.replace(node, value);
            }
        }

    }

    public int decompose(){

        if(!(vertices%2 == 0)){
           return 0;
        }
        dfs(0);
        System.out.println(subtreeMap);
        int sum = 0;
        for(Map.Entry<Integer, Integer> entry : subtreeMap.entrySet()){
            if(entry.getValue() % 2 == 0 && entry.getValue() != vertices){
                sum++;
            }
        }

        return sum;
    }
}
