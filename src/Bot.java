abstract public class Bot {
    private final boolean isFirstTurn;
    private final String mark;

    public Bot(boolean isFirstTurn) {
        this.isFirstTurn = isFirstTurn;
        this.mark = isFirstTurn ? "X" : "O";
    }

    public String getMark() {
        return mark;
    }

    public boolean isFirstTurn() {
        return isFirstTurn;
    }

    abstract Action move(State s);

    public int[] move() {
        // create random move
        return new int[]{(int) (Math.random()*8), (int) (Math.random()*8)};
    }

}
