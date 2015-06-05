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

package models.database.brand

import models.brand.BrandLink
import play.api.db.slick.Config.driver.simple._

/**
 * Connects BrandLink object with its database representation
 */
private[models] object BrandLinks extends Table[BrandLink]("BRAND_LINK") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def brandId = column[Long]("BRAND_ID")
  def linkType = column[String]("LINK_TYPE", O.DBType("VARCHAR(10)"))
  def link = column[String]("LINK", O.DBType("VARCHAR(254)"))

  def * = id.? ~ brandId ~ linkType ~ link <> (BrandLink.apply _, BrandLink.unapply _)

  def forInsert = * returning id

}