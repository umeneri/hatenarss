package hatenarss.services

import hatenarss.models.HatenaRssItem

import scala.concurrent.Future

trait HatenaEntryService {
  def getHotEntryItems(keyword: String, page: Int): Future[Seq[HatenaRssItem]]

  def getRankingItems(period: String, page: Int): Future[Seq[HatenaRssItem]]
}
