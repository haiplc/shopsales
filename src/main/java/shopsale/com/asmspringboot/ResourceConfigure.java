package shopsale.com.asmspringboot;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ResourceConfigure {
	@Bean("messageSource")
	public MessageSource getMessageResource() {
		ReloadableResourceBundleMessageSource mSource = new ReloadableResourceBundleMessageSource();
		mSource.setBasenames("classpath:i18n/message", "classpath:i18n/index");
		mSource.setDefaultEncoding("utf-8");
		return mSource;
	}
}
