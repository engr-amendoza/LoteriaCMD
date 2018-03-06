/*
 * Base class for generating win combinations.
 * Child implementations will provide their own implementation of a particular
 * playcard win combinations a user can have to win the game.
 *
 * The @calculate(PlayCard) method (to be overriden) simply returns a hashset 
 * containing (in case a combination can occur more than once in a playcard)
 * 
 * An intermediary static class Combination simply holds star/end positions for 
 * row/col that denote where on a N*N matrix the combination was made
 * (See WinCombinationHorizontal/WinCombinationVertical/WinCombinationDiagonal
 * for an example)
**/

package mdza.games.loteria;

import java.util.HashSet;
import java.util.Set;

public abstract class WinCombination {
    
    public WinCombination(String name) {
        this.name = name;
        usedCombinations = new HashSet<>();
    }
    
    public Set<Combination> getUsedCombinations() { return usedCombinations; }
    
    public String getName() { return this.name; }
    
    abstract Set<Combination>  calculate(PlayCard card);
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Combinations\n");
        
        for (Combination combination : usedCombinations) {
            sb.append(String.format("%s\n", combination));
        }
        
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object o) {
        WinCombination o2 = (WinCombination) o;
        return this.getName().equalsIgnoreCase(o2.getName());
    } 
        
    protected boolean exists(int startRow, int endRow, int startCol, int endCol) {
        return usedCombinations.contains(
                new Combination(name, startRow, endRow, startCol, endCol));
    }

    public static class Combination {
        
        public Combination(String name, int startRow, int endRow, 
                int startCol, int endCol) {
            this.name = name;
            this.startRow = startRow;
            this.endRow = endRow;
            this.startCol = startCol;
            this.endCol = endCol;
        }
        
        public int getStartRow() { return startRow; };
        public int getEndRow() { return endRow; };
        
        public int getStartCol() { return startCol; };
        public int getEndCol() { return endCol; };
        public String getName() { return name; }
        
        @Override 
        public boolean equals(Object o) {
           return this.hashCode() == (o.hashCode());
        }
        
        @Override
        public int hashCode() { 
            return (startRow * 7) + 
                   (endRow * 17) +
                   (startCol * 27) + 
                   (endCol * 37) +
                    name.hashCode();
        }
        
        @Override 
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("\t%s Combination \n", name));
        
            sb.append(String.format(
                        "\tstartRow: %d endRow: %d startCol: %d endCol: %d\n", 
                        startRow, endRow, startCol, endCol));
            return sb.toString();
        }
        
        private int startRow;
        private int endRow;
        private int startCol;
        private int endCol;
        private String name;
    }
    
    protected Set<Combination> usedCombinations;
    protected String name;
    
    public static void Tester() throws Exception {
        PlayCard[] playCards = PlayCard.initPlayCards(1, Tester.cards);
        
        System.out.println("Play Card:");

        playCards[0].setSlot(0, 0);
        playCards[0].setSlot(0, 1);
        playCards[0].setSlot(0, 2);
        
        playCards[0].setSlot(1, 0);
        playCards[0].setSlot(1, 1);
        playCards[0].setSlot(1, 2);
        
        playCards[0].setSlot(2, 0);
        playCards[0].setSlot(2, 1);
        playCards[0].setSlot(2, 2);
        
        System.out.println(playCards[0]);
        
        
        System.out.println("Combinations found: ");
       
        Set<Combination> validCombinations = new HashSet<>();
        validCombinations.addAll(
               new WinCombinationHorizontal().calculate(playCards[0]));
        validCombinations.addAll(
               new WinCombinationVertical().calculate(playCards[0]));
        validCombinations.addAll(
               new WinCombinationDiagonal().calculate(playCards[0]));
        validCombinations.addAll(
               new WinCombinationCorners().calculate(playCards[0]));
        validCombinations.addAll(
               new WinCombinationFull().calculate(playCards[0]));
        
        for(Combination validCombination : validCombinations)
            System.out.println(validCombination); 
    }
}
