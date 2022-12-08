import zio.ZIOAppDefault
import zio.*
import zio.stream.*

object IssueTapSink extends ZIOAppDefault {

  val program = for {
    ref <- Ref.make(0)
    fib <- ZStream
      .range(0, 100000)
      .tapSink(ZSink.foreach(i => ref.update(_ + 1)))
      .runDrain
      .fork
    _ <- fib.join
    count <- ref.get
    _ <- Console.printLine(s"count: $count")
  } yield count

  override def run = program.repeatWhile(_ == 100000)

}

object SinkOK extends ZIOAppDefault {

  val program = for {
    ref <- Ref.make(0)
    fib <- (ZStream
      .range(0, 100000)
      >>> ZSink.foreach(i => ref.update(_ + 1))).fork
    _ <- fib.join
    count <- ref.get
    _ <- Console.printLine(s"count: $count")
  } yield count

  override def run = program.repeatWhile(_ == 100000)

}
