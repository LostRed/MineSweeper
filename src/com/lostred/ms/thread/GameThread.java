package com.lostred.ms.thread;

import com.lostred.ms.service.Service;
import com.lostred.ms.util.Difficulty;
import com.lostred.ms.util.Records;
import com.lostred.ms.view.MainFrame;
import com.lostred.ms.view.ScoreDialog;

import java.util.Objects;

/**
 * 游戏进程
 */
public class GameThread extends Thread {
    /**
     * 游戏进程创建的MainFrame
     */
    private final MainFrame mf;
    /**
     * 游戏进程创建的Service
     */
    private final Service ser;

    /**
     * 构建游戏进程，并进行初始化设置
     */
    public GameThread() {
        this.mf = new MainFrame();
        ScoreDialog sd = new ScoreDialog(mf);
        this.ser = new Service(mf, sd);
        this.mf.setSer(ser);
    }

    @Override
    public void run() {
        mf.setDifficulty(Enum.valueOf(Difficulty.class, Objects.requireNonNull(Records.readPropertiesValue("difficulty"))));
        mf.setVisible(true);
        mf.generateButtons();
        ser.generateMine();
        mf.init();
    }
}
