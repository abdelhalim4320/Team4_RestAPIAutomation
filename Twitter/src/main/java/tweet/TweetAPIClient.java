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

    private final String RETWEET_ENDPOINT = "/statuses/retweet.json";
    private final String POST_FAVORITE_ENDPOINT = "/favorites/create.json";
    private final String DELETE_FAVORITE_ENDPOINT = "/favorites/destroy.json";
    private final String GET_RETWEET_ENDPOINT = "/statuses/retweets.json";
    private final String UN_RETWEET_ENDPOINT = "/statuses/unretweet.json";
    private final String CREATE_LIST_ENDPOINT = "/lists/create.json";
    private final String DESTROY_LIST_ENDPOINT = "/lists/destroy.json";
    private final String GET_LISTS_LIST_ENDPOINT = "/lists/list.json";
    private final String POST_FRIENDSHIPS_CREATE_ENDPOINT = "/friendships/create.json";
    private final String GET_FOLLOWERS_ENDPOINT = "/followers/list.json";
    private final String DESTROY_FOLLOWERS_ENDPOINT = "/friendships/destroy.json";
    private final String GET_FRIENDS_ENDPOINT = "/friends/ids.json";
    private final String GET_FRIENDS_List_ENDPOINT = "/friends/list.json";
    private final String POST_ACCOUNT_UPDATE_PROFILE_ENDPOINT = "/account/update_profile.json";
    private final String POST_ACCOUNT_UPDATE_PROFILE_IMAGE_ENDPOINT = "/account/update_profile_image.json";
    private final String POST_ACCOUNT_UPDATE_PROFILE_BANNER_IMAGE_ENDPOINT = "/account/update_profile_banner.json";
    private final String POST_BLOCKS_USERS_CREATE_ENDPOINT = "/blocks/create.json";
    private final String POST_BLOCKS_USERS_DESTROY_ENDPOINT = "/blocks/destroy.json";
    private final String POST_MUTES_USERS_CREATE_ENDPOINT = "/mutes/users/create.json";
    private final String POST_MUTES_USERS_DESTROY_ENDPOINT = "/mutes/users/destroy.json";
    private final String POST_USERS_REPORT_SPAM_ENDPOINT = "/users/report_spam.json";
    private final String GET_BLOCKS_USER_LIST_ENDPOINT = "/blocks/list.json";
    private final String GET_MUTES_USER_LIST_ENDPOINT = "/mutes/users/list.json";
    private final String GET_FAVORITES_LIST_ENDPOINT = "/favorites/list.json";
    private final String GET_USERS_LOOKUP_END_POINT = "/users/lookup.json";
    private final String POST_DIRECT_MESSAGE_END_POINT = "/direct_messages/events/new.json";
    private final String POST_DESTROY_MESSAGE_END_POINT = "/direct_messages/events/destroy.json";
    private final String GET_MESSAGE_END_POINT = "/direct_messages/events/show.json";
    private final String POST_ADD_MEMBERS_END_POINT = "/lists/members/create.json";
    private final String POST_CUSTOM_PROFILES_END_POINT = "/custom_profiles/new.json";
    private final String POST_UPDATE_PROFILE_END_POINT = "/account/update_profile.json";
    private final String POST_COLLECTION_TWEETE_END_POINT = "/collections/create.json";
    private final String DESTROY_COLLECTION_TWEETE_END_POINT = "/collections/destroy.json";
    private final String POST_MEDIA_UPLOAD_END_POINT = "/media/upload.json";
    private final String POST_TWEET_WITH_MEDIA_UPLOAD_END_POINT = "/statuses/update_with_media.json";
    private final String POST_UPDATE_PROFILE_IMAGE_END_POINT = "/account/update_profile_image.json";
    private final String POST_UPDATE_PROFILE_BANNER_END_POINT = "/account/update_profile_banner.json";



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

    // reTweet
    public ValidatableResponse reTweet(long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .post(this.baseUrl + this.RETWEET_ENDPOINT)
                .then();
    }

    // Get retweet id
    public ValidatableResponse getUserRetweet(long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.GET_RETWEET_ENDPOINT).then().statusCode(200);
    }

    // postPOST statuses/unreTweet/:id
    public ValidatableResponse unreTweet(long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .post(this.baseUrl + this.UN_RETWEET_ENDPOINT).then();
    }

    //  favorite(like) a tweet
    public ValidatableResponse favoriteTweet(long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .post(this.baseUrl + this.POST_FAVORITE_ENDPOINT)
                .then();
    }

    // delete favorite(unlike tweet)
    public ValidatableResponse deleteFavoriteTweet(long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .post(this.baseUrl + this.DELETE_FAVORITE_ENDPOINT)
                .then();
    }

    // GET FAVORITES LIST
    public ValidatableResponse getFavoritesList() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .get(this.baseUrl + this.GET_FAVORITES_LIST_ENDPOINT).then();
    }

    // **** CREATE LIST
    public ValidatableResponse createList(String name) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("name", name)
                .post(this.baseUrl + this.CREATE_LIST_ENDPOINT).then();
    }
    // delete list
    public ValidatableResponse deleteList(String name, long id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParams("name", name, "id", id)
                .post(this.baseUrl + this.DESTROY_LIST_ENDPOINT).then();
    }

    // GET all LISTS
    public ValidatableResponse getAllLists() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_LISTS_LIST_ENDPOINT).then().statusCode(200);
    }

    // follow users
    public ValidatableResponse followUsers(String userName) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", userName)
                .post(this.baseUrl + this.POST_FRIENDSHIPS_CREATE_ENDPOINT).then();
    }

    public ValidatableResponse unFollowUsers(String userName) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", userName)
                .post(this.baseUrl + this.DESTROY_FOLLOWERS_ENDPOINT).then();
    }

    // GET FOLLOWERS
    public ValidatableResponse getFollowers() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_FOLLOWERS_ENDPOINT).then().statusCode(200);
    }

    // GET friends with id
    public ValidatableResponse getFriendsIds() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_FRIENDS_ENDPOINT).then().statusCode(200);
    }

    // GET friends list
    public ValidatableResponse getFriendsList() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_FRIENDS_List_ENDPOINT).then().statusCode(200);
    }

    // post account location
    public ValidatableResponse postAccountLocation(String location) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("location", location)
                .post(this.baseUrl + this.POST_ACCOUNT_UPDATE_PROFILE_ENDPOINT).then();
    }

    // postAccountUploadProfileImage
    public ValidatableResponse postAccountUploadProfileImage(String url) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("url", url)
                .post(this.baseUrl + this.POST_ACCOUNT_UPDATE_PROFILE_IMAGE_ENDPOINT).then();
    }

    // postAccount Upload Profile banner (header picture)
    public ValidatableResponse postAccountUploadProfileBanner(String banner) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("banner", banner)
                .post(this.baseUrl + this.POST_ACCOUNT_UPDATE_PROFILE_BANNER_IMAGE_ENDPOINT).then();
    }

    //**********  Mute, block, and report users
    //1.POST blocks/create
    public ValidatableResponse blockUsers(String screenName) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("screen_name", screenName)
                .post(this.baseUrl + this.POST_BLOCKS_USERS_CREATE_ENDPOINT).then();
    }

    //2.POST blocks/destroy
    public ValidatableResponse unBlockUsers(String screenName) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("screen_name", screenName)
                .post(this.baseUrl + this.POST_BLOCKS_USERS_DESTROY_ENDPOINT).then();
    }

    //3.POST MUTES USERS CREATE
    public ValidatableResponse muteUsers(String screenName) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("screen_name", screenName)
                .post(this.baseUrl + this.POST_MUTES_USERS_CREATE_ENDPOINT).then();
    }

    // 4.POST MUTES USERS destroy (unMute users)
    public ValidatableResponse unMuteUsers(String screenName) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("screen_name", screenName)
                .post(this.baseUrl + this.POST_MUTES_USERS_DESTROY_ENDPOINT).then();
    }

    // 5.POST users/report_spam
    public ValidatableResponse postUsersReportSpam(String screenName) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("screen_name", screenName)
                .post(this.baseUrl + this.POST_USERS_REPORT_SPAM_ENDPOINT).then();
    }

    //6.get blocks list
    public ValidatableResponse getBlocksList() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .get(this.baseUrl + this.GET_BLOCKS_USER_LIST_ENDPOINT).then();
    }

    //6.get mutes users list
    public ValidatableResponse getMutedUserList() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .get(this.baseUrl + this.GET_MUTES_USER_LIST_ENDPOINT).then();
    }

    //*** get users lookup
    public ValidatableResponse getUsersLookup() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .get(this.baseUrl + this.GET_USERS_LOOKUP_END_POINT).then();
    }

    // send direct message
    public ValidatableResponse sendDirectMessage() throws FileNotFoundException {
        String filePath="C:\\Users\\15513\\IdeaProjects\\Team4_RestAPIAutomation\\Twitter\\JsonFile\\sendMessage.json";
        FileInputStream message=new FileInputStream(filePath);
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .log().all()
                .accept(ContentType.JSON)
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .body(message)
                .when().post(this.baseUrl + this.POST_DIRECT_MESSAGE_END_POINT)
                .then().log().all();
    }
    // delete message , not working
    public ValidatableResponse deleteMessage(long id) throws FileNotFoundException {
        String filePath="C:\\Users\\15513\\IdeaProjects\\Team4_RestAPIAutomation\\Twitter\\JsonFile\\sendMessage.json";
        FileInputStream message=new FileInputStream(filePath);
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("recipient_id",id)
//                .log().all()
//                .accept(ContentType.JSON)
//                .header("Content-type", "application/json")
//                .contentType(ContentType.JSON)
                .body(message)
                .when().post(this.baseUrl + this.POST_DESTROY_MESSAGE_END_POINT)
                .then();
//                log().all();
    }
    //*** get message not working
    public ValidatableResponse getMessage() throws FileNotFoundException {
        String filePath="C:\\Users\\15513\\IdeaProjects\\Team4_RestAPIAutomation\\Twitter\\JsonFile\\getMessage.json";
        FileInputStream message=new FileInputStream(filePath);
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
//                .queryParam("id",id)
                .body(message)
                .get(this.baseUrl + this.GET_MESSAGE_END_POINT).then();
    }
    // add members to lis not working
    public ValidatableResponse addMembers() throws FileNotFoundException {
        String filePath="C:\\Users\\15513\\IdeaProjects\\Team4_RestAPIAutomation\\Twitter\\JsonFile\\memberList.json";
        FileInputStream message=new FileInputStream(filePath);
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .log().all()
                .accept(ContentType.JSON)
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .body(message)
                .when().post(this.baseUrl + this.POST_ADD_MEMBERS_END_POINT)
                .then().log().all();
    }
    //   // post account update profile name
    public ValidatableResponse postAccountUpdateName(String name) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("name", name)
                .post(this.baseUrl + this.POST_UPDATE_PROFILE_END_POINT).then();
    }
    //   // post account update profile description
    public ValidatableResponse postAccountUpdateProfileDescription(String description) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("description", description)
                .post(this.baseUrl + this.POST_UPDATE_PROFILE_END_POINT).then();
    }
    // create collection
    public ValidatableResponse createCollectionTweet(String name) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("name", name)
                .post(this.baseUrl + this.POST_COLLECTION_TWEETE_END_POINT).then();
    }
    // upload a media (media)
    public ValidatableResponse uploadMedia(String image) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("media", image)
                .when().post(this.imageBaseUrl + this.POST_MEDIA_UPLOAD_END_POINT).then().log().all();
    }
    // Create a Tweet with media
    public ValidatableResponse createTweetWithMedia(String tweet,long media_Id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status", tweet)
                .param("media_ids", media_Id)
                .when().post(this.baseUrl + this.CREATE_TWEET_ENDPOINT)
                .then();
    }
    // update profile picture
    public ValidatableResponse updateProfileImage(String image) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("image", image)
                .when().post(this.baseUrl + this.POST_UPDATE_PROFILE_IMAGE_END_POINT)
                .then();
    }
    // update profile picture
    public ValidatableResponse updateProfileBanner(String banner) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("banner", banner)
                .when().post(this.baseUrl + this.POST_UPDATE_PROFILE_BANNER_END_POINT)
                .then();
    }



    public ValidatableResponse sendDirectMessage1() throws FileNotFoundException {
        String filePath="C:\\Users\\15513\\IdeaProjects\\Team4_RestAPIAutomation\\Twitter\\JsonFile\\hello.json";
        FileInputStream data=new FileInputStream(filePath);
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .log().all()
                .accept(ContentType.JSON)
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .body(data)
                .when().post(this.baseUrl + this.POST_DIRECT_MESSAGE_END_POINT)
                .then().log().all();
    }










}
