## Separate environment from tests
In this a very basic example we are using Maven and Surefilre plugin to run system tests
The different environment varaibles are set in the resources folder of the tests, each env has its own folder.
The environment name/folder name is apssed to the maven as a system property
Example (cmd): 

    mvn test -Denvironment=production

Spring example will follow


