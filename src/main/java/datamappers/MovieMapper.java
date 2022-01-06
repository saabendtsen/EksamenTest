package datamappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AddInfoDTO;
import dtos.ImdbResponseDTO;
import dtos.MovieDTO;
import entities.MovieLikes;
import org.apache.http.client.methods.HttpGet;
import rest.MovieResource;
import security.HttpClient;

import java.util.ArrayList;
import java.util.List;

public class MovieMapper {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public List<MovieDTO> getMovie(ImdbResponseDTO imdbResponseDTO) throws Exception {
        List<MovieDTO> list = new ArrayList<>();
        for (ImdbResponseDTO.Result result : imdbResponseDTO.results) {
            List<String> movieDTOList = new ArrayList<>();
            for (ImdbResponseDTO.Location location : result.locations) {
                movieDTOList.add(location.display_name);
            }
            list.add(new MovieDTO(result.external_ids.imdb.id, result.name, movieDTOList));
        }
        addInfo(list);

        return list;
    }

    public List<MovieDTO> getMovieById(List<MovieDTO> movieDTO) throws Exception {
        List<MovieDTO> movieDTOS = addInfo(movieDTO);
        ImdbResponseDTO imdbResponseDTO;
        HttpClient httpClient = new HttpClient();
        for (MovieDTO m : movieDTOS) {
            String response = httpClient.sendGet(m.getMovieName(), false);
            System.out.println("here is a response" + response);
            try {
                imdbResponseDTO = gson.fromJson(response, ImdbResponseDTO.class);
                for (ImdbResponseDTO.Result r : imdbResponseDTO.getResults()) {
                    if (r.external_ids.imdb.id.equals(m.getId())) {
                        for (ImdbResponseDTO.Location location : r.locations) {
                            m.addPlacesToWatch(location.display_name);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return movieDTOS;
    }

    public List<MovieDTO> addInfo(List<MovieDTO> list) throws Exception {
        for (MovieDTO dto : list) {
            AddInfoDTO addInfoDTO = getAddInfo(dto);
            if (dto.getMovieName() == null) {
                dto.setMovieName(addInfoDTO.getTitle());
            }
            dto.setYear(addInfoDTO.getYear());
            dto.setGenre(addInfoDTO.getGenre());
            dto.setRated(addInfoDTO.getRated());
            dto.setRuntime(addInfoDTO.getRuntime());
            dto.setImdbRating(addInfoDTO.getImdbRating());
            dto.setPoster(addInfoDTO.getPoster());
            dto.setBoxOffices(addInfoDTO.getBoxOffice());
        }
        return list;
    }

    public AddInfoDTO getAddInfo(MovieDTO dto) throws Exception {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        security.HttpClient obj = new HttpClient();
        String response = "";
        try {
            response = obj.sendGet(dto.getId(), true);
        } finally {
            obj.close();
        }

        return gson.fromJson(response, AddInfoDTO.class);
    }


}
