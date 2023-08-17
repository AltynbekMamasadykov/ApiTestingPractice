package tests;
import io.restassured.response.Response;
import model.PostDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.PostService;
import utils.ApiTestData;
import utils.JsonUtils;
import org.assertj.core.api.SoftAssertions;
import java.net.HttpURLConnection;

class ApiTest {

    protected static final String baseUrl = ApiTestData.baseUrl;
    protected static final Logger logger = LogManager.getLogger(ApiTest.class);
    private final PostService postService = new PostService(baseUrl);
    private final int postId = ApiTestData.postId;
    SoftAssertions softAssertions = new SoftAssertions();

    @BeforeEach
    void setUp() {
        PostDTO postDTO = ApiTestData.newPost();
        String postAsJson = JsonUtils.toJson(postDTO);
        Response response = postService.createNewPost(postAsJson);
        logger.info("New Post Created");
        Assertions.assertEquals(HttpURLConnection.HTTP_CREATED, response.statusCode());
    }

    @AfterEach
    public void tearDownAfterEach() {
        Response response = postService.deletePostById(ApiTestData.newPostId);
        logger.info("clean up performed");
        Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
    }


    @Test
    void getAllPosts(){
        Response response = postService.getAllPosts();
        logger.info("Get All Posts");
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }

    @Test
    void getPostById(){
        Response response = postService.getPostById(postId);
        logger.info("Get post by Id: "+ postId);
        String responseBody = response.getBody().asString();
        PostDTO postDTO = JsonUtils.fromJson(responseBody, PostDTO.class);

        softAssertions.assertThat(HttpURLConnection.HTTP_OK).isEqualTo(response.statusCode());
        softAssertions.assertThat(postId).isEqualTo(postDTO.getId());
        softAssertions.assertAll();
    }

    @Test
    void updatePostById(){
        Response response = postService.getPostById(ApiTestData.postId);
        logger.info("Get post by Id: "+ ApiTestData.postId);
        String responseBody = response.getBody().asString();
        PostDTO postDTO = JsonUtils.fromJson(responseBody, PostDTO.class);
        String titleBeforeUpdate = postDTO.getTitle();
        logger.info("title before update: "+titleBeforeUpdate);
        softAssertions.assertThat(HttpURLConnection.HTTP_OK).isEqualTo(response.statusCode());

        PostDTO updatePostDTO = ApiTestData.updatePost();
        String updatePostAsJson = JsonUtils.toJson(updatePostDTO);
        Response responseAfterUpdate = postService.updatePostById(updatePostAsJson);
        logger.info("Updated post by Id: "+ ApiTestData.postId);
        String responseBodyAfterUpdate = responseAfterUpdate.getBody().asString();
        PostDTO postDTOAfterUpdate = JsonUtils.fromJson(responseBodyAfterUpdate, PostDTO.class);
        String titleAfterUpdate = postDTOAfterUpdate.getTitle();
        logger.info("title after update: "+titleAfterUpdate);
        softAssertions.assertThat(HttpURLConnection.HTTP_OK).isEqualTo(responseAfterUpdate.statusCode());

        softAssertions.assertThat(titleBeforeUpdate).isNotEqualTo(titleAfterUpdate);
        softAssertions.assertAll();
    }
}
