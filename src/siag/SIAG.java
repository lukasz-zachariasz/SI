/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siag;

import java.io.File;

/**
 *
 * @author Reniferek
 */
public class SIAG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello!");
        Graph test = new Graph(new File("test.col"));
        System.out.println(test.getNodesNumber());
    }
    
}
