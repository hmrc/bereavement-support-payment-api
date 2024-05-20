package uk.gov.hmrc.bereavementsupportpaymentapi.config

import javax.inject.{Inject, Singleton}
import uk.gov.hmrc.play.bootstrap.config.ServicesConfig

trait AppConfig {

  def backendBaseUrl(): String

  def backendEnvironment(): String

  def backendToken(): String
}

@Singleton
class AppConfigImpl @Inject()(configuration: ServicesConfig)
  extends AppConfig {

  private val backendServicePrefix = "microservice.services.des"
  //TODO check backendServicePrefix value

  override lazy val backendBaseUrl: String = configuration.baseUrl("des")
  override lazy val backendEnvironment: String = configuration.getString(s"$backendServicePrefix.env")
  override lazy val backendToken: String = configuration.getString(s"$backendServicePrefix.token")
}
