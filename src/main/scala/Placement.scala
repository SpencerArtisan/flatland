case class Placement(center: Coord, rotatedRadians: Double, shape: Shape) {
  def occupiesSpaceAt(coord: Coord): Boolean = {
    val coordRelativeToRotationCenter = coord - center
    val rotatedCoordRelativeToRotationCenter = coordRelativeToRotationCenter.rotate(rotatedRadians)
    val rotatedCoord = rotatedCoordRelativeToRotationCenter + center
    val relativeToPlacement = rotatedCoord - this.center
    shape.occupiesSpaceAt(relativeToPlacement)
  }

  def rotate(radians: Double): Placement =
    new Placement(center, rotatedRadians + radians, shape)
}
