Java Maven Project /w BDD cuccumber framework for the assignment as

With the API, implement your test scripts accomplishing the following:
Script 1:
    Query the URL by the city name
        Verify `name` attribute corresponds to the entered city name
Script 2:
    Query the URL by latitude and longitude
        Verify `coord.lat` and `coord.lon` attributes correspond to the values entered
Script 3:
    Query the URL by ZIP code
        Verify `name` attribute corresponds to the city that contains the ZIP code (e.g. 10007 should correspond to NYC, 94040 should correspond to Mountain View, CA, etc.)

This is completed in a single feature file "getWeather.feature"

Steps to build the project:
    1. Download via GIT or using the attached .zip
    2. Run 'mvn clean install'
    3. Run 'runnerAPI' from ~/src/test/java/runners

Verify the report at:
    ~/target/cucumber-report/index.html