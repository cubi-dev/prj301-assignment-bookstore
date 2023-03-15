/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungnv.listener;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import dungnv.utils.DBHelpers;
import dungnv.utils.PropertiesFileHelper;

/**
 * Web application lifecycle listener.
 *
 * @author ASUS
 */
public class MyContextServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Deploying................");
        ServletContext context = sce.getServletContext();
        String siteMapFile = context.getInitParameter("SITE_MAP_FILE");
        try {
            Properties siteMaps = DBHelpers.getSiteMaps(siteMapFile, context);
            context.setAttribute("SITE_MAPS", siteMaps);
        } catch (IOException ex) {
            context.log("Context Listener IO "+ ex.getMessage());
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("RIP");
    }
}
