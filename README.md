If you thought Monads are a mystery, then this demonstration would show you how to evolve your code towards a Monad without knowing about it. 
This demo will neither go into any Category Theory nor begin with monadic laws.  Instead, we will start with typical code that you see in your daily life as a developer, attempt to DRY (Don't Repeat Yourself) it up and eventually use Monad to remove duplication and verbosity. 
You'll also see how Monads make your code more declarative and succinct by sequencing the steps in your domain logic.

Also, we know in Java8 Checked Exceptions + λ == Pain!  To be more precise, we will evolve a Try (exception handling monad) which is missing in Java8, similar to one found in Scala. 
Finally, map back Try to the 3 monad laws.

Overview of sample example to be refactored.
* Refactor by DRYing
* Make failure (checked and unchecked exceptions) explicit by introducing Try (unit - create a monad).
* Introduce bind/flatMap (return a new monad)
* Map the above in Monad terminology.
* Refactor example to completion by introducing recoverWith, orElse and forEach 

Other Resources
* Youtube Video:

<a href="https://www.youtube.com/watch?feature=player_embedded&v=_ykDhFYRaQ8" target="_blank"><img src="http://img.youtube.com/vi/_ykDhFYRaQ8/0.jpg" alt="YouTube Video" width="260" height="200" border="10" /></a>
* Slides:

<a href="//www.slideshare.net/DhavalDalal/drying-tomonadsinjava8" title="DRYing to Monad in Java8" target="_blank">
<img src="https://image.slidesharecdn.com/drying-to-monads-in-java8-150912064904-lva1-app6891/95/drying-to-monad-in-java8-1-638.jpg?cb=1448126829" alt="DRYing to Monad in Java8 Presentation on Slideshare" width="260" height="200" border="10"/></a> </strong>
