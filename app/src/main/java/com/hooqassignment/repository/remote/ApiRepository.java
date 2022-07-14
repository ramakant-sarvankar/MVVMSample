package com.hooqassignment.repository.remote;

import androidx.lifecycle.MutableLiveData;

import com.hooqassignment.Constants;
import com.hooqassignment.repository.MovieApiResponse;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRepository {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static ApiService apiService ;

    private static ApiRepository instance;

    public static ApiRepository getInstance(){
        if(instance == null){
            apiService = retrofit.create(ApiService.class);
            instance = new ApiRepository();
        }
        return instance;
    }

    public static ApiService getApiService(){
        return apiService;
    }


    public Map<String, String> getNowPlayQueryParams(int page){
        Map<String, String> map = new HashMap<>();
        map.put(Constants.KEY_API_KEY,Constants.API_KEY);
        map.put(Constants.KEY_LANGUAGE,Constants.LANGUAGE);
        map.put(Constants.KEY_PAGE,String.valueOf(page));
        return map;
    }

    public MutableLiveData<MovieApiResponse> getNowPlayingMovieList(int pageNumber){
        final MutableLiveData<MovieApiResponse> moviesList = new MutableLiveData<>();
        apiService.getNowPlayingMovieList(getNowPlayQueryParams(pageNumber)).enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call,
                                   Response<MovieApiResponse> response) {
                MovieApiResponse data = response.body();
                data.setResponseStatus(response.isSuccessful());
                if (response.isSuccessful()){
                    response.body().responseStatus = Constants.RESPONSE_STATUS.SUCCESS;
                    moviesList.setValue(response.body());
                }else{
                    response.body().responseStatus = Constants.RESPONSE_STATUS.SERVER_FAILURE;
                    moviesList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {
                try{
                    MovieApiResponse data = new MovieApiResponse();
                    data.responseStatus = Constants.RESPONSE_STATUS.NO_INTERNET;
                    moviesList.setValue(data);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        return moviesList;
    }

    public MutableLiveData<MovieApiResponse> getSimilarMovieList(int movieId, int pageNumber){
        final MutableLiveData<MovieApiResponse> moviesList = new MutableLiveData<>();
        apiService.getSimilarMovieList(String.valueOf(movieId),getNowPlayQueryParams(pageNumber)).enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call,
                                   Response<MovieApiResponse> response) {
                MovieApiResponse data = response.body();
                data.setResponseStatus(response.isSuccessful());
                if (response.isSuccessful()){
                    response.body().responseStatus = Constants.RESPONSE_STATUS.SUCCESS;
                    moviesList.setValue(response.body());
                }else{
                    response.body().responseStatus = Constants.RESPONSE_STATUS.SERVER_FAILURE;
                    moviesList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {
                try{
                    MovieApiResponse data = new MovieApiResponse();
                    data.responseStatus = Constants.RESPONSE_STATUS.NO_INTERNET;
                    moviesList.setValue(data);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        return moviesList;
    }
}
