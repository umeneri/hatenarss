package hatenarss.services

import hatenarss.models.HatenaRssItem

import scala.concurrent.Future

trait HatenaRssService {
  def getHotEntryItems(keyword: String): Future[Seq[HatenaRssItem]]

  def getRankingItems(period: String): Future[Seq[HatenaRssItem]]
}
