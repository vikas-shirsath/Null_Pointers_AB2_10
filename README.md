# Null_Pointers_AB2_10

> Project Name : PII DETECTOR
> Problame Statement ID : PS10
> Problame Statement :  IDENTIFICATION OF PERSONALLY IDENTIFIABLE INFORMATION (PII) IN DOCUMENTS AND DATA

Setup Guide for Java Spring Backend and React Frontend.
This project consists of a Java Spring Boot backend and a React frontend. Follow the steps below to set up the environment and run the project locally.

Spring Boot Backend Setup in IntelliJ IDEA
Prerequisites
Before you start, make sure you have the following tools installed on your system:

[IntelliJ IDEA](https://www.jetbrains.com/idea/) (Community or Ultimate Edition)
[Java JDK 11](https://www.oracle.com/in/java/technologies/downloads/) (Spring Boot 2.x supports JDK 11 and later)
[Maven](https://maven.apache.org/download.cgi) (for building the project)

## Step 1: Clone the Repository

## Step 2: Open the Project in IntelliJ IDEA
Launch IntelliJ IDEA and select Open.
Navigate to the folder where you cloned the repository, and open the project.
IntelliJ IDEA will automatically detect that it's a Maven-based project and prompt you to import the Maven dependencies.

If it doesn't automatically import the dependencies, you can manually reload the project by clicking the Maven tool window (on the right side) and then clicking the Refresh button.

## Step 3: Configure the Spring Boot Application
Ensure that your application.properties file contains the necessary configurations for your project (like database connection, server port, etc.).

## Step 4: Build and Run the Spring Boot Application
Using IntelliJ IDEA:
In the Maven tool window (on the right), find the Lifecycle section.

Double-click on clean to clean the project, and then double-click on install to build the project.

You can also run the application directly from IntelliJ IDEA:

Open the YourApplication.java (the main class annotated with @SpringBootApplication).
Click the Run button (a green arrow) in the top-right corner of the editor, or press Shift + F10.
This will start the Spring Boot application, typically at http://localhost:8080.

Using the Terminal (Optional):
If you prefer to run it from the terminal, you can use Maven to run the Spring Boot app:
cd backend
mvn spring-boot:run
This will start the backend server.

## Step 5: Verify the Backend is Running
Open a browser or use Postman (or similar API tool) to test the API.

Navigate to:
http://localhost:8080/api/your-endpoint
You should get a response from your backend.

## Step 6: Optional IntelliJ IDEA Configuration
Run Configuration for Spring Boot: You can create a Run Configuration for your Spring Boot application in IntelliJ IDEA to make it easier to run directly from the IDE:

Go to Run > Edit Configurations.
Click the + sign and select Spring Boot.
Select your main application class (YourApplication.java).
Apply and save the configuration.

# Getting Started with Create React App

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can't go back!**

If you aren't satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead, it will copy all the configuration files and the transitive dependencies (webpack, Babel, ESLint, etc) right into your project so you have full control over them. All of the commands except `eject` will still work, but they will point to the copied scripts so you can tweak them. At this point you're on your own.

You don't have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn't feel obligated to use this feature. However we understand that this tool wouldn't be useful if you couldn't customize it when you are ready for it.

## Learn More

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).

### Code Splitting

This section has moved here: [https://facebook.github.io/create-react-app/docs/code-splitting](https://facebook.github.io/create-react-app/docs/code-splitting)

### Analyzing the Bundle Size

This section has moved here: [https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size](https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size)

### Making a Progressive Web App

This section has moved here: [https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app](https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app)

### Advanced Configuration

This section has moved here: [https://facebook.github.io/create-react-app/docs/advanced-configuration](https://facebook.github.io/create-react-app/docs/advanced-configuration)

### Deployment

This section has moved here: [https://facebook.github.io/create-react-app/docs/deployment](https://facebook.github.io/create-react-app/docs/deployment)

### `npm run build` fails to minify

This section has moved here: [https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify](https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify)

