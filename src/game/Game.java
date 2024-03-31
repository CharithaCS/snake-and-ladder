package game;

import board.Board;
import coordinates.Coordinates;
import dice.Dice;
import java.util.HashMap;
import java.util.Scanner;
import jumper.Jumper;
import player.Player;

public class Game {
    Board board;
    Player[] players;
    boolean[] isAllowed; // can't start until the player gets a one on dice
    int turn;
    Coordinates[] places;
    Dice dice;
    
    public Game(Board board, Player[] players, Dice dice) {
        this.board = board;
        this.players = players;
        this.dice = dice;
        turn = 0;
        isAllowed = new boolean[players.length];
        places = new Coordinates[players.length];
        
        for (int i = 0; i < players.length; i++) {
            places[i] = new Coordinates(board.getBoardSize() - 1, 0);
        }
    }
    
    public void play() {
        while(true) {
            System.out.println(players[turn].getPlayerName() + ", roll the die.");
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int num;
            if (n == 0) { 
                num = dice.diceRoll();
            } else {
                System.out.println("Press 0 to roll the die");
                continue;
            }
            System.out.println(players[turn].getPlayerName() + " rolled -> " + num);
            String initPos = board.getNumberString(places[turn]);
            if (!isAllowed[turn]) {
                if (num != 1) {
                    turn = 1 - turn;
                    continue;
                } else {
                    isAllowed[turn] = true;
                }
            }
            
            Coordinates newCoordinates = getNewCoordinates(places[turn], num);
            
            if (newCoordinates.getRow() < 0) {
                System.out.println("Roll exceeds board size");
                turn = 1 - turn;
                continue;
            }
            
            newCoordinates = snakeOrLadderJump(newCoordinates);
            
            places[turn] = newCoordinates;
            System.out.println(players[turn].getPlayerName() + " moved from "+ initPos + " to " + board.getNumberString(newCoordinates) + ".");
            if (newCoordinates.getRow() == 0) {
                if (board.getBoardSize() % 2 == 0) {
                    if(newCoordinates.getCol() == 0) {
                        System.out.println(players[turn].getPlayerName() + " won!");
                        return;
                    }
                } else if(newCoordinates.getCol() == board.getBoardSize() - 1) {
                    System.out.println(players[turn].getPlayerName() + " won!");
                    return;
                }
            }
            
            turn = 1 - turn;
        }
    }

    private Coordinates getNewCoordinates(Coordinates currCoordinates, int roll) {
        int row = currCoordinates.getRow();
        int col = currCoordinates.getCol();
        
        while (roll != 0) {
            if (row % 2 == 0) {
                if (col == 0) {
                    row--;
                } else {
                    col--;
                }
            } else {
                if (col == board.getBoardSize() - 1) {
                    row--;
                } else {
                    col++;
                }
            }
            roll--;
        }
        return new Coordinates(row, col);
    }

    private Coordinates snakeOrLadderJump(Coordinates coordinates) {
        String numberString = board.getNumberString(coordinates);
        HashMap<String, Jumper> map = board.getJumper();
        if (map.containsKey(numberString)) {
            Jumper j = map.get(numberString);
            System.out.println(j.getJumperName() + " found");
            return j.getDestCoordinates();
        }
        return coordinates;
     }
}