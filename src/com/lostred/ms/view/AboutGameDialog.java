package com.lostred.ms.view;

import com.lostred.ms.util.GBC;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * 关于游戏对话框
 */
public class AboutGameDialog extends JDialog {
    /**
     * 构建该对话框
     *
     * @param owner 一个该对话框所属的MainFrame类
     */
    public AboutGameDialog(MainFrame owner) {
        super(owner, "About Game", true);
        setResizable(false);
        //构建组件
        JLabel lbTitle = new JLabel("MineSweeper\u2122", SwingConstants.CENTER);
        JPanel panel = new JPanel();
        JLabel lbIcon = new JLabel();
        ImageIcon icon = new ImageIcon("images/title.png");
        lbIcon.setIcon(icon);
        JLabel label1 = new JLabel("author: LostRed");
        JLabel label2 = new JLabel("version: 4.00 [build 0718]");
        JLabel label3 = new JLabel("powered by Java");
        //布局
        lbTitle.setBorder(new EmptyBorder(10, 0, 10, 0));
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new CompoundBorder(new EmptyBorder(0, 10, 10, 10), new EtchedBorder()));
        //添加组件
        panel.add(lbIcon, new GBC(0, 0, 1, 3).setAnchor(GBC.CENTER).setInsets(5));
        panel.add(label1, new GBC(1, 0).setFill(GBC.HORIZONTAL).setInsets(5));
        panel.add(label2, new GBC(1, 1).setFill(GBC.HORIZONTAL).setInsets(5));
        panel.add(label3, new GBC(1, 2).setFill(GBC.HORIZONTAL).setInsets(5));
        add(lbTitle, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(owner);
    }
}
