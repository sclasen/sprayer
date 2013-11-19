# sprayer spray quick start

* copy .env.sample to .env
* fill it out
* run `forego run sbt`
* in sbt run `~reStart`
* go to http://localhost:5000/tables

# whats here

* bunch of plugins in project/plugins.sbt
* `sbt-revolver` reloads server on changes when you are running `~reStart` in sbt
* `sbt-scalariform` code formatter ala `gofmt` auto formats on build
* `sbt-idea`  run `gen-idea` in sbt to generate an intellij project
* `sbt-native-pckager` package the app up to run (on heroku)
* project with spray/akka/logback/dynamodb in project/Build.scala
* starter conf in src/main/resources
* starter main class, http service (routes), http actor (runs routes) in `src/main/scala/example`
