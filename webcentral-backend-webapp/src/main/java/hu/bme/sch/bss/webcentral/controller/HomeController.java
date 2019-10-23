package hu.bme.sch.bss.webcentral.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class HomeController {

    private static final String WELCOME = "Hello";

    @RequestMapping("/")
    public final String home(@RequestBody final String name) {
        return WELCOME + name;
    }

}
