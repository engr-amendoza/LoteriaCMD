/*
 * Tester class for the Loteria (spanish bingo) back-end game
*/

package mdza.games.loteria;

import java.util.EventObject;
import java.util.Scanner;
import mdza.games.TurnBasedGameEventListener;
import static mdza.games.loteria.PlayCard.initPlayCards;

public class Tester {

    public static void main(String[] args) throws Exception {
        // Test cases (uncomment to test)
        
        //Deck.Tester();
        //Dealer.Tester();
        //PlayCard.Builder.Tester();
        //WinCombination.Tester();
        Tester();
    }
    private static void Tester() throws Exception {
        Scanner in = new Scanner(System.in);
        PlayCard[] playCards = initPlayCards(3, cards);
        
        TurnBasedGameEventListener listener = new TurnBasedGameEventListener() {
            @Override
            public void handleMoveReceived(EventObject e, Object o) {
                Card card = (Card) o;
                System.out.println("Card dealt: " + card.getName());
                
                System.out.println("Enter row, col for used slot");
                String[] line = new Scanner(System.in).nextLine().split(" ");
                if(line.length > 1)
                    playCards[0].setSlot(
                        Integer.parseInt(line[0]), Integer.parseInt(line[1]));
                
                System.out.println(playCards[0]);
            }

            @Override
            public void handleGameFinished(EventObject e) {
                Loteria loteria =  (Loteria) e.getSource();
                System.out.println("The winner is: " + 
                        loteria.getWinner().getPlayCardName());
            }

            @Override
            public void handleGameStarted(EventObject e) {
               System.out.println("Game started");    
                for (int i=0; i<playCards.length; ++i)
                    System.out.println(playCards[i]);
            }

            @Override
            public Object handleMovePending(EventObject e) {
                Loteria loteria = (Loteria) e.getSource();
                
                System.out.println("What action to take?");
                String action = in.nextLine();
                switch(action.toLowerCase()) {
                    case "back":
                        loteria.dealPrevCard();
                        break;
                    case "loteria":
                        try { 
                            if (!loteria.loteria(playCards[0]))
                                System.out.println("Not yet a winner");
                        } 
                        catch (Exception ex) { ex.printStackTrace(); }
                        break;
                    case "deal":
                    default:
                        try { loteria.dealNextCard(); } 
                        catch (Exception ex) { ex.printStackTrace(); }  
                        break;
                }
                return null;
            }

            @Override
            public void handlePlayerChanged(EventObject e) {
                System.out.println("Player changed");
            }

            @Override
            public void handleInvalidMove(EventObject e) {
                System.out.println("Invalid move!");
            }

            @Override
            public void handleInvalidGameState(EventObject e) {
               System.out.println("Invalid game state!");
            }
        };

        Loteria loteria = new Loteria(new Dealer(cards),
                // Available win combinations (uncomment to test)
                new WinCombination[] { 
                //new WinCombinationCorners(),
                //new WinCombinationDiagonal(),
                //new WinCombinationHorizontal(),
                //new WinCombinationCenter(),
                new WinCombinationOuter()
            } 
        );
       
        loteria.addEventListener(listener);
        loteria.play();
    }
    
    // Dummy test cards for Animals
    public static final Card[] cards = {
                 new Card("MONKEY")
                ,new Card("BIRD")    
                ,new Card("CAT")          
                ,new Card("DOG")     
                ,new Card("TIGER") 
                ,new Card("LION")
                ,new Card("PIG")
                ,new Card("COW")
                ,new Card("RAT")
                ,new Card("RABBIT")
                ,new Card("JAGUAR")
                ,new Card("BEATLE")
        };
}
