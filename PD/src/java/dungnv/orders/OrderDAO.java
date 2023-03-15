/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungnv.orders;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import dungnv.product.ProductDTO;
import dungnv.utils.DBHelpers;

/**
 *
 * @author ASUS
 */
public class OrderDAO implements Serializable {

    public boolean updateOrders(String totalBill)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
//            1. Open Connection
            con = DBHelpers.makeConnection();
            if (con != null) {
//            2. Sql String 
                String sql = "INSERT INTO Orders "
                        + "VALUES(?,?)";
//            3. PreparedStatement 
                float value = Float.parseFloat(totalBill); //Khai báo value = total (Databases)
                long milis = System.currentTimeMillis();  //Khai báo milis bằng dateBuy(Databases)
                Date date = new Date(milis);
                stm = con.prepareStatement(sql);
                stm.setDate(1, date);
                stm.setFloat(2, value);
//            4. Process
                int effectedRow = stm.executeUpdate();
                if (effectedRow > 0) {
                    result = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean updateOrderDetail(String sku, int quantity)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm1 = null; //Orders
        PreparedStatement stm2 = null; //Product
        PreparedStatement stm3 = null; //OrderDetail
        ResultSet rs = null;
        boolean result = false;
        try {
//            1. Open connection 
            con = DBHelpers.makeConnection();
            if (con != null) {
//                2. SQL String 
//                3. PreparedStatement 
                String sql1 = "SELECT TOP 1 id "
                        + "FROM Orders "
                        + "ORDER BY id DESC"; //Lấy id của bảng [Orders] 
                stm1 = con.prepareStatement(sql1);
                rs = stm1.executeQuery();
                if (rs.next()) {//Đi qua các row của bảng Product
                    int orderID = rs.getInt("id");
                    String sql2 = "SELECT sku, name, description, quantity, price "
                            + "FROM Product "
                            + "WHERE sku = ?";//Lấy id của bảng [Product]
                    stm2 = con.prepareStatement(sql2);
                    stm2.setString(1, sku);
                    stm2.executeQuery();
                    if (rs.next()) {//Đi qua các row của bảng [OrderDetail]
                        float price = rs.getFloat("price");
                        float total = price * quantity;
                        String sql3 = "INSERT INTO OrderDetail ("
                                + "sku,"
                                + "orderID,"
                                + "quantity,"
                                + "price,"
                                + "total"
                                + ")"
                                + "VALUES ("
                                + "?,"
                                + "?,"
                                + "?,"
                                + "?,"
                                + "?"
                                + ")";
                        stm3 = con.prepareStatement(sql3);
                        stm3.setString(1, sku);
                        stm3.setInt(2, orderID);
                        stm3.setInt(3, quantity);
                        stm3.setFloat(4, price);
                        stm3.setFloat(5, total);
                        int effectedRow = stm3.executeUpdate();
                        if (effectedRow > 0) {
                            result = true;
                        }
                    }
                }
            }
        } finally {
            if(rs!= null){
                rs.close(); 
            }
            if(stm3!=null){
                stm3.close();
            }
            if(stm2!=null){
                stm2.close();
            }
            if(stm1!=null){
                stm1.close();
            }
            if(con!=null){
                con.close();
            }
        }
        return result;
    }

//    public ProductDTO selectProductByID(String sku)
//            throws SQLException, NamingException {
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        ProductDTO dto = null;
//        try {
//            con = DBHelpers.makeConnection();
//            if (con != null) {
//                String sql = "SELECT sku, name, description, price, quantity "
//                        + "FROM Product "
//                        + "WHERE sku = ?";
//                stm = con.prepareStatement(sql);
//                stm.setString(1, sku);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    String skuItem = rs.getString("sku");
//                    String name = rs.getString("name");
//                    String description = rs.getString("description");
//                    float price = rs.getFloat("price");
//                    int quantity = rs.getInt("quantity");
//                    dto = new ProductDTO(sku, name, description, quantity, price);
//                }
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return dto;
//    }
}
