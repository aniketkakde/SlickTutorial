package models

import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile

case class Album( id: Option[Long],artist: String, title: String,year: Int,rating : Int)

object Album{



  def myApply(artist: String, title: String,year: Int,rating : Int) =  Album(None, artist, title, year, rating)

  def myUnApply(album: Album) : Option[(String, String, Int, Int)] = Some((album.artist, album.title, album.year, album.rating))

}

/*sealed abstract class Rating(val stars: Int)

object Rating {

  case object Awesome extends Rating(5)

  case object Good extends Rating(4)

  case object NotBad extends Rating(3)

  case object Meh extends Rating(2)

  case object Aargh extends Rating(1)

  // columnType and to/From methods will go in here

  def fromInt(stars: Int): Rating = stars match {

    case 5 => Awesome
    case 4 => Good
    case 3 => NotBad
    case 2 => Meh
    case 1 => Aargh
    case _ => sys.error("Ratings only apply from 1 to 5")

  }

  def toInt(rating: Rating): Int = rating.stars

  implicit val columnType: BaseColumnType[Rating] = MappedColumnType.base[Rating, Int](Rating.toInt, Rating.fromInt)

  /* def all(): Future[Seq[Album]] = db.run(Album.result)

     def insert(album: Album): Future[Unit] = db.run(Album += album).map { _ => () }*/

}*/

 /* val selectAllAction: DBIO[Seq[(Artist, Album)]] =
    ArtistTable.join(AlbumTable)
      .on     { case (artist, album) => artist.id === album.artistId }
      .sortBy { case (artist, album) => artist.name.asc }
      .result*/


