package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    private static final String FALL_BACK_STRING = "API did not provide data.";

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
        String mainName = jsonParsed.getJSONObject(NAME).getString(MAIN_NAME);

        // get alsoKnownAs
        List<String> alsoKnownAsArray = new ArrayList<>();
        JSONArray alsoKnownAsJsonArray = jsonParsed.getJSONObject(NAME).getJSONArray(ALSO_KNOWN_AS);
        for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
            alsoKnownAsArray.add(alsoKnownAsJsonArray.getString(i));
        }

        // get PlaceOfOrigin
        // String placeOfOrigin = jsonParsed.getString(PLACE_OF_ORIGIN);
        String placeOfOrigin = jsonParsed.optString(PLACE_OF_ORIGIN, FALL_BACK_STRING);

        // get Description
        String description = jsonParsed.getString(DESCRIPTION);

        // get ImageUrl
        String imageUrl = jsonParsed.getString(IMAGE);

        // get Ingredients
        List<String> ingredientsArray = new ArrayList<>();
        JSONArray ingredientsJsonArray = jsonParsed.getJSONArray(INGREDIENTS);
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
