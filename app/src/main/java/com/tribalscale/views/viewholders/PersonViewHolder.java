package com.tribalscale.views.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tribalscale.R;
import com.tribalscale.models.Person;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by patel on 6/4/2017.
 */

public class PersonViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.image_person_avatar)
    ImageView personAvatarImageView;

    @BindView(R.id.text_person_name)
    TextView personNameTextView;

    public PersonViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bind(Person person) {

        Picasso.with(itemView.getContext()).load(person.getPicture().getThumbnail()).into(personAvatarImageView);

        personNameTextView.setText(person.getName().getFullName());
    }
}
