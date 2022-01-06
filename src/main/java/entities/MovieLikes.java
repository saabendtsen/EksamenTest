package entities;

import javax.persistence.*;

@Table(name = "movie_likes")
@Entity
public class MovieLikes {
    @Id
    @Basic(optional = false)
    @Column(name = "imdbId", nullable = false)
    private String imdbId;
    @Column(name = "quantity", nullable = false)
    private Long quantity;

    public MovieLikes()  {}

    public MovieLikes( String imdbId, Long quantity) {
        this.imdbId = imdbId;
        this.quantity = quantity;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}