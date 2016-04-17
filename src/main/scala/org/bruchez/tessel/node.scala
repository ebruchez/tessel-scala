package org.bruchez.tessel

import scala.scalajs.js

// These are a few Node.js APIs

@js.native
trait Error extends js.Object {
  def message: String = js.native
}

@js.native
trait EventEmitter extends js.Object {
  def on                (typ: String, listener: js.Function): EventEmitter = js.native
  def addListener       (typ: String, listener: js.Function): EventEmitter = js.native
  def removeListener    (typ: String, listener: js.Function): EventEmitter = js.native
  def once              (typ: String, listener: js.Function): EventEmitter = js.native

  def removeAllListeners(typ: String)                       : EventEmitter = js.native

  def listeners         (typ: String)                       : js.Array[js.Function] = js.native
  def emit              (typ: String, args: js.Any*)        : Boolean = js.native
}
