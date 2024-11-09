
case class Circle(id: Int, radius: Double) extends Shape {
  def occupiesSpaceAt(coord: Coord): Boolean =
    coord.distanceFromOrigin <= radius
}
