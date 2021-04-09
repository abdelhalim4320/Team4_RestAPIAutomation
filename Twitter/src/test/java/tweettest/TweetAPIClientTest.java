package tweettest;

import ImageBaseMethods.ImageRoles;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

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

    @Test(priority = 28)
    public void testGetSearchTweets() {
        String expectedText = "...some other resources if you as a dietitian or health professional or consumer want to learn more about how food… https://t.co/eoQkhkYE9J";
        ValidatableResponse response = this.tweetAPIClient.getSearchTweets("@food");
        System.out.println(response.extract().body().asPrettyString());
        String actualText = response.extract().body().path("statuses[0].text");
        assertEquals(actualText, expectedText, "Text did not not match");
    }

    @Test(priority = 29)
    public void testGetUserMutesByID() {
        ArrayList<String> expectedText = new ArrayList<>();
        ValidatableResponse response = this.tweetAPIClient.getUserMutesByID("true");
        System.out.println(response.extract().body().asPrettyString());
        ArrayList actualText = response.extract().body().path("ids[]");
        assertEquals(actualText, expectedText, "Text did not not match");

    }

    @Test(priority = 30)
    public void testPostMutesUsersCreate() {
        String expectedName = "kylegriffin1";
        ValidatableResponse response = this.tweetAPIClient.postMuteUsersCreate(32871086l);
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("screen_name");
        assertEquals(actualName, expectedName, "Query did not not match");

    }

    @Test(priority = 31)
    public void testPostMutesUsersDestroy() {
        String expectedName = "BarackObama";
        ValidatableResponse response = this.tweetAPIClient.postMuteUsersDestroy(813286l);
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("screen_name");
        assertEquals(actualName, expectedName, "Query did not not match");

    }

    @Test(priority = 32)
    public void testPostUsersReportSpam() {
        String expectedName = "kylegriffin1";
        ValidatableResponse response = this.tweetAPIClient.postUsersReportSpam(32871086l);
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("screen_name");
        assertEquals(actualName, expectedName, "Query did not not match");
    }

    @Test(priority = 33)
    public void testPostBlockUsersCreate() {
        String expectedName = "AdamMGrant";
        ValidatableResponse response = this.tweetAPIClient.postBlockUsersCreate("AdamMGrant");
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("screen_name");
        assertEquals(actualName, expectedName, "Name did not not match");
    }

    @Test(priority = 34)
    public void testPostBlockUsersDestroy() {
        String expectedName = "AdamMGrant";
        ValidatableResponse response = this.tweetAPIClient.postBlockUsersDestroy("AdamMGrant");
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("screen_name");
        assertEquals(actualName, expectedName, "Name did not not match");

    }

    @Test(priority = 35)
    public void testGetConfigurationHelp() {
        String expectedQuery = "about";
        ValidatableResponse response = this.tweetAPIClient.getConfigurationHelp();
        System.out.println(response.extract().body().asPrettyString());
        String actualQuery = response.extract().body().path("non_username_paths[0].");
        assertEquals(actualQuery, expectedQuery, "Query did not not match");
    }

    @Test(priority = 36)
    public void testGetLanguagesHelp() {
        String expectedLanguage = "Urdu";
        ValidatableResponse response = this.tweetAPIClient.getLanguagesHelp();
        System.out.println(response.extract().body().asPrettyString());
        String actualLanguage = response.extract().body().path("[0].name");
        assertEquals(actualLanguage, expectedLanguage, "Language did not not match");

    }//Get application rate limit status

    @Test(priority = 37)
    public void testGetApplicationRateLimitStatus() {
        String expectedLanguage = "1375997679377977346-TqMyeNx92vrPqAdaHQlguwHZRBTU4g";
        ValidatableResponse response = this.tweetAPIClient.getApplicationRateLimitStatus();
        System.out.println(response.extract().body().asPrettyString());
        String actualLanguage = response.extract().body().path("rate_limit_context.access_token");
        assertEquals(actualLanguage, expectedLanguage, "Language did not not match");

    }//get user accounts search

    @Test(priority = 38)
    public void testGetUserSearch() {
        String expectedText = "Dad, husband, President, citizen.";
        ValidatableResponse response = this.tweetAPIClient.getUsersSearch("Barack Obama");
        System.out.println(response.extract().body().asPrettyString());
        String actualText = response.extract().body().path("[0].description");
        assertEquals(actualText, expectedText, "Text did not not match");

    }//get trends available

    @Test(priority = 39)
    public void testGetTrendsAvailable() {
        String expectedText = "Worldwide";
        ValidatableResponse response = this.tweetAPIClient.getTrendsAvailable();
        System.out.println(response.extract().body().asPrettyString());
        String actualText = response.extract().body().path("[0].name");
        assertEquals(actualText, expectedText, "Text did not not match");
    }
    //get trends in a specific area
    @Test(priority = 40)
    public void testGetTrendsPlaces() {
        String expectedText = "#FearlessTaylorsVersion";
        ValidatableResponse response = this.tweetAPIClient.getTrendsInLocation(1);
        System.out.println(response.extract().body().asPrettyString());
        String actualText = response.extract().body().path("[0].trends[0].name");
        assertEquals(actualText, expectedText, "Text did not not match");

    }//get geo searches
    @Test(priority = 41)
    public void testGetGeoSearches() {
        String expectedText = "neighborhood";
        ValidatableResponse response = this.tweetAPIClient.getGeoSearch("food");
        System.out.println(response.extract().body().asPrettyString());
        String actualText = response.extract().body().path("query.params.granularity");
        assertEquals(actualText, expectedText, "Text did not not match");

    }//get blocks list
    @Test(priority = 42)
    public void testGetBlocksList() {
        String expectedText = "Adam H. Johnson";
        ValidatableResponse response = this.tweetAPIClient.getBlocksList();
        System.out.println(response.extract().body().asPrettyString());
        String actualText = response.extract().body().path("users[0].name");
        assertEquals(actualText, expectedText, "Text did not not match");

    }//get blocks ID
    @Test(priority = 43)
    public void testGetBlocksID() {
        ValidatableResponse response = this.tweetAPIClient.getBlocksID();
        System.out.println(response.extract().body().asPrettyString());
        int actualText = response.extract().body().path("ids[0]");
        Assert.assertFalse(Boolean.parseBoolean(String.valueOf(actualText)));

    }//get collections list
    @Test(priority = 44)
    public void testGetCollectionsList() {
        ValidatableResponse response = this.tweetAPIClient.getCollectionsList(1375997679377977346l);
        System.out.println(response.extract().body().asPrettyString());
        String actualText = response.extract().body().path("collection_url");
        Assert.assertFalse(Boolean.parseBoolean(actualText));
    }
    //post friendship create
    @Test(priority = 45)
    public void testPostFriendshipCreate() {
        String expectedName = "Former Congresswoman. Author of She Will Rise: https://t.co/3CSqe0jRHB. Founder of HER Time PAC: @hertime2020. Host of @_naked_politics podcast. Not done yet.";
        ValidatableResponse response = this.tweetAPIClient.postFriendshipCreate("@KatieHill4CA");
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("description");
        assertEquals(actualName,expectedName);
    }
    //post friendship update
    @Test(priority = 46)
    public void testPostFriendshipUpdate() {
        String expectedName = "Pritam85167928";
        ValidatableResponse response = this.tweetAPIClient.postFriendshipUpdate("@KatieHill4CA");
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("relationship.source.screen_name");
        assertEquals(actualName,expectedName);

    }//post friendship destroy
    @Test(priority = 47)
    public void testPostFriendshipDestroy() {
        String expectedName = "Washington, DC";
        ValidatableResponse response = this.tweetAPIClient.postFriendshipDestroy("@KatieHill4CA");
        System.out.println(response.extract().body().asPrettyString());
        String actualName = response.extract().body().path("location");
        assertEquals(actualName,expectedName);

    }
    @Test(priority = 48)
    public void testGetFriendshipNoRetweetsID() {
        ArrayList<Integer> x = new ArrayList<>();
        x.add(813286);
        AtomicReference<ArrayList<Integer>> expectedName = new AtomicReference<>(x);
        ValidatableResponse response = this.tweetAPIClient.getFriendshipsNoRetweets();
        System.out.println(response.extract().body().asPrettyString());
        ArrayList<Integer> actualName = response.extract().body().path("");
        assertEquals(actualName, expectedName.get());

    }//get friendship lookup
    @Test(priority = 49)
    public void testGetFriendshipLookUp() {
        String expectedText = "Barack Obama";
        ValidatableResponse response = this.tweetAPIClient.getFriendshipsLookUp("@BarackObama");
        System.out.println(response.extract().body().asPrettyString());
        String actualText = response.extract().body().path("[0].name");
        assertEquals(actualText, expectedText, "Text did not not match");
    }
    @Test(priority = 50)
    public void testGetUsersShow() {
        String expectedText = "Pritam";
        ValidatableResponse response = this.tweetAPIClient.getUsersShow("@Pritam85167928");
        System.out.println(response.extract().body().asPrettyString());
        String actualText = response.extract().body().path("name");
        assertEquals(actualText, expectedText, "Text did not not match");

    }
    @Test
    public void testUploadImage(){
        ValidatableResponse response = this.tweetAPIClient.postCreateImageUpload(ImageRoles.pleaseWork());
        System.out.println(response.extract().body().asPrettyString());
    }
}