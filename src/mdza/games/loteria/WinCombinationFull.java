package mdza.games.loteria;

import java.util.Set;


public class WinCombinationFull extends WinCombination {
    
    public WinCombinationFull() {
        super("Full");
    }

    @Override
    Set<Combination> calculate(PlayCard card) {
        int cols = card.getCols();
          
        if (!exists(0, 0, cols-1, cols-1)) {
            Set<Combination> combinations = 
                    new WinCombinationHorizontal().calculate(card);
        
            if (combinations.size() == cols) 
                addEntry(card);
        }

        return usedCombinations;
    }
    
    private void addEntry(PlayCard card) {
        int rows = card.getRows();
        int cols = card.getCols();
        usedCombinations.add(new Combination(name, 0, 0, cols-1, cols-1));
    }
}
