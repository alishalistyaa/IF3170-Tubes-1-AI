public class MMABBot extends Bot {
    public MMABBot(boolean isFirstTurn) {
        super(isFirstTurn);
    }

    public Action move(State s) {
        return new Action(0,0);
    }
}
