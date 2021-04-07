package tweet;

import base.RestAPI;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class TweetAPIClient extends RestAPI {

    private final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";
    private final String DELETE_TWEET_ENDPOINT = "/statuses/destroy.json";
    private final String GET_USER_TWEET_ENDPOINT = "/statuses/home_timeline.json";
    private final String POST_FAVORITE_TWEET_ENDPOINT = "/favorites/create.json";
    private final String POST_RETWEET_ENDPOINT = "/statuses/retweet.json";
    private final String POST_undoRETWEET_ENDPOINT = "/statuses/unretweet.json";
    private final String GET_TIMELINE_TWEET_ENDPOINT = "/statuses/home_timeline.json";
    private final String CREATE_MESSAGE_ENDPOINT = "/direct_messages/events/new.json";
    private final String DELETE_MESSAGE_ENDPOINT = "/direct_messages/events/destroy.json";

    // GET all Tweet Information
    public ValidatableResponse getUserTimeTweet() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT).then().statusCode(200);
    }

    // Create a Tweet from user twitter
    public ValidatableResponse createTweet(String tweet) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status", tweet)
                .when().post(this.baseUrl + this.CREATE_TWEET_ENDPOINT)
                .then();
    }

    // Delete a tweet from user twitter
    public ValidatableResponse deleteTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.DELETE_TWEET_ENDPOINT).then().statusCode(200);
    }

    //
    // Response Time check
    public ValidatableResponse responseTime() {
        System.out.println(given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT)
                .timeIn(TimeUnit.MILLISECONDS));
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT)
                .then();

    }

    // Header Value
    public void headerValue() {
        System.out.println(given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT)
                .then().extract().headers());

    }

    public void checkProperty() {
        Response response = given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT);
        JsonPath pathEvaluator = response.jsonPath();
        String createdAt = pathEvaluator.get("id");
        System.out.println(createdAt);
    }

    // Create_post favorite
    public ValidatableResponse postFavorite(long id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", id)
                .when().post(this.baseUrl + this.POST_FAVORITE_TWEET_ENDPOINT)
                .then();
    }

    // Create_post retweet
    public ValidatableResponse postRetweet(long id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", id)
                .when().post(this.baseUrl + this.POST_RETWEET_ENDPOINT)
                .then();
    }

    // Create_post unretweet
    public ValidatableResponse unRetweet(long id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", id)
                .when().post(this.baseUrl + this.POST_undoRETWEET_ENDPOINT)
                .then();
    }

    // GET all Tweet Information
    public ValidatableResponse getTimeLine() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT).then().statusCode(200);
    }

    public ValidatableResponse postDirectMessages() throws FileNotFoundException {
        FileInputStream jsonMessageSecond = new FileInputStream("C:/Users/sadou/IdeaProjects/Team4_RestAPIAutomation/Twitter/jsonDirectory/jsonMessageSecond.json");
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .log().all()
                .accept(ContentType.JSON)
                .header("content-type", "application/json")
                .contentType(ContentType.JSON)
                .body(jsonMessageSecond)
                .when().post(this.baseUrl + this.CREATE_MESSAGE_ENDPOINT)
                .then().log().all();

    }   public ValidatableResponse DeleteDirectMessages() throws FileNotFoundException {
       FileInputStream jsonMessageSecond = new FileInputStream("C:/Users/sadou/IdeaProjects/Team4_RestAPIAutomation/Twitter/jsonDirectory/jsonMessageSecond.json");
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .log().all()
                .accept(ContentType.JSON)
                .header("content-type", "application/json")
                .contentType(ContentType.JSON)
                .body(jsonMessageSecond)
                .when().post(this.baseUrl + this.CREATE_MESSAGE_ENDPOINT)
                .then().log().all();

    }
}
