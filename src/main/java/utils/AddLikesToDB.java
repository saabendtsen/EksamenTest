package utils;

import entities.MovieLikes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AddLikesToDB {

    public MovieLikes addlikeToMovie(String id,long likes) {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        MovieLikes like;
        try {
            em.getTransaction().begin();
            like = em.find(MovieLikes.class, id);
            if (like == null) {
                MovieLikes movieLikes = new MovieLikes(id, likes);
                em.persist(movieLikes);
                like = movieLikes;
            } else {
                like.setQuantity(like.getQuantity() + 1);
                em.merge(like);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return like;
    }
}
