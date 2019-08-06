Feature: Invite vendor using form

  Background: User login the marketcube platform to invite vendor
    Given User is on marketcube login page
    When User login with a valid credentials
    And User reaches to add vendor page


    #Positive Scenario
  Scenario:  Invite vendor using form and register using the invitation
    When User invites vendor to register the form
    And user logout marketcube profile
    And Vendor open his mailbox and reaches to the invitation mail
    And Vendor creates password and clicks on the register button
    And Vendor login the marketcube platform
    Then Vendor is on the dashboard


    # Negative Scenario for already registered Vendor
  Scenario: User invites already registered vendor
    When User selects "Invite vendors to self register using our form" option
    And User enters vendor email clicks Submit
    Then User finds out that vendor is already registered



