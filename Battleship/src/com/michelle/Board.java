package com.michelle;

import java.util.Arrays;

/**
 * Created by blondieymollo on 2/24/16.
 */

public class Board {

    private int boardSize = 0;
    private String name;
    private String[][] board;
    private String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "Q" };
    private String[] ships = {"B", "C", "C",
            "F","F","F","F",
            "M","M","M","M"
    };
    private int[] shipSizes = {
            5,4,4,3,3,3,3,2,2,2,2
    };



    public Board(int boardSize, String name){
        this.name = name;
        this.boardSize = boardSize+1;
        board = new String[this.boardSize][this.boardSize];
        prepBoard();
    }

    public String getName(){
        return name;
    }

    public void prepBoard () {
        for (int i = 0; i<boardSize; i++){
            for (int j = 0; j<boardSize; j++){
                //When J = 0, it fills the first column full of numbers
                if (j == 0){
                    if(i<10){
                        board[i][j]=Integer.toString(j)+Integer.toString(i);
                    }else {
                        board[i][j]=Integer.toString(i);
                    }
                }
                //When I = 0, fill the first row full of letters
                if (i == 0){
                    if (j != 0){
                        board[i][j] = letters[j-1];
                    }
                }

            }
        }
    }

    public void printBoard(){
        for (int i = 0; i<boardSize; i++){
            for (int j = 0; j<boardSize; j++){
                    if (board[i][j]==null){
                        System.out.print("- ");
                    }
                    else {
                        System.out.print(board[i][j] + " ");
                    }
            }
            System.out.print("\n");
        }
    }

    public boolean addShipToBoard (int kind, int i /*row*/, String j/*column*/, String orientation ){

        System.out.println(j);

        for (int k =0; k<shipSizes[kind]; k++){

            if (orientation.equals("h")){


                System.out.println("["+i+"]["+getIndex(j)+"]");
                //board[i][e] = letters[kind];


            } else {

                //if it's vertical then j will be the same


            }

        }

        return true;
    }



    private int getIndex(String letter){
        for (int i = 0; i < letters.length; i++) {
            if(letters[i].equalsIgnoreCase(letter)){
                return i+1;
            }
        }
        return -1;//not found
    }

}
