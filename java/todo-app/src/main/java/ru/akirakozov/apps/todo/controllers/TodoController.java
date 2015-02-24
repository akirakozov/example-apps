package ru.akirakozov.apps.todo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author akirakozov
 */
@Controller
public class TodoController {
    @RequestMapping(value = "/todo", method = RequestMethod.GET)
    public String getTodo(ModelMap map, @RequestParam("name") String name) {
        map.addAttribute("name", name);
        return "todo";
    }
}
