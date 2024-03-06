package game.scratchgame;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ScratchGame {

	public static void main(String[] args) {
        try {
            String configFilePath = "config.json";
            GameConfig gameConfig = GameConfigParser.parse(configFilePath);
            int bettingAmount = 100; // Assuming a fixed betting amount for simplicity

            // Generate the game matrix
            GameMatrix gameMatrix = GameMatrixGenerator.generate(gameConfig);

            // Check for winning combinations
            WinningCombinationChecker checker = new WinningCombinationChecker(gameConfig);
            Map<String, List<String>> appliedWinningCombinations = checker.checkWinningCombinations(gameMatrix, gameConfig);	

            // Apply bonuses
            BonusApplier bonusApplier = new BonusApplier(gameConfig);
            String appliedBonusSymbol = bonusApplier.applyBonus(gameMatrix, gameConfig, appliedWinningCombinations, bettingAmount);

            // Calculate final reward
            RewardCalculator calculator = new RewardCalculator(gameConfig);
            int reward = calculator.calculateReward(bettingAmount, gameConfig.getSymbols(), appliedWinningCombinations, appliedBonusSymbol);

            // Output the result
            OutputFormatter formatter = new OutputFormatter();
            String output = formatter.format(gameMatrix, reward, appliedWinningCombinations, appliedBonusSymbol);
            System.out.println(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
