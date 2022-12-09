package scala3experiments

case class Reader[R, A](run: R => A)

object Reader {
  def readerMonad[R] = new Monad[({ type f[x] = Reader[R, x] })#f] {
    def unit[A](a: => A): Reader[R, A] = Reader(_ => a)
    def flatMap[A, B](st: Reader[R, A])(f: A => Reader[R, B]): Reader[R, B] =
//      val x = st.run
//      val p = {r:R => f(x(r))}
//      val w = map(st)(f)
//      val y = x.andThen({z => f(z)})
//      w
      ???
  }
}
