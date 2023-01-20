package scala3experiments

import scala3experiments.Thing.map2

trait Applicative[F[_]] extends Functor[F]:

  def map2[A, B, C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C] =
    apply(map(fa)(f.curried))(fb)

  def apply[A, B](fab: F[A => B])(fa: F[A]): F[B] // =
//    map2(fab, fa)(_(_))

  def unit[A](a: A): F[A]

  override def map[A, B](fa: F[A])(f: A => B): F[B] =
    apply(unit(f))(fa)

  def sequence[A](fas: List[F[A]]): F[List[A]] =
    traverse[F[A], A](fas)(a => a)

  def traverse[A, B](as: List[A])(f: A => F[B]): F[List[B]] =
    as.foldRight(unit(List[B]()))((a, fbs) => map2(f(a), fbs)(_ :: _))

  def replicateM[A](n: Int, fa: F[A]): F[List[A]] = ???

  def factor[A, B](fa: F[A], fb: F[A]): F[(A, B)] = ???

object Thing extends Applicative[List]:

  override def apply[A, B](fab: List[A => B])(fa: List[A]): List[B] =
    fa.flatMap(a => fab.map(_(a)))

  override def unit[A](a: A): List[A] = List(a)
