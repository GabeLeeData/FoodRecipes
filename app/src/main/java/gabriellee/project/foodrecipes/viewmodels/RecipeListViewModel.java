package gabriellee.project.foodrecipes.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import gabriellee.project.foodrecipes.models.Recipe;
import gabriellee.project.foodrecipes.repositories.RecipeRepository;


public class RecipeListViewModel extends ViewModel {

    private RecipeRepository mRecipeRepository;


    public RecipeListViewModel() {
        mRecipeRepository = RecipeRepository.getInstance();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipeRepository.getRecipes();
    }

    public void searchRecipesApi(String query, int pageNumber) {
        mRecipeRepository.searchRecipesApi(query, pageNumber);
    }

}
