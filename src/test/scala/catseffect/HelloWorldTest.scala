package catseffect

import org.scalacheck.effect.PropF
import org.scalacheck.Test
import cats.effect.{ExitCode, IO, IOApp}

object HelloWorldTest extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {
    val p: PropF[IO] =
      PropF.forAllF { (x: Int) =>
        IO(x).map(res => assert(res == x))
      }
    val result: IO[Test.Result] = p.check()
    result.flatMap(r => IO(println(r))).as(ExitCode.Success)
  }
}
