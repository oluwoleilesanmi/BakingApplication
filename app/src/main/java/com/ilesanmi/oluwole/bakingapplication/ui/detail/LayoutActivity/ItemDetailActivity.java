package com.ilesanmi.oluwole.bakingapplication.ui.detail.LayoutActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.data.network.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.IngredientDetail.IngredientDetailFragment;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail.StepDetailFragment;

import java.util.ArrayList;

public class ItemDetailActivity extends AppCompatActivity {

    public static Intent getStartIntent(Context context, int clickedPositionMainActivity, ArrayList<Recipe> recipes, int clickedPositionDetailActivity, int click) {
        Intent intent = new Intent(context, ItemDetailActivity.class);
        intent.putParcelableArrayListExtra("ArrayList",recipes);
        intent.putExtra("positionClickedInMainActivity", clickedPositionMainActivity);
        intent.putExtra("Click", click);
        intent.putExtra("positionClickedInDetailActivity", clickedPositionDetailActivity);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);


        if(isPositionClickedInDetailActivityRecyclerView()){
        Fragment fragmentIngredientDetail = IngredientDetailFragment
                .newInstance(getParcelable(getClickedPositionFromIntentSentFromMainActivity(),getArrayList()));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.item_detail_container, fragmentIngredientDetail, IngredientDetailFragment.FRAGMENT_ID)
                .addToBackStack(null)
                .commit();

        }else {
            Fragment fragmentStepDetail = StepDetailFragment
                    .newInstance(getParcelable(getClickedPositionFromIntentSentFromMainActivity(), getArrayList()), getClickedPositionFromIntentSentFromDetailActivity());

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragmentStepDetail, StepDetailFragment.FRAGMENT_ID)
                    .addToBackStack(null)
                    .commit();
        }

    }

    public Parcelable getParcelable(int positionClickedInMainActivity, ArrayList<Recipe> recipes) {
        return recipes.get(positionClickedInMainActivity);
    }

    public ArrayList<Recipe> getArrayList(){
        return getIntent().getParcelableArrayListExtra("ArrayList");
    }

    public int getClickedPositionFromIntentSentFromMainActivity() {
        return getIntent().getIntExtra("positionClickedInMainActivity", 0);
    }


    public int getClickedPositionFromIntentSentFromDetailActivity() {
        return getIntent().getIntExtra("positionClickedInDetailActivity", 0);
    }

    public boolean isPositionClickedInDetailActivityRecyclerView(){
        return getIntent().getIntExtra("Click", 0) == 1;
    }
}
