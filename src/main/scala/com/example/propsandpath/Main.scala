package com.example

import akka.actor.{ActorSystem}
import akka.pattern.ask
import com.example.propsandpath.ArgsActor._
import com.example.propsandpath.ArgsActor

import scala.concurrent.Await
import scala.concurrent.duration._

object Main extends App {
  val system = ActorSystem("MyActorSystem")
  // ? を使うためのスレッドプールとタイムアウトを暗黙的に宣言
  implicit val dispatcher = system.dispatcher
  implicit val timeout:akka.util.Timeout = 3 seconds
  // ファクトリによるパラメータ付きの生成
  val myActor = system.actorOf(ArgsActor.props(3), "argsActor")
  // ?(askパターン)によってアクターにメッセージを渡し、帰ってくるまで待機。
  (myActor ? Input("hoge")).mapTo[String].foreach(x => println(x))
  // 3秒後に終了
  Await.ready(system.terminate(), 3 seconds)
}