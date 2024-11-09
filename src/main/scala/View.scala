case class View(occupiedCells: Seq[Seq[Boolean]]) {
  def render(blockChar: Char = '*', blankChar: Char = '.', xScale: Int = 1): String =
    occupiedCells
      .map(_.map(occupied => (if (occupied) blockChar else blankChar).toString * xScale).mkString)
      .mkString("\n")
}

object View {
  def on(world: World, width: Int, height: Int): View = {
    val rows = 0 until height
    val columns = 0 until width

    View(rows
      .map(row => columns
        .map(column => Coord(column, row))
        .map(cellCoord => world.exists(_.occupiesSpaceAt(cellCoord)))))
  }
}