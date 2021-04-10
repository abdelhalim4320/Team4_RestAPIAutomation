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

    private final String ID_JSON = ".json";
    private final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";
    private final String DELETE_TWEET_ENDPOINT = "/statuses/destroy.json";
    private final String GET_USER_TWEET_ENDPOINT = "/statuses/home_timeline.json";
    private final String POST_FAVORITE_TWEET_ENDPOINT = "/favorites/create.json";
    private final String POST_RETWEET_ENDPOINT = "/statuses/retweet.json";
    private final String POST_undoRETWEET_ENDPOINT = "/statuses/unretweet.json";
    private final String GET_TIMELINE_TWEET_ENDPOINT = "/statuses/home_timeline.json";
    private final String CREATE_MESSAGE_ENDPOINT = "/direct_messages/events/new.json";
    private final String DELETE_MESSAGE_ENDPOINT = "/direct_messages/events/destroy.json";
    private final String GET_FRIENDSLIST_ENDPOINT = "/friends/list.json";
    private final String POST_LISTS_ENDPOINT="/lists/create.json";
    private final String POST_LISTS_SUBSCRIBERS_ENDPOINT="/subscribers/create.json";
    private final String GET_LISTS_SUBSCRIBERS_ENDPOINT="/lists/subscribers.json";
    private final String POST_STATUES_FILTER = "/statuses/filter.json";
    private final String get_STATUES_SIMPLE = "/statuses/sample.json";
    private final String get_COLLECTIONS_ENTRIES = "/collections/entries.json";
    private final String CREATE_MEDIA_ENDPOINT = "/media/upload.json";
//    private final String CREATE_MESSAGE_ENDPOINT = "/direct_messages/events/new.json";
    private final String GET_ACCOUNT_SETTING = "/account/settings.json";
    private final String GET_ACCOUNT_VERIFY = "/account/verify_credentials.json";
    private final String GET_UsersProfile_BANNER = "/users/profile_banner.json";
    private final String POST_Account_Remove = "/account/remove_profile_banner.json";
    private final String POST_Account_Settings = "/account/settings.json";
    private final String POST_Account_UPDATE = "/account/update_profile.json";
    private final String POST_SAVE_SEARCH = "/saved_searches/list.json";
    private final String GET_SAVE_SEARCHShow = "/saved_searches/show/";
    private final String GET_SAVE_JSON = ".json";
    private final String POST_SAVED_SEARCH = "/saved_searches/create.json";
    private final String POST_SAVED_SEARCH_DESTROY = "/saved_searches/destroy/";
    private final String POST_ID_JSON = ".json";
    private final String POST_PROFILE_IMAGE_ENDPOINT = "/account/update_profile_image.json";
    private final String POST_MEDIA_ENDPOINT = "/media/upload.json";
    private final String POST_WELCOME_MESSAGE = "/direct_messages/welcome_messages/new.json";
    private final String POST_STATUES_ENDPOINT = "/statuses/update.json";
    private final String DELETE_STATUES_ENDPOINT = "/statuses/destroy/";

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

    }

    public ValidatableResponse DeleteDirectMessages() throws FileNotFoundException {
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

    // GET all Tweet Information
    public ValidatableResponse getFriendsList() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_FRIENDSLIST_ENDPOINT)
                .then().statusCode(200);
    }
    public ValidatableResponse createFreindList(String screenName){
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .queryParam("list",screenName)
                .when().post(this.baseUrl+this.POST_LISTS_ENDPOINT)
                .then();
    }
    public ValidatableResponse createListSubscriber(long listID){
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

    public ValidatableResponse getFilter() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().post(this.baseUrl + this.POST_STATUES_FILTER)
                .then().log().all().assertThat().statusCode(200);
    }


    public ValidatableResponse getStatuesSimple() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.get_STATUES_SIMPLE)
                .then().log().all().assertThat().statusCode(200);
    }

    public ValidatableResponse getCollectionsEntries(String tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.get_COLLECTIONS_ENTRIES)
                .then().log().all();
        //assertThat().statusCode(200)
    }


    public ValidatableResponse createMessage() throws FileNotFoundException {
        FileInputStream jsonMessage = new FileInputStream("C:/Users/sadou/IdeaProjects/Team4_RestAPIAutomation/Twitter/jsonDirectory/jsonMessageSecond.json");
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .log().all()
                .accept(ContentType.JSON)
                .header("Content_Type", "application/json")
                .contentType(ContentType.JSON)
                .body(jsonMessage)
                .when().post(this.baseUrl + this.CREATE_MESSAGE_ENDPOINT)
                .then().log().all();

    }


    public ValidatableResponse createSecondMessage() throws FileNotFoundException {
        FileInputStream jsonMessage = new FileInputStream("C:\\Users\\sadou\\IdeaProjects\\Team4_RestAPIAutomation\\Twitter\\jsonDirectory\\jsonMessageSecond.json");
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .log().all()
                .accept(ContentType.JSON)
                .header("Content_Type", "application/json")
                .contentType(ContentType.JSON)
                .body(jsonMessage)
                .when().post(this.baseUrl + this.CREATE_MESSAGE_ENDPOINT)
                .then().log().all();
    }


    public ValidatableResponse getAccountSetting() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
//                   .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.GET_ACCOUNT_SETTING)
                .then();
    }

    public ValidatableResponse getAccountVerifyCredentials() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_ACCOUNT_VERIFY)
                .then().log().all();
    }

    public ValidatableResponse getUsersProfileBanner(Long id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("user_id", id)
                .when().get(this.baseUrl + this.GET_UsersProfile_BANNER)
                .then();
    }

    public ValidatableResponse postRemoveAccount() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().post(this.baseUrl + this.POST_Account_Remove)
                .then().log().all();
    }


    public ValidatableResponse postAccountSetting() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().post(this.baseUrl + this.POST_Account_Settings)
                .then().log().all();
    }


    public ValidatableResponse postAccountUpdateProfile() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().post(this.baseUrl + this.POST_Account_UPDATE)
                .then().log().all();
    }


    public ValidatableResponse postSavedSearchesList() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                // .queryParam("banner", banner)
                .when().get(this.baseUrl + this.POST_SAVE_SEARCH)
                .then().log().all();
    }


    public ValidatableResponse getSavedSearchesShow(String tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.GET_SAVE_SEARCHShow + tweetId + GET_SAVE_JSON)
                .then().log().all();
    }


    public ValidatableResponse postSavedSearch(String Query) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("query", Query)
                .when().post(this.baseUrl + this.POST_SAVED_SEARCH)
                .then().log().all();
    }


    public ValidatableResponse postSavedSearchesDelete(String tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.POST_SAVED_SEARCH_DESTROY + tweetId + POST_ID_JSON)
                .then().log().all();
    }

    public ValidatableResponse uploadProfileImage(String image) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("image", image)
                .when().post(this.baseUrl + this.POST_PROFILE_IMAGE_ENDPOINT)
                .then().log().all();
    }
//
//    public ValidatableResponse uploadPostImage(String base64) {
//        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret);
////                .param("media", base64)
//                .param("shared", "1")
//                .when().post(this.uploadBase + this.POST_MEDIA_ENDPOINT)
//                .then().log().all();


    public ValidatableResponse createWelcomeMessage(String s, String flowerImage) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Owner\\IdeaProjects\\RestAPIAutomation_Team2\\Twitter\\JSonFile\\WelcomMessage.json");
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .log().all()
                .accept(ContentType.JSON)
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(inputStream)
                .when().post(this.baseUrl + this.POST_WELCOME_MESSAGE)
                .then();
    }
    public ValidatableResponse postStatus(String Status) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("status", Status)
                .when().post(this.baseUrl + this.POST_STATUES_ENDPOINT)
                .then().log().all().assertThat().statusCode(200);
    }
    public ValidatableResponse deleteStatus(String tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id_str", tweetId)
                .when().post(this.baseUrl + this.DELETE_STATUES_ENDPOINT + tweetId + ID_JSON)
                .then().log().all().assertThat().statusCode(200);
    }
}
