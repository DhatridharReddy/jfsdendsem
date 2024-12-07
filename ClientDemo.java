package com.klef.jfsd.exam;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
public class ClientDemo {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Customer.class);

        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();

        Transaction t = session.beginTransaction();

        Customer cust1 = new Customer();
        cust1.setName("sailesh");
        cust1.setEmail("sailesh@gmail.com");
        cust1.setAge(26);
        cust1.setLocation("chennai");
        
       System.out.println("inserted");
        session.persist(cust1);

        t.commit();

        Criteria cr = session.createCriteria(Customer.class);

        cr.add(Restrictions.eq("location", "New York"));
        System.out.println("Customers from New York:");
        List<Customer> customers = cr.list();
        for (Customer c : customers) {
            System.out.println(c.getName() + " - " + c.getEmail());
        }
        cr = session.createCriteria(Customer.class);
        cr.add(Restrictions.ne("age", 30));
        System.out.println("\nCustomers not aged 30:");
        customers = cr.list();
        for (Customer c : customers) {
            System.out.println(c.getName() + " - " + c.getAge());
        }
        cr = session.createCriteria(Customer.class);
        cr.add(Restrictions.gt("age", 25));
        System.out.println("\nCustomers older than 25:");
        customers = cr.list();
        for (Customer c : customers) {
            System.out.println(c.getName() + " - " + c.getAge());
        }
        cr = session.createCriteria(Customer.class);
        cr.add(Restrictions.between("age", 25, 30));
        System.out.println("\nCustomers aged between 25 and 30:");
        customers = cr.list();
        for (Customer c : customers) {
            System.out.println(c.getName() + " - " + c.getAge());
        }

        cr = session.createCriteria(Customer.class);
        cr.add(Restrictions.like("name", "A%"));
        System.out.println("\nCustomers whose name starts with 'A':");
        customers = cr.list();
        for (Customer c : customers) {
            System.out.println(c.getName());
        }

        session.close();
        sf.close();
    }
}
