import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class State {
    public int[][] mat; // Matrix
    private boolean isOpponentTurn = false;

    public int noNeighbor;
    public int oneNeighbor;
    public int twoNeighbor;
    public int threeNeighbor;
    public int fourNeighbor;

    public State (Button[][] buttons) {
        mat = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (buttons[i][j].getText().equals("")) {
                    mat[i][j] = 0;
                } else if (buttons[i][j].getText().equals("X")) {
                    mat[i][j] = -1;
                } else {
                    mat[i][j] = 1;
                }
            }
        }
    }

    public State (int noNeighbor, int oneNeighbor, int twoNeighbor, int threeNeighbor, int fourNeighbor) {
        this.noNeighbor = noNeighbor;
        this.oneNeighbor = oneNeighbor;
        this.twoNeighbor = twoNeighbor;
        this.threeNeighbor = threeNeighbor;
        this.fourNeighbor = fourNeighbor;

        mat = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mat[i][j] = 0;
            }
        }
        mat[0][0] = 1;
        mat[0][1] = 1;
        mat[1][0] = 1;
        mat[1][1] = 1;
        mat[6][6] = -1;
        mat[6][7] = -1;
        mat[7][6] = -1;
        mat[7][7] = -1;
    }

    public State(State prev, Action act) {
        this.noNeighbor = prev.noNeighbor;
        this.oneNeighbor = prev.oneNeighbor;
        this.twoNeighbor = prev.twoNeighbor;
        this.threeNeighbor = prev.threeNeighbor;
        this.fourNeighbor = prev.fourNeighbor;

        this.mat = Arrays.stream(prev.mat)
                .map(int[]::clone)
                .toArray(int[][]::new);

        // do action
        int a = prev.isOpponentTurn ? -1 : 1;
        this.mat[act.i][act.j] = a;

        // has left
        if ((act.j - 1 >= 0) && (this.mat[act.i][act.j - 1] != 0)) {
            this.mat[act.i][act.j - 1] = a;
        }

        // has right
        if ((act.j + 1 <= 7) && (this.mat[act.i][act.j + 1] != 0)) {
            this.mat[act.i][act.j + 1] = a;
        }

        // has top
        if ((act.i - 1 >= 0) && (this.mat[act.i - 1][act.j] != 0)) {
            this.mat[act.i - 1][act.j] = a;
        }

        // has bottom
        if ((act.i + 1 <= 7) && (this.mat[act.i + 1][act.j] != 0)) {
            this.mat[act.i + 1][act.j] = a;
        }

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

    public int calcValueHeuristic() {
        int value = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8 ; j++) {
                int neighbor = 0;
                // has left
                if ((j - 1 >= 0) && (this.mat[i][j - 1] != 0)) {
                    neighbor++;
                }

                // has right
                if ((j + 1 <= 7) && (this.mat[i][j + 1] != 0)) {
                    neighbor++;
                }

                // has top
                if ((i - 1 >= 0) && (this.mat[i - 1][j] != 0)) {
                    neighbor++;
                }

                // has bottom
                if ((i + 1 <= 7) && (this.mat[i + 1][j] != 0)) {
                    neighbor++;
                }

                if (neighbor == 0) {
                    value += noNeighbor * mat[i][j];
                } else if (neighbor == 1) {
                    value += oneNeighbor * mat[i][j];
                } else if (neighbor == 2) {
                    value += twoNeighbor * mat[i][j];
                } else if (neighbor == 3) {
                    value += threeNeighbor * mat[i][j];
                } else {
                    value += fourNeighbor * mat[i][j];
                }
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
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8 ; j++) {
                if (mat[i][j] == 0) {
                    actions.add(new Action(i, j));
                }
            }
        }
        return actions;
    }

    public void reverseBoard() {
        this.isOpponentTurn = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4 ; j++) {
                if (mat[i][j] == 1) {
                    mat[i][j] = -1;
                } else if (mat[i][j] == -1) {
                    mat[i][j] = 1;
                }
            }
        }
    }

    int calcAbsoluteValue() {
        int value = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8 ; j++) {
                if (mat[i][j] == 1) {
                    value += 1;
                }
            }
        }
        return value;
    }
}
