package ru.yradio;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.yradio.service.SendPlay;
import ru.yradio.service.SendStop;

@Controller
public class MainController {
    @Autowired
    private SendPlay sendPlay;
    @Autowired
    private SendStop sendStop;

    private static final Logger log = Logger.getLogger(MainController.class);

    @RequestMapping("/")
    public String HomePage() {
        log.debug("Starting the index page...");
        return "index";
    }

    @RequestMapping(value = "/play", method = RequestMethod.GET)
    @ResponseBody
    public void sPlay() {
        log.debug("Controller sends command START to player...");
        if(!sendPlay.go())
        {
            log.error("!!!!Could not send command PLAY!!!!");
            return;
        }
        log.debug("Success!");
    }

    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    @ResponseBody
    public void sStop() {
        log.debug("Controller sends command STOP to player...");
        if(!sendStop.go())
        {
            log.error("!!!!Could not send command STOP!!!!");
            return;
        }
        log.debug("Success!");
    }
}