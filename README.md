# CIS4307-3
CIS 4307 - Logical Clocks and Vector Clocks

## Android Application Design
The design of the application is primarily inspired by the requirements given to us in the lab instructions. Using the application is fairly straight forward and some steps to do this can be seen below.

1. Start the chat server by running the following command (linux only): java -jar chat_server.jar
2. Open the android application in either Android Studio or on a mobile device.
3. Enter a desired username, hostname, and port. The following are examples that work when running the application through an Android Studio emulator: any username, a hostname of "10.0.2.2", and a port of "4446".
4. Click on the button labelled "JOIN". When this button is clicked, the will be asynchronously registered with the server, if it exists.
5. You now have the choice of 3 different buttons; "ORDER CHAT LOGS", "RETRIEVE CHAT LOGS", "BACK". If you try to order the chat logs without retrieving them first, then you will be given an error message stating that you must first retrieve them. If you choose the "BACK" button, then you will be deregistered from the server automatically.
6. Choosing the "RETRIEVE CHAT LOGS" button will, again, asynchronously obtain the chat logs from the server, and will display them on the screen. They will be displayed in an unsorted, uneditted manner for demo purposes.
7. Now that the chat logs have been retrieved, you can choose the "ORDER CHAT LOGS" button. This button will take those existing chat messages and it will sort them, find any detected collisions, and display them in a more eye-pleasing manner.
8. You can now view the ordered messages, and click on the "BACK" button to deregister from the server.

### MainActivity
x

### SecondActivity
x

### ThirdActivity
x

### LamportClock.class
x

### VectorClock.class
x

### LamportClockTests
x

### VectorClockTests
x

### AsyncRegister
x

### AsyncDeregister
x

### AsyncRetrieveChatLog
x

## Existing Limitations
There are numerous existing limitations that I know of in the android application. These limitations, given enough time and experience in Android Studio, can be solved. These limitations incldue, but are not limited to; clicking on the android built-in back button will not deregister the currently registered user, turn the phone to any other orientation will cause errors with the current UDP connection to the server (don't turn the phone sideways), the activity named "SecondActivity" currently does not support changing the hostname and port (meaning that you must choose hostname and port from the main activity).