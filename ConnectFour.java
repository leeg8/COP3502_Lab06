import java.util.Scanner;

public class ConnectFour {

    //to print the board
    public static void printBoard(char[][] array){
        for(int i=array.length-1;i>=0;i--){         //rows are from top to bottom
            for(int j=0;j<array[0].length;j++){
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
    }

    //initialize the board
    public static void initializeBoard(char[][] array){
        for(int i=0;i<array.length;i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j]='-';
            }
        }
    }

    //inserting the chip in the desired column
    public static int insertChip(char[][] array, int col, char chipType){

        for(int i=0;i<array.length;i++){        //iterate through all rows and get the first empty row
            if(array[i][col]=='-'){
                array[i][col] = chipType;
                return i;
            }
        }
        return -1;  //should be an unreachable statement
    }

    //check after every turn if the game is over
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType){
        //if there are 3 columns before it
        if(col>=3){
            boolean win = true;
            for(int i=col-3;i<=col;i++){
                if(array[row][i]!=chipType){
                    win = false;
                    break;
                }
            }
            if(win)
                return true;
        }
        //if there are 3 rows before it
        if(row>=3){
            boolean win = true;
            for(int i=row-3;i<=row;i++){
                if(array[i][col]!=chipType){
                    win = false;
                    break;
                }
            }
            if(win)
                return true;
        }
        //if there are 3 columns after it
        if(col<=array[0].length-4){
            boolean win = true;
            for(int i=col+3;i>=col;i--){
                if(array[row][i]!=chipType){
                    win = false;
                    break;
                }
            }
            if(win)
                return true;
        }
        //if there are 3 rows after it
        if(row<=array.length-4){
            boolean win = true;
            for(int i=row+3;i>=row;i--){
                if(array[i][col]!=chipType){
                    win = false;
                    break;
                }
            }
            if(win)
                return true;
        }
        //didn't win for any other combination
        return false;
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int h,l;        //to take in the height and length parameters
        System.out.println("What would you like the height of the board to be? ");
        h=sc.nextInt();
        System.out.println("What would you like the length of the board to be? ");
        l=sc.nextInt();

        //initialize the array
        char[][] array = new char[h][l];
        initializeBoard(array);
        printBoard(array);
        int plays = 0;      //to keep track of the number of chances done

        System.out.println("\nPlayer 1: x\n" +
                "Player 2: o");

        boolean player1Chance = true;   //to switch  between the players
        int col;

        while(plays<l*h){               //can play only till number of free slots (initially its l*h)
            plays++;                    //increase the number of plays
            if(player1Chance){
                System.out.println("\nPlayer 1: Which column would you like to choose?");
            }
            else {
                System.out.println("\nPlayer 2: Which column would you like to choose?");
            }
            col = sc.nextInt();             //read input
            int row;

            //for each player, take the input, print the board and check if the game is won
            if(player1Chance) {
                row = insertChip(array, col, 'x');
                printBoard(array);
                if(checkIfWinner(array,col,row,'x')){
                    System.out.println("\nPlayer 1 won the game!");
                    return;
                }
            }
            else {
                row = insertChip(array, col, 'o');
                printBoard(array);
                if(checkIfWinner(array,col,row,'o')){
                    System.out.println("\nPlayer 2 won the game!");
                    return;
                }
            }
            player1Chance = !player1Chance;     //switch to the next player
        }
        //if the program reaches here, it means noone won and its a draw
        System.out.println("\nDraw. Nobody wins.");
    }
}
