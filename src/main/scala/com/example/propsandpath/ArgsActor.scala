package com.example.propsandpath

import akka.actor.{Actor, ActorLogging}
import ArgsActor.Input

// コンストラクタ引数有りのアクター。
//  注※ Scala ではクラス宣言のパラメータがコンストラクタ引数とフィールドになる
class ArgsActor(val repeat:Int) extends Actor with ActorLogging {

  def receive = {
    case Input(x) => {
      log.info(s"revive:${x}")
      // 文字列をフィールドrepeatの分繰り返したものを返信
      sender() ! x * repeat
    }
  }
}

// コンパニオンオブジェクト
object ArgsActor {
  // このアクターで使用するメッセージクラスの定義
  case class Input(x:String)
}
