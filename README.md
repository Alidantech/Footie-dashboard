[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-8d59dc4de5201274e310e4c54b9627a8934c3b88527886e3b421487c677d23eb.svg)](https://classroom.github.com/a/0HM85svl)
# COM1003 Assignment: A Footie Dashboard

This is the source code of the COM1003 SEM 2 Assignment (2022-23).

## Checking that your project compiles

To compile and execute the tests provided in the project, execute:

```shell
gradle build
```

or

```shell
./gradlew build
```

If you execute either of these commands as soon as you clone your repository, you should get this output:

```
Starting a Gradle Daemon (subsequent builds will be faster)

> Task :test FAILED

TestFileParsing > testParseFileLine() FAILED
    java.lang.NullPointerException at TestFileParsing.java:24

TestFileParsing > testParseFileLineTooFewColumns() FAILED
    org.opentest4j.AssertionFailedError at TestFileParsing.java:36

TestPlayerCatalog > testGetMeanAverageValue() FAILED
    org.opentest4j.AssertionFailedError at TestPlayerCatalog.java:52

TestPlayerCatalog > testUpdateCatalog() FAILED
    org.opentest4j.AssertionFailedError at TestPlayerCatalog.java:25

TestQueryParser > testReadSingleQuery() FAILED
    org.opentest4j.AssertionFailedError at TestQueryParser.java:18

TestQueryParser > testExceptionOnBadQuery() FAILED
    org.opentest4j.AssertionFailedError at TestQueryParser.java:26

TestRadarChart > testUpdateRadarChartSameData() FAILED
    java.lang.NullPointerException at TestRadarChart.java:41

TestRadarChart > testUpdateRadarChart() FAILED
    java.lang.NullPointerException at TestRadarChart.java:28

8 tests completed, 8 failed

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':test'.
> There were failing tests. See the report at: file:///Users/jmr/systems/COM1003/com1003_footie/build/reports/tests/test/index.html

* Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.

* Get more help at https://help.gradle.org

BUILD FAILED in 6s
8 actionable tasks: 8 executed
```

As you can see, the provided JUnit tests fail. By the time you finish implementing your solution, all these tests should pass and the build should be successful.

You can use the `-x test` option to ask Gradle to compile your project and skip test execution:

```shell
gradle build -x test
```

Which should output:

```
BUILD SUCCESSFUL in 625ms
6 actionable tasks: 6 executed
```

### Do not use `javac` to compile

Using `javac` directly to compile individual Java files will **not** work, e.g., executing:

```shell
javac FootieDashboard.java
```

will output:

```
error: file not found: FootieDashboard.java
Usage: javac <options> <source files>
```

and executing:

```shell
javac src/main/java/uk/ac/sheffield/com1003/assignment/FootieDashboard.java
```

will lead to a number of missing dependency errors, e.g.:

```
src/main/java/uk/ac/sheffield/com1003/assignment/FootieDashboard.java:8: error: package uk.ac.sheffield.com1003.assignment.codeprovided.gui does not exist
import uk.ac.sheffield.com1003.assignment.codeprovided.gui.AbstractPlayerDashboardPanel;

src/main/java/uk/ac/sheffield/com1003/assignment/FootieDashboard.java:24: error: cannot find symbol
        private final AbstractPlayerCatalog playerCatalog;
                      ^
  symbol:   class AbstractPlayerCatalog
  location: class FootieDashboard
```

## Submission

Your submission will consist of your repository at a link to the commit containing your solution, which you will include in the text field on Blackboard assignment submission page. The format of the link should be as follows:

```
https://github.com/tuos-dcs-COM1003/com1003_footie-<YOUR_GITHUB_USERNAME>/commit/131337e0893b6a47dd4646815cc16794077b3e34
```

### How can I find out the link to my commit for submission?

On Github.com, you can extract the url by navigating to `<YOUR_GITHUB_REPO_URL>/commits/main` and clicking on the _View commit details_ option.

Alternatively, if you are on Linux or Mac OS, the following one-liner should produce an URL for your **latest** commit:

```shell
echo "$(git config --get remote.origin.url | sed -e 's/\.git$//g')/commit/$(git rev-parse HEAD)"
```

For Windows users, you can compose the url by hand by concatenating:

```
<YOUR_GITHUB_REPO_URL>/commit/<LATEST_COMMIT_ID>
```
where `<LATEST_COMMIT_ID>` can be extracted from your project repo log by executing:

```shell
git --no-pager log --decorate=short --pretty=medium
```