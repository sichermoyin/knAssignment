# knAssignment

Two testcases were implemented in the class job.assignment.knab.test.AssignmentTest. They are:
1. createBoardApi - tests the creation of a board through API
2. createBoardUI - Tests the creation of a board through the UI.
   a) Screenshots taken during test execution  are located in folder screenshots.
   b) Test report is located at target/site/surefire-report.html
   
The maven project can  be imported into a suitable IDE. Alternatively, it can also be executed 
directly using maven from the commandline. The command is:

mvn clean install site

Test execution from an ide IDE such as eclipse should use the command without mvn - 
clean install site


Possible improvements
1. The addition of a commandline parameter to switch off/on taking of screenshots.
2. Graphical documentation to illustrate test execution.
