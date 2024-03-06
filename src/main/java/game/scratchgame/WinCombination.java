package game.scratchgame;

import java.util.List;

public class WinCombination {

	private final String name;
    private final double rewardMultiplier;
    private final WinConditionType winConditionType;
    private final int count;
    private final String group;
    private final List<List<String>> coveredAreas;

    public WinCombination(String name, double rewardMultiplier, WinConditionType winConditionType, int count, String group, List<List<String>> coveredAreas) {
        this.name = name;
        this.rewardMultiplier = rewardMultiplier;
        this.winConditionType = winConditionType;
        this.count = count;
        this.group = group;
        this.coveredAreas = coveredAreas;
    }

    public String getName() {
        return name;
    }

    public double getRewardMultiplier() {
        return rewardMultiplier;
    }

    public WinConditionType getWinConditionType() {
        return winConditionType;
    }

    public int getCount() {
        return count;
    }

    public String getGroup() {
        return group;
    }

    public List<List<String>> getCoveredAreas() {
        return coveredAreas;
    }
}
