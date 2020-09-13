package com.lostred.ms.controller;

import com.lostred.ms.model.MineButton;
import com.lostred.ms.util.GameIcon;
import com.lostred.ms.view.MainFrame;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 鼠标适配器，控制游戏按钮
 */
public class MouseHandler extends MouseAdapter implements MouseFunction {
    /**
     * 该鼠标适配器控制的MainFrame
     */
    private final MainFrame mf;
    /**
     * 鼠标左右键是否同时按下
     */
    private boolean bothPressed;

    /**
     * 构建游戏按钮的鼠标监听器，初始化其控制的MainFrame
     *
     * @param mf 该鼠标适配器控制的MainFrame
     */
    public MouseHandler(MainFrame mf) {
        this.mf = mf;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //获取鼠标点击的按钮
        MineButton mb = (MineButton) e.getComponent();
        //鼠标左右键同时点击
        if (e.getModifiersEx() == (InputEvent.BUTTON1_DOWN_MASK + InputEvent.BUTTON3_DOWN_MASK) || e.getButton() == MouseEvent.BUTTON2) {
            bothPressed = true;
            if (!mb.isEnabled()) {
                bothPressed(mb);
            }
        } else {
            bothPressed = false;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //获取鼠标点击的按钮
        MineButton mb = (MineButton) e.getComponent();
        if (mb != mf.getMouseStayButton()) {
            restoreButtonIcon(mb);
            return;
        }
        if (!mb.isEnabled() && bothPressed) {
            bothReleased(mb);
        } else if (mb.isEnabled()) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                leftReleased(mb);
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                RightReleased(mb);
            }
        }
        //检查游戏是否结束
        mf.getSer().checkResult();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        MineButton mb = (MineButton) e.getComponent();
        mf.setMouseStayButton(mb);
        if (mb.isEnabled() && mb.getTab() == MineButton.Tab.NONE && mb.getIcon() != GameIcon.BUTTON_PRESSED) {
            //按钮高亮显示
            mb.setIcon(GameIcon.BUTTON_HIGHLIGHT);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        MineButton mb = (MineButton) e.getComponent();
        if (mb.isEnabled() && mb.getTab() == MineButton.Tab.NONE && mb.getIcon() != GameIcon.BUTTON_PRESSED) {
            //按钮恢复正常
            mb.setIcon(GameIcon.BUTTON_COMMON);
        }
    }

    @Override
    public void leftReleased(MineButton mb) {
        //第一次点击鼠标，计时器开始工作
        if (!mf.isExistSchedule()) {
            mf.getTimer().schedule(mf.getGameTimerTask(), 1000, 1000);
            mf.setExistSchedule(true);
        }
        mf.getSer().openButton(mb);
    }

    @Override
    public void RightReleased(MineButton mb) {
        //第一次点击鼠标，计时器开始工作
        if (!mf.isExistSchedule()) {
            mf.getTimer().schedule(mf.getGameTimerTask(), 1000, 1000);
            mf.setExistSchedule(true);
        }
        mf.getSer().changeButtonTab(mb);
    }

    @Override
    public void bothPressed(MineButton mb) {
        changeButtonIcon(mb);
    }

    @Override
    public void bothReleased(MineButton mb) {
        if (mf.getSer().aroundFlag(mb) == mf.getSer().aroundMine(mb)) {
            mf.getSer().openAroundButtons(mb);
        } else {
            restoreButtonIcon(mb);
        }
    }

    @Override
    public void changeButtonIcon(MineButton mb) {
        for (MineButton b : mf.getSer().around(mb)) {
            if (b.isEnabled() && b.getTab() != MineButton.Tab.FLAG) {
                b.setIcon(GameIcon.BUTTON_PRESSED);
            }
        }
    }

    @Override
    public void restoreButtonIcon(MineButton mb) {
        for (MineButton b : mf.getSer().around(mb)) {
            if (b.isEnabled() && b.getTab() != MineButton.Tab.FLAG) {
                b.setIcon(GameIcon.BUTTON_COMMON);
            }
        }
    }
}
