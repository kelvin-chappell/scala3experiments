package scala3experiments

import munit.FunSuite

class TypeclassSuite extends FunSuite:
  test("area and display instances for rectangles"):
    val rectangle = Rectangle(2, 3)
    assertEquals(rectangle.areaOf, 6.0)
    assertEquals(rectangle.sho, "Rectangle of 2.0 x 3.0")
    assertEquals(rectangle.areaOf.sho, "6.0")

  test("area and display instances for circles"):
    val circle = Circle(2)
    assertEquals(circle.areaOf, Math.PI * 4)
    assertEquals(circle.sho, "Circle of radius 2.0")
