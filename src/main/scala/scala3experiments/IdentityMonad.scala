package scala3experiments

case class Id[A](value: A) {
  def map[B](f: A => B): B = f(value)
  def flatMap[F[_], B](f: A => F[B]): F[B] = f(value)
}

object Id {
  val idMonad = new Monad[Id]:
    override def unit[A](a: => A): Id[A] = Id(a)
    override def flatMap[A, B](ma: Id[A])(f: A => Id[B]): Id[B] = ma.flatMap(f)
}

@main
def idTest(): Unit =
  val x = Id(1)
  val y = x.flatMap(z => Some(z + 1))
  val w = Id.idMonad.sequence(List(Id(1), Id(2), Id(3)))
  val z2 = Id.idMonad.traverse(List(1, 2, 3))(p => Id(p + 3))
  val z = Id.idMonad.replicateM(5, Id(2))
  println(z)
