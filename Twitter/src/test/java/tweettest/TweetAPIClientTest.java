package tweettest;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.io.FileNotFoundException;
import java.util.UUID;

public class TweetAPIClientTest {

    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI() {
        this.tweetAPIClient = new TweetAPIClient();
    }
    // hello

//    @Test
//    public void testUserCanTweetSuccessfully() {
//        // User sent a tweet tweet
//        String tweet = "I am learning  Rest API using Rest Assured and our First Tweet"+ UUID.randomUUID().toString();
//        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
//        System.out.println(response.extract().body().asPrettyString());
//         //Verify that the tweet is successful
//        response.statusCode(200);
//        // Verity tweet value
//        String actualTweet=response.extract().body().path("text");
//       // Long id= response.extract().body().path("id");
//        //System.out.println(id);
//        Assert.assertEquals(actualTweet,tweet,"Tweet is not match");
//    }
//
//    @Test
//    public void testUserCanNotTweetTheSameTweetTwiceInARow() {
//        // User sent a tweet
//        String tweet = "I am  learning Rest API using Rest Assured and our First Tweet Same Tweet";
//        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
//        // Verify that the tweet is successful
//       response.statusCode(403);
//        // Verity Retweet
//        System.out.println(response.extract().body().asPrettyString());
//        String expectedMessage="Status is a duplicate.";
//        String actualTweet=response.extract().body().path("errors[0].message");
//        Assert.assertEquals(actualTweet,expectedMessage,"Tweet is match");
//        Assert.assertNotEquals("403",200);
//    }
//
//
//    @Test
//    public void testUserCanNotTweetTheSameTweetTwiceInARow2() {
//        // User sent a tweet
//        String tweet = "I am  learning Rest API using Rest Assured and our First Tweet Same Tweet";
//        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
//        // Verify that the tweet is successful
//        response.statusCode(403);
//        // Verity Retweet
//        System.out.println(response.extract().body().asPrettyString());
//        int expectedMessage=187;
//        int actualTweet=response.extract().body().path("errors[0].code");
//        Assert.assertEquals(actualTweet,expectedMessage,"Tweet is match");
//        Assert.assertNotEquals("403",200);
//    }
//
//
//    @Test
//    public void testDeleteTweet(){
//        String tweet="I am learning  Rest API using Rest Assured and our First Tweet898fe179-e2d1-4745-9247-211ea791ff1c";
//        ValidatableResponse deleteResponse= this.tweetAPIClient.deleteTweet(1379255632776331265L);
//        deleteResponse.statusCode(200);
//        String actualTweet= deleteResponse.extract().body().path("text");
//        Assert.assertEquals(tweet,actualTweet);
//
//    }
//
//
//    @Test(enabled = false)
//    public void testResponseTime() {
//        ValidatableResponse response = this.tweetAPIClient.responseTime();
//    }
//    @Test(enabled = false)
//    public void testHeaderValue() {
//        this.tweetAPIClient.headerValue();
//    }
//
//    @Test(enabled = false)
//    public void testPropertyFromResponse() {
//        //1. User send a tweet
//        String tweet = "We are learning Rest API Automation with WeekdaysEvening_Selenium_NY_Summer2020" + UUID.randomUUID().toString();
//        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
//        //2. Verify that the tweet was successful
//        // response.statusCode(200);
//
//        //this.tweetAPIClient.checkProperty();
//        //JsonPath pathEvaluator=response.;
//        //System.out.println(response.extract().body().asPrettyString());
//        System.out.println(response.extract().body().asPrettyString().contains("id"));
//
//        //String actualTweet = response.extract().body().path("text");
//        //Assert.assertEquals(actualTweet, tweet, "Tweet is not match");
//    }

   //====Added BY ME
    @Test
    public void TESTGetTimeLine(){
        String tweet="I am  learning Rest API using Rest Assured and our First Tweet Same Tweet01fe9c69-f8e6-4ba7-b82c-d9ef9ef5b8b6";
        ValidatableResponse response= this.tweetAPIClient.getTimeLine();
        //System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualTweet= response.extract().body().path("[0].text");
        Assert.assertEquals(tweet,actualTweet);

    }
//should take the last message (tweet)
    @Test
    public void TestGetMentionsTimeLine(){
        String tweet="Hello friends82ed1779-eb77-4ba8-9dff-002d552db552";
        ValidatableResponse response= this.tweetAPIClient.getTimeLine();
        //System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualTweet= response.extract().body().path("[0].text");
        Assert.assertEquals(tweet,actualTweet);

    }


//this method is for create a tweet
    @Test
    public void testUserCanNotTweetTheSameTweetTwiceInARow() {
        String tweet = "I would like to say thank you team 4" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet, "Tweet is not match");
        response = this.tweetAPIClient.createTweet(tweet);
        response.statusCode(403);
        System.out.println(response.extract().body().asPrettyString());
        String expectedMessage = "Status is a duplicate.";
        String actualMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualMessage, expectedMessage, "Message not match");
        Assert.assertNotSame("200", 403);
    }

    //this step for delete a tweet
    @Test
    public void testDeleteTweet(){
        String tweet="I am  learning Rest API using Rest Assured and our First Tweet Same Tweet90599a80-2b96-410f-bfc4-9fdd0ae3d46a";
        ValidatableResponse deleteResponse= this.tweetAPIClient.deleteTweet(1380388747485704196L);
        deleteResponse.statusCode(200);
        String actualTweet= deleteResponse.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }


    @Test
    public void testResponseTime() {
        ValidatableResponse response = this.tweetAPIClient.responseTime();
    }

    @Test
    public void testHeaderValue() {
        this.tweetAPIClient.headerValue();
    }

    @Test
    public void testPropertyFromResponse() {
        String tweet = "Hello friends" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString().contains("id"));
    }
//..
    @Test
    public void testPostStatus() {
        this.tweetAPIClient.postStatus("post status");
    }

    @Test//this one depend of the 177 method
    public void testDeleteStatus() {
        this.tweetAPIClient.deleteStatus("1380402675527860224");
    }

    @Test//id should took from the tweet
    public void testGetStatus() {
        this.tweetAPIClient.getStatues("1380400560596193282");
    }

    @Test
    public void testStatusLookup() {
        ValidatableResponse response = this.tweetAPIClient.getStatuesLookup("1380400560596193282");
    }

    @Test
    public void testPostSRetweet() {
        this.tweetAPIClient.postStatuesRetweet("1380399722574274561");
    }

    @Test
    public void testPostUnRetweetRetweet() {
        this.tweetAPIClient.postUnRetweet("1380399722574274561");
    }

    @Test
    public void testGetRetweet() {
        this.tweetAPIClient.getStatusRetweet("1335723341894868995");
    }

    @Test
    public void testGetRetweetOfMe() {
        this.tweetAPIClient.getStatusRetweetOfMe("1335723341894868995");
    }

    @Test
    public void testCreateFavorite() {
        this.tweetAPIClient.postCreateFavorite("1380400560596193282");
    }

    @Test
    public void testFavoriteDelete() {
        this.tweetAPIClient.postDeleteFavorite("1335735408479662080");
    }

    @Test
    public void testFavoriteList() {
        this.tweetAPIClient.getFavoriteLists("1335735408479662080");
    }

    @Test
    public void testPostStatues() {
        this.tweetAPIClient.postStatuesUpdate("how are you?");
    }

    @Test
    public void testTimeLine() {
        ValidatableResponse response = this.tweetAPIClient.getStatuesHome("");
    }

    @Test
    public void testGetMentionsTimeLine() {
        ValidatableResponse response = this.tweetAPIClient.getStatuesMentions();
    }

    @Test
    public void testGetUserTimeLine() {
        ValidatableResponse response = this.tweetAPIClient.getStatuesUserTimeLine();
    }

    @Test
    public void testStatusFilter() {
        ValidatableResponse response = this.tweetAPIClient.getFilter();
    }

    @Test
    public void testStatusSimple() {
        ValidatableResponse response = this.tweetAPIClient.getStatuesSimple();
    }

    @Test
    public void testCollectionEntries() {
        ValidatableResponse response = this.tweetAPIClient.getCollectionsEntries("1380400560596193282");
    }

    @Test
    public void testCreateMessage() throws FileNotFoundException {
        ValidatableResponse response = this.tweetAPIClient.createMessage();
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void testCreateSecondMessage() throws FileNotFoundException {
        ValidatableResponse response = this.tweetAPIClient.createSecondMessage();
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void testAccountSetting() {
        ValidatableResponse response = this.tweetAPIClient.getAccountSetting();
        response.statusCode(200);
    }

    @Test
    public void testAccountVerify() {
        ValidatableResponse response = this.tweetAPIClient.getAccountVerifyCredentials();
        response.statusCode(200);
    }

    @Test
    public void testUserBanner() {
        long userId = 6253282;
        ValidatableResponse response = this.tweetAPIClient.getUsersProfileBanner(userId);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
    }

    @Test
    public void testAccountRemove() {
        ValidatableResponse response = this.tweetAPIClient.postRemoveAccount();
        response.statusCode(200);
    }

    @Test
    public void testAccountSettings() {
        ValidatableResponse response = this.tweetAPIClient.postAccountSetting();
        response.statusCode(200);
    }

    @Test
    public void testAccountUpdateProfile() {
        ValidatableResponse response = this.tweetAPIClient.postAccountUpdateProfile();
        response.statusCode(200);
    }

    @Test
    public void testSaveSearchesLists() {
        ValidatableResponse response = this.tweetAPIClient.postSavedSearchesList();
        response.statusCode(200);
    }

    @Test
    public void testSaveSearchesListsShow() {
        ValidatableResponse response = this.tweetAPIClient.postSavedSearch("I love sandwiches");
        response.statusCode(200);
    }

    @Test
    public void testSaveSearchesDelete() {
        ValidatableResponse response = this.tweetAPIClient.postSavedSearchesDelete("1335828230096510977");
        response.statusCode(200);
    }

//    @Test
//    public void testUploadProfileImage() {
//        ValidatableResponse response = this.tweetAPIClient.uploadProfileImage(BaseUpload.cuteBalloons());
//        response.statusCode(200);
//    }
//
//    @Test
//    public void testImageUpload() {
//        ValidatableResponse response = this.tweetAPIClient.uploadPostImage(UploadImage.prettyBeord());
//        response.assertThat().statusCode(200);
//        System.out.println(response.extract().body().asPrettyString());
//    }


//    @Test
//    public void testWelcomeMessage() throws FileNotFoundException {
//        ValidatableResponse response = this.tweetAPIClient.createWelcomeMessage(WelcomeMessageghania.postWelcomeMessage(), WelcomeMessageghania.flowerImage());
//        response.statusCode(200);
//        System.out.println(response.extract().body().asPrettyString());
//    }

}
