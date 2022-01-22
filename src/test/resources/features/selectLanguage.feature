Feature: Wikipidia Language Render

  Scenario: Language Rendering Verification Test

    Given User is already available on wikipedia main page
    When User get the title of the home page
    Then Title of the main page contains "Wikipedia, the free encyclopedia"
    Then User click a language present under language settings tab and verify if they are rendered
    And User Verify Language is changed to english finally