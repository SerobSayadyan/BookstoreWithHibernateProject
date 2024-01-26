package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateConfig {

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    private HibernateConfig(){};

    public static SessionFactory getSessionFactory() {
        synchronized (Object.class) {
            if (sessionFactory == null) {
                try {
                    registry = new StandardServiceRegistryBuilder().configure().build();

                    Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();

                    sessionFactory = metadata.getSessionFactoryBuilder().build();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            return sessionFactory;
        }
    }

}
