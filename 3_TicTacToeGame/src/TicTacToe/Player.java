package TicTacToe;

public class Player {
    private final Symbol symbol;
    private final String name;

    public Player(Symbol symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }
}
