case class Dot(id: Int) extends Shape {
  def occupiesSpaceAt(coord: Coord): Boolean =
    coord == Coord.ZERO
}
