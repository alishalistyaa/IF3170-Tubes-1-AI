public class SABot extends Bot {
    private final int initTemp = 100;

    public SABot(boolean isFirstTurn) {
        super(isFirstTurn);
    }

    private int schedule(int currTemp) {
        return currTemp - 1;
    }

    public Action move(State s) {
        return new Action(0,0);
    }
}
