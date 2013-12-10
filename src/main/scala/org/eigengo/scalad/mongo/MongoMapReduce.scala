package org.eigengo.scalad.mongo

import com.mongodb.casbah.Imports._

trait MongoMapReduce {

  def mapReduce[T: CollectionProvider](mapJS: JSFunction,
                                       reduceJS: JSFunction,
                                       query: Option[DBObject] = None,
                                       output: MapReduceOutputTarget = MapReduceInlineOutput,
                                       sort: Option[DBObject] = None,
                                       limit: Option[Int] = None,
                                       finalizeJS: Option[JSFunction] = None,
                                       jsScope: Option[DBObject] = None,
                                       verbose: Boolean = false): Iterable[DBObject] = {
    val coll = implicitly[CollectionProvider[T]].getCollection
    val res =
      coll.mapReduce(mapJS, reduceJS, output, query, sort, limit, finalizeJS, jsScope, verbose)
    res.cursor.toIterable
  }
}

