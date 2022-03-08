import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum RuleSet {
    CLASSIC(0),
    CITIES(1),
    CORAL(2),
    DIAMOEBA(3),
    DAYNIGHT(4),
    SEEDS(5),
    SNOWFLAKE(6);


    final int ruleIndex;

    RuleSet(int ruleIndex) {
        this.ruleIndex = ruleIndex;
    }

    public static RuleSet getRule(int index) {
        return RuleSet.values()[index];
    }


    public List<Integer> getRuleForSurvival() {
        List<Integer> rule = new ArrayList<>();

        switch (this) {
            case CLASSIC -> rule.addAll(Arrays.asList(2, 3));
            case CORAL -> rule.addAll(Arrays.asList(4, 5, 6, 7, 8));
            case CITIES -> rule.addAll(Arrays.asList(2, 3, 4, 5));
            case DIAMOEBA -> rule.addAll(Arrays.asList(5, 6, 7, 8));
            case DAYNIGHT -> rule.addAll(Arrays.asList(3, 4, 6, 7, 8));
            case SEEDS -> rule.clear();
            case SNOWFLAKE -> rule.addAll(Arrays.asList(3, 4, 5, 6));
        }

        return rule;
    }

    public List<Integer> getRuleForBirth() {
        List<Integer> rule = new ArrayList<>();

        switch (this) {
            case CLASSIC, CORAL -> rule.add(3);
            case CITIES -> rule.addAll(Arrays.asList(4, 5, 6, 7, 8));
            case DIAMOEBA -> rule.addAll(Arrays.asList(3, 5, 6, 7, 8));
            case DAYNIGHT -> rule.addAll(Arrays.asList(3, 6, 7, 8));
            case SEEDS, SNOWFLAKE -> rule.add(2);

        }

        return rule;
    }


}
