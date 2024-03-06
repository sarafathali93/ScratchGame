package game.scratchgame;

import java.util.List;
import java.util.Map;

public class RewardCalculator {

	private final GameConfig gameConfig;

    public RewardCalculator(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    public static int calculateReward(int betAmount, Map<String, Symbol> symbols, Map<String, List<String>> appliedWinningCombinations, String appliedBonusSymbol) {
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

        // Apply bonus symbol multiplier if applicable
        if (appliedBonusSymbol != null) {
            Symbol bonusSymbol = symbols.get(appliedBonusSymbol);
            if (bonusSymbol != null && bonusSymbol.getType() == SymbolType.BONUS) {
                totalRewardMultiplier *= bonusSymbol.getRewardMultiplier();
            }
        }

        // Calculate final reward
        int finalReward = (int) (betAmount * totalRewardMultiplier);

        return finalReward;
    }
}
