package utils;

import model.PostDTO;

public class ApiTestData {

    public static final String baseUrl = ApiConfig.getValue("base.url");
    public static final Integer userId = Integer.valueOf(ApiConfig.getValue("post.userId"));
    public static final Integer newPostId = Integer.valueOf(ApiConfig.getValue("post.newPostId"));
    public static final Integer postId = Integer.valueOf(ApiConfig.getValue("post.id"));
    public static final String title = ApiConfig.getValue("post.title");
    public static final String newTitle = ApiConfig.getValue("post.newTitle");
    public static final String body = ApiConfig.getValue("post.body");

    public static PostDTO newPost(){
        return new PostDTO.PostBuilder()
                .setUserId(userId)
                .setId(newPostId)
                .setTitle(title)
                .setBody(body)
                .build();
    }

    public static PostDTO updatePost(){
        return new PostDTO.PostBuilder()
                .setUserId(userId)
                .setId(postId)
                .setTitle(newTitle)
                .setBody(body)
                .build();
    }

}
