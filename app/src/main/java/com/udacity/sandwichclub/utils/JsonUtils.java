package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        // Parse Json object
        JSONObject jsonParsed = null;
        Sandwich sandwich = new Sandwich();
        try {
            jsonParsed = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // get mainName
        String mainName = jsonParsed.getJSONObject("name").getString("mainName");

        // get alsoKnownAs
        List<String> alsoKnownAsArray = new ArrayList<>();
        JSONArray alsoKnownAsJsonArray = jsonParsed.getJSONObject("name").getJSONArray("alsoKnownAs");
        for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
            alsoKnownAsArray.add(alsoKnownAsJsonArray.getString(i));
        }

        // get PlaceOfOrigin
        String placeOfOrigin = jsonParsed.getString("placeOfOrigin");

        // get Description
        String description = jsonParsed.getString("description");

        // get ImageUrl
        String imageUrl = jsonParsed.getString("image");

        // get Ingredients
        List<String> ingredientsArray = new ArrayList<>();
        JSONArray ingredientsJsonArray = jsonParsed.getJSONArray("ingredients");
        for (int j = 0; j < ingredientsJsonArray.length(); j++) {
            ingredientsArray.add(ingredientsJsonArray.getString(j));
        }

        // set values to Sandwich Object
        sandwich.setMainName(mainName);
        sandwich.setAlsoKnownAs(alsoKnownAsArray);
        sandwich.setPlaceOfOrigin(placeOfOrigin);
        sandwich.setDescription(description);
        sandwich.setImage(imageUrl);
        sandwich.setIngredients(ingredientsArray);

        return sandwich;
    }
}
