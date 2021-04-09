package tweet;

import base.RestAPI;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class TweetAPIClient extends RestAPI {

    private final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";
    private final String DELETE_TWEET_ENDPOINT = "/statuses/destroy.json";
    private final String GET_USER_TWEET_ENDPOINT = "/statuses/home_timeline.json";
    private final String GET_ACCOUNT_SETTINGS_ENDPOINT = "/account/settings.json";
    private final String GET_STATUS_SHOW_ENDPOINT = "/statuses/show.json";
    private final String CREATE_RETWEET_ENDPOINT = "/statuses/retweet.json";
    private final String UN_RETWEET_ENDPOINT = "/statuses/unretweet.json";
    private final String UPDATE_TWEET_ENDPOINT = "/statuses/update.json";
    private final String GET_RETWEET_ENDPOINT = "/statuses/retweets.json";
    private final String CREATE_FAVORITE_ENDPOINT = "/favorites/create.json";
    private final String DESTROY_FAVORITE_ENDPOINT = "/favorites/destroy.json";
    private final String GET_LOOKUP_ENDPOINT = "/statuses/lookup.json";
    private final String DELETE_MESSAGE_ENDPOINT = "/direct_messages/events/destroy.json";
    private final String GET_USER_TIMELINE_ENDPOINT = "/statuses/user_timeline.json";
    private final String CREATE_LISTS_ENDPOINT = "/lists/create.json";
    private final String GET_LISTS_ENDPOINT = "/lists/list.json";
    private final String CREATE_COLLECTIONS_ENDPOINT = "/collections/create.json";
    private final String GET_COLLECTIONS_SHOW_ENDPOINT = "/collections/show.json";
    private final String DESTROY_COLLECTIONS_ENDPOINT = "/collections/destroy.json";
    private final String SEARCH_TWEET_ENDPOINT = "/search/tweets.json";
    private final String GET_LIST_MEMBERS_ENDPOINT = "/lists/members.json";
    private final String VERIFY_CREDENTIALS_ENDPOINT = "/account/verify_credentials.json";
    private final String GET_PROFILE_BANNER_ENDPOINT = "/users/profile_banner.json";
    private final String REMOVE_PROFILE_BANNER_ENDPOINT = "/account/remove_profile_banner.json";
    private final String POST_SEARCH_ENDPOINT = "/saved_searches/create.json";
    private final String DESTROY_SEARCH_ENDPOINT = "/saved_searches/destroy/:id.json";
    private final String GET_LIST_SUBSCRIBERS_ENDPOINT = "/lists/subscribers.json";
    private final String GET_FRIENDS_LIST_ENDPOINT = "/friends/list.json";
    private final String DELETE_WELCOME_MESSAGE_ENDPOINT = "/direct_messages/welcome_messages/destroy.json";
    private final String GET_CUSTOM_PROFILES_ENDPOINT = "/custom_profiles.json";
    private final String DELETE_CUSTOM_PROFILES_ENDPOINT = "/custom_profiles/destroy.json";
    private final String GET_CUSTOM_PROFILES_LIST_ENDPOINT = "/custom_profiles/list.json";
    private final String POST_BLOCK_CREATE_ENDPOINT = "/blocks/create.json";
    private final String GET_BLOCK_LIST_ENDPOINT = "/blocks/list.json";




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
    public ValidatableResponse deleteTweet(long tweetId) {
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

    // Get Account Settings
    public ValidatableResponse getAccountSettings() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_ACCOUNT_SETTINGS_ENDPOINT).then().statusCode(200);
    }

    // Get Tweet text using tweetId
    public ValidatableResponse getSearchTweets(long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.GET_STATUS_SHOW_ENDPOINT).then().statusCode(200);
    }

    // Retweet using ID
    public ValidatableResponse retweet(long retweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", retweetId)
                .when().post(this.baseUrl + this.CREATE_RETWEET_ENDPOINT)
                .then();
    }

    // Updating using String
    public ValidatableResponse updateTweet(String tweet) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("status", tweet)
                .when().post(this.baseUrl + this.UPDATE_TWEET_ENDPOINT)
                .then();
    }

    // Unretweet using ID
    public ValidatableResponse unRetweet(long retweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", retweetId)
                .when().post(this.baseUrl + this.UN_RETWEET_ENDPOINT)
                .then();
    }

    // Get retweet using String
    public ValidatableResponse getRetweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.GET_RETWEET_ENDPOINT)
                .then().statusCode(200);
    }

    // Create favorites
    public ValidatableResponse createFavorites(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetId)
                .when().post(this.baseUrl + this.CREATE_FAVORITE_ENDPOINT)
                .then();
    }

    // Delete favorites
    public ValidatableResponse destroyFavorites(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetId)
                .when().post(this.baseUrl + this.DESTROY_FAVORITE_ENDPOINT)
                .then();
    }

    // Get Lookup tweets
    public ValidatableResponse lookupTweets(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetId)
                .when().get(this.baseUrl + this.GET_LOOKUP_ENDPOINT)
                .then();
    }

    // Delete a Tweet from user twitter
    public ValidatableResponse deleteMessage(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().delete(this.baseUrl + this.DELETE_MESSAGE_ENDPOINT)
                .then();
    }

    // GET user timeline
    public ValidatableResponse getUserTimeLine() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_TIMELINE_ENDPOINT).then().statusCode(200);
    }

    // Create a List
    public ValidatableResponse createList(String name) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("name", name)
                .when().post(this.baseUrl + this.CREATE_LISTS_ENDPOINT)
                .then();
    }

    // GET lists
    public ValidatableResponse getLists() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_LISTS_ENDPOINT).then().statusCode(200);
    }

    // Create a collections
    public ValidatableResponse createCollections(String name) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("name", name)
                .when().post(this.baseUrl + this.CREATE_COLLECTIONS_ENDPOINT)
                .then();
    }

    public ValidatableResponse getCollections(String customId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", customId)
                .when().get(this.baseUrl + this.GET_COLLECTIONS_SHOW_ENDPOINT)
                .then();
    }

    // Delete collections
    public ValidatableResponse destroyCollections(String customId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", customId)
                .when().post(this.baseUrl + this.DESTROY_COLLECTIONS_ENDPOINT)
                .then();
    }

    // Search tweet
    public ValidatableResponse searchTweet(String value) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("q", value)
                .when().get(this.baseUrl + this.SEARCH_TWEET_ENDPOINT).then().statusCode(200);
    }

    // Get list members
    public ValidatableResponse getListMembers(long id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("list_id", id)
                .when().get(this.baseUrl + this.GET_LIST_MEMBERS_ENDPOINT).then().statusCode(200);
    }

    // Verify credentials
    public ValidatableResponse verifyCredentials() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.VERIFY_CREDENTIALS_ENDPOINT).then().statusCode(200);
    }

    // Get profile banner
    public ValidatableResponse getProfileBanner() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_PROFILE_BANNER_ENDPOINT).then().statusCode(404);
    }

    // Delete a profile banner
    public ValidatableResponse deleteProfileBanner() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().post(this.baseUrl + this.REMOVE_PROFILE_BANNER_ENDPOINT).then().statusCode(200);
    }

    // Create search
    public ValidatableResponse createSearch(String query) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("query", query)
                .when().post(this.baseUrl + this.POST_SEARCH_ENDPOINT)
                .then();
    }

    // Delete a search
    public ValidatableResponse deleteSearch(long searchId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", searchId)
                .when().post(this.baseUrl + this.DESTROY_SEARCH_ENDPOINT).then();
    }

    // Get list subscribers
    public ValidatableResponse getSubscribers(long listId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", listId)
                .when().get(this.baseUrl + this.GET_LIST_SUBSCRIBERS_ENDPOINT).then().statusCode(400);
    }

    // Get friends list
    public ValidatableResponse getFriendsList() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_FRIENDS_LIST_ENDPOINT).then();
    }

    // Delete message
    public ValidatableResponse deleteWelcomeMessage(long messageId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", messageId)
                .when().delete(this.baseUrl + this.DELETE_WELCOME_MESSAGE_ENDPOINT)
                .then();
    }

    // Get custom profiles
    public ValidatableResponse getCustomProfiles(long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.GET_CUSTOM_PROFILES_ENDPOINT).then();
    }

    // Get custom profiles
    public ValidatableResponse getCustomProfilesList() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_CUSTOM_PROFILES_LIST_ENDPOINT).then();
    }

    // Delete custom profiles
    public ValidatableResponse deleteCustomProfiles(long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.DELETE_CUSTOM_PROFILES_ENDPOINT).then();
    }

    // Block user using screen name
    public ValidatableResponse blockByScreenName(String screenName) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("screen_name", screenName)
                .when().post(this.baseUrl + this.POST_BLOCK_CREATE_ENDPOINT).then();
    }

    // Block user using id
    public ValidatableResponse blockById(long id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", id)
                .when().post(this.baseUrl + this.POST_BLOCK_CREATE_ENDPOINT).then();
    }

    // GET list of blocked users
    public ValidatableResponse getBlockList(boolean skipStatus) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("skip_status", skipStatus)
                .when().get(this.baseUrl + this.GET_BLOCK_LIST_ENDPOINT).then();
    }
}
