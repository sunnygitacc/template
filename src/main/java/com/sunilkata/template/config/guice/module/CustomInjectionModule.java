package com.sunilkata.template.config.guice.module;

import com.google.inject.AbstractModule;
import com.sunilkata.template.config.TemplateConfiguration;
import com.sunilkata.template.model.ModelObject;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;

/**
 * Created by Sunil Kata on 7/30/2017.
 */
public class CustomInjectionModule extends AbstractModule{

    public final HibernateBundle<TemplateConfiguration> hibernateBundle = new HibernateBundle<TemplateConfiguration>(ModelObject.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(TemplateConfiguration templateConfiguration) {
            return templateConfiguration.getDatabase();
        }

        @Override
        protected String name() {
            return "hibernate.Oracle";
        }
    };

    public CustomInjectionModule(Bootstrap<TemplateConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    protected void configure() {

    }
}
