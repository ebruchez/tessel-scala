enablePlugins(ScalaJSPlugin)

name := "tessel-scala"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.0"

scalaJSOutputWrapper := (
  """ |var addGlobalProps = function(obj) {
      |  obj.myRequire = require;
      |}
      |if((typeof __ScalaJSEnv === "object") && typeof __ScalaJSEnv.global === "object") {
      |  addGlobalProps(__ScalaJSEnv.global);
      |} else if(typeof  global === "object") {
      |  addGlobalProps(global);
      |} else if(typeof __ScalaJSEnv === "object") {
      |  __ScalaJSEnv.global = {};
      |  addGlobalProps(__ScalaJSEnv.global);
      |} else {
      |  var __ScalaJSEnv = { global: {} };
      |  addGlobalProps(__ScalaJSEnv.global)
      |}
  """.stripMargin,
  "org.bruchez.tessel.Demo().main();"
)