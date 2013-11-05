package Controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//import com.studentarchives.model.database.DatabaseFactory;

public class DatabaseListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

//	public void contextDestroyed(ServletContextEvent arg0) {
//		try {
//			DatabaseFactory.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		System.out.println("[DatabaseFactory closed...]");
//	}
//
//	public void contextInitialized(ServletContextEvent arg0) {
//		 try {
//			DatabaseFactory.registerFromDefaultProperties();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("[DatabaseFactory register...]");
//
//	}

}
