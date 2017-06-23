package ru.yradio.service;

import org.apache.log4j.Logger;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.stereotype.Service;
import java.net.URL;
@Service
public class SendStop {
    private static final Logger log = Logger.getLogger(SendStop.class);

    public boolean go() {
        //return true;
        try{
            log.debug("Getting RPC server configuration...");
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("http://10.22.99.73:8080/RPC2/"));
            config.setConnectionTimeout(15000);
            config.setReplyTimeout(15000);
            XmlRpcClient server = new XmlRpcClient();
            server.setConfig(config);
            log.debug("Sending command to stop playing track...");
            server.execute("stop_urge_message", new Object[]{});
            log.debug("Command STOP have sent successfully!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Something FAILED: ", e);
            return false;
        }
    }
}