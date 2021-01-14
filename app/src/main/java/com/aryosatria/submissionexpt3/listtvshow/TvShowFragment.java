package com.aryosatria.submissionexpt3.listtvshow;


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

import com.aryosatria.submissionexpt3.listtvshow.detailtvshow.DetailTvShow;
import com.aryosatria.submissionexpt3.listtvshow.pojo.ResponseTvShow;
import com.aryosatria.submissionexpt3.listtvshow.pojo.ResultsItem;
import com.aryosatria.submissionexpt3.R;

public class TvShowFragment extends Fragment {

    private RecyclerView recyclerView;
    private AlertDialog alertDialog;
    private ProgressBar progressBar;

    private Observer<ResponseTvShow> getTvShows = new Observer<ResponseTvShow>() {
        @Override
        public void onChanged(ResponseTvShow responseTvShows) {
            if(responseTvShows!=null){
                if(responseTvShows.getAnError()==null){
                    ListTvShowAdapter mAdapter = new ListTvShowAdapter(responseTvShows.getResults());
                    mAdapter.SetOnItemClickListener(new ListTvShowAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, ResultsItem model) {
                            Intent goToDetailMovie = new Intent(view.getContext(), DetailTvShow.class);
                            goToDetailMovie.putExtra(DetailTvShow.SELECTED_TV_SHOWS,model);
                            startActivity(goToDetailMovie);
                        }
                    });

                    recyclerView.setAdapter(mAdapter);
                }else{
                    alertDialog.setMessage(responseTvShows.getAnError().getMessage());
                    alertDialog.show();
                }
            }
            progressBar.setVisibility(View.GONE);
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
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
        ListTvShowViewModel mViewModel = ViewModelProviders.of(this).get(ListTvShowViewModel.class);
        mViewModel.doRequestListTvShows();
        mViewModel.getTvShowList().observe(this, getTvShows);
    }
}

