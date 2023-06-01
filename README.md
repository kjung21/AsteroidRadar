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

## References

- [Secrets](https://guides.codepath.com/android/storing-secret-keys-in-android)
- [Coroutines](https://developer.android.com/kotlin/coroutines)
- [Detect memory leaks](https://square.github.io/leakcanary)

