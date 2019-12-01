package sample;

public class Player {
    private int numOfLeft;
    private String color;
    public Player(int x, String c){
        this.color = c;
        this.numOfLeft = x;
    }
    public int getNumOfLeft(){
        return numOfLeft;
    }
    public String getColor(){
        return color;
    }
}
