import java.util.Objects;

public class Ship
{
    private int row;
    private int col;
    private final int length;
    private String direction;

    public static final int UNSET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    public Ship(int length){
        this.length = length;
        row = UNSET;
        col = UNSET;
    }
    public void setLocation(int r, int c){
        row = r;
        col = c;
    }

    public void setDirection(int d){
        if(d == HORIZONTAL){
            direction = "horizontal";
        }
        if(d == VERTICAL){
            direction = "vertical";
        } if (d == UNSET){
            direction = "unset";
        }

    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public int getLength(){
        return length;
    }
    public int getDirection(){
        if (Objects.equals(direction, "horizontal")){
            return HORIZONTAL;
        }
        if(Objects.equals(direction, "vertical")){
            return VERTICAL;
        }
        return UNSET;
    }

    private String directionToString(){
        if(direction == null){
            return "unset direction";
        }
        return direction;
    }

    private String locationToString(){
        if(!this.isLocationSet()){
            return "unset location";
        }
        return  row + ", " + col;
    }
    public String toString(){
        return directionToString() + " ship of length " + length + " at " + "(" + locationToString() + ")";
    }


    public boolean isLocationSet(){
        return row != -1 && col != -1;
    }
    public boolean isDirectionSet(){
        return direction != null;
    }

}
