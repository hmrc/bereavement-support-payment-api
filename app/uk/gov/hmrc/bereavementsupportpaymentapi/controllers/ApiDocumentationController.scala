package uk.gov.hmrc.bereavementsupportpaymentapi.controllers

import controllers.Assets
import play.api.http.HttpErrorHandler
import play.api.libs.json.{JsObject, Json}

import javax.inject.{Inject, Singleton}
import play.api.mvc.{Action, AnyContent, ControllerComponents}
import uk.gov.hmrc.bereavementsupportpaymentapi.config.ApiDefinitionConfig

import scala.concurrent.Future

//TODO DocumentationController an old or deprecated api?
import uk.gov.hmrc.api.controllers.DocumentationController

@Singleton
class ApiDocumentationController @Inject()(cc: ControllerComponents,
                                           assets: Assets,
                                           errorHandler: HttpErrorHandler,
                                           apiConfig: ApiDefinitionConfig)
  extends DocumentationController(cc, assets, errorHandler) {

  override def definition(): Action[AnyContent] = Action.async {

    lazy val apiAccess: JsObject = Json.obj(
      "type" -> apiConfig.accessType()
    )

    val apiDefinition = Json.parse(
      s"""
         |{
         |  "scopes": [
         |    {
         |      "key": "read:state-pension-calculation",
         |      "name": "Get State Pension calculation",
         |      "description": "Get State Pension calculation"
         |    }
         |  ],
         |  "api": {
         |    "name": "Get State Pension Calculation",
         |    "description": "Get an Individuals State Pension calculation",
         |    "context": "individuals/state-pension-calculation",
         |    "categories": ["PRIVATE_GOVERNMENT"],
         |    "versions": [
         |      {
         |        "version": "1.0",
         |        "status": "${apiConfig.status()}",
         |        "endpointsEnabled": ${apiConfig.endpointsEnabled()},
         |        "access" : $apiAccess
         |      }
         |    ]
         |  }
         |}
      """.stripMargin
    )
    //todo update api def

    Future.successful(Ok(apiDefinition))
  }

  def specification(version: String, file: String): Action[AnyContent] = {
    assets.at(s"/public/api/conf/$version", file)
  }
  //todo spec function: do we need? remove if not
}
