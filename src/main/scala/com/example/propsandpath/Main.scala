package com.example

import java.util.concurrent.TimeUnit

import akka.actor.{ActorSystem, PoisonPill, Props}
import com.example.MyActor.Request
import com.example.propsandpath.ArgsActor._
import com.example.propsandpath.ArgsActor

import scala.concurrent.Await
import scala.concurrent.duration._

import akka.pattern.ask

object Main extends App {
  val system = ActorSystem("MyActorSystem")
  val myActor = system.actorOf(Props(classOf[ArgsActor], 34), "argsActor")
  implicit val timeout:akka.util.Timeout = 3 seconds
  implicit val dispatcher = system.dispatcher

  Props.apply()

  (myActor ? Input("hoge")).mapTo[String].foreach(x => println(x))

  Await.ready(system.terminate(), 3 seconds)

}