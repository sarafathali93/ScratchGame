package game.scratchgame;

import java.util.List;
import java.util.Map;

public class GameConfig {

	private final int columns;
    private final int rows;
    private final Map<String, Symbol> symbols;
    private final Map<String, Symbol> bonusSymbols;
    private final Map<String, Symbol> standardSymbols;
    private final List<StandardSymbolProbability> standardSymbolsProbabilities;
    private final Map<String, Integer> bonusSymbolProbabilities;
    private final Map<String, WinCombination> winCombinations;

    public GameConfig(int columns, int rows, Map<String, Symbol> symbols, Map<String, Symbol> bonusSymbols, Map<String, Symbol> standardSymbols, List<StandardSymbolProbability> standardSymbolsProbabilities, Map<String, Integer> bonusSymbolProbabilities, Map<String, WinCombination> winCombinations) {
        this.columns = columns;
        this.rows = rows;
        this.symbols = symbols;
        this.bonusSymbols = bonusSymbols;
        this.standardSymbols = standardSymbols;
        this.standardSymbolsProbabilities = standardSymbolsProbabilities;
        this.bonusSymbolProbabilities = bonusSymbolProbabilities;
        this.winCombinations = winCombinations;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }

    public List<StandardSymbolProbability> getStandardSymbolsProbabilities() {
        return standardSymbolsProbabilities;
    }

    public Map<String, Integer> getBonusSymbolProbabilities() {
        return bonusSymbolProbabilities;
    }

    public Map<String, WinCombination> getWinCombinations() {
        return winCombinations;
    }

	public Map<String, Symbol> getBonusSymbols() {
		return bonusSymbols;
	}

	public Map<String, Symbol> getStandardSymbols() {
		return standardSymbols;
	}

}
