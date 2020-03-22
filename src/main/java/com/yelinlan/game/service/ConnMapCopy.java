package com.yelinlan.game.service;

import com.yelinlan.game.utils.Common;
import com.yelinlan.game.utils.PosType;

import java.util.Random;

/**
 * @项目名称: game
 * @类名称: ConnMapCopy
 * @类描述:
 * @创建人: 权义翔
 * @创建时间: 2020/3/21 19:54
 **/
public class ConnMapCopy {
    public char[][] conn = new char[10][10];
    //边界定义 "#"
    protected final char BORDER = '#';
    //生成空
    public final char EMPTY = ' ';
    //通过 "!"
    protected final char PASS = '!';
    //走过但不通 "@"
    protected final char PASSNOT = '@';
    //其他

    protected int row = 10;
    protected int col = 10;
    public PosType start;
    public PosType end;

    public ConnMapCopy(int row, int col) {
        if ((row * col) % 2 == 0) {
            this.conn = new char[row][col];
            this.row = row;
            this.col = col;
        }
    }

    public ConnMapCopy() {
    }

    /**
     * @return : void
     * @方法名 : initMaze
     * @创建人 : 权义翔
     * @创建日期 : 2020/3/7 18:23
     * @功能描述 : 初始化连连看
     */
    public void initMaze() {
        String baseChar = "ABCDEFGHJKL";
        int charLen = baseChar.length();
        Random rand = new Random();
        char val = baseChar.charAt(rand.nextInt(charLen));
        //初始化
        for (int i = 0; i < conn.length; i++) {
            for (int j = 0; j < conn.length; j++) {
                conn[i][j] = EMPTY;
            }
        }
        //随机阵
        char[] tempC = new char[(row - 2) * (col - 2)];
        for (int i = 0; i < (row - 2) * (col - 2) / 2; i++) {
            tempC[2 * i] = baseChar.charAt(rand.nextInt(charLen));
            tempC[2 * i + 1] = tempC[2 * i];
        }
        randChar(tempC);
        for (int i = 1; i < conn.length - 1; i++) {
            for (int j = 1; j < conn.length - 1; j++) {
                conn[i][j] = tempC[(i - 1) * (conn.length - 2) + j - 1];
            }
        }
    }

    /**
     * @return : void
     * @方法名 : randChar
     * @创建人 : 权义翔
     * @创建日期 : 2020/3/20 2:51
     * @功能描述 : 随机字符数组
     */
    public void randChar(char[] arr) {
        Random rand = new Random();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int index = rand.nextInt(len - i) + i;
            char temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }

    public boolean canPass(PosType start, PosType end) {
        this.start = start;
        this.end = end;
        return lean(start, end);
    }

    /**
     * @return : boolean
     * @方法名 : horizonalMid
     * @创建人 : 权义翔
     * @创建日期 : 2020/3/21 20:42
     * @功能描述 : 水平中
     */
    public boolean horizonalMid(PosType start, PosType end) {
        for (int i = Common.min(start.y, end.y); i <= Common.max(start.y, end.y); i++) {
            if (conn[start.x][i] != EMPTY) {
                if (conn[start.x][i] == conn[this.start.x][this.start.y] || conn[this.start.x][i] == conn[this.end.x][this.end.y]) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    /**
     * @return : boolean
     * @方法名 : verticalUp
     * @创建人 : 权义翔
     * @创建日期 : 2020/3/21 20:46
     * @功能描述 : 垂直中
     */
    public boolean verticalMid(PosType start, PosType end) {
        //中间水平
        for (int i = Common.min(start.x, end.x); i <= Common.max(start.x, end.x); i++) {
            if (conn[i][start.y] != EMPTY) {
                if (conn[i][start.y] == conn[this.start.x][this.start.y] || conn[i][this.start.y] == conn[this.end.x][this.end.y]) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    /**
     * @return : boolean
     * @方法名 : lean
     * @创建人 : 权义翔
     * @创建日期 : 2020/3/21 20:57
     * @功能描述 : 斜线上 先水平后垂直
     */
    public boolean leanHV(PosType start, PosType end) {
        //先水平后垂直
        for (int i = 0; i < row; i++) {
            PosType posStart = new PosType(start.x, i);
            PosType posEnd = new PosType(end.x, i);
            //垂直通 水平通
            if (verticalMid(posStart, posEnd) && horizonalMid(start, posStart) && horizonalMid(posEnd, end)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return : boolean
     * @方法名 : lean
     * @创建人 : 权义翔
     * @创建日期 : 2020/3/21 20:57
     * @功能描述 : 斜线上 先垂直后水平
     */
    public boolean leanVH(PosType start, PosType end) {
        //先垂直后水平
        for (int i = 0; i < col; i++) {
            PosType posStart = new PosType(i, start.y);
            PosType posEnd = new PosType(i, end.y);
            //垂直通 水平通
            if (horizonalMid(posStart, posEnd) && verticalMid(start, posStart) && verticalMid(posEnd, end)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return : boolean
     * @方法名 : lean
     * @创建人 : 权义翔
     * @创建日期 : 2020/3/21 21:50
     * @功能描述 : 斜着
     */
    public boolean lean(PosType start, PosType end) {
        boolean leanhv = leanHV(start, end);
        boolean leanvh = leanVH(start, end);
        return leanhv || leanvh;
    }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}