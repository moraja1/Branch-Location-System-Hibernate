package application;

import data.hibernate.HibernateUtility;
import org.hibernate.Session;

public class Application {
    public static void main(String[] args) {
        Session session = HibernateUtility.getSessionFactory().openSession();
    }
}
