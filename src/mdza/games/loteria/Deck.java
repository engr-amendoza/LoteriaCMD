/*
 * A wrapper container stack for holding cards.
**/

package mdza.games.loteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Deck {
    
    public Deck() {
        deck = new Stack();
    }
    
    public Deck(Card[] cards) {
        deck = new Stack();
        toStack(cards);
    }
    
    public void shuffle() {
        List<Card> cardsCopy = new ArrayList<>(deck);
        int size = cardsCopy.size();
        Random rand = new Random();
        deck.clear();
        while(size > 0) {
            int randIndex = rand.nextInt(size);
            Card randCard = cardsCopy.get(randIndex);
            deck.push(randCard);
            cardsCopy.remove(randIndex); 
            size = cardsCopy.size();
        }
    } 
    
    private void toStack(Card[] cards) {
        Stack temp = new Stack();
        deck.clear();
        for (Card card : cards) {
            temp.push(card);
        }
        // reverse order
        while(!temp.isEmpty()) {
            deck.push(temp.pop());
        }
    }
    
    public Card peek() {
        return (deck.size() > 0) ? (Card) deck.pop() : null;
    }
    
    public void put(Card card) {
        deck.push((Card)card);
    }
    
    public Card get() {
        return (size() > 0) ? (Card) deck.pop() : null;
    }
    
    public int size() { return deck.size(); }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for (int i=0; i<size(); ++i) {
            Card card = (Card) deck.get(i);
            sb.append(String.format("\t%d: %s\n", i, card.getName()));
        }
        
        return sb.toString();
    }
    
    public Card[] copy() { return (Card[]) deck.toArray(new Card[0]); }
   
    private Stack deck;
    
    public static void Tester() {
        Card[] cards = Tester.cards;
        Deck deck = new Deck(cards);
        System.out.println("~~ Deck created\n" + deck);
        deck.shuffle();
        System.out.println("~~ Deck shuffled\n" + deck);    
    }
}
