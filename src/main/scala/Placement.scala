case class Placement(center: Coord, rotatedRadians: Double, shape: Shape) {
  def occupiesSpaceAt(coord: Coord): Boolean = {
    val coordRelativeToRotationCenter = coord - center
    val rotatedCoordRelativeToRotationCenter = coordRelativeToRotationCenter.rotate(rotatedRadians)
    shape.occupiesSpaceAt(rotatedCoordRelativeToRotationCenter)
  }

  def rotate(radians: Double): Placement =
    copy(rotatedRadians = rotatedRadians + radians)

  def move(x: Double, y: Double): Placement =
    copy(center = center + Coord(x, y))
}
