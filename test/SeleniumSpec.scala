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
  val host = "http://localhost:8080/"

  setCaptureDir("./tmp")

  "The blog app home page" should "have the correct title" in {
    go to host

    implicitlyWait(Span(1, Seconds))
    pageTitle should be ("frontend")

    capture to "MyScreenShot.png"

    val element: Option[Element] = find(query = ClassNameQuery("title"))
    element.get.text shouldBe "Hotentry"

    val cardHeaderTitles = findAll(query = ClassNameQuery("card-header-title")).toList
    cardHeaderTitles.size should be > 0

    cardHeaderTitles.head.attribute("href").isDefined shouldBe true
    
    quit()
  }
}
