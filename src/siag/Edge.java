/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siag;

/**
 *
 * @author Reniferek
 */
class Edge {
        private Node first;
        private Node second;
    
    Edge(Node first, Node second) {
        this.first=first;
        this.second=second;
    }
    public Node getFirst()
    {
        return first;
    }
    public Node getSecond(){
        return second;
    }
    
}
