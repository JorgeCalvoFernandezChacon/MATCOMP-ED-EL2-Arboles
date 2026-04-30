· Para encontrar el camino mínimo, tenemos el camino mínimo por aristas y el camino mínimo por pesos (véase en MainGrafo)
· Podemos comprobar si un grafo es disjunto, en ese caso, habrá nodos que no tienen nada en común.
· En primer lugar fijamos el orígen de la búsqueda en Einstein y buscamos su ciudad, dentro de su ciudad buscamos los nodos con la etiqueta
"nobel" y los almacenamos (véase en MainGrafo)
· Al añadir esa tripleta, realmente no hacemos ningun cambio relacinado con los nóbeles, para encontrar los lugares de nacimiento de los ganadores del
premio nóbel, recorremos todos los vértices, en búsqueda de la etiqueta "ganó" hacia "nobel" y anotamos su lugar de nacimiento (véase en MainGrafo)
· Tiene dos tipos de nodos:
    - Los vértices, que almacenan información de tipo T comparable, además tienen colas con datos sobre los caminos de los que son orígen y de los que 
      son destino, y por último guardan información importante para los caminos, como cual es el nodo padre, es decir, el nodo anterior y desde el cual
      hemos llegado a ese vértice, el peso acumulado que lleva el camino pasando por ese vértice, la distancia o si se ha visitado o no.
    - Por otro lado, tenemos los arcos o aristas, que conectan dos vértices, por ello almacenan información del vértice de orígen y el vértice de destino
      además de guardar la etiqueta, necesaria para los algoritmos de búsqueda y el peso de la arista, también necesaria para los caminos.
· Una ontología es es la rama de la filosofía que estudia el ser, la existencia y la naturaleza de la realidad, buscando categorizar qué cosas existen 
y cómo se relacionan, por lo que trasladado a nuestro campo, una ontología define que cosas existen en un sistema y cómo se relacionan entre sí. La relación
entre ontología y grafo es que las ontologías se suelen representar como grafos, con por ejemplo RDF. Podríamos crear un grafo para nuestro problema relacionado 
con los nóbeles, ya que la ontología necesita establecer las relaciones entre los nodos, por lo que necesitamos las etiquetas, en un grafo sin etiquetas
no se podría crear una ontología. Con la ontología podríamos recorrerla al igual que el grafo y establecer los caminos y relaciones necesarias