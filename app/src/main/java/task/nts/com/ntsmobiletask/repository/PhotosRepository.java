package task.nts.com.ntsmobiletask.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import task.nts.com.ntsmobiletask.pojo.PhotosModel;
import task.nts.com.ntsmobiletask.services.ApiClient;
import task.nts.com.ntsmobiletask.services.ApiInterface;

public class PhotosRepository {

   ApiInterface client = null;

    public PhotosRepository(){

        client = ApiClient.getClient().create(ApiInterface.class);
    }


    public MutableLiveData<List<PhotosModel>> getPhotosList(){

        final MutableLiveData<List<PhotosModel>> listPhotosModel = new MutableLiveData<List<PhotosModel>>();
        client.getThumbUrls().enqueue(new Callback<List<PhotosModel>>() {
            @Override
            public void onResponse(Call<List<PhotosModel>> call, Response<List<PhotosModel>> response) {

                 listPhotosModel.postValue(response.body());

            }

            @Override
            public void onFailure(Call<List<PhotosModel>> call, Throwable t) {
                Log.i("Error","Service Error");
            }
        });
        return  listPhotosModel;
    }
}
