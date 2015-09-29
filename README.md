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

<iframe src="//www.slideshare.net/slideshow/embed_code/key/MX7stPfSFKKcsa" width="425" height="355" frameborder="0" marginwidth="0" marginheight="0" scrolling="no" style="border:1px solid #CCC; border-width:1px; margin-bottom:5px; max-width: 100%;" allowfullscreen> </iframe> <div style="margin-bottom:5px"> <strong> <a href="//www.slideshare.net/DhavalDalal/drying-tomonadsinjava8" title="DRYing to Monad in Java8" target="_blank">DRYing to Monad in Java8</a> </strong> from <strong><a href="//www.slideshare.net/DhavalDalal" target="_blank">Dhaval Dalal</a></strong> </div>
