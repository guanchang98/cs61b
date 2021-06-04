package creatures;

import edu.princeton.cs.algs4.StdRandom;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;
    /**
     * lose units energy on action
     */
    private double loseEnergyOnAction = 0.15;
    /**
     * gain units energy on stay
     */
    private double gainEnergyOnStay = 0.2;
    /**
     * energy retained after reproduction
     */
    private double repEnergyRetained = 0.5;
    /**
     * energy given to spring
     */
    private double repEnergyGiven = 0.5;
    /**
     * probability of moving
     */
    private double moveProbability = 0.5;

    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        super("plip");
        r = 99;
        g = 63;
        b = 76;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        g = 63 + (int) (96 * energy);
        return color(r, g, b);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        // TODO
        energy -= loseEnergyOnAction;
        energy = Math.max(0, energy);
        energy = Math.min(2, energy);
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        // TODO
        energy += gainEnergyOnStay;
        energy = Math.max(0, energy);
        energy = Math.min(2, energy);
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        double springEnergy = energy * repEnergyGiven;
        energy = energy * repEnergyRetained;
        return new Plip(springEnergy);
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        List<Direction> emptyNeighbors = new ArrayList<>();
        boolean anyClorus = false;
        // TODO
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}
        for (Direction key : neighbors.keySet()) {
            if (neighbors.get(key).name().equals("empty")) {
                emptyNeighbors.add(key);
            }
            if (neighbors.get(key).name().equals("clorus")) {
                anyClorus = true;
            }
        }
        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }
        /*if (!neighbors.get(Direction.TOP).name().equals("empty") &&
                !neighbors.get(Direction.BOTTOM).name().equals("empty") &&
                !neighbors.get(Direction.RIGHT).name().equals("empty") &&
                !neighbors.get(Direction.LEFT).name().equals("empty")) {
            return new Action(Action.ActionType.STAY);
        }*/
        /*if (false) { // FIXME
            // TODO
        }*/

        // Rule 2
        // HINT: randomEntry(emptyNeighbors)

        if (energy >= 1.0) {
            int size = emptyNeighbors.size();
            int index = StdRandom.uniform(size);
            Direction d = emptyNeighbors.get(index);
            return new Action(Action.ActionType.REPLICATE, d);
        }

        // Rule 3
        if (anyClorus) {
            int size = emptyNeighbors.size();
            int index = StdRandom.uniform(size);
            Direction d = emptyNeighbors.get(index);
            if (Math.random() < moveProbability) {
                return new Action(Action.ActionType.MOVE, d);
            }
        }
        // Rule 4
        return new Action(Action.ActionType.STAY);
    }
}
