package com.yberdaliyev;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yerlan on 06.03.2017.
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showBasePage( ) {
        return "home";
    }
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String showTestPage(Model model) {
        model.addAttribute("myText","My super text");
        return "text";
    }
    @RequestMapping(value = "/text", method = RequestMethod.POST)
    public String showPostMessage(Model model,
                                  @RequestParam(name = "param1", defaultValue = "1") String param) {
        model.addAttribute("param1",param);
        return "text";
    }
    public ModelAndView showModePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("text");
        modelAndView.addObject("car", new Car(1000,"bmw","re222x"));
        return modelAndView;
    }
}
