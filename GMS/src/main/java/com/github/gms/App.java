package com.github.gms;

import com.github.gms.controller.GmsController;
import com.github.gms.dao.GmsDao;
import com.github.gms.dao.GmsDaoFileImpl;
import com.github.gms.service.ServiceLayer;
import com.github.gms.service.ServiceLayerFileImpl;
import com.github.gms.ui.GMSView;
import com.github.gms.ui.UserIO;
import com.github.gms.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * GMS Main
 *
 */
public class App {
    public static void main(String[] args) throws Exception {
        //UserIO io = new UserIOConsoleImpl();
        //GMSView view = new GMSView(io);
        //GmsDao dao = new GmsDaoFileImpl();
        //ServiceLayer service = new ServiceLayerFileImpl(dao);
        //GmsController controller = new GmsController(view, service);
        //controller.execute();
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        GmsController control = ctx.getBean("control", GmsController.class);
        control.execute();
    }
}
