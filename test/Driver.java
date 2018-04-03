import com.valkryst.VDice.DiceRoller;

public class Driver {
    public static void main(final String[] args) {
        final DiceRoller roller = new DiceRoller();
        roller.addDie(6);
        roller.addDie(3);
        roller.addDie(-7);

        for (int i = 0 ; i < 100 ; i++) {
            System.out.println(roller.roll());
        }
    }
}
