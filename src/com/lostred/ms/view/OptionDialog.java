package com.lostred.ms.view;

import com.lostred.ms.controller.DialogActionListener;
import com.lostred.ms.util.Difficulty;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * 选项对话框
 */
public class OptionDialog extends JDialog {
    /**
     * BEGINNER难度单选框
     */
    private JRadioButton rdoBeginner;
    /**
     * INTERMEDIATE难度单选框
     */
    private JRadioButton rdoIntermediate;
    /**
     * ADVANCED难度单选框
     */
    private JRadioButton rdoAdvanced;

    /**
     * 构建构建该对话框
     *
     * @param owner 一个该对话框所属的MainFrame类
     */
    public OptionDialog(MainFrame owner) {
        super(owner, "Option", true);
        setResizable(false);
        //构建组件
        JPanel panel = new JPanel();
        JPanel checkPanel = new JPanel();
        ButtonGroup buttonGroup = new ButtonGroup();
        rdoBeginner = new JRadioButton(Difficulty.BEGINNER.getName());
        rdoIntermediate = new JRadioButton(Difficulty.INTERMEDIATE.getName());
        rdoAdvanced = new JRadioButton(Difficulty.ADVANCED.getName());
        if (owner.getDifficulty() == Difficulty.BEGINNER) {
            rdoBeginner.setSelected(true);
        } else if (owner.getDifficulty() == Difficulty.INTERMEDIATE) {
            rdoIntermediate.setSelected(true);
        } else if (owner.getDifficulty() == Difficulty.ADVANCED) {
            rdoAdvanced.setSelected(true);
        }
        buttonGroup.add(rdoBeginner);
        buttonGroup.add(rdoIntermediate);
        buttonGroup.add(rdoAdvanced);
        JButton btnOK = new JButton("Ok");
        JButton btnCancel = new JButton("Cancel");
        //设置焦点框
        rdoBeginner.setFocusPainted(false);
        rdoIntermediate.setFocusPainted(false);
        rdoAdvanced.setFocusPainted(false);
        btnOK.setFocusPainted(false);
        btnCancel.setFocusPainted(false);
        //设置按钮加速器
        btnOK.setMnemonic('O');
        btnCancel.setMnemonic('C');
        //设置回车的默认按钮
        getRootPane().setDefaultButton(btnOK);
        //布局
        rdoBeginner.setBorder(new EmptyBorder(5, 20, 5, 20));
        rdoIntermediate.setBorder(new EmptyBorder(5, 20, 5, 20));
        rdoAdvanced.setBorder(new EmptyBorder(5, 20, 5, 20));
        panel.setLayout(new GridLayout(3, 1));
        panel.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 5, 10),
                new TitledBorder(null, "Choose Difficulties", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION)));
        checkPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
        //设置监听
        btnOK.addActionListener(new DialogActionListener(this));
        btnOK.setActionCommand("ok");
        btnCancel.addActionListener(new DialogActionListener(this));
        btnCancel.setActionCommand("cancel");
        //添加组件
        panel.add(rdoBeginner);
        panel.add(rdoIntermediate);
        panel.add(rdoAdvanced);
        add(panel);
        add(checkPanel, BorderLayout.SOUTH);
        checkPanel.add(btnOK);
        checkPanel.add(btnCancel);
        pack();
        setLocationRelativeTo(owner);
    }

    public JRadioButton getRdoBeginner() {
        return rdoBeginner;
    }

    public void setRdoBeginner(JRadioButton rdoBeginner) {
        this.rdoBeginner = rdoBeginner;
    }

    public JRadioButton getRdoIntermediate() {
        return rdoIntermediate;
    }

    public void setRdoIntermediate(JRadioButton rdoIntermediate) {
        this.rdoIntermediate = rdoIntermediate;
    }

    public JRadioButton getRdoAdvanced() {
        return rdoAdvanced;
    }

    public void setRdoAdvanced(JRadioButton rdoAdvanced) {
        this.rdoAdvanced = rdoAdvanced;
    }
}
