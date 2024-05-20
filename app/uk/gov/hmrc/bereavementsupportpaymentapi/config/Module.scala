package uk.gov.hmrc.bereavementsupportpaymentapi.config

import com.google.inject.AbstractModule


class Module extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[AppConfig]).to(classOf[AppConfigImpl]).asEagerSingleton()
    bind(classOf[ApiDefinitionConfig]).to(classOf[ApiDefinitionConfigImpl]).asEagerSingleton()
  }
}
