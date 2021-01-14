package com.aryosatria.submissionexpt3.listmovie;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.aryosatria.submissionexpt3.listmovie.pojo.ResponseMovie;
import com.aryosatria.submissionexpt3.MovieCatalogue;

public class ListMovieViewModel extends ViewModel {

    private MutableLiveData<ResponseMovie> responseMovie = new MutableLiveData<>();


    public MutableLiveData getMovies(){
        if(responseMovie==null){
            doRequestListMovies();
        }
        return responseMovie;
    }

    public void doRequestListMovies(){
        AndroidNetworking.get("https://api.themoviedb.org/3/discover/movie")
                .addQueryParameter("api_key", MovieCatalogue.MOVIE_DB_API_KEY)
                .addQueryParameter("language","en-US")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ResponseMovie.class, new ParsedRequestListener<ResponseMovie>() {
                    @Override
                    public void onResponse(ResponseMovie response) {
                        responseMovie.postValue(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        responseMovie.setValue(new ResponseMovie(anError));
                    }
                });
    }
}
