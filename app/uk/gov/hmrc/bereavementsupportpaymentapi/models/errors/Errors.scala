package uk.gov.hmrc.bereavementsupportpaymentapi.models.errors

import play.api.libs.json.{JsValue, Json, Writes}

case class Errors(errors: Seq[Error])

object Errors {
  def apply(error: Error): Errors = Errors(Seq(error))

  implicit val writes: Writes[Errors] = new Writes[Errors] {
    override def writes(data: Errors): JsValue = {
      if (data.errors.size > 1) {
        Json.obj("errors" -> Json.toJson(data.errors))
      } else {
        Json.toJson(data.errors.head)
      }
    }
  }
}
