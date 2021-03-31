package com.example.techseeker;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPost();
    @GET("posts/{id}/comments")
    Call<List<Comment>> getComment(@Path("id") int postId);

    @GET
    Call<List<Comment>> getComments(@Url String url);

    @GET("domains")
    Call<List<Domain>> getDomains();
    @GET
    Call<List<Technician>> getTechnicians(@Url String url);
}
