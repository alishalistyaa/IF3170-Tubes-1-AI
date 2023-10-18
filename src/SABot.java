import java.util.List;
import java.util.Random;

public class SABot extends Bot {
    private final double initTemp = 2.8;
    private final double stopTemp = 0;

    private double schedule(double currTemp) {
        return (currTemp - 0.2);
    }

    /** Pemilihan aksi random **/
    public Action getRandomAction(State s){
        List<Action> actions = s.getDefaultActions();
        Random rand = new Random();
        return (actions.get(rand.nextInt(actions.size())));
    }

    public Action move(State s) {
        // Timer
        long startTime = System.currentTimeMillis();
        long maxDuration = 4990; // in ms

        // Ambil salah satu random state
        Action current = getRandomAction(s);
        State currentState = new State(s, current);
        int currStateValue = currentState.calcValue();
        double currTemp = initTemp;

        while (System.currentTimeMillis() - startTime < maxDuration) {
//            System.out.println(System.currentTimeMillis()- startTime);

            currTemp = schedule(currTemp);
            if (currTemp <= stopTemp){ // or time abis
                break;
            }

            // Ambil random successor
            Action next = getRandomAction(s);
            State nextState = new State(s, next);

            // Kalkulasi successor
            int delta =  nextState.calcValue() - currStateValue;
            System.out.println("delta " + delta);

            double probability = new Random().nextDouble();
            double threshold = Math.exp((double) delta /currTemp);
            System.out.println("p = " + probability);
            System.out.println("t = " + threshold);
            if( delta > 0 || ( delta < 0 && probability < threshold) ){
                System.out.println("hitung");
                return next;
            }
        }
        return current;
    }
}
