package com.example

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import com.example.MyActor.Request
import com.example.SubActor.Response
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class SampleActorSpec(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {
 
  def this() = this(ActorSystem("MySpec"))
 
  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }
 
  "A Sub actor" must {
    "send back a Response(64) on a Request(8)" in {
      val subActor = system.actorOf(Props[SubActor])
      subActor ! Request(8)

      expectMsg(Response(64))
    }
  }

}
