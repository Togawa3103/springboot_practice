package com.kintaisystem.kintaisystem;

import java.nio.charset.StandardCharsets;

import org.hibernate.validator.messageinterpolation.AbstractMessageInterpolator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web設定クラス。
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public Validator getValidator() {

    // ValidationMessages.propertiesをUTF-8で設定できるようにする。
    ReloadableResourceBundleMessageSource messageSource =
        new ReloadableResourceBundleMessageSource();
    messageSource.setBasename(AbstractMessageInterpolator.USER_VALIDATION_MESSAGES);
    messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());

    LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
    validator.setValidationMessageSource(messageSource);

    return validator;
  }
}
