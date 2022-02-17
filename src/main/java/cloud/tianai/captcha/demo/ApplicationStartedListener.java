package cloud.tianai.captcha.demo;

import cloud.tianai.captcha.slider.SliderCaptchaApplication;
import cloud.tianai.captcha.template.slider.Resource;
import cloud.tianai.captcha.template.slider.ResourceStore;
import cloud.tianai.captcha.template.slider.SliderCaptchaConstant;
import cloud.tianai.captcha.template.slider.SliderCaptchaResourceManager;
import cloud.tianai.captcha.template.slider.provider.ClassPathResourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static cloud.tianai.captcha.template.slider.StandardSliderCaptchaTemplate.DEFAULT_SLIDER_IMAGE_TEMPLATE_PATH;

@Component
public class ApplicationStartedListener implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    private SliderCaptchaApplication sliderCaptchaApplication;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        SliderCaptchaResourceManager sliderCaptchaResourceManager = sliderCaptchaApplication.getSliderCaptchaResourceManager();
        ResourceStore resourceStore = sliderCaptchaResourceManager.getResourceStore();
        // 清除内置的背景图片
        resourceStore.clearResources();
//        resourceStore.clearTemplates();
        Map<String, Resource> template2 = new HashMap<>(4);
        template2.put(SliderCaptchaConstant.TEMPLATE_ACTIVE_IMAGE_NAME, new Resource(ClassPathResourceProvider.NAME, "captcha-template/bb.png"));
        template2.put(SliderCaptchaConstant.TEMPLATE_FIXED_IMAGE_NAME, new Resource(ClassPathResourceProvider.NAME, "captcha-template/aa.png"));
        template2.put(SliderCaptchaConstant.TEMPLATE_MATRIX_IMAGE_NAME, new Resource(ClassPathResourceProvider.NAME, DEFAULT_SLIDER_IMAGE_TEMPLATE_PATH.concat("/2/matrix.png")));
        // 让新模板出现的概率变大
        resourceStore.addTemplate(template2);
        resourceStore.addTemplate(template2);
        resourceStore.addTemplate(template2);
        resourceStore.addTemplate(template2);
        resourceStore.addTemplate(template2);

        // 添加自定义背景图片
        resourceStore.addResource(new Resource("classpath", "bgimages/a.jpg"));
        resourceStore.addResource(new Resource("classpath", "bgimages/b.jpg"));
        resourceStore.addResource(new Resource("classpath", "bgimages/c.jpg"));
        resourceStore.addResource(new Resource("classpath", "bgimages/d.jpg"));
        resourceStore.addResource(new Resource("classpath", "bgimages/e.jpg"));
        resourceStore.addResource(new Resource("classpath", "bgimages/g.jpg"));
        resourceStore.addResource(new Resource("classpath", "bgimages/h.jpg"));
        resourceStore.addResource(new Resource("classpath", "bgimages/i.jpg"));
        resourceStore.addResource(new Resource("classpath", "bgimages/j.jpg"));
    }
}
