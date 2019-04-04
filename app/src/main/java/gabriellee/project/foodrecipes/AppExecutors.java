package gabriellee.project.foodrecipes;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


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
