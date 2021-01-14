package com.aryosatria.submissionexpt3.listmovie.detailmovie;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.aryosatria.submissionexpt3.listmovie.pojo.ResultsItem;
import com.aryosatria.submissionexpt3.R;
import com.aryosatria.submissionexpt3.databinding.ActivityDetailMovieBinding;
import com.bumptech.glide.Glide;

import java.util.Objects;

public class DetailMovie extends AppCompatActivity {
    public static final String SELECTED_MOVIE = "selected_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DetailMovieViewModel viewModel = ViewModelProviders.of(this).get(DetailMovieViewModel.class);
        ActivityDetailMovieBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie);
        ResultsItem movieModel = getIntent().getParcelableExtra(SELECTED_MOVIE);
        viewModel.setResultsItem(movieModel);
        binding.setViewmodel(viewModel);

        Glide.with(this).load("https://image.tmdb.org/t/p/w185/"+movieModel.getPosterPath()).into(binding.imgPoster);
        setTitle(viewModel.getResultsItem().getTitle());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
