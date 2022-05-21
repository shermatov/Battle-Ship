public class Player
{
    public static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};

    private final Ship[] myShips=new Ship[SHIP_LENGTHS.length];

    private final Grid[] myGrids=new Grid[2];

    public int numShips=0;

    public Player()
    {
        for(int i=0;i<SHIP_LENGTHS.length;i++)
        {
            myShips[i]=new Ship(SHIP_LENGTHS[i]);
        }

        myGrids[0]=new Grid();
        myGrids[1]=new Grid();
    }

    public void printMyShips()
    {
        myGrids[0].printShips();
    }

    public void printOpponentGuesses()
    {
        myGrids[0].printStatus();
    }

    public void printMyGuesses()
    {
        myGrids[1].printStatus();
    }

    public boolean validShip(int row,int col,int direction)
    {
        return myGrids[0].validShip(row,col,direction,SHIP_LENGTHS[numShips]);
    }

    public void addShip(Ship s)
    {
        myGrids[0].addShip(s);
        numShips++;
    }

    public void chooseShipLocation(int row,int col,int direction)
    {
        if(numShips<5)
        {
            Ship s = myShips[numShips];
            s.setLocation(row,col);
            s.setDirection(direction);
            myGrids[0].addShip(s);
            numShips++;
        }
    }

    public boolean alreadyGuessed(int row, int col)
    {
        return myGrids[0].getStatus(row,col)!=0;
    }

    public boolean win()
    {
        return myGrids[0].win();
    }

    public void recordOpponentGuess(int a,int b)
    {
        if(myGrids[0].hasShip(a,b))
        {
            myGrids[0].markHit(a,b);
        }
        else
        {
            myGrids[0].markMiss(a,b);
        }
    }
}
