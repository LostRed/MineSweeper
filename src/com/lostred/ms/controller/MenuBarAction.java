package com.lostred.ms.controller;

import com.lostred.ms.util.Records;
import com.lostred.ms.view.AboutGameDialog;
import com.lostred.ms.view.MainFrame;
import com.lostred.ms.view.OptionDialog;
import com.lostred.ms.view.ScoreDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * 动作，处理菜单触发的事件
 */
public class MenuBarAction extends AbstractAction {
    /**
     * 该动作操作的MainFrame
     */
    private final MainFrame mf;
    /**
     * 该动作创建的AboutGameDialog
     */
    private AboutGameDialog aboutGameDialog;
    /**
     * 该动作创建的OptionDialog
     */
    private OptionDialog optionDialog;
    /**
     * 该动作创建的ScoreDialog
     */
    private ScoreDialog scoreDialog;

    /**
     * 构建菜单项事件，初始化其所属的对象
     *
     * @param mf      该动作操作的MainFrame
     * @param name    该动作的名称
     * @param command 该动作的命令字符串
     */
    public MenuBarAction(MainFrame mf, String name, String command) {
        this.mf = mf;
        putValue(Action.NAME, name);
        putValue(Action.ACTION_COMMAND_KEY, command);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取监听器监听到的动作命令
        String command = e.getActionCommand();
        //根据监听的动作命令处理
        switch (command) {
            case "newGame":
                mf.generateButtons();
                mf.init();
                mf.getSer().generateMine();
                break;
            case "option":
                if (optionDialog == null) {
                    optionDialog = new OptionDialog(mf);
                }
                optionDialog.setVisible(true);
                break;
            case "exit":
                System.exit(0);
            case "aboutGame":
                if (aboutGameDialog == null) {
                    aboutGameDialog = new AboutGameDialog(mf);
                }
                aboutGameDialog.setVisible(true);
                break;
            case "score":
                if (scoreDialog == null) {
                    scoreDialog = new ScoreDialog(mf);
                    String beginnerTime = Records.readPropertiesValue("beginnerTime");
                    String intermediateTime = Records.readPropertiesValue("intermediateTime");
                    String advancedTime = Records.readPropertiesValue("advancedTime");
                    if (beginnerTime != null && !beginnerTime.equals("0")) {
                        scoreDialog.getLblTime1().setText(beginnerTime + " s");
                    }
                    if (intermediateTime != null && !intermediateTime.equals("0")) {
                        scoreDialog.getLblTime2().setText(intermediateTime + " s");
                    }
                    if (advancedTime != null && !advancedTime.equals("0")) {
                        scoreDialog.getLblTime3().setText(advancedTime + " s");
                    }
                }
                scoreDialog.getLblTime1().repaint();
                scoreDialog.setVisible(true);
        }
    }
}
