package com.lostred.ms.controller;

import com.lostred.ms.util.Difficulty;
import com.lostred.ms.util.Records;
import com.lostred.ms.view.MainFrame;
import com.lostred.ms.view.OptionDialog;
import com.lostred.ms.view.ScoreDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 对话框事件监听器，触发对话框按钮按下的事件
 */
public class DialogActionListener implements ActionListener {
    /**
     * 该事件监听器所属的对话框，可能是OptionDialog、GameOverDialog、ScoreDialog
     */
    private final JDialog owner;

    /**
     * 构建对话框的事件监听器，初始化其所属的对象
     *
     * @param owner 该类对象所属的JDialog类
     */
    public DialogActionListener(JDialog owner) {
        this.owner = owner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取监听器监听到的动作命令
        String command = e.getActionCommand();
        //获取主窗口
        MainFrame mf = ((MainFrame) owner.getOwner());
        //根据监听的动作命令处理
        switch (command) {
            case "ok":
                OptionDialog od = (OptionDialog) owner;
                if (od.getRdoBeginner().isSelected()) {
                    mf.setDifficulty(Difficulty.BEGINNER);
                    Records.writeProperties("difficulty", "BEGINNER");
                } else if (od.getRdoIntermediate().isSelected()) {
                    mf.setDifficulty(Difficulty.INTERMEDIATE);
                    Records.writeProperties("difficulty", "INTERMEDIATE");
                } else if (od.getRdoAdvanced().isSelected()) {
                    mf.setDifficulty(Difficulty.ADVANCED);
                    Records.writeProperties("difficulty", "ADVANCED");
                }
                mf.generateButtons();
                mf.getSer().generateMine();
                mf.init();
                owner.setVisible(false);
                break;
            case "cancel":
                owner.setVisible(false);
                break;
            case "newGame":
                mf.generateButtons();
                mf.getSer().generateMine();
                mf.init();
                owner.setVisible(false);
                break;
            case "restart":
                mf.init();
                owner.setVisible(false);
                break;
            case "exit":
                System.exit(0);
                break;
            case "clear":
                ((ScoreDialog) owner).getLblTime1().setText("-");
                Records.writeProperties("beginnerTime", "0");
                ((ScoreDialog) owner).getLblTime2().setText("-");
                Records.writeProperties("intermediateTime", "0");
                ((ScoreDialog) owner).getLblTime3().setText("-");
                Records.writeProperties("advancedTime", "0");
                break;
        }
    }
}
