# Scala 3 experiments

Small Scala 3 examples of hand-written functional typeclasses:

- `Functor.scala`, `Applicative.scala`, and `Monad.scala` define the abstractions and
  common collection helpers.
- `IdentityMonad.scala` and `ReaderMonad.scala` provide Identity and Reader instances.
- `Typeclass.scala` demonstrates `Area` and `Show` typeclasses with rectangle and circle
  instances; `TypeclassDemo.scala` is the runnable demonstration.
- `catseffect/HelloWorld.scala` is a minimal Cats Effect application.

Run the test suite with `sbt test`. Run an example with `sbt run` and select the main
class when prompted (for example, `typeclassDemo`).
