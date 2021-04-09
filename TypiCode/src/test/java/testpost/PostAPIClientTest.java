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
    public void setUpPostsAPI() {
        this.postsAPIClient = new PostsAPIClient();
    }

    @Test
    public void testGetAllPosts() {
        ValidatableResponse response = this.postsAPIClient.getAllPosts();
        response.statusCode(HttpStatus.SC_OK);
        System.out.println(response.extract().body().asPrettyString());
        String expectedBody = "qui est esse";
        String actualBody = response.extract().body().path("[1].title");
        Assert.assertEquals(actualBody, expectedBody);
    }

    @Test
    public void testUserCanCreateAPostUsingPojoSuccessfully() {
        PostPojo obj = new PostPojo(1, 101, "test title", "test body");
        ValidatableResponse response = this.postsAPIClient.createPost(obj);
        response.statusCode(HttpStatus.SC_CREATED);
        System.out.println(response.extract().body().asPrettyString());
        String expectedBody = "test body";
        String actualBody = response.extract().body().path("body");
        Assert.assertEquals(actualBody, expectedBody);
    }

    @Test
    public void testUserCanCreateAPostSuccessfully() {
        JSONObject json = new JSONObject();
        json.put("userId", 11);
        json.put("id", 101);
        json.put("title", "new title");
        json.put("body", "new body");
        ValidatableResponse response = this.postsAPIClient.createPost(json);
        response.statusCode(HttpStatus.SC_CREATED);
        System.out.println(response.extract().body().asPrettyString());
        String expectedBody = "new body";
        String actualBody = response.extract().body().path("body");
        Assert.assertEquals(actualBody, expectedBody);
    }

    @Test
    public void testUserCanGetPostsUsingId() {
        ValidatableResponse response = this.postsAPIClient.getAllPostsId();
        response.statusCode(HttpStatus.SC_OK);
        System.out.println(response.extract().body().asPrettyString());
        String expectedEmail = "Eliseo@gardner.biz";
        String actualEmail = response.extract().body().path("[0].email");
        Assert.assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void testDeletePost() {
        ValidatableResponse response = this.postsAPIClient.deletePost(1);
        response.statusCode(HttpStatus.SC_OK);
        System.out.println(response.extract().body().asPrettyString());
        String expectedTitle = null;
        String actualTitle = response.extract().body().path("[0].title");
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testUserCanPutAPostUsingPojoSuccessfully() {
        PostPojo obj = new PostPojo(1, 1, "update title", "update body");
        ValidatableResponse response = this.postsAPIClient.putPost(obj);
        response.statusCode(HttpStatus.SC_OK);
        System.out.println(response.extract().body().asPrettyString());
        String expectedTitle = "update title";
        String actualTitle = response.extract().body().path("title");
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetUsersUsingId() {
        ValidatableResponse response = this.postsAPIClient.getUsersUsingId(1);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(HttpStatus.SC_OK);
        String expectedName = "Leanne Graham";
        String actualName = response.extract().body().path("[0].name");
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void testDeletePhotoUsingTitle() {
        String title = "accusamus beatae ad facilis cum similique qui sunt";
        ValidatableResponse response = this.postsAPIClient.deletePhotoUsingTitle(title);
        System.out.println(response.extract().body().asPrettyString());
        String expectedTitle = null;
        String actualTitle = response.extract().body().path("[0].title");
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetAlbumUsingTitle() {
        String title = "eaque aut omnis a";
        ValidatableResponse response = this.postsAPIClient.getAlbumUsingId(title);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(HttpStatus.SC_OK);
        String actualTitle = response.extract().body().path("[0].title");
        Assert.assertEquals(title, actualTitle);
    }

    @Test
    public void testUserCanCreateTodos() {
        JSONObject json = new JSONObject();
        json.put("userId", 11);
        json.put("id", 1);
        json.put("title", "my new todos title");
        json.put("completed", "true");
        ValidatableResponse response = this.postsAPIClient.updateTodos(json);
        response.statusCode(HttpStatus.SC_CREATED);
        System.out.println(response.extract().body().asPrettyString());
        String expectedTitle = "my new todos title";
        String actualTitle = response.extract().body().path("title");
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testGetPhotoUsingUrl() {
        String url = "https://via.placeholder.com/600/771796";
        ValidatableResponse response = this.postsAPIClient.getPhotoUsingUrl(url);
        System.out.println(response.extract().body().asPrettyString());
        String actualUrl = response.extract().body().path("[0].url");
        Assert.assertEquals(url, actualUrl);
    }

    @Test
    public void testUserCanUploadPicture() {
        JSONObject json = new JSONObject();
        json.put("albumId", 101);
        json.put("id", 5001);
        json.put("title", "my new uploaded photo");
        json.put("url", "https://via.placeholder.com/600/6ffa50");
        json.put("thumbnailUrl", "https://via.placeholder.com/150/6ffa50");
        ValidatableResponse response = this.postsAPIClient.uploadPhotoUsingTitle(json);
        response.statusCode(HttpStatus.SC_CREATED);
        System.out.println(response.extract().body().asPrettyString());
        String expectedTitle = "my new uploaded photo";
        String actualTitle = response.extract().body().path("title");
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testCanGetPhotoUsingUrl() {
        String url = "https://via.placeholder.com/600/771796";
        ValidatableResponse response = this.postsAPIClient.getPhotoUsingUrl(url);
        System.out.println(response.extract().body().asPrettyString());
        String actualUrl = response.extract().body().path("[0].url");
        Assert.assertEquals(url, actualUrl);
    }
}
