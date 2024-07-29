package scala3experiments

//case class Reader[R, A](run: R => A)

//object Reader:
//
//  def readerMonad[R] = new Monad[({ type f[x] = Reader[R, x] })#f]:
//
//    def unit[A](a: => A): Reader[R, A] = Reader(_ => a)
//
//    def flatMap[A, B](st: Reader[R, A])(f: A => Reader[R, B]): Reader[R, B] =
//      Reader(r => f(st.run(r)).run(r))
//
//
//// Reader Monad in Scala
//
////import scala.language.higherKinds
//
//// A type alias for the type constructor of a Reader monad.
//  type Reader[R, A] = R => A
//
//// The method to construct a Reader Monad.
//  def reader[R, A](f: R => A): Reader[R, A] = f
//
//// The map method for a Reader Monad
//  def map[R, A, B](f: A => B)(fa: Reader[R, A]): Reader[R, B] =
//    r => f(fa(r))
//
//// The flatMap (bind) method for a Reader Monad.
//  def flatMap[R, A, B](f: A => Reader[R, B])(fa: Reader[R, A]): Reader[R, B] =
//    r => f(fa(r))(r)
//
//// The pure method for a Reader Monad.
//  def pure[R, A](a: A): Reader[R, A] =
//    _ => a

//@main
//def readerTest(): Unit =
//  val x = Reader.reader[Int, String](_.toString)
//  val y = Reader.reader[String, Long](_.length.toLong)
//  val u = Reader.reader[Int, String](n => s"${n.toString}s")
//  val w = Reader.readerMonad.sequence(List(x, u))
//  val z2 = Reader.readerMonad.traverse(List(1, 2, 3))(_ => x)
//  val z = Reader.readerMonad.replicateM(5, u)
//  println(u(5))
