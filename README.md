# Performance Documentation

### Current

In the current methods we can see that the program takes around 107 ms to run
![alt text]()

I have run the program a couple times, and the difference is very minor (+- 5 ms)

In the profiler I was unable to find any information pointing to an error, however in the method we can see that there are couple null pointers when adding characters, these characters I believe to be the spaces, and the special characters (*,;,"", etc)


#### Bottlenecks

![alt text]()

When we look at the objects, we notice that "Long" and "HeapCharBuffer" are the ones that are taking the most time

### Hypothesis

From looking at the performance of the objects I have realized that in order to optimize the program, I needed to do something about the HeapCharBuffer and the java.lang.Long. 

I believed that if the file is loaded into a string it would be more performant since it does not need to read it on runtime.

### New Performance

#### 1. Loading File to String

The first attempt was to read the file into a string and see what difference it made, you can see the outcome in the image below.

###### Metrics for the Methods

![alt text]()

Now we notice a slight change in the performance of the method, but not super significant

###### Metrics for Objects

![alt text]()

Here we notice that the HeapCharBuffer is not present, however all the load is currently on the Long. It was a success in regards to optimizing on the HeapCharBuffer, since we are not reading a file on runtime, but lets try to push it some more and optimize on Long.

#### 2. Optimizing Long

First of all, is it even necessary to use Long for achieving our purpose? The answer is No, so let's get rid of it and create a HashMap<Integer, Integer>

###### Metrics on Methods

![alt text]()

As we can see, there is a major improvement compared to where we started. 

I believe that my profiling is running without erros, since I have debugged the results, and there is nothing stuck in a loop.

###### Metrics for Objects

![alt text]()

Here we can see that we no longer have problems with the Long, however it was replaced by Integer. Further optimization could be done on this, but it might not be necessary.




