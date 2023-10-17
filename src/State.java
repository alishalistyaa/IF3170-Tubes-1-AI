import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class State {
    private byte[][] mat; // Matrix
    private Bot bot; // Pelaku action terakhir pada state

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
    }

    public State(State prev, Bot nextBot, Action act) {
        this.mat = Arrays.stream(prev.mat)
                .map(byte[]::clone)
                .toArray(byte[][]::new);
        // do action
        this.mat[act.i][act.j] = nextBot.getMarkByte();
        this.bot = nextBot;
    }

    private int calcValueBase() {
        int value = 0;

        for (byte[] button : mat) {
            for (byte item : button) {
                value += item == bot.getMarkByte() ? 1 : -1;
            }
        }

        return value;
    }

    public int calcValue() {
        return calcValueBase();
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8 ; j++) {
                if (mat[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Action> getDefaultActions() {
        List<Action> actions = new ArrayList<>();
        int idx = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8 ; j++) {
                if (mat[i][j] == 0) {
                    actions.add(new Action(i, j));
                }
            }
        }
        return actions;
    }
}
