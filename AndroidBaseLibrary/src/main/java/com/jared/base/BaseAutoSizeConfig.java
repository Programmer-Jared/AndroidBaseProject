package com.jared.base;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;

/**
 * @ClassName BaseAutoSizeConfig
 * @Author Jared
 * @Date 2022/3/3 14:12
 * @Version 1.0
 * @Description
 */
public class BaseAutoSizeConfig {
   private int designWidth;

    private int designHeight;

    private boolean isBaseOnWidth=true;

    // TODO: 2022/3/3
    private boolean openCustomFragment=true;
    // TODO: 2022/3/3
    Subunits supportSubunits=Subunits.NONE;

    public BaseAutoSizeConfig(int designWidth, int designHeight, boolean isBaseOnWidth) {
        this.designWidth = designWidth;
        this.designHeight = designHeight;
        this.isBaseOnWidth = isBaseOnWidth;
    }

    public int getDesignWidth() {
        return designWidth;
    }

    public void setDesignWidth(int designWidth) {
        this.designWidth = designWidth;
    }

    public int getDesignHeight() {
        return designHeight;
    }

    public void setDesignHeight(int designHeight) {
        this.designHeight = designHeight;
    }

    public boolean isBaseOnWidth() {
        return isBaseOnWidth;
    }

    public void setBaseOnWidth(boolean baseOnWidth) {
        isBaseOnWidth = baseOnWidth;
    }

    public boolean isOpenCustomFragment() {
        return openCustomFragment;
    }

    public void setOpenCustomFragment(boolean openCustomFragment) {
        this.openCustomFragment = openCustomFragment;
    }

    public Subunits getSupportSubunits() {
        return supportSubunits;
    }

    public void setSupportSubunits(Subunits supportSubunits) {
        this.supportSubunits = supportSubunits;
    }
}
