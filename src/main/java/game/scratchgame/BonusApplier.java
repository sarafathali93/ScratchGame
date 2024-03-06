package game.scratchgame;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BonusApplier {

	private final GameConfig gameConfig;

    public BonusApplier(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    public static String applyBonus(GameMatrix gameMatrix, GameConfig gameConfig, Map<String, List<String>> appliedWinningCombinations, int bettingAmount) {
        String appliedBonusSymbol = null;
        Map<String, Symbol> symbols = gameConfig.getSymbols();
        Map<String, Symbol> bonusSymbols = gameConfig.getBonusSymbols();
        double totalRewardMultiplier = 1.0;

        // Calculate total reward multiplier based on applied winning combinations
        for (Map.Entry<String, List<String>> entry : appliedWinningCombinations.entrySet()) {
            String symbolName = entry.getKey();
            List<String> combinations = entry.getValue();
            Symbol symbol = symbols.get(symbolName);
            if (symbol != null) {
                double symbolRewardMultiplier = symbol.getRewardMultiplier();
                for (String combination : combinations) {
                    totalRewardMultiplier *= symbolRewardMultiplier;
                }
            }
        }

        // Apply bonus symbols if applicable
        for (String[] row : gameMatrix.getMatrix()) {
            for (String symbolName : row) {
                if (bonusSymbols.containsKey(symbolName)) {
                    Symbol bonusSymbol = bonusSymbols.get(symbolName);
                    totalRewardMultiplier = applyBonusSymbol(bonusSymbol, totalRewardMultiplier);
                    appliedBonusSymbol = symbolName;
                }
            }
        }


        return appliedBonusSymbol;
    }

    private static double applyBonusSymbol(Symbol bonusSymbol, double totalRewardMultiplier) {
        switch (bonusSymbol.getImpact()) {
            case "multiply_reward":
                totalRewardMultiplier *= bonusSymbol.getRewardMultiplier();
                break;
            case "extra_bonus":
                totalRewardMultiplier += bonusSymbol.getExtra();
                break;
            case "miss":
                break; // Do nothing
        }
        return totalRewardMultiplier;
    }	
}
