/*  ________________
 * |___|___|___|___|
 * |___|_*_|_*_|___|
 * |___|_*_|_*_|___|
 * |___|___|___|___|
 *
 * The above example depics a 4*4 playcard matrix
 * A center combination exists, determined as valid by @calculate()
*/

package mdza.games.loteria;

import java.util.Set;

public class WinCombinationCenter extends WinCombination {
    public WinCombinationCenter() {
        super("Center");
    }
    
    @Override
    Set<WinCombination.Combination> calculate(PlayCard card) {
        int rows = card.getRows();
        int cols = card.getCols();
        
        calculateCenter(rows, cols);
        boolean centerValid = true;
        if (!exists(centerStartRow, centerEndRow, 
                centerStartCol, centerEndCol)) {
            
            for (int row=centerStartRow; row<centerEndRow; ++row) {
                for (int col=centerStartCol; col<centerEndCol; ++ col) {
                    if (!card.isUsed(row, col)) {
                        centerValid = false;
                    break;
                    }
                }
            }
            
            if (centerValid) {
                usedCombinations.add(new Combination(
                    name, centerStartRow, centerEndRow, 
                    centerStartCol, centerEndCol
                ));
            }
        }
        
        return usedCombinations;
    }
    
    
    private void calculateCenter(int rows, int cols) {
        if (rows >= 3 && cols >= 3) {
            centerStartRow = 1;
            centerEndRow = rows - 1;
            centerStartCol = 1;
            centerEndCol = cols - 1;
        } else {
            centerStartRow = 0;
            centerEndRow = rows;
            centerStartCol = 0;
            centerEndCol = cols;
        }
    }
      
    private int centerStartRow;
    private int centerEndRow;
    private int centerStartCol;
    private int centerEndCol;
}
