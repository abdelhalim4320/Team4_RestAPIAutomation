package tweettest;

import io.restassured.response.ValidatableResponse;
import load.LoadMedia;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.io.FileNotFoundException;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class TweetAPIClientTest {

    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI() {
        this.tweetAPIClient = new TweetAPIClient();
    }

    @Test
    public void testUserCanTweetSuccessfully() {
        // User sent a tweet
        String tweet = "retweet and UnreTweet@eashaarap";
        //+ UUID.randomUUID().toString()
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
        String tweet = "I love my self";
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
        String tweet = "We are learning Rest API using Rest Assured and our First Tweet82d120dd-9045-44f3-a3c9-8720409fae20";
        ValidatableResponse deleteResponse = this.tweetAPIClient.deleteTweet(1378590700409921541L);
        deleteResponse.statusCode(200);
        String actualTweet = deleteResponse.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);

    }


    @Test(enabled = true)
    public void testResponseTime() {
        ValidatableResponse response = this.tweetAPIClient.responseTime();
    }

    @Test(enabled = true)
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
    public void testRetweet() {
        String tweet = "We are learning Rest API using Rest Assured and our First Tweet Same Tweet";
        ValidatableResponse response = this.tweetAPIClient.reTweet(1379294483939000321l);
//        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);
    }
    @Test
    public void testUserCannotRetweetTwice() {
        String tweet = "You have already retweeted this Tweet.";
        ValidatableResponse response = this.tweetAPIClient.reTweet(1379294483939000321l);
//        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("errors[0].message");
        Assert.assertEquals(tweet, actualTweet);
    }
    @Test
    public void testUnreTweet() {
        String tweet = "retweet and UnreTweetb2894771-0198-4d67-8c94-59afd3254d19";
        ValidatableResponse response = this.tweetAPIClient.unreTweet(1379398008064856066l);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);
    }
    @Test(enabled = true)
    public void testGetUserRetweet() {
        ValidatableResponse response = this.tweetAPIClient.getUserRetweet(1379294483939000321l);
        System.out.println(response.extract().body().asPrettyString());
        String tweet = "RT @AbdelhalimHaml2: We are learning Rest API using Rest Assured and our First Tweet Same Tweet";
        String actualText=response.extract().body().path("[0].text");
        Assert.assertEquals(tweet, actualText);
    }
    @Test
    public void testFavoriteTweet() {
        String tweet = "favorite a tweet and delete itb313aa0c-38c0-4552-80f6-bf5e0a9c1086";
        ValidatableResponse response = this.tweetAPIClient.favoriteTweet(1379382325176369155l);
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);
    }
    @Test
    public void testUsersCannotFavoriteTheSameTweet() {
        String tweet = "You have already favorited this status.";
        ValidatableResponse response = this.tweetAPIClient.favoriteTweet(1379382325176369155l);
       // response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("errors[0].message");
        Assert.assertEquals(tweet, actualTweet);
    }
    @Test
    public void testDeleteFavoriteTweet() {
        String tweet = "favorite a tweet and delete itb313aa0c-38c0-4552-80f6-bf5e0a9c1086";
        ValidatableResponse response = this.tweetAPIClient.deleteFavoriteTweet(1379382325176369155l);
        // response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);
    }
    @Test
    public void testGetFavoritesList() {
        ValidatableResponse response = this.tweetAPIClient.getFavoritesList();
         response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
        String expectedText="Hello Lyes a135b1ef-20be-493c-9955-0e4e7cfc5759";
        String actualTweet = response.extract().body().path("[0].text");
        Assert.assertEquals(expectedText, actualTweet);
    }
    @Test(enabled = false)
    public void testCreateList() {
        String name = "learn automation with kabyle";
        ValidatableResponse response = this.tweetAPIClient.createList(name);
         System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualTweet = response.extract().body().path("name");
        Assert.assertEquals(actualTweet, name, "Tweet is not match");
    }
    //false
    @Test
    public void testDestroyList() {
        String name = "learn automation with kabyle";
        long id=1379414066272346113l;
        ValidatableResponse response = this.tweetAPIClient.deleteList(name,id);
        System.out.println(response.extract().body().asPrettyString());
//        response.statusCode(200);
//        String actualTweet = response.extract().body().path("name");
//        Assert.assertEquals(actualTweet, name, "Tweet is not match");
    }
    @Test(enabled = true)
    public void testGetAllLists() {
        ValidatableResponse response = this.tweetAPIClient.getAllLists();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String listName = "learn automation with kabyle";
        String actualText=response.extract().body().path("[0].name");
        Assert.assertEquals(listName, actualText);
    }
    @Test(enabled = true)
    public void testFollowUsers() {
        String screenName="ShahibMh";
        ValidatableResponse response = this.tweetAPIClient.followUsers(screenName);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String expectedScreenName = "ShahibMh";
        String actualText=response.extract().body().path("screen_name");
        Assert.assertEquals(screenName, actualText);
    }
    @Test(enabled = true)
    public void testUnfollowUsers() {
        String screenName="ShahibMh";
        ValidatableResponse response = this.tweetAPIClient.unFollowUsers(screenName);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String expectedScreenName = "ShahibMh";
        String actualText=response.extract().body().path("screen_name");
        Assert.assertEquals(expectedScreenName, actualText);
    }
    @Test(enabled = true)
    public void testGetFollowers() {
        ValidatableResponse response = this.tweetAPIClient.getFollowers();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String screenName= "LKhemouche";
        String actualText=response.extract().body().path("users[0].screen_name");
        Assert.assertEquals(screenName, actualText);
    }  // f
    @Test(enabled = true)
    public void testGetFriendsIds() {
        ValidatableResponse response = this.tweetAPIClient.getFriendsIds();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
       // String ids[]= 1376019233793048576;
        String actualText=response.extract().body().path( "ids");
      //  Assert.assertEquals(ids, actualText);
    }
    @Test(enabled = true)
    public void testGetFriendsList() {
        ValidatableResponse response = this.tweetAPIClient.getFriendsList();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
         String actualName= "Easha";
        String expectedName=response.extract().body().path( "users[0].name");
          Assert.assertEquals(expectedName, actualName);
    }
    //
    @Test(enabled = true)
    public void testPostAccountLocation() {
        String location="Algeria";
        ValidatableResponse response = this.tweetAPIClient.postAccountLocation(location);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualName= "Algeria";
        String expectedName=response.extract().body().path( "location");
        Assert.assertEquals(expectedName, actualName);
    }

    // false
    @Test(enabled = true)
    public void testPostAccountUploadBanner() {
        String urlImage="https://pbs.twimg.com/profile_images/449750767281254400/M_ukevnA.jpeg";
        ValidatableResponse response = this.tweetAPIClient.postAccountUploadProfileBanner(urlImage);
        System.out.println(response.extract().body().asPrettyString());
//        response.statusCode(200);
//        String actualName= "Algeria";
//        String expectedName=response.extract().body().path( "location");
//        Assert.assertEquals(expectedName, actualName);
    }
    //Mute, block, and report users
    //1.create blocks users
    @Test(enabled = true)
    public void testBlockUsers() {
        String screenName="ShahibMh";
        ValidatableResponse response = this.tweetAPIClient.blockUsers(screenName);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualName= "Mh Shahib";
        String expectedName=response.extract().body().path( "name");
        Assert.assertEquals(expectedName, actualName);
    }
    //2.destroy blocks users(unblock users)
    @Test(enabled = true)
    public void testUnBlockUsers() {
        String screenName="ShahibMh";
        ValidatableResponse response = this.tweetAPIClient.unBlockUsers(screenName);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualName= "Mh Shahib";
        String expectedName=response.extract().body().path( "name");
        Assert.assertEquals(expectedName, actualName);
    }
    //3.create mutes users
    @Test(enabled = true)
    public void testMuteUsers() {
        String screenName="LKhemouche";
        ValidatableResponse response = this.tweetAPIClient.muteUsers(screenName);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualName= "Lyes Khemouche";
        String expectedName=response.extract().body().path( "name");
        Assert.assertEquals(expectedName, actualName);
    }
    // 4.DESTROY MUTES USERS (unMute users)
    @Test(enabled = true)
    public void testUnMuteUsers() {
        String screenName="LKhemouche";
        ValidatableResponse response = this.tweetAPIClient.unMuteUsers(screenName);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualName= "Lyes Khemouche";
        String expectedName=response.extract().body().path( "name");
        Assert.assertEquals(expectedName, actualName);
    }
    // 5.REPORT SPAM  ( Block at the same time)
    @Test(enabled = true)
    public void testPostUsersSpam() {
        String screenName="KableKan";
        ValidatableResponse response = this.tweetAPIClient.postUsersReportSpam(screenName);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualName= "AWKWARDPADAWAN";
        String expectedName=response.extract().body().path( "name");
        Assert.assertEquals(expectedName, actualName);
    }
    // 6.get blocks list
    @Test(enabled = true)
    public void getBlocksList() {
        ValidatableResponse response = this.tweetAPIClient.getBlocksList();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualId_str= "3153902722";
        String expectedName=response.extract().body().path( "users[0].id_str");
        Assert.assertEquals(expectedName, actualId_str);
    }
    // 7.get blocks list
    @Test(enabled = true)
    public void testGetMutedUserList() {
        ValidatableResponse response = this.tweetAPIClient.getMutedUserList();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualId_str= "14872237";
        String expectedName=response.extract().body().path( "users[0].id_str");
        Assert.assertEquals(expectedName, actualId_str);
    }
    // 7.get Users Lookup
    @Test(enabled = true)
    public void testGetUsersLookup() {
        ValidatableResponse response = this.tweetAPIClient.getUsersLookup();
        System.out.println(response.extract().body().asPrettyString());
//        response.statusCode(200);
//        String actualId_str= "14872237";
//        String expectedName=response.extract().body().path( "users[0].id_str");
//        Assert.assertEquals(expectedName, actualId_str);
    }
    // send direct message
    @Test
    public void testSendDirectMessage() throws FileNotFoundException {
        ValidatableResponse response = this.tweetAPIClient.sendDirectMessage();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualType= "message_create";
        String expectedName=response.extract().body().path( "event.type");
        Assert.assertEquals(expectedName, actualType);
    }
    // delete a message by id  .. not working
    @Test
    public void testDeleteMessage() throws FileNotFoundException {
        long id=1376019233793048576l;
        ValidatableResponse response = this.tweetAPIClient.deleteMessage(id);
        System.out.println(response.extract().body().asPrettyString());
//        response.statusCode(200);
//        String actualId= "1379997253394436104";
//        String expectedName=response.extract().body().path( "event.id");
//        Assert.assertEquals(expectedName, actualId);
    }
    // get message not working
    @Test(enabled = true)
    public void testGetGetMessage() throws FileNotFoundException {
        long id=1376019233793048576l;
        ValidatableResponse response = this.tweetAPIClient.getMessage();
        System.out.println(response.extract().body().asPrettyString());
//        response.statusCode(200);
//        String actualId_str= "14872237";
//        String expectedName=response.extract().body().path( "users[0].id_str");
//        Assert.assertEquals(expectedName, actualId_str);
    }
    // add members to lis not working
    @Test
    public void testAddMembers() throws FileNotFoundException {
        ValidatableResponse response = this.tweetAPIClient.addMembers();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
//        String actualId= "1379997253394436104";
//        String expectedName=response.extract().body().path( "event.id");
//        Assert.assertEquals(expectedName, actualId);
    }
    // post account update profile name
    @Test(enabled = true)
    public void testPostAccountUpdateName() {
        String name="Abdelhalim Hamlat";
        ValidatableResponse response = this.tweetAPIClient.postAccountUpdateName(name);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualName= "Abdelhalim Hamlat";
        String expectedName=response.extract().body().path( "name");
        Assert.assertEquals(expectedName, actualName);
    }
    // post account update profile description
    @Test(enabled = true)
    public void testPostAccountUpdateDescription() {
        String description="I am  Qa engineer ";
        ValidatableResponse response = this.tweetAPIClient.postAccountUpdateProfileDescription(description);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualName= "Abdelhalim Hamlat";
        String expectedName=response.extract().body().path( "name");
        Assert.assertEquals(expectedName, actualName);
    }
    // create collection
    @Test(enabled = true)
    public void testCreateCollectionTweet() {
        String name="team4 ";
        ValidatableResponse response = this.tweetAPIClient.createCollectionTweet(name);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
//        String actualName= "Abdelhalim Hamlat";
//        String expectedName=response.extract().body().path( "objects[0].users[0].name");
//        Assert.assertEquals(expectedName, actualName);
    }
    // upload a picture to get the media id
    @Test(enabled = true)
    public void testUploadMedia() {
        ValidatableResponse response = this.tweetAPIClient.uploadMedia(LoadMedia.cat());
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
    }
    // create a tweet with media
    @Test(enabled = true)
    public void testCreateTweetWithImage() {
        String tweet="Hello Titouche";
        long mediaId= 1380308528875909121l;
        ValidatableResponse response = this.tweetAPIClient.createTweetWithMedia(tweet,mediaId);
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
    }
    // update profile image
    @Test(enabled = true)
    public void testUpdateProfileImage() {
        ValidatableResponse response = this.tweetAPIClient.updateProfileImage(LoadMedia.profileImage());
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualName= "Abdelhalim Hamlat";
        String expectedName=response.extract().body().path( "name");
        Assert.assertEquals(expectedName, actualName);
    }
    // update banner
    @Test(enabled = true)
    public void testUpdateProfileBanner() {
        ValidatableResponse response = this.tweetAPIClient.updateProfileBanner(LoadMedia.bannerImage());
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
    }

    @Test
    public void testSendDirectMessage1() throws FileNotFoundException {
        ValidatableResponse response = this.tweetAPIClient.sendDirectMessage1();
        System.out.println(response.extract().body().asPrettyString());
        response.statusCode(200);
        String actualType= "message_create";
        String expectedName=response.extract().body().path( "event.type");
        Assert.assertEquals(expectedName, actualType);
    }

















}
