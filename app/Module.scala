import com.google.inject.AbstractModule
import hatenarss.helpers.{Json4sSerializer, JsonSerializer}
import hatenarss.services.{CachedService, HatenaEntryService, HatenaEntryServiceWS, PlayCachedService}

class Module extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[HatenaEntryService]).to(classOf[HatenaEntryServiceWS])
    bind(classOf[JsonSerializer]).to(classOf[Json4sSerializer])
    bind(classOf[CachedService]).to(classOf[PlayCachedService])
  }
}
