package com.lostred.ms.view;

import com.lostred.ms.controller.MouseHandler;
import com.lostred.ms.model.MineButton;
import com.lostred.ms.service.Service;
import com.lostred.ms.util.GameIcon;
import com.lostred.ms.util.Difficulty;
import com.lostred.ms.thread.GameTimerTask;
import com.lostred.ms.util.GBC;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Timer;

/**
 * 游戏主窗口
 */
public class MainFrame extends JFrame {
    /**
     * 某难度下的按钮行数
     */
    private int row;
    /**
     * 某难度下的按钮列数
     */
    private int col;
    /**
     * 某难度下的地雷数量
     */
    private int numOfMine;
    /**
     * 游戏难度
     */
    private Difficulty difficulty;
    /**
     * 存放所有按钮的数组
     */
    private MineButton[][] mineButtons;
    /**
     * 定时器任务
     */
    private GameTimerTask gameTimerTask;
    /**
     * 定时器
     */
    private Timer timer;
    /**
     * 窗口总面板
     */
    private JPanel panel;
    /**
     * 容纳所有按钮的面板
     */
    private JPanel buttonPanel;
    /**
     * 计时器面板
     */
    private TextPanel timePanel;
    /**
     * 地雷计数器面板
     */
    private TextPanel minePanel;
    /**
     * 鼠标停留的按钮
     */
    private MineButton mouseStayButton;
    /**
     * 计时器是否有时间任务
     */
    private boolean isExistSchedule;
    /**
     * 依赖的后台服务
     */
    private Service ser;

    /**
     * 构建扫雷主窗口，初始化标题、标题栏、标题栏图标，并添加菜单栏及所有组件的面板，暂未添加按钮
     */
    public MainFrame() {
        //设置标题
        setTitle("MineSweeper");
        //设置标题栏图标
        setIconImage(GameIcon.TITLE.getImage());
        //设置默认的关闭方式
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //设置菜单栏
        setJMenuBar(new MainMenuBar(this));
        //构建组件
        JLabel lbTime = new JLabel();
        lbTime.setIcon(GameIcon.LABEL_TIME);
        timePanel = new TextPanel();
        JLabel lbBlank = new JLabel();
        minePanel = new TextPanel();
        JLabel lbMine = new JLabel();
        lbMine.setIcon(GameIcon.LABEL_MINE);
        buttonPanel = new JPanel();
        panel = new JPanel();
        //布局
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(60, 60, 30, 60));
        setLayout(new GridBagLayout());
        //添加组件
        panel.add(buttonPanel, new GBC(0, 0, 5, 1).setAnchor(GBC.CENTER).
                setInsets(0, 0, 30, 0).setWeight(0, 0));
        panel.add(lbTime, new GBC(0, 1).setInsets(5).setWeight(0, 0));
        panel.add(timePanel, new GBC(1, 1).setInsets(5).setWeight(0, 0));
        panel.add(lbBlank, new GBC(2, 1).setInsets(5).setWeight(100, 0));
        panel.add(minePanel, new GBC(3, 1).setInsets(5).setWeight(0, 0));
        panel.add(lbMine, new GBC(4, 1).setInsets(5).setWeight(0, 0));
        add(panel, new GBC(0, 0).setWeight(0, 0));
    }

    /**
     * 游戏初始化，重置所有计数器，重置按钮图形界面
     */
    public void init() {
        //重置计时器
        if (gameTimerTask != null) {
            gameTimerTask.cancel();
        }
        if (timer != null) {
            timer.cancel();
        }
        gameTimerTask = new GameTimerTask(this);
        timer = new Timer();
        timePanel.setText("0");
        //重置地雷计数器
        minePanel.setText(Integer.toString(numOfMine));
        //重置按钮图形
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mineButtons[i][j].setEnabled(true);
                mineButtons[i][j].setIcon(GameIcon.BUTTON_COMMON);
                mineButtons[i][j].setPressedIcon(GameIcon.BUTTON_PRESSED);
                mineButtons[i][j].setDisabledIcon(GameIcon.BUTTON_DISABLE);
                mineButtons[i][j].setTab(MineButton.Tab.NONE);
                mineButtons[i][j].repaint();
            }
        }
        //重置第一次点击
        isExistSchedule = false;
        panel.updateUI();
    }

    /**
     * 生成按钮，并重绘窗口图形
     */
    public void generateButtons() {
        //清除按钮
        buttonPanel.removeAll();
        //获取难度数据
        row = difficulty.getRow();
        col = difficulty.getCol();
        numOfMine = difficulty.getNumOfMine();
        //重构按钮布局
        buttonPanel.setLayout(new GridLayout(row, col, 1, 1));
        //添加按钮
        mineButtons = new MineButton[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mineButtons[i][j] = new MineButton(i, j);
                mineButtons[i][j].addMouseListener(new MouseHandler(this));
                buttonPanel.add(mineButtons[i][j]);
            }
        }
        //取消窗口最小限制
        setMinimumSize(null);
        //重新包裹设置窗口最小限制
        pack();
        setMinimumSize(new Dimension(this.getWidth(), this.getHeight()));
        //将窗口移至屏幕中间
        setLocationRelativeTo(null);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getNumOfMine() {
        return numOfMine;
    }

    public void setNumOfMine(int numOfMine) {
        this.numOfMine = numOfMine;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public MineButton[][] getMineButtons() {
        return mineButtons;
    }

    public void setMineButtons(MineButton[][] mineButtons) {
        this.mineButtons = mineButtons;
    }

    public GameTimerTask getGameTimerTask() {
        return gameTimerTask;
    }

    public void setGameTimerTask(GameTimerTask gameTimerTask) {
        this.gameTimerTask = gameTimerTask;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public TextPanel getTimePanel() {
        return timePanel;
    }

    public void setTimePanel(TextPanel timePanel) {
        this.timePanel = timePanel;
    }

    public TextPanel getMinePanel() {
        return minePanel;
    }

    public void setMinePanel(TextPanel minePanel) {
        this.minePanel = minePanel;
    }

    public MineButton getMouseStayButton() {
        return mouseStayButton;
    }

    public void setMouseStayButton(MineButton mouseStayButton) {
        this.mouseStayButton = mouseStayButton;
    }

    public boolean isExistSchedule() {
        return isExistSchedule;
    }

    public void setExistSchedule(boolean existSchedule) {
        this.isExistSchedule = existSchedule;
    }

    public Service getSer() {
        return ser;
    }

    public void setSer(Service ser) {
        this.ser = ser;
    }
}
