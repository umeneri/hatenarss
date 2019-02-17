import com.google.inject.{Guice, Injector}
import org.openqa.selenium.chrome.ChromeDriver
import org.scalatest.selenium.{Driver, WebBrowser}
import org.scalatest.time.{Seconds, Span}
import org.scalatest.{FlatSpec, Matchers}

trait HeadlessChrome extends WebBrowser with Driver {
  val injector: Injector = Guice.createInjector(new SeleniumModule)
  val headlessDriver: ChromeDriver = injector.getInstance(classOf[ChromeDriver])

  implicit val webDriver: ChromeDriver = headlessDriver
}

class SeleniumSpec extends FlatSpec with Matchers with HeadlessChrome {
  setCaptureDir("./tmp")

  "The blog app home page" should "have the correct title" in {
    go to "http://localhost:8080/"

    implicitlyWait(Span(1, Seconds))
    pageTitle should be ("frontend")

    capture to "MyScreenShot.png"

    val cardHeaderTitles = findAll(query = ClassNameQuery("media-content")).toList
    cardHeaderTitles.size should be > 0

    val bookmarkButtons = findAll(query = ClassNameQuery("fa-bookmark-o")).toList
    bookmarkButtons.size should be > 0

    quit()
  }
}
