package scala3experiments

@main
def typeclassDemo(): Unit =
  val rectangle = Rectangle(2, 3)
  println(rectangle.sho)
  println(rectangle.areaOf.sho)
  val circle = Circle(2)
  println(circle.sho)
  println(circle.areaOf.sho)
