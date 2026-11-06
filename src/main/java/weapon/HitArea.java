package weapon;

public enum HitArea {
    HEAD(3),
    TORSO(2),
    LEGS(1);

    private final int multiplier;

    HitArea(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMultiplier() {
        return multiplier;
    }
}


