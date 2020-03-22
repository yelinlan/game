//package com.yelinlan.game.Actinon;
//
//import com.google.common.collect.Maps;
//import com.yelinlan.game.service.ConnMap;
//import com.yelinlan.game.utils.PosType;
//import com.yelinlan.game.utils.StringUtils;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
///**
// * @项目名称: game
// * @类名称: LinkGame
// * @类描述:
// * @创建人: 权义翔
// * @创建时间: 2020/3/20 22:22
// **/
//@Controller
//@RequestMapping(value = "/main1")
//public class LinkGame {
//
//    @RequestMapping("/login")
//    public String login(){
//        return "LinkGame";
//    }
//
//    @RequestMapping(value = "/initLinkGame")
//    @ResponseBody
//    public Object initLinkGame(@RequestBody Map<String,Object> paramMap, HttpServletRequest req) {
//        //矩阵大小
//        Integer size = Integer.parseInt(Objects.toString(paramMap.get("size"), ""));
//        ConnMap maze = new ConnMap(size, size);
//        maze.initMaze();
//        req.getSession().removeAttribute("maze");
//        req.getSession().setAttribute("maze", maze);
//        Map<String, Object> map = Maps.newHashMap();
//        map.put("maze",maze);
//        map.put("infomation","加载成功");
//        map.put("state",1);
//        return map;
//    }
//
//    @RequestMapping(value = "/doLinkGame")
//    @ResponseBody
//    public Object doLinkGame(@RequestBody Map<String,Object> paramMap, HttpServletRequest req) {
//        ConnMap maze = null;
//        Map<String, Object> map = Maps.newHashMap();
//        try {
//            maze = (ConnMap) req.getSession().getAttribute("maze");
//        } catch (Exception e) {
//            map.put("infomation","未初始化");
//            map.put("state",0);
//            return map;
//        }
//        //获取位置,一定不为空
//        String pos_x = Objects.toString(paramMap.get("pos_x"));
//        String pos_y = Objects.toString(paramMap.get("pos_y"));
//        String pos_a = Objects.toString(paramMap.get("pos_a"));
//        String pos_b = Objects.toString(paramMap.get("pos_b"));
//
//        Integer x_a = null;
//        Integer y_a = null;
//        Integer x_b = null;
//        Integer y_b = null;
//        x_a = StringUtils.toInteger(pos_x);
//        y_a = StringUtils.toInteger(pos_y);
//        x_b = StringUtils.toInteger(pos_a);
//        y_b = StringUtils.toInteger(pos_b);
//        PosType startPosType = new PosType(x_a, y_a);
//        PosType endPosType = new PosType(x_b, y_b);
//        if (StringUtils.isBlank(maze.conn[x_a][y_a]) || StringUtils.isBlank(maze.conn[x_b][y_b])) {
//            map.put("infomation","未选中有效范围");
//            map.put("state",0);
//            return map;
//        }
//        if (!(maze.conn[x_a][y_a] == maze.conn[x_b][y_b])) {
//            map.put("infomation","无法删除，两个完全不一样");
//            map.put("state",0);
//            return map;
//        }
//        if (startPosType.equals(endPosType)) {
//            map.put("infomation","不能消除自己");
//            map.put("state",0);
//            return map;
//        }
//        if (!(startPosType.x < maze.getRow() && startPosType.x >= 0 && startPosType.y < maze.getCol() && startPosType.y >= 0)) {
//            map.put("infomation","越界");
//            map.put("state",0);
//            return map;
//        }
//        if (!(endPosType.x < maze.getRow() && endPosType.x >= 0 && endPosType.y < maze.getCol() && endPosType.y >= 0)) {
//            map.put("infomation","越界");
//            map.put("state",0);
//            return map;
//        }
//
//        maze.ConnGame(startPosType, endPosType);
//
//        if (maze.num <= 2&&maze.num>=0) {
//            maze.num=65536;
//            maze.conn[x_a][y_a] = ' ';
//            maze.conn[x_b][y_b] = ' ';
//            if(clearAll(maze)){
//                //消除完毕，移除session数据
//                req.getSession().removeAttribute("maze");
//                map.put("infomation","胜利");
//                map.put("state",1);
//                map.put("maze",maze);
//                return map;
//            }
//            map.put("infomation","加油");
//            map.put("state",1);
//            map.put("maze",maze);
//            req.getSession().setAttribute("maze",maze);
//            return map;
//        }
//        map.put("infomation","消除失败");
//        map.put("state",0);
//        maze.num=65536;
//        req.getSession().setAttribute("maze",maze);
//        return map;
//    }
//
//    /**
//     * @方法名 : clearAll
//     * @创建人 : 权义翔
//     * @创建日期 : 2020/3/20 23:35
//     * @功能描述 : 已消除完毕
//     *
//     * @return : boolean
//     */
//    public  boolean  clearAll(ConnMap maze){
//        for (int i = 0; i <maze.conn.length ; i++) {
//            for (int j = 0; j < maze.conn.length; j++) {
//                if(maze.conn[i][j]!=maze.EMPTY){
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//}