//package utils;
//
//import javax.mail.Session;
//import java.lang.module.Configuration;
//
///**
// * @author Sergey Klunniy
// */
//public class HibernateSessionFactoryUtil {
//
//    private static SessionFactory sessionFactory;
//
//    private HibernateSessionFactoryUtil() {
//    }
//
//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            try{
//                Configuration configuration = new Configuration().configure();
//                configuration.addAnnotatedClass(User.class);
//                StandartServiceRegistryBuilder builder = new StandartServiceRegistryBuilder().applySettings(...)
//                sessionFactory = configuration.buildSessionFactory(builder.build());
//
//            } catch (Exception e) {
//                System.out.println("Исключение!" + e);
//            }
//
//
//        }
//    }
//}
