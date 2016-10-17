package com.example

import akka.actor.{Actor, PoisonPill, Props}
import com.example.MyActor.{Request}
import com.example.SubActor.Response

object MyActor {
  case class Request(num:Int)
}

class MyActor extends Actor{

  var finished = 0

  def receive = {
    case req:Request =>
      val child = context.actorOf(Props[SubActor])
      child ! req
    case Response(n) =>
      println("Answer is " + n)
      sender() ! PoisonPill
      finished = finished +1
      if (finished >= 10) {
        context.system.terminate()
      }
  }
}
