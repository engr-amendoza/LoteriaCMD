package mdza.games.loteria;

import mdza.games.*;

public class Loteria  {

    public Loteria(Dealer dealer, PlayCard[] playCards) {
        this.dealer = dealer;
        this.playCards = playCards;
        this.listener = listener;
        this.isGameStarted = false;
        this.winner = null;
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
    
    public void dealPrevCard() {
        card = dealer.getPrevious();
        moveReceivedEvent(card);
    }
    
    public void dealNextCard() throws Exception {
        if (dealer.cardsLeft() > 0) {
            card = dealer.getNext();
            moveReceivedEvent(card);
            movePendingEvent();
        } else {
            exit = true;
        }
    }
    
    public void loteria(PlayCard playCard) throws Exception {
        if (isWinner(playCard))
            gameFinishedEvent();
        else
            movePendingEvent();//dealNextCard();
    }
    
    //
    // Getters
    //
    
    // 
    // Setters
    //
    public void setWinCombinations(WinCombination
    
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
        quit();
    }
    
    private synchronized Status movePendingEvent() throws Exception {
        GameEvent event = new GameEvent(this);
        Status status = null;
        if (listener != null) 
            status = (Status) listener.handleMovePending(event);

        return status;
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
        //gameFinishedEvent();
        this.exit = true; 
    }
    
    private boolean exit;
    private Dealer dealer;
    private TurnBasedGameEventListener listener;
    private PlayCard[] playCards;
    private boolean isGameStarted;
    private Card card;
    private PlayCard winner;
    private WinCombination[] winCombinations;
    /*
    public enum WIN_COMBINATIONS {
        FULL(20), 
        ROW_LINE(3), 
        COLUMN_LINE(3), 
        DIAGONAL(7), 
        CORNERS(4);
        
        private WIN_COMBINATIONS(int value) {
            this.value = value;
        }
        
        private final int value;
    }*/
}
