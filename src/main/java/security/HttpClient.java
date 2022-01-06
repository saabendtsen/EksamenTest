package security;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClient {

    // one instance, reuse
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    String ApiID = "https://movie-database-imdb-alternative.p.rapidapi.com/?r=json&i=id";

    public void close() throws IOException {
        httpClient.close();
    }

    public String sendGet(String search, Boolean isID) throws Exception {
        String res = gson.toJson("NotFound : Movie not Found!");
        HttpGet request;
        search = search.replace(" ", "%20");
        boolean test = true;


        if (test) {
            if (isID) {
                return isIDTest();
            } else {
                return byTitleTest();
            }

        } else {
            if (isID) {
                request = new HttpGet("https://movie-database-imdb-alternative.p.rapidapi.com/?r=json&i=" + search);
            } else {
                request = new HttpGet("https://utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com/lookup?term=%22" + search + "&country=us");
                System.out.println("URL from Http Client" + request);
            }
        }
        // request headers
        request.addHeader("x-rapidapi-key", "7bdff84145msh4480520f958f4ebp1f543fjsnda49759e8066");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                res = EntityUtils.toString(entity);
            }
        }
        return res;
    }

    public String isIDTest(){

        return "{\n" +
                "    \"Title\": \"The Shawshank Redemption\",\n" +
                "    \"Year\": \"1994\",\n" +
                "    \"Rated\": \"R\",\n" +
                "    \"Released\": \"14 Oct 1994\",\n" +
                "    \"Runtime\": \"142 min\",\n" +
                "    \"Genre\": \"Drama\",\n" +
                "    \"Director\": \"Frank Darabont\",\n" +
                "    \"Writer\": \"Stephen King, Frank Darabont\",\n" +
                "    \"Actors\": \"Tim Robbins, Morgan Freeman, Bob Gunton\",\n" +
                "    \"Plot\": \"Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.\",\n" +
                "    \"Language\": \"English\",\n" +
                "    \"Country\": \"United States\",\n" +
                "    \"Awards\": \"Nominated for 7 Oscars. 21 wins & 43 nominations total\",\n" +
                "    \"Poster\": \"https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg\",\n" +
                "    \"Ratings\": [\n" +
                "        {\n" +
                "            \"Source\": \"Internet Movie Database\",\n" +
                "            \"Value\": \"9.3/10\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Source\": \"Rotten Tomatoes\",\n" +
                "            \"Value\": \"91%\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Source\": \"Metacritic\",\n" +
                "            \"Value\": \"80/100\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"Metascore\": \"80\",\n" +
                "    \"imdbRating\": \"9.3\",\n" +
                "    \"imdbVotes\": \"2,485,751\",\n" +
                "    \"imdbID\": \"tt0111161\",\n" +
                "    \"Type\": \"movie\",\n" +
                "    \"DVD\": \"21 Dec 1999\",\n" +
                "    \"BoxOffice\": \"$28,699,976\",\n" +
                "    \"Production\": \"N/A\",\n" +
                "    \"Website\": \"N/A\",\n" +
                "    \"Response\": \"True\"\n" +
                "}";
    }


    public String byTitleTest(){
        return "{\n" +
                "    \"variant\": \"rapidapi_basic\",\n" +
                "    \"results\": [\n" +
                "        {\n" +
                "            \"locations\": [\n" +
                "                {\n" +
                "                    \"display_name\": \"Google Play\",\n" +
                "                    \"id\": \"5d84d6dcd95dc7385f6a43e1\",\n" +
                "                    \"url\": \"https://play.google.com/store/tv/show?amp=&amp=&cdid=tvseason-qftHiCImfw8&gdid=tvepisode-HBR40uMa9Xc&gl=GB&hl=en&id=5GMvTunkJ3U\",\n" +
                "                    \"name\": \"GooglePlayIVAGB\",\n" +
                "                    \"icon\": \"https://utellyassets7.imgix.net/locations_icons/utelly/black_new/GooglePlayIVAGB.png?w=92&auto=compress&app_version=ca3dfc72-d1dd-4964-9606-1259c49593c3_1e1212w2021-12-05\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"display_name\": \"Amazon Instant Video\",\n" +
                "                    \"id\": \"5d8415b31e1521005490e1bc\",\n" +
                "                    \"url\": \"https://watch.amazon.co.uk/watch?gti=amzn1.dv.gti.74a9f66f-1406-f1b4-8cb9-eceb409775b4&tag=utellycom00-21\",\n" +
                "                    \"name\": \"AmazonInstantVideoIVAGB\",\n" +
                "                    \"icon\": \"https://utellyassets7.imgix.net/locations_icons/utelly/black_new/AmazonInstantVideoIVAGB.png?w=92&auto=compress&app_version=ca3dfc72-d1dd-4964-9606-1259c49593c3_1e1212w2021-12-05\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"weight\": 7463,\n" +
                "            \"id\": \"5eebef7cb4d219285f17cfad\",\n" +
                "            \"external_ids\": {\n" +
                "                \"imdb\": {\n" +
                "                    \"url\": \"https://www.imdb.com/title/tt2092588\",\n" +
                "                    \"id\": \"tt2092588\"\n" +
                "                },\n" +
                "                \"tmdb\": {\n" +
                "                    \"url\": \"https://www.themoviedb.org/tv/8724\",\n" +
                "                    \"id\": \"8724\"\n" +
                "                },\n" +
                "                \"iva\": {\n" +
                "                    \"id\": \"453666\"\n" +
                "                },\n" +
                "                \"facebook\": null,\n" +
                "                \"rotten_tomatoes\": null,\n" +
                "                \"wiki_data\": {\n" +
                "                    \"url\": \"https://www.wikidata.org/wiki/Q559669\",\n" +
                "                    \"id\": \"Q559669\"\n" +
                "                },\n" +
                "                \"iva_rating\": null,\n" +
                "                \"gracenote\": null\n" +
                "            },\n" +
                "            \"picture\": \"https://utellyassets9-3.imgix.net/api/Images/965bed4c332a5feabb7d83a9bb5dbbca/Redirect?fit=crop&auto=compress&crop=faces,top\",\n" +
                "            \"provider\": \"iva\",\n" +
                "            \"name\": \"Frozen Planet\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"locations\": [\n" +
                "                {\n" +
                "                    \"display_name\": \"iTunes\",\n" +
                "                    \"id\": \"5d8415b3ca549f00528a99f0\",\n" +
                "                    \"url\": \"https://itunes.apple.com/gb/movie/frozen-2010/id1476349922\",\n" +
                "                    \"name\": \"iTunesIVAGB\",\n" +
                "                    \"icon\": \"https://utellyassets7.imgix.net/locations_icons/utelly/black_new/iTunesIVAGB.png?w=92&auto=compress&app_version=ca3dfc72-d1dd-4964-9606-1259c49593c3_1e1212w2021-12-05\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"display_name\": \"Google Play\",\n" +
                "                    \"id\": \"5d84d6dcd95dc7385f6a43e1\",\n" +
                "                    \"url\": \"https://play.google.com/store/movies/details/Frozen?gl=GB&hl=en&id=X-X_X3lfvjE\",\n" +
                "                    \"name\": \"GooglePlayIVAGB\",\n" +
                "                    \"icon\": \"https://utellyassets7.imgix.net/locations_icons/utelly/black_new/GooglePlayIVAGB.png?w=92&auto=compress&app_version=ca3dfc72-d1dd-4964-9606-1259c49593c3_1e1212w2021-12-05\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"display_name\": \"Amazon Instant Video\",\n" +
                "                    \"id\": \"5d8415b31e1521005490e1bc\",\n" +
                "                    \"url\": \"https://watch.amazon.co.uk/detail?asin=B00FCFOPTA&tag=utellycom00-21\",\n" +
                "                    \"name\": \"AmazonInstantVideoIVAGB\",\n" +
                "                    \"icon\": \"https://utellyassets7.imgix.net/locations_icons/utelly/black_new/AmazonInstantVideoIVAGB.png?w=92&auto=compress&app_version=ca3dfc72-d1dd-4964-9606-1259c49593c3_1e1212w2021-12-05\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"weight\": 0,\n" +
                "            \"id\": \"5e2ce0af90c0e033a487f745\",\n" +
                "            \"external_ids\": {\n" +
                "                \"imdb\": {\n" +
                "                    \"url\": \"https://www.imdb.com/title/tt1323045\",\n" +
                "                    \"id\": \"tt1323045\"\n" +
                "                },\n" +
                "                \"tmdb\": {\n" +
                "                    \"url\": \"https://www.themoviedb.org/movie/44363\",\n" +
                "                    \"id\": \"44363\"\n" +
                "                },\n" +
                "                \"iva\": {\n" +
                "                    \"id\": \"591663\"\n" +
                "                },\n" +
                "                \"facebook\": null,\n" +
                "                \"rotten_tomatoes\": null,\n" +
                "                \"wiki_data\": {\n" +
                "                    \"url\": \"https://www.wikidata.org/wiki/Q887961\",\n" +
                "                    \"id\": \"Q887961\"\n" +
                "                },\n" +
                "                \"iva_rating\": null,\n" +
                "                \"gracenote\": null\n" +
                "            },\n" +
                "            \"picture\": \"https://utellyassets9-3.imgix.net/api/Images/e84b39e3887d87c74522aeeee5bbcebf/Redirect?fit=crop&auto=compress&crop=faces,top\",\n" +
                "            \"provider\": \"iva\",\n" +
                "            \"name\": \"Frozen\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"locations\": [\n" +
                "                {\n" +
                "                    \"display_name\": \"iTunes\",\n" +
                "                    \"id\": \"5d8415b3ca549f00528a99f0\",\n" +
                "                    \"url\": \"https://itunes.apple.com/gb/movie/frozen/id757508943\",\n" +
                "                    \"name\": \"iTunesIVAGB\",\n" +
                "                    \"icon\": \"https://utellyassets7.imgix.net/locations_icons/utelly/black_new/iTunesIVAGB.png?w=92&auto=compress&app_version=ca3dfc72-d1dd-4964-9606-1259c49593c3_1e1212w2021-12-05\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"display_name\": \"Google Play\",\n" +
                "                    \"id\": \"5d84d6dcd95dc7385f6a43e1\",\n" +
                "                    \"url\": \"https://play.google.com/store/movies/details/Frozen_2013?gl=GB&hl=en&id=Zh6OfduCP_E\",\n" +
                "                    \"name\": \"GooglePlayIVAGB\",\n" +
                "                    \"icon\": \"https://utellyassets7.imgix.net/locations_icons/utelly/black_new/GooglePlayIVAGB.png?w=92&auto=compress&app_version=ca3dfc72-d1dd-4964-9606-1259c49593c3_1e1212w2021-12-05\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"display_name\": \"Amazon Instant Video\",\n" +
                "                    \"id\": \"5d8415b31e1521005490e1bc\",\n" +
                "                    \"url\": \"https://watch.amazon.co.uk/detail?asin=B07YJ65Q91&tag=utellycom00-21\",\n" +
                "                    \"name\": \"AmazonInstantVideoIVAGB\",\n" +
                "                    \"icon\": \"https://utellyassets7.imgix.net/locations_icons/utelly/black_new/AmazonInstantVideoIVAGB.png?w=92&auto=compress&app_version=ca3dfc72-d1dd-4964-9606-1259c49593c3_1e1212w2021-12-05\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"display_name\": \"Disney+\",\n" +
                "                    \"id\": \"5e573a41efcb7769c4908a9e\",\n" +
                "                    \"url\": \"https://disneyplus.bn5x.net/c/1206980/707638/9358?u=https%3a%2f%2fwww.disneyplus.com%2fmovies%2ffrozen%2f4uKGzAJi3ROz&subId3=justappsvod\",\n" +
                "                    \"name\": \"DisneyPlusIVAGB\",\n" +
                "                    \"icon\": \"https://utellyassets7.imgix.net/locations_icons/utelly/black_new/DisneyPlusIVAGB.png?w=92&auto=compress&app_version=ca3dfc72-d1dd-4964-9606-1259c49593c3_1e1212w2021-12-05\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"weight\": 0,\n" +
                "            \"id\": \"5e2ce0af90c0e033a487f746\",\n" +
                "            \"external_ids\": {\n" +
                "                \"imdb\": {\n" +
                "                    \"url\": \"https://www.imdb.com/title/tt2294629\",\n" +
                "                    \"id\": \"tt2294629\"\n" +
                "                },\n" +
                "                \"tmdb\": {\n" +
                "                    \"url\": \"https://www.themoviedb.org/movie/109445\",\n" +
                "                    \"id\": \"109445\"\n" +
                "                },\n" +
                "                \"iva\": {\n" +
                "                    \"id\": \"610908\"\n" +
                "                },\n" +
                "                \"facebook\": null,\n" +
                "                \"rotten_tomatoes\": null,\n" +
                "                \"wiki_data\": {\n" +
                "                    \"url\": \"https://www.wikidata.org/wiki/Q246283\",\n" +
                "                    \"id\": \"Q246283\"\n" +
                "                },\n" +
                "                \"iva_rating\": null,\n" +
                "                \"gracenote\": null\n" +
                "            },\n" +
                "            \"picture\": \"https://utellyassets9-3.imgix.net/api/Images/5fb97e95ad2fe5f8f2bb55281d08f724/Redirect?fit=crop&auto=compress&crop=faces,top\",\n" +
                "            \"provider\": \"iva\",\n" +
                "            \"name\": \"Frozen\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"updated\": \"2021-12-05T05:09:04+0000\",\n" +
                "    \"term\": \"frozen\",\n" +
                "    \"status_code\": 200\n" +
                "}";
    }

}