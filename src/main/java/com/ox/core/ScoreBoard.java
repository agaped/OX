package com.ox.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ScoreBoard {

    Map<Player, Integer> points;

    public ScoreBoard() {
        this.points = new HashMap<>();
    }

    public void givePoints(Player player, int points) {
        if (this.points.containsKey(player)) {
            this.points.put(player, this.points.get(player) + points);
        } else {
            this.points.put(player, points);
        }
    }

    public Map<Player, Integer> getScores() {
        return Collections.unmodifiableMap(this.points);
    }
}
