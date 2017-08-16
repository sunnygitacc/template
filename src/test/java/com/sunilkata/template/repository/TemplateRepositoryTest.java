package com.sunilkata.template.repository;

import com.sunilkata.template.config.TemplateConfiguration;
import com.sunilkata.template.model.ModelObject;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import junit.DAOTestRule;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by Sunil Kata on 8/14/2017.
 */
public class TemplateRepositoryTest {
    @Rule
    public DAOTestRule daoTestRule = DAOTestRule.newBuilder().addEntityClass(ModelObject.class).build();

    private TemplateRepository templateRepository;

    @Before
    public void setup() throws Exception {
        HibernateBundle<TemplateConfiguration> hibernateBundle = new HibernateBundle<TemplateConfiguration>(ModelObject.class) {
            @Override
            public DataSourceFactory getDataSourceFactory(TemplateConfiguration templateConfiguration) {
                return templateConfiguration.getDatabase();
            }

            @Override
            public SessionFactory getSessionFactory() { return daoTestRule.getSessionFactory();}
        };
        templateRepository = TemplateRepository.getInstance(hibernateBundle);
    }

    @Test
    public void testForGetTestIds() {
        System.out.println(templateRepository.getAllTestIds());
    }
}