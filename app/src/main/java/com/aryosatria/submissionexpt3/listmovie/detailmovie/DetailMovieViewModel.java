package com.aryosatria.submissionexpt3.listmovie.detailmovie;

import androidx.lifecycle.ViewModel;

import com.aryosatria.submissionexpt3.listmovie.pojo.ResultsItem;

public class DetailMovieViewModel extends ViewModel {
    private ResultsItem resultsItem;

    public ResultsItem getResultsItem() {
        return resultsItem;
    }

    public void setResultsItem(ResultsItem resultsItem) {
        this.resultsItem = resultsItem;
    }
}
