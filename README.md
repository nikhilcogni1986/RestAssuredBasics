# API Automation: Rest Assured Basics

<a id="quick-start-guide"></a>
## 🏃 Quick Start Guide

#### 1: Initial Setup

- Create a new Java/Maven project using Eclipse, IntelliJ or your favourite IDE.
- Copy the contents of 
  this [pom.xml](https://github.com/nikhilcogni1986/RestAssuredBasics/blob/master/pom.xml) file into yours
  inside the ```<project>``` tag and refresh the project to install the dependencies.

#### 2: Folder Structure
```
   src
      test
          Java
              Courses
              Deserilization
              GoogleAPI
              Jira
              LibraryAPI
              OAuthAPI
              Pojo
              PostManAPI
              Serilization
              eCommerce
          resources    
```

#### 3: Understanding the Folders  
- Courses - This contains test to parse the JSON schema even when the endpoint for API in not available.
- Deserialization - This contains Oauth test which use the concept of deserialization.
- GoogleAPI - This contains end-to-end tests for add, update, delete place. 
- JiraAPI - This contains end-to-end tests for Jira api where in we log in using key, add an attachment and comments to an issue with assertions.
- LibraryAPI - This contains tests to add book using post method and also uses TestNg data provider annotation to add multiple books.
- OAuthAPI: This contains tests to demonstrate the use of OAuth authentication
- PostMan API - This contains several tests covering Hamcrest assertions, BDD, Non BDD tests for get, Post calls.
- Pojo: This contains class files which are the payload files for the course API.
- Serialization: This contains tests which demonstrates the concept of serialization using Google API.
- eCommerce - This contains tests which demonstrate login to AUT, placing orders using API calls.


#### Step 4: Running Tests
- Navigate to folders based on concepts or API
and pick a test file and run them as TestNG test

## 👨‍💻 Tech Stack
<a href="https://www.oracle.com/eg/java/technologies/downloads/" target="_blank"><img src="https://www.chrisjmendez.com/content/images/2019/01/Java_logo_icon.png" alt="Java" height="50px"></a>
<a href="https://maven.apache.org/" target="_blank"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Apache_Maven_logo.svg/340px-Apache_Maven_logo.svg.png" alt="Maven" height="50px"></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="https://www.jetbrains.com/idea/" target="_blank"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/IntelliJ_IDEA_Icon.svg/1200px-IntelliJ_IDEA_Icon.svg.png" alt="IntelliJ IDEA" height="50px"></a>
![REST Assured](rest-assured-logo-green.png)
#### Learn Rest Assured Basics from this Repo.
