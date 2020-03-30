/**
 * Created by IntelliJ IDEA.
 * User: migusdn
 * Date: 20. 3. 30.
 * Time: 오후 10:33
 * GitHub: https://migusdn.github.com/
 * Blog: https://migusdn.tistory.com/
 */
package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by NESOY on 2017-02-04.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String test(){
        return "uploadAjax";
    }
}
