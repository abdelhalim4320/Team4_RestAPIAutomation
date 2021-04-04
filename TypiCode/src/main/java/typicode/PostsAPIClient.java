package typicode;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class PostsAPIClient extends TypiCodeAPIClient {

    private String POST_ENDPOINT = "/posts";
    private String POST_ENDPOINT_ID = "/comments?postId=1";
    private String POST_ENDPOINT_DELETE= "/posts/1";
    private String POST_ENDPOINT_PUT= "/posts/1";

    public ValidatableResponse getAllPosts() {
        return when().get(this.baseUrl + POST_ENDPOINT).then();
    }


    public ValidatableResponse createPost(Object json){
        return given().header("Content-type","application/json").body(json)
                .when().post(this.baseUrl+POST_ENDPOINT).then();
    }

    public ValidatableResponse getAllPostsId() {
        return when().get(this.baseUrl + POST_ENDPOINT_ID).then();
    }

    public ValidatableResponse deletePost(int id) {
        return given().queryParam("id", id)
                .when().delete(this.baseUrl + POST_ENDPOINT_DELETE).then();
    }

    public ValidatableResponse putPost(Object json){
        return given().header("Content-type","application/json").body(json)
                .when().put(this.baseUrl+POST_ENDPOINT_PUT).then();
    }






}
