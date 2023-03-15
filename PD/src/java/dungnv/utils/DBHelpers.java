/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungnv.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;

/**
 *
 * @author ASUS
 */
public class DBHelpers implements Serializable {

    public static Connection makeConnection()
            throws NamingException, SQLException {
        //1. Tìm file server context 
        Context serverContext = new InitialContext();
        //2. Tìm file container context 
        Context tomcatContext = (Context) serverContext.lookup("java:comp/env");
        //3. Tìm DataSource 
        DataSource ds = (DataSource) tomcatContext.lookup("PD");
        //4. Open Connection 
        Connection con = ds.getConnection();
        return con;
    }

    public static Properties getSiteMaps(String siteMapFile, ServletContext context)
            throws IOException {
        if (siteMapFile == null) {
            return null;
        }
        if (siteMapFile.trim().isEmpty()) {
            return null;
        }
        Properties result = new Properties();
        InputStream is = null;
        try {
            is = context.getResourceAsStream(siteMapFile);
            result.load(is);
            return result;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
}
