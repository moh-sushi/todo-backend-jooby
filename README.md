# todo-backend-jooby

This project is an implementation of [Todo-Backend](https://www.todobackend.com/) using [Jooby](https://jooby.io/), [Hibernate](https://hibernate.org/) and [H2-Database](https://www.h2database.com/).

## running

    mvn clean jooby:run

## building

    mvn clean package

## docker

     docker build . -t todo-backend-jooby
     docker run -p 8080:8080 -it todo-backend-jooby

## heroku

:information_source: both deployment variants contain the same application code

### deployment jar file

* deploy link : https://todo-backend-jooby-v2.herokuapp.com/todos
* link for automatic testing todo-backend : https://www.todobackend.com/specs/index.html?https://todo-backend-jooby-v2.herokuapp.com/todos
* link for using client with this todo-backend : https://www.todobackend.com/client/index.html?https://todo-backend-jooby-v2.herokuapp.com/todos

### docker deployment

* deploy link : https://todo-backend-jooby-docker.herokuapp.com/todos
* link for automatic testing todo-backend : https://www.todobackend.com/specs/index.html?https://todo-backend-jooby-docker.herokuapp.com/todos
* link for using client with this todo-backend : https://www.todobackend.com/client/index.html?https://todo-backend-jooby-docker.herokuapp.com/todos


## License
All resources of this repository are distributed under the terms of the BSD License (2-clause).
The text of the license is included in the file [LICENSE](./LICENSE) in the root of the project.
