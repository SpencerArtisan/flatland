object Main {
  private val CLEAR = "\u001b[2J"
  private val BLOCK = '\u2588'
  private val SHAPE_1_ID = 100
  private val SHAPE_2_ID = 101
  private val SHAPE_3_ID = 102

  def main(args: Array[String]): Unit =
    animate(buildAnimationFrames(buildWorld))

  private def buildWorld =
    World()
      .add(Rectangle(SHAPE_1_ID, 160, 70), Coord(-30, -10))
      .add(Rectangle(SHAPE_2_ID, 30, 70), Coord(80, 50))
      .add(Circle(SHAPE_3_ID, 24), Coord(76, -25))

  private def buildAnimationFrames(world: World): Seq[String] =
    LazyList.from(0).map(rotateShapes(world, _)).collect {
      case Right((frame, world)) => View.on(world, 260, 200, Coord(- frame, - frame, -10 - frame / 4.0)).render(BLOCK, ' ', 2)
    }

  private def animate(frames: Seq[String]): Unit =
    frames.foreach { frame =>
      Console.print(CLEAR)
      Console.print(frame)
      Thread.sleep(50)
    }

  private def rotateShapes(world: World, frameIndex: Int): Either[NoSuchShape, (Int, World)] =
    for {
      world1 <- world.rotate(SHAPE_1_ID, frameIndex * Math.PI / 20)
      world2 <- world1.rotate(SHAPE_2_ID, frameIndex * Math.PI / -12)
      world3 <- world2.move(SHAPE_3_ID, -frameIndex / 10.0, frameIndex / 10.0)
    } yield (frameIndex, world3)
}