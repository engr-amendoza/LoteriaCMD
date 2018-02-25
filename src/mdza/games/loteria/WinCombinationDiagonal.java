package mdza.games.loteria;

import java.util.Set;

public class WinCombinationDiagonal extends WinCombination {

    public WinCombinationDiagonal(PlayCard card) {
        super("Diagonal", card);
    }

    @Override
    Set<Combination> calculate() {
        int rows = card.getRows();
        int cols = card.getCols();  
        
        int row;
        int col;
        
        boolean diagonalValid = true;
        // left diagonal
        if (!exists(0, rows-1, 0, cols-1)) {
            for (row=0; row<rows; ++row) {
                col = row;    

                if (!card.isUsed(row, col)) {
                    diagonalValid = false;
                    break;
                }                
            }
            if (diagonalValid)
                addEntry("Left", 0, rows-1, 0, cols-1);    
        }
        
        
        // right diagonal
        diagonalValid = true;
        col = cols - 1;
        if (!exists(0, rows-1, cols-1, 0)) {
            for (row=0; row<rows; ++row) {   

                if (!card.isUsed(row, col--)) {
                    diagonalValid = false;
                    break;
                }                
            }
            if (diagonalValid)
                addEntry("Right", 0, rows-1, cols-1, 0);    
        }
       
        return usedCombinations;
    }
    
    private void addEntry(String name, int startRow, int endRow, int startCol, int endCol) {
        int cols = card.getCols();
        usedCombinations.add(
                new Combination(name + " " + this.name, startRow, endRow, startCol, endCol));
    }
    
}
