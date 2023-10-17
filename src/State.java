import javafx.scene.control.Button;

public class State {
    private byte[][] mat; // Matrix
    private Bot bot; // Pelaku action terakhir pada state
    private int value; // value setelah action terakhir dilakukan

    public State(Button[][] buttons, Bot bot) {
        mat = new byte[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (buttons[i][j].getText().equals("")) {
                    mat[i][j] = 0;
                } else if (buttons[i][j].getText().equals("X")) {
                    mat[i][j] = 1;
                } else {
                    mat[i][j] = 2;
                }
            }
        }
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

    public State generateState(Action act, Bot actor) {
    }
}
