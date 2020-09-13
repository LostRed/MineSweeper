package com.lostred.ms.thread;

import com.lostred.ms.view.MainFrame;

import java.util.TimerTask;

/**
 * 定时器任务，按照时间改变主窗口中的时间计数器
 */
public class GameTimerTask extends TimerTask {
    /**
     * 该时间任务所属的MainFrame
     */
    private final MainFrame owner;

    /**
     * 构建时间任务，初始化其所属的对象
     *
     * @param owner 该时间任务所属的MainFrame
     */
    public GameTimerTask(MainFrame owner) {
        this.owner = owner;
    }

    @Override
    public void run() {
        //获取所属主窗口的当前时间文本
        String currentTime = owner.getTimePanel().getText();
        //时间加一秒
        currentTime = Integer.toString(Integer.parseInt(currentTime) + 1);
        //重新设置所属主窗口的时间文本
        owner.getTimePanel().setText(currentTime);
    }
}
