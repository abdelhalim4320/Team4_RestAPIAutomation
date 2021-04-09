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
    private final String GET_USER_TWEET_TIMELINE = "/statuses/user_timeline.json";
    private final String GET_ACCOUNT_SETTINGS_ENDPOINT = "/account/settings.json";
    private final String GET_STATUS_SHOW_ENDPOINT = "/statuses/show.json";
    private final String GET_USER_ACCOUNT_SETTINGS = "/account/settings.json";
    private final String GET_USER_STATUSES_RETWEET = "/statuses/retweet.json";
    private final String POST_STATUS_UNRETWEET = "/statuses/unretweet.json";
    private final String POST_FAVORITES_CREATE = "/favorites/create.json";
    private final String POST_FAVORITES_DESTROY = "/favorites/destroy.json";
    private final String GET_STATUSES_LOOKUP = "/statuses/lookup.json";
    private final String GET_STATUSES_RETWEETS_ME = "/statuses/retweets_of_me.json";
    private final String GET_FAVORITES_LIST = "/favorites/list.json";
    private final String GET_STATUS_MENTIONS = "/statuses/mentions_timeline.json";
    private final String GET_STATUS_SAMPLES = "/statuses/sample.json";
    private final String GET_SAVED_SEARCHES = "/saved_searches/list.json";
    private final String GET_FRIENDS_ID = "/friends/ids.json";
    private final String POST_ACCOUNT_SETTINGS = "/account/settings.json";
    private final String POST_ACCOUNT_UPDATE_PROFILE = "/account/update_profile.json";
    private final String GET_VERIFY_ACCOUNT_CREDENTIALS = "/account/verify_credentials.json";
    private final String POST_REMOVE_PROFILE_BANNER = "/account/remove_profile_banner.json";
    private final String POST_SAVED_SEARCHES_DESTROY = "/saved_searches/destroy.json";
    private final String GET_USERS_PROFILE_BANNER = "/users/profile_banner.json";
    private final String GET_SAVED_SEARCHES_LIST = "/saved_searches/list.json";
    private final String GET_SHOW_SAVED_SEARCHES_ID = "/saved_searches/list.json";
    private final String POST_SAVED_SEARCHES_CREATE = "/saved_searches/create.json";
    private final String GET_SEARCH_TWEETS = "/search/tweets.json";
    private final String GET_USERS_MUTES_ID = "/mutes/users/ids.json";
    private final String POST_MUTES_USERS_DESTROY = "/mutes/users/destroy.json";
    private final String POST_MUTES_USERS_CREATE = "/mutes/users/create.json";
    private final String POST_USERS_REPORT_SPAM = "/users/report_spam.json";
    private final String POST_BLOCK_USERS_CREATE = "/blocks/create.json";
    private final String POST_BLOCK_USERS_DESTROY = "/blocks/destroy.json";
    private final String GET_HELP_CONFIGURATION = "/help/configuration.json";
    private final String GET_HELP_LANGUAGES = "/help/languages.json";
    private final String GET_APPLICATION_RATE_LIMIT_STATUS = "/application/rate_limit_status.json";
    private final String GET_ACCOUNTS_DRAFT_TWEETS = "/accounts/account_id/draft_tweets";
    private final String GET_USERS_SEARCH = "/users/search.json";
    private final String GET_ACCOUNT_SCHEDULED_TWEETS = "/accounts/account_id/scheduled_tweets";
    private final String GET_TRENDS_AVAILABLE = "/trends/available.json";
    private final String GET_ACCOUNTS_TAX_SETTINGS = "/accounts/:account_id/tax_settings";
    private final String GET_TRENDS_PLACES = "/trends/place.json";
    private final String GET_GEO_SEARCH = "/geo/search.json";
    private final String GET_ACCOUNTS_ID_APP_LISTS = "/accounts/account_id/app_lists";
    private final String POST_ACCOUNT_UPDATE_PROFILE_IMAGE = "/account/update_profile_image.json";
    private final String GET_BLOCKS_LIST = "/blocks/list.json";
    private final String GET_BLOCKS_ID = "/blocks/ids.json";
    private final String POST_COLLECTIONS_CREATE = "/collections/create.json";
    private final String GET_COLLECTIONS_LIST = "/collections/list.json";
    private final String POST_FRIENDSHIPS_CREATE = "/friendships/create.json";
    private final String POST_FRIENDSHIPS_UPDATE = "/friendships/update.json";
    private final String POST_FRIENDSHIPS_DESTROY = "/friendships/destroy.json";
    private final String GET_FRIENDSHIPS_NO_RETWEETS_IDS = "/friendships/no_retweets/ids.json";
    private final String GET_FRIENDSHIPS_LOOKUP = "/friendships/lookup.json";
    private final String GET_USERS_SHOW = "/users/show.json";
    private final String POST_MEDIA_UPLOAD = "//media/upload.json";


///////////////////////////////////////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

    // Get Account Settings
    public ValidatableResponse getAccountSettings() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_ACCOUNT_SETTINGS_ENDPOINT).then().statusCode(200);

    }

    // Get Tweet text using tweetId
    public ValidatableResponse getSearchTweets(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.GET_STATUS_SHOW_ENDPOINT).then().statusCode(200);

    }

    // Get User Timeline
    public ValidatableResponse getUserTimeLine() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_TWEET_TIMELINE).then().statusCode(200);

    }

    // Get User Account Settings
    public ValidatableResponse getUserAccountSettings() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_ACCOUNT_SETTINGS).then().statusCode(200);

    }

    public ValidatableResponse postRetweets(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.GET_USER_STATUSES_RETWEET).then();
    }

    public ValidatableResponse getRetweets(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.GET_STATUS_SHOW_ENDPOINT).then().statusCode(200);
    }

    public ValidatableResponse postUnRetweets(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.POST_STATUS_UNRETWEET).then();
    }

    public ValidatableResponse postFavoritesCreate(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.POST_FAVORITES_CREATE).then();
    }

    public ValidatableResponse postFavoritesDelete(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.POST_FAVORITES_DESTROY).then();

    }//Get Statuses Lookup

    public ValidatableResponse getStatusesLookup(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.GET_STATUSES_LOOKUP).then().statusCode(200);

    }//Get status retweets of me

    public ValidatableResponse getStatusReTweetOfMe() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_STATUSES_RETWEETS_ME).then().statusCode(200);


    }//Get favorites list

    public ValidatableResponse getFavoritesList() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_FAVORITES_LIST).then().statusCode(200);
    }

    //Get status mentions <- needs work
    public ValidatableResponse getStatusMentions(String username) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_STATUS_MENTIONS).then().statusCode(200);
    }

    //Get status samples
    public ValidatableResponse getStatusSamples() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_STATUS_SAMPLES).then();

    }//Get Saved Searches <- needs work

    public ValidatableResponse getSavedSearches() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_SAVED_SEARCHES).then();
    }

    public ValidatableResponse getFriendsID() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_FRIENDS_ID).then();

    }

    //Post Account Settings
    public ValidatableResponse postAccountSettings() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().post(this.baseUrl + this.POST_ACCOUNT_SETTINGS).then();

    }//Post Update Profile

    public ValidatableResponse postUpdateAccountProfile() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().post(this.baseUrl + this.POST_ACCOUNT_UPDATE_PROFILE).then();

    }//verify account credentials

    public ValidatableResponse getVerifyAccountCredentials() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_VERIFY_ACCOUNT_CREDENTIALS).then();

    }//remove profile banner

    public ValidatableResponse postRemoveProfileBanner() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().post(this.baseUrl + this.POST_REMOVE_PROFILE_BANNER).then();

    }//Get profile banner

    public ValidatableResponse getProfileBanner() { //<- needs work
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USERS_PROFILE_BANNER).then();

    }

    //get saved searches list
    public ValidatableResponse getSavedSearchesList() { //<- needs work
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_SAVED_SEARCHES_LIST).then();

    }//get saved searches by ID

    public ValidatableResponse getSavedSearchesByID(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.GET_SHOW_SAVED_SEARCHES_ID).then().statusCode(200);

    }

    public ValidatableResponse postSavedSearchesDestroy(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.POST_SAVED_SEARCHES_DESTROY).then();

    }

    public ValidatableResponse postSavedSearchesCreate(String query) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("query", query)
                .when().post(this.baseUrl + this.POST_SAVED_SEARCHES_CREATE).then();
    }//get status

    public ValidatableResponse getSearchTweets(String q) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("q", q)
                .when().get(this.baseUrl + this.GET_SEARCH_TWEETS).then().statusCode(200);
    }

    public ValidatableResponse getUserMutesByID(String id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("stringify_ids", id)
                .when().get(this.baseUrl + this.GET_USERS_MUTES_ID).then();
    }

    public ValidatableResponse postMuteUsersDestroy(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.POST_MUTES_USERS_DESTROY).then().statusCode(200);
    }

    public ValidatableResponse postMuteUsersCreate(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.POST_MUTES_USERS_CREATE).then().statusCode(200);

    }

    public ValidatableResponse postUsersReportSpam(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.POST_USERS_REPORT_SPAM).then().statusCode(200);

    }

    //Create block
    public ValidatableResponse postBlockUsersCreate(String name) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("screen_name", name)
                .when().post(this.baseUrl + this.POST_BLOCK_USERS_CREATE).then();
    }

    //destroy block - unblock
    public ValidatableResponse postBlockUsersDestroy(String name) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("screen_name", name)
                .when().post(this.baseUrl + this.POST_BLOCK_USERS_DESTROY).then();
    }

    public ValidatableResponse getConfigurationHelp() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_HELP_CONFIGURATION).then().statusCode(200);
    }

    public ValidatableResponse getLanguagesHelp() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_HELP_LANGUAGES).then().statusCode(200);
    }//get application rate limit status

    public ValidatableResponse getApplicationRateLimitStatus() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_APPLICATION_RATE_LIMIT_STATUS).then().statusCode(200);
    }

    public ValidatableResponse getAccountsDraftTweets(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.GET_ACCOUNTS_DRAFT_TWEETS).then();

    }//get search

    public ValidatableResponse getUsersSearch(String q) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("q", q)
                .when().get(this.baseUrl + this.GET_USERS_SEARCH).then().statusCode(200);

    }//get scheduled tweets

    public ValidatableResponse getScheduledTweets(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("account_id", tweetId)
                .when().get(this.baseUrl + this.GET_ACCOUNT_SCHEDULED_TWEETS).then();

    }//get trends available

    public ValidatableResponse getTrendsAvailable() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_TRENDS_AVAILABLE).then().statusCode(200);

    }

    public ValidatableResponse getAccountsTaxSettings(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("account_id", tweetId)
                .when().get(this.baseUrl + this.GET_ACCOUNTS_TAX_SETTINGS).then();

    }//get trends in a location

    public ValidatableResponse getTrendsInLocation(int tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.GET_TRENDS_PLACES).then().statusCode(200);
    }//get geo searches

    public ValidatableResponse getGeoSearch(String query) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("query", query)
                .when().get(this.baseUrl + this.GET_GEO_SEARCH).then();

    }//get account app list

    public ValidatableResponse getAccountsAppList(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("app_list_ids", tweetId)
                .when().get(this.baseUrl + this.GET_ACCOUNTS_ID_APP_LISTS).then();
    }

    public ValidatableResponse postAccountProfilePicUpdate(String img) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("image", img)
                .when().post(this.baseUrl + this.POST_ACCOUNT_UPDATE_PROFILE_IMAGE).then();

    }//get blocks list

    public ValidatableResponse getBlocksList() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_BLOCKS_LIST).then();
    }

    //get blocks ID
    public ValidatableResponse getBlocksID() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_BLOCKS_ID).then();

    }//create collections

    public ValidatableResponse getCollectionsList(long id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("user_id", id)
                .when().get(this.baseUrl + this.GET_COLLECTIONS_LIST).then();

    }//post friendship create

    public ValidatableResponse postFriendshipCreate(String id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("screen_name", id)
                .when().post(this.baseUrl + this.POST_FRIENDSHIPS_CREATE).then();
    }

    //post friendship update
    public ValidatableResponse postFriendshipUpdate(String id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("screen_name", id)
                .when().post(this.baseUrl + this.POST_FRIENDSHIPS_UPDATE).then();
    }//post friendship destroy

    public ValidatableResponse postFriendshipDestroy(String id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("screen_name", id)
                .when().post(this.baseUrl + this.POST_FRIENDSHIPS_DESTROY).then();
    }

    public ValidatableResponse getFriendshipsNoRetweets() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_FRIENDSHIPS_NO_RETWEETS_IDS).then();
    }

    //get friendships lookup
    public ValidatableResponse getFriendshipsLookUp(String id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("screen_name", id)
                .when().get(this.baseUrl + this.GET_FRIENDSHIPS_LOOKUP).then();
    }

    public ValidatableResponse getUsersShow(String id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("screen_name", id)
                .when().get(this.baseUrl + this.GET_USERS_SHOW).then();

    }//post create upload image

    public ValidatableResponse postCreateImageUpload(String img) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("media", img)
                .when().post(this.uploadImageBase + this.POST_MEDIA_UPLOAD)
                .then();

    }
}

