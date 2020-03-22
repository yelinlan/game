package com.yelinlan.game.utils;

import java.util.Objects;

/**
 * @项目名称: DATA_STRUCTURE
 * @类名称: posType
 * @类描述: 迷宫块位置
 * @创建人: 权义翔
 * @创建时间: 2020/3/6 22:57
 **/
public class PosType {
    public int x;
    public int y;

    public PosType(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PosType posType = (PosType) o;
        return x == posType.x &&
                y == posType.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "PosType{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}