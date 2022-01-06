package dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImdbResponseDTO {

    public String variant;
    public List<Result> results;
    public Date updated;
    public String term;
    public int status_code;

    public ImdbResponseDTO(String variant, List<Result> results, Date updated, String term, int status_code) {
        this.variant = variant;
        this.results = results;
        this.updated = updated;
        this.term = term;
        this.status_code = status_code;
    }

    public ImdbResponseDTO() {
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public class Location{
        public String display_name;
        public String id;
        public String url;
        public String name;
        public String icon;

        public Location(String display_name, String id, String url, String name, String icon) {
            this.display_name = display_name;
            this.id = id;
            this.url = url;
            this.name = name;
            this.icon = icon;
        }

        public String getDisplay_name() {
            return display_name;
        }

        public void setDisplay_name(String display_name) {
            this.display_name = display_name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    public class Imdb{
        public String url;
        public String id;

        public Imdb(String url, String id) {
            this.url = url;
            this.id = id;
        }
    }

    public class Tmdb{
        public String url;
        public String id;

        public Tmdb(String url, String id) {
            this.url = url;
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public String getId() {
            return id;
        }
    }

    public class Iva{
        public String id;

        public Iva(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    public class WikiData{
        public String url;
        public String id;

        public WikiData(String url, String id) {
            this.url = url;
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public String getId() {
            return id;
        }
    }

    public class ExternalIds{
        public Imdb imdb;
        public Tmdb tmdb;
        public Iva iva;
        public Object facebook;
        public Object rotten_tomatoes;
        public WikiData wiki_data;
        public Object iva_rating;
        public Object gracenote;

        public ExternalIds(Imdb imdb, Tmdb tmdb, Iva iva, Object facebook, Object rotten_tomatoes, WikiData wiki_data, Object iva_rating, Object gracenote) {
            this.imdb = imdb;
            this.tmdb = tmdb;
            this.iva = iva;
            this.facebook = facebook;
            this.rotten_tomatoes = rotten_tomatoes;
            this.wiki_data = wiki_data;
            this.iva_rating = iva_rating;
            this.gracenote = gracenote;
        }

        public Imdb getImdb() {
            return imdb;
        }

        public Tmdb getTmdb() {
            return tmdb;
        }

        public Iva getIva() {
            return iva;
        }

        public Object getFacebook() {
            return facebook;
        }

        public Object getRotten_tomatoes() {
            return rotten_tomatoes;
        }

        public WikiData getWiki_data() {
            return wiki_data;
        }

        public Object getIva_rating() {
            return iva_rating;
        }

        public Object getGracenote() {
            return gracenote;
        }
    }

    public class Result{
        public List<Location> locations;
        public int weight;
        public String id;
        public ExternalIds external_ids;
        public String picture;
        public String provider;
        public String name;

        public Result(List<Location> locations, int weight, String id, ExternalIds external_ids, String picture, String provider, String name) {
            this.locations = locations;
            this.weight = weight;
            this.id = id;
            this.external_ids = external_ids;
            this.picture = picture;
            this.provider = provider;
            this.name = name;
        }

        public List<Location> getLocations() {
            return locations;
        }

        public int getWeight() {
            return weight;
        }

        public String getId() {
            return id;
        }

        public ExternalIds getExternal_ids() {
            return external_ids;
        }

        public String getPicture() {
            return picture;
        }

        public String getProvider() {
            return provider;
        }

        public String getName() {
            return name;
        }
    }
}
