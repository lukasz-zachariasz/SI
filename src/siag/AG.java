/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siag;

import java.util.ArrayList;
import static java.util.Collections.sort;
import java.util.Random;

/**
 *
 * @author Reniferek
 */
public class AG {

    private int populationNumber;
    private int chanceCrossing;
    private int chanceMutation;
    private Graph schema;
    Random random;
    //private ArrayList<int[]> population;
    private ArrayList<Subject> population;
    private ArrayList<Subject> newPopulation;
    private int[] ratings;

    AG(int pop, int cross, int mut, Graph problem) {
        this.populationNumber = pop;
        this.chanceCrossing = cross;
        this.chanceMutation = mut;
        this.schema = schema;
        //population = new ArrayList<int[]>();
        population = new ArrayList<Subject>();

        random = new Random();
    }

    public void populate() {
        int temp[] = new int[schema.getNodesNumber()];
        for (int i = 0; i < populationNumber; i++) {
            for (int j = 0; j < schema.getNodesNumber(); j++) {
                temp[j] = random.nextInt(schema.getNodesNumber() - 1) + 1;
            }
            population.add(new Subject(temp));
        }
    }

    public void evaluate(Subject one) {
        Graph test = new Graph(schema, one.getColors());
        ArrayList<Node> op = test.getNodes();
        int rate = 0;
        boolean x = true;
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int i = 0; i < test.getNodesNumber(); i++) {
            rate += op.get(i).rate();
            for (int j = 0; j < colors.size(); j++) {
                x = true;
                if (op.get(i).getColor() == colors.get(j)) {
                    x = false;
                }
            }
            if (x) {
                colors.add(op.get(i).getColor());
            }
        }
        one.setRating(rate * colors.size());
    }

    public void crossing() {

        ratings = new int[this.populationNumber];
        newPopulation = new ArrayList<Subject>();
        int[] childOne = new int[schema.getNodesNumber()];
        int[] childTwo = new int[schema.getNodesNumber()];
        Subject parentOne;
        Subject parentTwo;
        int ratingsSummary = 0;
        double result;
        int point;
        for (int i = 0; i < this.populationNumber; i++) {
            evaluate(population.get(i));
        }
        sort(population);
        population.subList(population.size() / 2, population.size()); //population.size should be equal to population number
        for (int i = 0; i < population.size(); i++) {
            ratingsSummary += population.get(i).getRating();
        }
        double range = 1.0 / ratingsSummary;
        for (int i = 0; i < population.size(); i++) {
            parentOne = population.get(i);
            result = random.nextDouble() * range;
            for (int j = 0; j < population.size(); j++) {
                result -= population.get(j).getChance();
                if (result >= 0.0) {
                    parentTwo = population.get(j);
                    if (random.nextInt(100) < this.chanceCrossing) {
                        point = random.nextInt(schema.getNodesNumber() - 1);

                        for (int k = 0; k < schema.getNodesNumber(); k++) {
                            if (k < point) {
                                childOne[k] = parentOne.getColors()[k];
                                childTwo[k] = parentTwo.getColors()[k];
                            } else {
                                childOne[k] = parentTwo.getColors()[k];
                                childTwo[k] = parentOne.getColors()[k];
                            }
                        }
                    } else {
                        childOne = parentOne.getColors();
                        childTwo = parentTwo.getColors();
                    } 
                }
                
                
            }
        }
    }
}
