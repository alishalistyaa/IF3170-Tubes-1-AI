import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class State {
    private int[][] mat; // Matrix
    private boolean isOpponentTurn;

    public State(Button[][] buttons, boolean isOpponentTurn) {
        mat = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (buttons[i][j].getText().equals("")) {
                    mat[i][j] = 0;
                } else if (buttons[i][j].getText().equals("X")) {
                    mat[i][j] = isOpponentTurn ? -1 : 1;
                } else {
                    mat[i][j] = isOpponentTurn ? 1 : -1;
                }
            }
        }
        this.isOpponentTurn = isOpponentTurn;
    }

    public State(State prev, Action act) {
        this.mat = Arrays.stream(prev.mat)
                .map(int[]::clone)
                .toArray(int[][]::new);

        // do action
        this.mat[act.i][act.j] = prev.isOpponentTurn ? 2 : 1;
        this.isOpponentTurn = !prev.isOpponentTurn;
    }

    private int calcValueBase() {
        int value = 0;

        for (int[] button : mat) {
            for (int item : button) {
                value += item;
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
