/*
 * Happy Melly Teller
 * Copyright (C) 2013 - 2015, Happy Melly http://www.happymelly.com
 *
 * This file is part of the Happy Melly Teller.
 *
 * Happy Melly Teller is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Happy Melly Teller is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Happy Melly Teller.  If not, see <http://www.gnu.org/licenses/>.
 *
 * If you have questions concerning this license or the applicable additional
 * terms, you may contact by email Sergey Kotlov, sergey.kotlov@happymelly.com or
 * in writing Happy Melly One, Handelsplein 37, Rotterdam, The Netherlands, 3071 PR
 */
package controllers.apiv2

import models.{ Brand, PeopleCollection, Person }
import play.api.libs.json._
import play.mvc.Controller
import views.Languages

/**
 * Facilitators API
 */
trait FacilitatorsApi extends Controller with ApiAuthentication {

  implicit val facilitatorWrites = new Writes[Person] {
    def writes(person: Person): JsValue = {
      Json.obj(
        "id" -> person.id.get,
        "first_name" -> person.firstName,
        "last_name" -> person.lastName,
        "photo" -> person.photo.url,
        "country" -> person.address.countryCode,
        "languages" -> person.languages.map(r ⇒ Languages.all.getOrElse(r.language, "")).toList,
        "countries" -> person.countries.map(_.country).toList)
    }
  }

  /**
   * Returns a list of facilitators for the given brand in JSON format
   *
   * @param code Brand code
   */
  def facilitators(code: String) = TokenSecuredAction(readWrite = false) {
    implicit request ⇒
      implicit token ⇒
        Brand.find(code) map { brand ⇒
          val facilitators = Brand.findFacilitators(code, brand.coordinator)
          PeopleCollection.countries(facilitators)
          PeopleCollection.languages(facilitators)
          PeopleCollection.addresses(facilitators)
          jsonOk(Json.toJson(facilitators))
        } getOrElse jsonNotFound("Unknown brand")
  }
}

object FacilitatorsApi extends FacilitatorsApi with ApiAuthentication
