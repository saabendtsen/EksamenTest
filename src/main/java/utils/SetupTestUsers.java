package utils;


import entities.Role;
import entities.User;
import entities.WatchList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupTestUsers {

  public static void main(String[] args) {
    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    User user = new User("user", "user1");
    User user1 = new User("user1", "user1");
    User admin = new User("admin", "admin1");

    try {
      em.getTransaction().begin();
      Role userRole = new Role("user");
      Role adminRole = new Role("admin");
      WatchList watchList = new WatchList("tt4972582"); // Split
      WatchList watchList1 = new WatchList("tt11126994"); // Arcane
      user.addRole(userRole);
      user1.addRole(userRole);
      admin.addRole(adminRole);
      user.addToWatchList(watchList);
      user.addToWatchList(watchList1);
      user1.addToWatchList(watchList);
      user1.addToWatchList(watchList1);
      em.persist(userRole);
      em.persist(adminRole);
      em.persist(watchList);
      em.persist(watchList1);
      em.persist(user);
      em.persist(user1);
      em.persist(admin);
      em.getTransaction().commit();
      System.out.println("Users Created!");
    } catch (Exception e){
      System.out.println(e.getMessage());
    }
  }
}
