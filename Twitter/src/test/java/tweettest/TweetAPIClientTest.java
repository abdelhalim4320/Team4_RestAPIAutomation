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
    // hello

    //************ azul

    @Test
    public void testUserCanTweetSuccessfully() {
        // User sent a tweet tweet
        String tweet = "This is my first tweet" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // System.out.println(response.extract().body().asPrettyString());
        // Verify that the tweet is successful
        response.statusCode(200);
        // Verity tweet value
        String actualTweet = response.extract().body().path("text");
        // Long id= response.extract().body().path("id");
        //System.out.println(id);
        Assert.assertEquals(actualTweet, tweet, "Tweet is not match");
    }

    @Test
    public void testUserCanNotTweetTheSameTweetTwiceInARow2() {
        // User sent a tweet
        String tweet = "second tweet";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet is successful
        response.statusCode(403);
        // Verity Retweet
        System.out.println(response.extract().body().asPrettyString());
        int expectedMessage = 187;
        int actualTweet = response.extract().body().path("errors[0].code");
        Assert.assertEquals(actualTweet, expectedMessage, "Tweet is match");
        Assert.assertNotEquals("403", 200);
    }

    @Test
    public void testUserCanNotTweetTheSameTweetTwiceInARow() {
        // User sent a tweet
        String tweet = "second tweet";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet is successful
        response.statusCode(403);
        // Verity Retweet
        System.out.println(response.extract().body().asPrettyString());
        String expectedMessage = "Status is a duplicate.";
        String actualTweet = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet, expectedMessage, "Tweet is match");
        Assert.assertNotEquals("403", 200);
    }


    @Test
    public void testDeleteTweet() {
        String tweet = "This is my first tweet961574b0-4b0d-4601-be1a-df86e9aded8d";
        ValidatableResponse deleteResponse = this.tweetAPIClient.deleteTweet(1379270224713818112L);
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
        String tweet = "We are learning Rest API Automation with WeekdaysEvening_Selenium_NY_Summer2020" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        //2. Verify that the tweet was successful
        // response.statusCode(200);

        //this.tweetAPIClient.checkProperty();
        //JsonPath pathEvaluator=response.;
        //System.out.println(response.extract().body().asPrettyString());
        System.out.println(response.extract().body().asPrettyString().contains("id"));
        //String actualTweet = response.extract().body().path("text");
        //Assert.assertEquals(actualTweet, tweet, "Tweet is not match");
    }

    @Test
    public void TESTPostFavoriteTweet() {
        // User sent a tweet tweet
        long id = 1379271364868198400l;
        ValidatableResponse response = this.tweetAPIClient.postFavorite(id);
        System.out.println(response.extract().body().asPrettyString());


//        // Verify that the tweet is successful
//        response.statusCode(200);
//        // Verity tweet value
//        String actualTweet=response.extract().body().path("text");
//        // Long id= response.extract().body().path("id");
//        //System.out.println(id);
//        Assert.assertEquals(actualTweet,tweet,"Tweet is not match");
    }

    @Test
    public void testRetweet() {
        // User sent a tweet tweet
        long id = 1379271364868198400l;
        ValidatableResponse response = this.tweetAPIClient.postRetweet(id);
        System.out.println(response.extract().body().asPrettyString());


//        // Verify that the tweet is successful
//        response.statusCode(200);
//        // Verity tweet value
//        String actualTweet=response.extract().body().path("text");
//        // Long id= response.extract().body().path("id");
//        //System.out.println(id);
//        Assert.assertEquals(actualTweet,tweet,"Tweet is not match");
    }

    @Test
    public void testunRetweet() {
        // User sent a tweet tweet
        long id = 1379271364868198400l;
        ValidatableResponse response = this.tweetAPIClient.unRetweet(id);
        System.out.println(response.extract().body().asPrettyString());


//        // Verify that the tweet is successful
//        response.statusCode(200);
//        // Verity tweet value
//        String actualTweet=response.extract().body().path("text");
//        // Long id= response.extract().body().path("id");
//        //System.out.println(id);
//        Assert.assertEquals(actualTweet,tweet,"Tweet is not match");
    }

    @Test
    public void testGetTimeLine() {
        String tweet = "second tweet";
        ValidatableResponse response = this.tweetAPIClient.getTimeLine();
//        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualTweet= response.extract().body().path("[0].text");
        Assert.assertEquals(tweet,actualTweet);

    }
}
