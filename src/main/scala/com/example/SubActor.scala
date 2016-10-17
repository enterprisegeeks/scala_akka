package com.example

import java.util.Date

import akka.actor.Actor
import com.example.MyActor.Request
import com.example.SubActor.Response


object SubActor {
  case class Response(answer:Int)
}

class SubActor extends Actor{

  def receive = {
    case Request(n) =>
      println("start task at" + new Date() )
      Thread.sleep(1000L)
      println("finish task at" + new Date() )
      sender() ! Response(n * n);
  }

}
