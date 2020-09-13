package com.lostred.ms.service;

import com.lostred.ms.model.MineButton;
import com.lostred.ms.util.GameIcon;
import com.lostred.ms.util.Records;
import com.lostred.ms.view.GameOverDialog;
import com.lostred.ms.view.MainFrame;
import com.lostred.ms.view.ScoreDialog;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * 后台服务：控制游戏的开始和结束，实现自动打开安全按钮的方法
 */
public class Service {
    /**
     * 控制的MainFrame
     */
    private final MainFrame mf;
    /**
     * 控制的ScoreDialog
     */
    private final ScoreDialog sd;

    /**
     * 构建后台，初始化其所属
     *
     * @param mf 该后台服务控制的MainFrame
     * @param sd 该后台服务控制的ScoreDialog
     */
    public Service(MainFrame mf, ScoreDialog sd) {
        this.mf = mf;
        this.sd = sd;
    }

    /**
     * 按照主窗口的行、列及地雷数量随机生成地雷
     */
    public void generateMine() {
        int row = mf.getRow();
        int col = mf.getCol();
        int numOfMine = mf.getNumOfMine();
        Random rand = new Random();
        do {
            int x = rand.nextInt(row);
            int y = rand.nextInt(col);
            if (!mf.getMineButtons()[x][y].isMine()) {
                mf.getMineButtons()[x][y].setMine(true);
                numOfMine--;
            }
        } while (numOfMine > 0);
    }

    /**
     * 遍历某个按钮周围的所有按钮，返回一个它们的集合
     *
     * @param mb 某个MineButton
     * @return 某个MineButton周围一圈MineButton的集合
     */
    public ArrayList<MineButton> around(MineButton mb) {
        ArrayList<MineButton> list = new ArrayList<>();
        int x = mb.getRow();
        int y = mb.getCol();
        for (int i = -1; i <= 1; i++) {
            if (x + i < 0 || x + i >= mf.getRow()) {
                continue;
            }
            for (int j = -1; j <= 1; j++) {
                if (y + j < 0 || y + j >= mf.getCol()) {
                    continue;
                }
                list.add(mf.getMineButtons()[x + i][y + j]);
            }
        }
        return list;
    }

    /**
     * 确定某个按钮周围的地雷数量
     *
     * @param mb 某个MineButton
     * @return 某个MineButton周围的地雷数量
     */
    public int aroundMine(MineButton mb) {
        int aroundMine = 0;
        for (MineButton b : around(mb)) {
            if (b.isMine()) {
                aroundMine++;
            }
        }
        return aroundMine;
    }

    /**
     * 确定某个按钮周围的旗子数量
     *
     * @param mb 某个MineButton
     * @return 某个MineButton周围的旗子数量
     */
    public int aroundFlag(MineButton mb) {
        int aroundFlag = 0;
        for (MineButton b : around(mb)) {
            if (b.getTab() == MineButton.Tab.FLAG) {
                aroundFlag++;
            }
        }
        return aroundFlag;
    }

    /**
     * 打开某个按钮
     *
     * @param mb 某个MineButton
     */
    public void openButton(MineButton mb) {
        mb.setEnabled(false);
        if (mb.isMine()) {
            mb.setDisabledIcon(GameIcon.MINE_CLICKED);
        } else {
            switch (aroundMine(mb)) {
                case 1:
                    mb.setDisabledIcon(GameIcon.BUTTON_1);
                    break;
                case 2:
                    mb.setDisabledIcon(GameIcon.BUTTON_2);
                    break;
                case 3:
                    mb.setDisabledIcon(GameIcon.BUTTON_3);
                    break;
                case 4:
                    mb.setDisabledIcon(GameIcon.BUTTON_4);
                    break;
                case 5:
                    mb.setDisabledIcon(GameIcon.BUTTON_5);
                    break;
                case 6:
                    mb.setDisabledIcon(GameIcon.BUTTON_6);
                    break;
                case 7:
                    mb.setDisabledIcon(GameIcon.BUTTON_7);
                    break;
                case 8:
                    mb.setDisabledIcon(GameIcon.BUTTON_8);
                    break;
            }
        }
        if (aroundMine(mb) == 0) {
            openAroundButtons(mb);
        }
    }

    /**
     * 自动打开按钮某个按钮周围未标记为旗子的按钮
     *
     * @param mb 某个MineButton
     */
    public void openAroundButtons(MineButton mb) {
        for (MineButton b : around(mb)) {
            if (b.isEnabled() && b.getTab() != MineButton.Tab.FLAG) {
                openButton(b);
            }
        }
    }

    /**
     * 切换标签，无、旗子、问号循环切换，并改变所在的MainFrame的地雷计数器
     */
    public void changeButtonTab(MineButton mb) {
        String remainder = mf.getMinePanel().getText();
        switch (mb.getTab()) {
            case NONE:
                mb.setTab(MineButton.Tab.FLAG);
                mb.setIcon(GameIcon.FLAG);
                remainder = Integer.toString(Integer.parseInt(remainder) - 1);
                break;
            case FLAG:
                mb.setTab(MineButton.Tab.QUESTION);
                mb.setIcon(GameIcon.QUESTION);
                remainder = Integer.toString(Integer.parseInt(remainder) + 1);
                break;
            case QUESTION:
                mb.setTab(MineButton.Tab.NONE);
                mb.setIcon(GameIcon.BUTTON_COMMON);
                break;
        }
        mf.getMinePanel().setText(remainder);
        mf.getMinePanel().repaint();
    }

    /**
     * 检查游戏是否结束
     */
    public void checkResult() {
        int remainButton = 0;
        int remainMine = Integer.parseInt(mf.getMinePanel().getText());
        for (int i = 0; i < mf.getRow(); i++) {
            for (int j = 0; j < mf.getCol(); j++) {
                if (mf.getMineButtons()[i][j].isEnabled() && mf.getMineButtons()[i][j].getTab() != MineButton.Tab.FLAG) {
                    remainButton++;
                } else if (mf.getMineButtons()[i][j].isMine() && !mf.getMineButtons()[i][j].isEnabled()) {
                    lose();
                    return;
                }
            }
        }
        if (remainButton == remainMine) {
            win();
        }
    }

    /**
     * 游戏失败
     */
    public void lose() {
        loseShow();
        mf.getGameTimerTask().cancel();
        mf.getTimer().cancel();
        int time = Integer.parseInt(mf.getTimePanel().getText());
        GameOverDialog god = new GameOverDialog(mf, false, Integer.toString(time));
        god.setVisible(true);
    }

    /**
     * 游戏胜利
     */
    public void win() {
        winShow();
        mf.getGameTimerTask().cancel();
        mf.getTimer().cancel();
        int time = Integer.parseInt(mf.getTimePanel().getText());
        record(time);
        GameOverDialog god = new GameOverDialog(mf, true, Integer.toString(time));
        god.setVisible(true);
    }

    /**
     * 与配置文件中的时间比较，若当前time更小，则写入配置文件
     *
     * @param time 游戏结束时计时器的秒数
     */
    public void record(int time) {
        int highestScore;
        switch (mf.getDifficulty()) {
            case BEGINNER:
                if (Records.readPropertiesValue("beginnerTime") == null) {
                    highestScore = 0;
                } else {
                    highestScore = Integer.parseInt(Objects.requireNonNull(Records.readPropertiesValue("beginnerTime")));
                }
                if (time < highestScore || highestScore == 0) {
                    Records.writeProperties("beginnerTime", Integer.toString(time));
                    sd.getLblTime1().setText(time + " s");
                }
                break;
            case INTERMEDIATE:
                if (Records.readPropertiesValue("intermediateTime") == null) {
                    highestScore = 0;
                } else {
                    highestScore = Integer.parseInt(Objects.requireNonNull(Records.readPropertiesValue("intermediateTime")));
                }
                if (time < highestScore || highestScore == 0) {
                    Records.writeProperties("intermediateTime", Integer.toString(time));
                    sd.getLblTime2().setText(time + " s");
                }
                break;
            case ADVANCED:
                if (Records.readPropertiesValue("advancedTime") == null) {
                    highestScore = 0;
                } else {
                    highestScore = Integer.parseInt(Objects.requireNonNull(Records.readPropertiesValue("advancedTime")));
                }
                if (time < highestScore || highestScore == 0) {
                    Records.writeProperties("advancedTime", Integer.toString(time));
                    sd.getLblTime3().setText(time + " s");
                }
                break;
        }
    }

    /**
     * 失败的情况下显示所有按钮
     */
    public void loseShow() {
        int missMine = 0;
        int remainder = Integer.parseInt(mf.getMinePanel().getText());
        for (int i = 0; i < mf.getRow(); i++) {
            for (int j = 0; j < mf.getCol(); j++) {
                if (mf.getMineButtons()[i][j].isMine() && mf.getMineButtons()[i][j].getTab() != MineButton.Tab.FLAG && mf.getMineButtons()[i][j].isEnabled()) {
                    mf.getMineButtons()[i][j].setDisabledIcon(GameIcon.MINE);
                    mf.getMineButtons()[i][j].setEnabled(false);
                } else if (!mf.getMineButtons()[i][j].isMine() && mf.getMineButtons()[i][j].getTab() == MineButton.Tab.FLAG) {
                    missMine++;
                    mf.getMineButtons()[i][j].setDisabledIcon(GameIcon.MINE_MISS);
                    mf.getMineButtons()[i][j].setEnabled(false);
                } else if (!mf.getMineButtons()[i][j].isMine() && mf.getMineButtons()[i][j].getTab() != MineButton.Tab.FLAG) {
                    mf.getMineButtons()[i][j].setEnabled(false);
                }
            }
        }
        //剩余地雷数加上错误标记，显示在主窗口上
        mf.getMinePanel().setText(Integer.toString(remainder + missMine));
    }

    /**
     * 成功的情况下显示显示所有按钮
     */
    public void winShow() {
        for (int i = 0; i < mf.getRow(); i++) {
            for (int j = 0; j < mf.getCol(); j++) {
                if (mf.getMineButtons()[i][j].isMine()) {
                    mf.getMineButtons()[i][j].setIcon(GameIcon.FLAG);
                }
            }
        }
        mf.getMinePanel().setText("0");
    }
}
