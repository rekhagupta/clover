@tag
Feature: Open Weather Api

  @getWeatherByParameters
  Scenario Outline: GET weather data by "<queryParameters>"
    Given I'm using the OpenWeather API
    When I make a valid API "<Type>" request by "<queryParameters>" for "<path>"
    Then I get a valid response with Code="<statusCode>"
    And I get the "<validation>" in the response
    Examples:
      |   queryParameters |   path       |  statusCode  |   Type  |     validation      |
      |   q=sacramento    |   /weather   |    200       |   GET   | name=sacramento     |
      |   q=new york      |   /weather   |    200       |   GET   | name=new york       |
      |   q=london,uk     |   /weather   |    200       |   GET   | name=London       |
      |   lat=35;lon=139  |   /weather   |    200       |   GET   | coord.lon=139,coord.lat=35 |
      |     zip=94040     |   /weather   |    200       |   GET   | name=Mountain view  |
      |     zip=94040,us  |   /weather   |    200       |   GET   | name=Mountain view  |
      |     zip=10007     |   /weather   |    200       |   GET   | name=new york       |

  @invalidApiKey_NotImplemented
  Scenario: Call current weather data with Invalid Api Id Key
    Given I'm using the OpenWeatherMap Api