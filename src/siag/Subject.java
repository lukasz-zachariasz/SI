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
public class Subject implements Comparable {
    private int[] colors;
    private int rating;
    private double chance;
    
    Subject(int[] colors){
        //this.colors=colors;
        this.colors=new int[colors.length];
        for(int i =0;i<colors.length;i++){
            this.colors[i]=colors[i];
        }
    }
    
    public int getRating(){
        return rating;
    }
    public void setRating(int rating){
        this.rating=rating;
        this.chance=1.0/rating;
    }
    public double getChance(){
        return chance;
    }
    public int[] getColors(){
        return colors;
    }
    public void setColors(int[] colors){
        this.colors=colors;
    }

    @Override
    public int compareTo(Object o) {
        Subject test = (Subject)o;
        if(rating<test.getRating()) return -1;
        else if(rating==test.getRating()) return 0;
        else return 1;
    }
    
}
