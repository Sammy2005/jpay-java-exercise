<h1 align="center">jpay-java-exercise 👋</h1>

This repository comprises two apps. JPAY backend app and jpay-angular app

> Track customer details with refined dashboard. Unique features like filter and search available<br>
> Ability to get customized page sizes has been added in the latest release.

## ✨ JPAY
> Spring boot app run in java. <br>
> Has a SQLite database for storage.


## How to run

##Local
To run the app locally simply clone the project navigate to JPAY `cd JPAY` then run `gradle bootRun`
The app will be available on `http://localhost:8000`

##Docker

To build an image run:
>`docker build . -t jpay-service:version`

To run the image after build run:

>`docker run -p 8000:8000 jpay-service:version` <br>

The app will be available on `http://localhost:8000`

## ✨ jpay-angular
> This is an angular based application
> Makes use of libraries such as primeng and tailwindcss for styling

## How to run

##Local
To run the app locally simply clone the project navigate to jpay-angular `cd jpay-angular`<br>
To install dependencies run `npm install` <br>
To run the application after installation run `npm run start` <br>
The app will be available on `http://localhost:4200`

##Docker

To build an image run:
>`docker build . -t jpay-angular-app:version`

To run the image just build run:
>`docker run -p 8000:8000 jpay-angular-app:version` <br>


## ✨ To Build The App On Docker Compose

Ensure you have docker compose installed on you machine. <br>
If not follow the following link to install [docker-impose](https://docs.docker.com/compose/install/)

##Build and Run
navigate to the project directory and run the following command: <br>
`docker-compose up -d`

To see the running images run `docker ps -a`
