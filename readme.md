# sprayer spray quick start

* copy `.env.sample` to `.env`
* fill it out
* run `forego run sbt`
* in sbt run `~reStart`
* go to [here](http://localhost:8080/tables)

# whats here

* bunch of plugins in project/plugins.sbt
* `sbt-revolver` reloads server on changes when you are running `~reStart` in sbt
* `sbt-scalariform` code formatter ala `gofmt` auto formats on build
* `sbt-idea`  run `gen-idea` in sbt to generate an intellij project
* `sbt-native-pckager` package the app up to run (on heroku)
* project with spray/akka/logback/dynamodb in project/Build.scala
* starter conf in src/main/resources
* starter main class, http service (routes), http actor (runs routes) in `src/main/scala/example`
* some sample spray-json stuff in `src/main/example/SprayerModels.scala` and handling in the `SprayerHttpService`
* testing in `src/test/example/SprayerHttpServiceSpec.scala`

# run on heroku

* create a heroku app
* add the config vars from .env
* git push heroku master (will take quite a few minutes on first push/build)

