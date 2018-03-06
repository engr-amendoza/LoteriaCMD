/*   
 *   Loteria: spanish bingo game.
 *   A driver class containing all Loteria game events, card dealer and valid
 *   win combinations for a game session.
 *   Issues fixed:
 *   1. Before dealNextCard would automatically invoke move
 *      pending events. It calling class if, a delay was introduced in pending 
 *      event (i.e. delay in between cards dealt), some cards would be "skipped"
**/


package mdza.games.loteria;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import mdza.games.*;


public class Loteria  {
    
    public Loteria(Dealer dealer) {
        this(dealer, new WinCombinationFull[] {
            new WinCombinationFull()
        });
    }

    public Loteria(Dealer dealer, WinCombination[] winCombinations) {
        this.dealer = dealer;
        this.listener = listener;
        this.isGameStarted = false;
        this.winner = null;
        this.winCombinations = new HashSet<>();
        this.winCombinations.addAll(Arrays.asList(winCombinations));
    }
    
    public void play() {
        if (isGameStarted)
            return;
        
        gameStartedEvent();
        
        while(!exit) {
            try {
                movePendingEvent();
            } catch (Exception ex) {
                moveInvalidEvent();
                ex.printStackTrace();
            }        
        }

        gameFinishedEvent();
    }
    
    public Card dealPrevCard() {
        Card card = dealer.getPrevious();
        moveReceivedEvent(card);
        return card;
    }
    
    public Card dealNextCard() throws Exception {
        Card card = null;
        if (dealer.cardsLeft() > 0) {
            card = dealer.getNext();
            moveReceivedEvent(card);
        } else {
            exit = true;
        }
        return card;
    }
    
    public boolean loteria(PlayCard playCard) throws Exception {
        if (isWinner(playCard)) {
            winner = playCard;
            exit = true;
            return true;
        }
        else
            return false;
    }
    
    // 
    // Getters
    //
    
    public PlayCard getWinner() {
        return winner;
    }
    
    // 
    // Listeners
    //
    
    public synchronized void addEventListener(TurnBasedGameEventListener listener) {
        this.listener = listener;
    }

    public synchronized void removeEventListener() {
        this.listener = null;
    }
    
    
    //
    // Misc
    //
    
    private boolean isWinner(PlayCard playCard) {
        
        // at least one of the valid combinations is found
        for (WinCombination wc : winCombinations) {
            if (wc.calculate(playCard).size() < 1)
                return false;
        }
        
        // ensure playCards marked off (used slots) are called by the dealer
        int rows = playCard.getRows();
        int cols = playCard.getCols();
        
        for (int row=0; row<rows; ++row) {
            for (int col=0; col<cols; ++col) {
                if (playCard.isUsed(row, col)) {
                    Card card = playCard.getSlot(row, col);
                    if (!dealer.cardsUsed().contains(card))
                        return false;
                }
            }
        }
        
        return true;
    }
    
    
    //
    // Game Events
    //

    private synchronized void gameStartedEvent() {
        isGameStarted = true;
        GameEvent event = new GameEvent(this);
        if (listener != null)
            listener.handleGameStarted(event);
    }

    private synchronized void moveReceivedEvent(Card card) {   
        GameEvent event = new GameEvent(this);
        if (listener != null)
            listener.handleMoveReceived(event, card);
    }

    private synchronized void playerChangedEvent() {
        GameEvent event = new GameEvent(this);
        if (listener != null) 
            listener.handlePlayerChanged(event);
    }

    private synchronized void gameFinishedEvent() {
        GameEvent event = new GameEvent(this);
        if (listener != null) 
        listener.handleGameFinished(event);     
    }
    
    private synchronized Object movePendingEvent() throws Exception {
        GameEvent event = new GameEvent(this);
        if (listener != null) 
            return listener.handleMovePending(event);

        return null;
    }
    
    private synchronized void moveInvalidEvent() {
        GameEvent event = new GameEvent(this);
        Card card = null;
        if (listener != null) 
            listener.handleInvalidMove(event);
    }
    
    private synchronized void invalidGameStateEvent() {
        GameEvent event = new GameEvent(this);
        if (listener != null)
            listener.handleInvalidGameState(event); 
    }
    
    public void quit() { 
        this.exit = true; 
    }
    
    private boolean exit;
    private Dealer dealer;
    private TurnBasedGameEventListener listener;
    private boolean isGameStarted;
    private PlayCard winner;
    private Set<WinCombination> winCombinations;
}
