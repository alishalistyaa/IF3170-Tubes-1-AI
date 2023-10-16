import javafx.scene.control.Button;

public class State {
    private Button[][] buttons; // Matrix
    private Bot bot; // Pelaku action terakhir pada state
    private int value; // value setelah action terakhir dilakukan

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

    public State generateState(Action act, Bot actor) {
    }
}
