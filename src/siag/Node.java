/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siag;

import java.util.ArrayList;

/**
 *
 * @author Reniferek
 */
public class Node {

    private int name;
    private ArrayList<Node> relations;
    private int color;
    private int rating;

    Node(int i) {
        color=0;
        rating=0;
        relations=new ArrayList<Node>();
        this.name = i;
    }
    Node(Node n){
        color=n.getColor();
        rating=n.getRating();
        relations=n.getRelations();
        this.name = n.getName();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
    public ArrayList<Node> getRelations(){
        return relations;
    }
    public void addRelation(Node newNode){
        relations.add(newNode);
    }
    
    public int rate(){
        rating=0;
        for(int i=0;i<relations.size();i++){
            if(relations.get(i).getColor()==this.color)
            {
                rating++;
            }
        }
        return rating;
    }
    
    public int getRating(){
        return rating;
    }

}
