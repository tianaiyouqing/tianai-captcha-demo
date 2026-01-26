package cloud.tianai.captcha.demo;

import cloud.tianai.captcha.common.constant.CaptchaTypeConstant;
import cloud.tianai.captcha.resource.CrudResourceStore;
import cloud.tianai.captcha.resource.FontCache;
import cloud.tianai.captcha.resource.ResourceStore;
import cloud.tianai.captcha.resource.common.model.dto.Resource;
import cloud.tianai.captcha.resource.impl.LocalMemoryResourceStore;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Configuration
public class CaptchaResourceConfiguration {

    @Bean
    public ResourceStore resourceStore() {
        // 使用简单的本地内存存储器，实际项目中可以使用数据库等存储
        LocalMemoryResourceStore resourceStore = new LocalMemoryResourceStore();

        // 2. 添加自定义背景图片
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/a.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/b.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/c.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/d.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/e.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/g.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/h.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/i.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/j.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.ROTATE, new Resource("classpath", "bgimages/48.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.CONCAT, new Resource("classpath", "bgimages/48.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.WORD_IMAGE_CLICK, new Resource("classpath", "bgimages/c.jpg", "default"));

        return resourceStore;
    }
}
