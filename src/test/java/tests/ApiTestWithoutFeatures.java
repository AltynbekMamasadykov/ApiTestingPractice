package tests;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

 class ApiTestWithoutFeatures {

    protected static final String baseUrl = "https://jsonplaceholder.typicode.com";
    protected static final Logger logger = LogManager.getLogger(ApiTestWithoutFeatures.class);
     int postId = 1;

    @Test
    void getPosts(){
        when()
                .get(baseUrl+"/posts")
                .then()
                .assertThat().statusCode(200);
                logger.info("Get All Posts");

    }

    @Test
    void getPostById(){
            given()
                .pathParam("postId", postId)
                .when()
                .get(baseUrl+"/posts/{postId}")
                .then()
                .log().all()
                .assertThat().statusCode(200);
                logger.info("HERE");
    }

    @Test
    void createNewPost(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "newTitle");
        jsonObject.put("body", "newBody");
        jsonObject.put("userId", 1);

        given()
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .log().all()
                .assertThat().statusCode(201);
                logger.info("New Post Created");
    }


    @Test
    void updatePostById(){
        JSONObject updatedJsonObject = new JSONObject();
        updatedJsonObject.put("id", postId);
        updatedJsonObject.put("title", "Rest");
        updatedJsonObject.put("body", "Assured");
        updatedJsonObject.put("userId", 1);

        given()
                .contentType(ContentType.JSON)
                .body(updatedJsonObject.toString())
                .pathParam("postId",postId)
                .when()
                .put("https://jsonplaceholder.typicode.com/posts/{postId}")
                .then()
                .statusCode(200)
                .log().all();
                logger.info("Post :" + postId + " Updated");
    }


    @Test
    void deletePostById(){
        given()
        .pathParam("postId", postId)
                .when()
                .delete(baseUrl + "/posts/{postId}")
                .then()
                .log().all()
                .assertThat().statusCode(200);
                logger.info("Post :" + postId+ " deleted");

    }



}
