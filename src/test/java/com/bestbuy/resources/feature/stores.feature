Feature: Testing different requests on the best buy stores application

  @Smoke

  Scenario: check if the best buy stores application can be accessed by users
    When User sends a GET request to the stores endpoint , they must get back a valid status code 200

  Scenario Outline: Create a new stores & verify if the stores is added

    When I create a new stores by providing the information name type  address addresstwo  city state zip lat "<lat>" lng "<lng>" hours "<hours>"
    Then I verify the stores is created

    Examples:
      | lat       | lng        | hours                                                                  |
      | 45.958785 | -90.445596 | Mon: 9-6; Tue: 9-6; Wed: 9-6; Thurs: 9-6; Fri: 9-6; Sat: 9-6; Sun: 9-6 |

  Scenario: Getting stores information by Id
     When I send GET request to stores endpoint with Id "Id" , I should received the store information

  Scenario: Update a created store & verify if the store is updated
    When  I update a created store providing the new name type and hours
    Then I verify the store is updated

  Scenario: Delete a created store & verify the store is deleted
    When I delete a created store ,they must get back a valid status code is 200
   Then I verify the store is deleted
