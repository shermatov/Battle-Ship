public class Grid
{
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;

    private final Location[][] grid=new Location[NUM_ROWS][NUM_COLS];

    public boolean validShip(int row, int col,int direction,int length)
    {
        for(int i=0;i<length;i++)
        {
            if(direction==0&&col+length>NUM_COLS)
                return false;
            if(direction==1&&row+length>NUM_ROWS)
                return false;
            if(direction==0)
                if(grid[row][col+i].hasShip()) {
                    return false;
                }
            if(direction==1)
                if(grid[row+i][col].hasShip()) {
                    return false;
                }
        }
        return true;
    }

    public void addShip(Ship s)
    {
        for(int i=0;i<s.getLength();i++)
        {
            if(s.getDirection()==0)
                grid[s.getRow()][s.getCol()+i].setShip(true);
            if(s.getDirection()==1)
                grid[s.getRow()+i][s.getCol()].setShip(true);
        }
    }

    public Grid()
    {
        for(int i=0;i<NUM_ROWS;i++)
        {
            for(int j=0;j<NUM_COLS;j++)
            {
                grid[i][j]=new Location();
            }
        }
    }

    public void markHit(int row, int col)
    {
        grid[row][col].markHit();
    }

    public void markMiss(int row, int col)
    {
        grid[row][col].markMiss();
    }

    public void setStatus(int row, int col, int status)
    {
        grid[row][col].setStatus(status);
    }

    public int getStatus(int row, int col)
    {
        return grid[row][col].getStatus();
    }

    public boolean alreadyGuessed(int row, int col)
    {
        return !grid[row][col].isUnguessed();
    }

    public void setShip(int row, int col, boolean val)
    {
        grid[row][col].setShip(val);
    }

    public boolean hasShip(int row, int col)
    {
        return grid[row][col].hasShip();
    }

    public Location get(int row, int col)
    {
        return grid[row][col];
    }

    public void printStatus()
    {
        System.out.println("Your current grid of ships.");
        for(int i=0;i<NUM_ROWS;i++)
        {
            if (i==0)
                System.out.println("   A B C D E F J H I J");
            if(i==NUM_ROWS-1)
                System.out.print(i+1+" ");
            else
                System.out.print(i+1+"  ");
            for(int j=0;j<NUM_COLS;j++)
            {
                if(grid[i][j].getStatus()==0)
                    System.out.print("- ");
                if(grid[i][j].getStatus()==1)
                    System.out.print("X ");
                if(grid[i][j].getStatus()==2)
                    System.out.print("O ");
            }
            System.out.print("\n");
        }
    }

    public boolean win()
    {
        for(int i=0;i<NUM_ROWS;i++)
        {
            for(int j=0;j<NUM_COLS;j++)
            {
                if(grid[i][j].hasShip())
                {
                    if(grid[i][j].getStatus()==0)
                        return false;
                }
            }
        }
        return true;
    }

    public void printShips()
    {
        for(int i=0;i<NUM_ROWS;i++)
        {
            if (i==0)
                System.out.println("   1 2 3 4 5 6 7 8 9 10");
            if(i==NUM_ROWS-1)
                System.out.print(i+1+" ");
            else
                System.out.print(i+1+"  ");
            for(int j=0;j<NUM_COLS;j++)
            {
                if(grid[i][j].hasShip())
                    System.out.print("X ");
                else
                    System.out.print("- ");
            }
            System.out.print("\n");
        }
    }
}
