package com.example

import java.util.concurrent.TimeUnit

import akka.actor.{ActorSystem, PoisonPill, Props}
import com.example.MyActor.Request

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object ApplicationMain extends App {
  val system = ActorSystem("MyActorSystem")
  val myActor = system.actorOf(Props[MyActor], "myActor")

  (1 to 10).foreach(n => myActor ! Request(n))


  Await.ready(system.whenTerminated, Duration(1, TimeUnit.MINUTES))

}