package ch.zhaw.deeplearningjava.playground;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class SentimentController {
    
    @GetMapping("/sentiment")
    public String predict(@RequestParam(name="text", required = true) String text) throws Exception {
        var result = SentimentAnalysis.predict(text);
        return result.toJson();
    }
    
    
    @GetMapping("/ping")
    public String ping() {
        return "Sentiment app is up and running";
    }

    @GetMapping("/count")
    public int count() {
        return 42;
    }
    
}
