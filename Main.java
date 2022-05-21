import java.util.*;
public class Main {

    static Scanner sc = new Scanner(System.in);
    static Player[] players = new Player[2];
    public static void main(String[] args) {

        players[0]=new Player();
        players[1]=new Player();
        System.out.println("Place your ships.");
        System.out.println("Direction 1 is horizontal. Direction 2 is vertical.");
        players[0].printMyShips();

        //Has the player place their ships
        for(int i=0;i< Player.SHIP_LENGTHS.length;i++)
        {
            placeShip();
            players[0].printMyShips();
        }

        //Places the computers ships
        for(int i=0;i<Player.SHIP_LENGTHS.length;i++)
        {
            int col = -1;
            int row = -1;
            int direction = -1;
            boolean finished=false;
            while(!finished)
            {
                col = Randomizer.nextInt(0,Grid.NUM_COLS-1);
                row = Randomizer.nextInt(0,Grid.NUM_ROWS-1);
                direction = Randomizer.nextInt(0,1);
                finished=true;
                if(players[1].validShip(row,col,direction))
                    players[1].chooseShipLocation(row,col,direction);
                else
                    finished=false;
            }
        }

        //Main gameplay runs while neither player has won
        while(!players[0].win()&&!players[1].win())
        {
            System.out.println("Your guesses:");
            players[1].printOpponentGuesses();

            //Gets player guess and checks if they won
            askForGuess();
            if(players[1].win())
            {
                System.out.println("Congrats! You win.");
                continue;
            }

            //Generates computer guess, prints info, and then checks if computer won
            int col=-1;
            int row=-1;
            boolean finished=false;
            while(!finished)
            {
                col = Randomizer.nextInt(0,Grid.NUM_COLS-1);
                row = Randomizer.nextInt(0,Grid.NUM_ROWS-1);
                finished=true;
                if(players[0].alreadyGuessed(row,col))
                    finished = false;
                else
                {
                    finished = true;
                    players[0].recordOpponentGuess(row,col);
                }
            }
            sleep(1000);
            System.out.println("Opponent's guesses:");
            players[0].printOpponentGuesses();
            sleep(1000);
            System.out.println("Your ships:");
            players[0].printMyShips();
            sleep(1000);
            if(players[0].win())
                System.out.println("Sorry, you lose.");
        }

    }

    //delay function
    static void sleep(int time)
    {
        try {
            Thread.sleep(time);
        } catch (Exception e) {}
    }

    //Gets the users guess and makes sure they enter a valid guess
    static void askForGuess()
    {
        int col=-1;
        int row=-1;
        while(col<1||col>Grid.NUM_COLS)
        {
            System.out.println("Please enter a valid column: ");
            col = sc.nextInt();
        }
        while(row<1||row>Grid.NUM_ROWS)
        {
            System.out.println("Please enter a valid row: ");
            row = sc.nextInt();
        }
        players[1].recordOpponentGuess(row-1,col-1);

    }

    //Has the player place a ship and makes sure the position is valid
    static void placeShip()
    {
        boolean repeat = false;
        int col;
        int row;
        int direction;
        boolean finished=false;
        while(!finished)
        {
            if(repeat)
            {
                System.out.println("You can't place overlapping ships or");
                System.out.println("ships that go out of the field.");
            }
            col=-1;
            row=-1;
            direction=-1;
            while(col<1||col>Grid.NUM_COLS)
            {
                System.out.println("Please enter a valid column: ");
                col = sc.nextInt();
            }
            while(row<1||row>Grid.NUM_ROWS)
            {
                System.out.println("Please enter a valid row: ");
                row = sc.nextInt();
            }
            while(direction<1||direction>2)
            {
                System.out.println("Please enter a valid direction: ");
                direction = sc.nextInt();
            }
            finished=true;
            if(players[0].validShip(row-1,col-1,direction-1))
                players[0].chooseShipLocation(row-1,col-1,direction-1);
            else
                finished = false;
            if(!repeat)
                repeat=true;
        }

    }
}
