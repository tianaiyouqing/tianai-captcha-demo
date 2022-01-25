package cloud.tianai.captcha.demo;

import cloud.tianai.captcha.slider.SliderCaptchaApplication;
import cloud.tianai.captcha.vo.CaptchaResponse;
import cloud.tianai.captcha.vo.SliderCaptchaVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        CaptchaResponse<SliderCaptchaVO> response = sliderCaptchaApplication.generateSliderCaptcha();
        stopWatch.stop();
        // 打点日志记录
        log.info("[生成验证码], IP={}, id={}, 耗时={}ms", IPUtils.getIpAddr(request), response.getId(), stopWatch.getTotalTimeMillis());
        return response;
    }

    @GetMapping("/check")
    @ResponseBody
    public boolean checkCaptcha(@RequestParam("id") String id,
                                @RequestParam("percentage") Float percentage,
                                HttpServletRequest request) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        boolean matching = sliderCaptchaApplication.matching(id, percentage);
        stopWatch.stop();
        log.info("[校验验证码], IP={}, id={},percentage={},耗时={}ms", IPUtils.getIpAddr(request), id, percentage, stopWatch.getTotalTimeMillis());
        System.out.println("id=" + id + ",percentage=" + percentage);
        return matching;
    }


    public static void main(String[] args) {
        SpringApplication.run(TianaiCaptchaDemoApplication.class, args);
    }

}
