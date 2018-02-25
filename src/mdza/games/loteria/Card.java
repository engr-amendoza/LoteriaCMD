/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
