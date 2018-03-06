/*  ________________
 * |_*_|___|___|_*_|
 * |_*_|___|___|_*_|
 * |_*_|___|___|_*_|
 * |_*_|___|___|_*_|
 *
 * The above example depics a 4*4 playcard matrix
 * Two vertical combinations exists, determined as valid by @calculate()
*/

package mdza.games.loteria;

import java.util.Set;

public class WinCombinationVertical extends WinCombination {

    public WinCombinationVertical() {
        super("Vertical");
    }

    @Override
    Set<Combination> calculate(PlayCard card) {
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
                add(card, col);
        }
        return usedCombinations; 
    }
    
    private void add(PlayCard card, int col) {
        int rows = card.getRows();
        usedCombinations.add(new Combination(name, 0, rows - 1, col, col));
    }
}
