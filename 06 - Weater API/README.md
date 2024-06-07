# 🌦️ Weather Application

This is a Spring Boot application that provides weather information for a specific location. The application uses the Open-Meteo API to fetch the weather data.

## 🚀 Endpoints

The application provides the following endpoints:

1. **GET /getWeather**: 🌡️ This endpoint returns the current weather information for a specific location. The required parameters are `latitude` and `longitude`. For example, you can make a GET request to `/getWeather?latitude=52.52&longitude=13.41`. This endpoint will return a JSON with the current weather information.

2. **GET /getTemperature**: 🌡️ This endpoint returns the current temperature for a specific location. The required parameters are `latitude` and `longitude`. For example, you can make a GET request to `/getTemperature?latitude=52.52&longitude=13.41`. This endpoint will return a JSON with the current temperature information.

3. **GET /getHumidity**: 💧 This endpoint returns the current humidity for a specific location. The required parameters are `latitude` and `longitude`. For example, you can make a GET request to `/getHumidity?latitude=52.52&longitude=13.41`. This endpoint will return a JSON with the current humidity information.

4. **GET /getRain**: ☔ This endpoint returns the current rain information for a specific location. The required parameters are `latitude` and `longitude`. For example, you can make a GET request to `/getRain?latitude=52.52&longitude=13.41`. This endpoint will return a JSON with the current rain information.

## 🛠️ Setup

To run this application, you need to have Java 22 and Maven installed on your machine.

1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Run the command `mvn spring-boot:run` to start the application.

The application will start and run on `http://localhost:8080`.

## 📚 Dependencies

The application uses the following dependencies:

- Spring Boot Starter Web
- Spring Boot Starter Test
- Spring Boot Starter Webflux
- org.json:json

## 🤝 Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.