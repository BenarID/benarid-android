package id.benar.benarid.network;

import java.util.List;

import id.benar.benarid.models.Portal;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    public static final String ENDPOINT = "https://benarid-dev.herokuapp.com/api/";

    @GET("portals")
    Call<List<Portal>> getPortals();
}
