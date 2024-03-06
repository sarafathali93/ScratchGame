package game.scratchgame;

public class Symbol {

	private final String name;
    private final double rewardMultiplier;
    private final SymbolType type;
    private int extra = 0;
    private String impact;

    public Symbol(String name, double rewardMultiplier, SymbolType type) {
        this.name = name;
        this.rewardMultiplier = rewardMultiplier;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public double getRewardMultiplier() {
        return rewardMultiplier;
    }

    public SymbolType getType() {
        return type;
    }

    public int getExtra() {
        return extra;
    }

    public void setExtra(int extra) {
        this.extra = extra;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }
}

