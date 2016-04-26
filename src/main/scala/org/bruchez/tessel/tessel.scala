package org.bruchez.tessel

import scala.concurrent.{Future, Promise}
import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global ⇒ g}

/**
 * Facades for a few Tessel 2 APIs.
 */

// "tessel"
// https://github.com/tessel/t2-firmware/blob/master/node/tessel-export.js

@js.native
trait Led extends js.Object {
  def on(): Unit = js.native
  def off(): Unit = js.native
}

@js.native
trait Pin extends js.Object with EventEmitter {
  def output(value: Int): Unit = js.native
  def read(callback: js.Function2[Error, Int, _]): Unit = js.native
}

@js.native
trait Port extends js.Object {
  def pin: js.Array[Pin] = js.native
}

@js.native
trait Ports extends js.Object {
  def A: Port = js.native
  def B: Port = js.native
}

@js.native
trait Tessel extends js.Object {
  def led: js.Array[Led] = js.native
  def port: Ports = js.native
}

object Tessel {
  def apply() = g.require("tessel").asInstanceOf[Tessel]
}

// "relay-mono"
// https://github.com/tessel/relay-mono/blob/master/index.js

@js.native
trait Relay extends js.Object with EventEmitter {
  def setState(channel: Int, state: Boolean, callback: js.Function2[Error, Boolean, _] = null): Unit = js.native
  def turnOn  (channel: Int, delay: Int,     callback: js.Function2[Error, Boolean, _] = null): Unit = js.native
  def turnOff (channel: Int, delay: Int,     callback: js.Function2[Error, Boolean, _] = null): Unit = js.native
  def toggle  (channel: Int,                 callback: js.Function2[Error, Boolean, _] = null): Unit = js.native
  def getState(channel: Int,                 callback: js.Function2[Error, Boolean, _] = null): Unit = js.native
}

object Relay {
  implicit class RelayOps(val relay: Relay) extends AnyVal {
    def onReady(listener: ⇒ Any)                = relay.on("ready", () ⇒ listener)
    def onLatch(listener: (Int, Boolean) ⇒ Any) = relay.on("latch", listener)

    def state(channel: Int): Future[Boolean] = {

      val p = Promise[Boolean]()

      relay.getState(channel, (err: Error, state: Boolean) ⇒ {
        if (err ne null)
          p.failure(new RuntimeException(err.message))
        else
          p.success(state)
      })

      p.future
    }
  }
}

@js.native
trait RelayMono extends js.Object {
  def use(port: Port, callback: js.Function2[Error, Relay, _] = null): Relay = js.native
}

object RelayMono {
  def apply() = g.require("relay-mono").asInstanceOf[RelayMono]
}