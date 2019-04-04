package gabriellee.project.foodrecipes.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

import gabriellee.project.foodrecipes.models.Recipe;
import gabriellee.project.foodrecipes.repositories.RecipeRepository;


public class RecipeListViewModel extends ViewModel {

    private RecipeRepository mRecipeRepository;
    private boolean mIsViewingRecipes;
    private boolean mIsPerformingQuery;


    public RecipeListViewModel() {
        mRecipeRepository = RecipeRepository.getInstance();
        mIsPerformingQuery = false;
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipeRepository.getRecipes();
    }

    public LiveData<Boolean> isQueryExhaused(){
        return mRecipeRepository.isQueryExhausted();
    }

    public void searchRecipesApi(String query, int pageNumber) {
        mIsViewingRecipes = true;
        mIsPerformingQuery = true;
        mRecipeRepository.searchRecipesApi(query, pageNumber);
    }

    public void searchNextPage() {
        if (!mIsPerformingQuery && mIsViewingRecipes && !isQueryExhaused().getValue()) {
            mRecipeRepository.searchNextPage();
        }
    }

    public boolean isViewingRecipes() {
        return mIsViewingRecipes;
    }

    public void setIsViewingRecipes(boolean isViewingRecipes) {
        mIsViewingRecipes = isViewingRecipes;
    }

    public void setIsPerformingQuery(Boolean isPerformingQuery){
        mIsPerformingQuery = isPerformingQuery;
    }

    public boolean isPerformingQuery(){
        return mIsPerformingQuery;
    }

    public boolean onBackPressed(){
        if(mIsPerformingQuery) {
            mRecipeRepository.cancelRequest();   //cancel query
            mIsPerformingQuery = false;
        }
        if(mIsViewingRecipes){
            mIsViewingRecipes = false;
            return false;
        }

        return true;
    }
}
