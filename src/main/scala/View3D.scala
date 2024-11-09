case class View3D() {

}

object View3D {
  def worldToScreen(world3DCoord: Coord, screenZ: Double, observer: Coord = Coord.ZERO): Coord = {
    val scalingFactor = (screenZ - observer.z) / (world3DCoord.z - observer.z)
    (world3DCoord * scalingFactor).to2D - observer.to2D
  }

  def screenToWorld(screen3DCoord: Coord, worldZ: Double, observer: Coord = Coord.ZERO): Coord = {
    val scalingFactor = (worldZ - observer.z) / (screen3DCoord.z - observer.z)
    ((screen3DCoord * scalingFactor) - observer.to2D).setZ(worldZ)
  }
}
