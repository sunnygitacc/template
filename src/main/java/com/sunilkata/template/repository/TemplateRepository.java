package com.sunilkata.template.repository;

import com.google.common.collect.Lists;
import com.sunilkata.template.config.TemplateConfiguration;
import com.sunilkata.template.model.ModelObject;
import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Sunil Kata on 8/14/2017.
 */
public class TemplateRepository extends AbstractDAO<ModelObject> {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static TemplateRepository getInstance(HibernateBundle<TemplateConfiguration> hibernateBundle) {
        return new TemplateRepository(hibernateBundle);
    }

    public TemplateRepository(HibernateBundle<TemplateConfiguration> hibernateBundle) {
        super(hibernateBundle.getSessionFactory());
        this.sessionFactory = hibernateBundle.getSessionFactory();
    }

    public List<String> getAllTestIds() {
        return namedQuery("fetchTest").list();
//        List<String> result = Lists.newArrayList();
//        for(ModelObject testId : testIds) {
//            result.add(String.valueOf(testId.getTestString()));
//        }
//        return result;
    }

    public int insertTestId(String testId) {
        int result = namedQuery("insertTest").setString("testString", testId).executeUpdate();
        return result;
    }
}
