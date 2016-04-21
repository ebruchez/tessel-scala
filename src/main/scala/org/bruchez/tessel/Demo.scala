package org.bruchez.tessel

import scala.concurrent.duration._
import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global ⇒ g}

/**
  * This is a quick demo of using Tessel 2 from Scala. I uses the `relay-mono` module and on-board LEDs.
  */
object Demo extends js.JSApp {

  def main(): Unit = {

    println(s"starting `main()` at ${new js.Date()} with node version ${g.process.version}")

    val tessel    = g.require("tessel").asInstanceOf[Tessel]
    val relayMono = g.require("relay-mono").asInstanceOf[RelayMono]

    val relay     = relayMono.use(tessel.port.A)

    var elapsedMinutes = 0

    js.timers.setInterval(1.minute) {
      elapsedMinutes += 1
      println(s"Running for $elapsedMinutes minutes!")
    }

    relay.onReady {
      println("Relay ready!")

      js.timers.setInterval(2.seconds) {
        relay.toggle(1)
      }

      js.timers.setInterval(1.seconds) {
        relay.toggle(2)
      }
    }

    relay.onLatch { (channel, value) ⇒
        println(s"Latch on relay channel $channel switched to $value")

        if (value)
          tessel.led(channel + 1).on()
        else
          tessel.led(channel + 1).off()
    }
  }
}
