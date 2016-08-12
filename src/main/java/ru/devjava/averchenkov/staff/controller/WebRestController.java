package ru.devjava.averchenkov.staff.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest контроллер
 *
 * @author Averchenkov R.A.
 */
@RestController
@RequestMapping("/api")
public class WebRestController {
    @RequestMapping("/home")
    public String home(){
        return "{\"home\": \"available\"}";
    }
}
