package com.lostred.ms.util;

/**
 * 游戏难度
 */
public enum Difficulty {
    BEGINNER("Beginner", 9, 9, 10),
    INTERMEDIATE("Intermediate", 16, 16, 40),
    ADVANCED("Advanced", 16, 30, 99);
    /**
     * 难度名称
     */
    private String name;
    /**
     * 难度行数
     */
    private int row;
    /**
     * 难度列数
     */
    private int col;
    /**
     * 难度地雷数
     */
    private int numOfMine;

    /**
     * 构建难度
     *
     * @param name      难度名称
     * @param row       某难度下的按钮行数
     * @param col       某难度下的按钮列数
     * @param numOfMine 某难度下的地雷数量
     */
    Difficulty(String name, int row, int col, int numOfMine) {
        this.name = name;
        this.row = row;
        this.col = col;
        this.numOfMine = numOfMine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
