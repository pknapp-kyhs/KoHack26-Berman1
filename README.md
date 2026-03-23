List of files:
Perek: a folder containing classes to handle an output from ssefaria
Perek/Perek.java: Is the main class to hold all data from an api call
Perek/Version.java: Holds all the data about a particular version of the text

Quiz: a folder containing classes to hold and run a quiz.
Quiz/Quiz.java: The main class holding all data from a quiz json. it also has methods to run the quiz and to set it up from a json.
Quiz/Question.java: A class containing all information about one question. It also contains a method to utelize GUI elements to ask the question.
Quiz/Answer.java: contains all information about one potential answer in a question

API.java: A general class to make API calls, used in this project to make calls to the Sefaria API
App.java: main class to start the program
Chumash.java: A class with both general methods and variables for use of the sefaria API for tanach and other sources, in addition to specific methods and variables to access chumash texts.
GUI.java: A class with many methods used throughout the program to simplify the building of java swing pages.
HomePage.java: A class to build and display the home page using methods in GUI.java
Leaderboard.java: A class to build and display the home page using methods in GUI.java (and a merge sort)
LoginPage.java: A class to build, display, and run the home page using methods in GUI.java
Parser.java: A general class using the Gson library to parse jsons. Used to process API calls and stored quizes.
PassukDisplay.java: A class to allow any sefer of tanach or other sefaria source to be easily displayed and moved through
PassukSelector.java: A class allowing users to select any passuk in chumash to open to in a PassukDisplay. uses GUI.java
PerekAdapter.java: A custom Json Deserializer allowing a string, array of string, or 2d array of strings, to be parsed to a 2d string array in java.
SortElement.java: an element used to jointly sort names and numbers in the merge sort in Leaderboard.java
TextToSpeech.java: a class implementing TTS libraries to be easily callable throughout the program. Also uses GUI.java to make voice settings available to the user.

Three features:
1. Our text reader. Includes Various GUI methods, pulls texts from the sefaria API, and allows the user to both select and navigate through a sefer of chumash.
2. Our quiz platform. Opens a quiz from where it is stored as a JSON, allows the user to answer questions, scores the quiz, and ranks the user in a leaderboard (using a merge sort).
3. Screen reader. Maybe areas of the app have been made accessible with a TTS system, which is spread across the app and also configurable by the user.