
package mdza.games.loteria;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PlayCard {
    
    public PlayCard(Card[] card) {
        this(card, "Default Card");
    }
    
    public PlayCard(Card[] card, String name) {
        this(card, name, 4, 4);
    }
    
    public PlayCard(Card[] card, String name, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.name = name;
        used = new boolean[rows][cols];
        playCard = new Card[rows][cols];
        //combinations = new HashMap<>();
    }
    
    
    //
    // Setters
    //
    
    public boolean isUsed(int row, int col) {
        return used[row][col];
    }
    
    public void setSlot(int row, int col) {
        used[row][col] = true;
    }
    
    public void clearSlot(int row, int col) {
        used[row][col] = false;
    }
    
    //
    // Getters
    //
    
    public Card getSlot(int row, int col) {
        return playCard[row][col];
    }
    
    public String getPlayCardName() {
        return name;
    }
    
    public int getRows() { return rows; }
    
    public int getCols() { return cols; }
    
    public String toString() {
        StringBuilder slotSb = new StringBuilder();
        StringBuilder isUsedSb = new StringBuilder();
        for (int row=0; row<rows; ++row) {
            slotSb.append("|");
            isUsedSb.append(" ==> |");
            for (int col=0; col<cols; ++col) {
                slotSb.append(LPad(playCard[row][col].getName(), 10, ' '));
                slotSb.append("|");
                isUsedSb.append( (used[row][col] ? "*" : " ") );
                isUsedSb.append("|");
            }
            slotSb.append(isUsedSb);
            isUsedSb =  new StringBuilder();
            slotSb.append("\n");
        }
        return slotSb.toString();
    }
    
    /*
    public void checkCard() {
        // corners
        if (!combinations.containsKey(Loteria.WIN_COMBINATIONS.CORNERS)) {
            if (used[0][0] && used[0][cols - 1] 
                && used[rows - 1][0] && used[0][cols - 1]) {
                combinations.put(Loteria.WIN_COMBINATIONS.CORNERS, 1);
            }
        }
    }
    
    public Map<Loteria.WIN_COMBINATIONS, Integer> getCombinations() {
        return combinations;
    }*/
    
    //
    // Helpers
    //
    
    private static String RPad(String str, Integer length, char car) {
        return String.format("%" + (length - str.length()) + "s", "")
               .replace(" ", String.valueOf(car)) +  str;
    }
    
    private static String LPad(String str, Integer length, char car) {
        return str
         + 
         String.format("%" + (length - str.length()) + "s", "")
                     .replace(" ", String.valueOf(car));
    }
    
    private void initRandomSlots(Card[] card) {
        List<Card> cardsCopy = new LinkedList<>(Arrays.asList(card));
        int size = card.length;
        
        Random rand = new Random();
        for (int row=0; row < rows; ++row) {
            for (int col=0; col<cols; ++col) {
                int copySize = cardsCopy.size();
                int index = rand.nextInt(copySize);
                playCard[row][col] = (Card) cardsCopy.remove(index);
            }
        }
    }
    
    //
    // Builder
    //
    
    public static class Builder {
        
        public Builder(Card[] cards) {
            this.name = "Builder Card";
            this.cards = cards;
        }
        
        public Builder setRows(int rows) { 
            this.rows = rows; 
            return this;
        }
        
        public Builder setCols(int cols) { 
            this.cols = cols; 
            return this;
        }
        
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        
        public PlayCard build() throws Exception {
            if ((rows * cols) > cards.length)
                throw new Exception("Number of cards < play card size");
            else
                return new PlayCard(cards, name, rows, cols);
        }
        
        public PlayCard buildWithRandomSlots() throws Exception {
            PlayCard temp = build();
            temp.initRandomSlots(cards);
            return temp;
        }
        
        private int rows;
        private int cols;
        private String name;
        private Card[] cards;
        
        public static void Tester() throws Exception {
            PlayCard card = new Builder(LoteriaTester.cards)
                    .setRows(2)
                    .setCols(3)
                    .setName("Dummy Card")
                    .buildWithRandomSlots();
            
            card.setSlot(0, 0);
            card.setSlot(1, 2);
            System.out.println("~~Randomly generated play card\n" + card);
        }
        
    }
    
    //
    // Aux
    //
    public static PlayCard[] initPlayCards(int numOfPlayers, Card[] cards) throws Exception {
        PlayCard[] playCards = new PlayCard[numOfPlayers];
        for(int i=0; i<numOfPlayers; ++i)
            playCards[i] = new PlayCard.Builder(cards)
                    .setCols(3)
                    .setRows(3)
                    .setName("Player" + i)
                    .buildWithRandomSlots();
        return playCards;
    }
    
    private boolean[][] used;
    private Card[][] playCard;
    //private Map<Loteria.WIN_COMBINATIONS, Integer> combinations;
    private int rows;
    private int cols;
    private String name;
}
