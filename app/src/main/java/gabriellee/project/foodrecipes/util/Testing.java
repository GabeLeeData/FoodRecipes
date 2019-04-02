package gabriellee.project.foodrecipes.util;

import android.util.Log;

import java.util.List;

import gabriellee.project.foodrecipes.models.Recipe;

public class Testing {
    private static final String TAG = "Testing";
    public static void printRecipes(List<Recipe>list, String tag) {
        for (Recipe recipe: list) {
            Log.d(TAG, "printRecipes: " + recipe.getTitle());
        }
    }
}
