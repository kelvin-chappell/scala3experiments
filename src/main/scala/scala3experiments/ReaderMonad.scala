package scala3experiments

final case class Reader[R, A](run: R => A)

object Reader:
  def readerMonad[R]: Monad[[A] =>> Reader[R, A]] =
    new Monad[[A] =>> Reader[R, A]]:
      override def unit[A](a: => A): Reader[R, A] = Reader(_ => a)

      override def flatMap[A, B](ma: Reader[R, A])(
          f: A => Reader[R, B]
      ): Reader[R, B] =
        Reader(r => f(ma.run(r)).run(r))
