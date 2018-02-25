package mdza.games.loteria;

import java.util.Set;

public class WinCombinationCorners extends WinCombination {
    
    public WinCombinationCorners(PlayCard card) {
        super("Corners", card);
    }

    @Override
    Set<Combination> calculate() {
        int rows = card.getRows();
        int cols = card.getCols();
        
        if(!exists(0, rows-1, 0, cols-1)) {
            if (card.isUsed(0, 0) && card.isUsed(0, rows-1) &&
                card.isUsed(rows-1, 0) && card.isUsed(rows-1, cols-1)) {
                addEntry();
            }
        }

        return usedCombinations;
    }
    
    private void addEntry() {
        int rows = card.getRows();
        int cols = card.getCols();
        usedCombinations.add(new Combination(name, 0, rows-1, 0, cols-1));
    }
}
