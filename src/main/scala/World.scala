
case class World(width: Int, height: Int, private val shapes: Map[Int, Placement] = Map()) {
  def add(shape: Shape, coord: Coord, angle: Double = 0): World =
    this.copy(shapes = shapes + (shape.id -> Placement(coord, angle, shape)))

  def rotate(shapeId: Int, radians: Double): Either[NoSuchShape, World] =
    shapes.get(shapeId)
      .map(placement => this.copy(shapes = shapes + (shapeId -> placement.rotate(radians))))
      .toRight(NoSuchShape(shapeId))

  def move(shapeId: Int, x: Double, y: Double): Either[NoSuchShape, World] =
    shapes.get(shapeId)
      .map(placement => this.copy(shapes = shapes + (shapeId -> placement.move(x, y))))
      .toRight(NoSuchShape(shapeId))

  def exists(predicate: Placement => Boolean): Boolean =
    shapes.values.exists(predicate)
}
