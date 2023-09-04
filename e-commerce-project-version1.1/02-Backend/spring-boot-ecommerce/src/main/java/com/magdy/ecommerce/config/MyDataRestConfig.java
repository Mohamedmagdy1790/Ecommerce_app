package com.magdy.ecommerce.config;

import com.magdy.ecommerce.entity.Country;
import com.magdy.ecommerce.entity.Product;
import com.magdy.ecommerce.entity.ProductCategory;
import com.magdy.ecommerce.entity.State;
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
// we wnt to allow only specifc methods for whoever client takes to us
@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    EntityManager entityManager;

    @Autowired
    MyDataRestConfig(EntityManager entityManager){this.entityManager=entityManager;}

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[]  theunsupportedactions = {HttpMethod.POST,HttpMethod.PUT,HttpMethod.DELETE};
        //disable http methods put.post delete update allow only read or get
        disableHttpMethods(Product.class,config, theunsupportedactions);
        disableHttpMethods(ProductCategory.class,config, theunsupportedactions);
        disableHttpMethods(State.class,config, theunsupportedactions);
        disableHttpMethods(Country.class,config, theunsupportedactions);


        exposeIds(config);

    }

    private static void disableHttpMethods(Class theclass,RepositoryRestConfiguration config, HttpMethod[] theunsupportedactions) {
        config.getExposureConfiguration().forDomainType(theclass).withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theunsupportedactions)))
                                                                              .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theunsupportedactions));
    }


    //all entity classes return json without id so we want to configure it to expose id field with all entities
    private void exposeIds(RepositoryRestConfiguration config) {

        Set<EntityType<?>> entites = entityManager.getMetamodel().getEntities();

        List<Class> entityclasses =new ArrayList<>();

        for(EntityType temp : entites){
            entityclasses.add(temp.getJavaType());
        }
        Class[] domaintypes =entityclasses.toArray(new Class[0]);
        config.exposeIdsFor(domaintypes);
    }

}
