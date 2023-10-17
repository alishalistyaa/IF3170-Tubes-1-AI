public class SABot extends Bot {
    private final int initTemp = 100;

    private int schedule(int currTemp) {
        return currTemp - 1;
    }

    public Action move(State s) {
        return new Action(0,0);
    }
}
