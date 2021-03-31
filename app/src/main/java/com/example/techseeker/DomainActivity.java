package com.example.techseeker;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DomainActivity extends AppCompatActivity {
    private TextView textResults;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domain);

        textResults = (TextView) findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        //getPost();
        getComment();


    }
    private void  getPost(){
        Call<List<Post>> call = jsonPlaceHolderApi.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    textResults.setText("Code: "+response.code());
                    return;
                }

                List<Post> posts = response.body();
                for (Post post : posts){
                    String content = "";
                    content += "ID: "+ post.getId() + "\n";
                    content += "User Id: "+ post.getUserId() + "\n";
                    content += "Title: "+ post.getTitle() + "\n";
                    content += "Text: "+ post.getText() + "\n\n";
                    textResults.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textResults.setText(t.getMessage());
            }
        });
    }

    private void  getComment(){
        Call<List<Comment>> call = jsonPlaceHolderApi
                .getComments("https://jsonplaceholder.typicode.com/posts/3/comments");
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    textResults.setText("Code: " + response.code());
                    return;
                }
                List<Comment> comments = response.body();
                for (Comment comment : comments) {
                    String content = "";
                    content += "ID: " + comment.getId() + "\n";
                    content += "Post ID: " + comment.getPostId() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "Email: " + comment.getEmail() + "\n";
                    content += "Text: " + comment.getText() + "\n\n";
                    textResults.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textResults.setText(t.getMessage());
            }
        });
    }
}