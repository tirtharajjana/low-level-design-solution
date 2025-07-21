package SnakeLadder;

import java.util.concurrent.Delayed;

public class Player {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    String id;
    int currentPosition;

    public Player(String id, int currentPosition) {
        this.id = id;
        this.currentPosition = currentPosition;
    }
}
