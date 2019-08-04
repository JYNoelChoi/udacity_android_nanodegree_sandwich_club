package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
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


/*
{
    \"name\":
        {
            \"mainName\":\"Ham and cheese sandwich\",
            \"alsoKnownAs\":[]
        },
    \"placeOfOrigin\":\"\",
    \"description\":\"A ham and cheese sandwich is a common type of sandwich. It is made by putting cheese and sliced ham between two slices of bread. The bread is sometimes buttered and/or toasted. Vegetables
                like lettuce, tomato, onion or pickle slices can also be included. Various kinds of mustard and mayonnaise are also common.\",
    \"image\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG\",
    \"ingredients\":[\"Sliced bread\",\"Cheese\",\"Ham\"]}
 */

/*
this.mainName = mainName;
        this.alsoKnownAs = alsoKnownAs;
        this.placeOfOrigin = placeOfOrigin;
        this.description = description;
        this.image = image;
        this.ingredients = ingredients;
 */