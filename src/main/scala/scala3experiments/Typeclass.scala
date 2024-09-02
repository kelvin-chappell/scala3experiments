package scala3experiments

// 1. Trait
trait Area[A] {
  def area(a: A): Double
}
trait Show[A] {
  def show(a: A): String
}

// 2. Extension method
extension [A](a: A)(using ar: Area[A]) def areaOf: Double = ar.area(a)
extension [A](a: A)(using s: Show[A]) def sho: String = s.show(a)

case class Rectangle(width: Double, length: Double)
case class Circle(radius: Double)

// 3. Typeclass instances
given Area[Rectangle] with
  def area(r: Rectangle): Double = r.width * r.length
given Area[Circle] with
  def area(c: Circle): Double = Math.PI * c.radius * c.radius
given Show[Rectangle] with
  def show(r: Rectangle): String = s"Rectangle of ${r.width} x ${r.length}"
given Show[Circle] with
  def show(c: Circle): String = s"Circle of radius ${c.radius}"
given Show[Double] with
  def show(d: Double): String = d.toString

// 4. Use
@main
def main(): Unit =
  val shape1 = Rectangle(2, 3)
  println(shape1.sho)
  println(shape1.areaOf.sho)
  val shape2 = Circle(2)
  println(shape2.sho)
  println(shape2.areaOf.sho)
