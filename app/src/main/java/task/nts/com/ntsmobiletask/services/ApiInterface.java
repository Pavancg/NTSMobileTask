package task.nts.com.ntsmobiletask.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import task.nts.com.ntsmobiletask.pojo.PhotosModel;

public interface ApiInterface {

    String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @GET("photos")
    Call<List<PhotosModel>> getThumbUrls();

}
