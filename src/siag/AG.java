/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siag;

import java.util.ArrayList;
import static java.util.Collections.sort;
import java.util.HashSet;
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
    private Random random;
    private ArrayList<Subject> population;
    private ArrayList<Subject> newPopulation;
    private int[] ratings;

    AG(int pop, int cross, int mut, Graph problem) {
        this.populationNumber = pop;
        this.chanceCrossing = cross;
        this.chanceMutation = mut;
        this.schema = problem;
        //population = new ArrayList<int[]>();
        population = new ArrayList<Subject>();

        random = new Random();
    }

    /**
     * @return the populationNumber
     */
    public int getPopulationNumber() {
        return populationNumber;
    }

    /**
     * @param populationNumber the populationNumber to set
     */
    public void setPopulationNumber(int populationNumber) {
        this.populationNumber = populationNumber;
    }

    /**
     * @return the chanceCrossing
     */
    public int getChanceCrossing() {
        return chanceCrossing;
    }

    /**
     * @param chanceCrossing the chanceCrossing to set
     */
    public void setChanceCrossing(int chanceCrossing) {
        this.chanceCrossing = chanceCrossing;
    }

    /**
     * @return the chanceMutation
     */
    public int getChanceMutation() {
        return chanceMutation;
    }

    /**
     * @param chanceMutation the chanceMutation to set
     */
    public void setChanceMutation(int chanceMutation) {
        this.chanceMutation = chanceMutation;
    }

    /**
     * @return the schema
     */
    public Graph getSchema() {
        return schema;
    }

    /**
     * @param schema the schema to set
     */
    public void setSchema(Graph schema) {
        this.schema = schema;
    }

    /**
     * @return the random
     */
    public Random getRandom() {
        return random;
    }

    /**
     * @param random the random to set
     */
    public void setRandom(Random random) {
        this.random = random;
    }

    /**
     * @return the population
     */
    public ArrayList<Subject> getPopulation() {
        return population;
    }

    /**
     * @param population the population to set
     */
    public void setPopulation(ArrayList<Subject> population) {
        this.population = population;
    }

    /**
     * @return the newPopulation
     */
    public ArrayList<Subject> getNewPopulation() {
        return newPopulation;
    }

    /**
     * @param newPopulation the newPopulation to set
     */
    public void setNewPopulation(ArrayList<Subject> newPopulation) {
        this.newPopulation = newPopulation;
    }

    /**
     * @return the ratings
     */
    public int[] getRatings() {
        return ratings;
    }

    /**
     * @param ratings the ratings to set
     */
    public void setRatings(int[] ratings) {
        this.ratings = ratings;
    }

    public void populate() {
        int temp[] = new int[schema.getNodesNumber()];
        for (int i = 0; i < getPopulationNumber(); i++) {
            for (int j = 0; j < schema.getNodesNumber(); j++) {
                temp[j] = random.nextInt(schema.getNodesNumber());
            }
            population.add(new Subject(temp));
        }
    }

    public int evaluate(Subject one) {
        Graph test = new Graph(getSchema(), one.getColors());
        ArrayList<Node> op = test.getNodes();
        int rate = 0;
        HashSet<Integer> colors = new HashSet<Integer>();
        for (int i = 0; i < test.getNodesNumber(); i++) {
            rate += op.get(i).rate();
            colors.add(op.get(i).getColor());
            }
            one.setRating((1 + rate) * colors.size());
            one.setColorsNumber(colors.size());
            return one.getRating();
        }

    

    public void crossing() {

        newPopulation = new ArrayList<Subject>();
        int[] childOne = new int[getSchema().getNodesNumber()];
        int[] childTwo = new int[getSchema().getNodesNumber()];
        Subject parentOne;
        Subject parentTwo;
        int ratingsSummary = 0;
        double result;
        int point;
        for (int i = 0; i < this.getPopulationNumber(); i++) {
            evaluate(getPopulation().get(i));
        }
        sort(getPopulation());
        getPopulation().subList(getPopulation().size() / 2, getPopulation().size()).clear(); //population.size should be equal to population number
        for (int i = 0; i < getPopulation().size(); i++) {
            evaluate(getPopulation().get(i));
            ratingsSummary += getPopulation().get(i).getRating();
        }
        double range = 1.0 / ratingsSummary;
        for (int i = 0; i < getPopulation().size(); i++) {
            parentOne = getPopulation().get(i);
            result = getRandom().nextDouble() * range;
            for (int j = 0; j < getPopulation().size(); j++) {
                result -= getPopulation().get(j).getChance();
                if (result <= 0.0) {
                    parentTwo = getPopulation().get(j);
                    if (parentTwo == parentOne) {
                        getPopulation().get(j + 1);
                    }
                    if (getRandom().nextInt(100) < this.getChanceCrossing()) {
                        point = getRandom().nextInt(getSchema().getNodesNumber());

                        for (int k = 0; k < getSchema().getNodesNumber(); k++) {
                            if (k < point) {
                                childOne[k] = parentOne.getColors()[k];
                                childTwo[k] = parentTwo.getColors()[k];
                            } else {
                                childOne[k] = parentTwo.getColors()[k];
                                childTwo[k] = parentOne.getColors()[k];
                            }
                        }
                    } else {
                        for (int k = 0; k < getSchema().getNodesNumber(); k++) {
                            childOne[k] = parentOne.getColors()[k];
                            childTwo[k] = parentTwo.getColors()[k];
                        }
                    }

                }
                newPopulation.add(new Subject(childOne));
                newPopulation.add(new Subject(childTwo));
                break;
            }
        }
        population = newPopulation;
    }

    public void mutate() {
        for (int i = 0; i < this.getPopulationNumber(); i++) {

            for (int j = 0; j < schema.getNodesNumber(); j++) {
                if (random.nextInt(10000) < this.chanceMutation) {
                    int[] temp = population.get(i).getColors();
                    temp[j] = random.nextInt(schema.getNodesNumber());
                    population.get(i).setColors(temp);
                }
            }
        }
    }

}
