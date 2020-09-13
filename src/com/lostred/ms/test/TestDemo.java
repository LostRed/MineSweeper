package com.lostred.ms.test;

import com.lostred.ms.thread.GameThread;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

public class TestDemo {
    public static void main(String[] args) {
        InitGlobalFont(new Font("Arial", Font.BOLD, 14));
        GameThread gt = new GameThread();
        gt.start();
    }

    /**
     * 设置全局字体
     *
     * @param font 字体格式
     */
    private static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }
}
