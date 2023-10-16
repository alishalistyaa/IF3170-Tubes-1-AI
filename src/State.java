import javafx.scene.control.Button;

public class State {
    private Button[][] buttons; // Matrix
    private Bot bot; // Pelaku action terakhir pada state
    private int value; // value setelah action terakhir dilakukan
    private static final int COL = 8;
    private static final int ROW = 8;

    public State(Button[][] buttons, Bot bot) {
        this.buttons = buttons;
        this.bot = bot;
        this.value = calcValue();
    }

    public State(Button[][] buttons, Bot bot, int value) {
        this.buttons = buttons;
        this.bot = bot;
        this.value = value;
    }

    private int calcValueBase() {
        int value = 0;

        for (Button[] button : buttons) {
            for (Button item : button) {
                value += item.getText().equals(bot.getMark()) ? 1 : -1;
            }
        }

        return value;
    }

    private int calcValue() {
        return calcValueBase();
    }

    public int getValue() {
        return value;
    }

//    public State generateState(Action act, Bot actor) {
//
//    }
    public int calculateFitness(int i, int j) {
       // fitness value setiap cell
        int fitness = 0;

        // Check right
        fitness += countFlippableMarks(i, j, 0, 1);

        // Check left
        fitness += countFlippableMarks(i, j, 0, -1);

        // Check down
        fitness += countFlippableMarks(i, j, 1, 0);

        // Check up
        fitness += countFlippableMarks(i, j, -1, 0);

        return fitness;
    }

    public int countFlippableMarks(int row, int col, int rowDirection, int colDirection) {
        int opponentMarks = 0;

        int currentRow = row + rowDirection;
        int currentCol = col + colDirection;

        while (isValidCell(currentRow, currentCol)) {
            if (this.buttons[currentRow][currentCol].getText().equals(getOpponentMark(this.bot))) {
                opponentMarks++;
                currentRow += rowDirection;
                currentCol += colDirection;
            } else {
                break;
            }
        }
        return opponentMarks;
    }

    public boolean isValidCell(int row, int col) {
        return row >= 0 && row < ROW && col >= 0 && col < COL;
    }

    public String getOpponentMark(Bot bot) {
        String currentMark = bot.getMark();
        return (currentMark.equals("X")) ? "O" : "X";
    }


    public int stateFitness() {
        // fitness value keseluruhan pada suatu state
        int totalFitness = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                totalFitness += calculateFitness(i, j);
            }
        }
        return totalFitness;
    }

    public Button[][] getButtons() {
        return buttons;
    }

    public Bot getBot() {
        return bot;
    }

    public static State getRandomState() {
        // Assuming ROW and COL are constants
        Button[][] randomButtons = new Button[ROW][COL];

        String[] MARKS = {/* your array elements */};

        // Populate randomButtons with random marks
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                String randomMark = MARKS[(int) (Math.random() * MARKS.length)];
                randomButtons[i][j] = new Button(randomMark);
            }
        }

        State randomState = getRandomState();
        Bot randomBot = randomState.getBot();

        return new State(randomButtons, randomBot);
    }

    public void setValue(int value) {
        this.value = value;
    }

}
