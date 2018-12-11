package task.nts.com.ntsmobiletask.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import java.util.List;

import task.nts.com.ntsmobiletask.pojo.PhotosModel;
import task.nts.com.ntsmobiletask.repository.PhotosRepository;

public class PhotosViewModel extends ViewModel {

    private PhotosRepository repository;
    MutableLiveData<List<PhotosModel>> listData;
    public PhotosViewModel(){
        repository = new PhotosRepository();
    }

    public LiveData<List<PhotosModel>> getPhotosList() {
        return repository.getPhotosList();
    }

}
