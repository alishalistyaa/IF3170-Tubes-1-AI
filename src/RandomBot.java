import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.control.Button;
public class RandomBot extends Bot {
    public RandomBot(boolean isFirstTurn) {
        super(isFirstTurn);
    }

    @Override
    public Action move(State s) {
        // random move as kyk di class Bot, move() method
        return new Action((int) (Math.random()*8), (int) (Math.random()*8));
    }
}
