package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private Sandwich sandwich = null;
    private ImageView ingredientsIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Grab views
        ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];

        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        } else {
            populateUI();
        }

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        // Load image
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(android.R.drawable.sym_def_app_icon) // Reviewer's suggestion (To provide default placeholder in case of error
                .error(android.R.drawable.stat_notify_error) // Review's suggestion (To provide error image)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

        TextView placeOfOriginTv = findViewById(R.id.origin_tv);
        TextView alsoKnownAsTv = findViewById(R.id.also_known_as_tv);
        TextView ingredientsTv = findViewById(R.id.ingredients_tv);
        TextView descriptionTv = findViewById(R.id.description_tv);

        List<String> alsoKnownAsList = sandwich.getAlsoKnownAs();
        List<String> ingredientsList = sandwich.getIngredients();

        // for (String alsoKnownAs : alsoKnownAsList) {
        //     alsoKnownAsTv.append(alsoKnownAs + "\n\n");
        // }
        //
        // for (String ingredient : ingredientsList) {
        //     ingredientsTv.append(ingredient + "\n\n");
        // }

        String noData = "No Data Available.";
        // String placeOfOrigin = sandwich.getPlaceOfOrigin();
        String placeOfOrigin = sandwich.getPlaceOfOrigin();
        String alsoKnownAs = TextUtils.join(", ", sandwich.getAlsoKnownAs());
        String ingredients = TextUtils.join(", ", sandwich.getIngredients());
        String description = sandwich.getDescription();

        if (TextUtils.isEmpty(placeOfOrigin)) placeOfOrigin = noData;
        if (TextUtils.isEmpty(alsoKnownAs)) alsoKnownAs = noData;
        if (TextUtils.isEmpty(ingredients)) ingredients = noData;
        if (TextUtils.isEmpty(description)) description = noData;

        placeOfOriginTv.setText(placeOfOrigin);
        alsoKnownAsTv.setText(alsoKnownAs);
        ingredientsTv.setText(ingredients);
        descriptionTv.setText(description);

    }
}
