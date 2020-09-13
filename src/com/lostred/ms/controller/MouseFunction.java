package com.lostred.ms.controller;

import com.lostred.ms.model.MineButton;

/**
 * 鼠标对按钮操作的功能
 */
public interface MouseFunction {
    /**
     * 鼠标左键点击按钮后释放
     *
     * @param mb 鼠标操作的MineButton
     */
    void leftReleased(MineButton mb);

    /**
     * 鼠标右键点击按钮后释放
     *
     * @param mb 鼠标操作的MineButton
     */
    void RightReleased(MineButton mb);

    /**
     * 鼠标左右键同时按下按钮
     *
     * @param mb 鼠标操作的MineButton
     */
    void bothPressed(MineButton mb);

    /**
     * 鼠标左右键同时按下按钮后释放
     *
     * @param mb 鼠标操作的MineButton
     */
    void bothReleased(MineButton mb);

    /**
     * 改变被按下按钮周围的按钮图标
     *
     * @param mb 鼠标操作的MineButton
     */
    void changeButtonIcon(MineButton mb);

    /**
     * 复原被按下按钮周围的按钮图标
     *
     * @param mb 鼠标操作的MineButton
     */
    void restoreButtonIcon(MineButton mb);
}
