public class BotFactory {
    public Bot createBot(String mode) {
        if (mode.startsWith("S")) {
            return new SABot();
        } else if (mode.endsWith("1")) {
            return new GABot();
        } else if (mode.endsWith("2")) {
            return new SimulBot();
        } else {
            return new MMABBot();
        }
    }
}
