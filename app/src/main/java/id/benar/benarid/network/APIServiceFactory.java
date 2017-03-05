package id.benar.benarid.network;

import id.benar.benarid.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIServiceFactory {

    private static APIService service;

    public static APIService getInstance() {
        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BENARID_API_HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(APIService.class);
        }

        return service;
    }

}
