package com.lostred.ms.view;

import com.lostred.ms.util.GameIcon;

import javax.swing.*;
import java.awt.*;

/**
 * 承载时间计数器和地雷计数器的文本类面板
 */
public class TextPanel extends JPanel {
    /**
     * 面板的文本框
     */
    private final JTextField textField;
    /**
     * 面板的文本内容
     */
    private String text;

    /**
     * 构建该面板
     */
    public TextPanel() {
        textField = new JTextField();
        textField.setColumns(3);
        textField.setFont(new Font("Arial", Font.BOLD, 24));
        textField.setDisabledTextColor(Color.WHITE);
        //文本框设置文字居中
        textField.setHorizontalAlignment(JTextField.CENTER);
        //文本框设置为透明背景
        textField.setOpaque(false);
        //文本框设置为无边框
        textField.setBorder(null);
        //文本框设置为不可用且不可编辑
        textField.setEnabled(false);
        textField.setEditable(false);
        this.add(textField);
    }

    /**
     * 获取面板内的文本字符串
     *
     * @return 文本字符串
     */
    public String getText() {
        return text;
    }

    /**
     * 设置面板内的文本字符串
     *
     * @param text 需要设定的字符串
     */
    public void setText(String text) {
        this.text = text;
        this.textField.setText(text);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(GameIcon.PANEL_BACKGROUND.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        this.repaint();
    }
}
