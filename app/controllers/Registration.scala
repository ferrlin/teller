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

package controllers

import models.service.Services
import play.api.mvc._
import securesocial.core.SecureSocial

/**
 * Contains actions for a registration process
 */
class Registration extends Controller with Security with Services {

  /**
   * The authentication flow for all providers starts here.
   *
   * @param provider The id of the provider that needs to handle the call
   */
  def authenticate(provider: String) = Action { implicit request ⇒
    val session = request.session -
      SecureSocial.OriginalUrlKey +
      (SecureSocial.OriginalUrlKey -> routes.Registration.step2.url)
    Redirect("/authenticate/" + provider).withSession(session)
  }

  /**
   * Renders step 1 page of the registration process
   */
  def step1 = Action { implicit request ⇒
    Ok(views.html.registration.step1())
  }

  /**
   * Renders step 2 page of the registration process
   * @return
   */
  def step2 = Action { implicit request ⇒
    Ok(views.html.registration.step1())
  }

  /**
   * Renders step 3 page of the registration process
   * @return
   */
  def step3 = Action { implicit request ⇒
    Ok(views.html.registration.step1())
  }
}

object Registration extends Registration with Security with Services
