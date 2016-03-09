package com.michelle;

import java.util.Scanner;

/**
 * Created by blondieymollo on 3/7/16.
 */
public class Play {

    private Board ub = null;
    private Board cb = null;
    private Board tb = null;
    private boolean winner = false;

    public Play(Board ub, Board cb, Board tb) {

        this.ub = ub;
        this.cb = cb;
        this.tb = tb;

        Start();
    }

    private void Start(){
        System.out.println("Are you ready to start? (Press any key to start)");
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()){
            do{
                userTurn();
                checkWinner();
                computerTurn();
                checkWinner();
            }while (!winner);
        }
    }

    private void userTurn(){
        int error = -1;
        int j = 0, i = 0;

        tb.printBoard();

        System.out.println("Where would you like to attack? (Letter/Number)");
        Scanner sc = new Scanner(System.in);
        while(error == -1) {
            if (sc.hasNextLine()) {
                try {
                    String[] temp = sc.nextLine().split("/");
                    try {
                        j = tb.getIndex(temp[0]);
                        i = Integer.parseInt(temp[1]);
                        error = 1;
                    } catch (Exception e) {
                        System.out.println("You need to specify a letter/number!!");
                        error = -1;
                    }
                } catch (Exception e) {
                    System.out.println("There was an error there, could you try again to write the Letter/Number of your choice?");
                    error = -1;
                }
            }

            if (tb.coordinatesOutOfBounds(i, j)){
                System.out.println("You can't attack outside the board! Pick another coordinate");
                error = -1;
            }
            if (!tb.Null(i, j)){
                System.out.println("There was an attack there already! Pick another coordinate");
                error = -1;
            }
        }

        int result = cb.attack(i,j);
        switch (result){
            case -1: {
                tb.insertTurn(i,j,"$");
                tb.printBoard();
                System.out.println("Snap! There was no ship there!");
                break;
            }
            case 1:{
                cb.insertSunkenShip(cb.returnCoordinatesOfShip(i,j));
                tb.printBoard();
                System.out.println("Congratulations! You sank a ship!");
                break;
            }
        }
    }

    private void computerTurn(){

        int error = -1;

        while(error == -1) {
            int i = (int )(Math.random() * tb.getBoardSize() + 1);
            int j = (int )(Math.random() * tb.getBoardSize() + 1);

            if (tb.coordinatesOutOfBounds(i, j) || !tb.Null(i, j)) {
                error = -1;
            } else {
                int result = ub.attack(i,j);
                switch (result){
                    case -1: {
                        tb.insertTurn(i,j,"$");
                        System.out.println("Phew! The computer didn't hit any of your ships");
                        break;
                    }
                    case 1:{
                        ub.insertSunkenShip(ub.returnCoordinatesOfShip(i,j));
                        System.out.println("Doh! The computer sunk one of your ships!");
                        break;
                    }
                }
                error = 1;
            }
        }
        System.out.println("The computer has finished it's turn!");
    }
    private boolean checkWinner(){

        if(!cb.shipsLeft()){
            //User wins
            System.out.println("Oh wow! You won! Congratulations!");
            winner = true;
            return true;
        }

        if(!ub.shipsLeft()){
            //User wins
            System.out.println("Oh nou! The evil computer won!");
            winner = true;
            return true;
        }


        return false;
    }



}
