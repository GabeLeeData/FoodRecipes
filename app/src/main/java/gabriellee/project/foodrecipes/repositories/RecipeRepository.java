package gabriellee.project.foodrecipes.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;


import java.util.List;

import gabriellee.project.foodrecipes.models.Recipe;
import gabriellee.project.foodrecipes.requests.RecipeApiClient;

public class RecipeRepository {

    private static RecipeRepository instance;
    private RecipeApiClient mRecipeApiClient;
    private String mQuery;
    private int mPageNumber;
    private MutableLiveData<Boolean> mIsQueryExhaused = new MutableLiveData<>();
    private MediatorLiveData<List<Recipe>> mRecipes = new MediatorLiveData<>();

    public static RecipeRepository getInstance(){
        if (instance == null) {
            instance = new RecipeRepository();
        }
        return instance;
    }

    private RecipeRepository(){
        mRecipeApiClient = RecipeApiClient.getInstance();
        initMediators();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipes;

    }

    private void initMediators(){
        LiveData<List<Recipe>> recipeListApiSource = mRecipeApiClient.getRecipes();
       mRecipes.addSource(recipeListApiSource, new Observer<List<Recipe>>() {
           @Override
           public void onChanged(@Nullable List<Recipe> recipes) {
               if(recipes != null){
                   mRecipes.setValue(recipes);
                   doneQuery(recipes);
               }
               else {
                   // search database cache
                   doneQuery(null);
               }
           }
       });
    }

    private void doneQuery(List<Recipe> list){
        if(list != null){
            if(list.size() % 30 != 0) {
                mIsQueryExhaused.setValue(true);
            }
        }
        else {
            mIsQueryExhaused.setValue(true);
        }
    }

    public LiveData<Boolean> isQueryExhausted() {
        return mIsQueryExhaused;
    }

    public LiveData<Recipe> getRecipe() {
        return mRecipeApiClient.getRecipe();
    }

    public LiveData<Boolean> isRecipeRequestTimedOut() {
        return mRecipeApiClient.isRecipeRequestTimedOut();
    }


    public void searchRecipeById(String recipeId){
        mRecipeApiClient.searchRecipebyId(recipeId);
    }

    public void searchRecipesApi(String query, int pageNumber) {
        if(pageNumber == 0) pageNumber = 1;
        mQuery = query;
        mPageNumber = pageNumber;
        mIsQueryExhaused.setValue(false);
        mRecipeApiClient.searchRecipesApi(query, pageNumber);
    }

    public void searchNextPage(){
        searchRecipesApi(mQuery, mPageNumber + 1);
    }

    public void cancelRequest() {
        mRecipeApiClient.cancelRequest();
    }
}
