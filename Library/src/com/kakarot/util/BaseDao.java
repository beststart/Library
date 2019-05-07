package com.kakarot.util;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

/**
 * 两种使用情况：
 * 1.继承basedao
 * 2.方法静态，以工具类的方式调用
 */
public class BaseDao {

    private static ResourceBundle rb;
    static {
        rb=ResourceBundle.getBundle("db");
    }
    private final static String URL=rb.getString("jdbc.url");
    private final static String UNAME=rb.getString("jdbc.username");
    private final static String PWD=rb.getString("jdbc.password");
    private final static String DRIVER=rb.getString("jdbc.driver");

    private static Connection conn =null;
    private static PreparedStatement pstm=null;
    private static ResultSet rs=null;


    //获取链接
    public static void getConn(){
        try {
            Class.forName(DRIVER);
            conn= DriverManager.getConnection(URL,UNAME,PWD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通用增删改
     * @param sql   sql语句
     * @param list  参数列表
     * @return      受影响行数
     */
    public static int baseUpdate(String sql, List<Object> list){
        int result = 0;
        try {
            managerPstm(sql, list);
            result = pstm.executeUpdate();
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int baseUpdate(String sql){
        return baseUpdate(sql,null);
    }
    //通用查询
    public static <T> List<T> baseQuery(String sql, List<Object> list,Class<T> tClass){
        managerPstm(sql,list);
        List<T> dataList=new ArrayList<>();
        try {
            rs=pstm.executeQuery();
            /*获取结果集的元数据：能够拿到表名和列名以及别名*/
            ResultSetMetaData rsmd=rs.getMetaData();
            while (rs.next()){
                T t = tClass.newInstance();//获取泛型的实例对象
                Field[] fs=tClass.getDeclaredFields();//获取所有属性
                for(int i=0;i<rsmd.getColumnCount();i++){//遍历所有列
                    for(Field f:fs){
                        if(f.getName().equals(rsmd.getColumnLabel(i+1))){
                            /*当属性名和查到的列名相同时*/
                            /*设置私有属性可用*/
                            f.setAccessible(true);
                            /*设置值*/
                            /*给t对象的f属性设置查询到的值*/
                            f.set(t,rs.getObject(rsmd.getColumnLabel(i+1)));
                        }
                    }
                }
                dataList.add(t);
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public static <T> List<T> baseQuery(String sql,Class<T> tClass){
        return baseQuery(sql,null,tClass);
    }

    public static <T> T baseQueryBean(String sql,List<Object> list,Class<T> tClass){
        List<T> tList=baseQuery(sql,list,tClass);
        if(tList!=null&&tList.size()>0){
            return tList.get(0);
        }
        return null;
    }

    public static <T> T baseQueryBean(String sql,Class<T> tClass){
        return baseQueryBean(sql,null,tClass);
    }

    public static Integer getCount(String sql,List list){
        managerPstm(sql,list);
        try{
            rs=pstm.executeQuery();
            if(rs.next()){
                return rs.getInt("count");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    private static void managerPstm(String sql,List<Object> list){
        try {
            getConn();
            pstm=conn.prepareStatement(sql);
            if(list!=null&&list.size()>0){
                for(int i=0;i<list.size();i++){
                    pstm.setObject(i+1,list.get(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //关闭链接
    public static void closeAll(){
        try {
            if(rs!=null){
                rs.close();
            }
            if(pstm!=null){
                pstm.close();
            }
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
