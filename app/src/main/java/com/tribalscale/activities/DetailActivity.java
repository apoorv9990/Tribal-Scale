package com.tribalscale.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tribalscale.R;
import com.tribalscale.models.Person;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by patel on 6/4/2017.
 *
 * Shows the details about the selected person
 */

public class DetailActivity extends AppCompatActivity {

    private static final String PERSON = "DetailActivity.Person";

    @BindView(R.id.image_detail_avatar)
    ImageView detailAvatarImageView;

    @BindView(R.id.text_detail_name)
    TextView detailNameTextView;

    private Person mPerson;

    public static void startDetailActivity(Context context, Person person) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(PERSON, person);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        if(getIntent().hasExtra(PERSON)) {
            mPerson = getIntent().getParcelableExtra(PERSON);
        }

        if(mPerson != null) {
            Picasso.with(this).load(mPerson.getPicture().getLarge()).into(detailAvatarImageView);

            detailNameTextView.setText(mPerson.getName().getFullName());
        }
    }
}
