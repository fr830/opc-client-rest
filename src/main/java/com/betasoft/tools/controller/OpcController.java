package com.betasoft.tools.controller;

import javafish.clients.opc.JEasyOpc;
import javafish.clients.opc.component.OpcGroup;
import javafish.clients.opc.component.OpcItem;
import javafish.clients.opc.exception.ConnectivityException;
import org.springframework.web.bind.annotation.*;


/**
 * Description:
 * <p>
 * Created by A.T on 2018/03/07
 */
@RestController
@RequestMapping("/tools")
public class OpcController {
    @GetMapping
    public String sayHello() {
        return "Hello from Spring5 and embedded Tomcat8!";
    }

    @GetMapping("/ping")
    public String ping(String ip, String server) {
        JEasyOpc jopc = new JEasyOpc(ip, server, "");

        try {
            jopc.connect();
        } catch (ConnectivityException e) {
            e.printStackTrace();
        }

        boolean status = jopc.ping();
        jopc.terminate();

        return status ? "true" : "false";
    }

    @PostMapping("/read")
    public String read(String ip, String server, String group, String item) {
        String result = "null";
        JEasyOpc jopc = new JEasyOpc(ip, server, "");

        OpcGroup opcGroup = new OpcGroup(group, true, 2000, 0.0f);
        opcGroup.addItem(new OpcItem(item, true, ""));
        jopc.addGroup(opcGroup);

        try {
            jopc.connect();
            jopc.registerGroups();

            OpcGroup resultGroup = jopc.synchReadGroup(opcGroup);
            result = resultGroup.getItems().get(0).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        jopc.terminate();
        return result;
    }
}
