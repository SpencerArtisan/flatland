
case class Circle(id: Int, radius: Double) extends Shape {
  val center: Coord = Coord.ZERO

  def occupiesSpaceAt(coord: Coord): Boolean =
    coord.distanceFromOrigin() <= radius
}
