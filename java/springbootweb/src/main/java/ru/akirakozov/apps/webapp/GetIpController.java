package ru.akirakozov.apps.webapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author akirakozov
 */
@RestController
public class GetIpController {
    @RequestMapping("/")
    public String index(@RequestParam(name = "name", required =  false) String name) throws UnknownHostException {
        String greetingName = name == null ? "guest" : name;
        return "Hi, " + greetingName + "!\n" +
                "My Ip: " + InetAddress.getLocalHost().getHostAddress();
    }
}
