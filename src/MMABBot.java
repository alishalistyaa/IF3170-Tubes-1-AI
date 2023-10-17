public class MMABBot extends Bot {
    private final int maxTreeDepth = 3;
    public Action move(State s) {
        int currValue = Integer.MIN_VALUE;
        Action optimalAction = new Action(0,0);

        for (Action action : s.getDefaultActions()) {
            State newState = new State(s, action);
            int newValue = minValue(newState, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
            if (newValue > currValue) {
                currValue = newValue;
                optimalAction = action;
            }
        }
        return optimalAction;
    }

    public int minValue(State s, int alpha, int beta, int currTreeDepth) {
        if (currTreeDepth == maxTreeDepth || s.isBoardFull()) {
            return s.calcValue();
        }
        int currValue = Integer.MAX_VALUE;
        for (Action action : s.getDefaultActions()) {
            State newState = new State(s, action);
            int newValue = maxValue(newState, alpha, beta, currTreeDepth + 1);
            if (newValue < currValue) {
                currValue = newValue;
            }
            if (currValue <= alpha) {
                return currValue;
            }
            beta = Math.min(beta, currValue);
        }
        return currValue;
    }

    public int maxValue(State s, int alpha, int beta, int currTreeDepth) {
        if (s.isBoardFull() || currTreeDepth == maxTreeDepth) {
            return s.calcValue();
        }
        int currValue = Integer.MIN_VALUE;
        for  (Action action : s.getDefaultActions()) {
            State newState = new State(s, action);
            int newValue = minValue(newState, alpha, beta, currTreeDepth + 1);
            if (newValue > currValue) {
                currValue = newValue;
            }
            if (currValue >= beta) {
                return currValue;
            }
            alpha = Math.max(alpha, currValue);
        }
        return currValue;
    }
}
