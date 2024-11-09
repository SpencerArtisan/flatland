import org.scalatest.flatspec._
import org.scalatest.matchers._

class ViewSpec extends AnyFlatSpec with should.Matchers {

  "A view" should "render if it has no size" in {
    View.on(World(), 0, 0).render() should be("")
  }

  it should "render a single rectangle" in {
    View.on(World(), 1, 1).render() should be(".")
  }

  it should "render a single row" in {
    View.on(World(), 3, 1).render() should be("...")
  }

  it should "render a single column" in {
    View.on(World(), 1, 3).render() should be(".\n.\n.")
  }

  it should "render multiple rows and columns" in {
    View.on(World(), 3, 2).render() should be("...\n...")
  }

  it should "render a circle" in {
    View.on(World().add(Circle(100, 1), Coord.ZERO), 3, 3)
      .render() should be(".*.\n***\n.*.")
  }

  it should "render a small rectangle" in {
    View.on(World().add(Rectangle(100, 3, 1), Coord.ZERO), 3, 1)
      .render() should be("***")
  }

  it should "render a large rectangle" in {
    View.on(World().add(Rectangle(100, 4, 2), Coord.ZERO), width = 6, height = 4, observer = Coord(0, 0, -10))
      .render() should be("......\n.****.\n.****.\n......")
  }

  it should "render a smaller rectangle when the view screen is moved towards the observer" in {
    val world = World().add(Rectangle(100, 4, 2), Coord.ZERO)
    View.on(world, width = 6, height = 4, screenZ = -5, observer = Coord(0, 0, -10))
      .render() should be("......\n..**..\n..**..\n......")
  }

  it should "render a rectangle rotated through 90 degrees" in {
    View.on(World().add(Rectangle(100, 3, 1), Coord.ZERO, Math.PI / 2), 3, 3)
      .render() should be(".*.\n.*.\n.*.")
  }

  it should "rotate a rectangle after placement" in {
    val s = for {
      w <- World().add(Rectangle(100, 3, 1), Coord.ZERO).rotate(100, Math.PI / 2)
      scene = View.on(w, 3, 3).render()
    } yield scene
    s should be(Right(".*.\n.*.\n.*."))
  }

  it should "render a rectangle which fills the entire world" in {
    View.on(World().add(Rectangle(100, 5, 5), Coord.ZERO), 3, 3)
      .render() should be("***\n***\n***")
  }

  it should "not render a rectangle which is outside the world" in {
    View.on(World().add(Rectangle(100, 5, 5), Coord(100, 100)), 3, 3)
      .render() should be("...\n...\n...")
  }
}