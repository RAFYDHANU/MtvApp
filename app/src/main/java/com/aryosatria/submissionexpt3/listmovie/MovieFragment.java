package com.aryosatria.submissionexpt3.listmovie;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.aryosatria.submissionexpt3.listmovie.detailmovie.DetailMovie;
import com.aryosatria.submissionexpt3.listmovie.pojo.ResponseMovie;
import com.aryosatria.submissionexpt3.listmovie.pojo.ResultsItem;
import com.aryosatria.submissionexpt3.R;

public class MovieFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private AlertDialog alertDialog;

    private Observer<ResponseMovie> getMovies = new Observer<ResponseMovie>() {
        @Override
        public void onChanged(ResponseMovie responseMovie) {
            if (responseMovie != null) {
                if (responseMovie.getAnError() == null) {
                    ListMovieAdapter mAdapter = new ListMovieAdapter(responseMovie.getResults());
                    mAdapter.SetOnItemClickListener(new ListMovieAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, ResultsItem model) {
                            Intent goToDetailMovie = new Intent(view.getContext(), DetailMovie.class);
                            goToDetailMovie.putExtra(DetailMovie.SELECTED_MOVIE,model);
                            startActivity(goToDetailMovie);
                        }
                    });
                    recyclerView.setAdapter(mAdapter);
                } else {
                    alertDialog.setMessage(responseMovie.getAnError().getMessage());
                    alertDialog.show();
                }
            }
            progressBar.setVisibility(View.GONE);
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progressBar);

        alertDialog = new AlertDialog.Builder(view.getContext()).setTitle(getString(R.string.failure)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create();

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        ListMovieViewModel mViewModel = ViewModelProviders.of(this).get(ListMovieViewModel.class);
        mViewModel.doRequestListMovies();
        mViewModel.getMovies().observe(this,getMovies);
    }
}

