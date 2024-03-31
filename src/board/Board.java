package board;

import coordinates.Coordinates;
import java.util.HashMap;
import jumper.Jumper;

public class Board {
    int size;
    String[][] numbers;
    HashMap<String, Jumper> jumper;

    public Board(int size, HashMap<String, Jumper> jumper) {
        this.size = size;
        numbers = new String[size][size];
        this.jumper = jumper;
        
        int num = 1;
        for (int i = size - 1; i >= 0; i--) {
            if (i % 2 != 0) {
                for (int j = 0; j < size; j++) {
                    numbers[i][j] = num + "";
                    num++;
                }
            }
            else {
                for (int j = size - 1; j >= 0; j--) {
                    numbers[i][j] = num + "";
                    num++;
                }
            }
        }
    }
    
    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(numbers[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public int getBoardSize() {
        return this.size;
    }
    
    public HashMap<String, Jumper> getJumper() {
        return this.jumper;
    }
    
    public String getNumberString(Coordinates c) {
        return this.numbers[c.getRow()][c.getCol()];
    }
}
