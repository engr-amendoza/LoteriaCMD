/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdza.games.loteria;

import java.util.List;

public class Dealer {
    
    Dealer(Card[] cards) {
        unused = new Deck(cards);
        used = new Deck();
        unused.shuffle();
    }
    
    public Card getNext() {
        Card temp = unused.get();
        used.put(temp);
        return temp;
    }
    
    public Card getPrevious() {
        return (Card) used.peek();
    }
    
    
    public int cardsLeft() { return unused.size(); }
    
    private Deck used;
    private Deck unused;
    
    public static void Tester() {
        Card[] cards = LoteriaTester.cards;
        Dealer dealer  = new Dealer(cards); 
        int size = cards.length;
        int i = 0;
        
        System.out.println("~~ Getting all cards");
        while(dealer.cardsLeft() > 0) 
            System.out.println(dealer.getNext());
        
        System.out.println("~~ Getting prev cards");
        while(i++ < size)
            System.out.println(dealer.getPrevious());       
    }
}
