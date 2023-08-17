package service;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import utils.ApiTestData;

import static io.restassured.RestAssured.given;

public class PostService {

    private final String baseUrl;

    public PostService(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Response getAllPosts() {
        return given()
         .get(baseUrl+"/posts")
                .then()
                .extract().response();
    }

    public Response getPostById(int postId){
        return given()
                .pathParam("postId", postId)
                .when()
                .get(baseUrl+"/posts/{postId}")
                .then()
                .extract().response();
    }

    public Response createNewPost(String postAsJson){
        return given()
                .contentType(ContentType.JSON)
                .body(postAsJson)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .extract().response();
    }

    public Response deletePostById(int postId){
        return given()
                .pathParam("postId", postId)
                .when()
                .delete(baseUrl + "/posts/{postId}")
                .then()
                .extract().response();
    }

    public Response updatePostById(String updatedPostAsJson){
        return given()
                .contentType(ContentType.JSON)
                .body(updatedPostAsJson)
                .pathParam("postId", ApiTestData.postId)
                .when()
                .put("https://jsonplaceholder.typicode.com/posts/{postId}")
                .then()
                .extract().response();
    }


}
