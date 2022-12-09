package scala3experiments

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}
