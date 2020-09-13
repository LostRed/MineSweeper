package com.lostred.ms.view;

import com.lostred.ms.controller.DialogActionListener;
import com.lostred.ms.util.GBC;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * 游戏结束对话框
 */
public class GameOverDialog extends JDialog {
    /**
     * 构建该对话框
     *
     * @param owner 一个该对话框所属的MainFrame类
     * @param isWin 是否胜利
     * @param time  游戏所用的时间
     */
    public GameOverDialog(MainFrame owner, boolean isWin, String time) {
        super(owner, "Game Over", true);
        setResizable(false);
        //构建组件
        JPanel panel = new JPanel();
        JPanel checkPanel = new JPanel();
        JLabel lblIcon = new JLabel();
        ImageIcon icon = new ImageIcon("images/title.png");
        lblIcon.setIcon(icon);
        JLabel lblLose = new JLabel("You lose!");
        JLabel lblWin = new JLabel("You success!");
        JLabel lblTime = new JLabel();
        JButton btnNewGame = new JButton("New Game");
        JButton btnRestart = new JButton("Restart");
        JButton btnExit = new JButton("Exit");
        //设置按钮加速器
        btnNewGame.setMnemonic('N');
        btnRestart.setMnemonic('R');
        btnExit.setMnemonic('E');
        //设置回车的默认按钮
        getRootPane().setDefaultButton(btnRestart);
        //布局
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 30, 0, 0));
        lblIcon.setBorder(new EmptyBorder(0, 0, 0, 20));
        checkPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
        lblTime.setText("Time: " + time + " seconds");
        //设置动作监听
        btnNewGame.addActionListener(new DialogActionListener(this));
        btnNewGame.setActionCommand("newGame");
        btnRestart.addActionListener(new DialogActionListener(this));
        btnRestart.setActionCommand("restart");
        btnExit.addActionListener(new DialogActionListener(this));
        btnExit.setActionCommand("exit");
        //添加组件
        add(panel,BorderLayout.WEST);
        panel.add(lblIcon, new GBC(0, 0, 1, 2).setInsets(0));
        if (!isWin) {
            panel.add(lblLose, new GBC(1, 0).setFill(GBC.HORIZONTAL).setInsets(5));
        } else {
            panel.add(lblWin, new GBC(1, 0).setFill(GBC.HORIZONTAL).setInsets(5));
        }
        panel.add(lblTime, new GBC(1, 1).setFill(GBC.HORIZONTAL).setInsets(5));
        add(checkPanel, BorderLayout.SOUTH);
        checkPanel.add(btnNewGame);
        checkPanel.add(btnRestart);
        checkPanel.add(btnExit);
        pack();
        setLocationRelativeTo(owner);
    }
}
