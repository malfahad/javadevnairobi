[![codecov](https://codecov.io/gh/malfahad/javadevnairobi/branch/develop/graph/badge.svg)](https://codecov.io/gh/malfahad/javadevnairobi)

[![CircleCI](https://circleci.com/gh/malfahad/javadevnairobi/tree/develop.svg?style=svg)](https://circleci.com/gh/malfahad/javadevnairobi/tree/develop)

[![Maintainability](https://api.codeclimate.com/v1/badges/27a7d9bf46c46876a246/maintainability)](https://codeclimate.com/github/malfahad/javadevnairobi/maintainability)


# Javadevnairobi

## Goal

An android App that lists Java developers in Nairobi.

## Android setup

This Project is built with `Java`. It also has unit tests and Espresso UI tests.

### Testing

Espresso UI tests can be run using:
~~~~
./gradlew connectedCheck
~~~~

JUnit unit tests can be run using one of the following:
~~~~
./gradlew test
./gradlew check
./gradlew jacocoTestReport
~~~~

Test just runs the tests, check would run tests plus checkstyle, lint etc, and build compiles everything & checks & tests, jacocoTestReport runs debug, release tests, and prepares a coverage report.
 
 #### Fastlane 
 Fastlane is used to automate sharing of debug sample APK's on slack. To set it up;
   * Install fastlane
   ~~~~
   brew cask install fastlane
   ~~~~
   * Setup Slack environment variables for `SLACK_URL` and `SLACK_TOKEN`
   * Run `fastlane` in the terminal


## Development Android Signing Key

The Android system requires that all installed applications be digitally signed with a certificate whose private key is held by the application's developer. The Android system uses the certificate as a means of identifying the author of an application and establishing trust relationships between applications. The certificate is not used to control which applications the user can install. The certificate does not need to be signed by a certificate authority: it is perfectly allowable, and typical, for Android applications to use self-signed certificates.


## UI Mock ups
 The Java Devs Nairobi has these screens:

#### Mockup animation
 ![image](wireframes/mockup.gif)

#### List of developers
   ![image](wireframes/devsList.png)

#### Profile of Single Developer
   ![image](wireframes/devsDetail.png)

#### Share Profile Intent Chooser
 ![image](wireframes/devsShare.png)


#### Wire framing tool used  

  The UI wireframes were created with **JustInMind**. A popular wireframing tool for designing, animating, testing and collecting feedback on wireframes and mock ups.  
  This tool was used because of it's;
  * Simplicity of use and flat learning curve.
  * Rich feature set and components library.
  * `Test on phone` feature that allows you to test out the UI immediately on a mobile device.
  * Free plan. The software can be used with all it's features free of charge.
  
  