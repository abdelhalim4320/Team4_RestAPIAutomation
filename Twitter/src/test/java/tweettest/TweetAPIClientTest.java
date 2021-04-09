package tweettest;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class TweetAPIClientTest {

    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI() {
        this.tweetAPIClient = new TweetAPIClient();
    }

    @Test(priority = 1)
    public void testUserCanTweetSuccessfully() {
        String tweet = "This is another tweet";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet, "Tweet did not match");
    }

    @Test(priority = 2)
    public void testUserCanTweetSuccessfullyAddingRandomUUID() {
        String tweet = "This is for Halim" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet, "Tweet did not match");
    }

    @Test(priority = 3)
    public void testUserCanNotTweetTheSameTweetTwiceInARow() {
        String tweet = "This is the first time I tweet using Assured Rest API";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        response.statusCode(403);
        System.out.println(response.extract().body().asPrettyString());
        String expectedMessage = "Status is a duplicate.";
        String actualTweet = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet, expectedMessage, "Tweet does not match");
        Assert.assertNotEquals("403", 200);
    }

    @Test(priority = 4)
    public void testDeleteTweet() {
        String tweet = "This is the first time I tweet using Assured Rest API";
        ValidatableResponse deleteResponse = this.tweetAPIClient.deleteTweet(1378050948472406019l);
        deleteResponse.statusCode(200);
        String actualTweet = deleteResponse.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);
    }

    @Test(priority = 5)
    public void testResponseTime() {
        ValidatableResponse response = this.tweetAPIClient.responseTime();
    }

    @Test(priority = 6)
    public void testHeaderValue() {
        this.tweetAPIClient.headerValue();
    }

    @Test(priority = 7)
    public void testPropertyFromResponse() {
        String tweet = "I am verifying property from response" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString().contains("id"));
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet, "Tweet did not not match");
    }

    @Test(priority = 8)
    public void testGetAccountSettings() {
        ValidatableResponse response = this.tweetAPIClient.getAccountSettings();
        System.out.println(response.extract().body().asPrettyString());
        String expectedTwitterProfile = "LKhemouche";
        String actualTweeterProfile = response.extract().body().path("screen_name");
        Assert.assertEquals(actualTweeterProfile, expectedTwitterProfile, "Tweeter profile did not not match");
    }

    @Test(priority = 9)
    public void testPostLanguage() {
        String expectedLanguage = "en";
        ValidatableResponse response = this.tweetAPIClient.getAccountSettings();
        System.out.println(response.extract().body().asPrettyString());
        String actualLanguage = response.extract().body().path("language");
        Assert.assertEquals(actualLanguage, expectedLanguage, "Post did not not match");
    }

    @Test(priority = 10)
    public void testSearchTweets() {
        String expectedTweet = "I am verifying property from response82115ed8-5fc5-4e67-b893-8829708beed5";
        ValidatableResponse response = this.tweetAPIClient.getSearchTweets(1378098132874055681l);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, expectedTweet, "Tweet did not not match");
    }

    @Test(priority = 11)
    public void testGetName() {
        String expectedName = "Lyes Khemouche";
        ValidatableResponse response = this.tweetAPIClient.getSearchTweets(1378098132874055681l);
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("user.name");
        Assert.assertEquals(actualName, expectedName, "Name did not not match");
    }

    @Test(priority = 12)
    public void testRetweet() {
        String retweet = "RT @LKhemouche: I am learning Rest API using Rest Assured and my First Tweet Same Tweet";
        ValidatableResponse response = this.tweetAPIClient.retweet(1378040227609128963l);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, retweet);
    }

    @Test(priority = 13)
    public void testUserCannotRetweetTwice() {
        String retweet = "You have already retweeted this Tweet.";
        ValidatableResponse response = this.tweetAPIClient.retweet(1378040227609128963l);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet, retweet);
    }

    @Test(priority = 14)
    public void testUpdateTweet() {
        long tweetId = 1379117354064486411l;
        String updatedTweet = "I updated my using Assured Rest API2" + tweetId;
        ValidatableResponse response = this.tweetAPIClient.updateTweet(updatedTweet);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, updatedTweet);
    }

    @Test(priority = 15)
    public void testUnRetweet() {
        String unRetweet = "This is the first time I tweet using Assured Rest API";
        ValidatableResponse response = this.tweetAPIClient.unRetweet(1379117354064486411l);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, unRetweet);
    }

    @Test(priority = 16)
    public void testGetRetweet() {
        String retweetTime = "RT @LKhemouche: This is the first time I tweet using Assured Rest API";
        ValidatableResponse response = this.tweetAPIClient.getRetweet(1379117354064486411l);
        System.out.println(response.extract().body().asPrettyString());
        String actualRetweet = response.extract().body().path("[0].text");
        Assert.assertEquals(actualRetweet, retweetTime);
    }

    @Test(priority = 17)
    public void testCreateFavoriteTweet() {
        String tweet = "This is the first time I tweet using Assured Rest API";
        ValidatableResponse response = this.tweetAPIClient.createFavorites(1379117354064486411l);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet, "Tweet did not match");
    }

    @Test(priority = 18)
    public void testUserCannotCreateFavoriteTweetTwice() {
        String tweet = "You have already favorited this status.";
        ValidatableResponse response = this.tweetAPIClient.createFavorites(1379117354064486411l);
        response.statusCode(403);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet, tweet, "Tweet did not match");
    }

    @Test(priority = 19)
    public void testDestroyFavoriteTweet() {
        String tweet = "This is the first time I tweet using Assured Rest API";
        ValidatableResponse response = this.tweetAPIClient.destroyFavorites(1379117354064486411l);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet, "Tweet did not match");
    }

    @Test(priority = 20)
    public void testLookupTweets() {
        String tweet = "This is the first time I tweet using Assured Rest API";
        ValidatableResponse response = this.tweetAPIClient.lookupTweets(1379117354064486411l);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("[0].text");
        Assert.assertEquals(actualTweet, tweet, "Tweet did not match");
    }

    @Test(priority = 21)
    public void testUserCannotDeleteMessage() {
        String message = "This application is not allowed to access or delete your direct messages.";
        ValidatableResponse response = this.tweetAPIClient.deleteMessage(1376004471281582083l);
        response.statusCode(403);
        System.out.println(response.extract().body().asPrettyString());
        String actualMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualMessage, message, "Message did not match");
    }

    @Test(priority = 22)
    public void testGetUserTimeLine() {
        String text = "I updated my using Assured Rest API11379117354064486411";
        ValidatableResponse response = this.tweetAPIClient.getUserTimeLine();
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualText = response.extract().body().path("[0].text");
        Assert.assertEquals(actualText, text, "Tweet did not match");
    }

    @Test(priority = 23)
    public void testCreateList() {
        String name = "This is my first list created";
        ValidatableResponse response = this.tweetAPIClient.createList(name);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("name");
        Assert.assertEquals(actualName, name, "Tweet did not match");
    }

    @Test(priority = 24)
    public void testGetLists() {
        String name = "This is my first list created";
        ValidatableResponse response = this.tweetAPIClient.getLists();
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("[0].name");
        Assert.assertEquals(actualName, name, "Tweet did not match");
    }

    @Test(priority = 25)
    public void testCreateCollections() {
        String name = "this is my first collection";
        ValidatableResponse response = this.tweetAPIClient.createCollections(name);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test(priority = 26)
    public void testUserCannotGetCollections() {
        ValidatableResponse response = this.tweetAPIClient.getCollections("custom-1379482328825802760");
        System.out.println(response.extract().body().asPrettyString());
        String error = "Forbidden from viewing this collection";
        String actualError = response.extract().body().path("error");
        Assert.assertEquals(actualError, error, "Tweet did not match");
    }

    @Test(priority = 27)
    public void testDestroyCollections() {
        ValidatableResponse response = this.tweetAPIClient.destroyCollections("custom-1379482328825802760");
        System.out.println(response.extract().body().asPrettyString());
        boolean actualTweet = response.extract().body().path("destroyed");
        Assert.assertEquals(actualTweet, true, "Tweet did not match");
    }

    @Test(priority = 28)
    public void testSearchTweet() {
        String text = "RT @LKhemouche: I am learning Rest API using Rest Assured and my First Tweet Same Tweet";
        ValidatableResponse response = this.tweetAPIClient.searchTweet("@LKhemouche");
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualText = response.extract().body().path("statuses[0].text");
        Assert.assertEquals(actualText, text, "Tweet did not match");
    }

    @Test(priority = 29)
    public void testGetListMembers() {
        String user = null;
        ValidatableResponse response = this.tweetAPIClient.getListMembers(1379460543191379973l);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualText = response.extract().body().path("[0].next_cursor");
        Assert.assertEquals(actualText, user, "Tweet did not match");
    }

    @Test(priority = 30)
    public void testGetUserTimeTweet() {
        String expectedText = "I updated my using Assured Rest API11379117354064486411";
        ValidatableResponse response = this.tweetAPIClient.getUserTimeTweet();
        System.out.println(response.extract().body().asPrettyString());
        String actualText = response.extract().body().path("[0].text");
        Assert.assertEquals(actualText, expectedText, "Text did not not match");
    }

    @Test(priority = 31)
    public void testVerifyCredentials() {
        String expectedName = "Lyes Khemouche";
        ValidatableResponse response = this.tweetAPIClient.verifyCredentials();
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("name");
        Assert.assertEquals(actualName, expectedName, "Name did not not match");
    }

    @Test(priority = 32)
    public void testGetProfileBanner() {
        String expectedError = "Sorry, that page does not exist.";
        ValidatableResponse response = this.tweetAPIClient.getProfileBanner();
        System.out.println(response.extract().body().asPrettyString());
        String actualError = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualError, expectedError, "Error did not not match");
    }

    @Test(priority = 33)
    public void testDeleteProfileBanner() {
        ValidatableResponse response = this.tweetAPIClient.deleteProfileBanner();
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test(priority = 34)
    public void testCreateSearch() {
        String query = "Abdelhalim pnt1234";
        ValidatableResponse response = this.tweetAPIClient.createSearch(query);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualQuery = response.extract().body().path("query");
        Assert.assertEquals(actualQuery, query, "Query did not match");
    }

    @Test(priority = 35)
    public void testDeleteSearch() {
        String name = null;
        ValidatableResponse response = this.tweetAPIClient.deleteSearch(1379578368178405377l);
        System.out.println(response.extract().body().asPrettyString());
        String actualQuery = response.extract().body().path("name");
        Assert.assertEquals(actualQuery, name, "Query did not match");
    }

    @Test(priority = 36)
    public void testCannotDeleteSearchTwice() {
        String name = "Sorry, that page does not exist.";
        ValidatableResponse response = this.tweetAPIClient.deleteSearch(1379578368178405377l);
        System.out.println(response.extract().body().asPrettyString());
        String actualQuery = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualQuery, name, "Query did not match");
    }

    @Test(priority = 37)
    public void testGetSubscribers() {
        String expectedError = "You must specify either a list ID or a slug and owner.";
        ValidatableResponse response = this.tweetAPIClient.getSubscribers(1379494425441804297l);
        System.out.println(response.extract().body().asPrettyString());
        String actualError = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualError, expectedError, "Error did not not match");
    }

    @Test(priority = 38)
    public void testGetFriendsList() {
        String name = "Easha";
        ValidatableResponse response = this.tweetAPIClient.getFriendsList();
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("users[0].name");
        Assert.assertEquals(actualName, name, "Error did not not match");
    }

    @Test(priority = 39)
    public void testDeleteMessage() {
        String name = "This application is not allowed to access or delete your direct messages.";
        ValidatableResponse response = this.tweetAPIClient.deleteWelcomeMessage(1376004471281582083l);
        System.out.println(response.extract().body().asPrettyString());
        String actualQuery = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualQuery, name, "Query did not match");
    }

    @Test(priority = 40)
    public void testGetCustomProfiles() {
        String name = "Sorry, that page does not exist";
        ValidatableResponse response = this.tweetAPIClient.getCustomProfiles(1379822648633520129l);
        System.out.println(response.extract().body().asPrettyString());
        String actualQuery = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualQuery, name, "Error did not match");
    }

    @Test(priority = 41)
    public void testCannotGetCustomProfilesList() {
        String name = "Client is not permitted to perform this action.";
        ValidatableResponse response = this.tweetAPIClient.getCustomProfilesList();
        System.out.println(response.extract().body().asPrettyString());
        String actualQuery = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualQuery, name, "Query did not match");
    }

    @Test(priority = 42)
    public void testCannotDeleteCustomProfiles() {
        String error = "Client is not permitted to perform this action.";
        ValidatableResponse response = this.tweetAPIClient.deleteCustomProfiles(1379822648633520129l);
        System.out.println(response.extract().body().asPrettyString());
        String actualQuery = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualQuery, error, "Error did not match");
    }

    @Test(priority = 43)
    public void testCreateFavoriteGif() {
        String tweet = "https://t.co/OPPnbu0223";
        ValidatableResponse response = this.tweetAPIClient.createFavorites(1379822648633520129l);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet, "Gif did not match");
    }

    @Test(priority = 44)
    public void testUserCanBlockByScreenName() {
        String screenName = "RealShkreli";
        ValidatableResponse response = this.tweetAPIClient.blockByScreenName(screenName);
        System.out.println(response.extract().body().asPrettyString());
        String actualScreenName = response.extract().body().path("screen_name");
        Assert.assertEquals(actualScreenName, screenName, "Screen name did not match");
    }

    @Test(priority = 45)
    public void testUserCanBlockById() {
        String screenName = "TebbouneAmadjid";
        ValidatableResponse response = this.tweetAPIClient.blockById(1192946702891790336l);
        System.out.println(response.extract().body().asPrettyString());
        String actualScreenName = response.extract().body().path("screen_name");
        Assert.assertEquals(actualScreenName, screenName, "Screen name did not match");
    }

    @Test(priority = 46)
    public void testGetBlockList() {
        List<String> blockedPeople = Arrays.asList("TebbouneAmadjid", "RealShkreli");
        ValidatableResponse response = this.tweetAPIClient.getBlockList(true);
        System.out.println(response.extract().body().asPrettyString());
        List<String> actualBlockedPeople = response.extract().body().jsonPath().getList("users.screen_name");
        Assert.assertEquals(actualBlockedPeople, blockedPeople, "Screen name did not match");
    }
}
