package scala3experiments

trait Applicative[F[_]] extends Functor[F] {
  def map2[A, B, C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C] =
    val x = apply[A,B](f)(fa)
    val y = apply[B,C](f)(fb)
    y
  def apply[A, B](fab: F[A => B])(fa: F[A]): F[B]
  def unit[A](a: A): F[A]
}
