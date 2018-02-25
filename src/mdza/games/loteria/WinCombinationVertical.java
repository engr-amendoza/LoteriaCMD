package mdza.games.loteria;

import java.util.Set;

public class WinCombinationVertical extends WinCombination {

    public WinCombinationVertical(PlayCard card) {
        super("Vertical", card);
    }

    @Override
    Set<Combination> calculate() {
        int rows = card.getRows();
        int cols = card.getCols();  
        
        int col=0;
        for (col=0; col<cols; ++col) {
            int row;
            boolean verticalValid = true;
            for (row=0; row<rows; ++row) {
                 
                if (exists(row, rows-1, col, col))
                    break;
                
                if (!card.isUsed(row, col)) {
                    verticalValid = false;
                    break;
                }
            }
            
            if (verticalValid)
                add(col);
        }
        return usedCombinations; 
    }
    
    private void add(int col) {
        int rows = card.getRows();
        usedCombinations.add(new Combination(name, 0, rows - 1, col, col));
    }
    
}
