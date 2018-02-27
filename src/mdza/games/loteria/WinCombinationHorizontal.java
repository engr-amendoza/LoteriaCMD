package mdza.games.loteria;

import java.util.Set;

public class WinCombinationHorizontal extends WinCombination {

    public WinCombinationHorizontal() {
        super("Horizontal");
    }

    @Override
    Set<Combination> calculate(PlayCard card) {
        int rows = card.getRows();
        int cols = card.getCols();
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
                addEntry(card, row);
        }
        return usedCombinations;
    }
    

    
    private void addEntry(PlayCard card, int row) {
        int cols = card.getCols();
        usedCombinations.add(new Combination(name, row, row, 0, cols - 1));
    }
}
