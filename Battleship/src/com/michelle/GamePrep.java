package com.michelle;

import java.util.Scanner;

/**
 * Created by blondieymollo on 2/24/16.
 */
public class GamePrep {

    private int size = -1;
    private String[] ships = {"Battleship", "first Cruiser", "second Cruiser",
        "first Frigate","second Frigate","third Frigate","fourth Frigate",
        "first Minesweeper","second Minesweeper","third Minesweeper","fourth Minesweeper"
    };

    public GamePrep(){
        printWelcomeMessage();
        askArraySize();
        initializeGame();
    }


    private void printWelcomeMessage(){
        System.out.println("Welcome to Battleship!");
        System.out.println("Created by Diana Michelle");
        System.out.println("");
        System.out.println("Let's start!!");
        System.out.println("");
        System.out.println("");
    }

    private void askArraySize(){
        System.out.println("How big should the board be? (Pick a number from 10 - 15 and press enter)");
        while (size == -1) {
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextLine()) {
                try {
                    size = Integer.parseInt(sc.nextLine());
                } catch (Exception e) {
                    System.out.println("There was an error with the size, please try with a number from 10 - 15!");
                }
            }

            if (size < 10 && size != -1){
                System.out.println("That board size is too small, try 10 or bigger!");
                size = -1;
            }

            if (size > 15 && size != -1){
                System.out.println("That board size is too big, try 15 or smaller!");
                size = -1;
            }
        }
    }

    private void initializeGame(){
        Board ub = new Board(size);
        Board cb = new Board(size);
        Board tb = new Board(size);
        //placeShips(ub);
        autoPlaceShips(ub);
        placeComputerShips(cb);
        new Play(ub, cb, tb);
    }

    private void autoPlaceShips (Board board){

        //This is the debug method that inserts ships automatically for debugging. Preffered size of board is 12
        int j = 1;
        int kind = 0;
        for (int i = 1; i<= ships.length; i++){
            board.addShipToBoard(kind,i,j,"h");
            kind++;
        }
        System.out.println("All done. Proceed");
        board.printBoard();
    }

    private void placeShips (Board board){

        //This is the one that should help the user place the ships
        int error = -1;
        int kind = 0;
        int i = 0;
        int j = 0;
        String orientation = "";

        System.out.println("Time to place your ships on the board!!");

        for (String ship : ships){
            do{
                board.printBoard();
                System.out.println("Where would you like to place the "+ship+" ? (Letter/Number)");
                Scanner sc = new Scanner(System.in);
                while(error == -1) {
                    if (sc.hasNextLine()) {
                        try {
                            String[] temp = sc.nextLine().split("/");
                            try {
                                j = board.getIndex(temp[0]);
                                i = Integer.parseInt(temp[1]);
                                error = 1;

                            } catch (Exception e) {
                                System.out.println("You need to specify the letter/number!!");
                                error = -1;
                            }
                        } catch (Exception e) {
                            System.out.println("There was an error there, could you try again to write the Letter/Number of your choice?");
                            error = -1;
                        }
                    }
                }
                error = -1;
                Scanner sc2 = new Scanner(System.in);
                while (error == -1) {
                    System.out.println("Horizontally or Vertically? (H/V)");
                    if (sc2.hasNextLine()) {
                        String temp1 = sc2.nextLine();

                        if (temp1.equalsIgnoreCase("h") || temp1.equalsIgnoreCase("v")) {
                            orientation = temp1;
                            error = 1;

                        } else {
                            System.out.println("There was an error, please choose H or V.");
                            error = -1;
                        }

                    }
                }
                error = -1;
            } while (!checkError(board, i, j, kind, orientation));
            board.addShipToBoard(kind,i,j,orientation);
            kind++;
        }
    }

    private boolean checkError(Board board, int i, int j, int kind, String orientation){

        if (board.coordinatesOutOfBounds(i, j)){
            System.out.println("You can't place a ship outside the board! Pick another coordinate");
            return false;
        }

        if (board.shipOutOfBounds(i,j,kind,orientation)){
            System.out.println("Uh Oh! The ship falls outside the board! Pick another coordinate");
            return false;
        }

        if (board.shipAlreadyExistsThere(i, j, kind, orientation)){
            System.out.println("There is a ship there already! Pick another coordinate");
            return false;
        }

        return true;
    }

    private void placeComputerShips (Board board){

        //This method will auto place the ships on the computer board
        int kind = 0;
        int randomi = 0;
        int randomj = 0;
        String orientation = "";

        for (String ship : ships){
            //System.out.println("Placing the computer's "+ship);
            do{
                randomi = (int )(Math.random() * size + 1);
                randomj = (int )(Math.random() * size + 1);
                int randomOrientation = (int )(Math.random() * 2 + 1);
                if (randomOrientation == 1){
                    orientation = "h";
                } else {
                    orientation ="v";
                }

            } while (!checkComputerError(board, randomi, randomj, kind, orientation));
            board.addShipToBoard(kind,randomi, randomj,orientation);
            kind++;
        }

        System.out.println("The computer has placed its ships!");
        board.printBoard();
    }

    private boolean checkComputerError(Board board, int i, int j, int kind, String orientation){

        if (board.shipOutOfBounds(i,j,kind,orientation)){
            return false;
        }

        if (board.coordinatesOutOfBounds(i, j)){
            return false;
        }

        if (board.shipAlreadyExistsThere(i, j, kind, orientation)){
            return false;
        }

        return true;
    }

}
