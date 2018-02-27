package mdza.games.loteria;

import java.util.Set;

public class WinCombinationOuter extends WinCombination {

    public WinCombinationOuter() {
        super("Outer");
    }

    @Override
    Set<Combination> calculate(PlayCard card) {
        int rows = card.getRows();
        int cols = card.getCols();
        
        boolean outerValid = true;
        if (!exists(0, 0, 0, cols-1) && !exists(rows-1, rows-1, 0, cols-1) &&
            !exists(0, rows-1, 0, 0) && !exists(0, rows-1, cols-1, cols-1)) {
            
            // check first and last row
            for(int col=0; col<col; ++col) {
                
                if (!card.isUsed(0, col) || !card.isUsed(rows-1, col)) {
                    outerValid=false;
                    break;
                }
            }
            
            // check first and last column
            for(int row=0; row<rows; ++row) {
                
                if (!card.isUsed(0, row) || !card.isUsed(row, cols-1)) {
                    outerValid=false;
                    break;
                }
            }
            
            if (outerValid) {
                addEntry(card);
            }
            
        }
        
        return usedCombinations;
    }
    
    private void addEntry(PlayCard card) {
        int cols = card.getCols();
        int rows = card.getRows();
        
        usedCombinations.add(new Combination(name, 0, 0, 0, cols - 1));
        usedCombinations.add(new Combination(name, rows-1, rows-1, 0, cols - 1));
        usedCombinations.add(new Combination(name, 0, rows-1, 0, 0));
        usedCombinations.add(new Combination(name, 0, rows-1, cols-1, cols - 1));
    }
}
