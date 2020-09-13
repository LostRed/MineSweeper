package com.lostred.ms.model;

import com.lostred.ms.util.GameIcon;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏按钮
 */
public class MineButton extends JButton {
    /**
     * 该按钮所在的行数
     */
    private final int row;
    /**
     * 该按钮所在的列数
     */
    private final int col;
    /**
     * 该按钮是否是地雷
     */
    private boolean isMine;
    /**
     * 该按钮的标签
     */
    private Tab tab;

    /**
     * 按钮标签，分为无、旗子和问号，用于右键标记
     */
    public enum Tab {
        NONE, FLAG, QUESTION
    }

    /**
     * 构建游戏按钮，初始化其所属对象
     *
     * @param row 该按钮所在的行数
     * @param col 该按钮所在的列数
     */
    public MineButton(int row, int col) {
        this.row = row;
        this.col = col;
        this.isMine = false;
        this.tab = Tab.NONE;
        this.setIcon(GameIcon.BUTTON_COMMON);
        this.setDisabledIcon(GameIcon.BUTTON_DISABLE);
        this.setPreferredSize(new Dimension(50, 50));
        this.setBorder(null);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public Tab getTab() {
        return tab;
    }

    public void setTab(Tab tab) {
        this.tab = tab;
    }
}
