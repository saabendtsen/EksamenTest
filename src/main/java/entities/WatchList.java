package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "watch_list")
@Entity
public class WatchList {
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "watchlater_imdb_id")
    private String watchLaterImdbId;

    @ManyToMany(mappedBy = "watchList")
    private List<User> userList;

    public WatchList(){

    }

    public WatchList(String watchLaterImdbId) {
        this.watchLaterImdbId = watchLaterImdbId;
    }

    public String getWatchLaterImdbId() {
        return watchLaterImdbId;
    }

    public void setWatchLaterImdbId(String watchLaterImdbId) {
        this.watchLaterImdbId = watchLaterImdbId;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }


}