package com.simple.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SuppressWarnings("ALL")
@RestController
public class RootRestService {

    private final String LOCALHOST_IPV4 = "127.0.0.1";
    private final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

    @RequestMapping("/")
    public String ok(HttpServletRequest request) {
        return "OK";
    }

    @GetMapping("/echo/{who}")
    public String hello(@PathVariable("who") String who) {
        return who + "";
    }

    @RequestMapping("/ip")
    public String ip(HttpServletRequest request) {
        return getClientIp(request);
    }

    public String getClientIp(HttpServletRequest request) {

        String ipAddress = request.getRemoteAddr();
        if (LOCALHOST_IPV4.equals(ipAddress) || LOCALHOST_IPV6.equals(ipAddress)) {
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                ipAddress = inetAddress.getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }

        return ipAddress;
    }


}
