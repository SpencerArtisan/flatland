case class View3D() {

}

object View3D {
  def worldToScreen(world3DCoord: Coord, screenZ: Double, observerZ: Double = 0): Coord = {
    val scalingFactor = (screenZ - observerZ) / (world3DCoord.z - observerZ)
    (world3DCoord * scalingFactor).to2D
  }

  def screenToWorld(screen3DCoord: Coord, worldZ: Double, observerZ: Double = 0): Coord = {
    val scalingFactor = (worldZ - observerZ) / (screen3DCoord.z - observerZ)
    (screen3DCoord * scalingFactor).setZ(worldZ)
  }
}
