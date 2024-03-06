package game.scratchgame;

import java.util.Map;

public class StandardSymbolProbability {

	private final int column;
    private final int row;
    private final Map<String, Integer> symbols;

    public StandardSymbolProbability(int column, int row, Map<String, Integer> symbols) {
        this.column = column;
        this.row = row;
        this.symbols = symbols;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Map<String, Integer> getSymbols() {
        return symbols;
    }}
