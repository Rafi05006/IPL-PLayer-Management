# IPL Insights

IPL Insights is a JavaFX-based cricket player and club management application. It
supports player searches, club statistics, authentication, and player transfers
through a client-server architecture.

## Features

- Club login and signup
- Search players by:
  - Name
  - Position
  - Club and country
  - Salary range
- View country and club information
- View total club salary, maximum salary, age, and height
- Add and manage players
- Mark players for transfer and view the transfer list
- Store player data using local text files
- Communicate between the client and server using sockets

## Project Structure

```text
fxml/
  *.java                 Java application, controller, model, and networking code
  *.fxml                 JavaFX user interface layouts
  playerImages[1]/       Player images, country flags, and club logos
players.txt              Main player data
playersoffline.txt       Offline player data used by the client
credentials.txt          Local login data (not tracked by Git)
```

## Requirements

- Java Development Kit (JDK)
- JavaFX SDK
- IntelliJ IDEA or another Java IDE

The project does not include a Maven or Gradle build file, so configure the
JavaFX library and VM options in the IDE before running it.

## Running the Application

1. Open the project in IntelliJ IDEA.
2. Configure the project SDK and JavaFX library.
3. Create a local `credentials.txt` file in the project root. Each entry should
   use this format:

   ```text
   clubName=clubCode
   ```

4. Start `fxml.Server` first. The server listens on port `33333`.
5. Start `fxml.Main` to launch the JavaFX client.

The client connects to `127.0.0.1:33333` by default.

## Notes

- `out/`, `.idea/`, IntelliJ module files, and `credentials.txt` are excluded
  from version control.
- Keep credentials local and do not commit them to the repository.
