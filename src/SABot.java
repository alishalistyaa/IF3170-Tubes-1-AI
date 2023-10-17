import java.util.List;
import java.util.Random;

public class SABot extends Bot {
    private final int initTemp = 100;
    private final int stopTemp = 0;

    private int schedule(int currTemp) {
        return currTemp - 1;
    }

    /** Pemilihan aksi random **/
    public Action getRandomAction(State s){
        List<Action> actions = s.getDefaultActions();
        Random rand = new Random();
        return (actions.get(rand.nextInt(actions.size())));
    }

    public Action move(State s) {
        // Ambil salah satu random state
        Action current = getRandomAction(s);
        State currentState = new State(s, current);
        int currStateValue = currentState.calcValue();
        int currTemp = initTemp;


        // TODO: make timer
        for(;;){

            currTemp = schedule(currTemp);
            if (currTemp <= stopTemp){ // or time abis
                break;
            }

            // Ambil random successor
            Action next = getRandomAction(s);
            State nextState = new State(s, next);

            // Kalkulasi successor
            int delta =  nextState.calcValue() - currStateValue;
            if(delta>0 || new Random().nextDouble() < Math.exp((double) delta /currTemp) ){
                return next;
            }
        }

        return current;
    }
}
