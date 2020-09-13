package com.lostred.ms.view;

import com.lostred.ms.controller.DialogActionListener;
import com.lostred.ms.util.Difficulty;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * 最高分对话框
 */
public class ScoreDialog extends JDialog {
    /**
     * BEGINNER难度的最短时间标签
     */
    private JLabel lblTime1;
    /**
     * INTERMEDIATE难度的最短时间标签
     */
    private JLabel lblTime2;
    /**
     * ADVANCED难度的最短时间标签
     */
    private JLabel lblTime3;

    public ScoreDialog(MainFrame owner) {
        super(owner, "Score", true);
        setResizable(false);
        //构建组件
        JPanel panel = new JPanel();
        JPanel checkPanel = new JPanel();
        JLabel lblTitle1 = new JLabel("Difficulty");
        JLabel lblTitle2 = new JLabel("Time");
        JLabel label1 = new JLabel(Difficulty.BEGINNER.getName());
        JLabel label2 = new JLabel(Difficulty.INTERMEDIATE.getName());
        JLabel label3 = new JLabel(Difficulty.ADVANCED.getName());
        lblTime1 = new JLabel("-");
        lblTime2 = new JLabel("-");
        lblTime3 = new JLabel("-");
        JButton btnClear = new JButton("Clear");
        JButton btnBack = new JButton("Back");
        //设置焦点框
        btnClear.setFocusPainted(false);
        btnBack.setFocusPainted(false);
        //设置按钮加速器
        btnClear.setMnemonic('C');
        btnBack.setMnemonic('B');
        //设置回车的默认按钮
        getRootPane().setDefaultButton(btnBack);
        //布局
        lblTitle1.setBorder(new EmptyBorder(5, 20, 5, 20));
        lblTitle2.setBorder(new EmptyBorder(5, 20, 5, 20));
        label1.setBorder(new EmptyBorder(5, 20, 5, 20));
        label2.setBorder(new EmptyBorder(5, 20, 5, 20));
        label3.setBorder(new EmptyBorder(5, 20, 5, 20));
        lblTime1.setBorder(new EmptyBorder(5, 20, 5, 20));
        lblTime2.setBorder(new EmptyBorder(5, 20, 5, 20));
        lblTime3.setBorder(new EmptyBorder(5, 20, 5, 20));
        panel.setLayout(new GridLayout(4, 2));
        panel.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 5, 10),
                new TitledBorder(null, "Highest Scores", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION)));
        checkPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
        //添加监听
        btnClear.addActionListener(new DialogActionListener(this));
        btnClear.setActionCommand("clear");
        btnBack.addActionListener(new DialogActionListener(this));
        btnBack.setActionCommand("cancel");
        //添加组件
        panel.add(lblTitle1);
        panel.add(lblTitle2);
        panel.add(label1);
        panel.add(lblTime1);
        panel.add(label2);
        panel.add(lblTime2);
        panel.add(label3);
        panel.add(lblTime3);
        checkPanel.add(btnClear);
        checkPanel.add(btnBack);
        add(panel);
        add(checkPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(owner);
    }

    public JLabel getLblTime1() {
        return lblTime1;
    }

    public void setLblTime1(JLabel lblTime1) {
        this.lblTime1 = lblTime1;
    }

    public JLabel getLblTime2() {
        return lblTime2;
    }

    public void setLblTime2(JLabel lblTime2) {
        this.lblTime2 = lblTime2;
    }

    public JLabel getLblTime3() {
        return lblTime3;
    }

    public void setLblTime3(JLabel lblTime3) {
        this.lblTime3 = lblTime3;
    }
}
