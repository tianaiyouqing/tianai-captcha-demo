package cloud.tianai.captcha.demo;

import cloud.tianai.captcha.slider.SliderCaptchaApplication;
import cloud.tianai.captcha.vo.CaptchaResponse;
import cloud.tianai.captcha.vo.SliderCaptchaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
@SpringBootApplication
public class TianaiCaptchaDemoApplication {


    @Autowired
    private SliderCaptchaApplication sliderCaptchaApplication;


    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/gen")
    @ResponseBody
    public CaptchaResponse<SliderCaptchaVO> genCaptcha() {
        CaptchaResponse<SliderCaptchaVO> response = sliderCaptchaApplication.generateSliderCaptcha();
        return response;
    }


    @GetMapping("/check")
    @ResponseBody
    public boolean checkCaptcha(@RequestParam("id") String id, @RequestParam("percentage") Float percentage) {
        boolean matching = sliderCaptchaApplication.matching(id, percentage);
        return matching;
    }



    public static void main(String[] args) {
        SpringApplication.run(TianaiCaptchaDemoApplication.class, args);
    }

}
