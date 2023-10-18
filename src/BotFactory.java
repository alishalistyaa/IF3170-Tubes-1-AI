public class BotFactory {
    public Bot createBot(String mode) {
        if (mode.startsWith("S")) {
            return new SABot();
        } else if (mode.startsWith("G")) {
            return new SimulBot();
        } else {
            return new MMABBot();
        }
    }
}
