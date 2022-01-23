Feature: Wikipedia Celebrity Search

  Scenario: Extract DOB and Spouse Details

    Given User is already available on wikipedia main page
    When User get the title of the home page
    Then Title of the main page contains "Wikipedia, the free encyclopedia"
    And User verify simple searchbar is present
    And User search a celebrity name and extract the data
      |Tom Hanks|
      |Will Smith|
      |Jackie Chan|
      |Arnold Schwarzenegger|
      |Dwayne Johnson|
      |Paul Walker|
      |Randy Orton|
      |Emma Watson|
      |Mark Wahlberg|
      |Salma Hayek|
    Then user write extracted data in logs
