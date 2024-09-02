package catseffect

import cats.effect.{IO, IOApp}

object HelloWorld extends IOApp.Simple {
  def run: IO[Unit] =
    IO.println("Hello, World!")
}
