package com.sandip.model;

import java.io.Serializable;

/**
 * Created by Admin on 05-02-2017.
 */

public class ModelLevel implements Serializable{

    String gameLevel,letterSize;
    boolean clickable=false;

    public boolean isClickable() {
        return clickable;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }

    public String getGameLevel() {
        return gameLevel;
    }

    public String getLetterSize() {
        return letterSize;
    }

    public void setLetterSize(String letterSize) {
        this.letterSize = letterSize;
    }

    public void setGameLevel(String gameLevel) {
        this.gameLevel = gameLevel;
    }
}
