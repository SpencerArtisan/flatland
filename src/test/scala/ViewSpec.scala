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

  it should "not render a dot outside the world" in {
    View.on(World().add(Dot(100), Coord(-1, -1)), 2, 2).render() should be("..\n..")
  }

  it should "render a single dot in the top left" in {
    View.on(World().add(Dot(100), Coord(0, 0)), 2, 2).render() should be("*.\n..")
  }

  it should "render a single dot in the bottom right" in {
    View.on(World().add(new Dot(100), Coord(1, 1)), 2, 2).render() should be("..\n.*")
  }

  it should "render multiple dots" in {
    View.on(World().add(Dot(100), Coord(0, 0))
      .add(Dot(101), Coord(1, 1)), 2, 2)
      .render() should be("*.\n.*")
  }

  it should "render colocated dots as one dot" in {
    View.on(World().add(Dot(100), Coord(0, 0))
      .add(Dot(101), Coord(0, 0)), 2, 2)
      .render() should be("*.\n..")
  }

  it should "render a circle" in {
    View.on(World().add(Circle(100, 1), Coord(1, 1)), 3, 3)
      .render() should be(".*.\n***\n.*.")
  }

  it should "render a rectangle" in {
    View.on(World().add(Rectangle(100, 2, 1), Coord(1, 1)), 3, 3)
      .render() should be("...\n.**\n...")
  }

  it should "render a rectangle rotated through 90 degrees" in {
    View.on(World().add(Rectangle(100, 3, 1), Coord(1, 1), Math.PI / 2), 3, 3)
      .render() should be(".*.\n.*.\n.*.")
  }

  it should "rotate a rectangle after placement" in {
    val s = for {
      w <- World().add(Rectangle(100, 3, 1), Coord(1, 1)).rotate(100, Math.PI / 2)
      scene = View.on(w, 3, 3).render()
    } yield scene
    s should be(Right(".*.\n.*.\n.*."))
  }

  it should "render a rectangle which fills the entire world" in {
    View.on(World().add(Rectangle(100, 5, 5), Coord(1, 1)), 3, 3)
      .render() should be("***\n***\n***")
  }

  it should "not render a rectangle which is outside the world" in {
    View.on(World().add(Rectangle(100, 5, 5), Coord(100, 100)), 3, 3)
      .render() should be("...\n...\n...")
  }
}