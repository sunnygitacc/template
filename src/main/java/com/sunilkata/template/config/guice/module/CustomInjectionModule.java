package com.sunilkata.template.config.guice.module;

import com.google.inject.AbstractModule;
import com.sunilkata.template.config.TemplateConfiguration;
import io.dropwizard.setup.Bootstrap;

/**
 * Created by Sunil Kata on 7/30/2017.
 */
public class CustomInjectionModule extends AbstractModule{

    public CustomInjectionModule(Bootstrap<TemplateConfiguration> bootstrap) {
    }

    @Override
    protected void configure() {

    }
}
