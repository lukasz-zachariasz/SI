/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siag;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Reniferek
 */
public class SIAG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Hello!");
        Subject bestOne;
        Graph test = new Graph(new File("test.col"));
        System.out.println(test.getNodesNumber());
        AG algorytm = new AG(500, 70, 100, test);
        algorytm.populate();
        bestOne = algorytm.getPopulation().get(0);
        for (int k = 0; k < 50000; k++) {
            for (int i = 0; i < algorytm.getPopulationNumber(); i++) {
                if (algorytm.evaluate(algorytm.getPopulation().get(i)) < bestOne.getRating()) {

                    bestOne = algorytm.getPopulation().get(i);
                }
            }
            System.out.println(k+"    "+bestOne.getRating() + "   " + bestOne.getColorsNumber());
            algorytm.crossing();
            algorytm.mutate();
            

        }
        System.out.println("Stop!");
    }

}
