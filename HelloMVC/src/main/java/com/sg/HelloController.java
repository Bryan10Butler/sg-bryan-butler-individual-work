package com.sg;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// #1 - Controller annotation
@Controller
// #2 - Request Mapping annotation
@RequestMapping({"/hello"})
public class HelloController {
        
    public HelloController() {
    }
    // #3 - Request Mapping annotation
    @RequestMapping(value="/sayhi", method=RequestMethod.GET)
    public String sayHi(Map<String, Object> model) {
        // #4 - Object put in the model are passed to the view
        model.put("message", "Hello from the controller" );
        // #5 - return the logical name of the View we want to use
        //      (this maps to /jsp/hello.jsp)
        return "hello";
    }
}
