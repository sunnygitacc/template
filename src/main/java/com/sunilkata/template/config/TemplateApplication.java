package com.sunilkata.template.config;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.sunilkata.template.resources.TemplateResource;
import com.sunilkata.template.health.TemplateHealthCheck;

/**
 * Created by Sunil Kata on 7/30/2017.
 */
public class TemplateApplication extends Application<TemplateConfiguration> {
    public static void main(String[] args) throws Exception {
        new TemplateApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<TemplateConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(TemplateConfiguration configuration,
                    Environment environment) {
        final TemplateResource resource = new TemplateResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}