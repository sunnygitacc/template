package com.sunilkata.template.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.sunilkata.template.config.guice.module.CustomInjectionModule;
import com.wordnik.swagger.jaxrs.config.BeanConfig;
import com.wordnik.swagger.jaxrs.listing.ApiListingResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.sunilkata.template.resources.TemplateResource;
import com.sunilkata.template.health.TemplateHealthCheck;
import org.glassfish.jersey.server.ServerProperties;


/**
 * Created by Sunil Kata on 7/30/2017.
 */
public class TemplateApplication extends Application<TemplateConfiguration> {

    private GuiceBundle<TemplateConfiguration> guiceBundle;

    public static void main(String[] args) throws Exception {
        new TemplateApplication().run(args);
    }

    @Override
    public String getName() {
        return "Template Application";
    }

    public void setGuiceBundle(GuiceBundle<TemplateConfiguration> guiceBundle) {
        this.guiceBundle = guiceBundle;
    }

    @Override
    public void initialize(Bootstrap<TemplateConfiguration> bootstrap) {
        guiceBundle = GuiceBundle.<TemplateConfiguration> newBuilder().addModule(new CustomInjectionModule(bootstrap))
                .setConfigClass(TemplateConfiguration.class)
                .enableAutoConfig(getClass().getPackage().getName(), "com.sunilkata.template.resources")
                .build();
        bootstrap.addBundle(guiceBundle);
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false, true)
                )
        );
        bootstrap.addBundle(new AssetsBundle("/swagger/", "/api1", "index.html"));
    }

    @Override
    public void run(TemplateConfiguration configuration,
                    Environment environment) {
        final TemplateResource resource = new TemplateResource();
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        environment.jersey().register(new ApiListingResource());
        environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        environment.jersey().property(ServerProperties.PROCESSING_RESPONSE_ERRORS_ENABLED, true);
        new AssetsBundle("/swagger", "/swagger", "index.html", "swagger-assets").run(environment);

        BeanConfig config = new BeanConfig();
        Injector injector = guiceBundle.getInjector();

        // api specific configuration
        config.setTitle("Template  ");
        config.setVersion("1.0.0");
        config.setResourcePackage("com.sunilkata.template.resources");
        config.setScan(true);
    }

}