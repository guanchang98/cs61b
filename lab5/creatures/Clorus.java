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

public class Clorus extends Creature {
    private int r;
    private int g;
    private int b;
    private double loseEnergyOnAction = 0.03;
    private double loseEnergyOnStay = 0.01;
    private double repEnergyRetained = 0.5;
    private double repEnergyGiven = 0.5;

    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    public Color color() {
        return color(r, g, b);
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    public void move() {
        // TODO
        energy -= loseEnergyOnAction;
    }


    public void stay() {
        // TODO
        energy -= loseEnergyOnStay;
    }

    public Clorus replicate() {
        double springEnergy = energy * repEnergyGiven;
        energy = energy * repEnergyRetained;
        return new Clorus(springEnergy);
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        List<Direction> emptyNeighbors = new ArrayList<>();
        List<Direction> plipNeighbors = new ArrayList<>();
        boolean anyPlip = false;
        for (Direction key : neighbors.keySet()) {
            if (neighbors.get(key).name().equals("empty")) {
                emptyNeighbors.add(key);
            }
            if (neighbors.get(key).name().equals("plip")) {
                anyPlip = true;
                plipNeighbors.add(key);
            }
        }
        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        if (anyPlip) {
            int size = plipNeighbors.size();
            int index = StdRandom.uniform(size);
            Direction d = plipNeighbors.get(index);
            return new Action(Action.ActionType.ATTACK, d);
        }

        // Rule 3
        if (energy >= 1.0) {
            int size = emptyNeighbors.size();
            int index = StdRandom.uniform(size);
            Direction d = emptyNeighbors.get(index);
            return new Action(Action.ActionType.REPLICATE, d);
        }
        // Rule 4

        int size = emptyNeighbors.size();
        int index = StdRandom.uniform(size);
        Direction d = emptyNeighbors.get(index);
        return new Action(Action.ActionType.MOVE, d);
    }

}
