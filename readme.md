# Playlist songs 

  - Spotify playlist based on your location's temperature

### Installation

This project is using gradle as builder and dependency manager.
In the root folder of the project run.

```sh
$ ./gradlew clean build
$ docker build -t playlist:latest .
$ docker run -p 8080:8080 playlist:latest
```

### API Rest Endpoints
http://localhost:8080/v1/city?name=santos

http://localhost:8080/v1/coordinates?lat=-21.176630&lon=-47.820839