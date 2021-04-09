package typicode;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class PostsAPIClient extends TypiCodeAPIClient {

    private String POST_ENDPOINT = "/posts";
    private String POST_ENDPOINT_ID = "/comments?postId=1";
    private String POST_ENDPOINT_DELETE = "/posts/1";
    private String POST_ENDPOINT_PUT = "/posts/1";
    private String GET_ENDPOINT_USERS = "/users";
    private String GET_ENDPOINT_ALBUM = "/albums";
    private String DELETE_PHOTO_ENDPOINT = "/albums/1/photos";
    private String PUT_TODOS_ENDPOINT = "/todos";
    private String POST_PHOTO_ENDPOINT = "/photos";

    public ValidatableResponse getAllPosts() {
        return when().get(this.baseUrl + POST_ENDPOINT).then();
    }


    public ValidatableResponse createPost(Object json) {
        return given().header("Content-type", "application/json").body(json)
                .when().post(this.baseUrl + POST_ENDPOINT).then();
    }

    public ValidatableResponse getAllPostsId() {
        return when().get(this.baseUrl + POST_ENDPOINT_ID).then();
    }

    public ValidatableResponse deletePost(int id) {
        return given().queryParam("id", id)
                .when().delete(this.baseUrl + POST_ENDPOINT_DELETE).then();
    }

    public ValidatableResponse putPost(Object json) {
        return given().header("Content-type", "application/json").body(json)
                .when().put(this.baseUrl + POST_ENDPOINT_PUT).then();
    }

    public ValidatableResponse getUsersUsingId(int id) {
        return given().queryParam("id", id)
                .when().get(this.baseUrl + GET_ENDPOINT_USERS).then();
    }

    public ValidatableResponse deletePhotoUsingTitle(String title) {
        return given().queryParam("title", title)
                .when().delete(this.baseUrl + DELETE_PHOTO_ENDPOINT).then();
    }

    public ValidatableResponse getAlbumUsingId(String title) {
        return given().queryParam("title", title)
                .when().get(this.baseUrl + GET_ENDPOINT_ALBUM).then();
    }

    public ValidatableResponse updateTodos(Object json) {
        return given().header("Content-type", "application/json").body(json)
                .when().post(this.baseUrl + PUT_TODOS_ENDPOINT).then();
    }

    public ValidatableResponse uploadPhotoUsingTitle(Object json) {
        return given().header("Content-type", "application/json").body(json)
                .when().post(this.baseUrl + POST_PHOTO_ENDPOINT).then();
    }

    public ValidatableResponse getPhotoUsingUrl(String url) {
        return given().queryParam("url", url)
                .when().get(this.baseUrl + POST_PHOTO_ENDPOINT).then();
    }











}
