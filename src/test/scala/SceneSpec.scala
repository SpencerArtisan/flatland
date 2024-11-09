import org.scalatest.flatspec._
import org.scalatest.matchers._

class SceneSpec extends AnyFlatSpec with should.Matchers {

  "A scene" should "render a world if it has no size" in {
    Scene.from(World(0, 0)).render() should be("")
  }

  it should "render a single rectangle" in {
    Scene.from(World(1, 1)).render() should be(".")
  }

  it should "render a single row" in {
    Scene.from(World(3, 1)).render() should be("...")
  }

  it should "render a single column" in {
    Scene.from(World(1, 3)).render() should be(".\n.\n.")
  }

  it should "render multiple rows and columns" in {
    Scene.from(World(3, 2)).render() should be("...\n...")
  }

  it should "not render a dot outside the world" in {
    Scene.from(World(2, 2).add(Dot(100), Coord(-1, -1))).render() should be("..\n..")
  }

  it should "render a single dot in the top left" in {
    Scene.from(World(2, 2).add(Dot(100), Coord(0, 0))).render() should be("*.\n..")
  }

  it should "render a single dot in the bottom right" in {
    Scene.from(World(2, 2).add(new Dot(100), Coord(1, 1))).render() should be("..\n.*")
  }

  it should "render multiple dots" in {
    Scene.from(World(2, 2).add(Dot(100), Coord(0, 0))
      .add(Dot(101), Coord(1, 1)))
      .render() should be("*.\n.*")
  }

  it should "render colocated dots as one dot" in {
    Scene.from(World(2, 2).add(Dot(100), Coord(0, 0))
      .add(Dot(101), Coord(0, 0)))
      .render() should be("*.\n..")
  }

  it should "render a circle" in {
    Scene.from(World(3, 3).add(Circle(100, 1), Coord(1, 1)))
      .render() should be(".*.\n***\n.*.")
  }

  it should "render a rectangle" in {
    Scene.from(World(3, 3).add(Rectangle(100, 2, 1), Coord(1, 1)))
      .render() should be("...\n.**\n...")
  }

  it should "render a rectangle rotated through 90 degrees" in {
    Scene.from(World(3, 3).add(Rectangle(100, 3, 1), Coord(1, 1), Math.PI / 2))
      .render() should be(".*.\n.*.\n.*.")
  }

  it should "rotate a rectangle after placement" in {
    val s = for {
      w <- World(3, 3).add(Rectangle(100, 3, 1), Coord(1, 1)).rotate(100, Math.PI / 2)
      scene = Scene.from(w).render()
    } yield scene
    s should be(Right(".*.\n.*.\n.*."))
  }

  it should "render a rectangle which fills the entire world" in {
    Scene.from(World(3, 3).add(Rectangle(100, 5, 5), Coord(1, 1)))
      .render() should be("***\n***\n***")
  }

  it should "not render a rectangle which is outside the world" in {
    Scene.from(World(3, 3).add(Rectangle(100, 5, 5), Coord(100, 100)))
      .render() should be("...\n...\n...")
  }
}