package tweettest;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.util.UUID;

import static io.restassured.RestAssured.given;

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
        String tweet = "I am learning Rest API using Rest Assured and our First Tweet" + UUID.randomUUID().toString();
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
    public void testUserCanNotTweetTheSameTweetTwiceInARow() {
        // User sent a tweet
        String tweet = "We are learning Rest API using Rest Assured and our First Tweet Same Tweet";
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
        String tweet = "I am learning Rest API using Rest Assured and our First Tweetbe9d71fe-6de8-4709-b1c2-84a677bb6aab";
        ValidatableResponse deleteResponse = this.tweetAPIClient.deleteTweet(1379296713744265216L);
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
    public void  TestGetUserTimeLineTweet() {
        ValidatableResponse response = this.tweetAPIClient.getUserTimeLineTweet();
        System.out.println(response.extract().body().asPrettyString());
     String expectedTweet = "We are learning Rest API using Rest Assured and our First Tweet Same Tweet";
      String actualTweet = response.extract().body().path("[0].text");
      Assert.assertEquals(expectedTweet, actualTweet);
    }
    @Test(enabled = true)
    public void TestCreateFavorites() {
        String tweet = "This is the first time I tweet using Assured Rest API";
        ValidatableResponse response = this.tweetAPIClient.createFavorites(1379927918281900034L);
       //response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet,tweet, "Tweet did not match");

    }
    @Test(enabled = true)
    public void TestDestroyFavorites() {
        String tweet = "This is the first time I tweet using Assured Rest API";
        ValidatableResponse response = this.tweetAPIClient.destroyFavorites(1379927918281900034L);
        //deleteResponse.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet,tweet, "Tweet did not match");

    }
    @Test(enabled = true)
    public void TestRetweet() {
        String retweet = "RT @OOunnouch: This is the first time I tweet using Assured Rest API";
        ValidatableResponse response = this.tweetAPIClient.retweet(1379927918281900034L);
        //deleteResponse.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, retweet);

    }
    @Test
    public void TestUserCannotRetweetTwice() {
        String retweet = "You have already retweeted this Tweet.";
        ValidatableResponse response = this.tweetAPIClient.retweet(1379927918281900034L);
        //deleteResponse.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet, retweet);
    }
    @Test
    public void testLookupTweets() {
        String tweet = "This is the first time I tweet using Assured Rest API";
        ValidatableResponse response = this.tweetAPIClient.lookupTweets(1379927918281900034l);
        //response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("[0].text");
        Assert.assertEquals(actualTweet, tweet, "Tweet did not match");
    }
    @Test
    public void testUserCannotDeleteMessage() {
        String message = "This application is not allowed to access or delete your direct messages.";
        ValidatableResponse response = this.tweetAPIClient.deleteMessage(1380651552638128131l);
        //response.statusCode(403);
        System.out.println(response.extract().body().asPrettyString());
        String actualMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualMessage, message, "Message did not match");
    }
    @Test
    public void testCreateList() {
        String name = "my first list created";
        ValidatableResponse response = this.tweetAPIClient.createList(name);
        //response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("name");
        Assert.assertEquals(actualName, name, "Tweet did not match");
    }
    @Test
    public void testGetLists() {
        String name = "my first list created";
        ValidatableResponse response = this.tweetAPIClient.getLists();
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("[0].name");
        Assert.assertEquals(actualName, name, "Tweet did not match");
    }
    @Test
    public void testCreateCollections() {
        String name = "my first collection";
        ValidatableResponse response = this.tweetAPIClient.createCollections(name);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void testUserCannotGetCollections() {
        ValidatableResponse response = this.tweetAPIClient.getCollections("custom-1379482328825802760");
        System.out.println(response.extract().body().asPrettyString());
        String error = "Forbidden from viewing this collection";
        String actualError = response.extract().body().path("error");
        Assert.assertEquals(actualError, error, "Tweet did not match");
    }

    @Test
    public void testDestroyCollections() {
        ValidatableResponse response = this.tweetAPIClient.destroyCollections("custom-1379482328825802760");
        System.out.println(response.extract().body().asPrettyString());
        boolean actualTweet = response.extract().body().path("destroyed");
        Assert.assertEquals(actualTweet, true, "Tweet did not match");
    }
    @Test
    public void testSearchTweet() {
        String text = "RT @OOunnouch: I am learning Rest API using Rest Assured and my First Tweet Same Tweet";
        ValidatableResponse response = this.tweetAPIClient.searchTweet("@OOunnouch");
        //response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualText = response.extract().body().path("statuses[0].text");
        Assert.assertEquals(actualText, text, "Tweet did not match");
    }
    @Test
    public void testGetListMembers() {
        String user = null;
        ValidatableResponse response = this.tweetAPIClient.getListMembers(1380651552638128131l);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualText = response.extract().body().path("[0].next_cursor");
        Assert.assertEquals(actualText, user, "Tweet did not match");
    }
    @Test
    public void testVerifyCredentials() {
        String expectedName = "Oussama Ounnouch";
        ValidatableResponse response = this.tweetAPIClient.verifyCredentials();
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("name");
        Assert.assertEquals(actualName, expectedName, "Name did not not match");
    }

    @Test
    public void testGetProfileBanner() {
        String expectedError = "Sorry, that page does not exist.";
        ValidatableResponse response = this.tweetAPIClient.getProfileBanner();
        System.out.println(response.extract().body().asPrettyString());
        String actualError = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualError, expectedError, "Error did not not match");
    }

    @Test
    public void testDeleteProfileBanner() {
        ValidatableResponse response = this.tweetAPIClient.deleteProfileBanner();
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void testCreateSearch() {
        String query = "Lyes pnt1234";
        ValidatableResponse response = this.tweetAPIClient.createSearch(query);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualQuery = response.extract().body().path("query");
        Assert.assertEquals(actualQuery, query, "Query did not match");
    }

    @Test
    public void testDeleteSearch() {
        String name = null;
        ValidatableResponse response = this.tweetAPIClient.deleteSearch(1379578368178405377l);
        System.out.println(response.extract().body().asPrettyString());
        String actualQuery = response.extract().body().path("name");
        Assert.assertEquals(actualQuery, name, "Query did not match");
    }

    @Test
    public void testCannotDeleteSearchTwice() {
        String name = "Sorry, that page does not exist.";
        ValidatableResponse response = this.tweetAPIClient.deleteSearch(1379578368178405377l);
        System.out.println(response.extract().body().asPrettyString());
        String actualQuery = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualQuery, name, "Query did not match");
    }

    @Test
    public void testGetSubscribers() {
        String expectedError = "You must specify either a list ID or a slug and owner.";
        ValidatableResponse response = this.tweetAPIClient.getSubscribers(1379494425441804297l);
        System.out.println(response.extract().body().asPrettyString());
        String actualError = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualError, expectedError, "Error did not not match");
    }

    @Test
    public void testGetFriendsList() {
        String name = "Lyes Khemouche";
        ValidatableResponse response = this.tweetAPIClient.getFriendsList();
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("users[0].name");
        Assert.assertEquals(actualName, name, "Error did not not match");
    }
    @Test
    public void testCannotGetCustomProfilesList() {
        String name = "Client is not permitted to perform this action.";
        ValidatableResponse response = this.tweetAPIClient.getCustomProfilesList();
        System.out.println(response.extract().body().asPrettyString());
        String actualQuery = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualQuery, name, "Query did not match");
    }





}
