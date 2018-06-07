package name.huatong.dtwl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring Boot HelloWorld案例
 *
 * Created by tong.hua on 2018-06-06.
 */
@Controller
public class HelloWorldController {

    @RequestMapping("/")
    public String sayHello() {
        return "redirect:/index.html";
    }
}