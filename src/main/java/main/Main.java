package main;

import base.AccountService;
import base.DBService;
import dbService.DBServiceImplements;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AccountServiceImplement;
import servlets.SignInServlet;
import servlets.SignUpServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        DBService dbService = new DBServiceImplements();
        dbService.create();
        dbService.check();

        AccountServiceImplement accountServiceImplement = new AccountServiceImplement(dbService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignInServlet(accountServiceImplement)),"/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(accountServiceImplement)),"/signup");
        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }
}
