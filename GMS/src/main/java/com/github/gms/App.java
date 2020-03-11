package com.github.gms;

import com.github.gms.controller.GmsController;
import com.github.gms.service.ServiceLayer;
import com.github.gms.service.ServiceLayerFileImpl;
import com.github.gms.ui.GMSView;
import com.github.gms.ui.UserIO;
import com.github.gms.ui.UserIOConsoleImpl;

/**
 * GMS Main
 *
 */
public class App {
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        GMSView view = new GMSView(io);
        ServiceLayer service = new ServiceLayerFileImpl();
        GmsController controller = new GmsController(view, service);
        controller.execute();
    }
}
