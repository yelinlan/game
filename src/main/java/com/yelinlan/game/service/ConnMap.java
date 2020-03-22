//package com.yelinlan.game.service;
//
//import com.yelinlan.game.utils.Common;
//import com.yelinlan.game.utils.PosType;
//import org.springframework.stereotype.Service;
//
//import java.util.Random;
//
///**
// * @项目名称: game
// * @类名称: ConnMap
// * @类描述:
// * @创建人: 权义翔
// * @创建时间: 2020/3/20 22:32
// **/
//public class ConnMap {
//
//    //边界定义 "#"
//    protected final char BORDER = '#';
//    //生成空
//    public final char EMPTY = ' ';
//    //通过 "!"
//    protected final char PASS = '!';
//    //走过但不通 "@"
//    protected final char PASSNOT = '@';
//    //其他
//
//
//    public char[][] conn = new char[10][10];
//    public char[][] connTemp = new char[10][10];
//    protected int row = 10;
//    protected int col = 10;
//
//    public PosType start;
//    public PosType end;
//
//    public int num = 65536;
//    //记录拐角
//    public String record = "";
//
//    public ConnMap(int row, int col) {
//        if ((row * col) % 2 == 0) {
//            this.conn = new char[row][col];
//            this.connTemp = new char[row][col];
//            this.row = row;
//            this.col = col;
//        }
//    }
//
//    public ConnMap() {
//    }
//
//    /**
//     * @return : void
//     * @方法名 : initMaze
//     * @创建人 : 权义翔
//     * @创建日期 : 2020/3/7 18:23
//     * @功能描述 : 初始化连连看
//     */
//    public void initMaze() {
//        String baseChar = "ABCDEFGHJKL";
//        int charLen = baseChar.length();
//        Random rand = new Random();
//        char val = baseChar.charAt(rand.nextInt(charLen));
//        //初始化
//        for (int i = 0; i < conn.length; i++) {
//            for (int j = 0; j < conn.length; j++) {
//                conn[i][j] = EMPTY;
//            }
//        }
//        //随机阵
//        char[] tempC = new char[(row - 2) * (col - 2)];
//        for (int i = 0; i < (row - 2) * (col - 2) / 2; i++) {
//            tempC[2 * i] = baseChar.charAt(rand.nextInt(charLen));
//            tempC[2 * i + 1] = tempC[2 * i];
//        }
//        randChar(tempC);
//        for (int i = 1; i < conn.length - 1; i++) {
//            for (int j = 1; j < conn.length - 1; j++) {
//                conn[i][j] = tempC[(i - 1) * (conn.length - 2) + j - 1];
//            }
//        }
//        copyConn();
//    }
//
//    /**
//     * @return : void
//     * @方法名 : randChar
//     * @创建人 : 权义翔
//     * @创建日期 : 2020/3/20 2:51
//     * @功能描述 : 随机字符数组
//     */
//    public void randChar(char[] arr) {
//        Random rand = new Random();
//        int len = arr.length;
//        for (int i = 0; i < len; i++) {
//            int index = rand.nextInt(len - i) + i;
//            char temp = arr[index];
//            arr[index] = arr[i];
//            arr[i] = temp;
//        }
//    }
//
//    /**
//     * @return : void
//     * @方法名 : copyLlk2Arr
//     * @创建人 : 权义翔
//     * @创建日期 : 2020/3/7 19:11
//     * @功能描述 : 得到副本
//     */
//    public void copyConn() {
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                this.connTemp[i][j] = conn[i][j];
//            }
//        }
//    }
//
//    public void ConnGame(PosType start, PosType end) {
//        copyConn();
//        this.start = start;
//        this.end = end;
//        mark(connTemp, start, PASS, 0);
//        mark(connTemp, end, EMPTY, 1);
//        doConn(connTemp, start);
//        System.out.println("拐角数 ： " + num);
//    }
//
//    public void doConn(char[][] co, PosType pos) {
//        if (pos.equals(end)) {
//            num =Common.min(countConner(record), num);
//        } else{
//            if(countConner(record)<=2) {
//                for (int i = 1; i <= 4; i++) {
//                    PosType rePos = canVisit(co, pos, i);
//                    if (rePos != null) {
//                        int tempNum = ((rePos.x - pos.x) == 0) ? 0 : 1;
//                        record += tempNum;
//                        mark(co, rePos, PASS, 0);
//                        doConn(co, rePos);
//                        mark(co, rePos, EMPTY, 0);
//                        record = record.substring(0, record.length() - 1);
//                    }
//                }
//            }
//        }
//    }
//
//
//    /**
//     * @return : int
//     * @方法名 : countConner
//     * @创建人 : 权义翔
//     * @创建日期 : 2020/3/7 16:45
//     * @功能描述 : 统计拐角次数
//     */
//    public int countConner(String str) {
//        int count = 0;
//        char[] chars = str.toCharArray();
//        int len = chars.length;
//        for (int i = 0; i < len - 1; i++) {
//            if (chars[i] != chars[i + 1]) {
//                count++;
//            }
//        }
//        return count;
//    }
//
//    /**
//     * @return : boolean
//     * @方法名 : canVisit
//     * @创建人 : 权义翔
//     * @创建日期 : 2020/3/20 0:23
//     * @功能描述 : 是否可走
//     */
//    protected PosType canVisit(char[][] conn, PosType pos, int direction) {
//        PosType nextPosType = new PosType(pos.x, pos.y);
//        switch (direction) {
//            //DOWN = 1;
//            case 1:
//                nextPosType.x++;
//                break;
//            //RIGHT = 2;
//            case 2:
//                nextPosType.y++;
//                break;
//            //UP = 3;
//            case 3:
//                nextPosType.x--;
//                break;
//            // LEFT = 4;
//            case 4:
//                nextPosType.y--;
//                break;
//            default:
//                return null;
//        }
//        //越界
//        if (isOutOfXBounds(nextPosType)) {
//            return null;
//        }
//        //障碍或已走过
//        //BORDER PASSNOT PASS
//        if (conn[nextPosType.x][nextPosType.y] != EMPTY) {
//            return null;
//        }
//        pos = nextPosType;
//        return pos;
//    }
//
//    /**
//     * @return : void
//     * @方法名 : mark
//     * @创建人 : 权义翔
//     * @创建日期 : 2020/3/7 11:50
//     * @功能描述 : //todo 标记迷宫块
//     */
//    public void mark(char[][] co, PosType pos, char markSymbol, int state) {
//        if (state != 0) {
//            co[pos.x][pos.y] = markSymbol;
//        } else {
//            if (!pos.equals(end)) {
//                co[pos.x][pos.y] = markSymbol;
//            }
//        }
//    }
//
//    /**
//     * @return : boolean
//     * @方法名 : isOutOfXBounds
//     * @创建人 : 权义翔
//     * @创建日期 : 2020/3/7 13:20
//     * @功能描述 : 坐标越界
//     */
//    public boolean isOutOfXBounds(PosType pos) {
//        return !(pos.x < this.row && pos.x >= 0 && pos.y < this.col && pos.y >= 0);
//    }
//
//    public int getRow() {
//        return row;
//    }
//
//    public int getCol() {
//        return col;
//    }
//}
//
