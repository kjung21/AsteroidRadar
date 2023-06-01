# Asteroid Radar

## API

- [NASA API](https://api.nasa.gov/)
- base url: https://api.nasa.gov/neo/rest/v1/feed?
- query parameters: 
  - start_date=2023-05-21&
  - end_date=2023-05-22&
  - api_key=DEMO_KEY
- key: near_earth_objects
- fields of interest: 
  - id
  - absolute_magnitude_h
  - estimated_diameter (kms)
  - is_potentially_hazardous_asteroid
  - close_approach_data -> relative_velocity -> kilometers_per_hour
  - close_approach_data -> miss_distance -> astronomical
- NASA image of the day
  - url: https://api.nasa.gov/planetary/apod?api_key=YOUR_API_KEY
  - media_type: image, ignore video
  - title: content_description

## [Secrets](https://guides.codepath.com/android/storing-secret-keys-in-android)

## [Coroutines](https://developer.android.com/kotlin/coroutines)

```
Lightweight: The app can run many coroutines on a single thread.
Fewer memory leaks: We can launch coroutines in the specific scope of the operation 
                    we are performing instead of launching them in the global scope.
Built-in cancellation support: Cancellation is propagated automatically through the running coroutine hierarchy.
Jetpack integration: Many Jetpack libraries include extensions that provide full coroutines support. 
Some libraries also provide their own coroutine scope that you can use for structured concurrency.
```

