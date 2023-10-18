import java.util.Arrays;

public class GA2Bot {
    private int iteration;

    private int[][] weightTable = new int[4][5];

    public GA2Bot(int iteration) {
        this.iteration = iteration;
    }

    public void initiateWeightTable() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5 ; j++) {
                weightTable[i][j] = (int) (Math.random() * 10);
            }
        }
    }

    public void choseWeight() {
        int[] fitness = new int[4];
        for (int i = 0; i < 4; i++) {
            fitness[i] = new Fitness(weightTable[i][0], weightTable[i][1], weightTable[i][2], weightTable[i][3], weightTable[i][4]).value();
//            System.out.println("Hasil " + i +" :" + fitness[i]);
        }
        int[][] chosenWeight = new int[4][5];
        for (int i = 0; i < 4; i++) {
            int chosenIndex = chooseWeightFromFitness(fitness);
            chosenWeight[i] = weightTable[chosenIndex];
        }
        weightTable = chosenWeight;
    }

    public int chooseWeightFromFitness(int[] fitness) {
        int minFitness = Arrays.stream(fitness).min().getAsInt() - 1;
        for (int i = 0; i < 4; i++) {
            fitness[i] -= minFitness;
        }
        int fitnessSum = Arrays.stream(fitness).sum();
        int random = (int) (Math.random() * fitnessSum);
        int currSum = 0;
        for (int i = 0; i < 4; i++) {
            currSum += fitness[i];
            if (currSum >= random) {
                return i;
            }
        }
        return 3;
    }

    public void crossOver() {

        int[][] newWeightTable = new int[4][5];
        int random = (int) (Math.random() * 5);
        for (int j = 0; j < 5; j++) {
            if (j < random) {
                newWeightTable[0][j] = weightTable[0][j];
                newWeightTable[1][j] = weightTable[1][j];
            } else {
                newWeightTable[0][j] = weightTable[1][j];
                newWeightTable[1][j] = weightTable[0][j];
            }
        }
        random = (int) (Math.random() * 5);
        for (int j = 0; j < 5; j++) {
            if (j < random) {
                newWeightTable[2][j] = weightTable[2][j];
                newWeightTable[3][j] = weightTable[3][j];
            } else {
                newWeightTable[2][j] = weightTable[3][j];
                newWeightTable[3][j] = weightTable[2][j];
            }
        }
        weightTable = newWeightTable;
    }

    public void mutate() {
        int random = (int) (Math.random() * 5);
        int random2 = (int) (Math.random() * 4);
        weightTable[random2][random] += (int) (Math.random() * 10) - 5;
        if (weightTable[random2][random] < 0) {
            weightTable[random2][random] = 0;
        }
    }

    public void run() {
        initiateWeightTable();
        for (int i = 0; i < iteration-1; i++) {
//            System.out.println();
            choseWeight();
            crossOver();
            mutate();
            mutate();
            mutate();
            mutate();
        }
        choseWeight();
        crossOver();
        for (int i = 0; i < 4; i++) {
            System.out.println(Arrays.toString(weightTable[i]));
        }
    }

    public static void main(String[] args) {
        GA2Bot bot = new GA2Bot(100);
        bot.run();
//        for (int i = 0; i < 4; i++) {
//            System.out.println(Arrays.toString(bot.weightTable[i]));
//        }
    }
}
