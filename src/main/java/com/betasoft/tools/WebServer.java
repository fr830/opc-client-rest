package com.betasoft.tools;

import javafish.clients.opc.JOpc;
import javafish.clients.opc.exception.CoInitializeException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;

/**
 * Description:
 * <p>
 * Created by A.T on 2018/03/07
 */
public class WebServer {
    static final int port = 9001;
    static final String SERVER_ROOT = Class.class.getResource("/webroot").getPath();
    static final String APP_ROOT = Class.class.getResource("/apps").getPath();
    public static void main(String[] args) throws Exception {
        // Step 1 Init JOpc
        try {
            JOpc.coInitialize();
        }
        catch (CoInitializeException e1) {
            e1.printStackTrace();
            System.exit(0);
        }

        // Step 2 Start web server
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.setBaseDir(SERVER_ROOT);
        tomcat.getHost().setAutoDeploy(false);

        String contextPath = ""; // do not use "/"
        StandardContext context = new StandardContext();
        context.setPath(contextPath); // Set the context path for this Context.
        context.setDocBase(SERVER_ROOT); // Set the document root for this Context.
        context.addLifecycleListener(new Tomcat.FixContextListener());
        tomcat.getHost().addChild(context);

        /**
         *  Use DefaultServlet for fetching static resources ：
         *
         *      http://localhost:8080/static/js/hello.js
         */
        tomcat.addServlet(contextPath, "defaultServlet", new DefaultServlet());
        context.addServletMappingDecoded("/static/*", "defaultServlet");

        tomcat.addWebapp("/opc",APP_ROOT+"/opc");

        tomcat.start();
        tomcat.getServer().await();
    }
}
