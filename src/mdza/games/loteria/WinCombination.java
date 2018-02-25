package mdza.games.loteria;

import java.util.HashSet;
import java.util.Set;

public abstract class WinCombination {
    
    public WinCombination(String name, PlayCard card) {
        this.name = name;
        this.card = card;
        usedCombinations = new HashSet<>();
    }
    
    // OLD
    /*
    public static Set<Combination> getCombinations(
            WinCombination[] winCombinations) {
        //calculate();
        //return combinations;
        Set<Combination> validCombinations = new HashSet<>();
        for (WinCombination winCombination : winCombinations) {
            validCombinations.addAll( winCombination.calculate() );
        }
        return validCombinations;
    }*/
    
    public static Set<Combination> getCombinations(
            WinCombination[] winCombinations) {
        //calculate();
        //return combinations;
        Set<Combination> validCombinations = new HashSet<>();
        for (WinCombination winCombination : winCombinations) {
            validCombinations.addAll( winCombination.calculate() );
        }
        return validCombinations;
    }
    
    abstract Set<Combination>  calculate();
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%Combinations for %s\n", card.getPlayCardName()));
        
        for (Combination combination : usedCombinations) {
            sb.append(String.format("%s\n", combination));
        }
        
        return sb.toString();
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
    
    protected PlayCard card;
    protected Set<Combination> usedCombinations;
     //
    protected String name;
    
    public static void Tester() throws Exception {
        PlayCard[] playCards = PlayCard.initPlayCards(1, LoteriaTester.cards);
        
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
        Set<Combination> validCombinations = 
                WinCombination.getCombinations(new WinCombination[] {
                    new WinCombinationHorizontal(playCards[0]),
                    new WinCombinationVertical(playCards[0]),
                    new WinCombinationDiagonal(playCards[0]),
                    new WinCombinationCorners(playCards[0]),
                    new WinCombinationFull(playCards[0]),
                    new WinCombinationFull(playCards[0])
        });
        
        for(Combination validCombination : validCombinations)
            System.out.println(validCombination); 
    }
}
