package game.scratchgame;

import java.util.Map;

public class GameMatrixGenerator {

	public static GameMatrix generate(GameConfig gameConfig) {
        // Implement game matrix generation logic based on the provided configuration
        // For simplicity, let's assume we generate a random matrix of symbols
        int columns = gameConfig.getColumns();
        int rows = gameConfig.getRows();
        String[][] matrix = new String[rows][columns];
        Map<String, Symbol> symbols = gameConfig.getSymbols();
        symbols.keySet().forEach(symbolName -> {
            Symbol symbol = symbols.get(symbolName);
            if (symbol.getType() == SymbolType.STANDARD) {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        if (matrix[i][j] == null) {
                            matrix[i][j] = symbolName;
                            break;
                        }
                    }
                }
            }
        });
        return new GameMatrix(matrix);
    }
}
