package problems.easy.algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class EvenTree{
    private int vertices, edges;
    List<List<Integer>> adjacency = new ArrayList<>();

    public EvenTree(int vertices, int edges, List<String> edgeList){
        this.vertices = vertices;
        this.edges = edges;
        populate(edgeList);
    }

    private void populate(List<String> edges){
        int randomRoot = new Random().nextInt(edges.size());

        String[] nodes= edges.get(randomRoot).split(" ");
        assert(nodes.length == 2);
        int root = Integer.parseInt(nodes[1]), child = Integer.parseInt(nodes[0]);
        List<Integer> list = new ArrayList<>();
        list.add(root);
        list.add(child);

        adjacency.add(list);

        for(int i =0; i < this.edges; i++){
            if(i != randomRoot){
                nodes = edges.get(i).split(" ");
                assert(nodes.length == 2);
                list.clear();
                root = Integer.parseInt(nodes[1]);
                child = Integer.parseInt(nodes[0]);
                Iterator<List<Integer>> it = adjacency.iterator();
                while(it.hasNext()){
                    List<Integer> adjList = it.next();
                    if(adjList.size() > 0 && adjList.get(0) == root){
                        adjList.add(child);
                    }else{
                        list.add(root);
                        list.add(child);
                        adjacency.add(list);
                    }
                }
            }
        }
        System.out.println(adjacency);
    }

    public int decompose(){
        if(!(vertices/2 == 0)){
           return 0;
        }
        return 0;
    }
}
