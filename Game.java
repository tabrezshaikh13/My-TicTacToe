import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class Game {
    static Scanner scan = new Scanner(System.in);
    static Random rando = new Random();
    static int chances;

    static ArrayList<Integer> humanPositionsList = new ArrayList<Integer>();
    static ArrayList<Integer> pcPositionsList = new ArrayList<Integer>();

    static char[][] gameBoard = 
    { 
        { ' ', '|', ' ', '|', ' ' },
        { '-', '+', '-', '+', '-' },
        { ' ', '|', ' ', '|', ' ' },
        { '-', '+', '-', '+', '-' },
        { ' ', '|', ' ', '|', ' ' }
    };

    public static void printGameBoard() {
        System.out.println();
        for (char[] x1 : gameBoard) {
            for (char x2 : x1) {
                System.out.print(x2);
            }
            System.out.println();
        }
    }

    public static void setPiece(int Position, String Player) {
        char symbol = '#';
        if (Player == "Human") {
            symbol = 'X';
        } else if (Player == "PC") {
            symbol = 'O';
        }
        switch (Position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                System.out.println("INVALID POSITION");
        }
    }

    public static String checkWinner() {
        @SuppressWarnings("rawtypes")
        List topRow = Arrays.asList(1, 2, 3);
        @SuppressWarnings("rawtypes")
        List midRow = Arrays.asList(4, 5, 6);
        @SuppressWarnings("rawtypes")
        List botRow = Arrays.asList(7, 8, 9);
        @SuppressWarnings("rawtypes")
        List leftCol = Arrays.asList(1, 4, 7);
        @SuppressWarnings("rawtypes")
        List midCol = Arrays.asList(2, 5, 8);
        @SuppressWarnings("rawtypes")
        List rightCol = Arrays.asList(3, 6, 9);
        @SuppressWarnings("rawtypes")
        List diag1 = Arrays.asList(1, 5, 9);
        @SuppressWarnings("rawtypes")
        List diag2 = Arrays.asList(3, 5, 7);

        @SuppressWarnings("rawtypes")
        List<List> winningConditions = new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);
        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);
        winningConditions.add(diag1);
        winningConditions.add(diag2);

        for (@SuppressWarnings("rawtypes") List l : winningConditions) {
            if (humanPositionsList.containsAll(l)) {
                return "Human";
            } else if (pcPositionsList.containsAll(l)) {
                return "PC";
            } else if (chances == 9) {
                return "TIE";
            }
        }

        return "";
    }

    public static void main(String[] args) {
        boolean flag = true;
        printGameBoard();
        while (flag == true) {
            int humanPosition, pcPosition;
            String winnerString;

            System.out.print("Enter your Position(1-9): ");
            humanPosition = scan.nextInt();
            while (humanPositionsList.contains(humanPosition) || pcPositionsList.contains(humanPosition)) {
                System.out.println("Position already taken");
                System.out.print("Enter again(1-9): ");
                humanPosition = scan.nextInt();
            }
            humanPositionsList.add(humanPosition);
            chances++;
            setPiece(humanPosition, "Human");
            printGameBoard();
            winnerString = checkWinner();
            if (winnerString == "Human") {
                System.out.println("CONGRATULATIONS! YOU WIN");
                flag = false;
                break;
            } else if (winnerString == "PC") {
                System.out.println("YOU LOSE :( Better Luck Next Time");
                flag = false;
                break;
            } else if (winnerString == "TIE") {
                System.out.println("ITS A TIE");
                flag = false;
                break;
            }

            pcPosition = rando.nextInt(9) + 1;
            while (pcPositionsList.contains(pcPosition) || humanPositionsList.contains(pcPosition)) {
                pcPosition = rando.nextInt(9) + 1;
            }
            pcPositionsList.add(pcPosition);
            chances++;
            System.out.println("PC Played: " + pcPosition);
            setPiece(pcPosition, "PC");
            printGameBoard();
            winnerString = checkWinner();
            if (winnerString == "Human") {
                System.out.println("CONGRATULATIONS! YOU WIN");
                flag = false;
                break;
            } else if (winnerString == "PC") {
                System.out.println("YOU LOSE :( Better Luck Next Time");
                flag = false;
                break;
            } else if (winnerString == "TIE") {
                System.out.println("ITS A TIE");
                flag = false;
                break;
            }
        }
    }
}