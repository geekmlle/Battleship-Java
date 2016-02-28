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
        Board ub = new Board(size, "User Board");
        Board cb = new Board(size, "Computer Board");
        Board tb = new Board(size, "Turn Board");
        placeShips(ub);
        placeShips(cb, true);
    }

    private void placeShips (Board board){

        //This is the one that should help the user place the ships

        int error = -1;
        int kind = 0;
        int i = 0;
        String j = "";
        String orientation = "";

        System.out.println("Time to place your ships on the board!!");

        for (String ship : ships){
            board.printBoard();
            System.out.println("Where would you like to place the "+ship+" ? (Letter/Number)");
            Scanner sc;
            while(error == -1){
                sc = new Scanner(System.in);
                if (sc.hasNextLine()){
                    try{
                        String[] temp = sc.nextLine().split("/");
                        try{
                            j = temp[0];
                            i = Integer.parseInt(temp[1]);
                            error = 1;
                        }catch (Exception e){
                            System.out.println("You need to specify a number!!");
                        }
                    } catch (Exception e){
                        System.out.println("There was an error there, could you try again to write the Letter/Number of your choice?");
                    }
                }
            }

            error = -1;

            while (error == -1){
                System.out.println("Horizontally or Vertically? (H/V)");

                sc = new Scanner(System.in);
                if (sc.hasNextLine()){
                    String temp1 = sc.nextLine();

                    if (temp1.equalsIgnoreCase("h") ||  temp1.equalsIgnoreCase("v")){
                        orientation = temp1;
                        error = 1;
                    } else {
                        System.out.println("There was an error, please choose H or V.");
                    }

                }
            }


            board.addShipToBoard(kind,i,j,orientation);

            kind++;

        }


    }


    private void placeShips (Board board, boolean auto){

        //This method will auto place the ships on the computer board

    }

}
