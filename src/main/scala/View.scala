case class View(occupiedCells: Seq[Seq[Boolean]]) {
  def render(blockChar: Char = '*', blankChar: Char = '.', xScale: Int = 1): String =
    occupiedCells
      .map(_.map(occupied => (if (occupied) blockChar else blankChar).toString * xScale).mkString)
      .mkString("\n")
}

object View {
  def on(world: World, width: Int, height: Int, screenZ: Double = 0, observer: Coord = Coord(0, 0, -10)): View = {
    val rows = 0 until height
    val columns = 0 until width

    View(rows
      .map(row => columns
        .map(column => Coord(column, row, screenZ))
        .map(screenCoord => View3D.screenToWorld(screenCoord, 0, observer))
        .map(worldCoord => world.exists(_.occupiesSpaceAt(worldCoord)))))
  }
}