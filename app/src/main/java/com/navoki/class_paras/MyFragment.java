package com.navoki.class_paras;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MyFragment extends Fragment {

    public static MyFragment getInstance(String title, String link) {
        MyFragment fragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("link", link);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.e("MSG", "1111111111");
        View view = View.inflate(getContext(), R.layout.imagelayout, null);
        TextView title;
        ImageView image;
        title = (TextView) view.findViewById(R.id.title);
        image = (ImageView) view.findViewById(R.id.image);

        final Bundle bundle = getArguments();
        title.setText(bundle.getString("title", "N/A"));

        Picasso.get().load(bundle.getString("link")).resize(200,100).centerCrop().into(image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), bundle.getString("title"), Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }
}
