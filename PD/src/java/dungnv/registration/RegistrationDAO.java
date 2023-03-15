/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungnv.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.naming.spi.DirStateFactory;
import dungnv.utils.DBHelpers;

/**
 *
 * @author ASUS
 */
public class RegistrationDAO implements Serializable {

    public RegistrationDTO checkLogin(String username, String password)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RegistrationDTO result = null;
        try {
//            1. Open Connection 
            con = DBHelpers.makeConnection();
            if (con != null) {
//                2. Create sql String 
                String sql = "SELECT username, lastname " //Lưu ý: Ko ddc quên khoảng trắng 
                        + "From Registration "
                        + "Where username = ? And password = ? ";// các dấu "?" được gọi là placeholder
//            3. Prepare statement 
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
//            4. Process
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("lastname");// Lấy giá trị trong cột lastname 
                    result = new RegistrationDTO(username, password, fullName, false);
                }//check if rs has value 
            }//end if connections existed 
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
    private List<RegistrationDTO> accountList;

    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }

    public boolean searchLastName(String searchValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            //1. Open connection 
            con = DBHelpers.makeConnection();
            if (con != null) {
//                2. Create SQL String 
                String sql = "SELECT username, password, lastname, isAdmin "
                        + "FROM Registration "
                        + "WHERE lastname LIKE ?";
//            3. Prepared Statement 
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
//            4. Process 
                rs = stm.executeQuery();
                while (rs.next()) {
                    //get field/column
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");//Vì kiểu dữ liệu dưới databases là bit 0|1
                    //create DTO instance
                    RegistrationDTO dto
                            = new RegistrationDTO(username, password, lastname, role);
                    //add to account list
                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    }//end account list is not existed
                    this.accountList.add(dto);
                    result = true;
                }
            }//end if connection existed 
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

    private List<RegistrationDTO> removeAccountList;

    public List<RegistrationDTO> getRemoveAccountList() {
        return removeAccountList;
    }

    public RegistrationDTO deleteAccount(String username)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm1 = null;
        PreparedStatement stm2 = null;
        ResultSet rs = null;
        int effectedRow = 0;
        RegistrationDTO tmp = null;
        try {
//            1.Open connection 
            con = DBHelpers.makeConnection();
//            2.Sql String 

//STM1
            String sql1 = "SELECT username, password, lastname, isAdmin "
                    + "FROM Registration "
                    + "WHERE username = ?";
            stm1 = con.prepareStatement(sql1);
            stm1.setString(1, username);
            rs = stm1.executeQuery();
//END STM1            

//STM2            
            String sql2 = "DELETE FROM Registration "
                    + "WHERE username = ? ";
//            3.Prepared Stm 
            stm2 = con.prepareStatement(sql2);
            stm2.setString(1, username);
            effectedRow = stm2.executeUpdate();
//END STM2
//            4.Process 
            if (rs.next()) {//begin add delete account
                String password = rs.getString("password");
                String lastname = rs.getString("lastname");
                boolean role = rs.getBoolean("isAdmin");
                RegistrationDTO dto
                        = new RegistrationDTO(username, password, lastname, role);
                if (this.removeAccountList == null) {
                    this.removeAccountList = new ArrayList<RegistrationDTO>();
                }
                this.removeAccountList.add(dto);//end add delete account 
                if (effectedRow > 0) {
                    tmp = dto;
                }//End if effected row 
            }//End if existed account 
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm1 != null) {
                stm1.close();
            }
            if (stm2 != null) {
                stm2.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return tmp;
    }

    public boolean updateAccount(String username, String password, boolean role)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
//           1.Connect DB 
            con = DBHelpers.makeConnection();
//           2.CRUD 
            String sql = "UPDATE Registration "
                    + "SET password = ?, isAdmin = ? "
                    + "WHERE username = ?";
//           3.Prepared stm 
            String isAdmin = "0";
            if (role) {
                isAdmin = "1";
            }
            stm = con.prepareStatement(sql);
            stm.setString(1, password);
            stm.setString(2, isAdmin);
            stm.setString(3, username);
//          4.Execute Query 
            int effectedRows = stm.executeUpdate();
//          5.Process 
            if (effectedRows > 0) {
                result = true;
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
        return result;
    }

    public boolean createAccount(RegistrationDTO dto)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
//           1.Connect DB 
            con = DBHelpers.makeConnection();
//           2.CRUD 
            String sql = "INSERT INTO Registration("
                    + "username, password, lastname, isAdmin"
                    + ") "
                    + "VALUES("
                    + "?, ?, ?, ?"
                    + ")";
//           3.Prepared stm 
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getUsername());
            stm.setString(2, dto.getPassword());
            stm.setString(3, dto.getLastname());
            stm.setBoolean(4, dto.isRole());
//          4.Execute Query 
            int effectedRows = stm.executeUpdate();
//          5.Process 
            if (effectedRows > 0) {
                result = true;
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
}
