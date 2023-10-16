import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.control.Button;

public class GeneticAlgorithm {
    private static final int COL = 8;
    private static final int ROW = 8;
    private static final String[] MARKS = {/* your array elements */};
    private List<State> generateRandomParents(int numParents) {
        List<State> parents = new ArrayList<>();

        for (int i = 0; i < numParents; i++) {
            State randomState = State.getRandomState();

            Bot randomBot = randomState.getBot();

            State newState = generateRandomState(randomBot);
            parents.add(newState);
        }

        return parents;
    }

    public static State generateRandomState(Bot currentBot) {
        Button[][] randomButtons = new Button[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                String randomMark = MARKS[(int) (Math.random() * MARKS.length)];
                randomButtons[i][j] = new Button(randomMark);
            }
        }

        return new State(randomButtons, currentBot);
    }

    private void calculateAndSortFitness(List<State> population) {
        for (State parent : population) {
            parent.setValue(parent.stateFitness());
        }

        Collections.sort(population, (s1, s2) -> Integer.compare(s2.getValue(), s1.getValue()));
    }

    private List<State> selectParentsRoulette(List<State> population, int numParents) {
        List<State> selectedParents = new ArrayList<>();
        int totalFitness = population.stream().mapToInt(State::getValue).sum();

        for (int i = 0; i < numParents; i++) {
            double randomValue = Math.random() * totalFitness;
            double cumulativeFitness = 0;

            for (State parent : population) {
                cumulativeFitness += parent.getValue();
                if (cumulativeFitness >= randomValue) {
                    selectedParents.add(parent);
                    break;
                }
            }
        }

        return selectedParents;
    }

    private List<State> crossover(List<State> parents) {
        List<State> successors = new ArrayList<>();

        // Select two parents for crossover
        State parent1 = parents.get(0);
        State parent2 = parents.get(1);

        // Crossover at crossover point 3
        State successor1 = crossoverAtPoint(parent1, parent2, 3);
        State successor2 = crossoverAtPoint(parent2, parent1, 3);

        successors.add(successor1);
        successors.add(successor2);

        return successors;
    }

    private State crossoverAtPoint(State parent1, State parent2, int crossoverPoint) {
        Button[][] childButtons = new Button[ROW][COL];

        // Copy the first part of parent1
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < crossoverPoint; j++) {
                childButtons[i][j] = new Button(parent1.getButtons()[i][j].getText());
            }
        }

        // Copy the second part of parent2
        for (int i = 0; i < ROW; i++) {
            for (int j = crossoverPoint; j < COL; j++) {
                childButtons[i][j] = new Button(parent2.getButtons()[i][j].getText());
            }
        }

        Bot childBot = parent1.getBot();
        return new State(childButtons, childBot);
    }

    public void runGeneticAlgorithm() {
        // Generate 4 random parents
        List<State> parents = generateRandomParents(4);

        // Calculate and sort fitness values
        calculateAndSortFitness(parents);

        // Select parents using roulette wheel selection
        List<State> selectedParents = selectParentsRoulette(parents, 2);

        // Perform crossover to generate successors
        List<State> successors = crossover(selectedParents);

        // Calculate and sort successors' fitness values s
        calculateAndSortFitness(successors);

        // Select the neighbor with the highest fitness as the next generation
        State nextNeighbor = successors.get(0);

        // gatau mau return apa tp yg pasti udh dpt tetangga
    }
}
