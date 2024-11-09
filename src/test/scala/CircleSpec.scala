import org.scalatest.flatspec._
import org.scalatest.matchers._

class CircleSpec extends AnyFlatSpec with should.Matchers {

  "A circle" should "occupy a circle space" in {
    val circle = Circle(100, 2)
    circle.occupiesSpaceAt(Coord(0, 0)) should be (true)
    circle.occupiesSpaceAt(Coord(2, 0)) should be (true)
    circle.occupiesSpaceAt(Coord(0, 2)) should be (true)
    circle.occupiesSpaceAt(Coord(-2, 0)) should be (true)
    circle.occupiesSpaceAt(Coord(0, -2)) should be (true)
    circle.occupiesSpaceAt(Coord(1, 1)) should be (true)
    circle.occupiesSpaceAt(Coord(-1, -1)) should be (true)
    circle.occupiesSpaceAt(Coord(1.8, 1.8)) should be (false)
    circle.occupiesSpaceAt(Coord(-1.8, -1.8)) should be (false)
  }
}