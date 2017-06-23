package ru.yradio.service;

import org.apache.log4j.Logger;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.stereotype.Service;
import java.net.URL;

@Service
public class SendPlay {
    private static final Logger log = Logger.getLogger(SendPlay.class);

    public boolean go() {
        try{
            log.info("Getting RPC server configuration...");
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            URL url = new URL("http://10.22.99.73:8080/RPC2/");
            config.setServerURL(url);
            log.debug("Server URL: " + url.toString());
            config.setConnectionTimeout(15000);
            config.setReplyTimeout(15000);
            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);
            log.debug("Sending command to play track...");
            //String result = (String) client.execute("play_urge_message", new Object[]{16422});
            client.execute("play_urge_message", new Object[]{16422});
            //log.info("RESULT IS: " + result);
            log.info("Command PLAY have sent successfully!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Something FAILED: ", e);
            return false;
        }
    }
}