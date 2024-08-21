public class Tile {
    private String letter;
    private int value;
    public Tile(){
        letter = "";
        value = 0;
    }
    public Tile(String letter, int value){
        this.letter = letter;
        this.value = value;
    }
    public String getLetter(){
        return letter;
    }
    public int getValue(){
        return value;
    }
    public void setLetter(String letter){
        this.letter = letter;
    }
    public void setValue(int value){
        this.value = value;
    }
    public void printTile(){
        System.out.println(this.toString());
    }
    public boolean equals(Tile target){
        return letter.equals(target.getLetter()) && (value == target.getValue());
    }
    public String toString(){
        return String.format("Letter: %s, Value: %d", letter, value);
    }
}
