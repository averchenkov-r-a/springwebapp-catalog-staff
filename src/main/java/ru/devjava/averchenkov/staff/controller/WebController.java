package ru.devjava.averchenkov.staff.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер статики.
 *
 * @author Averchenkov R.A.
 */
@Controller
public class WebController {
    @RequestMapping(value = {"/", "/home", "/welcome"})
    public String getHome(){
        return "home";
    }
}
