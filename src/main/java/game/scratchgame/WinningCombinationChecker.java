package game.scratchgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningCombinationChecker {

	private final GameConfig gameConfig;

    public WinningCombinationChecker(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    public static Map<String, List<String>> checkWinningCombinations(GameMatrix gameMatrix, GameConfig gameConfig) {
        Map<String, List<String>> appliedWinningCombinations = new HashMap<>();
        String[][] matrix = gameMatrix.getMatrix();
        Map<String, Symbol> symbols = gameConfig.getSymbols();
        Map<String, WinCombination> winCombinations = gameConfig.getWinCombinations();

        for (Map.Entry<String, WinCombination> entry : winCombinations.entrySet()) {
            String combinationName = entry.getKey();
            WinCombination winCombination = entry.getValue();
            List<String> matchingSymbols = new ArrayList<>();

            switch (winCombination.getWinConditionType()) {
                case SAME_SYMBOLS:
                    matchingSymbols = checkSameSymbolsCombination(matrix, winCombination.getCount());
                    break;
                case LINEAR_SYMBOLS:
                    matchingSymbols = checkLinearSymbolsCombination(matrix, winCombination.getCoveredAreas(), winCombination.getCount());
                    break;
            }

            if (!matchingSymbols.isEmpty()) {
                appliedWinningCombinations.put(combinationName, matchingSymbols);
            }
        }

        return appliedWinningCombinations;
    }

    private static List<String> checkSameSymbolsCombination(String[][] matrix, int count) {
        List<String> matchingSymbols = new ArrayList<>();
        for (String[] row : matrix) {
            for (String symbol : row) {
                if (symbol != null && countOccurrences(matrix, symbol) >= count && !matchingSymbols.contains(symbol)) {
                    matchingSymbols.add(symbol);
                }
            }
        }
        return matchingSymbols;
    }

    private static List<String> checkLinearSymbolsCombination(String[][] matrix, List<List<String>> coveredAreas, int count) {
        List<String> matchingSymbols = new ArrayList<>();
        for (List<String> area : coveredAreas) {
            int rowCount = matrix.length;
            int columnCount = matrix[0].length;
            int[][] visited = new int[rowCount][columnCount];

            for (String coordinate : area) {
                String[] coordinates = coordinate.split(":");
                int row = Integer.parseInt(coordinates[0]);
                int column = Integer.parseInt(coordinates[1]);
                String symbol = matrix[row][column];

                if (symbol != null && visited[row][column] == 0 && countOccurrencesInArea(matrix, area, symbol) >= count) {
                    matchingSymbols.add(symbol);
                    break;
                }
            }
        }
        return matchingSymbols;
    }

    private static int countOccurrences(String[][] matrix, String symbol) {
        int count = 0;
        for (String[] row : matrix) {
            for (String s : row) {
                if (symbol.equals(s)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int countOccurrencesInArea(String[][] matrix, List<String> area, String symbol) {
        int count = 0;
        for (String coordinate : area) {
            String[] coordinates = coordinate.split(":");
            int row = Integer.parseInt(coordinates[0]);
            int column = Integer.parseInt(coordinates[1]);
            if (symbol.equals(matrix[row][column])) {
                count++;
            }
        }
        return count;
    }
  }
