package catseffect

import cats.effect.IO
import munit.{CatsEffectSuite, ScalaCheckEffectSuite}
import org.scalacheck.effect.PropF
import scala3experiments.{Id, Reader}

class HelloWorldTest extends CatsEffectSuite with ScalaCheckEffectSuite:
  test("Identity functor identity and composition"):
    PropF.forAllF { (a: Int, f: Int => Int, g: Int => Int) =>
      val id = Id.idMonad
      IO {
        assertEquals(id.map(Id(a))(identity), Id(a))
        assertEquals(id.map(id.map(Id(a))(f))(g), id.map(Id(a))(g.compose(f)))
      }
    }

  test("Identity monad left and right identity"):
    PropF.forAllF { (a: Int, f: Int => Int) =>
      val monad = Id.idMonad
      IO {
        assertEquals(monad.flatMap(monad.unit(a))(n => Id(f(n))), Id(f(a)))
        assertEquals(monad.flatMap(Id(a))(monad.unit), Id(a))
      }
    }

  test("Identity monad associativity"):
    PropF.forAllF { (a: Int, f: Int => Int, g: Int => Int) =>
      val monad = Id.idMonad
      val first = monad.flatMap(monad.flatMap(Id(a))(n => Id(f(n))))(n => Id(g(n)))
      val second = monad.flatMap(Id(a))(n => monad.flatMap(Id(f(n)))(m => Id(g(m))))
      IO(assertEquals(first, second))
    }

  test("Reader functor identity and composition"):
    PropF.forAllF { (environment: Int, a: Int, f: Int => Int, g: Int => Int) =>
      val monad = Reader.readerMonad[Int]
      val reader = Reader[Int, Int](_ => a)
      IO {
        assertEquals(monad.map(reader)(identity).run(environment), a)
        assertEquals(
          monad.map(monad.map(reader)(f))(g).run(environment),
          monad.map(reader)(g.compose(f)).run(environment)
        )
      }
    }

  test("Reader monad left and right identity"):
    PropF.forAllF { (environment: Int, a: Int, f: Int => Int) =>
      val monad = Reader.readerMonad[Int]
      val reader = Reader[Int, Int](_ => a)
      IO {
        assertEquals(
          monad.flatMap(monad.unit(a))(n => Reader[Int, Int](_ => f(n))).run(environment),
          f(a)
        )
        assertEquals(monad.flatMap(reader)(monad.unit).run(environment), a)
      }
    }

  test("Reader monad associativity"):
    PropF.forAllF { (environment: Int, a: Int, f: Int => Int, g: Int => Int) =>
      val monad = Reader.readerMonad[Int]
      val initial = Reader[Int, Int](_ => a)
      val first =
        monad.flatMap(monad.flatMap(initial)(n => Reader[Int, Int](_ => f(n))))(n =>
          Reader[Int, Int](_ => g(n))
        )
      val second = monad.flatMap(initial)(n =>
        monad.flatMap(Reader[Int, Int](_ => f(n)))(m => Reader[Int, Int](_ => g(m)))
      )
      IO(assertEquals(first.run(environment), second.run(environment)))
    }

  test("sequence, traverse, and large replicateM for Identity"):
    val monad = Id.idMonad
    assertEquals(monad.sequence(List(Id(1), Id(2), Id(3))), Id(List(1, 2, 3)))
    assertEquals(monad.traverse(List(1, 2, 3))(n => Id(n * 2)), Id(List(2, 4, 6)))
    val replicated = monad.replicateM(100000, Id(7)).value
    assertEquals(replicated.length, 100000)
    assertEquals(replicated.head, 7)
    assertEquals(replicated.last, 7)

  test("sequence, traverse, and replicateM for Reader"):
    val monad = Reader.readerMonad[Int]
    val readers = List(Reader[Int, Int](_ + 1), Reader[Int, Int](_ * 2))
    assertEquals(monad.sequence(readers).run(10), List(11, 20))
    assertEquals(
      monad.traverse(List(1, 2, 3))(n => Reader[Int, Int](_ + n)).run(10),
      List(11, 12, 13)
    )
    assertEquals(monad.replicateM(3, Reader[Int, Int](_ + 1)).run(10), List(11, 11, 11))
