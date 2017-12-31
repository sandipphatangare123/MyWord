package com.sandip.model;

import java.io.Serializable;


public class ModelSetting implements Serializable{

    String gameSettingTitle,settingName;

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getGameSettingTitle() {
        return gameSettingTitle;
    }

    public void setGameSettingTitle(String gameSettingTitle) {
        this.gameSettingTitle = gameSettingTitle;
    }
}
