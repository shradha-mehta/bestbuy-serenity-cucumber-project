Feature: Testing different requests on the best buy categories application

  @Smoke

  Scenario: check if the best buy categories application can be accessed by users
    When User sends a GET request to the categories endpoint , they must get back a valid status code 200


  Scenario: Create a new categories & verify if the categories is added
    When I create a new categories by providing the information name & id
    Then I verify the categories is created

  Scenario: Getting categories information by Id
      When I send GET request to categories endpoint with Id "Id" , I should received the categories information

  Scenario: Update a created categories & verify if the categories is updated
    When  I update a created categories providing the new name
    Then I verify the categories is updated

  Scenario: Delete a created categories & verify the categories is deleted
    When I delete a created categories ,they must get back a valid status code  is 200
    Then I verify the categories is deleted


