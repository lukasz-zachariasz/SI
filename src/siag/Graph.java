/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siag;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Reniferek
 */
public class Graph {

    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private int nodesNumber;
    private int edgesNumber;

    Graph(File file) {
        Parser(this, file);
        Relations();
    }
    Graph(Graph graph, int[] colors){
        nodes=new ArrayList<Node>();
        for(int i=0; i<graph.nodesNumber;i++){
            nodes.add(new Node(graph.nodes.get(i),colors[i]));
        }
        this.nodesNumber=graph.nodesNumber;
        this.edgesNumber=graph.edgesNumber;
        
    }

    private void Parser(Graph aThis, File file) {
        aThis.edges = new ArrayList<Edge>();
        aThis.nodes = new ArrayList<Node>();
        try {
            Scanner sc = new Scanner(file);
            String temp;
            String data[];
            while (sc.hasNext()) {
                temp = sc.nextLine();
                if (temp.startsWith("e")) {
                    data = temp.split(" ");
                    aThis.edges.add(new Edge(aThis.nodes.get(Integer.parseInt(data[1])), aThis.nodes.get(Integer.parseInt(data[1]))));
                } else if (temp.startsWith("p edge")) {
                    data = temp.split(" ");
                    aThis.nodesNumber = Integer.parseInt(data[2]);
                    aThis.edgesNumber = Integer.parseInt(data[3]);
                    for (int i = 0; i < nodesNumber; i++) {
                        aThis.nodes.add(new Node(i + 1));
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }

    }
    
        private void Relations() {
        for(int i=0;i<this.edgesNumber;i++){
            this.edges.get(i).getFirst().addRelation(this.edges.get(i).getSecond());
            this.edges.get(i).getSecond().addRelation(this.edges.get(i).getFirst());
        }
    }
    

    int getNodesNumber() {
        return this.nodesNumber;
    }
    public ArrayList<Node> getNodes(){
        return nodes;
    }
}
