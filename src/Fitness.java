public class Fitness {
    private Bot bot1 = new SimulBot();
    private Bot bot2 = new MMABBot(3);

    private State state;

    public Fitness(int noNeighbor, int oneNeighbor, int twoNeighbor, int threeNeighbor, int fourNeighbor) {
        this.state = new State(noNeighbor, oneNeighbor, twoNeighbor, threeNeighbor, fourNeighbor);
    }

    public int value() {
        for (int i = 0; i < 28; i++) {
            Action action = bot1.move(state);
            state = new State(state, action);
            state.reverseBoard();
            action = bot2.move(state);
            state = new State(state, action);
            state.reverseBoard();

        }
        return state.calcAbsoluteValue();
    }
}
