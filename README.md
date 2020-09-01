# AREP Lab 3

## Description
In this project we build a Web Server like Java Spark, this server support the method get with this you can build a API with a lambda function and we implement the static document devolution here read the files that are in the main/resources path

[![CircleCI](https://circleci.com/gh/orlandoagk/AREPlab3.svg?style=svg)](https://app.circleci.com/pipelines/github/orlandoagk)
## Execute
### Code (Deploying in Localhost)
1. `git clone https://github.com/orlandoagk/https://github.com/orlandoagk/AREPLab3.git`
2. `cd areplab2`
3. `mvn compile`
4. `mvn clean package`
5. `java -cp target\areplab3ServidorWeb-1.0-SNAPSHOT.jar edu.escuelaing.arep.Main`
6. `Enter to localhost with the port 36000` [localhost](localhost:36000)

**Only do this if you want to build and deploy the project in Localhost**
### Code Displayed (Using Heroku)
1. `Enter the url thats is the server deployed in Heroku` [Server deployed in Heroku](https://areplab3.herokuapp.com)

### Probe the resources
#### Static Resources
1. `/index.html`

#### API Resources
1. `/API/probandoapi`
2. `/API/getBDMessage`

**If you want to create your own API Endpoint you need to call in the main the get method of Spark implementation that is in Main class**

### Test
1. `mvn test`


## Documentation
To read the documentation of the project you need to enter to the root folder of the project and search in the Documentation folder, open the Index if you want

## Tecnology Stack
- [Java 8](https://www.java.com/es/download/)
- [Apache Maven](https://maven.apache.org/)
- [Github](https://www.github.com/)
- [Heroku](https://www.heroku.com)
- [CircleCI](https://www.circleci.com)

## Autor
- [orlandoagk - Github](https://www.github.com/orlandoagk)
- [Orlando Gelves - Linkedin](https://www.linkedin.com/in/orlando-antonio-gelves-kerguelen-11445b1a5/)

## Referencias
- [How to render HTML with Spark](http://campusvirtual.escuelaing.edu.co/moodle/pluginfile.php/129029/mod_resource/content/1/SparkWebApp.java)
- [Deploying in Heroku](https://devcenter.heroku.com/articles/git)
- [Send Image to Web Browser](https://stackoverflow.com/questions/40101964/send-images-to-browser-client)
- [Statement](http://campusvirtual.escuelaing.edu.co/moodle/pluginfile.php/222974/mod_resource/content/0/NamesNetworkClientService.pdf)

## Licencia
This project is licensed under the GNU General Public License v3.0 - see the [LICENSE](/LICENSE) file for more details.
