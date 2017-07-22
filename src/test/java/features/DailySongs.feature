@dailySongPost

Feature: Post and Get new daily songs

  Scenario Outline: Post new movies
    Given I have an artist "<artist>", and a song"<song>", and a publish date"<date>"
    When I make a post request to "http://turing.niallbunting.com:3004" and "/api/video/"
    Then I should have the correct status code "200"

    Examples:
      | artist     |  | song        |  | date       |
      | Lady Gaga  |  | Poker face  |  | 2017-09-01 |
      | Ed Sheeran |  | Galway Girl |  | 2013-02-01 |


  Scenario: Get a list of videos
    When I make a get request to "http://turing.niallbunting.com:3004" and "/api/video/"
    Then I should have a list of videos



  Scenario Outline: Get a video with ID
    When I make a get request to "http://turing.niallbunting.com:3004" and "/api/video/" and "<id>"
    Then I should have a matching video of artist "<artist>"

    Examples:
      | id                       |     |  artist      |
      | 596cbda86ed7c10011a68b24 |     | Lady Gaga    |
      | 596cc3b16ed7c10011a68b26 |     | Ed Sheeran   |