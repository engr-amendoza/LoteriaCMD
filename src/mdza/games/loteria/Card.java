/*
 * A card entity to be used for filling in PlayCard slots or to be dealt by 
 * a Dealer
**/

package mdza.games.loteria;

public class Card {
    public Card(String name) {
        this(name, "");
    }
    
    public Card(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
    
    @Override
    public String toString() { return getName(); }
    
    private String name;
    private String image;
}
