package mdza.games.loteria;

import java.util.Set;

public class WinCombinationHorizontal extends WinCombination {

    public WinCombinationHorizontal(PlayCard card) {
        super("Horizontal", card);
    }
    
    @Override
    Set<Combination> calculate() {
        int rows = card.getRows();
        int cols = card.getCols();
        
        //if (this.combinations != null)
        //    return;
        int row;
        for (row=0; row<rows; ++row) {
                    
            int col;
            boolean horizontalValid = true;
            for (col=0; col<cols; ++col) {
                
                if (exists(row, row, col, cols-1))
                    break;
                
                if (!card.isUsed(row, col)) {
                    horizontalValid = false;
                    break;
                }
            }
            
            if (horizontalValid)
                addEntry(row);
        }
        return usedCombinations;
    }
    

    
    private void addEntry(int row) {
        int cols = card.getCols();
        usedCombinations.add(new Combination(name, row, row, 0, cols - 1));
    }
}
