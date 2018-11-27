package dao;

import entity.Items;
import util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

//商品的业务逻辑类
public class ItemsDAO {

    //获得所有的商品信息
    public ArrayList<Items> getAllItems()
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Items> list = new ArrayList<Items>();//商品集合
        try
        {
            conn = DBHelper.getConnection();
            String sql = "select * from items;";//SQL语句
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next())
            {
                Items item = new Items();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setCity(rs.getString("city"));
                item.setNumber(rs.getInt("number"));
                item.setPrice(rs.getInt("price"));
                item.setPicture(rs.getString("picture"));
                list.add(item);//把一个商品加入集合
            }
            return list;//返回集合
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            if(rs != null)
            {
                //释放数据集对象
                try
                {
                    rs.close();
                    rs = null;
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            //释放语句对象
            if(stmt != null)
            {
                try
                {
                    stmt.close();
                    stmt = null;
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }
}
