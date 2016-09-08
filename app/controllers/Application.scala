package controllers


import play.api._
import play.api.mvc._
import javax.inject.Inject

import play.api.data._
import play.api.data.Forms._
import dao.AlbumDAO
import models.Album
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms.text
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.Action
import play.api.mvc.Controller

import scala.concurrent.Future

class Application @Inject() (albumDAO: AlbumDAO) extends Controller {



  def index = Action.async { implicit request =>
    //Ok(views.html.index("Your new application is ready."))
    albumDAO.all().map{albums => Ok(views.html.index(albums))}
  }

  val albumForm = Form(
    mapping(
      "artist" -> text(),
      "title" -> text(),
      "year" -> number,
      "rating" -> number
    )(Album.myApply)(Album.myUnApply)
  )


  def insertAlbum = Action.async { implicit request =>
    val a : Album = albumForm.bindFromRequest.get
    albumDAO.insert(a).map(_ => Redirect(routes.Application.index))
  }

}


/*

class Application @Inject() (catDao: CatDAO, dogDao: DogDAO) extends Controller {

  def index = Action.async {
    catDao.all().zip(dogDao.all()).map {case (cats, dogs) => Ok(views.html.index(cats, dogs)) }
  }

  val catForm = Form(
    mapping(
      "name" -> text(),
      "color" -> text()
    )(Cat.apply)(Cat.unapply)
  )

  val dogForm = Form(
    mapping(
      "name" -> text(),
      "color" -> text()
    )(Dog.apply)(Dog.unapply)
  )

  def insertCat = Action.async { implicit request =>
    val cat: Cat = catForm.bindFromRequest.get
    catDao.insert(cat).map(_ => Redirect(routes.Application.index))
  }

  def insertDog = Action.async { implicit request =>
    val dog: Dog = dogForm.bindFromRequest.get
    dogDao.insert(dog).map(_ => Redirect(routes.Application.index))
  }
}


*/
