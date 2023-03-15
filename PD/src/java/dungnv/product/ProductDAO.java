/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungnv.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import dungnv.utils.DBHelpers;

/**
 *
 * @author ASUS
 */
public class ProductDAO implements Serializable {

    private List<ProductDTO> items;

    public List<ProductDTO> getItems() {
        return items;
    }
    
    
    
//ShoppingPageServlet
    public void getProductFromInventory()
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
//            1.Open Connection 
            con = DBHelpers.makeConnection();
            if (con != null) {
//            2.Create SQL String 
                String sql = "SELECT sku, name, description, quantity, price "
                        + "FROM Product "
                        + "WHERE status = 1";
//            3.Prepare Statement
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
//            4.Process
                while (rs.next()) {
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");

                    ProductDTO dto
                            = new ProductDTO(sku, name, description, quantity, price);
                    if (this.items == null) {
                        items = new ArrayList<>();
                    }
                    this.items.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    
    
//PaymentProductServlet
    public boolean updateItemsInInventory(String id, int quantityBuy, int quantityInInventory)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. open connection
            con = DBHelpers.makeConnection();
            //2. check if connection existed
            if (con != null) {
                //3.create sql String
                String sql = "UPDATE Product "
                        + "SET quantity = ? "
                        + "WHERE sku LIKE ? ";
                //4. prepare statement
                stm = con.prepareStatement(sql);
                stm.setString(2, id);
                int tmp = quantityInInventory - quantityBuy;
                stm.setInt(1, tmp);
                result = stm.execute();
                
                //*check if quantity == 0
                if(tmp == 0){
                    String sql1 = "UPDATE Product "
                        + "SET status = 0 "
                        + "WHERE sku LIKE ? ";
                    stm = con.prepareStatement(sql1);
                    stm.setString(1, id);
                    stm.execute();
                }
                
            }//end if has connection
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

    private List<ProductDTO> list;

    public List<ProductDTO> getList() {
        return list;
    }

    
//CheckOutProductServlet
    public boolean getProductFromCart(String id)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
//            1. Make Connection 
            con = DBHelpers.makeConnection();
//            2. SQL String (CRUD) 
            String sql = "SELECT sku, name, description, quantity, price "
                    + "FROM Product "
                    + "WHERE sku = ? ";
//            3. PreparedStatement 
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
//            4. Process
            rs = stm.executeQuery();
            if (rs.next()) {
                String sku = rs.getString("sku");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                float price = rs.getFloat("price");
                ProductDTO dto
                        = new ProductDTO(sku, name, description, quantity, price);
                if (list == null) {
                    this.list = new ArrayList<ProductDTO>();
                }
                this.list.add(dto);
                result = true;
            }//end if have ResultSet
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
}
