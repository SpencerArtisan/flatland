case class View(occupiedCells: Seq[Seq[Boolean]]) {
  def render(blockChar: Char = '*', blankChar: Char = '.', xScale: Int = 1): String =
    occupiedCells
      .map(_.map(occupied => (if (occupied) blockChar else blankChar).toString * xScale).mkString)
      .mkString("\n")
}

object View {
  def on(world: World, width: Int, height: Int, observer: Coord = Coord(0, 0, -10)): View = {
    val rows = createDoubleRange(height)
    val columns = createDoubleRange(width)
    val screenZ = observer.z + 10

    View(rows
      .map(row => columns
        .map(column => Coord(column, row, screenZ))
        .map(screenCoord => View3D.screenToWorld(screenCoord, 0, observer))
        .map(worldCoord => world.exists(_.occupiesSpaceAt(worldCoord)))))
  }

  def createDoubleRange(n: Int): Seq[Double] = {
    if (n == 1) List(0) else {

      val totalRange = n.toDouble
      val step = totalRange / n
      val start = -totalRange / 2 + step / 2

      (0 until n).map(i => start + i * step)
    }
  }
}