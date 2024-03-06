package game.scratchgame;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OutputFormatter {

	public String format(GameMatrix gameMatrix, int reward, Map<String, List<String>> appliedWinningCombinations, String appliedBonusSymbol) {
        // Implement output formatting logic based on the game result
        // For simplicity, let's assume we format the output as a JSON string
        return "{\"matrix\": " + Arrays.deepToString(gameMatrix.getMatrix()) + ", \"reward\": " + reward + ", \"applied_winning_combinations\": " + appliedWinningCombinations + ", \"applied_bonus_symbol\": \"" + appliedBonusSymbol + "\"}";
    }
}
