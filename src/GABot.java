import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GABot extends Bot {
    private static final int NUM_PARENTS = 4; //byk parent

    public Action getRandomAction(State s){
        List<Action> actions = s.getDefaultActions();
        Random rand = new Random();
        return (actions.get(rand.nextInt(actions.size())));
    }

    private double fitnessValue (Action a, State s){
        State nextState = new State(s, a);
        return (double) (nextState.calcValue());
    }

    private Action crossoverParents (Action x, Action y, State s){
        // crossover sekaligus hitung fitness value
        List<Action> actions = s.getDefaultActions();
        Action c1 = new Action (x.i, y.j);
        Action c2 = new Action (y.i, x.j);

        if (fitnessValue(c1, s) > fitnessValue(c2, s) && actions.contains(c1)){
            return (c1);
        }
        else if (actions.contains(c2)){
            return (c2);
        }
        else {
            //If both crossover are illegal move
            if (fitnessValue(x, s) > fitnessValue(x, s)){
                return (x);
            }
            else {
                return (y);
            }
        }
    }

    private Action generateAction (State s) {
        List<Action> parents = new ArrayList<>();
        List<Double> persentase = new ArrayList<Double>();
        List<Double> fitnessValue = new ArrayList<Double>();
        double totalFitnessValue = 0.0;
        double patokan = 0.0;
        Action parent1 = new Action(1,1);
        Action parent2 = new Action(1,1);

        for (int i = 0; i < NUM_PARENTS; i++) {
            Action newAction = getRandomAction(s);
            totalFitnessValue += fitnessValue(newAction, s);
            fitnessValue.add(fitnessValue(newAction, s));
            parents.add(newAction);

        }
        for (int i = 0; i < NUM_PARENTS; i++) {
            double persen = fitnessValue.get(i) / totalFitnessValue;
            double urutanPersen = patokan + persen;
            persentase.add(urutanPersen);
            patokan += fitnessValue.get(i) / totalFitnessValue;
        }


        //generate random number from 0-1
        double randomNumber1 = Math.random();

        //iterate through persentase, kalau <= ambil action dengan indeks i
        for (int i = 0; i < NUM_PARENTS; i++) {
            if (randomNumber1 <= persentase.get(i)) {
                parent1 = parents.get(i);
            }
        }

        //generate random number from 0-1
        double randomNumber2 = Math.random();

        //iterate through persentase, kalau <= ambil action dengan indeks i
        for (int i = 0; i < NUM_PARENTS; i++) {
            if (randomNumber2 <= persentase.get(i)) {
                parent2 = parents.get(i);
            }

        }
        return (crossoverParents(parent1, parent2, s));
    }

    public Action move(State s) {
        return generateAction(s);
    }
}
