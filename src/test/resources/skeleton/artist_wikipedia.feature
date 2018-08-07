Feature: Verify Album information and behaviour for artists on wikipedia.

  Background: 
    Given The Wikipedia home page is loaded
      And User searches for 'Taylor Swift'
  
  Scenario: User verifies Studio Album Section under External links
     When User navigates to Studio Albums under External links
     Then Studio Albums section contains the following albums
      | Taylor Swift | 
      | Fearless     | 
      | Speak Now    | 
      | Red          | 
      | 1989         | 
      | Reputation   | 
  
  Scenario Outline: User verifies hover message on album link
     When User hovers over '<album>' link
     Then Hover message '<message>' appears
  
    Examples: 
      | album      | message                                                                             | 
      | Reputation | Reputation is the sixth studio album by American singer and songwriter Taylor Swift | 
