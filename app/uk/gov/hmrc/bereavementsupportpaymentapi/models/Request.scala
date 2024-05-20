package uk.gov.hmrc.bereavementsupportpaymentapi.models.errors

import java.time.LocalDate
import scala.util.matching.Regex

case class Request(nino: String,
                   forename: String,
                   surname: String,
                   dateOfBirth: LocalDate,
                   dateRange: String
                  )
  object Request {

    def createWithoutCorrelationId(nino: String,
                                   forename: String,
                                   surname: String,
                                   dateOfBirth: LocalDate,
                                   dateRange: String): Request =
      Request(nino, forename, surname, dateOfBirth, dateRange)
// todo: finish validation logic
    val ninoPattern: Regex = """^((?!(BG|GB|KN|NK|NT|TN|ZZ)|(D|F|I|Q|U|V)[A-Z]|[A-Z](D|F|I|O|Q|U|V))[A-Z]{2})[0-9]{6}[A-D\s]?$""".r

    //todo: find out value constraints for request parameters and add validation

    /*val checkBrickPattern: Regex = """^([A-Z]{1}[ A-Z-'.]{2,4})$""".r
    val genderPattern: Regex = """^[MF]$""".r
    val readAmount: BigDecimal => Boolean = {
      case amount if amount < BigDecimal("0") => false
      case amount if amount > BigDecimal("999999999.99") => false
      case amount if amount.scale > 2 => false
      case _ => true*/
    }

  }
