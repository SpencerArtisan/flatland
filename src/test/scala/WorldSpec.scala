import org.scalatest.flatspec._
import org.scalatest.matchers._
import org.scalatest.EitherValues._


class WorldSpec extends AnyFlatSpec with should.Matchers {

  it should "support adding a rectangle" in {
    val world = World().add(Rectangle(100, 2, 1), Coord(1, 1))
    world.exists(placement => placement.shape.id == 100) should be (true)
  }

  it should "support rotating a rectangle" in {
    val world = for {
      w <- World().add(Rectangle(100, 3, 1), Coord(1, 1)).rotate(100, 0.2)
      w2 <- w.rotate(100, 0.3)
    } yield w2

    world.value.exists(_.shape.id == 100) should be (true)
    world.value.exists(_.rotatedRadians == 0.5) should be (true)
  }

  it should "support moving a rectangle" in {
    val world = for {
      w <- World().add(Rectangle(100, 3, 1), Coord(1, 1)).move(100, 0.2, 1.4)
      w2 <- w.move(100, 0.3, 1.5)
    } yield w2

    world.value.exists(_.shape.id == 100) should be (true)
    world.value.exists(_.center == Coord(1.5, 3.9)) should be (true)
  }
}