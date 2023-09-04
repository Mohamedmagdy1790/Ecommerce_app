package com.magdy.ecommerce.config;

import com.magdy.ecommerce.entity.Product;
import com.magdy.ecommerce.entity.ProductCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    EntityManager entityManager;

    @Autowired
    MyDataRestConfig(EntityManager entityManager){this.entityManager=entityManager;}

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[]  theunsupportedactions = {HttpMethod.POST,HttpMethod.PUT,HttpMethod.DELETE};

        config.getExposureConfiguration().forDomainType(Product.class).withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theunsupportedactions)))
                                                                      .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theunsupportedactions));

        config.getExposureConfiguration().forDomainType(ProductCategory.class).withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theunsupportedactions)))
                                                                              .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theunsupportedactions));

        exposeids(config);

    }

    private void exposeids(RepositoryRestConfiguration config) {

        Set<EntityType<?>> entites = entityManager.getMetamodel().getEntities();

        List<Class> entityclasses =new ArrayList<>();

        for(EntityType temp : entites){
            entityclasses.add(temp.getJavaType());
        }
        Class[] domaintypes =entityclasses.toArray(new Class[0]);
        config.exposeIdsFor(domaintypes);
    }

}
