import com.google.inject.AbstractModule
import com.typesafe.config.Config
import org.openqa.selenium.chrome.ChromeDriver
import software.reinvent.commons.config.ConfigProvider
import software.reinvent.headless.chrome.provider.HeadlessChromeProvider

class SeleniumModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[Config]).toProvider(classOf[ConfigProvider])
    bind(classOf[ChromeDriver]).toProvider(classOf[HeadlessChromeProvider])
  }
}
