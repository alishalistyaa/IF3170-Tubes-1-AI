public class BotFactory {
    public Bot createBot(int mode) {
        return switch (mode) {
            case 0 -> new SABot();
            case 1 -> new GABot();
            default -> new MMABBot();
        };
    }
}
