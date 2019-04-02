package gabriellee.project.foodrecipes;

import android.os.Parcelable;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import gabriellee.project.foodrecipes.models.Recipe;
import gabriellee.project.foodrecipes.requests.ServiceGenerator;
import gabriellee.project.foodrecipes.requests.response.RecipeSearchResponse;
import retrofit2.Call;
import retrofit2.Response;

import static gabriellee.project.foodrecipes.util.Constants.BASE_URL;
import static gabriellee.project.foodrecipes.util.Constants.NETWORK_TIMEOUT;

public class AppExecutors {

    private static AppExecutors instance;
    private static final String TAG = "AppExecutors";
    public static AppExecutors getInstance() {
        if(instance == null) {
            instance = new AppExecutors();
        }
        return instance;
    }

    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService networkIO(){
        return mNetworkIO;
    }


}
