package scala3experiments

case class Reader[R, A](run: R => A)

object Reader:
  def readerMonad[R] = new Monad[({ type f[x] = Reader[R, x] })#f]:

    def unit[A](a: => A): Reader[R, A] = Reader(_ => a)

    def flatMap[A, B](st: Reader[R, A])(f: A => Reader[R, B]): Reader[R, B] =
      Reader(r => f(st.run(r)).run(r))

@main
def readerTest(): Unit =
  val x = Reader[Int, String](_.toString)
  val y = Reader[String, Long](_.length.toLong)
  val u = Reader[Int, String](n => s"${n.toString}s")
  val w = Reader.readerMonad.sequence(List(x, u))
  val z2 = Reader.readerMonad.traverse(List(1, 2, 3))(_ => x)
  val z = Reader.readerMonad.replicateM(5, u)
  println(z.run(5))
