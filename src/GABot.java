public class GABot extends Bot {
    public GABot(boolean isFirstTurn) {
        super(isFirstTurn);
    }

    public Action move(State s) {
        return new Action(0,0);
    }
}
