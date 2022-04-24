package cloud.tianai.captcha.demo;

import cloud.tianai.captcha.plugins.secondary.SecondaryVerificationApplication;
import cloud.tianai.captcha.slider.SliderCaptchaApplication;
import cloud.tianai.captcha.template.slider.generator.common.constant.CaptchaTypeConstant;
import cloud.tianai.captcha.template.slider.validator.common.model.dto.SliderCaptchaTrack;
import cloud.tianai.captcha.vo.CaptchaResponse;
import cloud.tianai.captcha.vo.SliderCaptchaVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    public CaptchaResponse<SliderCaptchaVO> genCaptcha(HttpServletRequest request, @RequestParam(value = "type", required = false)String type) {
        if (StringUtils.isBlank(type)) {
            type = CaptchaTypeConstant.SLIDER;
        }
        CaptchaResponse<SliderCaptchaVO> response = sliderCaptchaApplication.generateSliderCaptcha(type);
        return response;
    }

    @PostMapping("/check")
    @ResponseBody
    public boolean checkCaptcha(@RequestParam("id") String id,
                                @RequestBody SliderCaptchaTrack sliderCaptchaTrack,
                                HttpServletRequest request) {
        return sliderCaptchaApplication.matching(id, sliderCaptchaTrack);
    }

    /**
     * 二次验证，一般用于机器内部调用，这里为了方便测试
     * @param id id
     * @return boolean
     */
    @GetMapping("/check2")
    @ResponseBody
    public boolean check2Captcha(@RequestParam("id") String id) {
        // 如果开启了二次验证
        if (sliderCaptchaApplication instanceof SecondaryVerificationApplication) {
            return ((SecondaryVerificationApplication)sliderCaptchaApplication).secondaryVerification(id);
        }
        return false;
    }


    public static void main(String[] args) {
        SpringApplication.run(TianaiCaptchaDemoApplication.class, args);
    }

}
