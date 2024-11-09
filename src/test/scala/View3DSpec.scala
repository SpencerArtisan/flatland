import org.scalatest.flatspec._
import org.scalatest.matchers._

class View3DSpec extends AnyFlatSpec with should.Matchers {

  "A translation from world to screen" should "translate a zero coordinate beyond the screen to zero on the screen" in {
    View3D.worldToScreen(Coord(0, 0, 30), 10) should be(Coord(0, 0))
  }

  it should "translate a coordinate beyond the screen" in {
    View3D.worldToScreen(Coord(3, 3, 30), 10) should be(Coord(1, 1))
  }

  it should "not translate a coordinate on the screen" in {
    View3D.worldToScreen(Coord(3, 3, 30), 30) should be(Coord(3, 3))
  }

  it should "translate a coordinate beyond the screen with a negative observer z" in {
    View3D.worldToScreen(Coord(3, 3, 30), 10, -10) should be(View3D.worldToScreen(Coord(3, 3, 40), 20, 0))
  }

  "A translation from screen to world" should "translate a zero coordinate to zero coordinate in the world" in {
    View3D.screenToWorld(Coord(0, 0, 10), 30) should be(Coord(0, 0, 30))
  }

  it should "translate a coordinate to the world" in {
    View3D.screenToWorld(Coord(1, 1, 10), 30) should be(Coord(3, 3, 30))
  }
}