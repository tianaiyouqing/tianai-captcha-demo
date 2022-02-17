package cloud.tianai.captcha.demo;

import cloud.tianai.captcha.slider.SliderCaptchaApplication;
import cloud.tianai.captcha.template.slider.validator.SliderCaptchaTrack;
import cloud.tianai.captcha.vo.CaptchaResponse;
import cloud.tianai.captcha.vo.SliderCaptchaVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Slf4j
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
    public CaptchaResponse<SliderCaptchaVO> genCaptcha(HttpServletRequest request) {
        CaptchaResponse<SliderCaptchaVO> response = sliderCaptchaApplication.generateSliderCaptcha();
        return response;
    }

    @PostMapping("/check")
    @ResponseBody
    public boolean checkCaptcha(@RequestParam("id") String id,
                                @RequestBody SliderCaptchaTrack sliderCaptchaTrack,
                                HttpServletRequest request) {
        return sliderCaptchaApplication.matching(id, sliderCaptchaTrack);
    }


    public static void main(String[] args) {
        SpringApplication.run(TianaiCaptchaDemoApplication.class, args);
    }

}
