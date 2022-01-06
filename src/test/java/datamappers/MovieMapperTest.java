package datamappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ImdbResponseDTO;
import dtos.MovieDTO;
import org.junit.jupiter.api.Test;
import security.HttpClient;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieMapperTest {

    HttpClient httpClient = new HttpClient();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private MovieMapper movieMapper = new MovieMapper();


    @Test
    void getMovie() throws Exception {
        ImdbResponseDTO imdbResponseDTO = gson.fromJson(httpClient.sendGet("Frozen", false), ImdbResponseDTO.class);
        assertEquals(imdbResponseDTO.results.get(0).name, "Frozen Planet");

        List<MovieDTO> movieDTOList = movieMapper.getMovie(imdbResponseDTO);

        assertEquals(movieDTOList.get(0).getMovieName(), "Frozen Planet");
        assertTrue(movieDTOList.size() == 3);
    }

    @Test
    void getMovieById() throws Exception {
        List<MovieDTO> movieDTOList = new ArrayList<>();
        MovieDTO movieDTO = new MovieDTO("tt0111161");

        movieDTOList.add(movieDTO);
        movieDTOList = movieMapper.getMovieById(movieDTOList);

        assertEquals(movieDTOList.get(0).getMovieName(),"The Shawshank Redemption");
    }
}