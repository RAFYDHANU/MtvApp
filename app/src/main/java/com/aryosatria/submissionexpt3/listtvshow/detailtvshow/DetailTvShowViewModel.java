package com.aryosatria.submissionexpt3.listtvshow.detailtvshow;

import androidx.lifecycle.ViewModel;

import com.aryosatria.submissionexpt3.listtvshow.pojo.ResultsItem;

public class DetailTvShowViewModel extends ViewModel {
    private ResultsItem resultsItem;

    public ResultsItem getResultsItem() {
        return resultsItem;
    }

    public void setResultsItem(ResultsItem resultsItem) {
        this.resultsItem = resultsItem;
    }
}
