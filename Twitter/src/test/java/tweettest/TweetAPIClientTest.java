package tweettest;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.util.UUID;

import static org.testng.Assert.assertEquals;

public class TweetAPIClientTest {

    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI() {
        this.tweetAPIClient = new TweetAPIClient();
    }
    // hello

    //************ azul

    @Test(priority = 1)
    public void testUserCanTweetSuccessfully() {
        // User sent a tweet tweet
        String tweet = "4 Days until bootcamp ends" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        // Verify that the tweet is successful
        response.statusCode(200);
        // Verity tweet value
        String actualTweet = response.extract().body().path("text");
        assertEquals(actualTweet, tweet, "Tweet is not match");
    }

    @Test(priority = 2)
    public void testUserCanNotTweetTheSameTweetTwiceInARow() {
        // User sent a tweet
        String tweet = "bootcamp is a hangover I can't seem to get rid of";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet is successful
        response.statusCode(403);
        // Verity Retweet
        System.out.println(response.extract().body().asPrettyString());
        String expectedMessage = "Status is a duplicate.";
        String actualTweet = response.extract().body().path("errors[0].message");
        assertEquals(actualTweet, expectedMessage, "Tweet is match");
        Assert.assertNotEquals("403", 200);
    }


    @Test(priority = 3)
    public void testDeleteTweet() {
        String tweet = "4 Days until bootcamp ends396dd6e9-7fdf-4161-8c47-198384a761d1";
        ValidatableResponse deleteResponse = this.tweetAPIClient.deleteTweet(1379293990646902786L);
        deleteResponse.statusCode(200);
        String actualTweet = deleteResponse.extract().body().path("text");
        assertEquals(tweet, actualTweet);

    }


    @Test(priority = 4)
    public void testResponseTime() {
        String expectedResponseTime = "Wed Apr 07 18:32:15 +0000 2021";
        ValidatableResponse response = this.tweetAPIClient.responseTime();
        System.out.println(response.extract().body().asPrettyString());
        String actualResponseTime = response.extract().body().path("[0].created_at");
        assertEquals(actualResponseTime, expectedResponseTime, "Response time did not not match");
    }

    @Test(priority = 5)
    public void testHeaderValue() {
        this.tweetAPIClient.headerValue();
    }

    @Test(priority = 6)
    public void testPropertyFromResponse() {
        //1. User send a tweet
        String tweet = "We are learning Rest API Automation with WeekdaysEvening_Selenium_NY_Summer2020" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        //2. Verify that the tweet was successful
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        System.out.println(response.extract().body().asPrettyString().contains("id"));
        String actualTweet = response.extract().body().path("text");
        assertEquals(actualTweet, tweet, "Tweet is not match");
    }

    @Test(priority = 7)
    public void testGetAccountSettings() {
        ValidatableResponse response = this.tweetAPIClient.getAccountSettings();
        System.out.println(response.extract().body().asPrettyString());
        String expectedTwitterProfile = "Pritam85167928";
        String actualTweeterProfile = response.extract().body().path("screen_name");
        assertEquals(actualTweeterProfile, expectedTwitterProfile, "Tweeter profile did not not match");
    }

    @Test(priority = 8)
    public void testPostLanguage() {
        String expectedLanguage = "en";
        ValidatableResponse response = this.tweetAPIClient.getAccountSettings();
        System.out.println(response.extract().body().asPrettyString());
        String actualLanguage = response.extract().body().path("language");
        assertEquals(actualLanguage, expectedLanguage, "Post did not not match");
    }

    @Test(priority = 9)
    public void testSearchTweets() {
        String expectedTweet = "We are learning Rest API Automation with WeekdaysEvening_Selenium_NY_Summer2020b03f377e-ba39-4ad8-9ba1-7061a26a0d80";
        ValidatableResponse response = this.tweetAPIClient.getSearchTweets(1379323913566568448l);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        assertEquals(actualTweet, expectedTweet, "Tweet did not not match");
    }

    @Test(priority = 10)
    public void testGetName() {
        String expectedName = "Pritam";
        ValidatableResponse response = this.tweetAPIClient.getSearchTweets(1379323913566568448l);
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("user.name");
        assertEquals(actualName, expectedName, "Name did not not match");

    }

    // Get User Timeline
    @Test(priority = 11)
    public void testGetUserTimeLine() {
        String expectedTweet = "RT @Pritam85167928: bootcamp is a hangover I can't seem to get rid of";
        ValidatableResponse response = this.tweetAPIClient.getUserTimeLine();
        System.out.println(response.extract().body().asPrettyString());//text
        String actualTweet = response.extract().body().path("[0].text");//use for array index 0
        assertEquals(actualTweet, expectedTweet, "Tweet did not not match");

    }// Get User Account Settings

    @Test(priority = 12)
    public void testGetUserAccountSettings() {
        String expectedName = "Pritam85167928";
        ValidatableResponse response = this.tweetAPIClient.getUserAccountSettings();
        System.out.println(response.extract().body().asPrettyString());//screen_name
        String actualName = response.extract().body().path("screen_name");
        assertEquals(actualName, expectedName, "Name did not not match");
    }

    @Test(priority = 13)
    public void testPostRetweet() {
        String expectedName = "RT @Pritam85167928: bootcamp is a hangover I can't seem to get rid of";
        ValidatableResponse response = this.tweetAPIClient.postRetweets(1379294738390654977l);
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("text");
        assertEquals(actualName, expectedName, "Name did not not match");
    }

    @Test(priority = 14)
    public void testGetRetweet() {
        String expectedName = "RT @Pritam85167928: bootcamp is a hangover I can't seem to get rid of";
        ValidatableResponse response = this.tweetAPIClient.getRetweets(1379294738390654977l);
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("text");
        assertEquals(actualName, expectedName, "Name did not not match");
    }

    @Test(priority = 15)
    public void testPostUnRetweet() {
        String expectedName = "bootcamp is a hangover I can't seem to get rid of";
        ValidatableResponse response = this.tweetAPIClient.postUnRetweets(1379294738390654977l);
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("text");
        assertEquals(actualName, expectedName, "Name did not not match");
    }

    @Test(priority = 16)
    public void testPostFavoritesCreate() {
        String expectedName = "bootcamp is a hangover I can't seem to get rid of";
        ValidatableResponse response = this.tweetAPIClient.postFavoritesCreate(1379294738390654977l);
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("text");
        assertEquals(actualName, expectedName, "Name did not not match");

    }

    @Test(priority = 17)
    public void testPostFavoritesDestroy() {
        String expectedName = "bootcamp is a hangover I can't seem to get rid of";
        ValidatableResponse response = this.tweetAPIClient.postFavoritesDelete(1379294738390654977l);
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("text");
        assertEquals(actualName, expectedName, "Name did not not match");
    }

    @Test(priority = 18)
    public void testGetStatusesLookup() {
        String expectedName = "bootcamp is a hangover I can't seem to get rid of";
        ValidatableResponse response = this.tweetAPIClient.getStatusesLookup(1379864609658638345l);
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("[0].text");
        assertEquals(actualName, expectedName, "Name did not not match");

    }

    @Test(priority = 19)
    public void testGetStatusRetweetsOfMe() {
        String expectedName = "1379294738390654977";
        ValidatableResponse response = this.tweetAPIClient.getStatusReTweetOfMe();
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("[0].id_str");
        assertEquals(actualName, expectedName, "Name did not not match");
    }

    @Test(priority = 20)
    public void testGetFavoritesList() {
        String expectedName = "The only way we’ll be able to beat this pandemic is if enough people get vaccinated. So join me, Michelle, and Amer… https://t.co/Ks6IJHvbe2";
        ValidatableResponse response = this.tweetAPIClient.getFavoritesList();
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("[0].text");
        assertEquals(actualName, expectedName, "Name did not not match");

    }

    //Get Status Samples
    @Test(priority = 21)
    public void testGetStatusSamples() {
        String expectedName = "Sorry, that page does not exist";
        ValidatableResponse response = this.tweetAPIClient.getStatusSamples();
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("errors[0].message");
        assertEquals(actualName, expectedName, "Name did not not match");

    }//Get Friends ID

    @Test(priority = 22)
    public void testPostAccountSettings() {
        String expectedName = "Pritam85167928";
        ValidatableResponse response = this.tweetAPIClient.postAccountSettings();
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("screen_name");
        assertEquals(actualName, expectedName, "Name did not not match");

    }//Post Update Profile

    @Test(priority = 23)
    public void testPostUpdateAccountProfile() {
        String expectedName = "Pritam";
        ValidatableResponse response = this.tweetAPIClient.postUpdateAccountProfile();
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("name");
        assertEquals(actualName, expectedName, "Name did not not match");

    }//Get verify account credentials

    @Test(priority = 24)
    public void testGetVerifyAccountCredentials() {
        String expectedName = "Pritam";
        ValidatableResponse response = this.tweetAPIClient.getVerifyAccountCredentials();
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("name");
        assertEquals(actualName, expectedName, "Name did not not match");
    }

    //removes banner
    @Test(priority = 25)
    public void testPostRemoveProfileBanner() {
        String expectedName = "2017-05-13 (7).png";
        ValidatableResponse response = this.tweetAPIClient.postRemoveProfileBanner();
        System.out.println(response.extract().body().asPrettyString());
        boolean actualName = response.extract().equals(expectedName);
        Assert.assertFalse(actualName);

    }//Get List of Saved Searches

    @Test(priority = 26)
    public void testGetSavedSearchesList() {
        String expectedQuery = "food";
        ValidatableResponse response = this.tweetAPIClient.getSavedSearchesList();
        System.out.println(response.extract().body().asPrettyString());
        String actualQuery = response.extract().body().path("[0].query");
        assertEquals(actualQuery, expectedQuery, "Query did not not match");

    }//Get Saved Search By using ID

    @Test(priority = 26)
    public void testGetSavedSearchesByID() {
        String expectedQuery = "food";
        ValidatableResponse response = this.tweetAPIClient.getSavedSearchesByID(1380155945733459976l);
        System.out.println(response.extract().body().asPrettyString());
        String actualQuery = response.extract().body().path("[0].name");
        assertEquals(actualQuery, expectedQuery, "Query did not not match");
    }
    @Test(priority = 27)
    public void testPostSavedSearchesCreate() {
        String expectedQuery = "food";
        ValidatableResponse response = this.tweetAPIClient.postSavedSearchesCreate("food");
        System.out.println(response.extract().body().asPrettyString());
        String actualQuery = response.extract().body().path("query");
        assertEquals(actualQuery, expectedQuery, "Query did not not match");
}

}
