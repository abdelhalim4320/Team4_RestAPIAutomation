package testpost;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import typicode.PostPojo;
import typicode.PostsAPIClient;

public class PostAPIClientTest {
    private PostsAPIClient postsAPIClient;

    @BeforeClass
    public void setUpPostsAPI(){
        this.postsAPIClient=new PostsAPIClient();
    }
    @Test
    public void testGetAllPosts(){
        ValidatableResponse response=this.postsAPIClient.getAllPosts();
        response.statusCode(HttpStatus.SC_OK);
    }

    // write a test that creates a post
    @Test
    public void testUserCanCreateAPostUsingPojoSuccessfully() {
        PostPojo obj = new PostPojo(11, 101, "test title", "test body");
        ValidatableResponse response = this.postsAPIClient.createPost(obj);
        response.statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void testUserCanCreateAPostSuccessfully() {
        int userId = 11;
        String title = "test title";
        String body = "test body";

        JSONObject json = new JSONObject();
        json.put("userId", userId);
        json.put("id", 101);
        json.put("title", title);
        json.put("body", body);
        ValidatableResponse response = this.postsAPIClient.createPost(json);
        response.statusCode(HttpStatus.SC_CREATED);

        int actualUserId = response.extract().body().path("userId");
        String actualTitle = response.extract().body().path("title");
        String actualBody = response.extract().body().path("body");
        Assert.assertEquals(actualUserId, userId);
        Assert.assertEquals(actualTitle, title);
        Assert.assertEquals(actualBody, body);
    }


}
