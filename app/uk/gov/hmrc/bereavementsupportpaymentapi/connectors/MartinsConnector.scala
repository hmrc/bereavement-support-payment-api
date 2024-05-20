package uk.gov.hmrc.bereavementsupportpaymentapi.connectors

import uk.gov.hmrc.http.Request

class MartinsConnector {
 def hitHip(request: Request): String = {
  val successString = "You've hit the connector"
   successString
 }


}
