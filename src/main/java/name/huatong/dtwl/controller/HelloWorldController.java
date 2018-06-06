package name.huatong.dtwl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot HelloWorld案例
 *
 * Created by tong.hua on 2018-06-06.
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/")
    public String sayHello() {
        return "Hello,World!";
    }
}