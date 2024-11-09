
case class Rectangle(id: Int, width: Double, height: Double) extends Shape {
  def occupiesSpaceAt(coord: Coord): Boolean =
    (Coord(width / 2, height / 2) + coord).isWithin(this)
}
