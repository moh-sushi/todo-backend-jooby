# todo-backend-jooby

This project is an implementation of [Todo-Backend](https://www.todobackend.com/) using [Jooby](https://jooby.io/), [Hibernate](https://hibernate.org/) and [H2-Database](https://www.h2database.com/).

## running

    mvn clean jooby:run

## building

    mvn clean package

## docker

     docker build . -t todo-backend-jooby
     docker run -p 8080:8080 -it todo-backend-jooby


## License
All resources of this repository are distributed under the terms of the BSD License (2-clause).
The text of the license is included in the file [LICENSE](./LICENSE) in the root of the project.
