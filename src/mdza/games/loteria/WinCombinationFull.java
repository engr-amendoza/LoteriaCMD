package mdza.games.loteria;

import java.util.Set;


public class WinCombinationFull extends WinCombination {
    
    public WinCombinationFull(PlayCard card) {
        super("Full", card);
    }

    @Override
    Set<Combination> calculate() {
        int cols = card.getCols();
          
        if (!exists(0, 0, cols-1, cols-1)) {
            Set<Combination> combinations = WinCombination.getCombinations(
                    new WinCombination[] {  
                        new WinCombinationHorizontal(this.card)
                    }
            );
        
            if (combinations.size() == cols) 
                addEntry();

        }

        return usedCombinations;
    }
    
    private void addEntry() {
        int rows = card.getRows();
        int cols = card.getCols();
        usedCombinations.add(new Combination(name, 0, 0, cols-1, cols-1));
    }
}
