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

    private final String CREATE_TWEET_ENDPOINT="/statuses/update.json";
    private final String DELETE_TWEET_ENDPOINT="/statuses/destroy.json";
    private final String GET_USER_TWEET_ENDPOINT="/statuses/home_timeline.json";
    private final String GET_STATUSES_HOME_TIMELINE="/statuses/home_timeline.json";
    private final String POST_FAVORITE_TWEET_ENDPOINT="/favorites/create.json";
    private final String POST_DELETE_FAVORITE = "/favorites/destroy.json";
    private final String POST_RETWEET_ENDPOINT="/statuses/retweet.json";
    private final String POST_UNRETWEET_ENDPOINT="/unretweet.json";
    private final String GET_STATUSES_MENTIONS_TIMELINE="/mentions_timeline.json";
    private final String GET_LISTS_MEMBERS="/lists/members/show.json";
    private final String POST_DIRECT_MESSAGE="/direct_messages/events/new.json";
    private final String DELETE_DIRECT_MESSAGE="/direct_messages/events/destroy.json";
    private final String GET_FREIND_LIST_ENDPOINT="/friends/list.json";
    private final String POST_LISTS_ENDPOINT="/lists/create.json";
    private final String DELETE_LISTS_ENDPOINT="/lists/destroy.json";
    private final String POST_LISTS_MEMBERS_ENDPOINT="/lists/members/create.json";
    private final String POST_LISTS_SUBSCRIBERS_ENDPOINT="/subscribers/create.json";
    private final String GET_LISTS_SUBSCRIBERS_ENDPOINT="/lists/subscribers.json";
    private final String GET_LISTS_SHOW_ENDPOINT="/lists/show.json";
    private final String DESTROY_Members_ENDPOINT= "/members/destroy_all.json";
    private final String POST_STATUES_ENDPOINT = "/statuses/update.json";
    private final String GET_STATUES_ENDPOINT = "/statuses/show/";
    private final String DELETE_STATUES_ENDPOINT = "/statuses/destroy/";
    private final String POST_SAVE_SEARCH = "/saved_searches/list.json";



    // GET all Tweet Information
    public ValidatableResponse getUserTimeTweet(){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT).then().statusCode(200);
    }

    // Create a Tweet from user twitter
    public ValidatableResponse createTweet(String tweet){
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .param("status",tweet)
                .when().post(this.baseUrl+this.CREATE_TWEET_ENDPOINT)
                .then();
    }

    // Delete a tweet from user twitter
    public ValidatableResponse deleteTweet(Long tweetId){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("id",tweetId)
                .when().post(this.baseUrl+this.DELETE_TWEET_ENDPOINT).then().statusCode(200);
    }


    // Response Time check
    public ValidatableResponse responseTime(){
        System.out.println(given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT)
                .timeIn(TimeUnit.MILLISECONDS));
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT)
                .then();

    }

    // Header Value
    public void headerValue(){
        System.out.println(given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT)
                .then().extract().headers());

    }

    public  void checkProperty(){
        Response response= given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
       .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT);
        JsonPath pathEvaluator= response.jsonPath();
        String createdAt=pathEvaluator.get("id");
        System.out.println(createdAt);
    }

    public ValidatableResponse getTimeline(){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_STATUSES_HOME_TIMELINE).then().statusCode(200);
    }
public ValidatableResponse getTimeline1(){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_STATUSES_MENTIONS_TIMELINE).then().statusCode(200);
    }


    public ValidatableResponse postFavorite(long tweetId){
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .param("id",tweetId)
                .when().post(this.baseUrl+this.POST_FAVORITE_TWEET_ENDPOINT + tweetId + ".Json")
                .then();
    }

    public ValidatableResponse postDeleteFavorite(long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.POST_DELETE_FAVORITE+ tweetId + ".Json")
                .then();
    }

    public ValidatableResponse postRetweet(long id){
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .param("id",id)
                .when().post(this.baseUrl+this.POST_RETWEET_ENDPOINT)
                .then();
    }
    public ValidatableResponse postUnRetweet(long id){
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .queryParam("id",id)
                .when().post(this.baseUrl+this.POST_UNRETWEET_ENDPOINT)
                .then();
    }


//    ========================================== send message
    public ValidatableResponse postDirectMessages() throws FileNotFoundException {
    FileInputStream jsonmessage=new FileInputStream("C:/Users/user/IdeaProjects/Team4_RestAPIAutomation/Twitter/src/main/jsonDirectory/jsonMessage.json");
    return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
            //.log().all()
            .accept(ContentType.JSON)
            .header("content-type", "application/json")
            .contentType(ContentType.JSON)
            .body(jsonmessage)
            .when().post(this.baseUrl + this.POST_DIRECT_MESSAGE)
            .then();//.log().all();
}
//=======================================get list
    public ValidatableResponse getFreindList(String screenName){
    return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
            //.queryParam("screen_Name",screenName)
            .when().get(this.baseUrl+this.GET_FREIND_LIST_ENDPOINT)
            .then();
}
//===============================create list
    public ValidatableResponse creatFreindList(String screenName){
    return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
            .queryParam("list",screenName)
            .when().post(this.baseUrl+this.POST_LISTS_ENDPOINT)
            .then();
}
//=========================delete list
public ValidatableResponse deleteMemeberlIST(long listId){
    return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
            .queryParam("id",listId)
            .when().post(this.baseUrl+this.DELETE_LISTS_ENDPOINT).then().statusCode(200);
}


    public ValidatableResponse creatListMember(long listId, String screenName){
    return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
            .queryParam("ID", listId)
             .queryParam("name", screenName)
            .when().post(this.baseUrl+this.POST_LISTS_MEMBERS_ENDPOINT)
            .then();
}
//=============================================
    public ValidatableResponse creatListSubscriber(long listID){
    return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
            .queryParam("id",listID)
            .when().post(this.baseUrl+this.POST_LISTS_SUBSCRIBERS_ENDPOINT)
            .then();
}
    public ValidatableResponse getListSubscriber(long listID){
    return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
            .queryParam("id",listID)
            .when().get(this.baseUrl+this.GET_LISTS_SUBSCRIBERS_ENDPOINT)
            .then();
}
    public ValidatableResponse getListShow(long listID){
    return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
            .queryParam("id",listID)
            .when().get(this.baseUrl+this.GET_LISTS_SHOW_ENDPOINT)
            .then();
}
    public ValidatableResponse destroyMembersAll(long listId, String slug){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("id",listId)
                .queryParam("Slug", slug)
                .when().post(this.baseUrl+this.DESTROY_Members_ENDPOINT+ listId + slug + ".Json")
                .then();
    }
//    ==========================
    public ValidatableResponse postStatus(String Status) {
    return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
            .queryParam("status", Status)
            .when().post(this.baseUrl + this.POST_STATUES_ENDPOINT)
            .then();
}
    public ValidatableResponse getStatues(String postId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", postId)
                .when().get(this.baseUrl + this.GET_STATUES_ENDPOINT + postId + ".Json")
                .then();
    }

    public ValidatableResponse deleteStatus(String postId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", postId)
                .when().post(this.baseUrl + this.DELETE_STATUES_ENDPOINT + postId + ".Json")
                .then();
    }




}
