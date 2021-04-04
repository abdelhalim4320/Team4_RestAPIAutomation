package tweettest;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.util.UUID;

public class TweetAPIClientTest {

    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI() {
        this.tweetAPIClient = new TweetAPIClient();
    }

    @Test
    public void testUserCanTweetSuccessfully() {
        // User sent a tweet
        String tweet = "I m learning RestAPI and this is my first tweet" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);

        // Verify that the tweet is successful
        response.statusCode(200);
        // Verity tweet value
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet, "Tweet did not match");

        // Print the whole code: very handy and needed a lot
        // System.out.println(response.extract().body().asPrettyString());
        // Get id
        // Long id = response.extract().body().path("id");
        // System.out.println(id);
    }

    @Test
    public void testUserCanNotTweetTheSameTweetTwiceInARow() {
        // User sent a tweet
        String tweet = "This is the first time I tweet using Assured Rest API";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet is successful
        response.statusCode(403);
        // Verity Retweet
        System.out.println(response.extract().body().asPrettyString());
        String expectedMessage = "Status is a duplicate.";
        String actualTweet = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet, expectedMessage, "Tweet does not match");
        Assert.assertNotEquals("403", 200);
    }


    @Test
    public void testDeleteTweet() {
        String tweet = "I m learning RestAPI and this is my first tweet27d532a0-6b27-4cd7-a31b-dc9ff4bd66fd";
        ValidatableResponse deleteResponse = this.tweetAPIClient.deleteTweet(1378055885352734725l);
        deleteResponse.statusCode(200);
        String actualTweet = deleteResponse.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);
    }


    @Test(enabled = false)
    public void testResponseTime() {
        ValidatableResponse response = this.tweetAPIClient.responseTime();
    }

    @Test(enabled = false)
    public void testHeaderValue() {
        this.tweetAPIClient.headerValue();
    }

    @Test(enabled = false)
    public void testPropertyFromResponse() {
        //1. User send a tweet
        String tweet = "I am verifying property from response" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        //2. Verify that the tweet was successful
        response.statusCode(200);

        System.out.println(response.extract().body().asPrettyString().contains("id"));

        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet, "Tweet did not not match");
    }

    @Test(enabled = false)
    public void testGetAccountSettings(){
        ValidatableResponse response = this.tweetAPIClient.getAccountSettings();
        System.out.println(response.extract().body().asPrettyString());
        String expectedTwitterProfile = "LKhemouche";
        String actualTweeterProfile = response.extract().body().path("screen_name");
        Assert.assertEquals(actualTweeterProfile, expectedTwitterProfile, "Tweeter profile did not not match");
    }

    @Test(enabled = false)
    public void testPostLanguage(){
        String expectedLanguage = "en";
        ValidatableResponse response = this.tweetAPIClient.getAccountSettings();
        System.out.println(response.extract().body().asPrettyString());
        String actualLanguage = response.extract().body().path("language");
        Assert.assertEquals(actualLanguage, expectedLanguage, "Post did not not match");
    }

    @Test(enabled = false)
    public void testSearchTweets(){
        String expectedTweet = "I am verifying property from response82115ed8-5fc5-4e67-b893-8829708beed5";
        ValidatableResponse response = this.tweetAPIClient.getSearchTweets(1378098132874055681l);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, expectedTweet, "Tweet did not not match");
    }

    @Test(enabled = true)
    public void testGetName(){
        String expectedName = "Lyes Khemouche";
        ValidatableResponse response = this.tweetAPIClient.getSearchTweets(1378098132874055681l);
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("user.name");
        Assert.assertEquals(actualName, expectedName, "Name did not not match");
    }






}
