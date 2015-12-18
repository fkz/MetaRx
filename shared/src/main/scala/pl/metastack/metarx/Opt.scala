package pl.metastack.metarx

import scala.concurrent.{ExecutionContext, Future}

object Opt {
  @inline def apply[T](): Opt[T] = Var(Option.empty[T])
  @inline def apply[T](value: T): Opt[T] = Var(Some(value))

  def fromOption[T](future: Future[Option[T]])(implicit exec: ExecutionContext): Opt[T] = {
    val opt = Opt[T]()
    future.foreach(v => opt.produce(v))
    opt
  }

  def from[T](future: Future[T])(implicit exec: ExecutionContext): Opt[T] = {
    val opt = Opt[T]()
    future.foreach(v => opt.produce(Some(v)))
    opt
  }
}
