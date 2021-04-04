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
        String tweet = "We are learning Rest API using Rest Assured and our First Tweet"+ UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
       // System.out.println(response.extract().body().asPrettyString());
        // Verify that the tweet is successful
        response.statusCode(200);
        // Verity tweet value
        String actualTweet=response.extract().body().path("text");
       // Long id= response.extract().body().path("id");
        //System.out.println(id);
        Assert.assertEquals(actualTweet,tweet,"Tweet is not match");
    }

    @Test
    public void testUserCanNotTweetTheSameTweetTwiceInARow() {
        // User sent a tweet
        String tweet = "We are learning Rest API using Rest Assured and our First Tweet Same Tweet";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet is successful
       response.statusCode(403);
        // Verity Retweet
        System.out.println(response.extract().body().asPrettyString());
        String expectedMessage="Status is a duplicate.";
        String actualTweet=response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet,expectedMessage,"Tweet is match");
        Assert.assertNotEquals("403",200);
    }


    @Test
    public void testDeleteTweet(){
        String tweet="We are learning Rest API using Rest Assured and our First Tweet82d120dd-9045-44f3-a3c9-8720409fae20";
        ValidatableResponse deleteResponse= this.tweetAPIClient.deleteTweet(1378590700409921541L);
        deleteResponse.statusCode(200);
        String actualTweet= deleteResponse.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);

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



}
