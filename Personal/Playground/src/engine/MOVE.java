package engine;

public enum MOVE{
    LEFT(-1), 
    RIGHT(1);
    
    private int pos;
    MOVE(int pos){this.pos = pos;}
    public int getPosition(){return pos;}
    public void updatePosition(int position){pos = position;}
}
