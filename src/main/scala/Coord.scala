
case class Coord(x: Double, y: Double, z: Double = 0) {
  val distanceFromOrigin: Double = Math.sqrt(x * x + y * y + z * z)

  def +(other: Coord): Coord = Coord(x + other.x, y + other.y, z + other.z)

  def *(a: Double): Coord = Coord(x * a, y * a, z * a)

  def -(other: Coord): Coord = Coord(x - other.x, y - other.y, z - other.z)

  def isWithin(rectangle: Rectangle): Boolean = {
    val xWithin = x >= 0 && x <= rectangle.width
    val yWithin = y >= 0 && y <= rectangle.height
    xWithin && yWithin
  }

  def setZ(z: Double): Coord = Coord(x, y, z)

  def rotate(radians: Double): Coord = {
    val sinRadians = Math.sin(radians)
    val cosRadians = Math.cos(radians)

    Coord(x * cosRadians - y * sinRadians, x * sinRadians + y * cosRadians)
  }

  def to2D: Coord = Coord(x, y)
}

object Coord {
  val ZERO: Coord = Coord(0, 0, 0)
}
