# Demo of Scala on Tessel 2

## What's this

This is a simple demo of running [Scala](http://www.scala-lang.org/) on [Tessel 2](https://tessel.io/) by using [Scala.js](https://www.scala-js.org/).

This code includes:

- the demo proper, [`Demo.scala`](src/main/scala/org/bruchez/tessel/Demo.scala)
- some minimal *facades* for [Tessel](src/main/scala/org/bruchez/tessel/tessel.scala) and [Node.js](src/main/scala/org/bruchez/tessel/node.scala)

See also the companion blog post [Scala on Tessel 2](http://blog.bruchez.name/2016/04/scala-on-tessel-2.html).

## Requirements

- Scala and `sbt`
- `npm`
- Tessel 2's `t2` command
- a connected Tessel 2

## Compiling and running

- `sbt fullOptJS`
- `t2 run target/scala-2.11/tessel-scala-opt.js`

## License

MIT license.
