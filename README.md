# Rafflery

## Start the application

1. `./gradlew clean build`
2. run `Main.kt`
3. `cd rafflery-ui && npm install && npm start`

## Docker
1. `docker build -t rafflery .`
2. `docker run -it -p 8080:8080 --rm rafflery`

## CircleCI
https://circleci.com/gh/ettisberger/rafflery

## end2end tests
Cypress + Docker + CircleCI

## ktlint code styles (pre commit hook)
- install git hook to automatically check files for style violations on commit
- run "ktlint installGitPrePushHook" if you wish to run ktlint on push instead

ktlint installGitPreCommitHook
