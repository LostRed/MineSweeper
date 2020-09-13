package com.lostred.ms.view;

import com.lostred.ms.controller.MenuBarAction;

import javax.swing.*;

/**
 * 菜单栏
 */
public class MainMenuBar extends JMenuBar {

    /**
     * 构建菜单栏，初始化其所属对象
     *
     * @param owner 该菜单栏所属的MainFrame
     */
    public MainMenuBar(MainFrame owner) {
        //构建菜单和菜单项
        JMenu menuGame = new JMenu("Game");
        JMenu menuHelp = new JMenu("Help");
        MenuBarAction actionNewGame = new MenuBarAction(owner, "New Game", "newGame");
        MenuBarAction actionOption = new MenuBarAction(owner, "Option", "option");
        MenuBarAction actionScore = new MenuBarAction(owner, "Score", "score");
        MenuBarAction actionExit = new MenuBarAction(owner, "Exit", "exit");
        MenuBarAction actionAboutGame = new MenuBarAction(owner, "About Game", "aboutGame");
        actionNewGame.putValue(Action.SHORT_DESCRIPTION, "Start a new game");
        actionOption.putValue(Action.SHORT_DESCRIPTION, "Set game difficulty");
        actionScore.putValue(Action.SHORT_DESCRIPTION, "Show the highest scores");
        actionExit.putValue(Action.SHORT_DESCRIPTION, "Exit Game");
        actionAboutGame.putValue(Action.SHORT_DESCRIPTION, "About Game");
        //设置开始菜单
        menuGame.setMnemonic('G');
        menuGame.add(actionNewGame);
        actionNewGame.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
        menuGame.addSeparator();//添加分割线
        menuGame.add(actionOption);
        actionOption.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl O"));
        menuGame.add(actionScore);
        actionScore.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl S"));
        menuGame.addSeparator();//添加分割线
        menuGame.add(actionExit);
        actionExit.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl E"));
        //设置帮助菜单
        menuHelp.setMnemonic('H');
        menuHelp.add(actionAboutGame);
        actionAboutGame.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl A"));
        //菜单条添加菜单
        add(menuGame);
        add(menuHelp);
    }
}
