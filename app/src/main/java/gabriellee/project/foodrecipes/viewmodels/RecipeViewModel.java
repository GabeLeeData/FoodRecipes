package gabriellee.project.foodrecipes.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import gabriellee.project.foodrecipes.models.Recipe;
import gabriellee.project.foodrecipes.repositories.RecipeRepository;

public class RecipeViewModel extends ViewModel {

    private RecipeRepository mRecipeRepository;
    private String mRecipeId;
    private boolean mDidRetrieveRecipe;


    public RecipeViewModel() {
        mRecipeRepository = RecipeRepository.getInstance();
        mDidRetrieveRecipe = false;
    }

    public LiveData<Recipe> getRecipe(){
        return mRecipeRepository.getRecipe();
    }

    public LiveData<Boolean> isRecipeRequestTimedOut() {
        return mRecipeRepository.isRecipeRequestTimedOut();
    }

    public void searchRecipeById(String recipeId){
        mRecipeId = recipeId;
        mRecipeRepository.searchRecipeById(recipeId);
    }

    public String getRecipeId() {
        return mRecipeId;
    }

    public void setRetrievedRecipe(boolean retrievedRecipe) {
        mDidRetrieveRecipe = retrievedRecipe;
    }

    public boolean didRetrieveRecipe() {
        return mDidRetrieveRecipe;
    }
}
