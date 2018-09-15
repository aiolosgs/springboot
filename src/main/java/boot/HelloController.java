package boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("boot")
public class HelloController {

	@RequestMapping("hello")
	public String index(){
		return "hello world!";
	}
	
	@RequestMapping("farewell")
	public String farewell(){
		String ret = "see ya";
		return ret;
	}
}
