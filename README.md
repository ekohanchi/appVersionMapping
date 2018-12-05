# Application Version Mapping
A java spring boot applications which utilizes multi threading capabilities to display application versions across multiple different environments in a tabular format. It also has the capability to encrypt/decrypt secure information such as api keys using a password and salt which can be stored in an external configuration file.

## Configure - Application

```bash
# create a directory called conf
$ mkdir conf
$ cd conf
# within the directory create a file called application.properties
$ vim application.properties
```

Within the file the following content should be added:

```
server.port={PORT_TO_START_APP_ON}
server.hostname={HOSTNAME_OF_SERVER_WITH_PERMISSION_TO_ENCRYPT}
proxy.ignore={TOGGLE_USAGE_OF_PROXY}

security.encryptPassword={ENCRYPTION_PASSWORD}
security.encryptSalt={ENCRYPTION_SALT}
```

Create an environment variable called `APISERVICES_HOME` and point it to where the conf directory is located

```
$ vim ~/.bash_profile
export APISERVICES_HOME={PATH_TO_CONF_DIRECTORY}
$ . ~/.bash_profile
```

## Startup
To start up the application:
```
mvn spring-boot:run
```
## Build as war file
To build the application as a war file:
```
mvn clean package
```

## Access
To access the application, on your browser navigate to: http://localhost:{server.port}

## Screenshots
View of the home page when the application is first started up:
![Home Page](screenshots/HomePage.png?raw=true "Home Page")

The encryption page which allows a user to encrypt any text. This page can be accessed by going to the `/encryption` endpoint
![Encryption Page](screenshots/EncryptionPage.png?raw=true "Encryption Page")

A sample project utilizing a test version endpoint (which is started up when the application starts up in order to display data) which displays application version across multiple different environments.
![Sample Project Page](screenshots/SampleProject.png?raw=true "Sample Project Page")

A sample multi-component project utilizing a test version endpoint (which is started up when the application starts up in order to display data) which displays application version across multiple different environments.
![Multi Comp Sample Project Page](screenshots/MultiCompSampleProject.png?raw=true "Multi Comp Sample Project Page")


