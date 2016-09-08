package dao

import javax.inject.Inject

import models.Album
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

class AlbumDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider ) extends HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._


  private val AlbumTable = TableQuery[AlbumTable]

  class AlbumTable(tag: Tag) extends Table[Album](tag, "albums") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def artist = column[String]("artist")

    def title = column[String]("title")

    def year = column[Int]("year")

    def rating = column[Int]("rating")

    def * = (id.?, artist, title, year,rating) <> ((Album.apply _).tupled, Album.unapply _ )

  }


  def all(): Future[Seq[Album]] = db.run(AlbumTable.result)

  def insert(album: Album): Future[Unit] = db.run(AlbumTable += album).map { _ => () }


}