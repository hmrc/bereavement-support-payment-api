package uk.gov.hmrc.bereavementsupportpaymentapi.connectors

import uk.gov.hmrc.bereavementsupportpaymentapi.config.AppConfig
import models.{CalculationOutcome, CalculationRequest}
import play.api.http.HeaderNames
import uk.gov.hmrc.http.{HeaderCarrier, HttpClient}
import utils.AdditionalHeaderNames.{CorrelationIdHeader, Environment}
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class BackendConnector @Inject()(http: HttpClient,
                             appConfig: AppConfig) {
  //TODO confirm backend headers needed from backend openapi spec
  private[connectors] def backendHeaders(correlationId: String): Seq[(String, String)] =
    Seq(
      "Authorization" -> s"Bearer ${appConfig.backendToken()}",
      Environment -> appConfig.backendEnvironment(),
      CorrelationIdHeader -> correlationId,
      HeaderNames.CONTENT_TYPE -> "application/json"
    )

  // TODO refactor to point to HIP/NPS endpoint
  def getInitialCalculation(request: CalculationRequest)
                           (implicit hc: HeaderCarrier, ec: ExecutionContext): Future[CalculationOutcome] = {
    import connectors.httpParsers.GetCalculationHttpParser.getCalculationHttpReads
    val url = s"${appConfig.backendBaseUrl()}/individuals/pensions/ltb-calculation/initial/${request.nino}"
    http.POST(url, request, backendHeaders(request.correlationId))(implicitly, getCalculationHttpReads, hc, implicitly)
  }
}
