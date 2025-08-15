# FlowForge-FX (In development)

<img width="1920" height="1040" alt="image" src="https://github.com/user-attachments/assets/1e83452a-d75e-438e-b41b-e648704107c1" />



This is a rewrite of my current ongoing FlowForge project. Previously was using Java swing, and now it's being re-written in JavaFX.
Right now it's in a seperate repository from the original FlowForge, but once this is complete, the old FlowForge will be archived and named "FlowForge-Swing", 
and FlowForgeFX will be ranamed to FlowForge. 

**Specifications**

* Java version : 21
* JavaFX version : 21
* Build tool : Gradle
* Development machine : Linux Ubuntu (X11 Desktop)
* IDE : IntelliJ IDEA

## Status of project 
The project is only 15% complete. The UI looks empty and bland because I haven't started working on it. For now I am only focusing 
on coding the functionalities and features first. Once I get the backend right, I will focus more on frontend  

## Why JavaFX? 
* Modern API's and Graphics pipeline
* Better UI library
* CSS styling, more customizable than swing
* Hardware acceleration

## Need to re-write FlowForge in JavaFX
* Later on I will need graphs and charts. JavaFX has native support for that
* Swing doesn't scale properly on various displays well.
* Easy to distribute as installers
* Better for FlowForge in the long run, as JavaFX is more modern and powerful than swing

Also this project won't be using FXML. I just prefer to code by hand (coming from a swing background) and also for performance reasons
However I will still follow the MVC architecture (Model-view-controller)

## To run FlowForgeFX on your machine
(IntelliJ IDEA is heavily suggested)
1. Clone this repo :- ``` https://github.com/gufranthakur/FlowForgeFX.git ```
2. Open IntelliJ IDEA, go to the home screen by clicking on "File > close project.
3. Clone the project by clicking on "Clone repository"
4. (If you don't have Java installed) A yellow bar will appear displaying "JDK not configured". Click on "configure JDK" and install JDK version 21, from Eclipse Temurin
5. Run the project from gradle run (or from the terminal, ```./gradlew run```)
