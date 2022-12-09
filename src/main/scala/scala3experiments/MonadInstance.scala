package scala3experiments

object MonadInstance {

  val optionMonad = new Monad[Option]:
    override def unit[A](a: => A): Option[A] = None
    override def flatMap[A, B](ma: Option[A])(f: A => Option[B]): Option[B] =
      ma match
        case None    => None
        case Some(a) => f(a)

  val listMonad = new Monad[List]:
    override def unit[A](a: => A): List[A] = Nil
    override def flatMap[A, B](ma: List[A])(f: A => List[B]): List[B] =
      ma match
        case hd :: tl => f(hd) ::: flatMap(tl)(f)
        case _        => Nil
}
